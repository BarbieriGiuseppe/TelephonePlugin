package restar.dev.telephonePlugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import restar.dev.telephonePlugin.TelephonePlugin;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class YamlFileUtils {

    private String phoneNumber;
    private OfflinePlayer player;
    PlayerInfo playerInfo;
    private File dataFolder;
    private File configFile;
    private FileConfiguration config;
    private HashMap<UUID, YamlConfiguration> usersFiles;

    public YamlFileUtils() {
        this.playerInfo = new PlayerInfo();
        this.usersFiles = new HashMap();
    }

    public void createFile(OfflinePlayer p) {
        this.dataFolder = new TelephonePlugin().getDataFolder();
        this.configFile = new File(this.dataFolder, p.getUniqueId() + ".yml");
        if (!this.dataFolder.exists()) {
            this.dataFolder.mkdir();
        }

        if (!this.configFile.exists()) {
            try {
                this.configFile.createNewFile();
            } catch (Exception var3) {
                Bukkit.getConsoleSender()
                        .sendMessage(ChatColor.RED + "Error creating " + this.configFile.getName() + "!");
            }
        }

        this.config = YamlConfiguration.loadConfiguration(this.configFile);
    }

    public File getfile() {
        return this.configFile;
    }

    public void loadFile(OfflinePlayer p) {
        this.configFile = new File(this.dataFolder, p.getUniqueId() + ".yml");
        this.config = YamlConfiguration.loadConfiguration(this.configFile);
    }

    public void saveFile() {
        try {
            this.config.save(this.configFile);
        } catch (Exception var2) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error saving " + this.configFile.getName() + "!");
        }

    }

    public FileConfiguration get() {
        return this.config;
    }

}
