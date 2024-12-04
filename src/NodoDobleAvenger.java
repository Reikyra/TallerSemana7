public class NodoDobleAvenger {
    public Avenger avenger;
    public NodoDobleAvenger anterior;
    public NodoDobleAvenger siguiente;

    public NodoDobleAvenger(Avenger avenger) {
        this.avenger = avenger;
        this.anterior = null;
        this.siguiente = null;
    }
}
