package Modelos;

import Calculos.ConversorMonedasAPI;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuConversorMonedas {
    private final ConversorMonedasAPI conversor;//Declarando una variable que puede contener una referencia a un objeto de tipo ConversorMonedasAPI
    private boolean salir = false;// Variable que permite salir del menú (bucle while)

    public MenuConversorMonedas() {
        /*Crea un objeto (para instanciar) de la clase ConversorMonedasAPI y se asigna a la variable conversor. Para
         utilizar sus métodos y propiedades en la clase MenuConversorMonedas.*/
        this.conversor = new ConversorMonedasAPI();
    }

    // Método para mostrar el menú de conversión
    public void mostrarMenu() {
        while (!salir) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("""
                
                                    Conversor de Monedas
                -------------------------------------------------------------
                Elija la moneda que desea convertir:
                 1. ARS - Peso argentino
                 2. BOB - Boliviano boliviano
                 3. BRL - Real brasileño
                 4. CLP - Peso chileno
                 5. COP - Peso colombiano
                 6. USD - Dólar estadounidense
                 7. Salir
                 -------------------------------------------------------------
                """);

            int opcion = scanner.nextInt();
            String monedaOrigen;

            switch (opcion) {
                case 1 -> {
                    monedaOrigen = "ARS";
                    cantidadMonedasMenu(monedaOrigen);
                }
                case 2 -> {
                    monedaOrigen = "BOB";
                    cantidadMonedasMenu(monedaOrigen);
                }
                case 3 -> {
                    monedaOrigen = "BRL";
                    cantidadMonedasMenu(monedaOrigen);
                }
                case 4 -> {
                    monedaOrigen = "CLP";
                    cantidadMonedasMenu(monedaOrigen);
                }
                case 5 -> {
                    monedaOrigen = "COP";
                    cantidadMonedasMenu(monedaOrigen);
                }
                case 6 -> {
                    monedaOrigen = "USD";
                    cantidadMonedasMenu(monedaOrigen);
                }
                case 7 -> {
                    System.out.println("¡Gracias por usar el Conversor de Monedas!");
                    salir = true;
                }
                default -> System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }

    }
    // Método para ingresar cantidad de monedas a convertir
    private void cantidadMonedasMenu(String monedaOrigen){

        System.out.println("\nMoneda seleccionada: " + monedaOrigen);

        try {
            Scanner scannerCantidad = new Scanner(System.in);
            System.out.println("Ingrese la cantidad a convertir:");
            double cantidad = scannerCantidad.nextDouble();
            realizarConversion(cantidad , monedaOrigen);

        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, ingrese solo números");
            mostrarMenu();
        }

    }

    // Método para mostrar el menú que permite seleccionar moneda a convertir y mostrar resultado conversión
    private void realizarConversion(double cantidad , String monedaOrigen) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("""
             
                Seleccione la moneda de conversión:
                 1. ARS - Peso argentino
                 2. BOB - Boliviano boliviano
                 3. BRL - Real brasileño
                 4. CLP - Peso chileno
                 5. COP - Peso colombiano
                 6. USD - Dólar estadounidense
                 7. Salir
                 -------------------------------------------------------------
                """);

        int opcion = scanner.nextInt();
        String monedaDestino = " ";

        switch (opcion) {
            case 1 -> monedaDestino = "ARS";
            case 2 -> monedaDestino = "BOB";
            case 3 -> monedaDestino = "BRL";
            case 4 -> monedaDestino = "CLP";
            case 5 -> monedaDestino = "COP";
            case 6 -> monedaDestino = "USD";
            case 7 -> {
                System.out.println("¡Gracias por usar el Conversor de Monedas!");
                salir = true;
            }
            default -> System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
        }

        conversor.convertir(cantidad, monedaOrigen, monedaDestino);//intancia de objeto que llama al método convertir de la clase ConversorMonedasAPI

        System.out.println("""
        
        ¿Desea realizar otra conversión?
        1. Si
        2. No
        
        """);
        try {
            scanner.nextLine();
            opcion = scanner.nextInt();

            if (opcion == 1) {
                mostrarMenu();
            } else if (opcion == 2) {
                System.out.println("¡Gracias por usar el Conversor de Monedas!");
                salir = true;
            } else {
                System.out.println("Opción no disponible. Volviendo a menú principal...");
                mostrarMenu();
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, ingrese una opción válida. Volviendo a menú principal...");
            mostrarMenu();
        }
    }


}

