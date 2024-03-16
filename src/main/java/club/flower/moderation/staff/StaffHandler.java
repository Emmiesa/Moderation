package club.flower.moderation.staff;

import club.flower.moderation.utils.CC;
import org.bukkit.Sound;

/**
 * Created by Risas
 * Project: StaffMode
 * Date: 23-11-2021 - 15:49
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */
public class StaffHandler {

    public static void sendMessageAllStaff(String message, boolean sound) {
        for (Staff staff : Staff.getStaffs().values()) {
            if (sound) staff.getPlayer().playSound(staff.getPlayer().getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
            staff.getPlayer().sendMessage(CC.translate(message));
        }
    }

    public static void disable() {
        for (Staff staff : Staff.getStaffs().values()) {
            staff.disableStaffMode(false);
        }
    }
}
