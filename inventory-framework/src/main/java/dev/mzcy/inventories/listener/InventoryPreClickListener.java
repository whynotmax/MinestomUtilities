package dev.mzcy.inventories.listener;

import dev.mzcy.inventories.InventoryFramework;
import dev.mzcy.inventories.contents.IntelligentItem;
import dev.mzcy.inventories.impl.Gui;
import lombok.experimental.FieldDefaults;
import net.kyori.adventure.text.TextComponent;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.inventory.InventoryClickEvent;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import org.jetbrains.annotations.NotNull;

@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class InventoryPreClickListener implements EventListener<InventoryPreClickEvent> {

    InventoryFramework instance;

    public InventoryPreClickListener(InventoryFramework instance) {
        this.instance = instance;
        MinecraftServer.getGlobalEventHandler().addListener(this);
    }
    @Override
    public @NotNull Class<InventoryPreClickEvent> eventType() {
        return InventoryPreClickEvent.class;
    }

    @NotNull
    @Override
    public Result run(@NotNull InventoryPreClickEvent inventoryClickEvent) {

        TextComponent title = (TextComponent) inventoryClickEvent.getInventory().getTitle();
        Gui gui;
        if ((gui = instance.getManager().getGui(title.content())) != null) {
            IntelligentItem item = gui.getContents().getSlot(inventoryClickEvent.getSlot());
            if (item != null) {
                if (item.getInventoryPreClickEventConsumer() != null)
                    item.getInventoryPreClickEventConsumer().accept(inventoryClickEvent);
            }
        }
        return Result.SUCCESS;
    }
}
