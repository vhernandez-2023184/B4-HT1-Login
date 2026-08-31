/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.victorhernandez.system.repository;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import org.victorhernandez.system.config.ConexionDB;
import org.victorhernandez.system.model.User;

public class AuthenticacionRepository implements AuthenticationInterface {

    //CallableStatement
    private CallableStatement callSP;

    //ConexionDB
    private ConexionDB conexionDB = ConexionDB.getInstanciaConexionDB();

    public AuthenticacionRepository() {
    }

    @Override
    public User login(String email, String password) {
        User user = null;
        try {
            callSP = conexionDB.getConnection().prepareCall("{call sp_login(?,?)}");
            callSP.setString(1, email);
            callSP.setString(2, password);
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
            System.out.println("Error al iniciar sesion");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return user;
    }
}
