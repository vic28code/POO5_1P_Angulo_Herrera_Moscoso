/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Usuarios;
import java.io.*;
import java.util.Scanner;
import Enums.EstadoConductor;
import Vehiculos.Vehiculo;
public class Conductor extends Usuario {
  // private int numeroLicencia;
  private EstadoConductor estado;
  private String codigoVehiculo;
  private Vehiculo vehiculo;
  private String[] inicializateConductorAttributes(){
    String cedulaUsuario=getCedula();
    //this.codigo=vehiculo.getCodigoVehiculo();
      try{
      FileReader archivo = new FileReader("conductores.txt");
      BufferedReader br =new BufferedReader(archivo);
      String linea;
      while ((linea = br.readLine()) != null) {
        String[] lista = linea.split(",");
        if (cedulaUsuario.equals(lista[0])) {
          br.close();
          archivo.close();
          return lista;
        }
      }
      br.close();
      archivo.close();
      return null;
    }
    catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
  public Conductor(String user, String contrasenia) {
    super(user, contrasenia);
    String[] lista = inicializateConductorAttributes();
    this.estado = EstadoConductor.valueOf(lista[1]);
    this.codigoVehiculo = lista[2];
    vehiculo = new Vehiculo(codigoVehiculo);
  }

  public void mostrarMenu(){
      
    System.out.println(
      "/**********MENU CONDUCTOR*********/\n"+
      "/*                               */\n"+
      "/*********************************/\n"+
      "1.Consultar Servicio Asignado\n"
              + "2.Datos de su vehiculo\n"
              + "3.Salir del programa\n"
              + "\n"
              + "Elija una opcion: ");
    Scanner sc = new Scanner(System.in);
    String opcion;
    do{
        opcion = sc.nextLine();
        switch(opcion){
            case "1":
                consultarServicio();
                break;
            case "2":
                System.out.println("\n"+vehiculo+"\n");
                retornarMenu();
                break;
            case "3":
                System.out.println("\nGracias por su visita.");
                break;
            default:
                System.out.println("Opción no válida. Por favor, elija nuevamente: ");
                break;
          }
        }while(!opcion.equals("3"));
      sc.close();
    }
 

  public EstadoConductor getEstadoConductor(){
    return estado;
  }
  @Override
  public String toString(){
    return (super.toString()+", "+estado+", "+codigoVehiculo+", "+vehiculo.toString()); 
  }
  
  public void retornarMenu(){
      System.out.println("Regresando al menú...\n");
      mostrarMenu();
  }
  
  @Override
  public void consultarServicio() {
    Scanner sc = new Scanner(System.in);
    boolean tieneServicios = false;

    try (BufferedReader fichero = new BufferedReader(new FileReader("servicios.txt"))) {
        String linea;
        while ((linea = fichero.readLine()) != null) {
            String[] lista = linea.split(",");
            if (getNombre().equals(lista[3])) {
                tieneServicios = true; // Indica que se encontró al menos un servicio

                switch (String.valueOf(lista[1])) {
                    case "T":
                        try (BufferedReader ficheroViajes = new BufferedReader(new FileReader("viajes.txt"))) {
                            String lineaViajes;
                            while ((lineaViajes = ficheroViajes.readLine()) != null) {
                                String[] listaViajes = lineaViajes.split(",");
                                if (listaViajes[0].equals(lista[0])) {
                                    System.out.println(
                                        "\n/************************************/\n" +
                                            "\n" +
                                            "Tipo: Servicio Taxi\n" +
                                            "Cantidad pasajeros: " + listaViajes[1] + "\n" +
                                            "Fecha: " + lista[6] + "\n" +
                                            "Hora: " + lista[7] + "\n" +
                                            "Desde: " + lista[4] + "\n" +
                                            "Hasta: " + lista[5] + "\n"
                                    );
                                }
                            }
                            System.out.println("\nNo tiene más servicios de taxi asignados");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "E":
                        try (BufferedReader ficheroEncomiendas = new BufferedReader(new FileReader("encomiendas.txt"))) {
                            String lineaEncomiendas;
                            while ((lineaEncomiendas = ficheroEncomiendas.readLine()) != null) {
                                String[] listaEncomiendas = lineaEncomiendas.split(",");
                                if (listaEncomiendas[0].equals(lista[0])) {
                                    System.out.println(
                                        "\n/************************************/\n" +
                                            "\n" +
                                            "Tipo: Servicio Encomienda\n" +
                                            "Cantidad Encomienda: " + listaEncomiendas[1] + "\n" +
                                            "Fecha: " + lista[6] + "\n" +
                                            "Hora: " + lista[7] + "\n" +
                                            "Desde: " + lista[4] + "\n" +
                                            "Hasta: " + lista[5] + "\n"
                                    );
                                }
                            }
                            System.out.println("\nNo tiene ningún servicio de encomienda asignado\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        }

        if (!tieneServicios) {
            System.out.println("Usted no tiene ningún servicio asignado");
        }

        System.out.println("Regresando al menú...");
        mostrarMenu();
        sc.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

  
  
  
//  public void consultarServicio() {
//            Scanner sc = new Scanner(System.in);
//            try(
//                 BufferedReader fichero = new BufferedReader(new FileReader("servicios.txt")))
//              {
//                String linea;
//                  while ((linea = fichero.readLine()) != null){
//                      String[] lista = linea.split(",");
//                      if (getNombre().equals(lista[3])){
//                        switch(String.valueOf(lista[1])){
//                            case "T":
//                                try(
//                                  BufferedReader ficheroViajes = new BufferedReader(new FileReader("viajes.txt"));
//                                ){
//                                  String lineaViajes;
//                                  while (( lineaViajes = ficheroViajes.readLine()) !=null){
//                                    String[] listaViajes = lineaViajes.split(",");
//                                    if (listaViajes[0].equals(lista[0])){
// 
//                                      System.out.println(
//                                        "\n/************************************/\n"+
//                                        "\n"+
//                                        "Tipo: Servicio Taxi\n"+
//                                        "Cantidad pasajeros: "+listaViajes[1]+"\n"+
//                                        "Fecha: "+lista[6]+"\n"+
//                                        "Hora: "+lista[7]+"\n"+
//                                        "Desde: "+lista[4]+"\n"+
//                                        "Hasta: "+lista[5]+"\n"
//                                      );
//                                    }
//                                  }
//                                System.out.println("\nNo tiene más servicios de taxi asignados");
//                                ficheroViajes.close();
//                                }catch(IOException e){
//                                     e.printStackTrace();
//                                }
//                            break;
// 
//                            case "E":
//                                try(
//                                  BufferedReader ficheroEncomiendas = new BufferedReader(new FileReader("encomiendas.txt"));
//                                )
//                                {
//                                    String lineaEncomiendas;
//                                    while (( lineaEncomiendas = ficheroEncomiendas.readLine()) !=null){
//                                        String[] listaEncomiendas = lineaEncomiendas.split(",");
//                                        if (listaEncomiendas[0].equals(lista[0])){
// 
//                                            System.out.println(
//                                              "\n/************************************/\n"+
//                                              "\n"+
//                                              "Tipo: Servicio Encomienda\n"+
//                                              "Cantidad Encomienda: "+listaEncomiendas[1]+"\n"+
//                                              "Fecha: "+lista[6]+"\n"+
//                                              "Hora: "+lista[7]+"\n"+
//                                              "Desde: "+lista[4]+"\n"+
//                                              "Hasta: "+lista[5]+"\n"
//                                            );
// 
//                                        }
//                                    }
//                                ficheroEncomiendas.close();
//                                System.out.println("\nNo tiene ningún servicio de encomienda asignado\n");
//                                }
//                                catch(IOException e){
//                                     e.printStackTrace();
//                                }
//                            break;
//                        }   
//                      }
//                      else{
//                          System.out.println("Usted no tiene ningún servicio asignado");
//                      }
//                  }
//                  System.out.println("Regresando al menu...");
//                  mostrarMenu();
//                  
//                  
////              System.out.println("Desea continuar?\n 2 = Ver datos de su veiculo \n3 = No");
////              opcion = sc.nextLine();
//              fichero.close();
//              }catch(IOException e){
//                  e.printStackTrace();
//              }
//  }
  }