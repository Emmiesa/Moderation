package club.flower.moderation.commands;

import club.flower.moderation.freeze.Freeze;
import club.flower.moderation.staff.Staff;
import club.flower.moderation.utils.CC;
import club.flower.moderation.utils.command.BaseCommand;
import club.flower.moderation.utils.command.Command;
import club.flower.moderation.utils.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Risas
 * Project: StaffMode
 * Date: 23-11-2021 - 23:01
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */
public class FreezeCommand extends BaseCommand {

    @Command(name = "freeze", aliases = {"ss"}, permission = "moderation.command.freeze")
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length == 0) {
            player.sendMessage(CC.translate("&cUsage: /" + command.getLabel() + " <player>"));
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(CC.translate("&cPlayer " + args[0] + " not found!"));
            return;
        }

        Staff staff = Staff.getStaff(player.getUniqueId());

        if (staff == null) {
            player.sendMessage(CC.translate("&cYou are not a staff member!"));
            return;
        }

        Freeze freeze;

        if (Freeze.getFreezes().get(target.getUniqueId()) == null) {
            freeze = new Freeze(target.getUniqueId());
        }
        else {
            freeze = Freeze.getFreezes().get(target.getUniqueId());
        }

        freeze.setStaff(staff);

        if (freeze.isFrozen()) {
            freeze.unFreezePlayer(true);
        }
        else {
            freeze.freezePlayer(true);
        }
    }
}
