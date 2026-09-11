package org.example.registroclientes.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class RegistroClientesController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtRutaDocumento;
    @FXML
    private TextField txtRutaDirectorio;
    @FXML
    private ComboBox<String> cmbTipoCliente;
    @FXML
    private Button btnSeleccionarDocumento;
    @FXML
    private Button btnSeleccionarDirectorio;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCrearSolicitud;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnCerrar;
    @FXML
    public void initialize() {
        cmbTipoCliente.getItems().addAll(
                "Individual",
                "Corporativo");

        btnSeleccionarDocumento.setOnAction(
                e -> txtRutaDocumento
                        .setText(seleccionarArchivo()));
        btnSeleccionarDirectorio.setOnAction(
                e -> txtRutaDirectorio
                        .setText(seleccionarCarpeta()));

        btnLimpiar.setOnAction(e -> limpiarCampos());
        btnCerrar.setOnAction(e -> ((Stage) btnCerrar.getScene().getWindow()).close());
        btnGuardar.setOnAction(e -> procesarFormulario());
    }

    private void procesarFormulario() {
        if (validarCampos()) {
            mostrarAlerta(Alert.AlertType.INFORMATION,
                    "Éxito",
                    "El cliente ha sido registrado correctamente.");
        }
    }

    private boolean validarCampos() {
        if (txtNombre.getText().isBlank() || txtCorreo.getText().isBlank() || txtTelefono.getText().isBlank() ||
                cmbTipoCliente.getValue() == null || txtRutaDocumento.getText().isBlank() || txtRutaDirectorio.getText().isBlank()) {
            mostrarAlerta(Alert.AlertType.ERROR,
                    "Error de validación",
                    "Todos los campos son obligatorios.");
            return false;
        }
        if (!txtCorreo.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            mostrarAlerta(Alert.AlertType.ERROR,
                    "Error de validación",
                    "El correo electrónico no es válido.");
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtCorreo.clear();
        txtTelefono.clear();
        txtRutaDocumento.clear();
        txtRutaDirectorio.clear();
        cmbTipoCliente.getSelectionModel().clearSelection();
    }

    private String seleccionarArchivo() {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(null);
        return (file != null) ? file.getAbsolutePath() : "";
    }

    private String seleccionarCarpeta() {
        DirectoryChooser dc = new DirectoryChooser();
        File dir = dc.showDialog(null);
        return (dir != null) ? dir.getAbsolutePath() : "";
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}