package Modelo;

/**
 * Representa a una secretaria en el sistema.
 */
public class Secretaria {
    private String nombre;
    private String usuario;
    private String contrasena;

    public Secretaria() {
    }

    public Secretaria(String nombre, String usuario, String contrasena) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return String.format("Nombre: %s, Usuario: %s", nombre, usuario);
    }
}
