package za.co.zubair.service;

import za.co.zubair.model.Item;
import za.co.zubair.model.TYPE;

import java.util.List;


public interface ItemService {
    Item getItemBySerialNumber(String serialNumber);

    List<Item> getItemsByDescription(String description);

    List<Item> getItemsByType(TYPE type);

    List<Item> getAllItems();

    void addItem(Item item);

    void deleteItem(Item item);

    void updateItem(Item item);
}
