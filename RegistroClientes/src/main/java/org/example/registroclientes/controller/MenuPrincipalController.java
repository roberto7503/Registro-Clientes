package org.example.registroclientes.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MenuPrincipalController {

    @FXML
    private MenuItem itemRegistroClientes;

    @FXML
    private MenuItem itemSolicitudServicio;

    @FXML
    private MenuItem itemCerrarApp;

    @FXML
    public void initialize() {
        itemRegistroClientes.setOnAction(
                e -> abrirVentana(
                        "/org/example/registroclientes/registro-clientes.fxml",
                        "Registro de Clientes"));
        itemSolicitudServicio.setOnAction(
                e -> abrirVentana(
                        "/org/example/registroclientes/solicitud-servicios.fxml",
                        "Solicitud de Servicio"));
        itemCerrarApp.setOnAction(
                e -> System.exit(0));
    }

    private void abrirVentana(String rutaFXML, String titulo) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(rutaFXML));
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}