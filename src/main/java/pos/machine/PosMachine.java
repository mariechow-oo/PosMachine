package pos.machine;

import java.util.ArrayList;
import java.util.List;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        List<Item> allItems = ItemDataLoader.loadAllItems();
        List<Item> items = new ArrayList<>();
        for (String barcode : barcodes) {
            for (Item allItem : allItems) {
                if (barcode.equals(allItem.getBarcode())) {
                    items.add(allItem);
                }
            }
        }

        List<GroupedItem> groupedItems = GroupedItem.fromItems(items);
        String receipt = "***<store earning no money>Receipt***\n";
        receipt += generateItemListString(groupedItems);
        receipt += "----------------------\n";
        receipt += "Total: " + getTotalPrice(groupedItems) + " (yuan)\n";
        receipt += "**********************";

        return receipt;
    }
    String generateItemListString(List<GroupedItem> groupedItems) {
        String itemListString = "";
        for (GroupedItem groupedItem : groupedItems) {
            itemListString += generateItemString(groupedItem);
        }
        return itemListString;
    }
    String generateItemString(GroupedItem groupedItem) {
        return "Name: " + groupedItem.getName() + ", Quantity: " + groupedItem.getQuantity() + ", Unit price: " + groupedItem.getUnitPrice() + " (yuan), Subtotal: " + groupedItem.getSubtotalPrice() + " (yuan)\n";
    }
    int getTotalPrice(List<GroupedItem> groupedItems) {
        int totalPrice = 0;
        for (GroupedItem groupedItem : groupedItems) {
            totalPrice += groupedItem.getSubtotalPrice();
        }
        return totalPrice;
    }
}
