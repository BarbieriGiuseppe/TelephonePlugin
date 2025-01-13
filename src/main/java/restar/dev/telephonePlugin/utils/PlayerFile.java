package restar.dev.telephonePlugin.utils;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PlayerFile {
    private String uuid;
    private String name;
    private int phoneNumber;
    private final static int PREFIX = 39;
    private final static int MINRND = 100000;
    private final static int MAXRND = 900000;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public void createPlayerFile(String uuid, String name) {
        // Create plugin folder if it does not exist
        File pluginFolder = new File("plugins/TelephonePlugin/PlayerData");
        if (!pluginFolder.exists()) {
            pluginFolder.mkdirs();
        }

        // create player yaml file
        File fileYml = new File(pluginFolder, uuid + ".yml");

        // check if file exists
        if (!fileYml.exists()) {
            // if not, create it with default values
            try {
                fileYml.createNewFile();
                DumperOptions options = new DumperOptions();
                options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
                options.setPrettyFlow(true);

                Yaml yaml = new Yaml(options);

                HashMap<String, Object> playerData = new HashMap<>();
                playerData.put("uuid", uuid);
                playerData.put("name", name);
                playerData.put("phone", generatePhoneNumber());

                // write the hashmap inside the yaml
                try (FileWriter writer = new FileWriter(fileYml)) {
                    yaml.dump(playerData, writer);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (FileInputStream input = new FileInputStream(fileYml)) {
                Yaml yaml = new Yaml();
                // load file from yaml in a hashmap
                @SuppressWarnings("unchecked")
                Map<String, Object> playerData = yaml.load(input);

                if (playerData != null) {
                    //call setters to properly set player values
                    setName((String) playerData.get("name"));
                    setUuid((String) playerData.get("uuid"));
                    setPhoneNumber((int) playerData.get("phone"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updatePlayerFile(String uuid) {
        // Create plugin folder if it does not exist
        File cartellaPlugin = new File("plugins/TelephonePlugin/PlayerData");
        if (!cartellaPlugin.exists()) {
            cartellaPlugin.mkdirs();
        }
        File fileYml = new File(cartellaPlugin, uuid + ".yml");

        try {
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            options.setPrettyFlow(true);

            Yaml yaml = new Yaml(options);

            HashMap<String, Object> playerData = new HashMap<>();
            playerData.put("uuid", uuid);
            playerData.put("name", name);
            playerData.put("phone", phoneNumber);

            // write the map inside the YAML file
            try (FileWriter writer = new FileWriter(fileYml)) {
                yaml.dump(playerData, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int generatePhoneNumber(){
        Random random = new Random();
        int number = MINRND + random.nextInt(MAXRND);

        return Integer.parseInt(""+ PREFIX + number);
    }

    public void setPlayerFile(Player player){
        String uuid = String.valueOf(player.getUniqueId());
        String playerName = player.getName();

        setName(playerName);
        setUuid(uuid);

        createPlayerFile(uuid,playerName);


    }
}