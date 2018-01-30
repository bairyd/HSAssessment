package za.co.zubair.service.impl;

import org.springframework.util.ObjectUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import za.co.zubair.model.Item;
import za.co.zubair.respository.ItemRepository;
import za.co.zubair.service.ItemService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Inject
    public ItemServiceImpl (ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getItem(String serialNumber, String description, Item.TYPE type) {
        List <Item> items = new ArrayList<>();
        if (!ObjectUtils.isEmpty(serialNumber)){
            Item item = getItemBySerialNumber(serialNumber);
            items.add(item);
        } else if (!ObjectUtils.isEmpty(description)) {
            items = getItemsByDescription(description);
        } else if (!ObjectUtils.isEmpty(type)) {
            items = getItemsByType(type);
        } else {
            items = getAllItems();
        }
        return items;
    }

    @Override
    public Item getItemBySerialNumber(String serialNumber) {
        return itemRepository.findOne(serialNumber);
    }

    @Override
    public List<Item> getItemsByDescription(String description) {
        return itemRepository.getItemsMatchingDescription(description);
    }

    @Override
    public List<Item> getItemsByType(Item.TYPE type) {
        return itemRepository.getItemsByType(type);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public void addItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void deleteItem(Item item) {
        itemRepository.delete(item);
    }

    @Override
    public void updateItem(Item item) {
        throw new NotImplementedException();
    }


}
