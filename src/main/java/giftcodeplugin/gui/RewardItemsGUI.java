package giftcodeplugin.gui;

import giftcodeplugin.builder.GiftCodeBuilder;
import giftcodeplugin.holder.RewardItemsHolder;
import net.kyori.adventure.text.Component;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RewardItemsGUI {

    public void open(Player player, GiftCodeBuilder builder) {
        Inventory inv = Bukkit.createInventory(new RewardItemsHolder() ,27, Component.text("Reward Items"));
        int slot = 0;

        for (ItemStack item : builder.getItems()) {
            if (slot >= 27) break;
            inv.setItem(slot++, item.clone());
        }

        player.openInventory(inv);
    }
}