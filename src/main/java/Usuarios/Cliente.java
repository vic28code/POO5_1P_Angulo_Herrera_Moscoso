/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Usuarios;

import Sistemas.*;
import java.io.*;
import java.util.Scanner;
import Servicios.*;
import Enums.*;
import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class Cliente extends Usuario {

    private String edad;
    private String nTarjetaCredito;

    /**
     * @param cedula
     *
     */
    private String[] aniadirCliente(String cedula) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("escriba su edad: ");
            String e = sc.nextLine();
            System.out.print("escriba su numero de tarjeta de credito: ");
            String nT = sc.nextLine();

            // El FileWriter es un objeto que permite la escritura de docuemtos, hay que especificar en los parametros cada vez que se use la adicion, si no, se eliminara la informacion del documento de texto
            BufferedWriter escritura = new BufferedWriter(new FileWriter("clientes.txt", true));
            //El bufferedWriter sirv para envolver las operaciones del FileWriter para ahorrar recrusos a la hora de hacer una sola escritura en el documento */
            String cadena = "\n" + cedula + "," + e + "," + nT;
            escritura.write(cadena);
            String[] lista = cadena.split(",");
            escritura.close();
            return lista;
        } catch (IOException e) {
            return null;
        }
    }

    private String[] initializeClienteAttributes() {
        String cedulaUsuario = getCedula();
        boolean registrado = false;
        try (BufferedReader lector = new BufferedReader(new FileReader("clientes.txt"));) {

            String cadena;
            while ((cadena = lector.readLine()) != null) {
                String[] lista = cadena.split(",");
                if (cedulaUsuario.equals(lista[0])) {
                    registrado = true;
                    lector.close();
                    return lista;
                }
            }
            if (!registrado) {
                String[] lista = aniadirCliente(cedulaUsuario);
                lector.close();
                return lista;
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public Cliente(String user, String contrasenia) {
        super(user, contrasenia);
        String[] lista = initializeClienteAttributes();
        this.edad = lista[1];
        this.nTarjetaCredito = lista[2];
    }

    public void mostrarMenu() {
        System.out.println(
                "/**********MENU USUARIO*********/\n"
                + "/*                               */\n"
                + "/*********************************/\n"
                + "1.Solicitar servicio de taxi\n"
                + "2.Solicitar entrega encomienda\n"
                + "3.Consultar servicios\n"
                + "4.Salir del sistema.\n"
                + "Elija una opción:");

        Scanner sc = new Scanner(System.in);
        String opcion;
        do {
            opcion = sc.next().trim();
            switch (opcion) {
                case "1":
                    ServicioTaxi servicioTaxi = new ServicioTaxi(TipoServicio.T, Sistema.getCliente1());
                    break;
                case "2":

                    break;
                case "3":
                    consultarServicio();
                    break;
                case "4":
                    System.out.println("\nGracias por su visita.");
                    break;
                default:
                    System.out.println("Escriba una opción solo entre 1 y 4.");
            }
//          if (opcion.equals("4")) {
//              break;
//          }
        } while (!opcion.equals("4") && opcion != null);

    }

    @Override
    public void consultarServicio() {
        ArrayList<Servicio> serviciosCliente = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean tieneServicios = false;

        try (BufferedReader fichero = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            while ((linea = fichero.readLine()) != null) {
                String[] lista = linea.split(",");
                if (getCedula().equals(lista[0])) {
                    tieneServicios = true; // Indica que se encontró al menos un servicio
                    switch (String.valueOf(lista[1])) {

                        case "T":
                        try (BufferedReader ficheroServicios = new BufferedReader(new FileReader("servicios.txt"))) {
                            String lineaServicio;
                            while ((lineaServicio = ficheroServicios.readLine()) != null) {
                                String[] listaServicio = lineaServicio.split(",");
                                if (listaServicio[2].equals(lista[0]) && listaServicio[1].equals("T")) {
                                    try (BufferedReader ficheroViajes = new BufferedReader(new FileReader("viajes.txt"))) {
                                        String lineaViaje;
                                        while ((lineaViaje = ficheroViajes.readLine()) != null) {
                                            String[] listaViajes = lineaViaje.split(",");
                                            if (listaViajes[0].equals(listaServicio[0])) {
                                                System.out.println(
                                                        "\n/************************************/\n"
                                                        + "\n"
                                                        + "Tipo: Viaje\n"
                                                        + "Cantidad pasajeros: " + listaServicio[1] + "\n"
                                                        + "Fecha: " + listaServicio[6] + "\n"
                                                        + "Hora: " + listaServicio[7] + "\n"
                                                        + "Desde: " + listaServicio[4] + "\n"
                                                        + "Hasta: " + listaServicio[5] + "\n");
                                            }//cierra if
                                        }//cierra while
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }//cierra if
                            }//cierra while
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("\nNo tiene más servicios de taxi ");
                        }
                        break;

                        case "TE":
                        try (BufferedReader ficheroServicios = new BufferedReader(new FileReader("servicios.txt"))) {
                            String lineaServicio;
                            while ((lineaServicio = ficheroServicios.readLine()) != null) {
                                String[] listaServicio = lineaServicio.split(",");
                                if (listaServicio[2].equals(lista[0]) && listaServicio[1].equals("TE")) {
                                    try (BufferedReader ficheroEncomiendas = new BufferedReader(new FileReader("encomiendas.txt"))) {
                                        String lineaEncomienda;
                                        while ((lineaEncomienda = ficheroEncomiendas.readLine()) != null) {
                                            String[] listaEncomiendas = lineaEncomienda.split(",");
                                            if (listaEncomiendas[0].equals(listaServicio[0])) {
                                                System.out.println(
                                                        "\n/************************************/\n"
                                                        + "\nTipo: Encomienda"
                                                        + "Tipo Encomienda:"+listaEncomiendas[1]+"\n"
                                                        + "Cantidad Encomienda: " + listaEncomiendas[3] + "\n"
                                                        + "Fecha: " + lista[6] + "\n"
                                                        + "Hora: " + lista[7] + "\n"
                                                        + "Desde: " + lista[4] + "\n"
                                                        + "Hasta: " + lista[5] + "\n");
                                            }
                                        }
                                        System.out.println("\nNo tiene ningún servicio de encomienda\n");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!tieneServicios) {
            System.out.println("Usted no tiene ningún servicio");
        }

//        System.out.println(
//                "Regresando al menú...");
//        mostrarMenu();
//        sc.close();
    }

    @Override
    public String toString() {
        return (super.toString() + ", " + getCedula() + ", " + edad + ", " + nTarjetaCredito);

    }
}
