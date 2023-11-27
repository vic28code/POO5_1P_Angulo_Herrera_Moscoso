/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Usuarios;

import Enums.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author usuario
 */
public abstract class Usuario {
  /*
   * El sistema tendrá usuarios de los que se desea conocer su número de cédula,
   * nombres, apellidos, usuario, contraseña y número de celular.
   */

  private String cedula;
  private String nombre;
  private String apellidos;
  private String user;
  private String contrasenia;
  private String celular;

  /*
   * Al ser dos tipos de usuarios, clientes o conductores, se necesita una
   * variable de tipo de usuario, que lo clasifica como Conductor o Cliente.
   */
  
  private TipoUsuario tipoUsuario;
  
  public abstract void mostrarMenu();
  public abstract void consultarServicio();
  
  private String[] initializeUserAttributes(String user ,String contrasenia) {
      try{
          FileReader archivo = new FileReader("usuarios.txt");
          BufferedReader lector =new BufferedReader(archivo);

          String cadena;
          while ((cadena=lector.readLine()) !=null){
              String[] lista =cadena.split(",");
              if (user.equals(lista[3]) && contrasenia.equals(lista[4])){
                  lector.close();
                  archivo.close();
                  return lista;
              }
          }
          return null;
      }catch(IOException e){
          return null;
      }
  }

  public Usuario(String user,String contrasenia){
      String[] lista =initializeUserAttributes(user,contrasenia);
      this.cedula = lista[0];
      this.nombre = lista[1];
      this.apellidos = lista[2];
      this.user = lista[3];
      this.contrasenia = lista[4];
      this.celular = lista[5];
      this.tipoUsuario = TipoUsuario.valueOf(lista[6]);
  }

  public String getNombre(){
    return nombre;
  }
  
  public String getCedula(){
      return cedula;
  }

  @Override
  public String toString() {
    return (cedula + ", " + nombre + ", " + apellidos + ", " + user + ", " + contrasenia + ", " + celular + ", "
        + tipoUsuario);
  }
}

