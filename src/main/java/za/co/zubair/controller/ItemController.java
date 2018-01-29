package za.co.zubair.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import za.co.zubair.model.TYPE;
import za.co.zubair.service.ItemService;

import javax.inject.Inject;

@Controller
public class ItemController {

    @Inject
    ItemService itemService;

    @RequestMapping("/item")
    public String getAllItems(@RequestParam(value = "serialNumber", required = false) String serialNumber,
                              @RequestParam(value = "description", required = false) String description,
                              @RequestParam(value = "type", required = false) TYPE type,
                              Model model){
        model.addAttribute("retrievedItems", itemService.getItem(serialNumber, description, type));
        return "item";
    }
}
