/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegorolessolid;

/**
 *
 * @author Sexxxrvio
 */
public class DisparoPreciso implements Habilidad {

    private int cooldownActual;

    @Override
    public String getNombre() {
        return "Disparo Preciso";
    }

    @Override
    public int getCostoEnergia() {
        return 25;
    }

    @Override
    public int getCooldownMaximo() {
        return 2;
    }

    @Override
    public void ejecutar(Personajes lanzador, Personajes objetivo) throws EnergiaInsuficienteException {
        if (lanzador.getEnergia() < getCostoEnergia()) {
            throw new EnergiaInsuficienteException(lanzador.getNombre(), lanzador.getEnergia(), getCostoEnergia());
        }
        lanzador.setEnergia(lanzador.getEnergia() - getCostoEnergia());
        int danio = lanzador.getAtaque() + 25;
        objetivo.recibirDanio(danio);
        cooldownActual = getCooldownMaximo();
        System.out.println(lanzador.getNombre() + " usa Disparo Preciso y causa " + danio + " de daño!");
    }

    public int getCooldownActual() {
        return cooldownActual;
    }

    public void reducirCooldown() {
        if (cooldownActual > 0) {
            cooldownActual--;
        }
    }

    public boolean estaDisponible() {
        return cooldownActual == 0;
    }

}
