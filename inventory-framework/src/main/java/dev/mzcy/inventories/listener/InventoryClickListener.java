package dev.mzcy.inventories.listener;

import dev.mzcy.inventories.InventoryFramework;
import dev.mzcy.inventories.contents.IntelligentItem;
import dev.mzcy.inventories.impl.Gui;
import lombok.experimental.FieldDefaults;
import net.kyori.adventure.text.TextComponent;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class InventoryClickListener implements EventListener<InventoryClickEvent> {

    InventoryFramework instance;

    public InventoryClickListener(InventoryFramework instance) {
        this.instance = instance;
        MinecraftServer.getGlobalEventHandler().addListener(this);
    }
    @Override
    public @NotNull Class<InventoryClickEvent> eventType() {
        return InventoryClickEvent.class;
    }

    @NotNull
    @Override
    public Result run(@NotNull InventoryClickEvent inventoryClickEvent) {

        TextComponent title = (TextComponent) inventoryClickEvent.getInventory().getTitle();
        Gui gui;
        if ((gui = instance.getManager().getGui(title.content())) != null) {
            IntelligentItem item = gui.getContents().getSlot(inventoryClickEvent.getSlot());
            if (item != null) {
                item.getInventoryClickEventConsumer().accept(inventoryClickEvent);
            }
        }
        return null;
    }
}
