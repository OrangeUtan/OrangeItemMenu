package io.github.orangeutan.orangeitemmenu.items;

import io.github.orangeutan.orangeitemmenu.events.ItemClickEvent;
import io.github.orangeutan.orangeitemmenu.menus.IItemMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.UUID;

/**
 * Created by Michael on 26.01.2016.
 */
public class NavigateItem extends StaticItem {

    protected IItemMenu mDestination;

    public NavigateItem(IItemMenu menu, String displayName, IItemMenu destination, String... lore) {
        super(menu, null, displayName, Arrays.asList(lore));
        this.mDestination = destination;

        if (destination == null) {
            setIcon(getLeftArrow());
        } else {
            setIcon(getRightArrow());
        }
    }

    public NavigateItem(IItemMenu menu, String displayName, ItemStack icon, IItemMenu destination, String... lore) {
        super(menu, icon, displayName, Arrays.asList(lore));
        this.mDestination = destination;
    }

    @Override
    public void onItemClick(ItemClickEvent event) {
        super.onItemClick(event);

        if (mDestination == null) {
            getMenu().navigateBack(event.getPlayer());
        } else {
            mDestination.open(event.getPlayer(), mDestination);
        }
    }

    private static ItemStack getLeftArrow() {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner("MHF_ArrowLeft");
        skull.setItemMeta(meta);

        return skull;
    }

    private static ItemStack getRightArrow() {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(Bukkit.getOfflinePlayer(UUID.fromString("MHF_ArrowRight")).getName());
        skull.setItemMeta(meta);

        return skull;
    }

    public void setDestination(IItemMenu destination) {
        mDestination = destination;
    }
}
