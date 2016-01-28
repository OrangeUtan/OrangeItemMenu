package io.github.orangeutan.orangeitemmenu.items;

import io.github.orangeutan.orangeitemmenu.events.ItemClickEvent;
import io.github.orangeutan.orangeitemmenu.menus.IItemMenu;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Created by Michael on 26.01.2016.
 */
public class EmptySlotItem extends StaticItem {

    public EmptySlotItem(IItemMenu menu, DyeColor color) {
        super(menu, new ItemStack(Material.STAINED_GLASS_PANE, 1, color.getData()), " ");
    }

    @Override
    public boolean canBeReplaced() {
        return true;
    }
}