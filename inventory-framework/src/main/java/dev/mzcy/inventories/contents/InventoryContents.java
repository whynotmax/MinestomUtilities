package dev.mzcy.inventories.contents;

import dev.mzcy.utilities.pair.Pair;
import lombok.Getter;
import lombok.Setter;
import net.minestom.server.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the contents of an inventory.
 * The contents are stored as a map of slots to intelligent items.
 */
public class InventoryContents {

    // The map of slots to intelligent items.
    Map<Slot, IntelligentItem> contents;
    // The size of the GUI for the inventory.
    final int guiSize;

    /**
     * Constructs a new InventoryContents with the specified GUI size.
     *
     * @param guiSize the size of the GUI for the inventory.
     */
    public InventoryContents(int guiSize) {
        this.guiSize = guiSize;
        contents = new HashMap<>(guiSize);
    }

    /**
     * Sets the item in the specified slot.
     *
     * @param row    the row of the slot.
     * @param column the column of the slot.
     * @param item   the item to set.
     */
    public void setSlot(int row, int column, IntelligentItem item) {
        contents.put(Slot.of(row, column), item);
    }

    /**
     * Sets the item in the specified slot.
     *
     * @param slot the slot to set.
     * @param item the item to set.
     */
    public void setSlot(int slot, IntelligentItem item) {
        contents.put(Slot.of(slot), item);
    }

    /**
     * Returns the item in the specified slot.
     *
     * @param row    the row of the slot.
     * @param column the column of the slot.
     * @return the item in the specified slot.
     */
    public IntelligentItem getSlot(int row, int column) {
        return contents.get(Slot.of(row, column));
    }

    /**
     * Returns the item in the specified slot.
     *
     * @param slot the slot to get.
     * @return the item in the specified slot.
     */
    public IntelligentItem getSlot(int slot) {
        return contents.get(Slot.of(slot));
    }

    /**
     * Updates the item in the specified slot.
     *
     * @param row    the row of the slot.
     * @param column the column of the slot.
     * @param item   the item to set.
     */
    public void updateSlot(int row, int column, IntelligentItem item) {
        contents.replace(Slot.of(row, column), item);
    }

    /**
     * Updates the item in the specified slot.
     *
     * @param slot the slot to update.
     * @param item the item to set.
     */
    public void updateSlot(int slot, IntelligentItem item) {
        contents.replace(Slot.of(slot), item);
    }

    /**
     * Removes the item from the specified slot.
     *
     * @param row    the row of the slot.
     * @param column the column of the slot.
     */
    public void removeSlot(int row, int column) {
        contents.remove(Slot.of(row, column));
    }

    /**
     * Removes the item from the specified slot.
     *
     * @param slot the slot to remove.
     */
    public void removeSlot(int slot) {
        contents.remove(Slot.of(slot));
    }

    /**
     * Clears all items from the inventory.
     */
    public void clear() {
        contents.clear();
    }

    /**
     * Fills all slots in the inventory with the specified item.
     *
     * @param item the item to fill with.
     */
    public void fill(IntelligentItem item) {
        for (int i = 0; i < guiSize; i++) {
            contents.put(Slot.of(i), item);
        }
    }

    /**
     * Fills all slots in the specified row with the specified item.
     *
     * @param row  the row to fill.
     * @param item the item to fill with.
     */
    public void fillRow(int row, IntelligentItem item) {
        for (int i = (row * 9); i < ((row * 9) + 9); i++) {
            contents.put(Slot.of(row, i), item);
        }
    }

    /**
     * Fills all slots in the specified column with the specified item.
     *
     * @param column the column to fill.
     * @param item   the item to fill with.
     */
    public void fillColumn(int column, IntelligentItem item) {
        for (int i = column; i < guiSize; i += 9) {
            contents.put(Slot.of(i), item);
        }
    }

    /**
     * Fills all slots in the specified range with the specified item.
     *
     * @param start the start of the range.
     * @param end   the end of the range.
     * @param item  the item to fill with.
     */
    public void fillRange(int start, int end, IntelligentItem item) {
        for (int i = start; i < end; i++) {
            contents.put(Slot.of(i), item);
        }
    }

    /**
     * Fills all slots in the specified rectangle with the specified item.
     *
     * @param startRow    the start row of the rectangle.
     * @param startColumn the start column of the rectangle.
     * @param endRow      the end row of the rectangle.
     * @param endColumn   the end column of the rectangle.
     * @param item        the item to fill with.
     */
    public void fillRectangle(int startRow, int startColumn, int endRow, int endColumn, IntelligentItem item) {
        for (int i = startRow; i < endRow; i++) {
            for (int j = startColumn; j < endColumn; j++) {
                contents.put(Slot.of(i, j), item);
            }
        }
    }

    /**
     * Fills all slots in the specified rectangle with the specified item.
     *
     * @param startSlot the start slot of the rectangle.
     * @param endSlot   the end slot of the rectangle.
     * @param item      the item to fill with.
     */
    public void fillRectangle(int startSlot, int endSlot, IntelligentItem item) {
        for (int i = startSlot; i < endSlot; i++) {
            contents.put(Slot.of(i), item);
        }
    }

    /**
     * Fills the border of the inventory with the specified item.
     *
     * @param item the item to fill with.
     */
    public void fillBorder(IntelligentItem item) {
        //Gui slots range from 0 to guiSize
        //Top border
        fillRange(0, 9, item);
        //Bottom border
        fillRange(guiSize - 9, guiSize, item);
        //Left border
        fillColumn(0, item);
        //Right border
        fillColumn(8, item);
    }

    /**
     * Fills all slots from the start to the end with the specified item.
     *
     * @param start the start of the range.
     * @param end   the end of the range.
     * @param item  the item to fill with.
     */
    public void fillFromTo(int start, int end, IntelligentItem item) {
        for (int i = start; i < end; i++) {
            contents.put(Slot.of(i), item);
        }
    }

    /**
     * Fills all slots from the start to the end with the specified item.
     *
     * @param startRow    the start row of the range.
     * @param startColumn the start column of the range.
     * @param endRow      the end row of the range.
     * @param endColumn   the end column of the range.
     * @param item        the item to fill with.
     */
    public void fillFromTo(int startRow, int startColumn, int endRow, int endColumn, IntelligentItem item) {
        for (int i = startRow; i < endRow; i++) {
            for (int j = startColumn; j < endColumn; j++) {
                contents.put(Slot.of(i, j), item);
            }
        }
    }

    /**
     * A private inner class representing a slot in an inventory.
     * A slot is defined by its row and column in the inventory grid.
     */
    @Setter
    @Getter
    private static class Slot {
        // The row of the slot in the inventory grid.
        int row;
        // The column of the slot in the inventory grid.
        int column;

        /**
         * Constructs a new Slot with the specified row and column.
         *
         * @param row    the row of the slot in the inventory grid.
         * @param column the column of the slot in the inventory grid.
         */
        private Slot(int row, int column) {
            this.row = row;
            this.column = column;
        }

        /**
         * Returns the absolute position of the slot in the inventory.
         * The position is calculated as (row * 9) + column.
         *
         * @return the absolute position of the slot in the inventory.
         */
        public int getSlot() {
            return (row * 9) + column;
        }

        /**
         * Returns the row of the slot in the inventory grid.
         *
         * @return the row of the slot.
         */
        public int getRow() {
            return row;
        }

        /**
         * Returns the column of the slot in the inventory grid.
         *
         * @return the column of the slot.
         */
        public int getColumn() {
            return column;
        }

        /**
         * Returns a new Slot with the specified row and column.
         *
         * @param row    the row of the slot in the inventory grid.
         * @param column the column of the slot in the inventory grid.
         * @return a new Slot with the specified row and column.
         */
        public static Slot of(int row, int column) {
            return new Slot(row, column);
        }

        /**
         * Returns a new Slot with the specified slot.
         *
         * @param slot the slot in the inventory grid.
         * @return a new Slot with the specified slot.
         */
        public static Slot of(int slot) {
            return new Slot(slot / 9, slot % 9);
        }
    }
}