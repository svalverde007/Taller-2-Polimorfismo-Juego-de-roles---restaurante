import java.util.ArrayList;
import java.util.List;

public abstract class Personajes {

    protected String nombre;
    protected int vida, ataque, nivel;
    protected List<Objeto> inventario;
    protected Objeto equipado;
    protected List<EstadoAlterado> estados;
    protected int energia;
    protected int energiaMax;
    protected int cooldownEspecial;
    protected int cooldownActual;

    public Personajes(String nombre, int vida, int ataque) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.nivel = 1;
        this.inventario = new ArrayList<>();
        this.equipado = null;
        this.estados = new ArrayList<>();
        this.energiaMax = 50;
        this.energia = this.energiaMax;
        this.cooldownEspecial = 1;
        this.cooldownActual = 0;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = vida; }

    public int getAtaque() { return ataque; }
    public void setAtaque(int ataque) { this.ataque = ataque; }

    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }

    public void recibirDanio(int cantidad) {
        this.vida -= cantidad;
    }

    public void subirNivel() {
        this.nivel++;
    }

    public void agregarObjeto(Objeto objeto) {
        inventario.add(objeto);
    }

    public boolean equiparObjeto(Objeto objeto) {
        if (inventario.contains(objeto)) {
            equipado = objeto;
            return true;
        }
        return false;
    }

    public boolean equiparObjeto(String nombreObjeto) {
        for (Objeto objeto : inventario) {
            if (objeto.getNombre().equalsIgnoreCase(nombreObjeto)) {
                equipado = objeto;
                return true;
            }
        }
        return false;
    }

    public void desequipar() { equipado = null; }

    public Objeto getObjetoEquipado() { return equipado; }

    public int getBonusAtaqueEquipado() {
        return equipado != null ? equipado.getBonusAtaque() : 0;
    }

    public int getBonusDefensaEquipado() {
        return equipado != null ? equipado.getBonusDefensa() : 0;
    }

    public abstract int atacar();
    public abstract int defender();

    public void agregarEstado(EstadoAlterado estado) {
        this.estados.add(estado);
        System.out.println(this.nombre + " ahora tiene el estado: " + estado.getNombre());
    }

    public boolean puedeAtacar() {
        for (EstadoAlterado est : estados) {
            if (!est.permiteAtacar()) {
                return false;
            }
        }
        return true;
    }

    public int obtenerAtaqueFinal() {
        int base = this.atacar();
        int mod = base;
        for (EstadoAlterado est : estados) {
            mod = est.modificarAtaque(mod);
        }
        return mod;
    }

    public int getEnergia() { return energia; }
    public int getEnergiaMax() { return energiaMax; }

    protected void setEnergiaMax(int max) {
        this.energiaMax = max;
        if (this.energia > max) this.energia = max;
    }

    protected void setCooldownEspecial(int turnos) {
        this.cooldownEspecial = Math.max(0, turnos);
    }

    // ← CAMBIO: sin excepciones, retorna false si no puede usar la habilidad
    public boolean usarHabilidadEspecial(Personajes objetivo) {
        if (this.cooldownActual > 0) {
            System.out.println(this.nombre + ": habilidad en cooldown (" + this.cooldownActual + " turnos restantes)");
            return false;
        }
        int costo = this.costoHabilidadEspecial();
        if (this.energia < costo) {
            System.out.println(this.nombre + " no tiene suficiente energía. Necesita: " + costo + ", tiene: " + this.energia);
            return false;
        }
        this.energia -= costo;
        this.cooldownActual = this.cooldownEspecial;
        this.habilidadEspecial(objetivo);
        return true;
    }

    protected abstract int costoHabilidadEspecial();
    protected abstract void habilidadEspecial(Personajes objetivo);

    public void procesarEstadosInicioTurno() {
        List<EstadoAlterado> copia = new ArrayList<>(estados);
        for (EstadoAlterado est : copia) {
            est.aplicarInicioTurno(this);
            est.decrementarDuracion();
            if (!est.estaActivo()) {
                estados.remove(est);
                System.out.println(this.nombre + " deja de tener el estado: " + est.getNombre());
            }
        }
        int regen = Math.max(1, this.energiaMax / 10);
        this.energia = Math.min(this.energiaMax, this.energia + regen);
        if (this.cooldownActual > 0) this.cooldownActual--;
    }
}