/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.victorhernandez.system.repository;

import org.victorhernandez.system.model.User;
import org.victorhernandez.system.config.ConexionDB;
import java.sql.CallableStatement;
/**
 *
 * @author informatica
 */
public class UserRepository implements UserInterface{
    //CallableStatement
    private CallableStatement callSP;
    //conexionDB
    private ConexionDB conexionDB = ConexionDB.getInstanciaConexionDB();
    
    @Override 
    public void create (User user){
        try {
            callSP= conexionDB.getConnection().prepareCall("{call sp_create_users(?,?,?,?,?)}");
            callSP.setString(1, user.getName());
            callSP.setString(2, user.getLastname());
            callSP.setString(3, user.getEmail());
            callSP.setString(4, user.getUser());
            callSP.setString(5, user.getPassword());
            
            callSP.execute();
            callSP.close();
            
        } catch (Exception e) {
        }
        
    }
}
