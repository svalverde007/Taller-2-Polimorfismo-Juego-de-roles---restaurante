
public class Envenenado extends EstadoAlterado {

    private int danoPorTurno;

    public Envenenado(int duracion, int danoPorTurno) {
        super("Envenenado", duracion);
        this.danoPorTurno = danoPorTurno;
    }

    @Override
    public void aplicarInicioTurno(Personajes p) {
        p.recibirDanio(danoPorTurno);
        System.out.println(p.getNombre() + " recibe " + danoPorTurno + " de daño por envenenamiento.");
    }
}
