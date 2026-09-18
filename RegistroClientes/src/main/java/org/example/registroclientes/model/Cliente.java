package org.example.registroclientes.model;

public class Cliente {
    private String nombre;
    private String correo;
    private String telefono;
    private String tipoCliente;
    private String rutaDocumento;
    private String rutaDirectorio;

    public Cliente(String nombre, String correo, String telefono, String tipoCliente, String rutaDocumento, String rutaDirectorio) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.tipoCliente = tipoCliente;
        this.rutaDocumento = rutaDocumento;
        this.rutaDirectorio = rutaDirectorio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getRutaDocumento() {
        return rutaDocumento;
    }

    public void setRutaDocumento(String rutaDocumento) {
        this.rutaDocumento = rutaDocumento;
    }

    public String getRutaDirectorio() {
        return rutaDirectorio;
    }

    public void setRutaDirectorio(String rutaDirectorio) {
        this.rutaDirectorio = rutaDirectorio;
    }
}