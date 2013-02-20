class Property extends Site {
    private PropertyLevel level;
    private Player owner = null;

    public Property(PropertyLevel level) {
        super(level.getType());
        this.level = level;
    }

    public void upgrade() {
        level = level.upgrade();
        setType(level.getType());
    }

    public PropertyLevel getLevel() {
        return level;
    }

    public int getSalePrice() {
        return level.getSalePrice();
    }

    public int getTollFee() {
        return level.getTollFee();
    }

    public int getPrice() {
        return level.getPrice();
    }

    public Player getOwner() {
        return owner;
    }

    public void reset() {
        level = new Land(getPrice());
        owner = null;
    }

    public boolean hasOwner() {
        return owner != null;
    }

    public void setOwner(Player player) {
        owner = player;

        player.addProperty(this);
    }

    public String display() {
        return level.display();
    }

    public boolean matchPropertyType(String propertyType) {
        return this.level.getType().equals(propertyType);
    }

    public Command giveCommand(Player player) {
        // TODO: 感觉这里需要重构, 但是不知道怎么弄.
        super.giveCommand(player);

        String description;
        String commandSuffix;
        String answer;

        if (!hasOwner()) {
            description = "是否购买该处空地，" + getPrice() + " 元（Y/N）?";
            answer = prompt(description).toLowerCase();
            commandSuffix = Command.BUY_LAND_COMMAND_SUFFIX;
        } else if (getOwner() == player) {
            description = "是否升级该处地产，" + getPrice() + " 元（Y/N）?";
            answer = prompt(description).toLowerCase();
            commandSuffix = Command.UPGRADE_PROPERTY_COMMAND_SUFFIX;
        } else {
            answer = "toll";
            commandSuffix = "";
            if (player.hasGodOfLuck())
                System.out.println("福神附身，可免过路费");
        }

        Command command = Command.makeCommand(answer + commandSuffix, player);
        return command;
    }
}