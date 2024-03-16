package club.flower.moderation;

import club.flower.moderation.commands.FreezeCommand;
import club.flower.moderation.commands.StaffModeCommand;
import club.flower.moderation.commands.VanishCommand;
import club.flower.moderation.freeze.FreezeHandler;
import club.flower.moderation.listeners.FreezeChatListener;
import club.flower.moderation.listeners.FreezeListener;
import club.flower.moderation.listeners.StaffItemsListener;
import club.flower.moderation.listeners.StaffListener;
import club.flower.moderation.staff.StaffHandler;
import club.flower.moderation.utils.CC;
import club.flower.moderation.utils.command.CommandFramework;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Moderation extends JavaPlugin {

    @Getter public static Moderation instance;
    @Getter private CommandFramework framework;
    @Override
    public void onEnable() {
        instance = this;
        long start = System.currentTimeMillis();

        checkDescription();
        saveDefaultConfig();
        registerManagers();
        registerListeners();
        registerCommands();

        long end = System.currentTimeMillis();
        long timeTaken = end - start;

        CC.on(timeTaken);
    }

    private void checkDescription() {
        String author = getDescription().getAuthors().get(0);
        String expectedAuthor = "Emmy";

        Bukkit.getConsoleSender().sendMessage(CC.translate("&f[&bModeration&f] Expected author: &a" + expectedAuthor + "&f, Retrieved author: &c" + author));

        if (!author.equalsIgnoreCase(expectedAuthor)) {
            Bukkit.getConsoleSender().sendMessage(CC.translate("&f[&bModeration&f] &4&lAuthor mismatch! Shutting down the server."));
            System.exit(0);
            Bukkit.shutdown();
        } else {
            Bukkit.getConsoleSender().sendMessage(CC.translate("&f[&bModeration&f] &aNo changes detected!"));
        }
    }

    private void registerManagers() {
        framework = new CommandFramework(this);

        new StaffListener(this);
        new StaffItemsListener(this);
        new FreezeListener(this);
        new FreezeChatListener(this);
    }

    private void registerListeners() {
    }

    private void registerCommands() {
        new FreezeCommand();
        new StaffModeCommand();
        new VanishCommand();
    }

    @Override
    public void onDisable() {
        CC.off();
        StaffHandler.disable();
        FreezeHandler.disable();
    }

    public FileConfiguration getConfig(String fileName) {
        File configFile = new File(getDataFolder(), fileName);
        return YamlConfiguration.loadConfiguration(configFile);
    }
}
