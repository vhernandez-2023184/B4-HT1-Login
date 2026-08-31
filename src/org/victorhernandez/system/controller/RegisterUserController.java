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
import org.victorhernandez.system.service.UserService;
import org.victorhernandez.system.service.UserStatus;
import org.victorhernandez.system.utils.AlertInformation;
import org.victorhernandez.system.utils.Validations;
import org.victorhernandez.system.utils.ViewFactory;
/**
 *
 * @author informatica
 */
public class RegisterUserController implements Initializable {
 
    @FXML private TextField txtUser;
    @FXML private TextField txtName;
    @FXML private TextField txtLastName;
    @FXML private TextField txtEmail;
    @FXML private PasswordField pwdPassword;
    @FXML private TextField pwdConfirmPassword;    
    private Validations validate = new Validations();
    private AlertInformation alertInfo = new AlertInformation();
    private UserService userService = new UserService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    @FXML
    public void onCancel(MouseEvent event) {
        ViewFactory viewFacto = new ViewFactory();
        viewFacto.viewLogin();
    }
 @FXML
    public void onCreateUser(MouseEvent event) {
        boolean isEmailValid = validate.validateEmail(txtEmail.getText().trim());
 
        if (isEmailValid == false) {
            alertInfo.viewAlert("ERROR", "ERROR EMAIL", "ERROR DE CAMPO", "HAS INGRESADO UN EMAIL INCORRECTO");
            return;
        }
        
        String user, name, email, lastName, password, confirmPass;
        user = txtUser.getText().trim();
        name = txtName.getText().trim();
        email = txtEmail.getText().trim();
        lastName = txtLastName.getText().trim();
        password = pwdPassword.getText().trim();
        confirmPass = pwdConfirmPassword.getText().trim();
 
        if (validate.emptyText(user) == true
                || validate.emptyText(name) == true
                || validate.emptyText(lastName) == true
                || validate.emptyText(email) == true
                || validate.emptyText(password) == true
                || validate.emptyText(confirmPass) == true) {
            alertInfo.viewAlert("ERROR", "ERROR DE CAMPOS VACIOS",
                    "ERROR DE CAMPO",
                    "DEJÓ CAMPOS VACIOS DEL FORMULARIO");
 
            return;
        }
        
        String msgField="";
        if(validate.validateLengthText(user, 25)== false){
            msgField="El campo Usuario es mayor a 25 caracteres";
      }
        if( 
           validate.validateLengthText(name, 50)== false){
             msgField="El campo Nombre es mayor a 50 caracteres";
        }
        if( validate.validateLengthText(lastName, 50)== false){
             msgField="El campo Apellidos es mayor a 50 caracteres";
        }
        if(validate.validateLengthText(email, 50)== false){
            msgField="El campo EMAIL es mayor a 50 caracteres";
        }
        if(validate.validateLengthText(password, 35)==false){
             msgField="El campo PASSWORD es mayor a 50 caracteres";
         }
        if(msgField.isEmpty()== false){
            alertInfo.viewAlert("ERROR", "ERRO DE CAMPO", "ERROR", msgField);
           return;
        }
        if(validate.equalsText(password, confirmPass)==false){
             alertInfo.viewAlert("ERROR", "ERRO DE CONTRASEÑA", "ERROR", "SUS CONTRASEÑAS NO COINCIDEN");
           return;
        }
        UserStatus status=
        userService.createUser(user, name, lastName, email, password);
        switch(status){
            case UserStatus.ERROR_USER_CREATE->
                alertInfo.viewAlert("ERROR", "ERROR AL CREAR USUARIO", "ERROR DE CREACION",
                        "OCURRIO UN ERROR AL CREAR EL USUARIO, INTENTE DE NUEVO");
            case UserStatus.USER_CREATED-> {
                alertInfo.viewAlert("INFORMATION", "CUENTA CREADA", "REGISTRO EXITOSO",
                        "SU CUENTA HA SIDO CREADA CORRECTAMENTE, YA PUEDE INICIAR SESION");
                ViewFactory viewFacto = new ViewFactory();
                viewFacto.viewLogin();
            }
            case UserStatus.FIELDS_EMPTY->
                System.out.println("Los campos no estan vacios");
            case UserStatus.VALUE_LENGHT_INVALID->
             System.out.println("Validar longitud  de texto");
            default -> System.out.println("Error desconocido");
        }
    }
    
}