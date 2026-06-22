package juegoderoles;

public class Magos extends Personajes {

    private int potenciaMagica;

    public Magos(String nombre, int vida, int ataque, int potenciaMagica) {
        super(nombre, vida, ataque);
        this.potenciaMagica = potenciaMagica;

    }

    public int getPotenciaMagica() {
        return potenciaMagica;
    }

    public void setPotenciaMagica(int potenciaMagica) {
        this.potenciaMagica = potenciaMagica;
    }

    @Override
    public int atacar() {
        return this.ataque + (this.potenciaMagica / 2) + getBonusAtaqueEquipado();
    }

    @Override
    public int defender() {
        System.out.println(this.nombre + " crea un escudo mágico.");
        return (this.potenciaMagica / 2) + getBonusDefensaEquipado();
    }
}