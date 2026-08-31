/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
    package org.victorhernandez.system.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.victorhernandez.system.model.User;
import org.victorhernandez.system.service.AuthenticationService;
import org.victorhernandez.system.service.AuthenticationStatus;
import static org.victorhernandez.system.service.AuthenticationStatus.FIELDS_EMPTY; //permite acceder directamente a los miembros estáticos públicos de una clase (campos y métodos)
import static org.victorhernandez.system.service.AuthenticationStatus.LOGIN_SUCCES;//permite acceder directamente a los miembros estáticos públicos de una clase (campos y métodos)
import static org.victorhernandez.system.service.AuthenticationStatus.NOT_EXIST_USER;//permite acceder directamente a los miembros estáticos públicos de una clase (campos y métodos)
import static org.victorhernandez.system.service.AuthenticationStatus.WRONG_PASSWORD;//permite acceder directamente a los miembros estáticos públicos de una clase (campos y métodos)
import org.victorhernandez.system.utils.AlertInformation;
import org.victorhernandez.system.utils.UserSession;
import org.victorhernandez.system.utils.ViewFactory;
/**
 *
 * @author victor
 */
public class LoginController implements Initializable {

    @FXML private TextField txtUserOrEmail;
    @FXML private PasswordField pwdPassword;

    private AuthenticationService authService = new AuthenticationService();
    private AlertInformation alertInfo = new AlertInformation();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    @FXML
    public void onRegister(MouseEvent event){
        ViewFactory viewFacto= new ViewFactory();
        viewFacto.viewRegister();
    }

    @FXML
    public void onLogin(MouseEvent event) {
        String userOrEmail = txtUserOrEmail.getText().trim();
        String password = pwdPassword.getText().trim();

        AuthenticationStatus status = authService.login(userOrEmail, password);

        switch (status) {
            case FIELDS_EMPTY ->
                alertInfo.viewAlert("ERROR", "ERROR DE CAMPOS VACIOS",
                        "ERROR DE CAMPO",
                        "DEBE INGRESAR SU USUARIO/CORREO Y CONTRASEÑA");
            case NOT_EXIST_USER ->
                alertInfo.viewAlert("ERROR", "USUARIO NO EXISTE",
                        "CUENTA NO ENCONTRADA",
                        "NO EXISTE UNA CUENTA CON ESE USUARIO O CORREO. DEBE REGISTRARSE");
            case WRONG_PASSWORD ->
                alertInfo.viewAlert("ERROR", "CONTRASEÑA INCORRECTA",
                        "ERROR DE CREDENCIALES",
                        "LA CONTRASEÑA INGRESADA NO ES CORRECTA");
            case LOGIN_SUCCES -> {
                User loggedUser = authService.getLoggedUser();
                UserSession.getInstanciaUserSession().setUserLogueado(loggedUser);
                ViewFactory viewFacto = new ViewFactory();
                viewFacto.viewDashboard();
            }
            default ->
                alertInfo.viewAlert("ERROR", "ERROR DESCONOCIDO", "ERROR", "OCURRIO UN ERROR INESPERADO");
        }
    }
}
