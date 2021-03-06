package tw.rich.site.property;

import tw.rich.Color;
import tw.rich.Player;
import tw.rich.command.Command;
import tw.rich.site.Site;

public class Property extends Site {
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

    public boolean matchPropertyType(String propertyType) {
        return this.level.getType().equals(propertyType);
    }

    public Command giveCommand(Player player) {
        Command command = super.giveCommand(player);
        if(!player.isActive()) return command;

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

        return Command.makeCommand(answer + commandSuffix, player);
    }

    public String display() {
        if (hasPlayer()) return players.get(players.size() - 1).display();
        if (hasBlockTool()) return blockTool.display();
        if (hasBombTool()) return bombTool.display();

        String color;
        if (hasOwner()) color = owner.getColor();
        else color = Color.ANSI_BLACK;
        return Color.paint(color, type);
    }
}