package club.flower.moderation.utils.command;


import club.flower.moderation.Moderation;

public abstract class BaseCommand {

    public Moderation main = Moderation.getInstance();

    public BaseCommand() {
        main.getFramework().registerCommands(this);
    }

    public abstract void onCommand(CommandArgs command);

}
