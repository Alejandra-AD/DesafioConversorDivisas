package Modelos;

import Calculos.ConversorMonedasAPI;


import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuConversorMonedas {
    private final ConversorMonedasAPI conversor;
    private boolean salir = false;

    public MenuConversorMonedas() {
        this.conversor = new ConversorMonedasAPI();
    }

    public void mostrarMenu() {
        while (!salir) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("""
                
                                    Conversor de Monedas
                -------------------------------------------------------------
                Seleccione una de nuestras opciones de divisa para convertir:
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

    private void cantidadMonedasMenu(String monedaOrigen){

        System.out.println("\nMoneda seleccionada: " + monedaOrigen);

        try {
            Scanner scanner = new Scanner(System.in);
            Scanner scannerCantidad = new Scanner(System.in);
            System.out.println("Ingrese la cantidad a convertir:");
            double cantidad = scannerCantidad.nextDouble();
            realizarConversion(cantidad , monedaOrigen);

        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, ingrese solo números");
            mostrarMenu();
        }

    }

    private void realizarConversion(double cantidad , String monedaOrigen) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("""
             
                Seleccione la divisa a la que desea convertir:
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

        conversor.convertir(cantidad, monedaOrigen, monedaDestino);

        System.out.println("""
        
        ¿Desea realizar otra conversion?
        1. Si
        2. No
        
        """);
        try {
            scanner.nextLine();
            opcion = scanner.nextInt();

            if (opcion == 1) {
                mostrarMenu();
            } else if (opcion == 2) {
                salir = true;
            } else {
                System.out.println("Volviendo a menú principal");
                mostrarMenu();
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, ingrese una opción válida. Volviendo a menú principal");
            mostrarMenu();
        }
    }


}

