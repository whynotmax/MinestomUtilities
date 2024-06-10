package dev.mzcy.inventories.contents;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;
import net.minestom.server.event.inventory.InventoryClickEvent;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * Represents an item in an inventory that can respond intelligently to click events.
 * This class is experimental and may change in future versions.
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author KeineSecrets
 *
 * @see ItemStack
 * @see InventoryClickEvent
 * @see InventoryPreClickEvent
 * @see Consumer
 */
@Getter
@Setter
@ApiStatus.Experimental
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class IntelligentItem {

    // The item stack represented by this intelligent item.
    ItemStack itemStack;
    // The consumer that handles inventory click events for this item.
    Consumer<InventoryClickEvent> inventoryClickEventConsumer;
    // The consumer that handles inventory pre-click events for this item.
    // This field is experimental and may change in future versions.
    @ApiStatus.Experimental
    Consumer<InventoryPreClickEvent> inventoryPreClickEventConsumer;

    /**
     * Constructs a new IntelligentItem with the specified item stack and event consumers.
     * This constructor is experimental and may change in future versions without warning.
     *
     * @param itemStack the item stack represented by this intelligent item.
     * @param inventoryClickEventConsumer the consumer that handles inventory click events for this item.
     * @param inventoryPreClickEventConsumer the consumer that handles inventory pre-click events for this item.
     */
    @ApiStatus.Internal
    private IntelligentItem(@NonNull ItemStack itemStack, @Nullable Consumer<InventoryClickEvent> inventoryClickEventConsumer, @Nullable Consumer<InventoryPreClickEvent> inventoryPreClickEventConsumer) {
        this.itemStack = itemStack;
        this.inventoryClickEventConsumer = inventoryClickEventConsumer;
        this.inventoryPreClickEventConsumer = inventoryPreClickEventConsumer;
    }

    /**
     * Returns a new IntelligentItem that represents an empty slot in the inventory.
     *
     * @return a new IntelligentItem that represents an empty slot in the inventory.
     */
    public static IntelligentItem empty() {
        return new IntelligentItem(ItemStack.builder(Material.AIR).build(), null, null);
    }

    /**
     * Returns a new IntelligentItem that represents an empty slot in the inventory with the specified item stack.
     *
     * @param itemStack the item stack to use for the empty slot.
     * @return a new IntelligentItem that represents an empty slot in the inventory with the specified item stack.
     */
    public static IntelligentItem empty(ItemStack itemStack) {
        return new IntelligentItem(itemStack, null, null);
    }

    /**
     * Returns a new IntelligentItem with the specified item stack and click event consumer.
     *
     * @param itemStack the item stack represented by this intelligent item.
     * @param inventoryClickEventConsumer the consumer that handles inventory click events for this item.
     * @return a new IntelligentItem with the specified item stack and click event consumer.
     */
    public static IntelligentItem of(ItemStack itemStack, Consumer<InventoryClickEvent> inventoryClickEventConsumer) {
        return new IntelligentItem(itemStack, inventoryClickEventConsumer, null);
    }

    /**
     * Returns a new IntelligentItem with the specified item stack, click event consumer, and pre-click event consumer.
     * This method is experimental and may change in future versions.
     *
     * @param itemStack the item stack represented by this intelligent item.
     * @param inventoryClickEventConsumer the consumer that handles inventory click events for this item.
     * @param inventoryPreClickEventConsumer the consumer that handles inventory pre-click events for this item.
     * @return a new IntelligentItem with the specified item stack, click event consumer, and pre-click event consumer.
     */
    @ApiStatus.Experimental
    public static IntelligentItem of(ItemStack itemStack, Consumer<InventoryClickEvent> inventoryClickEventConsumer, Consumer<InventoryPreClickEvent> inventoryPreClickEventConsumer) {
        return new IntelligentItem(itemStack, inventoryClickEventConsumer, inventoryPreClickEventConsumer);
    }

}