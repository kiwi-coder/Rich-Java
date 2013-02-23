package tw.rich.command;

import tw.rich.Player;

public class ChooseGiftCommand extends Command {
    private int giftCode;

    public ChooseGiftCommand(Player player, int giftCode) {
        super();
        this.player = player;
        this.giftCode = giftCode;
    }

    @Override
    public void execute() {
        player.chooseGift(giftCode);
        player.becomeInactive();
    }
}
