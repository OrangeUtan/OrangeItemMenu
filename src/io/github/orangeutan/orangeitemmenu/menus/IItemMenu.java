package io.github.orangeutan.orangeitemmenu.menus;

import io.github.orangeutan.orangeitemmenu.items.IMenuItem;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

/**
 * Inventory GUI Menu with Items as Buttons
 * Created by Michael on 26.01.2016.
 */
public interface IItemMenu {

    /**
     * Menu reacts to Player clicking a Slot in the Inventory
     * @param event The {@link InventoryClickEvent}
     */
    void onInventoryClick(InventoryClickEvent event);

    /**
     * Menu reacts to Inventory GUI being closed
     * @param event The {@link InventoryCloseEvent}
     */
    void onInventoryClose(InventoryCloseEvent event);

    /**
     * Open the Inventory GUI for a Player
     * @param player The {@link Player} for which to open the Inventory GUI Menu
     * @param previousMenu The previous {@link IItemMenu} opening this Inventory GUI Menu
     */
    void open(IItemMenu previousMenu);

    /**
     * Close this Inventory GUI Menu
     * @param player The {@link Player} for which to close the Inventory GUI Menu
     */
    void close(Player player);

    /**
     * Update the Inventory GUI Menu for a Player
     * @param player The {@link Player} for which to update the Inventory GUI Menu
     */
    void update(Player player);

    void clear(Player player);

    void navigateBack(Player player);

    void resume(Player player, IItemMenu oldMenu);

    void applyOnInventory(Inventory inventory, Player player);

    IItemMenu fillEmptySlots(DyeColor color);

    boolean addItem(IMenuItem newItem);

    boolean removeItem(int index);

    boolean removeItem(IMenuItem item);

    void replaceItem(int index, IMenuItem replaceWith);

    IItemMenu setItem(int index, IMenuItem item);

    IItemMenu setParent(IItemMenu menu);

    void setName(String name);

    void setSize(Size size);

    String getName();

    String getRawName();

    Size getSize();

    int getIndex(IMenuItem item);

    Plugin getPlugin();

    IItemMenu getParent();

    Player getPlayer();

    void setPlayer(Player player);

    enum Size {
        ONE_LINE(9),
        TWO_LINE(18),
        THREE_LINE(27),
        FOUR_LINE(36),
        FIVE_LINE(45),
        SIX_LINE(54);

        private final int slots;

        private Size(int size) {
            this.slots = size;
        }

        public int getSlots() {
            return slots;
        }

        public static Size fit(int slots) {
            if (slots < 10) {
                return ONE_LINE;
            } else if (slots < 19) {
                return TWO_LINE;
            } else if (slots < 28) {
                return THREE_LINE;
            } else if (slots < 37) {
                return FOUR_LINE;
            } else if (slots < 46) {
                return FIVE_LINE;
            } else {
                return SIX_LINE;
            }
        }
    }
}