package za.co.zubair.respository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import za.co.zubair.model.Item;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, String>{

    @Query("SELECT new Item (i.serialNumber, i.type, i.description,  i.thumbnail) " +
            "FROM Item i " +
            "WHERE description LIKE CONCAT('%',:description,'%')")
    List<Item> getItemsMatchingDescription(@Param("description") String description);

    List<Item> getItemsByType(Item.TYPE type);

    @Override
    Item findOne(String serialNumber);

    @Override
    List<Item> findAll();

}
