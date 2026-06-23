
public class Arqueros extends Personajes {

    private int cantidadFlechas, precision;

    public Arqueros(String nombre, int vida, int ataque, int cantidadFlechas, int precision) {
        super(nombre, vida, ataque);
        this.cantidadFlechas = cantidadFlechas;
        this.precision = precision;
        // configurar energía y cooldown para arquero
        setEnergiaMax(70);
        setCooldownEspecial(2);
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

    @Override
    protected int costoHabilidadEspecial() {
        return 10;
    }

    @Override
    protected void habilidadEspecial(Personajes objetivo) {
        int danio = this.atacar() + 10; // tiro certero
        objetivo.recibirDanio(danio);
        System.out.println(this.nombre + " usa Tiro Certero y causa " + danio + " de daño a " + objetivo.getNombre());
    }

}
