/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegorolessolid;

public abstract class Objeto {

    protected String nombre;
    protected int bonusAtaque;
    protected int bonusDefensa;

    public Objeto(String nombre, int bonusAtaque, int bonusDefensa) {
        this.nombre = nombre;
        this.bonusAtaque = bonusAtaque;
        this.bonusDefensa = bonusDefensa;
    }

    public String getNombre() {
        return nombre;
    }

    public int getBonusAtaque() {
        return bonusAtaque;
    }

    public int getBonusDefensa() {
        return bonusDefensa;
    }

    @Override
    public String toString() {
        return nombre + " (Ataque: " + bonusAtaque + ", Defensa: " + bonusDefensa + ")";
    }
}
