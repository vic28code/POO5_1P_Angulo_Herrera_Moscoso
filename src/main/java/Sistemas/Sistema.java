/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Sistemas;

import Usuarios.*;
import java.io.*;
import java.util.Scanner;
import Enums.*;
import Servicios.*;
//import java.util.ArrayList;
/**
 *
 * @author usuario
 */
public class Sistema {

  public static void main(String[] args){
    aperturaSistema();
    iniciarSesion();
  }
  
  private static Cliente cliente1 ;
  private static Conductor conductor1;
  

  private static boolean verificarUsuario(String user, String contrasenia) {
    try (BufferedReader lector = new BufferedReader( new FileReader("usuarios.txt"));) {
      String cadena;
      while ((cadena = lector.readLine()) != null) {
        String[] lista = cadena.split(",");
        if (user.equals(lista[3]) && contrasenia.equals(lista[4])) {
          lector.close();
          return true;
        }
      }
      lector.close();
      return false;

    } catch (IOException e) {
      return false;
    }
  }

  private static TipoUsuario getTipoUsuario(String user, String contrasenia) {
    try (
        BufferedReader lector = new BufferedReader(new FileReader("usuarios.txt"));) {
      String cadena;
      while ((cadena = lector.readLine()) != null) {
        String[] lista = cadena.split(",");
        if (user.equals(lista[3]) && contrasenia.equals(lista[4])) {
          TipoUsuario tipoUsuario = TipoUsuario.valueOf(lista[6]);
          lector.close();
          return tipoUsuario;
        }
      }
      lector.close();
      return null;
    } catch (IOException e) {
      return null;
    }
  }




  public static void aperturaSistema(){
    System.out.println(
      "+++++++++++++++++++++++++++++++++\n"+
      "\n"+
      "      BIENVENIDO AL SISTEMA      \n"+
      "\n"+
      "+++++++++++++++++++++++++++++++++\n"
    );
  }

  public static void iniciarSesion() {
    Scanner sc = new Scanner(System.in);
    String user;
    String contrasenia;
    do{
        System.out.print("escriba el user: ");
        user = sc.nextLine();
        System.out.print("escriba contrasenia: ");
        contrasenia = sc.nextLine();
        if(verificarUsuario(user, contrasenia)){
            if (getTipoUsuario(user, contrasenia).equals(TipoUsuario.C)) {
                cliente1 = new Cliente(user, contrasenia);
                cliente1.mostrarMenu();
                
                break;
            }else if (getTipoUsuario(user, contrasenia).equals(TipoUsuario.R)){
                conductor1= new Conductor(user,contrasenia);
                conductor1.mostrarMenu();
               
                break;     
            }
        }else{
                System.out.println("Usuario no existente en el sistema, ¿Desea salir o continuar?");
                String mensaje = sc.nextLine().trim();
                if(mensaje.equalsIgnoreCase("salir")){
                    System.out.println("\n¡Gracias por visitarnos!");
                    break;
                }
            }
    }while(user!=null || contrasenia!=null || verificarUsuario(user,contrasenia)!=false);
    sc.close();

  }

    public static Cliente getCliente1() {
        return cliente1;
    }

    public static Conductor getConductor1() {
        return conductor1;
    }
  
  }

