/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package juegorolessolid;

/**
 *
 * @author Sexxxrvio
 */
public interface Habilidad {

    String getNombre();

    int getCostoEnergia();

    int getCooldownMaximo();

    void ejecutar(Personajes lanzador, Personajes objetivo) throws EnergiaInsuficienteException;

    void reducirCooldown();

    boolean estaDisponible();
}
