package za.co.zubair.api;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.co.zubair.model.Item;
import za.co.zubair.service.ItemService;

import javax.inject.Inject;

@RestController
@RequestMapping(value = "/api")
public class ItemResource {

    private ItemService itemService;

    @Inject
    public ItemResource (ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(value = "/item/add", method = RequestMethod.POST)
    public void addItem(@ModelAttribute Item item){
        itemService.addItem(item);
    }

}
