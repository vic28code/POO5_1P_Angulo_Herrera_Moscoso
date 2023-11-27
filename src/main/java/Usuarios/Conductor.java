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
  
  @Override
  public void mostrarMenu(){
      System.out.println(
      "/**********MENU CONDUCTOR*********/\n"+
      "/*                               */\n"+
      "/*********************************/\n"+
      "1.Consultar Servicio Asignado\n"+
      "2.Datos de su vehiculo\n"+"\n"
    );
  } 
  
  @Override
  public void consultarServicio(){
  }
  
  public EstadoConductor getEstadoConductor(){
    return estado;
  }

  
  @Override
  public String toString(){
    return (super.toString()+", "+estado+", "+codigoVehiculo+", "+vehiculo.toString()); 
  }
}