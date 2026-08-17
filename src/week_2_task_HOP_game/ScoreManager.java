package week_2_task_HOP_game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Manages game scores and leaderboard.
 *
 * Responsibilities:
 * - Calculate game score
 * - Save scores to file
 * - Load leaderboard
 * - Sort leaderboard
 * - Display top players
 */
public class ScoreManager {

    private static final String DATA_DIRECTORY = "data";
    private static final String LEADERBOARD_FILE =
            DATA_DIRECTORY + "/leaderboard.txt";

    private static final int MAX_LEADERBOARD_ENTRIES = 10;

    private static final int ATTEMPT_PENALTY = 100;
    private static final int HINT_PENALTY = 150;

    private final List<ScoreEntry> leaderboard;

    /**
     * Creates ScoreManager and loads existing leaderboard.
     */
    public ScoreManager() {

        leaderboard = new ArrayList<>();

        createDataDirectory();
        loadLeaderboard();
    }

    /**
     * Calculates score based on level, attempts and hints.
     *
     * Formula:
     *
     * Base Score
     * - Attempt Penalty
     * - Hint Penalty
     *
     * Then difficulty multiplier is applied.
     *
     * @param level selected difficulty
     * @param attemptsUsed number of attempts used
     * @param hintsUsed number of hints used
     * @return final score
     */
    public int calculateScore(
            Level level,
            int attemptsUsed,
            int hintsUsed
    ) {

        if (level == null) {
            throw new IllegalArgumentException(
                    "Level cannot be null."
            );
        }

        if (attemptsUsed <= 0) {
            throw new IllegalArgumentException(
                    "Attempts must be greater than zero."
            );
        }

        if (hintsUsed < 0) {
            throw new IllegalArgumentException(
                    "Hints cannot be negative."
            );
        }

        int score = level.getBaseScore();

        /*
         * Deduct points for wrong attempts.
         *
         * The first attempt does not receive a penalty.
         */
        int wrongAttempts = attemptsUsed - 1;

        score -= wrongAttempts * ATTEMPT_PENALTY;

        /*
         * Deduct points for using hints.
         */
        score -= hintsUsed * HINT_PENALTY;

        /*
         * Score should never become negative.
         */
        score = Math.max(score, 0);

        /*
         * Apply difficulty multiplier.
         */
        score = (int) (score * level.getMultiplier());

        return score;
    }

    /**
     * Saves a player's score.
     *
     * @param playerName player name
     * @param level game level
     * @param score earned score
     */
    public void saveScore(
            String playerName,
            Level level,
            int score
    ) {

        if (playerName == null || playerName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Player name cannot be empty."
            );
        }

        if (level == null) {
            throw new IllegalArgumentException(
                    "Level cannot be null."
            );
        }

        if (score < 0) {
            throw new IllegalArgumentException(
                    "Score cannot be negative."
            );
        }

        ScoreEntry entry = new ScoreEntry(
                playerName.trim(),
                level.getDisplayName(),
                score
        );

        leaderboard.add(entry);

        sortLeaderboard();
        limitLeaderboard();

        saveLeaderboardToFile();
    }

    /**
     * Displays the leaderboard.
     */
    public void displayLeaderboard() {

        if (leaderboard.isEmpty()) {

            System.out.println();
            System.out.println("No scores available yet.");
            System.out.println("Play a game to enter the leaderboard!");

            return;
        }

        sortLeaderboard();

        System.out.println();
        System.out.println(
                "================================================================"
        );

        System.out.printf(
                "%-6s %-20s %-12s %-10s%n",
                "Rank",
                "Player",
                "Level",
                "Score"
        );

        System.out.println(
                "----------------------------------------------------------------"
        );

        int rank = 1;

        for (ScoreEntry entry : leaderboard) {

            System.out.printf(
                    "%-6d %-20s %-12s %-10d%n",
                    rank,
                    truncate(entry.playerName(), 20),
                    entry.level(),
                    entry.score()
            );

            rank++;
        }

        System.out.println(
                "================================================================"
        );
    }

    /**
     * Returns a copy of the leaderboard.
     */
    public List<ScoreEntry> getLeaderboard() {

        return new ArrayList<>(leaderboard);
    }

    /**
     * Creates the data directory if it doesn't exist.
     */
    private void createDataDirectory() {

        try {

            Files.createDirectories(
                    Path.of(DATA_DIRECTORY)
            );

        } catch (IOException exception) {

            System.out.println(
                    "Warning: Could not create data directory."
            );
        }
    }

    /**
     * Loads leaderboard from file.
     *
     * File format:
     *
     * playerName|level|score
     */
    private void loadLeaderboard() {

        Path filePath = Path.of(LEADERBOARD_FILE);

        if (!Files.exists(filePath)) {
            return;
        }

        try (BufferedReader reader =
                     Files.newBufferedReader(filePath)) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|");

                if (parts.length != 3) {
                    continue;
                }

                String playerName = parts[0];
                String level = parts[1];

                try {

                    int score = Integer.parseInt(parts[2]);

                    leaderboard.add(
                            new ScoreEntry(
                                    playerName,
                                    level,
                                    score
                            )
                    );

                } catch (NumberFormatException exception) {

                    System.out.println(
                            "Skipping invalid score entry."
                    );
                }
            }

            sortLeaderboard();
            limitLeaderboard();

        } catch (IOException exception) {

            System.out.println(
                    "Warning: Could not load leaderboard."
            );
        }
    }

    /**
     * Saves the current leaderboard to file.
     */
    private void saveLeaderboardToFile() {

        Path filePath = Path.of(LEADERBOARD_FILE);

        try (BufferedWriter writer =
                     Files.newBufferedWriter(
                             filePath,
                             StandardOpenOption.CREATE,
                             StandardOpenOption.TRUNCATE_EXISTING,
                             StandardOpenOption.WRITE
                     )) {

            for (ScoreEntry entry : leaderboard) {

                writer.write(
                        entry.playerName()
                                + "|"
                                + entry.level()
                                + "|"
                                + entry.score()
                );

                writer.newLine();
            }

        } catch (IOException exception) {

            System.out.println(
                    "Warning: Could not save leaderboard."
            );
        }
    }

    /**
     * Sorts leaderboard from highest score to lowest score.
     *
     * If scores are equal, player name is used as
     * the secondary sorting criteria.
     */
    private void sortLeaderboard() {

        leaderboard.sort(
                Comparator
                        .comparingInt(ScoreEntry::score)
                        .reversed()
                        .thenComparing(
                                ScoreEntry::playerName,
                                String.CASE_INSENSITIVE_ORDER
                        )
        );
    }

    /**
     * Keeps only top leaderboard entries.
     */
    private void limitLeaderboard() {

        if (leaderboard.size() > MAX_LEADERBOARD_ENTRIES) {

            leaderboard.subList(
                    MAX_LEADERBOARD_ENTRIES,
                    leaderboard.size()
            ).clear();
        }
    }

    /**
     * Prevents very long player names from breaking
     * the leaderboard layout.
     */
    private String truncate(
            String text,
            int maxLength
    ) {

        if (text.length() <= maxLength) {
            return text;
        }

        return text.substring(0, maxLength - 3) + "...";
    }

    /**
     * Represents one leaderboard score entry.
     *
     * Java record is used because this is immutable
     * data that does not require setters.
     */
    public record ScoreEntry(
            String playerName,
            String level,
            int score
    ) {
    }
}