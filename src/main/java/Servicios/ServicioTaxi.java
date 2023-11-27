/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;
 
import Enums.TipoServicio;
 
import Sistemas.Sistema;
 
import java.util.Scanner;
 
import Usuarios.*;
 
import java.io.BufferedWriter;
 
import java.io.FileWriter;
 
import java.io.IOException;
 
import java.util.Random;
 
/**
 
*
 
* @author hilda
 
*/
 
 
public class ServicioTaxi extends Servicio{
 
  private int nPersonas;
 
  private double precioFinal;
  private double subPrecio;
 
  private int kilometros;
 
  private void obtenerNPersonas(){
 
    Scanner sc=new Scanner(System.in);
 
    System.out.print("cuantas personas irán: ");
 
    this.nPersonas=sc.nextInt();
    sc.nextLine();
 
  }
 
  private void precio(Cliente cliente){
 
      Scanner sc=new Scanner(System.in);
 
      Random rd = new Random();
 
      kilometros=rd.nextInt(45)+5;
 
      subPrecio=kilometros*0.5;
 
      System.out.print("¿Desea pagar con tarjeta de crédito?(SI/NO): ");
 
      String pagoTarjeta=sc.nextLine().toUpperCase();
 
      if (pagoTarjeta.equalsIgnoreCase("SI")){
 
          precioFinal=subPrecio+(subPrecio*0.15);
 
      }else{
          precioFinal=subPrecio;
      }
 
          System.out.println("total a pagar:"+precioFinal);
 
          System.out.println("¿Desea confirmar su viaje? (SI/NO)");
 
          String confirmar=sc.nextLine();
 
          if(confirmar.equalsIgnoreCase("SI")){
 
              registrarViaje();
              registrarPago(pagoTarjeta,cliente);
              registrarServicio(cliente, getConductor());
 
              System.out.println("Listo, se añadió el servicio.");
 
              System.out.println("Regresando al sistema...");
              cliente.mostrarMenu();
          }else if(confirmar.equalsIgnoreCase("NO")){
 
              System.out.println("Ha regresado al sistema.");
              cliente.mostrarMenu();
 
          }
 
      }
 
 
  public double getPrecio(){
 
      return precioFinal;
 
  }
  private int obtenerIdPago(){
      Random rd=new Random();
      int numero= rd.nextInt(500)+100;
      return numero;
  }
 private void registrarPago(String fP,Cliente cliente){
      try{BufferedWriter escritura = new BufferedWriter(new FileWriter("pagos.txt", true));
            String formaPago;
            if (fP.equals("SI")){
                formaPago="TC";
            }else{
                formaPago="E";
            }
            String cadena="\n"+getIdentificador()+","+getFecha()+","+obtenerIdPago()+","+formaPago+","+cliente.getCedula()+","+subPrecio+","+precioFinal;
            escritura.write(cadena);
            escritura.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
  }
  private void registrarViaje(){
 
        try{BufferedWriter escritura = new BufferedWriter(new FileWriter("viajes.txt", true));
 
            String cadena=getIdentificador()+","+nPersonas+","+kilometros+","+precioFinal+"\n";
 
            escritura.write(cadena);
 
            escritura.close();
 
        }catch(IOException e) {
 
            e.printStackTrace();
 
        }
 
  }
 
  public ServicioTaxi(TipoServicio tS, Cliente cliente){
 
    super(tS);
 
    obtenerNPersonas();
 
    precio(cliente);
 
  }
 
}
 
 
