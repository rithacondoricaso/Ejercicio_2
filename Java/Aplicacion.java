public class Aplicacion {
    private String nombre;
    private int tamanio;

    public Aplicacion(String nombre, int tamanio) {
        this.nombre = nombre;
        this.tamanio = tamanio;
    }

    public String getNombre() { return nombre; }
    public int getTamanio() { return tamanio; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTamanio(int tamanio) { this.tamanio = tamanio; }

    @Override
    public String toString() {
        return nombre + " (" + tamanio + " MB)";
    }
}
