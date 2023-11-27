/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Usuarios;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class Cliente extends Usuario {
  private String edad;
  private String nTarjetaCredito;

  /**
  *@param cedula 
  **/
  private String[] aniadirCliente(String cedula){
    try{
      Scanner sc=new Scanner (System.in);
      System.out.print("escriba su edad: ");
      String e =sc.nextLine();
      System.out.print("escriba su numero de tarjeta de credito: ");
      String nT =sc.nextLine();

      // El FileWriter es un objeto que permite la escritura de docuemtos, hay que especificar en los parametros cada vez que se use la adicion, si no, se eliminara la informacion del documento de texto
      BufferedWriter escritura = new BufferedWriter(new FileWriter("clientes.txt", true));
      //El bufferedWriter sirv para envolver las operaciones del FileWriter para ahorrar recrusos a la hora de hacer una sola escritura en el documento */
      String cadena="\n"+cedula+","+e+","+nT;
      escritura.write(cadena);
      String[] lista=cadena.split(",");
      escritura.close();
      return lista;
    }catch(IOException e){
        return null;
    }
  }

  private String[] initializeClienteAttributes() {
    String cedulaUsuario=getCedula();
    boolean registrado=false;
    try(BufferedReader lector = new BufferedReader(new FileReader("clientes.txt"));){

      String cadena;
        while ((cadena=lector.readLine()) !=null){
            String[] lista=cadena.split(",");
            if (cedulaUsuario.equals(lista[0])){
                registrado=true;
                lector.close();
                return lista;
            }
        }
        if(!registrado){
            String[] lista=aniadirCliente(cedulaUsuario);
            lector.close();
            return lista;
        }return null;
    }catch(IOException e){
        return null;
    }
  }

  public Cliente(String user,String contrasenia){
    super(user,contrasenia);
    String[] lista=initializeClienteAttributes();
    this.edad=lista[1];
    this.nTarjetaCredito=lista[2];
  }
  
  
  @Override
  public void mostrarMenu(){
      System.out.println(
      "/**********MENU USUARIO*********/\n"+
      "/*                               */\n"+
      "/*********************************/\n"+
      "1.Solicitar servicio de taxi\n"
              + "2.Solicitar entrega encomienda\n"
              + "3.Consultar servicios\n"
              + "4.Salir del sistema.\n"
              + "Elija una opción del 1 al 4.");
      
      Scanner sc=new Scanner(System.in);
      String opcion;
      do{
          opcion=sc.nextLine();
          switch(opcion){
              case "1":
                  
                  break;
              case "2":
                  break;
              case "3":
                  break;
              case "4":
                  break;
              default:
                  System.out.println("Escriba una opción solo entre 1 y 4.");
          }
      }while(opcion!=null);
      
  }
  
  @Override
   public void consultarServicio(){
   }


  @Override
  public String toString() {
    return (super.toString() + ", " + getCedula() + ", " + edad + ", " + nTarjetaCredito);
  }
}
