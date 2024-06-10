package dev.mzcy.inventories.manager;

import dev.mzcy.inventories.InventoryFramework;
import dev.mzcy.inventories.impl.Gui;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.inventory.InventoryCloseEvent;
import net.minestom.server.timer.Task;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Manages the GUIs for an inventory framework.
 * This class is responsible for registering, unregistering, and retrieving GUIs.
 */
@Getter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class InventoryManager {

    // The inventory framework this manager is part of.
    final InventoryFramework framework;
    // The instance of this manager.
    final InventoryManager instance;
    // The list of registered GUIs.
    final List<Gui> registeredGuis;
    final Map<Player, Task> guiUpdateTasks;

    /**
     * Constructs a new InventoryManager for the specified inventory framework.
     * This constructor is internal and should not be called directly.
     *
     * @param framework the inventory framework this manager is part of.
     */
    @ApiStatus.Internal
    private InventoryManager(@NonNull InventoryFramework framework) {
        this.framework = framework;
        instance = this;
        registeredGuis = new CopyOnWriteArrayList<>();
        guiUpdateTasks = new ConcurrentHashMap<>();
    }

    /**
     * Creates a new instance of InventoryManager for the specified inventory framework.
     *
     * @param framework the inventory framework to create a manager for.
     * @return a new instance of InventoryManager.
     */
    public static InventoryManager createInstance(@NonNull InventoryFramework framework) {
        return new InventoryManager(framework);
    }

    /**
     * Registers the specified GUI.
     *
     * @param gui the GUI to register.
     */
    public void registerGui(@NonNull Gui gui) {
        registeredGuis.add(gui);
    }

    /**
     * Unregisters the specified GUI.
     *
     * @param gui the GUI to unregister.
     */
    public void unregisterGui(@NonNull Gui gui) {
        registeredGuis.remove(gui);
    }

    /**
     * Unregisters all GUIs.
     */
    public void unregisterAllGuis() {
        registeredGuis.clear();
    }

    /**
     * Returns the GUI with the specified title.
     *
     * @param title the title of the GUI to retrieve.
     * @return the GUI with the specified title, or null if no such GUI exists.
     */
    public Gui getGui(@NonNull String title) {
        for (Gui gui : registeredGuis) {
            if (gui.getTitle().equals(title)) {
                return gui;
            }
        }
        return null;
    }

    public void openGui(@NonNull Gui gui, @NonNull Player player) {
        if (!registeredGuis.contains(gui)) {
            throw new IllegalArgumentException("The specified GUI is not registered. Please register it first using InventoryManager#registerGui(Gui).");
        }
        gui.initialize(gui.getContents());
        gui.open(player);
        gui.setPlayer(player);
        if (gui.isUpdateable()) {
            Task task = MinecraftServer.getSchedulerManager().buildTask(() -> {
                gui.update(player, gui.getContents());
            }).delay(gui.getUpdateInterval()).repeat(gui.getUpdateInterval()).schedule();
            guiUpdateTasks.put(player, task);
        }
    }

}