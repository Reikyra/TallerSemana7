import javax.swing.table.DefaultTableModel;

public class ListaDobleAvengers {
    private NodoDobleAvenger inicio;
    private NodoDobleAvenger fin;

    public ListaDobleAvengers() {
        inicio = null;
        fin = null;
    }

    public void agregarAvenger(Avenger avenger) {
        NodoDobleAvenger nuevoNodo = new NodoDobleAvenger(avenger);
        if (inicio == null) {
            inicio = fin = nuevoNodo;
        } else {
            fin.siguiente = nuevoNodo;
            nuevoNodo.anterior = fin;
            fin = nuevoNodo;
        }
    }

    public Avenger buscarAvenger(int id) {
        NodoDobleAvenger actual = inicio;
        while (actual != null) {
            if (actual.avenger.getId() == id) return actual.avenger;
            actual = actual.siguiente;
        }
        return null;
    }

    public boolean eliminarAvenger(int id) {
        NodoDobleAvenger actual = inicio;
        while (actual != null) {
            if (actual.avenger.getId() == id) {
                if (actual == inicio) inicio = actual.siguiente;
                if (actual == fin) fin = actual.anterior;
                if (actual.anterior != null) actual.anterior.siguiente = actual.siguiente;
                if (actual.siguiente != null) actual.siguiente.anterior = actual.anterior;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public void listarEnTabla(DefaultTableModel modeloTabla) {
        modeloTabla.setRowCount(0);
        NodoDobleAvenger actual = inicio;
        while (actual != null) {
            Avenger avenger = actual.avenger;
            modeloTabla.addRow(new Object[]{avenger.getId(), avenger.getNombre(), avenger.getMision(),
                    avenger.getPeligrosidad(), avenger.getPagoMensual()});
            actual = actual.siguiente;
        }
    }

    public void listarConCalculos(DefaultTableModel modeloTabla) {
        modeloTabla.setRowCount(0);
        NodoDobleAvenger actual = inicio;
        while (actual != null) {
            Avenger avenger = actual.avenger;
            modeloTabla.addRow(new Object[]{
                    avenger.getNombre(),
                    String.format("%.2f", avenger.getPagoMensual()), // Pago Mensual
                    avenger.calcularAporteFondoHeroesFormateado(),  // Aporte Fondo Héroes
                    avenger.calcularImpuestoGobiernoFormateado(),  // Impuesto Gobierno
                    avenger.calcularPagoNetoFormateado()           // Pago Neto
            });
            actual = actual.siguiente;
        }
    }

    public void ordenarPorPeligrosidad() {
        if (inicio == null || inicio.siguiente == null) return;

        for (NodoDobleAvenger i = inicio; i != null; i = i.siguiente) {
            NodoDobleAvenger actual = inicio;
            NodoDobleAvenger siguiente = inicio.siguiente;

            for (; siguiente != null; actual = siguiente, siguiente = siguiente.siguiente) {
                if (actual.avenger.getPeligrosidad() > siguiente.avenger.getPeligrosidad()) {
                    Avenger temp = actual.avenger;
                    actual.avenger = siguiente.avenger;
                    siguiente.avenger = temp;
                }
            }
        }
    }
}
