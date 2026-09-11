package org.example.registroclientes.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class SolicitudServicioController {

    @FXML
    private TextField txtCliente;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtAsunto;
    @FXML
    private TextField txtRutaArchivo;
    @FXML
    private TextField txtRutaCarpeta;
    @FXML
    private ComboBox<String> cmbTipoCliente;
    @FXML
    private ComboBox<String> cmbTipoServicio;
    @FXML
    private ToggleGroup grupoPrioridad;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private Button btnExaminarArchivo;
    @FXML
    private Button btnExaminarCarpeta;
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
        cmbTipoServicio.getItems().addAll(
                "Soporte Técnico",
                "Instalación",
                "Mantenimiento");

        btnExaminarArchivo.setOnAction(
                e -> txtRutaArchivo.setText(seleccionarArchivo()));
        btnExaminarCarpeta.setOnAction(
                e -> txtRutaCarpeta.setText(seleccionarCarpeta()));

        btnLimpiar.setOnAction(e -> limpiarCampos());
        btnCerrar.setOnAction(e -> ((Stage) btnCerrar.getScene().getWindow()).close());
        btnGuardar.setOnAction(e -> procesarFormulario());
    }

    private void procesarFormulario() {
        if (validarCampos()) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "La solicitud fue guardada correctamente.");
        }
    }

    private boolean validarCampos() {
        if (txtCliente.getText().isBlank() || txtCorreo.getText().isBlank() || txtAsunto.getText().isBlank() ||
                cmbTipoCliente.getValue() == null || cmbTipoServicio.getValue() == null ||
                txtDescripcion.getText().isBlank() || txtRutaArchivo.getText().isBlank() || txtRutaCarpeta.getText().isBlank()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de validación", "Todos los campos son obligatorios.");
            return false;
        }
        if (grupoPrioridad.getSelectedToggle() == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de validación", "Debe seleccionar una prioridad.");
            return false;
        }
        if (!txtCorreo.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de validación", "El correo electrónico no es válido.");
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        txtCliente.clear();
        txtCorreo.clear();
        txtAsunto.clear();
        txtDescripcion.clear();
        txtRutaArchivo.clear();
        txtRutaCarpeta.clear();
        cmbTipoCliente.getSelectionModel().clearSelection();
        cmbTipoServicio.getSelectionModel().clearSelection();
        if (grupoPrioridad.getSelectedToggle() != null) {
            grupoPrioridad.getSelectedToggle().setSelected(false);
        }
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