package club.flower.moderation.staff;

import club.flower.moderation.Moderation;
import club.flower.moderation.utils.CC;
import club.flower.moderation.utils.ItemBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
@AllArgsConstructor
public enum StaffItems {

    COMPASS(Moderation.getInstance().getConfig().getString("staff-mode.items.compass.displayname"),
            new ItemStack(Material.matchMaterial(Moderation.getInstance().getConfig().getString("staff-mode.items.compass.material"))),
            Moderation.getInstance().getConfig().getInt("staff-mode.items.compass.data"),
            Moderation.getInstance().getConfig().getInt("staff-mode.items.compass.slot")),

    INSPECTOR(Moderation.getInstance().getConfig().getString("staff-mode.items.inspector.displayname"),
            new ItemStack(Material.matchMaterial(Moderation.getInstance().getConfig().getString("staff-mode.items.inspector.material"))),
            Moderation.getInstance().getConfig().getInt("staff-mode.items.inspector.data"),
            Moderation.getInstance().getConfig().getInt("staff-mode.items.inspector.slot")),

    WORLD_EDIT(Moderation.getInstance().getConfig().getString("staff-mode.items.world-edit.displayname"),
            new ItemStack(Material.matchMaterial(Moderation.getInstance().getConfig().getString("staff-mode.items.world-edit.material"))),
            Moderation.getInstance().getConfig().getInt("staff-mode.items.world-edit.data"),
            Moderation.getInstance().getConfig().getInt("staff-mode.items.world-edit.slot")),

    FREEZE(Moderation.getInstance().getConfig().getString("staff-mode.items.freeze.displayname"),
            new ItemStack(Material.matchMaterial(Moderation.getInstance().getConfig().getString("staff-mode.items.freeze.material"))),
            Moderation.getInstance().getConfig().getInt("staff-mode.items.freeze.data"),
            Moderation.getInstance().getConfig().getInt("staff-mode.items.freeze.slot")),

    RANDOM_TELEPORT(Moderation.getInstance().getConfig().getString("staff-mode.items.random-teleport.displayname"),
            new ItemStack(Material.matchMaterial(Moderation.getInstance().getConfig().getString("staff-mode.items.random-teleport.material"))),
            Moderation.getInstance().getConfig().getInt("staff-mode.items.random-teleport.data"),
            Moderation.getInstance().getConfig().getInt("staff-mode.items.random-teleport.slot")),

    VANISH_OFF(Moderation.getInstance().getConfig().getString("staff-mode.items.vanish-off.displayname"),
            new ItemStack(Material.matchMaterial(Moderation.getInstance().getConfig().getString("staff-mode.items.vanish-off.material"))),
            Moderation.getInstance().getConfig().getInt("staff-mode.items.vanish-off.data"),
            Moderation.getInstance().getConfig().getInt("staff-mode.items.vanish-off.slot")),

    VANISH_ON(Moderation.getInstance().getConfig().getString("staff-mode.items.vanish-on.displayname"),
            new ItemStack(Material.matchMaterial(Moderation.getInstance().getConfig().getString("staff-mode.items.vanish-on.material"))),
            Moderation.getInstance().getConfig().getInt("staff-mode.items.vanish-on.data"),
            Moderation.getInstance().getConfig().getInt("staff-mode.items.vanish-on.slot"));
    //WORLD_EDIT("&6World Edit", new ItemStack(Material.WOOD_AXE),0,2),
    //FREEZE("&6Freeze", new ItemStack(Material.PACKED_ICE), 0,4),
    //RANDOM_TELEPORT("&6Random Teleport", new ItemStack(Material.WATCH), 0,7),
    //VANISH_OFF("&6Vanish&7: &c&lOFF", new ItemStack(Material.INK_SACK), 8,8),
    //VANISH_ON("&6Vanish&7: &a&lON", new ItemStack(Material.INK_SACK), 10,8);

    private final String displayName;
    private final ItemStack itemStack;
    private final int data;
    private final int slot;

    public ItemStack getItem() {
        return new ItemBuilder(this.itemStack)
                .name(CC.translate(this.displayName))
                .data(this.data)
                .build();
    }

    public boolean canUse(ItemStack itemStack) {
        return itemStack.isSimilar(getItem());
    }

    public static void giveItems(Staff staff) {
        for (StaffItems item : StaffItems.values()) {
            staff.getPlayer().getInventory().setItem(item.getSlot(), item.getItem());
        }

        staff.getPlayer().getInventory().setArmorContents(staff.getArmorStaff());
        staff.getPlayer().updateInventory();
    }
}
