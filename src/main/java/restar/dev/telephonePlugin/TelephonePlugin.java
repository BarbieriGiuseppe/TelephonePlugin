package restar.dev.telephonePlugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import restar.dev.telephonePlugin.commands.ChangeNumber;
import restar.dev.telephonePlugin.commands.GetNumber;
import restar.dev.telephonePlugin.listeners.PlayerListeners;
import restar.dev.telephonePlugin.utils.PlayerFile;

import java.util.logging.Logger;

public final class TelephonePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage("Telephone plugin Enabled");

        PlayerFile playerFile = new PlayerFile();
        PlayerListeners playerListeners = new PlayerListeners(playerFile);

        Bukkit.getPluginManager().registerEvents(playerListeners, this);

        this.getCommand("changenumber").setExecutor(new ChangeNumber());
        this.getCommand("getnumber").setExecutor(new GetNumber());

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("Telephone plugin Disabled");
    }
}
