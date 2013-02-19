public abstract class Command {
    public static Command makeCommand(String roll, Player player) {
        return new RollCommand(player);
    }

    public abstract void execute();
}
