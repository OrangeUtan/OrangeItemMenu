package io.github.orangeutan.orangeitemmenu.menus;

import io.github.orangeutan.orangeitemmenu.MenuHolder;
import io.github.orangeutan.orangeitemmenu.events.ItemClickEvent;
import io.github.orangeutan.orangeitemmenu.items.EmptySlotItem;
import io.github.orangeutan.orangeitemmenu.items.IMenuItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * {@link IItemMenu} that doesn't change for any Player ({@link IMenuItem}s may still change their Icons)
 * Created by Michael on 27.01.2016.
 */
public abstract class StaticMenu implements IItemMenu {

    protected Plugin mPlugin;
    protected Size mSize;
    protected String mName;
    protected ArrayList<IMenuItem> mItems;
    protected IItemMenu mParentMenu;

    public StaticMenu(Plugin plugin, Size size, String name, IMenuItem[] items, IItemMenu parentMenu) {
        mPlugin = plugin;
        mSize = size;
        mName = name;
        mItems = new ArrayList<>(size.getSlots());
        mParentMenu = parentMenu;

        mItems.addAll(Arrays.asList(items));
        fillEmptySlots(DyeColor.GRAY);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        int slot = event.getRawSlot();
        if (slot >= 0 && slot < mSize.getSlots() && mItems.get(slot) != null) {
            Player player = (Player) event.getWhoClicked();
            ItemClickEvent itemClickEvent = new ItemClickEvent(player, this, event.getCurrentItem(), event.getCursor(), event.getCurrentItem(), event.getClick(), event.getAction());
            mItems.get(slot).onItemClick(itemClickEvent);

            if (itemClickEvent.isCancelled()) event.setCancelled(true);
        }
    }

    @Override
    public void onInventoryClose(InventoryCloseEvent event) {
    }

    @Override
    public void open(Player player, IItemMenu previousMenu) {
        Inventory inventory = Bukkit.createInventory(new MenuHolder(this, Bukkit.createInventory(player, mSize.getSlots())), mSize.getSlots(), mName);
        applyOnInventory(inventory, player);

        player.openInventory(inventory);
    }

    @Override
    public void close(Player player) {
        if (player.getOpenInventory() != null) {
            if (player.getOpenInventory().getTopInventory().getHolder() instanceof MenuHolder) {
                final Player p = player;
                Bukkit.getScheduler().scheduleSyncDelayedTask(mPlugin, new Runnable() {
                    public void run() {
                        p.closeInventory();
                    }
                }, 1);
            }
        }
    }

    @Override
    public void update(Player player) {
        if (player.getOpenInventory() != null) {
            Inventory inventory = player.getOpenInventory().getTopInventory();
            if (inventory.getHolder() instanceof MenuHolder && ((MenuHolder) inventory.getHolder()).getMenu().equals(this)) {
                inventory.clear();
                applyOnInventory(inventory, player);
                player.updateInventory();
            }
        }
    }

    @Override
    public void clear(Player player) {
    }

    @Override
    public void navigateBack(Player player) {
        if (mParentMenu != null) {
            final Player p = player;
            Bukkit.getScheduler().scheduleSyncDelayedTask(mPlugin, new Runnable() {
                @Override
                public void run() {
                    if (p != null) mParentMenu.resume(p, StaticMenu.this);
                }
            }, 3);
        }
    }

    @Override
    public void resume(Player player, IItemMenu oldMenu) {
        mParentMenu = oldMenu;
        open(player, oldMenu);
    }

    @Override
    public void applyOnInventory(Inventory inventory, Player player) {
        for (int i = 0; i < mItems.size(); i++) {
            if (mItems.get(i) != null && inventory.getItem(i) == null) {
                inventory.setItem(i, mItems.get(i).getIcon(player));
            }
        }
    }

    @Override
    public IItemMenu fillEmptySlots(DyeColor color) {
        while (mItems.size() < mSize.getSlots()) {
            mItems.add(new EmptySlotItem(this, color));
        }

        for (int i = 0; i < mItems.size(); i++) {
            if (mItems.get(i) == null) {
                mItems.set(i, new EmptySlotItem(this, color));
            }
        }
        return this;
    }

    @Override
    public boolean addItem(IMenuItem newItem) {
        return false;
    }

    @Override
    public boolean removeItem(int index) {
        return false;
    }

    @Override
    public boolean removeItem(IMenuItem item) {
        return false;
    }

    @Override
    public void replaceItem(int index, IMenuItem replaceWith) {
    }

    @Override
    public IItemMenu setItem(int index, IMenuItem item) {
        return null;
    }

    @Override
    public IItemMenu setParent(IItemMenu menu) {
        mParentMenu = menu;
        return this;
    }

    @Override
    public void setName(String name) {
    }

    @Override
    public void setSize(Size size) {
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public String getRawName() {
        return ChatColor.stripColor(mName);
    }

    @Override
    public Size getSize() {
        return mSize;
    }

    @Override
    public int getIndex(IMenuItem item) {
        return mItems.indexOf(item);
    }

    @Override
    public Plugin getPlugin() {
        return mPlugin;
    }

}
