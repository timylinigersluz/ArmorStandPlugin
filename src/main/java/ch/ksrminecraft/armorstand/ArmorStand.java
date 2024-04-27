package ch.ksrminecraft.armorstand;

import ch.ksrminecraft.armorstand.commands.ArmorStandCommand;
import ch.ksrminecraft.armorstand.listeners.ArmorStandInteractListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArmorStand extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("armorstand").setExecutor(new ArmorStandCommand(this));
        getServer().getPluginManager().registerEvents(new ArmorStandInteractListener(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
