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
        if(validate.validateLengthText(user, 25) ||
           validate.validateLengthText(name, 50) ||
           validate.validateLengthText(lastName, 50) ||
           validate.validateLengthText(email, 50) ||
           validate.validateLengthText(password, 50)){
            
        }
    }
}