package org.example.registroclientes.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.registroclientes.model.Cliente;

import java.io.File;
import java.io.IOException;

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
        cmbTipoCliente.getItems().addAll("Individual", "Corporativo");

        btnSeleccionarDocumento.setOnAction(e -> txtRutaDocumento.setText(seleccionarArchivo()));
        btnSeleccionarDirectorio.setOnAction(e -> txtRutaDirectorio.setText(seleccionarCarpeta()));

        btnLimpiar.setOnAction(e -> limpiarCampos());
        btnCerrar.setOnAction(e -> ((Stage) btnCerrar.getScene().getWindow()).close());
        btnGuardar.setOnAction(e -> procesarFormulario());
        btnCrearSolicitud.setOnAction(e -> abrirSolicitudServicio());
    }

    private void procesarFormulario() {
        if (validarCampos()) {
            Cliente nuevoCliente = construirCliente();
            mostrarAlerta(Alert.AlertType.INFORMATION,
                    "Éxito",
                    "El cliente " + nuevoCliente.getNombre() + " ha sido registrado correctamente.");
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

    private Cliente construirCliente() {
        return new Cliente(
                txtNombre.getText().trim(),
                txtCorreo.getText().trim(),
                txtTelefono.getText().trim(),
                cmbTipoCliente.getValue(),
                txtRutaDocumento.getText().trim(),
                txtRutaDirectorio.getText().trim()
        );
    }

    private void abrirSolicitudServicio() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/registroclientes/solicitud-servicios.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Crear Solicitud de Servicio");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana de solicitud de servicios.");
            e.printStackTrace();
        }
    }
}
