
public class Guerreros extends Personajes {

    private int fuerzaFisica, armadura;

    public Guerreros(String nombre, int vida, int ataque, int fuerzaFisica, int armadura) {
        super(nombre, vida, ataque);
        this.fuerzaFisica = fuerzaFisica;
        this.armadura = armadura;
        // configurar energía y cooldown específicos de guerrero
        setEnergiaMax(60);
        setCooldownEspecial(2);
    }

    public int getFuerzaFisica() {
        return fuerzaFisica;
    }

    public void setFuerzaFisica(int fuerzaFisica) {
        this.fuerzaFisica = fuerzaFisica;
    }

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    @Override
    public int atacar() {
        return this.ataque + this.fuerzaFisica + getBonusAtaqueEquipado();
    }

    @Override
    public int defender() {
        return this.armadura + getBonusDefensaEquipado();
    }

    @Override
    protected int costoHabilidadEspecial() {
        return 15;
    }

    @Override
    protected void habilidadEspecial(Personajes objetivo) {
        int danio = this.atacar() + 15; // golpe poderoso
        objetivo.recibirDanio(danio);
        System.out.println(this.nombre + " usa Golpe Poderoso y causa " + danio + " de daño a " + objetivo.getNombre());
    }

}
