package juegorolessolid;

import java.util.Scanner;

public class JuegoRPG {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Creando Personaje 1:");
        Personajes p1 = crearPersonaje(sc);

        System.out.println("Creando Personaje 2:");
        Personajes p2 = crearPersonaje(sc);

        iniciarBatalla(p1, p2);
    }

    public static Personajes crearPersonaje(Scanner sc) {
        System.out.println("Elige: 1. Guerrero, 2. Mago, 3. Arquero");
        int opcion = sc.nextInt();
        sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        Personajes p;

        if (opcion == 1) {
            p = new Guerreros(nombre, 100, 20, 15, 10);
            p.setHabilidadEspecial(new GolpeFatal());
        } else if (opcion == 2) {
            p = new Mago(nombre, 80, 15, 40);
            p.setHabilidadEspecial(new BolaDeFuego());
        } else if (opcion == 3) {
            p = new Arqueros(nombre, 90, 12, 10, 20);
            p.setHabilidadEspecial(new DisparoPreciso());
        } else {
            p = null;
        }

        return p;
    }

    public static void iniciarBatalla(Personajes p1, Personajes p2) {
        p1.equiparArma(new Arma("Espada de Acero", 10));
        p1.equiparArmadura(new Armadura("Escudo de Madera", 5));
        p2.equiparArma(new Arma("Daga Básica", 5));

        System.out.println("\n¡Combate entre " + p1.getNombre() + " y " + p2.getNombre() + "!\n");

        int turnos = 0;
        while (p1.getVida() > 0 && p2.getVida() > 0 && turnos < 10) {

            turnos++;
            System.out.println("--- Turno " + turnos + " ---");
            System.out.println(p1.getNombre() + " | Vida: " + p1.getVida() + " | Energía: " + p1.getEnergia());
            System.out.println(p2.getNombre() + " | Vida: " + p2.getVida() + " | Energía: " + p2.getEnergia());

            // p1 intenta habilidad especial si está disponible
            if (p1.habilidadDisponible()) {
                try {
                    p1.usarHabilidadEspecial(p2);
                } catch (EnergiaInsuficienteException e) {
                    System.out.println("⚠ " + e.getMessage());
                    // Si no hay energía, hace ataque normal como fallback
                    int danio = Math.max(0, p1.atacar() - p2.defender());
                    p2.recibirDanio(danio);
                    System.out.println(p1.getNombre() + " ataca normalmente y causa " + danio + " de daño.");
                }
            } else {
                // Habilidad en cooldown: ataque normal
                int danio = Math.max(0, p1.atacar() - p2.defender());
                p2.recibirDanio(danio);
                System.out.println(p1.getNombre() + " ataca normalmente y causa " + danio + " de daño."
                        + " (Habilidad en cooldown)");
            }

            // al final del turno se reduce cooldown y se recupera energía
            p1.reducirCooldown();
            p1.recuperarEnergia(15);
            p2.reducirCooldown();
            p2.recuperarEnergia(15);

            System.out.println();
        }

        System.out.println("--- RESULTADO FINAL ---");
        if (p1.getVida() <= 0 && p2.getVida() <= 0) {
            System.out.println("¡Empate! Ambos han caído.");
        } else if (p1.getVida() > 0) {
            System.out.println("¡Ganador: " + p1.getNombre() + "!");
        } else {
            System.out.println("¡Ganador: " + p2.getNombre() + "!");
        }
    }
}
