/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.victorhernandez.system.utils;

import org.victorhernandez.system.model.User;

/**
 *
 * @author javih
 */
public class UserSession {

    private static UserSession instanciaUserSession;
    private User userLogueado;

    private UserSession() {
    }

    public static UserSession getInstanciaUserSession() {
        if (instanciaUserSession == null) {
            instanciaUserSession = new UserSession();
        }
        return instanciaUserSession;
    }

    public User getUserLogueado() {
        return userLogueado;
    }

    public void setUserLogueado(User userLogueado) {
        this.userLogueado = userLogueado;
    }
}
