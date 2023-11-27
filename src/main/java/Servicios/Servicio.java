/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;
 
import Usuarios.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;
import Enums.TipoServicio;
import Enums.EstadoConductor;
import java.util.ArrayList;
 
/**
*
* @author usuario
*/
public abstract class Servicio {
  private String rutaDesde;
  private String rutaHasta;
  private String fecha;
  private String identificador;
  private float precio;
  private Conductor conductor;
  private String hora;
  private TipoServicio tipoServicio;
  private Cliente cliente;
  private ArrayList<Conductor> conductores;
 
  private int generarIdentificador() {
    Random rd = new Random();
    int numero = rd.nextInt(10000) + 1000;
    return numero;
  }
 
  private void listaConductores(){
    try (BufferedReader lector = new BufferedReader(new FileReader("usuarios.txt"))) {
        String cadena;
        while ((cadena = lector.readLine()) != null) {
            String[] lista = cadena.split(",");
            System.out.println(lista[6]);
            String tipo= lista[6];
            if ("R".equals(tipo)){
                //System.out.println(lista[6]);
                //if (conductor.getEstadoConductor().equals(EstadoConductor.D)){
                conductores.add(new Conductor(lista[3], lista[4]));
                //}
            }
        }
      lector.close();
    }catch (IOException e) {
    }
  }
  private Conductor obtenerConductor(){
      try (BufferedReader lector = new BufferedReader(new FileReader("usuarios.txt"))) {
        String cadena;
        while ((cadena = lector.readLine()) != null) {
            String[] lista = cadena.split(",");
            //System.out.println(lista[6]);
            String tipo= lista[6];
            if ("R".equals(tipo)){
                Conductor conductor1 =new Conductor(lista[3],lista[4]);
                if (conductor1.getEstadoConductor().equals(EstadoConductor.D)){
                return conductor1;
                }
            }
        }
      lector.close();
    }catch (IOException e) {
    }
      Random rd = new Random();
      int numeroAleatorio=rd.nextInt(conductores.size());
      return conductores.get(numeroAleatorio);
  }
 
  private String[] inicializeServiciosAttributes(Conductor co, Cliente cl) {
    try {
      Scanner sc = new Scanner(System.in);
      System.out.print("desde donde: ");
      String rD = sc.nextLine();
      System.out.print("hasta donde: ");
      String rH = sc.nextLine();
      System.out.print("fecha del viaje(DD/MM/AAAA): ");
      String f = sc.nextLine();
      System.out.print("hora del viaje(HH:MM): ");
      String h = sc.nextLine();
      int gI = generarIdentificador();
 
      BufferedWriter escritura = new BufferedWriter(new FileWriter("servicios.txt", true));
      String cadena = "\n" + gI + "," + tipoServicio + "," + cl.getCedula() + "," + co.getNombre() + "," + rD + "," + rH
          + "," + f + "," + h;
      escritura.write(cadena);
      String[] lista = cadena.split(",");
      escritura.close();
      return lista;
    } catch (IOException e) {
      return null;
    }
  }
 
  public Servicio(TipoServicio tS, Cliente cliente) {
    this.tipoServicio = tS;
    this.conductor = obtenerConductor();
    String[] lista = inicializeServiciosAttributes(conductor, cliente);
    this.identificador = lista[0];
    this.rutaDesde = lista[4];
    this.rutaHasta = lista[5];
    this.fecha = lista[6];
    this.hora = lista[7];
  }
 public String getIdentificador(){
      return identificador;
  }
}
