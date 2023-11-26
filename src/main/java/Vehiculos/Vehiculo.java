/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos;

import Enums.TipoVehiculo;
import java.io.*;

public class Vehiculo {
  private String codigoVehiculo;
  private String placa;
  private String modelo;
  private String marca;
  private TipoVehiculo tipo;

  // codigo,placa,modelo,marca,tipo
  private String[] inicializateVehiculoAttributes(String codigo) {
    try{
      FileReader archivo = new FileReader("vehiculos.txt");
      BufferedReader br =new BufferedReader(archivo);
      String linea;
      while ((linea = br.readLine()) != null) {
        String[] lista = linea.split(",");
        if (codigo.equals(lista[0])) {
          br.close();
          archivo.close();
          return lista;
        }
      }
      br.close();
      archivo.close();
      return null;
    } catch (IOException e) {

      e.printStackTrace();
      return null;
    }
  }


  public Vehiculo(String codigo) {
    String[] lista = inicializateVehiculoAttributes(codigo);
    this.codigoVehiculo = lista[0];
    this.placa = lista[1];
    this.modelo = lista[2];
    this.marca = lista[3];
    this.tipo = TipoVehiculo.valueOf(lista[4]);

  }

  public String getCodigoVehiculo() {
    return codigoVehiculo;
  }

  public String toString(){
    return (placa+", "+modelo+", "+marca+", "+tipo);
  }
}
