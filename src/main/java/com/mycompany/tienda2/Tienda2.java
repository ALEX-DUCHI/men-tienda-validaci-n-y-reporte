/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tienda2;

/**
 *
 * @author User
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Tienda2 {


    static String[] productos = {"CAFE", "AZUCAR", "ARROZ"};
    static double[] precios = {50.0, 30.0, 25.0};

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Double> carrito = new ArrayList<>();
        int opcion;

        
        
        do {
            mostrarMenu();
            opcion = leerOpcion(sc);

            if (opcion >= 1 && opcion <= 3) {
                agregarProducto(carrito, precios[opcion - 1], productos[opcion - 1]);
                
                
                
            } else {
                switch (opcion) {
                    case 4:
                        pagar(carrito, sc);
                        break;
                    case 5:
                        System.out.println("Gracias por visitar Tienda Express!");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            }
            
            
            
        } while (opcion != 5);

        
        
        sc.close();
    }

    
    
    
    static void mostrarMenu() {
        System.out.println("\n=== Tienda Express ===");
        for (int i = 0; i < productos.length; i++) {
            System.out.println((i + 1) + ". " + productos[i] + " - $" + precios[i]);
        }
        System.out.println("4. Pagar");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    
    
    
    static int leerOpcion(Scanner sc) {
        if (sc.hasNextInt()) {
            return sc.nextInt();
        } else {
            sc.next();
            return -1;
        }
    }

    
    
    static void agregarProducto(List<Double> carrito, double precio, String nombre) {
        carrito.add(precio);
        System.out.println("Producto agregado: " + nombre + " ($" + precio + ")");
    }

    
    
    static double total(double base, double imp) {
        return base + (base * imp);
    }

    
    
    static void confirmarCompra(String correo, double total) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (Pattern.matches(regex, correo)) {
            System.out.println("\n---Reporte de Compra---");
            System.out.println("Correo: " + correo);
            System.out.println("Total pagado: $" + total);
            System.out.println("¡Gracias por su compra!");
        } else {
            System.out.println("❌ Correo inválido. Compra no confirmada.");
        }
    }

    
    
    
    static void pagar(List<Double> carrito, Scanner sc) {
        if (carrito.isEmpty()) {
            System.out.println(" El carrito está vacio.");
            return;
        }

        double subtotal = 0;
        for (double p : carrito) subtotal += p;

        double total = total(subtotal, 0.12);
        System.out.println(" Subtotal: $" + subtotal);
        System.out.println("IVA (16%): $" + (subtotal * 0.16));
        System.out.println("TOTAL: $" + total);

        System.out.print("Ingrese su correo para confirmar la compra: ");
        String correo = sc.next();
        confirmarCompra(correo, total);

        carrito.clear();
    }
}

