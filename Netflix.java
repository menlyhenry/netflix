package com.mycompany.netflix;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Netflix {
    public static void main(String[] args) {

        Scanner pepe = new Scanner(System.in);
        String[] planes = {"Basico", "Estandar", "Premium"};
        double[] precios = {24.90, 34.90, 44.90};
        String[][] beneficios = {
                {"1 Pantalla", "|HD|"},
                {"2 Pantallas", "|Full HD|"},
                {"4 Pantallas", "|Ultra HD|"}
        };

        ArrayList<String> clientes = new ArrayList<>();
        ArrayList<String> correos = new ArrayList<>();
        ArrayList<String> contraseñas = new ArrayList<>();

        int opcion;

        do {

            System.out.println("==== NETFLIX ====\n");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Mostrar Planes");
            System.out.println("3. Comprar Plan");
            System.out.println("4. Ver Clientes");
            System.out.println("5. Imprimir Factura");
            System.out.println("6. Salir");
            System.out.print("Elija una opcion: ");
            opcion = pepe.nextInt();
            pepe.nextLine();

            switch (opcion) {

                case 1:

                    System.out.print("Nombre del cliente: ");
                    String nombre = pepe.nextLine();

                    System.out.print("Correo electronico: ");
                    String correo = pepe.nextLine();

                    System.out.print("Cree una contraseña: ");
                    String contraseña = pepe.nextLine();

                    clientes.add(nombre);
                    correos.add(correo);
                    contraseñas.add(contraseña);

                    System.out.println("Cliente registrado correctamente.");

                    break;

                case 2:

                    System.out.println("PLANES NETFLIX\n");
                    for (int i = 0; i < planes.length; i++) {
                        System.out.println((i + 1) + ". " + planes[i]);
                        System.out.println("Precio: S/" + precios[i]);
                        System.out.println("Beneficios:");
                        System.out.println("- " + beneficios[i][0]);
                        System.out.println("- " + beneficios[i][1]);
                        System.out.println();
                    }
                    break;

                case 3:

                    if (clientes.isEmpty()) {
                        System.out.println("No existen clientes registrados.");
                        break;
                    }
                    System.out.println("Clientes:");
                    for (int i = 0; i < clientes.size(); i++) {
                        System.out.println((i + 1) + ". " + clientes.get(i));
                    }

                    System.out.print("Seleccione cliente: ");
                    int cli = pepe.nextInt();
                    System.out.println("\nPLANES");

                    for (int i = 0; i < planes.length; i++) {
                        System.out.println((i + 1) + ". " + planes[i] + " - S/" + precios[i]);
                    }

                    System.out.print("Seleccione plan: ");
                    int plan = pepe.nextInt();

                    System.out.println("Metodo de pago\n");
                    System.out.println("1. Tarjeta");
                    System.out.println("2. Yape");
                    System.out.println("3. Plin");
                    System.out.println("4. PayPal");

                    System.out.print("Seleccione: ");
                    int pago = pepe.nextInt();

                    String metodo = "";

                    switch (pago) {

                        case 1:
                            metodo = "Tarjeta";
                            break;

                        case 2:
                            metodo = "Yape";
                            break;

                        case 3:
                            metodo = "Plin";
                            break;

                        case 4:
                            metodo = "PayPal";
                            break;

                        default:
                            metodo = "No definido";
                    }

                    double subtotal = precios[plan - 1];
                    double igv = subtotal * 0.18;
                    double total = subtotal + igv;

                    System.out.println("= FACTURA =\n");
                    System.out.println("Fecha :03/07/2026\n");
                    System.out.println("Cliente : " + clientes.get(cli - 1));
                    System.out.println("Correo  : " + correos.get(cli - 1));
                    System.out.println("Plan    : " + planes[plan - 1]);
                    System.out.println("Metodo  : " + metodo);
                    System.out.println("Precio  : S/" + subtotal);
                    System.out.println("IGV     : S/" + String.format("%.2f", igv));
                    System.out.println("TOTAL   : S/" + String.format("%.2f", total));
                    System.out.println("============");

                    try {

                        FileWriter archivo = new FileWriter("Factura.txt", true);

                        archivo.write("===========\n");
                        archivo.write("FACTURA NETFLIX\n");
                        archivo.write("===========\n");
                        archivo.write("Fecha:  03/07/2026\n");
                        archivo.write("Cliente:  " + clientes.get(cli - 1) + "\n");
                        archivo.write("Correo:  " + correos.get(cli - 1) + "\n");
                        archivo.write("Plan:  " + planes[plan - 1] + "\n");
                        archivo.write("Metodo Pago:  " + metodo + "\n");
                        archivo.write("Precio:  S/" + subtotal + "\n");
                        archivo.write("IGV:  S/" + String.format("%.2f", igv) + "\n");
                        archivo.write("TOTAL:  S/" + String.format("%.2f", total) + "\n");
                        archivo.write("===========\n");

                        archivo.close();

                        System.out.println("Factura guardada en Factura.txt");

                    } catch (IOException e) {

                        System.out.println("ERROR al guardar archivo.");

                    }

                    break;

                case 4:

                    System.out.println("CLIENTES\n");

                    if (clientes.isEmpty()) {
                        System.out.println("No hay clientes registrados.");
                    } else {
                        for (int i = 0; i < clientes.size(); i++) {
                            System.out.println((i + 1) + ". " + clientes.get(i)
                                    + " - " + correos.get(i));
                        }
                    }

                    break;

                case 5:

                    System.out.println("==IMPRIMIENDO HISTORIAL DE FACTURAS==\n");

                    try {
                        FileReader archivo = new FileReader("Factura.txt");
                        BufferedReader leerarchivo = new BufferedReader(archivo);
                        String linea;

                        while ((linea = leerarchivo.readLine()) != null) {
                            System.out.println(linea);
                        }

                        leerarchivo.close();
                        archivo.close();

                    } catch (FileNotFoundException e) {
                        System.out.println("Aviso: No se encontraron facturas guardadas (El archivo Factura.txt aun no existe).");
                    } catch (IOException e) {
                        System.out.println("ERROR al leer el archivo de facturas.");
                    }

                    break;

                case 6:

                    System.out.println("Gracias por usar el sistema.");
                    break;

                default:

                    System.out.println("Opcion no existente.");

            }

        } while (opcion != 6);
    }
}

    

