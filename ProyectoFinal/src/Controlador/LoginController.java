package Controlador;

import Modelo.Alumno;
import Modelo.Secretaria;
import java.io.*;
import java.util.*;

/**
 *
 * @author Derek
 */
public class LoginController {
    private Map<String, Alumno> alumnos = new HashMap<>();
    private Map<String, Secretaria> secretarias = new HashMap<>();
    private final String SECRETARIAS_FILE = "secretarias.csv";

    public LoginController() {
        cargarAlumnos();
        cargarSecretarias();
    }

    // Método para cargar los alumnos desde un archivo CSV
    private void cargarAlumnos() {
        File file = new File("alumnos.csv");
        if (!file.exists()) {
            System.out.println("Archivo alumnos.csv no encontrado, no se cargarán alumnos.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // Saltar el encabezado si existe.
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Alumno alumno = new Alumno(data[0], data[1], Float.parseFloat(data[2]), Float.parseFloat(data[3]), Integer.parseInt(data[4]));
                alumnos.put(alumno.getNumeroCuenta(), alumno);
            }
            System.out.println("Alumnos cargados exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar las secretarias desde un archivo CSV
    private void cargarSecretarias() {
        File file = new File(SECRETARIAS_FILE);
        if (!file.exists()) {
            // Crear el archivo si no existe, pero solo con un encabezado, sin sobrescribir datos existentes.
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                writer.println("Nombre,Usuario,Contrasena"); // Encabezado solo al crear el archivo por primera vez.
                System.out.println("Archivo secretarias.csv creado por primera vez.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Cargar secretarias desde el archivo existente
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // Saltar el encabezado si existe
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) { // Verificar que tenga todos los campos necesarios
                    Secretaria secretaria = new Secretaria(data[0], data[1], data[2]);
                    secretarias.put(secretaria.getUsuario(), secretaria);
                }
            }
            System.out.println("Secretarias cargadas exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para acceder a un alumno
    public double accederAlumno(String numeroCuenta, String contrasena) {
        Alumno alumno = alumnos.get(numeroCuenta);
        if (alumno != null && alumno.getContrasena().equals("pass" + numeroCuenta)) {
            return alumno.getNumeroInscripcion();
        }
        return -1;
    }

    // Método para acceder a una secretaria
    public boolean accederSecretaria(String usuario, String contrasena) {
        Secretaria secretaria = secretarias.get(usuario);
        return secretaria != null && secretaria.getContrasena().equals(contrasena);
    }

    // Método para obtener la lista de alumnos
    public List<Alumno> getAlumnos() {
        return new ArrayList<>(alumnos.values());
    }

    // Método para buscar un alumno por número de cuenta
    public Alumno buscarAlumnoPorNumeroCuenta(String numeroCuenta) {
        return alumnos.get(numeroCuenta);
    }

    // Método para registrar una nueva secretaria
    public boolean registrarSecretaria(String nombre, String usuario, String contrasena) {
        if (secretarias.containsKey(usuario)) {
            return false; // Si ya existe una secretaria con el mismo usuario, no la registramos.
        }

        // Agregar la nueva secretaria al archivo CSV sin sobrescribir el contenido existente.
        try (PrintWriter writer = new PrintWriter(new FileWriter(SECRETARIAS_FILE, true))) {
            writer.printf("%s,%s,%s%n", nombre, usuario, contrasena);
            secretarias.put(usuario, new Secretaria(nombre, usuario, contrasena));
            System.out.println("Secretaria registrada exitosamente y guardada en secretarias.csv.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
