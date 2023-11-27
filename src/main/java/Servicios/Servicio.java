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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
  private ArrayList<Conductor> conductores = new ArrayList<>();
 
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
            String tipo= lista[6];
            if ("R".equals(tipo)){
                Conductor conductor1=new Conductor(lista[3], lista[4]);
                if (conductor1.getEstadoConductor().equals(EstadoConductor.D)){
                    conductores.add(conductor1);
                }
            }
        }
      lector.close();
    }catch (IOException e) {
    }
  }
  private Conductor obtenerConductor(){
      listaConductores();
      Random rd = new Random();
      int numeroAleatorio=rd.nextInt(conductores.size());
      return conductores.get(numeroAleatorio);
  }
 
  private String[] inicializeServiciosAttributes() {
      Scanner sc = new Scanner(System.in);
      System.out.print("desde donde: ");
      String rutaD = sc.nextLine();
      System.out.print("hasta donde: ");
      String rutaH = sc.nextLine();
      String f;
      do{
        System.out.print("Fecha del viaje(DD/MM/AAAA): ");
        f = sc.nextLine();
        try {
            if (validarFecha(f)) {
                System.out.println("La fecha es válida.");
                break;
            }else{
                System.out.println("La fecha no es válida");
            }
        } catch (ParseException e) {
            System.out.println("Fecha no válida.");
        }
      }while(f!=null);
      String h;
      do{
          System.out.print("Hora del viaje(HH:MM): ");
          h= sc.nextLine();
          try {
            if (validarHora(h)) {
                System.out.println("La hora es válida.");
                break;
            } else {
                System.out.println("La hora no es válida.");
            }
        } catch (ParseException e) {
            System.out.println("Hora no válida.");
        }
      }while(h!=null);
      
      
      int gI = generarIdentificador();
      String cadena = gI  + "," + rutaD + "," + rutaH+ "," + f + "," + h;
      String[] lista = cadena.split(",");
      return lista;
  }
  
  public static boolean validarFecha(String fecha) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        Date date = sdf.parse(fecha);
        if (date != null) {
            int anioActual = Calendar.getInstance().get(Calendar.YEAR);

            // Obtener el año de la fecha ingresada
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int anioFecha = cal.get(Calendar.YEAR);
            // Verificar la condición: año mayor a 2023 si el mes es menor al actual
            if (cal.get(Calendar.MONTH) < Calendar.getInstance().get(Calendar.MONTH) && anioFecha <= 2023) {
                return false;
            }
        } else {
            // La fecha no se pudo parsear correctamente
            return false;
        }

        // La fecha cumple con todas las condiciones
        return true;
    }
  
  public static boolean validarHora(String hora) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        sdf.setLenient(false);

        // Intentar parsear la hora
        Date time = sdf.parse(hora);

        // Verificar si la hora se parseó correctamente y está dentro del rango permitido
        if (time != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(time);

            int horas = cal.get(Calendar.HOUR_OF_DAY);
            int minutos = cal.get(Calendar.MINUTE);

            // Verificar el rango permitido (0-23 horas y 0-59 minutos)
            return horas >= 0 && horas <= 23 && minutos >= 0 && minutos <= 59;
        } else {
            // La hora no se pudo parsear correctamente
            return false;
        }
  }
  
  public void registrarServicio(Cliente cl,Conductor co){
      try{
          BufferedWriter escritura = new BufferedWriter(new FileWriter("servicios.txt", true));
          String cadena="\n"+identificador+ "," +tipoServicio+ "," +cl.getCedula()+","+co.getNombre()+ "," +rutaDesde+ "," +rutaHasta+ "," +fecha+ "," +hora;
          escritura.write(cadena);
      escritura.close();
      }catch (IOException e) {
    }
  }
  public Servicio(TipoServicio tS) {
    this.tipoServicio = tS;
    this.conductor = obtenerConductor();
    String[] lista = inicializeServiciosAttributes();
    this.identificador = lista[0];
    this.rutaDesde = lista[1];
    this.rutaHasta = lista[2];
    this.fecha = lista[3];
    this.hora = lista[4];
  }
 public String getIdentificador(){
      return identificador;
  }

 public Conductor getConductor() {
        return conductor;
  }

 public String getFecha() {
        return fecha;
 }
 
 
}
