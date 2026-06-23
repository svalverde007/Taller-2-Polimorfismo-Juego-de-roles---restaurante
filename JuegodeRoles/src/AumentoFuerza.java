
public class AumentoFuerza extends EstadoAlterado {

    private int bonus;

    public AumentoFuerza(int duracion, int bonus) {
        super("Aumento de Fuerza", duracion);
        this.bonus = bonus;
    }

    @Override
    public int modificarAtaque(int ataqueBase) {
        System.out.println("Aumento de fuerza: +" + bonus + " al ataque.");
        return ataqueBase + bonus;
    }
}
