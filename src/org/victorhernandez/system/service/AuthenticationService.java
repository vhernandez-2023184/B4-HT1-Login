/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.victorhernandez.system.service;

import org.victorhernandez.system.model.User;
import org.victorhernandez.system.repository.AuthenticacionRepository;
import org.victorhernandez.system.utils.Validations;

public class AuthenticationService {

    private Validations validate = new Validations();
    private AuthenticacionRepository authRepo = new AuthenticacionRepository();
    private UserService userService = new UserService();
    private User loggedUser;

    public AuthenticationService() {
    }


    public AuthenticationStatus login(String emailOrUser, String password) {
        if (validate.emptyText(emailOrUser) == true
                || validate.emptyText(password) == true) {
            return AuthenticationStatus.FIELDS_EMPTY;
        }

        //Paso 1: preguntarle al UserService si existe un usuario con ese dato
        User existingUser = userService.findUser(emailOrUser);
        if (existingUser == null) {
            return AuthenticationStatus.NOT_EXIST_USER;
        }

        //Paso 2: si existe, validar la contraseña contra el AuthRepository
        User authenticatedUser = authRepo.login(emailOrUser, password);
        if (authenticatedUser == null) {
            return AuthenticationStatus.WRONG_PASSWORD;
        }

        loggedUser = authenticatedUser;
        return AuthenticationStatus.LOGIN_SUCCES;
    }

    public User getLoggedUser() {
        return loggedUser;
    }
}
