package Modelo;

/**
 *
 * @author Derek
 */
public class Alumno {
    private String nombre;
    private String numeroCuenta;
    private float creditos;
    private float calificacion;
    private String contrasena;
    private int numeroInscripcion;
    private int semestre;

    public Alumno() {
    }

    public Alumno(String nombre, String numeroCuenta, float creditos, float calificacion, int semestre) {
        this.nombre = nombre;
        this.numeroCuenta = numeroCuenta;
        this.creditos = creditos;
        this.calificacion = calificacion;
        this.semestre = semestre;
        this.contrasena = "pass" + numeroCuenta;
        this.numeroInscripcion = calcularNumeroInscripcion();
    }

    private int calcularNumeroInscripcion() {
        return (int) (Math.random() * 1000) + 1;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }

    public float getCreditos() { return creditos; }
    public void setCreditos(float creditos) { this.creditos = creditos; }

    public float getCalificacion() { return calificacion; }
    public void setCalificacion(float calificacion) { this.calificacion = calificacion; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public int getNumeroInscripcion() { return numeroInscripcion; }
    public void setNumeroInscripcion(int numeroInscripcion) { this.numeroInscripcion = numeroInscripcion; }

    public int getSemestre() { return semestre; }
    public void setSemestre(int semestre) { this.semestre = semestre; }
}
