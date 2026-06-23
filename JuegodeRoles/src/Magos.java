
public class Magos extends Personajes {

    private int potenciaMagica;

    public Magos(String nombre, int vida, int ataque, int potenciaMagica) {
        super(nombre, vida, ataque);
        this.potenciaMagica = potenciaMagica;
        // configurar energía y cooldown para mago
        setEnergiaMax(100);
        setCooldownEspecial(3);
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
        System.out.println(this.nombre + " crea un escudo magico.");
        return (this.potenciaMagica / 2) + getBonusDefensaEquipado();
    }

    @Override
    protected int costoHabilidadEspecial() {
        return 20;
    }

    @Override
    protected void habilidadEspecial(Personajes objetivo) {
        int danio = this.potenciaMagica + 25; // bola de fuego
        objetivo.recibirDanio(danio);
        System.out.println(this.nombre + " lanza Bola de Fuego y causa " + danio + " de daño a " + objetivo.getNombre());
    }
}
