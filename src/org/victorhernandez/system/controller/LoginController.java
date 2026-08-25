/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
    package org.victorhernandez.system.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import org.victorhernandez.system.utils.ViewFactory;
/**
 *
 * @author victor
 */
public class LoginController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    @FXML
    public void onRegister(MouseEvent event){
        ViewFactory viewFacto= new ViewFactory();
        viewFacto.viewRegister();
    }
    
}
