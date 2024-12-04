import java.text.DecimalFormat;

public class Avenger {
    private static final DecimalFormat df = new DecimalFormat("#.00");
    private int id;
    private String nombre;
    private String mision;
    private int peligrosidad;
    private double pagoMensual;

    public Avenger(int id, String nombre, String mision, int peligrosidad, double pagoMensual) {
        this.id = id;
        this.nombre = nombre;
        this.mision = mision;
        this.peligrosidad = peligrosidad;
        this.pagoMensual = pagoMensual;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMision() {
        return mision;
    }

    public int getPeligrosidad() {
        return peligrosidad;
    }

    public double getPagoMensual() {
        return pagoMensual;
    }

    public double calcularAporteFondoHeroes() {
        return pagoMensual * 0.08;
    }

    public double calcularImpuestoGobierno() {
        double pagoAnual = pagoMensual * 12;
        if (pagoAnual <= 50000) return 0;
        if (pagoAnual <= 100000) return (pagoAnual - 50000) * 0.1;
        if (pagoAnual <= 200000) return (pagoAnual - 100000) * 0.2 + 5000;
        return (pagoAnual - 200000) * 0.3 + 25000;
    }

    public double calcularPagoNeto() {
        return pagoMensual - calcularAporteFondoHeroes() - (calcularImpuestoGobierno() / 12);
    }

    public String calcularPagoNetoFormateado() {
        return df.format(calcularPagoNeto());
    }

    public String calcularAporteFondoHeroesFormateado() {
        return df.format(calcularAporteFondoHeroes());
    }

    public String calcularImpuestoGobiernoFormateado() {
        return df.format(calcularImpuestoGobierno());
    }
}
