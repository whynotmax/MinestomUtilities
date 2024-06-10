package dev.mzcy.inventories.listener;

import dev.mzcy.inventories.InventoryFramework;
import dev.mzcy.inventories.impl.Gui;
import lombok.experimental.FieldDefaults;
import net.kyori.adventure.text.TextComponent;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.inventory.InventoryCloseEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class InventoryCloseListener implements EventListener<InventoryCloseEvent> {

    InventoryFramework instance;

    public InventoryCloseListener(InventoryFramework instance) {
        this.instance = instance;
        MinecraftServer.getGlobalEventHandler().addListener(this);
    }
    @Override
    public @NotNull Class<InventoryCloseEvent> eventType() {
        return InventoryCloseEvent.class;
    }

    @NotNull
    @Override
    public Result run(@NotNull InventoryCloseEvent inventoryCloseEvent) {
        TextComponent title = (TextComponent) inventoryCloseEvent.getInventory().getTitle();
        Gui gui;
        if ((gui = instance.getManager().getGui(title.content())) != null) {
            gui.close(inventoryCloseEvent.getPlayer());
            instance.getManager().getGuiUpdateTasks().remove(inventoryCloseEvent.getPlayer()).cancel();
        }
        return Result.SUCCESS;
    }
}
