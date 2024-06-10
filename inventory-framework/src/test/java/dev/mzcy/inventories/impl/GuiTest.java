package dev.mzcy.inventories.impl;

import dev.mzcy.inventories.contents.InventoryContents;
import dev.mzcy.inventories.impl.Gui;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.InventoryType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GuiTest {

    @Mock
    private Player player;

    @Mock
    private InventoryContents contents;

    private Gui gui;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        gui = new Gui("Test GUI", InventoryType.CHEST_1_ROW, Duration.ofSeconds(1)) {
            @Override
            public void initialize(InventoryContents contents) {
                // Implementation not needed for this test
            }

            @Override
            public void open(Player player) {
                // Implementation not needed for this test
            }

            @Override
            public void close(Player player) {
                // Implementation not needed for this test
            }

            @Override
            public void update(Player player, InventoryContents contents) {
                // Implementation not needed for this test
            }
        };
    }

    @Test
    public void shouldInitializeGui() {
        gui.initialize(contents);
        assertNotNull(gui.getContents());
    }

    @Test
    public void shouldOpenGuiForPlayer() {
        gui.open(player);
    }

    @Test
    public void shouldCloseGuiForPlayer() {
        gui.close(player);
        verify(player, times(1)).closeInventory();
    }

    @Test
    public void shouldUpdateGuiForPlayer() {
        gui.update(player, contents);
    }

    @Test
    public void shouldNotUpdateGuiWhenUpdateIntervalIsNull() {
        Gui nonUpdateableGui = new Gui("Test GUI", InventoryType.CHEST_1_ROW, null) {
            @Override
            public void initialize(InventoryContents contents) {
                // Implementation not needed for this test
            }

            @Override
            public void open(Player player) {
                // Implementation not needed for this test
            }

            @Override
            public void close(Player player) {
                // Implementation not needed for this test
            }

            @Override
            public void update(Player player, InventoryContents contents) {
                // Implementation not needed for this test
            }
        };
        nonUpdateableGui.update(player, contents);
    }
}