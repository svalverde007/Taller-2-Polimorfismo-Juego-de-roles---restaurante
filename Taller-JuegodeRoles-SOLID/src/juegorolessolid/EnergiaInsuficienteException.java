/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegorolessolid;

/**
 *
 * @author Sexxxrvio
 */
public class EnergiaInsuficienteException extends Exception {

    public EnergiaInsuficienteException(String nombrePersonaje, int energiaActual, int energiaNecesaria) {
        super(nombrePersonaje + " no tiene suficiente energía. "
                + "Tiene: " + energiaActual + ", necesita: " + energiaNecesaria);
    }
}
