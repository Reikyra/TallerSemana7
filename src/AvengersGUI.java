import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AvengersGUI {

    private JPanel pGeneral;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtPagoMensual;
    private JComboBox Peligrosidad;
    private JComboBox Mision;
    private JButton listarButton;
    private JButton eliminarButton;
    private JButton buscarButton;
    private JButton agregarButton;
    private JButton ordenarButton;
    private JButton calcularAporteButton;
    private JTextArea txtAreaResultados; // TextArea para mostrar mensajes
    private JTable tablaAvengers;
    private JTable tablaListado;
    private JTable tablaCalculos;


    private ListaDobleAvengers lista; // Uso de ListaDobleAvengers
    private DefaultTableModel modeloAvengers;
    private DefaultTableModel modeloListado;
    private DefaultTableModel modeloCalculos;

    public AvengersGUI() {
        lista = new ListaDobleAvengers(); // Inicialización de la lista

        Mision.setModel(new DefaultComboBoxModel<>(new String[]{"Rescate", "Defensa", "Infiltración", "Neutralización"}));
        Peligrosidad.setModel(new DefaultComboBoxModel<>(new Integer[]{1, 2, 3, 4, 5}));

        modeloAvengers = new DefaultTableModel(new String[]{"ID", "Nombre", "Misión", "Peligrosidad", "Pago Mensual"}, 0);
        tablaAvengers.setModel(modeloAvengers);

        modeloListado = new DefaultTableModel(new String[]{"ID", "Nombre", "Misión", "Peligrosidad", "Pago Mensual"}, 0);
        tablaListado.setModel(modeloListado);

        modeloCalculos = new DefaultTableModel(new String[]{"Nombre", "Pago Mensual", "Aporte Fondo Héroes", "Impuesto Gobierno", "Pago Neto"}, 0);
        tablaCalculos.setModel(modeloCalculos);

        // Acción del botón "Agregar"

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    String nombre = txtNombre.getText();
                    String mision = (String) Mision.getSelectedItem();
                    int peligrosidad = (int) Peligrosidad.getSelectedItem();
                    double pagoMensual = Double.parseDouble(txtPagoMensual.getText());

                    // Verificar si el ID ya existe
                    if (lista.buscarAvenger(id) != null) {
                        txtAreaResultados.setText("Error: El ID " + id + " ya está registrado.");
                        return;
                    }

                    Avenger avenger = new Avenger(id, nombre, mision, peligrosidad, pagoMensual);
                    lista.agregarAvenger(avenger);

                    txtAreaResultados.setText("Avenger agregado con éxito: " + nombre);
                    lista.listarEnTabla(modeloAvengers);

                } catch (Exception ex) {
                    txtAreaResultados.setText("Error: Datos inválidos. Por favor revise los campos.");
                }

            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    Avenger encontrado = lista.buscarAvenger(id);

                    if (encontrado != null) {
                        txtAreaResultados.setText("Avenger encontrado: " + encontrado.getNombre());
                        txtNombre.setText(encontrado.getNombre());
                        Mision.setSelectedItem(encontrado.getMision());
                        Peligrosidad.setSelectedItem(encontrado.getPeligrosidad());
                        txtPagoMensual.setText(String.valueOf(encontrado.getPagoMensual()));
                    } else {
                        txtAreaResultados.setText("Avenger con ID " + id + " no encontrado.");
                    }
                } catch (Exception ex) {
                    txtAreaResultados.setText("Error: Ingrese un ID válido para buscar.");
                }


            }
        });


        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    if (lista.eliminarAvenger(id)) {
                        txtAreaResultados.setText("Avenger con ID " + id + " eliminado con éxito.");
                        lista.listarEnTabla(modeloAvengers);
                    } else {
                        txtAreaResultados.setText("Avenger con ID " + id + " no encontrado.");
                    }
                } catch (Exception ex) {
                    txtAreaResultados.setText("Error: Ingrese un ID válido para eliminar.");
                }

            }
        });


        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lista.listarEnTabla(modeloListado);
                txtAreaResultados.setText("Avengers listados exitosamente.");

            }
        });


        ordenarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lista.ordenarPorPeligrosidad();
                lista.listarEnTabla(modeloListado);
                txtAreaResultados.setText("Lista ordenada por nivel de peligrosidad.");

            }
        });


        calcularAporteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lista.listarConCalculos(modeloCalculos);
                txtAreaResultados.setText("Cálculos de aportes realizados exitosamente.");

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AvengersGUI");
        frame.setContentPane(new AvengersGUI().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

