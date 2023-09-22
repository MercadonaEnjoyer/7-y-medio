import java.util.Stack;
import java.util.Random;
import java.util.Scanner;

class Carta {
    private String nombre;
    private double valor;

    public Carta(String nombre, double valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

public class SieteYMedioConObjetos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        Stack<Carta> deck = new Stack<>();
        String[] nombresCartas = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        double[] valoresCartas = {1, 2, 3, 4, 5, 6, 7, 0.5, 0.5, 0.5, 1, 1};

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < nombresCartas.length; j++) {
                deck.push(new Carta(nombresCartas[j], valoresCartas[j]));
            }
        }

        double playerScore = 0;
        double dealerScore = 0;
        boolean gameOver = false;

        while (!gameOver) {
            // Repartir una carta al jugador
            int randomIndex = random.nextInt(deck.size());
            Carta carta = deck.remove(randomIndex);
            double cardValue = carta.getValor();
            playerScore += cardValue;

            System.out.println("Tienes una carta: " + carta.getNombre() + ". Tu puntaje actual es: " + playerScore);

            if (playerScore == 7.5) {
                System.out.println("¡Has ganado! Tienes 7 y medio.");
                gameOver = true;
            } else if (playerScore > 7.5) {
                System.out.println("Te has pasado de 7 y medio. Has perdido.");
                gameOver = true;
            } else {
                System.out.print("¿Quieres otra carta? (S/N): ");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("N")) {
                    gameOver = true;
                }
            }
        }

        // Turno del dealer
        while (dealerScore < 7 && dealerScore < playerScore && playerScore <= 7.5) {
            int randomIndex = random.nextInt(deck.size());
            Carta carta = deck.remove(randomIndex);
            double cardValue = carta.getValor();
            dealerScore += cardValue;
        }

        System.out.println("El dealer tiene un puntaje de: " + dealerScore);

        if (dealerScore > 7.5) {
            System.out.println("El dealer se ha pasado de 7 y medio. ¡Has ganado!");
        }else if (playerScore > 7.5){
            System.out.println("El dealer ha ganado.");
        }else if (playerScore > dealerScore) {
            System.out.println("¡Has ganado!");
        } else if (playerScore < dealerScore) {
            System.out.println("El dealer ha ganado.");
        } else {
            System.out.println("Es un empate.");
        }

        scanner.close();
    }
}
