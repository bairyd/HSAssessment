package za.co.zubair.respository;

import org.springframework.data.repository.CrudRepository;
import za.co.zubair.model.Item;
import za.co.zubair.model.TYPE;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, String>{

    Item getItemBySerialNumber(String serialNumber);

    List<Item> getItemsByDescription(String description);

    List<Item> getItemsByType(TYPE type);

    @Override
    List<Item> findAll();

}
