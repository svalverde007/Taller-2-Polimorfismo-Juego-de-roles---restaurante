
public abstract class EstadoAlterado {

    protected String nombre;
    protected int duracion;

    public EstadoAlterado(String nombre, int duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
    }

    public void decrementarDuracion() {
        if (duracion > 0) {
            duracion--;
        }
    }

    public boolean estaActivo() {
        return duracion > 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void aplicarInicioTurno(Personajes p) {
    }

    public boolean permiteAtacar() {
        return true;
    }

    public int modificarAtaque(int ataqueBase) {
        return ataqueBase;
    }
}
