package io.github.orangeutan.orangeitemmenu.items;

import io.github.orangeutan.orangeitemmenu.events.ItemClickEvent;
import io.github.orangeutan.orangeitemmenu.menus.IItemMenu;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by Michael on 26.01.2016.
 */
public class ConfigurableItem implements IMenuItem {

    private IItemMenu menu;
    private ItemStack icon;
    private DyeColor emptyColor = DyeColor.GRAY;
    private boolean isEmpty = true;

    public ConfigurableItem(IItemMenu menu, ItemStack icon) {
        this.menu = menu;
        this.icon = icon;
        isEmpty = false;
    }

    public ConfigurableItem(IItemMenu menu) {
        this.menu = menu;
        this.icon = new ItemStack(Material.STAINED_GLASS_PANE, 1, emptyColor.getData());
    }

    @Override
    public void onItemClick(ItemClickEvent event) {
        if (isEmpty) return;

        switch (event.getClickType()) {
            case LEFT:
                icon.setAmount(icon.getAmount() + 1);
                break;
            case RIGHT:
                if (icon.getAmount() > 1) icon.setAmount(icon.getAmount() - 1);
                break;
            case MIDDLE:
                icon = new ItemStack(Material.STAINED_GLASS_PANE, 1, emptyColor.getData());
                isEmpty = true;
                break;
        }
        menu.update(event.getPlayer());
    }

    @Override
    public boolean canPickUp() {
        return false;
    }

    @Override
    public boolean canBeReplaced() {
        return false;
    }

    @Override
    public boolean canIconBeReplaced() {
        return true;
    }

    @Override
    public String getDisplayName() {
        return icon.getItemMeta().getDisplayName();
    }

    @Override
    public String getRawDisplayName() {
        return ChatColor.stripColor(icon.getItemMeta().getDisplayName());
    }

    @Override
    public ItemStack getIcon() {
        return icon;
    }

    @Override
    public List<String> getLore() {
        return null;
    }

    @Override
    public IItemMenu getMenu() {
        return menu;
    }

    @Override
    public void setIcon(ItemStack icon) {
        this.icon = icon;
        isEmpty = false;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    @Override
    public void setDisplayName(String name) {
    }

    @Override
    public void setLore(String... lore) {
    }
}
