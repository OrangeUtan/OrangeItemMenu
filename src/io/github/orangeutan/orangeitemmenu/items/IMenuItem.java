package io.github.orangeutan.orangeitemmenu.items;

import io.github.orangeutan.orangeitemmenu.events.ItemClickEvent;
import io.github.orangeutan.orangeitemmenu.menus.IItemMenu;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by Michael on 26.01.2016.
 */
public interface IMenuItem {

    void onItemClick(ItemClickEvent event);

    boolean canPickUp(Player player);

    boolean canBeReplaced();

    boolean canIconBeReplaced();

    /**
     * Get the Display Name of the ItemStack
     * @return
     */
    String getDisplayName();

    /**
     * Get the Display Name of the ItemStack without Colours
     * @return
     */
    String getRawDisplayName();

    ItemStack getIcon(Player player);

    List<String> getLore();

    IItemMenu getMenu();

    void setIcon(ItemStack icon);

    void setDisplayName(String name);

    void setLore(String... lore);
}
