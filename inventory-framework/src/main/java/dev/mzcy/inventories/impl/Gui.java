package dev.mzcy.inventories.impl;

import dev.mzcy.inventories.contents.InventoryContents;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.timer.Task;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;

@Getter
@Setter
public abstract class Gui {

    String title;
    Player player;
    InventoryType type;
    int rows;
    int size;
    InventoryContents contents;
    boolean isUpdateable;
    Duration updateInterval;

    /**
     * Constructs a new GUI with the specified title and number of rows.
     * @param title             the title of the GUI.
     * @param rows              the number of rows in the GUI.
     * @param updateInterval    the update interval of the GUI. If null, the GUI will not be updated.
     */
    Gui(@NonNull String title, @NonNull int rows, @Nullable Duration updateInterval) {
        this.title = title;
        switch (rows) {
            case 1:
                type = InventoryType.CHEST_1_ROW;
                break;
            case 2:
                type = InventoryType.CHEST_2_ROW;
                break;
            case 3:
                type = InventoryType.CHEST_3_ROW;
                break;
            case 4:
                type = InventoryType.CHEST_4_ROW;
                break;
            case 5:
                type = InventoryType.CHEST_5_ROW;
                break;
            case 6:
                type = InventoryType.CHEST_6_ROW;
                break;
            default:
                throw new IllegalArgumentException("Invalid number of rows: " + rows);
        }
        this.rows = rows;
        this.size = rows * 9;
        this.contents = new InventoryContents(rows);
        if (updateInterval != null) {
            this.isUpdateable = true;
            this.updateInterval = updateInterval;
            return;
        }
        this.isUpdateable = false;
        this.updateInterval = null;
    }

    /**
     * Constructs a new GUI with the specified title and inventory type.
     * @param title             the title of the GUI.
     * @param type              the type of the GUI.
     * @param updateInterval    the update interval of the GUI. If null, the GUI will not be updated.
     */
    Gui(@NonNull String title, @NonNull InventoryType type, @Nullable Duration updateInterval) {
        this.title = title;
        this.type = type;
        this.rows = type.getSize();
        this.size = type.getSize();
        this.contents = new InventoryContents(type.getSize());
        if (updateInterval != null) {
            this.isUpdateable = true;
            this.updateInterval = updateInterval;
            return;
        }
        this.isUpdateable = false;
        this.updateInterval = null;
    }

    /**
     * Initializes the GUI. This method will be called before the GUI is opened.
     */
    public abstract void initialize(InventoryContents contents);

    /**
     * Opens the GUI for the specified player.
     *
     * @param player the player for whom the GUI should be opened.
     */
    public abstract void open(Player player);

    /**
     * Closes the GUI for the specified player.
     *
     * @param player the player for whom the GUI should be closed.
     */
    public abstract void close(Player player);

    /**
     * Updates the GUI for the specified player.
     *
     * @param player the player for whom the GUI should be updated.
     */
    public abstract void update(Player player, InventoryContents contents);

}
