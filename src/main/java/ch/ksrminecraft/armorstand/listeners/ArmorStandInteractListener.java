package ch.ksrminecraft.armorstand.listeners;

import io.papermc.paper.event.player.AsyncChatCommandDecorateEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.plugin.Plugin;

public class ArmorStandInteractListener implements Listener {

    private Plugin plugin;

    public ArmorStandInteractListener(Plugin plugin) {
        this.plugin = plugin;
    }

    // OnPlayerAct-Methode
    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {

        if (!(event.getRightClicked() instanceof ArmorStand)) {

            return;
        }

        ArmorStand stand = (ArmorStand) event.getRightClicked();

        // Handelt es sich um den SpecialArmorStand? Falls ja, Creepersound -> Explosion -> Nachricht
        if (stand.hasMetadata("SpecialArmorStand")) {
            Player player = event.getPlayer();

            player.playSound(player.getLocation(), Sound.ENTITY_CREEPER_PRIMED, 1.0f, 1.0f);
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
            }, 20L);
            player.sendMessage(Component.text(ChatColor.DARK_RED + "Das würde ich lieber lassen!"));
        }

    }



}
