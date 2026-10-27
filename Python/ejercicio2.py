class Aplicacion:
    def __init__(self, nombre, tamanio):
        self.nombre = nombre
        self.tamanio = tamanio

    def __str__(self):
        return f"{self.nombre} ({self.tamanio} MB)"


class Pantalla:
    def __init__(self, tipo, ancho, alto):
        self.tipo = tipo
        self.ancho = ancho
        self.alto = alto

    def __str__(self):
        return f"{self.tipo} {self.ancho}x{self.alto}"


class Laptop:
    def __init__(self, modelo, pantalla):
        self.modelo = modelo
        self.apps = []
        self.pantalla = pantalla

    @property
    def cant_apps(self):
        return len(self.apps)

    def agregar_aplicacion(self, app):
        if app is None or len(self.apps) >= 50:
            return False
        self.apps.append(app)
        return True

    def eliminar_aplicaciones(self):
        self.apps.clear()

    def ordenar_aplicaciones(self):
        self.apps.sort(key=lambda app: (-app.tamanio, app.nombre.casefold()))

    def mostrar(self):
        print(f"Laptop: {self.modelo} | Aplicaciones: {self.cant_apps} | Pantalla: {self.pantalla}")
        if not self.apps:
            print("  Sin aplicaciones")
        for app in self.apps:
            print(f"  - {app}")


def leer_entero(mensaje):
    while True:
        try:
            return int(input(mensaje))
        except ValueError:
            print("Ingrese un número entero válido.")


def buscar_modelo(laptops, modelo):
    for laptop in laptops:
        if laptop.modelo.casefold() == modelo.casefold():
            return laptop
    return None


def mostrar_mayor_cantidad(laptops):
    if not laptops:
        print("No hay laptops registradas.")
        return
    mayor = max(laptop.cant_apps for laptop in laptops)
    for laptop in laptops:
        if laptop.cant_apps == mayor:
            laptop.mostrar()


def main():
    laptops = [
        Laptop("Lenovo", Pantalla("IPS", 1920, 1080)),
        Laptop("HP", Pantalla("LED", 1366, 768)),
        Laptop("Asus", Pantalla("OLED", 2560, 1440)),
    ]
    laptops[0].agregar_aplicacion(Aplicacion("Visual Studio Code", 500))
    laptops[0].agregar_aplicacion(Aplicacion("Python", 80))
    laptops[0].agregar_aplicacion(Aplicacion("Chrome", 300))
    laptops[1].agregar_aplicacion(Aplicacion("Word", 250))
    laptops[1].agregar_aplicacion(Aplicacion("Excel", 200))
    laptops[2].agregar_aplicacion(Aplicacion("Photoshop", 900))
    laptops[2].agregar_aplicacion(Aplicacion("Discord", 150))
    laptops[2].agregar_aplicacion(Aplicacion("Spotify", 180))
    laptops[2].agregar_aplicacion(Aplicacion("Firefox", 280))

    n = leer_entero("Cantidad de laptops a registrar adicionalmente (0 si no desea): ")
    for i in range(max(0, n)):
        print(f"Laptop {i + 1}")
        modelo = input("Modelo: ")
        tipo = input("Tipo de pantalla: ")
        ancho = leer_entero("Ancho de pantalla: ")
        alto = leer_entero("Alto de pantalla: ")
        laptop = Laptop(modelo, Pantalla(tipo, ancho, alto))
        cantidad = leer_entero("Cantidad de aplicaciones (máximo 50): ")
        for _ in range(max(0, min(cantidad, 50))):
            nombre = input("Nombre de aplicación: ")
            tamanio = leer_entero("Tamaño en MB: ")
            laptop.agregar_aplicacion(Aplicacion(nombre, tamanio))
        laptops.append(laptop)

    print("\n--- Todas las laptops ---")
    for laptop in laptops:
        laptop.mostrar()

    print("\n--- Laptop con más aplicaciones ---")
    mostrar_mayor_cantidad(laptops)

    modelo = input("\nModelo de laptop a la que desea agregar una aplicación: ")
    laptop = buscar_modelo(laptops, modelo)
    if laptop:
        nombre = input("Nombre de la aplicación: ")
        tamanio = leer_entero("Tamaño en MB: ")
        if laptop.agregar_aplicacion(Aplicacion(nombre, tamanio)):
            print("Aplicación agregada.")
        else:
            print("No se pudo agregar la aplicación (límite de 50).")
    else:
        print("No existe una laptop con ese modelo.")

    ancho_y = leer_entero("\nAncho de pantalla Y para eliminar sus aplicaciones: ")
    for laptop in laptops:
        if laptop.pantalla.ancho == ancho_y:
            laptop.eliminar_aplicaciones()

    print("\n--- Laptop(s) con más aplicaciones después de eliminar ---")
    mostrar_mayor_cantidad(laptops)

    print("\n--- Aplicaciones ordenadas por laptop (tamaño descendente, nombre ascendente si empatan) ---")
    for laptop in laptops:
        laptop.ordenar_aplicaciones()
        laptop.mostrar()


if __name__ == "__main__":
    main()
