/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package org.victorhernandez.system.repository;
 
import java.sql.CallableStatement;
import java.sql.ResultSet;
import org.victorhernandez.system.config.ConexionDB;
import org.victorhernandez.system.model.User;
 
public class UserRepository implements UserInterface {
 
    //CallableStatement
    private CallableStatement callSP;
 
    //ConexionDB
    private ConexionDB conexionDB = ConexionDB.getInstanciaConexionDB();
    public UserRepository(){
    }
 
    @Override
    public void create(User user) {
        try {
            callSP = conexionDB.getConnection().prepareCall("{call sp_create_users(?,?,?,?,?)}");
            callSP.setString(1, user.getName());
            callSP.setString(2, user.getLastname());
            callSP.setString(3, user.getEmail());
            callSP.setString(4, user.getUser());
            callSP.setString(5, user.getPassword());
            callSP.execute();
            callSP.close(); 
        } catch (Exception e) {
            System.out.println("Error al crear el usuario");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public User findByEmailOrUser(String login) {
        User user = null;
        try {
            callSP = conexionDB.getConnection().prepareCall("{call sp_find_user_by_login(?)}");
            callSP.setString(1, login);
            ResultSet resultSet = callSP.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getString("id_user"),
                        resultSet.getString("email"),
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getString("password"),
                        resultSet.getString("user")
                );
            }
            resultSet.close();
            callSP.close();
        } catch (Exception e) {
            System.out.println("Error al buscar el usuario");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return user;
    }
}