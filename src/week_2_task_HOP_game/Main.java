package week_2_task_HOP_game;

import java.util.Scanner;

/**
 * Main class of the Number Guessing Game.
 *
 * Responsibilities:
 * - Start the application
 * - Display the main menu
 * - Handle user menu selection
 * - Start the game
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Game game = new Game(scanner);

        displayWelcomeMessage();

        String playerName = GameUtils.getValidPlayerName(scanner);
        Player player = new Player(playerName);

        boolean running = true;

        while (running) {

            displayMainMenu();

            int choice = GameUtils
            		.getValidInteger(scanner,"Enter your choice: ",1,4);

            switch (choice) {

                case 1 -> {
                    game.start(player);
                }

                case 2 -> {
                    game.showHowToPlay();
                }

                case 3 -> {
                    game.showLeaderboard();
                }

                case 4 -> {
                    running = false;
                    displayGoodbyeMessage();
                }

                default -> System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }

    /**
     * Displays welcome message.
     */
    private static void displayWelcomeMessage() {

        System.out.println();
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║                                          ║");
        System.out.println("║          🎯 NUMBER MASTER 🎯             ║");
        System.out.println("║       MULTI-LEVEL GUESSING GAME          ║");
        System.out.println("║                                          ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Welcome to Number Master!");
        System.out.println("Test your guessing skills across multiple levels.");
        System.out.println();
    }

    /**
     * Displays the main menu.
     */
    private static void displayMainMenu() {

        System.out.println();
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║                MAIN MENU                 ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║  1. 🎮 Start Game                        ║");
        System.out.println("║  2. 📖 How to Play                       ║");
        System.out.println("║  3. 🏆 Leaderboard                       ║");
        System.out.println("║  4. 🚪 Exit                              ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    /**
     * Displays goodbye message.
     */
    private static void displayGoodbyeMessage() {

        System.out.println();
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║       Thank you for playing! 🎯          ║");
        System.out.println("║                                          ║");
        System.out.println("║          See you next time! 👋           ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println();
    }
}
