package restar.dev.telephonePlugin;

import org.bukkit.plugin.java.JavaPlugin;
import restar.dev.telephonePlugin.utils.PlayerFile;

public final class TelephonePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Telephone plugin Enabled");

        PlayerFile playerFile = new PlayerFile();


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
