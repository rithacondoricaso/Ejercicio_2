import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Laptop> laptops = new ArrayList<>();
        laptops.add(crearLaptop("Lenovo", new Pantalla("IPS", 1920, 1080), new Aplicacion("Visual Studio Code", 500), new Aplicacion("Python", 80), new Aplicacion("Chrome", 300)));
        laptops.add(crearLaptop("HP", new Pantalla("LED", 1366, 768), new Aplicacion("Word", 250), new Aplicacion("Excel", 200)));
        laptops.add(crearLaptop("Asus", new Pantalla("OLED", 2560, 1440), new Aplicacion("Photoshop", 900), new Aplicacion("Discord", 150), new Aplicacion("Spotify", 180), new Aplicacion("Firefox", 280)));

        System.out.print("Cantidad de laptops a registrar adicionalmente (0 si no desea): ");
        int n = leerEntero(sc);
        for (int i = 0; i < n; i++) {
            System.out.println("Laptop " + (i + 1));
            System.out.print("Modelo: "); String modelo = sc.nextLine();
            System.out.print("Tipo de pantalla: "); String tipo = sc.nextLine();
            System.out.print("Ancho de pantalla: "); int ancho = leerEntero(sc);
            System.out.print("Alto de pantalla: "); int alto = leerEntero(sc);
            Laptop laptop = new Laptop(modelo, new Pantalla(tipo, ancho, alto));
            System.out.print("Cantidad de aplicaciones: "); int cantidad = leerEntero(sc);
            for (int j = 0; j < cantidad && j < 50; j++) {
                System.out.print("Nombre de aplicación: "); String nombre = sc.nextLine();
                System.out.print("Tamaño en MB: "); int tamanio = leerEntero(sc);
                laptop.agregarAplicacion(new Aplicacion(nombre, tamanio));
            }
            laptops.add(laptop);
        }

        System.out.println("\n--- Todas las laptops ---");
        for (Laptop laptop : laptops) laptop.mostrar();

        System.out.println("\n--- Laptop con más aplicaciones ---");
        mostrarMayorCantidad(laptops);

        System.out.print("\nModelo de laptop a la que desea agregar una aplicación: ");
        String modeloBuscado = sc.nextLine();
        Laptop encontrada = buscarModelo(laptops, modeloBuscado);
        if (encontrada != null) {
            System.out.print("Nombre de la aplicación: "); String nombre = sc.nextLine();
            System.out.print("Tamaño en MB: "); int tamanio = leerEntero(sc);
            if (encontrada.agregarAplicacion(new Aplicacion(nombre, tamanio))) System.out.println("Aplicación agregada.");
            else System.out.println("No se pudo agregar la aplicación (límite de 50).");
        } else System.out.println("No existe una laptop con ese modelo.");

        System.out.print("\nAncho de pantalla Y para eliminar sus aplicaciones: ");
        int anchoY = leerEntero(sc);
        for (Laptop laptop : laptops) {
            if (laptop.getPantalla().getAncho() == anchoY) laptop.eliminarAplicaciones();
        }

        System.out.println("\n--- Laptop(s) con más aplicaciones después de eliminar ---");
        mostrarMayorCantidad(laptops);

        System.out.println("\n--- Aplicaciones ordenadas por laptop (tamaño descendente, nombre ascendente si empatan) ---");
        for (Laptop laptop : laptops) {
            laptop.ordenarAplicaciones();
            laptop.mostrar();
        }
        sc.close();
    }

    private static Laptop crearLaptop(String modelo, Pantalla pantalla, Aplicacion... aplicaciones) {
        Laptop laptop = new Laptop(modelo, pantalla);
        for (Aplicacion app : aplicaciones) laptop.agregarAplicacion(app);
        return laptop;
    }

    private static int leerEntero(Scanner sc) {
        while (true) {
            try {
                int valor = Integer.parseInt(sc.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número entero válido: ");
            }
        }
    }

    private static Laptop buscarModelo(List<Laptop> laptops, String modelo) {
        for (Laptop laptop : laptops) if (laptop.getModelo().equalsIgnoreCase(modelo)) return laptop;
        return null;
    }

    private static void mostrarMayorCantidad(List<Laptop> laptops) {
        if (laptops.isEmpty()) { System.out.println("No hay laptops registradas."); return; }
        int max = 0;
        for (Laptop laptop : laptops) max = Math.max(max, laptop.getCantApps());
        for (Laptop laptop : laptops) if (laptop.getCantApps() == max) laptop.mostrar();
    }
}
