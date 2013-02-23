package tw.rich;

import java.util.Random;

public class Dice {
    private static final int DICE_MAX_NUMBER = 6;

    public static int roll() {
        Random random = new Random();
        return random.nextInt(DICE_MAX_NUMBER) + 1;
    }
}
