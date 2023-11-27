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
  private double precio;
  private int kilometros;
  
  private void obtenerNPersonas(){
    Scanner sc=new Scanner(System.in);
    System.out.print("cuantas personas iran: ");
    this.nPersonas=sc.nextInt();
  }
  private void precio(){
      Scanner sc=new Scanner(System.in);
      Random rd = new Random();
      kilometros=rd.nextInt(45)+5;
      precio=kilometros*0.5;
      System.out.print("desea pagar con tarjeta de credito?(SI/NO): ");
      String pagoTarjeta=sc.nextLine().toUpperCase();
      if (pagoTarjeta.equalsIgnoreCase("SI")){
          precio=precio+(precio*0.15);
          System.out.println("Subtotal a pagar:"+precio);
          System.out.println("¿Desea confirmar su viaje? (SI/NO)");
          String confirmar=sc.nextLine();
          if(confirmar.equalsIgnoreCase("SI")){
              System.out.println("Listo, se añadió el servicio.");
              System.out.println("PRESIONE 4 PARA SALIR DEL SISTEMA");
          }else{
              Sistema.iniciarSesion();
          }
      }
  }
  
  
  public double getPrecio(){
      return precio;
  }
  
  private void registrarViaje(){
        try{BufferedWriter escritura = new BufferedWriter(new FileWriter("clientes.txt", true));
            String cadena=getIdentificador()+","+nPersonas+","+kilometros+","+precio;
            escritura.write(cadena);
            escritura.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
  }
  public ServicioTaxi(TipoServicio tS, Cliente cliente){
    super(tS, cliente);
    obtenerNPersonas();
    registrarViaje();
    precio();
  }
}
 

