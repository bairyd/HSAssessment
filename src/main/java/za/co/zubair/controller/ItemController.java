package za.co.zubair.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import za.co.zubair.service.ItemService;

import javax.inject.Inject;

@Controller
public class ItemController {

    @Inject
    ItemService itemService;

    @RequestMapping("/item")
    public String getAllItems(Model model){
        model.addAttribute("allItems", itemService.getAllItems());
        return "item";
    }
}
