/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    private double numeroInscripcion;
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
    
    private double calcularNumeroInscripcion() {
        double escolaridad = (creditos / 400.0) * 100.0;
        double velocidad = (creditos / 400.0) * 100.0;
        return calificacion * escolaridad * velocidad;
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
    
    public double getNumeroInscripcion() { return numeroInscripcion; }
    public void setNumeroInscripcion(double numeroInscripcion) { this.numeroInscripcion = numeroInscripcion; }
    
    public int getSemestre() { return semestre; }
    public void setSemestre(int semestre) { this.semestre = semestre; }
    
    @Override
    public String toString() {
        return String.format("""
            Nombre: %s
            Número de Cuenta: %s
            Semestre: %d
            Créditos: %.2f
            Calificación: %.2f
            Número de Inscripción: %.4f
            """, nombre, numeroCuenta, semestre, creditos, calificacion, numeroInscripcion);
    }
}