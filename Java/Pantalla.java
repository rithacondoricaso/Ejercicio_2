public class Pantalla {
    private String tipo;
    private int ancho;
    private int alto;

    public Pantalla(String tipo, int ancho, int alto) {
        this.tipo = tipo;
        this.ancho = ancho;
        this.alto = alto;
    }

    public String getTipo() { return tipo; }
    public int getAncho() { return ancho; }
    public int getAlto() { return alto; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setAncho(int ancho) { this.ancho = ancho; }
    public void setAlto(int alto) { this.alto = alto; }

    @Override
    public String toString() {
        return tipo + " " + ancho + "x" + alto;
    }
}
