package za.co.zubair.respository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import za.co.zubair.model.Item;
import za.co.zubair.model.TYPE;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, String>{

    @Query("SELECT i.serialNumber, i.description, i.type, i.thumbnail FROM Item i WHERE DESCRIPTION LIKE CONCAT('%',:description,'%')")
    List<Item> getItemsMatchingDescription(String description);

    @Query("SELECT i.serialNumber, i.description, i.type, i.thumbnail FROM Item i WHERE TYPE LIKE CONCAT('%',:type,'%')")
    List<Item> getItemsByType(@Param("type") TYPE type);

    @Override
    Item findOne(String serialNumber);

    @Override
    List<Item> findAll();

}
