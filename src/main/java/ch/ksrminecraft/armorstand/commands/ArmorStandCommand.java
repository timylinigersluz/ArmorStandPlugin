package ch.ksrminecraft.armorstand.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;
import org.bukkit.plugin.Plugin;

public class ArmorStandCommand implements CommandExecutor {

    private Plugin plugin;

    public ArmorStandCommand(Plugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!(commandSender instanceof Player)) {
            return true;
        }

        Player player = (Player) commandSender;

        // Armor Stand an der Position des Spielers erzeugen
        ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);

        // Das EntityEquipment des Armor Stands holen
        EntityEquipment equipment = stand.getEquipment();

        if (equipment == null) {
            return true;
        }

        // Den Armor Stand ausrüsten und Eigenschaften geben
        equipment.setHelmet(new ItemStack(Material.BEACON));
        equipment.setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
        equipment.setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
        equipment.setBoots(new ItemStack(Material.NETHERITE_BOOTS));

        ItemStack enchantedAxe = new ItemStack(Material.NETHERITE_AXE);
        enchantedAxe.addEnchantment(Enchantment.DAMAGE_ALL, 5);
        equipment.setItemInMainHand(enchantedAxe);

        stand.setRightArmPose(new EulerAngle(-Math.toRadians(90), 0, Math.toRadians(45)));

        stand.setGlowing(true);
        stand.setBasePlate(false);
        stand.setArms(true);
        stand.setBodyPose(new EulerAngle(Math.toRadians(0), Math.toRadians(45), Math.toRadians(0))); // Körperhaltung
        stand.setHeadPose(new EulerAngle(0, 45, 0)); // Kopfhaltung
        stand.setMetadata("SpecialArmorStand", new FixedMetadataValue(plugin, true));


        return true;
    }
}
