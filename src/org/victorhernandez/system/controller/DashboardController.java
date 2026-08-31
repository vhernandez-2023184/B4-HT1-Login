/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.victorhernandez.system.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import org.victorhernandez.system.model.User;
import org.victorhernandez.system.utils.UserSession;
import org.victorhernandez.system.utils.ViewFactory;

/**
 *
 * @author informatica
 */
public class DashboardController implements Initializable {

    @FXML private Label lblWelcome;
    @FXML private StackPane paneCenter;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User userLogueado = UserSession.getInstanciaUserSession().getUserLogueado();
        if (userLogueado != null) {
            lblWelcome.setText("Bienvenido, " + userLogueado.getName() + " " + userLogueado.getLastname());
        }
    }

    @FXML
    public void onInicio(MouseEvent event) {
        mostrarMensajeCentro("Inicio");
    }

    @FXML
    public void onPerfil(MouseEvent event) {
        mostrarMensajeCentro("Perfil");
    }

    @FXML
    public void onConfiguracion(MouseEvent event) {
        mostrarMensajeCentro("Configuración");
    }

    @FXML
    public void onLogout(MouseEvent event) {
        UserSession.getInstanciaUserSession().setUserLogueado(null);
        ViewFactory viewFacto = new ViewFactory();
        viewFacto.viewLogin();
    }

    /**
     * Placeholder: aquí, según la Hoja de Trabajo, más adelante se cargarán
     * los formularios reales dentro del paneCenter (StackPane).
     */
    private void mostrarMensajeCentro(String seccion) {
        paneCenter.getChildren().clear();
        Label label = new Label("Sección: " + seccion + " (próximamente)");
        label.getStyleClass().add("etiqueta");
        paneCenter.getChildren().add(label);
    }
}
