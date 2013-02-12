abstract class Tool {
    public abstract String display();

    public abstract int getPoint();

    public boolean equals(Tool tool) {
        return display().equals(tool.display());
    }
}
