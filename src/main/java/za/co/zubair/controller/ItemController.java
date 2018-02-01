package za.co.zubair.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import za.co.zubair.model.Item;
import za.co.zubair.service.ItemService;

import javax.inject.Inject;

@Controller
public class ItemController {

    private ItemService itemService;

    @Inject
    public ItemController (ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping("/item")
    public String getAllItems(@RequestParam(value = "serialNumber", required = false) String serialNumber,
                              @RequestParam(value = "description", required = false) String description,
                              @RequestParam(value = "type", required = false) Item.TYPE type,
                              Model model){
        model.addAttribute("item", new Item("",null,"",null));
        model.addAttribute("retrievedItems", itemService.getItem(serialNumber, description, type));
        return "item";
    }

    @RequestMapping(value = "/item/add",method = RequestMethod.GET)
    public String addItem(Model model){
        model.addAttribute("item", new Item("",null,"",null));
        return "addItem";
    }
}
