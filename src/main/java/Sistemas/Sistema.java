/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Sistemas;

import Usuarios.*;
import java.io.*;
import java.util.Scanner;
import Enums.*;

/**
 *
 * @author usuario
 */
public class Sistema {

  /**
   * @param user
   * @param contrasenia
   * @return
   */

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

  /* Acceso al sistema */

  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);

    System.out.println(
        "+++++++++++++++++++++++++++++++++\n\n     BIENVENIDO AL SISTEMA\n\n+++++++++++++++++++++++++++++++++");
    System.out.print("escriba el user: ");
    String user = sc.nextLine();
    System.out.print("escriba contrasenia: ");
    String contrasenia = sc.nextLine();

    if (verificarUsuario(user, contrasenia)) {
      if (getTipoUsuario(user, contrasenia).equals(TipoUsuario.C)) {
        Cliente cliente1 = new Cliente(user, contrasenia);
        System.out.println(cliente1.toString());

      }else if (getTipoUsuario(user, contrasenia).equals(TipoUsuario.R)){
        Conductor conductor1= new Conductor(user,contrasenia);
        //System.out.println(conductor1.toString());
        System.out.println(
          "/**********MENU CONDUCTOR*********/\n"+
          "/*                               */\n"+
          "/*********************************/\n"+
          "1.Consular Servicio Asignado\n"+
          "2.Datos de su vehiculo\n"+
          "\n"+
          "Elija una opcion: "
        );
        
        
      }
    }
  }

}

