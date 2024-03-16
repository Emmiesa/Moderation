package club.flower.moderation.commands;

import club.flower.moderation.staff.Staff;
import club.flower.moderation.utils.CC;
import club.flower.moderation.utils.command.BaseCommand;
import club.flower.moderation.utils.command.Command;
import club.flower.moderation.utils.command.CommandArgs;
import org.bukkit.entity.Player;

/**
 * Created by Risas
 * Project: StaffMode
 * Date: 23-11-2021 - 22:58
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */
public class VanishCommand extends BaseCommand {

    @Command(name = "vanish", aliases = {"v"}, permission = "moderation.command.vanish")
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        Staff staff = Staff.getStaff(player.getUniqueId());

        if (staff == null) {
            player.sendMessage(CC.translate("&cYou are not a staff member!"));
            return;
        }

        if (staff.isVanished()) {
            staff.disableVanish(true);
        }
        else {
            staff.enableVanish(true);
        }
    }
}
