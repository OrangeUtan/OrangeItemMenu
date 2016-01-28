package io.github.orangeutan.orangeitemmenu.items;

import io.github.orangeutan.orangeitemmenu.events.ItemClickEvent;
import io.github.orangeutan.orangeitemmenu.menus.IItemMenu;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Michael on 26.01.2016.
 */
public class PlainItem implements IMenuItem {

    private IItemMenu menu;
    private String displayName;
    private ItemStack icon;
    private List<String> lore;

    public PlainItem(IItemMenu menu, String displayName, ItemStack icon, String... lore) {
        this.menu = menu;
        this.displayName = displayName;
        this.icon = icon;
        this.lore = Arrays.asList(lore);
    }

    public PlainItem(IItemMenu menu, ItemStack icon, String... lore) {
        this.menu = menu;
        displayName = "";
        this.icon = icon;
        this.lore = Arrays.asList(lore);
    }

    @Override
    public void onItemClick(ItemClickEvent event) {
    }

    @Override
    public boolean canPickUp(Player player) {
        return false;
    }

    @Override
    public boolean canBeReplaced() {
        return false;
    }

    @Override
    public boolean canIconBeReplaced() {
        return false;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String getRawDisplayName() {
        return ChatColor.stripColor(displayName);
    }

    @Override
    public ItemStack getIcon(Player player) {
        ItemMeta meta = icon.getItemMeta();
        if (!displayName.equals("")) meta.setDisplayName(displayName);
        meta.setLore(lore);
        icon.setItemMeta(meta);

        return icon;
    }

    @Override
    public List<String> getLore() {
        return lore;
    }

    @Override
    public IItemMenu getMenu() {
        return menu;
    }

    @Override
    public void setIcon(ItemStack icon) {
        this.icon = icon;
    }

    @Override
    public void setDisplayName(String name) {
        displayName = name;
    }

    @Override
    public void setLore(String... lore) {
        this.lore = Arrays.asList(lore);
    }
}
