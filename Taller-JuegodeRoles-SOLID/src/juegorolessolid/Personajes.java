package juegorolessolid;

import java.util.ArrayList;
import java.util.List;

public abstract class Personajes {

    protected String nombre;
    protected int vida, ataque;
    // se agg 2 atributos
    protected int energia;
    protected int energiaMaxima;
    protected List<Objeto> inventario = new ArrayList<>();
    protected Arma armaEquipada;
    protected Armadura armaduraEquipada;
    protected Habilidad habilidadEspecial;

    public Personajes(String nombre, int vida, int ataque, int energiaMaxima) { // NUEVO: parámetro energía
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.energiaMaxima = energiaMaxima;
        this.energia = energiaMaxima;         // incia con energía llena
    }

    // recupera energía al final de cada turno, sin pasarse del máximo
    public void recuperarEnergia(int cantidad) {
        this.energia = Math.min(this.energia + cantidad, this.energiaMaxima);
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getEnergiaMaxima() {
        return energiaMaxima;
    }

    public void equiparArma(Arma a) {
        this.armaEquipada = a;
    }

    public void equiparArmadura(Armadura a) {
        this.armaduraEquipada = a;
    }

    public int getBonusAtaqueEquipado() {
        return (armaEquipada != null) ? armaEquipada.getBonusAtaque() : 0;
    }

    public int getBonusDefensaEquipado() {
        return (armaduraEquipada != null) ? armaduraEquipada.getBonusDefensa() : 0;
    }

    public abstract int atacar();

    public abstract int defender();

    public void recibirDanio(int danio) {
        this.vida -= danio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public List<Objeto> getInventario() {
        return inventario;
    }

    public void setInventario(List<Objeto> inventario) {
        this.inventario = inventario;
    }

    public Arma getArmaEquipada() {
        return armaEquipada;
    }

    public void setArmaEquipada(Arma armaEquipada) {
        this.armaEquipada = armaEquipada;
    }

    public Armadura getArmaduraEquipada() {
        return armaduraEquipada;
    }

    public void setArmaduraEquipada(Armadura armaduraEquipada) {
        this.armaduraEquipada = armaduraEquipada;
    }

    public void setHabilidadEspecial(Habilidad habilidad) {
        this.habilidadEspecial = habilidad;
    }

    public void usarHabilidadEspecial(Personajes objetivo) throws EnergiaInsuficienteException {
        if (habilidadEspecial == null) {
            System.out.println(nombre + " no tiene habilidad especial asignada.");
            return;
        }
        habilidadEspecial.ejecutar(this, objetivo);
    }

    // se llama al final de cada turno
    public void reducirCooldown() {
        if (habilidadEspecial != null) {
            habilidadEspecial.reducirCooldown();
        }
    }

    public boolean habilidadDisponible() {
        if (habilidadEspecial != null) {
            return habilidadEspecial.estaDisponible();
        }
        return false;
    }

}
