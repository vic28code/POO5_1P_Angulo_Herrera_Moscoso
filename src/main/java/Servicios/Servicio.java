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
import java.util.Calendar;
import java.util.Date;

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

  private int generarIdentificador() {
    Random rd = new Random();
    int numero = rd.nextInt(10000) + 1000;
    return numero;
  }


  private String[] inicializeServiciosAttributes(Conductor co, Cliente cl) {
    try {
      Scanner sc = new Scanner(System.in);
      System.out.print("desde donde: ");
      String rD = sc.nextLine();
      System.out.print("hasta donde: ");
      String rH = sc.nextLine();
      Date today = Calendar.getInstance().getTime();
      String day = String.valueOf(today);
      String[] listaDia = day.split(" ");
      String f = listaDia[2] + "/" + listaDia[1] + "/" + listaDia[5];
      String h = listaDia[3];
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
    //this.conductor = obtenerConductor();
    String[] lista = inicializeServiciosAttributes(conductor, cliente);
    this.identificador = lista[0];
    this.rutaDesde = lista[4];
    this.rutaHasta = lista[5];
    this.fecha = lista[6];
    this.hora = lista[7];
  }

}