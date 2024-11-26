package Controlador;

import java.io.*;
import java.util.*;
import Modelo.Alumno;
import Modelo.Secretaria;

public class LoginController {
    private Map<String, Alumno> alumnos; // Almacena los alumnos en memoria
    private Map<String, Secretaria> secretarias; // Almacena las secretarias en memoria
    private static final String ARCHIVO_ALUMNOS = "alumnos.csv";
    private static final String ARCHIVO_SECRETARIAS = "secretarias1.csv";

    public LoginController() {
        alumnos = new HashMap<>();
        secretarias = new HashMap<>();
        inicializarArchivos();
        cargarDatos();
    }

    private void inicializarArchivos() {
        inicializarArchivo(ARCHIVO_ALUMNOS, "numeroCuenta,nombre,creditos,calificacion,contrasena,numeroInscripcion,semestre");
        inicializarArchivo(ARCHIVO_SECRETARIAS, "nombre,usuario,contrasena");
    }

    private void inicializarArchivo(String archivo, String cabecera) {
        File file = new File(archivo);
        if (!file.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                writer.println(cabecera);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void cargarDatos() {
        cargarAlumnos();
        cargarSecretarias();
    }

    private void cargarAlumnos() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_ALUMNOS))) {
            String line = br.readLine(); // Saltar la cabecera
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 7) {
                    Alumno alumno = new Alumno(
                        data[1], // Nombre
                        data[0], // Número de cuenta
                        Float.parseFloat(data[2]), // Créditos
                        Float.parseFloat(data[3]), // Calificación
                        Integer.parseInt(data[6])  // Semestre
                    );
                    alumno.setContrasena(data[4]); // Asignar contraseña desde CSV
                    alumno.setNumeroInscripcion(Double.parseDouble(data[5])); // Asignar número de inscripción
                    alumnos.put(data[0], alumno); // Guardar en el mapa
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarSecretarias() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_SECRETARIAS))) {
            String line = br.readLine(); // Saltar la cabecera
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    Secretaria secretaria = new Secretaria(data[0], data[1], data[2]);
                    secretarias.put(data[1], secretaria);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Alumno> getAlumnos() {
        return new ArrayList<>(alumnos.values());
    }

    public Alumno buscarAlumnoPorNumeroCuenta(String numeroCuenta) {
        return alumnos.get(numeroCuenta);
    }

    public boolean accederSecretaria(String usuario, String contrasena) {
        Secretaria secretaria = secretarias.get(usuario);
        return secretaria != null && secretaria.getContrasena().equals(contrasena);
    }

    public boolean registrarSecretaria(String nombre, String usuario, String contrasena) {
        if (secretarias.containsKey(usuario)) {
            return false;
        }

        Secretaria nuevaSecretaria = new Secretaria(nombre, usuario, contrasena);
        secretarias.put(usuario, nuevaSecretaria);

        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO_SECRETARIAS))) {
            writer.println("nombre,usuario,contrasena");
            for (Secretaria secretaria : secretarias.values()) {
                writer.printf("%s,%s,%s%n", secretaria.getNombre(), secretaria.getUsuario(), secretaria.getContrasena());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}