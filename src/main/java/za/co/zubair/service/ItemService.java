package za.co.zubair.service;

import za.co.zubair.model.Item;

import java.util.List;


public interface ItemService {
    List<Item> getItem(String serialNumber, String description, Item.TYPE type);

    Item getItemBySerialNumber(String serialNumber);

    List<Item> getItemsByDescription(String description);

    List<Item> getItemsByType(Item.TYPE type);

    List<Item> getAllItems();

    void addItem(Item item);

    void deleteItem(Item item);

    void updateItem(Item item);
}
