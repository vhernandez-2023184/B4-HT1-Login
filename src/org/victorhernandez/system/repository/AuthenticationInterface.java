/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.victorhernandez.system.repository;

import org.victorhernandez.system.model.User;

public interface AuthenticationInterface {
    User login(String email, String password);
}