package restar.dev.telephonePlugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import restar.dev.telephonePlugin.commands.CallCommand;
import restar.dev.telephonePlugin.commands.CallResponseCommand;
import restar.dev.telephonePlugin.commands.ChangeNumberCommand;
import restar.dev.telephonePlugin.commands.GetNumberCommand;
import restar.dev.telephonePlugin.listeners.PlayerListeners;
import restar.dev.telephonePlugin.utils.PlayerInfo;

import java.util.Objects;

public final class TelephonePlugin extends JavaPlugin {
    PlayerInfo playerInfo = new PlayerInfo();
    PlayerListeners playerListeners = new PlayerListeners();
    CallCommand callCommand = new CallCommand();

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage("Telephone plugin Enabled");
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(this.playerListeners, this);

        Objects.requireNonNull(this.getCommand("changenumber")).setExecutor(new ChangeNumberCommand());
        Objects.requireNonNull(this.getCommand("getnumber")).setExecutor(new GetNumberCommand());
        Objects.requireNonNull(this.getCommand("call")).setExecutor(new CallCommand());
        Objects.requireNonNull(this.getCommand("callaccept")).setExecutor(new CallResponseCommand(callCommand, true));
        Objects.requireNonNull(this.getCommand("calldeny")).setExecutor(new CallResponseCommand(callCommand, false));

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("Telephone plugin Disabled");
    }
}
