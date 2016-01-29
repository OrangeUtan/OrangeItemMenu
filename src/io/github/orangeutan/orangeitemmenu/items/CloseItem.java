package io.github.orangeutan.orangeitemmenu.items;

import io.github.orangeutan.orangeitemmenu.events.ItemClickEvent;
import io.github.orangeutan.orangeitemmenu.menus.IItemMenu;
import io.github.orangeutan.orangeitemmenu.menus.StaticMenu;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Michael on 26.01.2016.
 */
public class CloseItem extends StaticItem {

    public CloseItem(IItemMenu menu, String displayName, ItemStack icon, String... lore) {
        super(menu, icon, displayName, lore);
    }

    @Override
    public void onItemClick(ItemClickEvent event) {
        super.onItemClick(event);

        mMenu.close();
    }
}