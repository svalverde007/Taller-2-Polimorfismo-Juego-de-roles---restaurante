
public class Congelado extends EstadoAlterado {

    public Congelado(int duracion) {
        super("Congelado", duracion);
    }

    @Override
    public void aplicarInicioTurno(Personajes p) {
        System.out.println(p.getNombre() + " está congelado y no puede atacar este turno.");
    }

    @Override
    public boolean permiteAtacar() {
        return false;
    }
}
