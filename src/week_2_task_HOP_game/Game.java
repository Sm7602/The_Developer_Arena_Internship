package week_2_task_HOP_game;

import java.util.Random;
import java.util.Scanner;

/**
 * Game class handles the complete game logic.
 *
 * Responsibilities:
 * - Level selection
 * - Random number generation
 * - Guess processing
 * - Attempts management
 * - Hint system
 * - Score calculation
 * - Player statistics
 * - Leaderboard integration
 */
public class Game {

    private final Scanner scanner;
    private final Random random;
    private final ScoreManager scoreManager;

    private static final int MAX_HINTS = 2;

    public Game(Scanner scanner) {
        this.scanner = scanner;
        this.random = new Random();
        this.scoreManager = new ScoreManager();
    }

    /**
     * Starts the game for a player.
     *
     * @param player current player
     */
    public void start(Player player) {

        boolean playAgain = true;

        while (playAgain) {

            Level level = selectLevel();

            if (level == null) {
                return;
            }

            playRound(player, level);

            playAgain = askPlayAgain();
        }
    }

    /**
     * Allows the player to select a difficulty level.
     *
     * @return selected Level
     */
    private Level selectLevel() {

        System.out.println();
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║             SELECT LEVEL                 ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║  1. 🟢 EASY                              ║");
        System.out.println("║     Range: 1 - 10 | Attempts: 5          ║");
        System.out.println("║                                          ║");
        System.out.println("║  2. 🟡 MEDIUM                            ║");
        System.out.println("║     Range: 1 - 50 | Attempts: 7          ║");
        System.out.println("║                                          ║");
        System.out.println("║  3. 🔴 HARD                              ║");
        System.out.println("║     Range: 1 - 100 | Attempts: 8         ║");
        System.out.println("║                                          ║");
        System.out.println("║  4. 🔥 EXPERT                            ║");
        System.out.println("║     Range: 1 - 500 | Attempts: 10        ║");
        System.out.println("║                                          ║");
        System.out.println("║  5. ↩ Back to Main Menu                 ║");
        System.out.println("╚══════════════════════════════════════════╝");

        int choice = GameUtils.getValidInteger(
                scanner,"Choose your level: ",1,5);

        return switch (choice) {
            case 1 -> Level.EASY;
            case 2 -> Level.MEDIUM;
            case 3 -> Level.HARD;
            case 4 -> Level.EXPERT;
            case 5 -> null;
            default -> null;
        };
    }

    /**
     * Runs one complete game round.
     *
     * @param player current player
     * @param level selected difficulty level
     */
    private void playRound(Player player, Level level) {

        int secretNumber = generateSecretNumber(level);

        int attemptsUsed = 0;
        int hintsUsed = 0;

        boolean won = false;

        System.out.println();
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║              GAME STARTED! 🎮            ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║ Player  : " + String.format("%-28s", player.getName()) + "║");
        System.out.println("║ Level   : " + String.format("%-28s", level.getDisplayName()) + "║");
        System.out.println("║ Range   : "
                + String.format("%-28s",
                level.getMinNumber() + " - " + level.getMaxNumber()) + "║");
        System.out.println("║ Attempts: "
                + String.format("%-28s", level.getMaxAttempts()) + "║");
        System.out.println("╚══════════════════════════════════════════╝");

        while (attemptsUsed < level.getMaxAttempts()) {

            int remainingAttempts =
                    level.getMaxAttempts() - attemptsUsed;

            System.out.println();
            System.out.println("------------------------------------------");
            System.out.println("Attempts Remaining: " + remainingAttempts);
            System.out.println("Hints Remaining   : " + (MAX_HINTS - hintsUsed));
            System.out.println("------------------------------------------");

            int guess = GameUtils.getValidInteger(
                    scanner,
                    "Enter your guess: ",
                    level.getMinNumber(),
                    level.getMaxNumber()
            );

            attemptsUsed++;

            if (guess == secretNumber) {

                won = true;

                int score = scoreManager.calculateScore(
                        level,
                        attemptsUsed,
                        hintsUsed
                );

                player.addScore(score);
                player.recordWin();
                player.updateBestScore(score);

                scoreManager.saveScore(
                        player.getName(),
                        level,
                        score
                );

                displayWinMessage(
                        secretNumber,
                        level,
                        attemptsUsed,
                        hintsUsed,
                        score
                );

                break;
            }

            if (guess < secretNumber) {

                System.out.println();
                System.out.println("❌ Too Low!");
                System.out.println("💡 Try a HIGHER number.");

            } else {

                System.out.println();
                System.out.println("❌ Too High!");
                System.out.println("💡 Try a LOWER number.");
            }

            /*
             * Give the player an opportunity to use a hint.
             */
            if (hintsUsed < MAX_HINTS
                    && attemptsUsed < level.getMaxAttempts()) {

                boolean useHint = GameUtils.getYesNo(
                        scanner,
                        "Do you want to use a hint? (Y/N): "
                );

                if (useHint) {

                    provideHint(
                            secretNumber,
                            level,
                            hintsUsed
                    );

                    hintsUsed++;
                }
            }
        }

        if (!won) {

            player.recordLoss();

            displayGameOverMessage(
                    secretNumber,
                    level,
                    attemptsUsed
            );
        }
    }

    /**
     * Generates a random number according to the selected level.
     */
    private int generateSecretNumber(Level level) {

        return random.nextInt(
                level.getMaxNumber() - level.getMinNumber() + 1
        ) + level.getMinNumber();
    }

    /**
     * Provides different hints based on the number.
     */
    private void provideHint(
            int secretNumber,
            Level level,
            int hintsUsed
    ) {

        System.out.println();
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║                 💡 HINT                  ║");
        System.out.println("╚══════════════════════════════════════════╝");

        if (hintsUsed == 0) {

            if (secretNumber % 2 == 0) {
                System.out.println("The number is EVEN.");
            } else {
                System.out.println("The number is ODD.");
            }

        } else {

            int midpoint =
                    (level.getMinNumber() + level.getMaxNumber()) / 2;

            if (secretNumber <= midpoint) {

                System.out.println(
                        "The number is in the LOWER half of the range."
                );

            } else {

                System.out.println(
                        "The number is in the UPPER half of the range."
                );
            }
        }

        System.out.println("⚠ Using a hint reduces your final score.");
    }

    /**
     * Displays winning information.
     */
    private void displayWinMessage(
            int secretNumber,
            Level level,
            int attemptsUsed,
            int hintsUsed,
            int score
    ) {

        System.out.println();
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║              🎉 YOU WON! 🎉              ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║ Secret Number : "
                + String.format("%-23s", secretNumber) + "║");
        System.out.println("║ Level         : "
                + String.format("%-23s", level.getDisplayName()) + "║");
        System.out.println("║ Attempts Used : "
                + String.format("%-23s", attemptsUsed) + "║");
        System.out.println("║ Hints Used    : "
                + String.format("%-23s", hintsUsed) + "║");
        System.out.println("║ Score         : "
                + String.format("%-23s", score) + "║");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    /**
     * Displays game-over information.
     */
    private void displayGameOverMessage(
            int secretNumber,
            Level level,
            int attemptsUsed
    ) {

        System.out.println();
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║             GAME OVER 😔                 ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║ Level         : "
                + String.format("%-23s", level.getDisplayName()) + "║");
        System.out.println("║ Attempts Used : "
                + String.format("%-23s", attemptsUsed) + "║");
        System.out.println("║ Secret Number : "
                + String.format("%-23s", secretNumber) + "║");
        System.out.println("╚══════════════════════════════════════════╝");

        System.out.println();
        System.out.println("Better luck next time! 💪");
    }

    /**
     * Asks whether the player wants another round.
     */
    private boolean askPlayAgain() {

        System.out.println();

        return GameUtils.getYesNo(
                scanner,
                "Do you want to play again? (Y/N): "
        );
    }

    /**
     * Displays game instructions.
     */
    public void showHowToPlay() {

        System.out.println();
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║              📖 HOW TO PLAY              ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║ 1. Select a difficulty level.            ║");
        System.out.println("║ 2. A secret number will be generated.     ║");
        System.out.println("║ 3. Guess the number within the attempts. ║");
        System.out.println("║ 4. You will receive HIGH/LOW hints.      ║");
        System.out.println("║ 5. You can use up to 2 hints.            ║");
        System.out.println("║ 6. Hints reduce your final score.        ║");
        System.out.println("║ 7. Fewer attempts = higher score.        ║");
        System.out.println("║ 8. Higher levels give higher scores.     ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    /**
     * Displays leaderboard.
     */
    public void showLeaderboard() {

        System.out.println();
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║              🏆 LEADERBOARD              ║");
        System.out.println("╚══════════════════════════════════════════╝");

        scoreManager.displayLeaderboard();
    }
}
