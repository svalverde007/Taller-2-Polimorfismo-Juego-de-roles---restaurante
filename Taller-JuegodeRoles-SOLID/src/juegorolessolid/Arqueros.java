/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegorolessolid;

public class Arqueros extends Personajes {

    private int cantidadFlechas, precision;

    public Arqueros(String nombre, int vida, int ataque, int cantidadFlechas, int precision) {
        super(nombre, vida, ataque, 80); 
        this.cantidadFlechas = cantidadFlechas;
        this.precision = precision;
    }

    public int getCantidadFlechas() {
        return cantidadFlechas;
    }

    public void setCantidadFlechas(int cantidadFlechas) {
        this.cantidadFlechas = cantidadFlechas;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    @Override
    public int atacar() {
        if (cantidadFlechas > 0) {
            cantidadFlechas--;
            return this.ataque + this.precision + getBonusAtaqueEquipado();
        }
        return this.ataque + getBonusAtaqueEquipado();
    }

    @Override
    public int defender() {
        return 5 + getBonusDefensaEquipado();
    }

}
