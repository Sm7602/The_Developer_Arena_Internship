package week_2_task_HOP_game;

public enum Level {

    EASY(
            "Easy",
            1,
            10,
            5,
            1000,
            1.0
    ),

    MEDIUM(
            "Medium",
            1,
            50,
            7,
            1500,
            1.5
    ),

    HARD(
            "Hard",
            1,
            100,
            8,
            2000,
            2.0
    ),

    EXPERT(
            "Expert",
            1,
            500,
            10,
            3000,
            3.0
    );

    private final String displayName;
    private final int minNumber;
    private final int maxNumber;
    private final int maxAttempts;
    private final int baseScore;
    private final double multiplier;

    /**
     * Constructor for Level.
     */
    Level(
            String displayName,
            int minNumber,
            int maxNumber,
            int maxAttempts,
            int baseScore,
            double multiplier
    ) {
        this.displayName = displayName;
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.maxAttempts = maxAttempts;
        this.baseScore = baseScore;
        this.multiplier = multiplier;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getMinNumber() {
        return minNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public int getBaseScore() {
        return baseScore;
    }

    public double getMultiplier() {
        return multiplier;
    }

    /**
     * Returns the range in readable format.
     */
    public String getRange() {
        return minNumber + " - " + maxNumber;
    }

    /**
     * Returns a formatted description of the level.
     */
    public String getDescription() {

        return displayName
                + " | Range: "
                + getRange()
                + " | Attempts: "
                + maxAttempts
                + " | Multiplier: "
                + multiplier
                + "x";
    }

    /**
     * Returns level information.
     */
    @Override
    public String toString() {
        return displayName;
    }
}
