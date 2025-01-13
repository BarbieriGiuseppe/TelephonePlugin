package restar.dev.telephonePlugin.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import restar.dev.telephonePlugin.utils.PlayerFile;

import java.util.Collections;

public class GetNumber implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            PlayerFile playerFile = new PlayerFile();
            playerFile.setPlayerFile(player);

            int phoneNumber = playerFile.getPhoneNumber();

            ItemStack paper = new ItemStack(Material.PAPER, 1);
            ItemMeta meta = paper.getItemMeta();

            // Imposta il nome e la descrizione dell'oggetto
            if (meta != null) {
                meta.displayName(Component.text(player.getName() + "Phone Number"));
                meta.lore(Collections.singletonList(Component.text(phoneNumber)));

                paper.setItemMeta(meta);
            }

            // Aggiungi l'oggetto all'inventario del giocatore
            player.getInventory().addItem(paper);

            return true;
        }
        return false;
    }
}