
public class JuegoRPG {

    public static void main(String[] args) {

        Personajes conan = new Guerreros("Conan", 100, 20, 15, 10);
        Personajes merlin = new Magos("Merlin", 80, 15, 40);
        Personajes legolas = new Arqueros("Legolas", 90, 12, 10, 20);
        Personajes debil1 = new Guerreros("Guerrero suicida", 20, 50, 0, 0);
        Personajes debil2 = new Magos("Mago suicida", 20, 50, 0);

        Arma espada = new Arma("Espada corta", 5);
        Armadura escudo = new Armadura("Escudo de cuero", 3);
        Arma arco = new Arma("Arco ligero", 4);
        Armadura capaMagica = new Armadura("Capa mágica", 4);

        conan.agregarObjeto(espada);
        conan.agregarObjeto(escudo);
        conan.equiparObjeto(espada);

        legolas.agregarObjeto(arco);
        legolas.equiparObjeto(arco);

        merlin.agregarObjeto(capaMagica);
        merlin.equiparObjeto(capaMagica);

        // Ejemplos de estados alterados
        // Envenenar a Merlin por 3 turnos (5 daño por turno)
        merlin.agregarEstado(new Envenenado(3, 5));
        // Dar aumento de fuerza a Conan por 2 turnos (+10 ataque)
        conan.agregarEstado(new AumentoFuerza(2, 10));

        System.out.println("=== BIENVENIDOS A LA ARENA ===");

        iniciarBatalla(conan, merlin);

        System.out.println("\n=== SEGUNDO COMBATE ===");

        iniciarBatalla(merlin, legolas);

        System.out.println("\n=== TERCER COMBATE ===");

        iniciarBatalla(debil1, debil2);
    }

        public static void iniciarBatalla(Personajes p1, Personajes p2) {
            System.out.println("¡Combate entre " + p1.nombre + " y " + p2.nombre + "!");

            int turnos = 0;
            while (p1.vida > 0 && p2.vida > 0 && turnos < 10) {
                System.out.println("\n--- Turno " + (turnos + 1) + " ---");

                p1.procesarEstadosInicioTurno();
                p2.procesarEstadosInicioTurno();

                // Turno de p1
                if (p1.puedeAtacar()) {
                    boolean usoHabilidad = p1.usarHabilidadEspecial(p2);
                    if (!usoHabilidad) {
                        int danio1 = Math.max(0, p1.obtenerAtaqueFinal() - p2.defender());
                        p2.vida -= danio1;
                        System.out.println(p1.nombre + " causa " + danio1 + " de daño a " + p2.nombre);
                    }
                } else {
                    System.out.println(p1.nombre + " no puede atacar este turno.");
                }

                // Turno de p2
                if (p1.vida > 0) {
                    if (p2.puedeAtacar()) {
                        boolean usoHabilidad = p2.usarHabilidadEspecial(p1);
                        if (!usoHabilidad) {
                            int danio2 = Math.max(0, p2.obtenerAtaqueFinal() - p1.defender());
                            p1.vida -= danio2;
                            System.out.println(p2.nombre + " causa " + danio2 + " de daño a " + p1.nombre);
                        }
                    } else {
                        System.out.println(p2.nombre + " no puede atacar este turno.");
                    }
                }

                turnos++;
            }

            System.out.println("\n--- RESULTADO FINAL ---");
            if (p1.vida <= 0 && p2.vida <= 0) {
                System.out.println("¡Empate! Ambos han caído.");
            } else if (p1.vida > 0) {
                System.out.println("¡Ganador: " + p1.nombre + "!");
            } else {
                System.out.println("¡Ganador: " + p2.nombre + "!");
            }

        }
}
