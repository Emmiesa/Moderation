package club.flower.moderation.staff;

import club.flower.moderation.Moderation;
import club.flower.moderation.utils.CC;
import club.flower.moderation.utils.PlayerUtil;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Risas
 * Project: StaffMode
 * Date: 23-11-2021 - 0:17
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */

@Getter @Setter
public class Staff {

    @Getter
    private static Map<UUID, Staff> staffs = Maps.newHashMap();

    private UUID uuid;
    private boolean vanished;
    private boolean staffMode;
    private ItemStack[] armorContents;
    private ItemStack[] contents;

    private ItemStack[] armorStaff = new ItemStack[] {
            new ItemStack(Material.LEATHER_BOOTS, 1),
            new ItemStack(Material.LEATHER_LEGGINGS, 1),
            new ItemStack(Material.LEATHER_CHESTPLATE, 1),
            new ItemStack(Material.LEATHER_HELMET, 1)
    };

    public Staff(UUID uuid) {
        this.uuid = uuid;
        staffs.put(uuid, this);
    }

    public void enableStaffMode(boolean message) {
        setStaffMode(true);
        enableVanish(false);

        Player player = getPlayer();
        player.setGameMode(GameMode.CREATIVE);

        setArmorContents(player.getInventory().getArmorContents());
        setContents(player.getInventory().getContents());

        PlayerUtil.clear(player, true, true);
        StaffItems.giveItems(this);

        if (message) {
            player.sendMessage(CC.translate(Moderation.getInstance().getConfig().getString("staff-mode.enabled")));
        }
    }

    public void disableStaffMode(boolean message) {
        setStaffMode(false);
        disableVanish(false);

        Player player = getPlayer();
        player.setGameMode(GameMode.SURVIVAL);

        PlayerUtil.clear(player, true, true);

        player.getInventory().setArmorContents(getArmorContents());
        player.getInventory().setContents(getContents());

        if (message) {
            player.sendMessage(CC.translate(Moderation.getInstance().getConfig().getString("staff-mode.disabled")));
        }
    }

    public void enableVanish(boolean message) {
        setVanished(true);

        Player player = getPlayer();

        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            if (Staff.getStaff(online.getUniqueId()) != null) {
                online.showPlayer(player);
            }
            else {
                online.hidePlayer(player);
            }
        }

        if (message) {
            player.sendMessage(CC.translate("&6Vanish &aEnabled"));
        }
    }

    public void disableVanish(boolean message) {
        setVanished(false);

        Player player = getPlayer();

        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            online.showPlayer(player);
        }

        if (message) {
            player.sendMessage(CC.translate("&6Vanish &cDisabled"));
        }
    }

    public ItemStack[] getArmorStaff() {
        ItemStack[] armor = this.armorStaff.clone();

        for (ItemStack pieces : armorStaff) {
            LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) pieces.getItemMeta();
            leatherArmorMeta.setDisplayName(CC.translate("&9Staff Armor"));
            leatherArmorMeta.setColor(Color.BLUE);
            pieces.setItemMeta(leatherArmorMeta);
        }

        return armor;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public String getName() {
        return getPlayer().getName();
    }

    public static Staff getStaff(UUID uuid) {
        return staffs.get(uuid);
    }
}