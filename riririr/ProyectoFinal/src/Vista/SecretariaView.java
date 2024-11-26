package Vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import Modelo.Alumno;
import Controlador.LoginController;

public class SecretariaView extends JFrame {
    private static final long serialVersionUID = 1L;
    private final LoginController controller;
    private JList<String> alumnosList;
    private JTextArea detallesArea;
    private DefaultListModel<String> listModel;

    public SecretariaView(LoginController controller) {
        this.controller = controller;
        initComponents();
        cargarAlumnosOrdenados();
    }

    private void initComponents() {
        setTitle("Sistema de Gestión - Panel de Secretaria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        listModel = new DefaultListModel<>();
        alumnosList = new JList<>(listModel);
        alumnosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listaScroll = new JScrollPane(alumnosList);
        listaScroll.setPreferredSize(new Dimension(300, 400));

        detallesArea = new JTextArea();
        detallesArea.setEditable(false);
        JScrollPane detallesScroll = new JScrollPane(detallesArea);

        JPanel searchPanel = new JPanel(new FlowLayout());
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Buscar");
        searchPanel.add(new JLabel("Buscar alumno:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        add(searchPanel, BorderLayout.NORTH);
        add(listaScroll, BorderLayout.WEST);
        add(detallesScroll, BorderLayout.CENTER);

        alumnosList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarDetallesAlumno();
            }
        });

        searchButton.addActionListener(e -> {
            String busqueda = searchField.getText().toLowerCase();
            filtrarAlumnos(busqueda);
        });

        pack();
        setLocationRelativeTo(null);
        setSize(800, 600);
    }

    private void cargarAlumnosOrdenados() {
        List<Alumno> alumnos = controller.getAlumnos();

        // Ordenar alumnos por número de inscripción de forma ascendente
        alumnos.sort((a1, a2) -> Double.compare(a1.getNumeroInscripcion(), a2.getNumeroInscripcion()));

        listModel.clear();
        for (Alumno alumno : alumnos) {
            listModel.addElement(alumno.getNombre() + " (" + alumno.getNumeroCuenta() + ")");
        }
    }

    private void mostrarDetallesAlumno() {
        int selectedIndex = alumnosList.getSelectedIndex();
        if (selectedIndex != -1) {
            String seleccion = alumnosList.getSelectedValue();
            String numeroCuenta = seleccion.substring(
                seleccion.lastIndexOf("(") + 1,
                seleccion.lastIndexOf(")")
            );
            Alumno alumno = controller.buscarAlumnoPorNumeroCuenta(numeroCuenta);
            if (alumno != null) {
                detallesArea.setText(alumno.toString());
            }
        }
    }

    private void filtrarAlumnos(String busqueda) {
        List<Alumno> alumnos = controller.getAlumnos();

        // Ordenar alumnos por número de inscripción antes de filtrar
        alumnos.sort((a1, a2) -> Double.compare(a1.getNumeroInscripcion(), a2.getNumeroInscripcion()));

        listModel.clear();
        for (Alumno alumno : alumnos) {
            if (alumno.getNombre().toLowerCase().contains(busqueda) ||
                alumno.getNumeroCuenta().contains(busqueda)) {
                listModel.addElement(alumno.getNombre() + " (" + alumno.getNumeroCuenta() + ")");
            }
        }
    }
}
