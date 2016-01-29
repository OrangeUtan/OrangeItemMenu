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

/**
 * {@link IItemMenu} that doesn't change for any Player ({@link IMenuItem}s may still change their Icons)
 * Created by Michael on 27.01.2016.
 */
public abstract class StaticMenu implements IItemMenu {

    protected Plugin mPlugin;
    protected Size mSize;
    protected String mName;
    protected ArrayList<IMenuItem> mItems = new ArrayList<>();
    protected IItemMenu mParentMenu;
    protected Player mPlayer;

    public StaticMenu(Plugin plugin, Player player, Size size, String name, IItemMenu parentMenu) {
        mPlugin = plugin;
        mSize = size;
        mName = name;
        mItems = new ArrayList<>(size.getSlots());
        mParentMenu = parentMenu;
        mPlayer = player;

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
    public void open(IItemMenu previousMenu) {
        Inventory inventory = Bukkit.createInventory(new MenuHolder(this, Bukkit.createInventory(mPlayer, mSize.getSlots())), mSize.getSlots(), mName);
        applyOnInventory(inventory);

        mPlayer.openInventory(inventory);
    }

    @Override
    public void close() {
        if (mPlayer.getOpenInventory() != null) {
            if (mPlayer.getOpenInventory().getTopInventory().getHolder() instanceof MenuHolder) {
                final Player p = mPlayer;
                Bukkit.getScheduler().scheduleSyncDelayedTask(mPlugin, new Runnable() {
                    public void run() {
                        p.closeInventory();
                    }
                }, 1);
            }
        }
    }

    @Override
    public void syncInventory() {
        if (mPlayer.getOpenInventory() != null) {
            Inventory inventory = mPlayer.getOpenInventory().getTopInventory();
            if (inventory.getHolder() instanceof MenuHolder && ((MenuHolder) inventory.getHolder()).getMenu().equals(this)) {
                inventory.clear();
                applyOnInventory(inventory);
                mPlayer.updateInventory();
            }
        }
    }

    @Override
    public void updateContent() {
        // Content doesn't change
    }

    @Override
    public void clear() {
        // Do nothing
    }

    @Override
    public void navigateBack() {
        if (mParentMenu != null) {
            final Player p = mPlayer;
            Bukkit.getScheduler().scheduleSyncDelayedTask(mPlugin, new Runnable() {
                @Override
                public void run() {
                    if (p != null) mParentMenu.resume(StaticMenu.this);
                }
            }, 3);
        }
    }

    @Override
    public void resume(IItemMenu oldMenu) {
        syncInventory();
    }

    @Override
    public void applyOnInventory(Inventory inventory) {
        for (int i = 0; i < mItems.size(); i++) {
            if (i >= mItems.size()) break;
            if (mItems.get(i) != null && inventory.getItem(i) == null) {
                inventory.setItem(i, mItems.get(i).getIcon());
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
        for (int i=0; i<mItems.size(); i++) {
            if (mItems.get(i) == null || mItems.get(i).canBeReplaced()) {
                mItems.set(i, newItem);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeItem(int index) {
        if (index >= mItems.size()) return false;
        mItems.set(index, new EmptySlotItem(this, DyeColor.GRAY));
        return true;
    }

    @Override
    public boolean removeItem(IMenuItem item) {
        return removeItem(mItems.indexOf(item));
    }

    @Override
    public void replaceItem(int index, IMenuItem replaceWith) {
        setItem(index, replaceWith);
    }

    @Override
    public IItemMenu setItem(int index, IMenuItem item) {
        if (index >= mItems.size()) return null;
        mItems.set(index, item);
        return this;
    }

    @Override
    public IItemMenu setParent(IItemMenu menu) {
        mParentMenu = menu;
        return this;
    }

    @Override
    public void setName(String name) {
        mName = name;
    }

    @Override
    public void setSize(Size size) {
        mSize = size;
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

    @Override
    public IItemMenu getParent() {
        return mParentMenu;
    }

    @Override
    public Player getPlayer() {
        return mPlayer;
    }

    @Override
    public void setPlayer(Player player) {
        mPlayer = player;
    }
}
