package io.github.orangeutan.orangeitemmenu.events;

import io.github.orangeutan.orangeitemmenu.menus.IItemMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Michael on 26.01.2016.
 */
public class ItemClickEvent {

    private Player player;
    private IItemMenu menu;
    private ClickType clickType;
    private InventoryAction action;
    private ItemStack item;
    private ItemStack cursorItem;
    private ItemStack currentItemInSlot;
    private boolean cancelled;

    public ItemClickEvent(Player player, IItemMenu menu, ItemStack item, ItemStack cursorItem, ItemStack currentItemInSlot, ClickType clickType, InventoryAction action) {
        this.player = player;
        this.menu = menu;
        this.item = item;
        this.clickType = clickType;
        this.action = action;
        this.cursorItem = cursorItem;
        this.currentItemInSlot = currentItemInSlot;
    }

    public boolean isRightClick() {
        return this.clickType.isRightClick();
    }

    public boolean isLeftClick() {
        return this.clickType.isLeftClick();
    }

    public boolean isShiftClick() {
        return this.clickType.isShiftClick();
    }

    public Player getPlayer() {
        return player;
    }

    public IItemMenu getMenu() {
        return menu;
    }

    public ItemStack getItem() {
        return item;
    }

    public ClickType getClickType() {
        return clickType;
    }

    public InventoryAction getAction() {
        return action;
    }

    public ItemStack getCursorItem() {
        return cursorItem;
    }

    public ItemStack getCurrentItemInSlot() {
        return currentItemInSlot;
    }

    public boolean isCancelled() { return cancelled; }

    public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }
}
