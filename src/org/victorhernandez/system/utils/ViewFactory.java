/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.victorhernandez.system.utils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import org.victorhernandez.system.ClasePrincipal;

/**
 *
 * @author informatica
 */
public class ViewFactory {

    private final String PATH_VIEWS = "/org/victorhernandez/system/view/";

    public Scene loadFileXML(String nameFXML, int width, int height) {
        String pathOfFile = PATH_VIEWS + nameFXML;
        try {
            //FXML Loader
            FXMLLoader loaderFXML = new FXMLLoader();
            // leer lamURL del archivo
            URL urlFile = ClasePrincipal.class.getResource(pathOfFile);
            loaderFXML.setBuilderFactory(new JavaFXBuilderFactory());
            loaderFXML.setLocation(urlFile);

            return new Scene(loaderFXML.load(), width, height);

        } catch (IOException e) {
            throw new UncheckedIOException(e);
            
            
        }
    }

    public void loadScene(String nameFXML) {
        Scene scene = null;
        try {
            switch (nameFXML) {

                case "login" -> {
                    SceneManager.getInstanciaSceneManager().getStagePrincipal().setTitle("Login de Usuarios");
                    SceneManager.getInstanciaSceneManager().getStagePrincipal().setResizable(false);
                    scene = loadFileXML("LoginView.fxml", 300, 400);
                }
                case "register" ->{
                    SceneManager.getInstanciaSceneManager().getStagePrincipal().setTitle("REGISTRO USUARIO");
                    SceneManager.getInstanciaSceneManager().getStagePrincipal().setResizable(false);
                    scene = loadFileXML("RegisterView.fxml", 450, 550);
                }
                case "dashboard" -> {
                    SceneManager.getInstanciaSceneManager().getStagePrincipal().setTitle("Dashboard");
                    SceneManager.getInstanciaSceneManager().getStagePrincipal().setResizable(true);
                    scene = loadFileXML("DashboardView.fxml", 600, 450);
                }
                default ->
                    scene = loadFileXML("LoginView.fxml", 300, 400);
            }
            SceneManager.getInstanciaSceneManager().changeScene(scene);
        } catch (NullPointerException objetoNulo) {
            //Alert
            System.out.println("error load scene");

        }
    }

    public void viewLogin() {
        loadScene("login");
    }
    public void viewRegister(){
        loadScene("register");
    }
    public void viewDashboard(){
        loadScene("dashboard");
    }
}
