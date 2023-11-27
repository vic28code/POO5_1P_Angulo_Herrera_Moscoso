/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import Enums.TipoServicio;
import Usuarios.Cliente;
//import java.util.ArrayList;


/**
 *
 * @author hilda
 */
public class ServicioEncomienda extends Servicio{
    private int cantProductosEnviar;
    private double pesoKG;
    private String[] tipoEncomienda={"Medicamentos","Documentos","Ropa"};

    public ServicioEncomienda(int cantProductosEnviar, double pesoKG, TipoServicio tS, Cliente cliente) {
        super(tS, cliente);
        this.cantProductosEnviar = cantProductosEnviar;
        this.pesoKG = pesoKG;
    }
    
    
    
    
   
    

    
}
