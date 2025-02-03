package restar.dev.telephonePlugin.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import restar.dev.telephonePlugin.utils.PlayerInfo;
import restar.dev.telephonePlugin.utils.YamlFileUtils;

import java.util.Collections;
import java.util.Objects;

public class GetNumberCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            YamlFileUtils yamlFileUtils = new YamlFileUtils();
            PlayerInfo playerInfo = new PlayerInfo();
            yamlFileUtils.loadFile(player);

            String phoneNumber = yamlFileUtils.get().getString("phoneNumber");
            if (Objects.equals(phoneNumber, "")) {
                phoneNumber = playerInfo.generatePhoneNumber();
                //here save the phone number inside his yml file
                yamlFileUtils.get().set("PhoneNumber", phoneNumber);
                yamlFileUtils.saveFile();

            }

            ItemStack paper = new ItemStack(Material.PAPER, 1);
            ItemMeta meta = paper.getItemMeta();

            if (meta != null) {
                meta.displayName(Component.text(player.getName() + " Phone Number"));
                meta.lore(Collections.singletonList(Component.text("Number: " + phoneNumber)));

                paper.setItemMeta(meta);
            }

            player.getInventory().addItem(paper);

            player.sendMessage("Your phone number is: " + phoneNumber);

            return true;
        }
        return false;
    }
}