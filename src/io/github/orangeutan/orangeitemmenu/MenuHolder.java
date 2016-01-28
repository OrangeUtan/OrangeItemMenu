package io.github.orangeutan.orangeitemmenu;

import io.github.orangeutan.orangeitemmenu.menus.IItemMenu;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * Created by Michael on 26.01.2016.
 */
public class MenuHolder implements InventoryHolder {

    private IItemMenu menu;
    private Inventory inventory;

    public MenuHolder(IItemMenu menu, Inventory inventory) {
        this.menu = menu;
        this.inventory = inventory;
    }

    public IItemMenu getMenu() {
        return menu;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
