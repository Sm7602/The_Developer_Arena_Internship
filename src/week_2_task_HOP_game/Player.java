package week_2_task_HOP_game;

import java.util.Objects;

/**
 * Represents a player in the Number Master game.
 *
 * Responsibilities:
 * - Store player information
 * - Maintain score
 * - Track wins and losses
 * - Track best score
 * - Provide player statistics
 */
public class Player {

    private final String name;

    private int totalScore;
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    private int bestScore;

    /**
     * Creates a new player.
     *
     * @param name player name
     */
    public Player(String name) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Player name cannot be empty."
            );
        }

        this.name = name.trim();
        this.totalScore = 0;
        this.gamesPlayed = 0;
        this.gamesWon = 0;
        this.gamesLost = 0;
        this.bestScore = 0;
    }

    /**
     * Returns player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns total score.
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * Returns number of games played.
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Returns number of games won.
     */
    public int getGamesWon() {
        return gamesWon;
    }

    /**
     * Returns number of games lost.
     */
    public int getGamesLost() {
        return gamesLost;
    }

    /**
     * Returns best score.
     */
    public int getBestScore() {
        return bestScore;
    }

    /**
     * Adds score to player's total score.
     *
     * @param score score earned
     */
    public void addScore(int score) {

        if (score < 0) {
            throw new IllegalArgumentException(
                    "Score cannot be negative."
            );
        }

        totalScore += score;
    }

    /**
     * Records a successful game.
     */
    public void recordWin() {

        gamesPlayed++;
        gamesWon++;
    }

    /**
     * Records a lost game.
     */
    public void recordLoss() {

        gamesPlayed++;
        gamesLost++;
    }

    /**
     * Updates the player's best score.
     *
     * @param score current game score
     */
    public void updateBestScore(int score) {

        if (score > bestScore) {
            bestScore = score;
        }
    }

    /**
     * Returns player's win percentage.
     */
    public double getWinPercentage() {

        if (gamesPlayed == 0) {
            return 0.0;
        }

        return (gamesWon * 100.0) / gamesPlayed;
    }

    /**
     * Returns average score per game.
     */
    public double getAverageScore() {

        if (gamesPlayed == 0) {
            return 0.0;
        }

        return (double) totalScore / gamesPlayed;
    }

    /**
     * Returns formatted player statistics.
     */
    public String getStatistics() {

        return String.format(
                """
                Player Statistics
                ------------------
                Player Name    : %s
                Games Played   : %d
                Games Won      : %d
                Games Lost     : %d
                Win Percentage : %.2f%%
                Total Score    : %d
                Best Score     : %d
                Average Score  : %.2f
                """,
                name,
                gamesPlayed,
                gamesWon,
                gamesLost,
                getWinPercentage(),
                totalScore,
                bestScore,
                getAverageScore()
        );
    }

    /**
     * Displays player information.
     */
    public void displayStatistics() {

        System.out.println();
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║            PLAYER STATISTICS             ║");
        System.out.println("╚══════════════════════════════════════════╝");

        System.out.println(getStatistics());
    }

    /**
     * Checks whether two players are equal based on their names.
     */
    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (!(object instanceof Player other)) {
            return false;
        }

        return name.equalsIgnoreCase(other.name);
    }

    /**
     * Generates hash code based on player name.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }

    /**
     * Returns player name when object is printed.
     */
    @Override
    public String toString() {

        return "Player{" +
                "name='" + name + '\'' +
                ", totalScore=" + totalScore +
                ", gamesPlayed=" + gamesPlayed +
                ", gamesWon=" + gamesWon +
                ", gamesLost=" + gamesLost +
                ", bestScore=" + bestScore +
                '}';
    }
}