package dev.mzcy.inventories;

import dev.mzcy.inventories.listener.InventoryClickListener;
import dev.mzcy.inventories.listener.InventoryCloseListener;
import dev.mzcy.inventories.manager.InventoryManager;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class InventoryFramework {

    final InventoryFramework instance;
    final InventoryManager manager;

    private InventoryFramework() {
        this.instance = this;
        this.manager = InventoryManager.createInstance(this);

        new InventoryCloseListener(this);
        new InventoryClickListener(this);
    }

    public static InventoryFramework createInstance() {
        return new InventoryFramework();
    }

}
