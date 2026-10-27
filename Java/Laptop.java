import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Laptop {
    private String modelo;
    private int cantApps;
    private final List<Aplicacion> apps;
    private Pantalla pantalla;

    public Laptop(String modelo, Pantalla pantalla) {
        this.modelo = modelo;
        this.pantalla = pantalla;
        this.apps = new ArrayList<>();
        this.cantApps = 0;
    }

    public String getModelo() { return modelo; }
    public int getCantApps() { return apps.size(); }
    public List<Aplicacion> getApps() { return new ArrayList<>(apps); }
    public Pantalla getPantalla() { return pantalla; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setPantalla(Pantalla pantalla) { this.pantalla = pantalla; }

    public boolean agregarAplicacion(Aplicacion app) {
        if (app == null || apps.size() >= 50) return false;
        apps.add(app);
        cantApps = apps.size();
        return true;
    }

    public void eliminarAplicaciones() {
        apps.clear();
        cantApps = 0;
    }

    public void ordenarAplicaciones() {
        apps.sort(Comparator.comparingInt(Aplicacion::getTamanio).reversed()
                .thenComparing(Aplicacion::getNombre, String.CASE_INSENSITIVE_ORDER));
    }

    public void mostrar() {
        System.out.println("Laptop: " + modelo + " | Aplicaciones: " + apps.size() + " | Pantalla: " + pantalla);
        if (apps.isEmpty()) System.out.println("  Sin aplicaciones");
        for (Aplicacion app : apps) System.out.println("  - " + app);
    }
}
