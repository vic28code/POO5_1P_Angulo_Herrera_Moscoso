/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import Enums.TipoServicio;
import Usuarios.Cliente;

/**
 *
 * @author hilda
 */
public class ServicioTaxi extends Servicio{
    private int numPersonas;

    public ServicioTaxi(int numPersonas, TipoServicio tS, Cliente cliente) {
        super(tS, cliente);
        this.numPersonas = numPersonas;
    }
    
    
}
