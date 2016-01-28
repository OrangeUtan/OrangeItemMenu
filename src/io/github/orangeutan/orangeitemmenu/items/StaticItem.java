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
 * Created by Michael on 27.01.2016.
 */
public abstract class StaticItem implements IMenuItem {

    protected IItemMenu mMenu;
    protected ItemStack mIcon;
    protected String mDisplayName;
    protected List<String> mLore;

    public StaticItem(IItemMenu menu, ItemStack icon, String displayName, List<String> lore) {
        mMenu = menu;
        mIcon = icon;
        mDisplayName = displayName;
        mLore = lore;
    }

    @Override
    public void onItemClick(ItemClickEvent event) {
        event.setCancelled(true);
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
        return mDisplayName;
    }

    @Override
    public String getRawDisplayName() {
        if (mDisplayName != null) return ChatColor.stripColor(mDisplayName);
        return null;
    }

    @Override
    public ItemStack getIcon(Player player) {
        ItemMeta meta = mIcon.getItemMeta();
        if (mDisplayName != null) {
            meta.setDisplayName(mDisplayName);
        }
        if (mLore != null) meta.setLore(mLore);
        mIcon.setItemMeta(meta);
        return mIcon;
    }

    @Override
    public List<String> getLore() {
        return mLore;
    }

    @Override
    public IItemMenu getMenu() {
        return mMenu;
    }

    @Override
    public void setIcon(ItemStack icon) {
        mIcon = icon;
    }

    @Override
    public void setDisplayName(String name) {
        mDisplayName = name;
    }

    @Override
    public void setLore(String... lore) {
        mLore = Arrays.asList(lore);
    }
}
