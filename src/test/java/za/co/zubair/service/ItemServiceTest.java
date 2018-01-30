package za.co.zubair.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import za.co.zubair.model.Item;
import za.co.zubair.model.TYPE;
import za.co.zubair.respository.ItemRepository;
import za.co.zubair.service.impl.ItemServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private ItemRepository mockItemRepository;

    @Before
    public void setup(){
        when(mockItemRepository.findOne(any())).thenReturn(createDummyItem());
    }

    @Test
    public void verifyThat_GetItemBySerialNumber_ReturnsAnItem(){
        String serialNumber = "12345";
        Item returnedItem = null;
        assertNull(returnedItem);
        returnedItem = itemService.getItemBySerialNumber(serialNumber);
        assertNotNull(returnedItem);
        assertEquals(TYPE.CPU,returnedItem.getType());
    }

    @Test
    public void verifyThatItemIsRetrievedBySerialNumber(){
        when(mockItemRepository.findOne(anyString())).thenReturn(createDummyItem());
        String serialNumber = "12345";
        List <Item> items = itemService.getItem(serialNumber, null, null);
        verify(mockItemRepository).findOne(serialNumber);
        assertTrue(items.size() > 0);
        assertEquals("dummy item", items.get(0).getDescription());
    }

    @Test
    public void verifyThatItemsAreRetrievedByType(){
        TYPE cpu = TYPE.CPU;
        List<Item> remainingItems = createListOfDummyItems()
                                        .stream()
                                        .filter(item -> item.getType().compareTo(cpu) == 0)
                                        .collect(Collectors.toList());
        when(mockItemRepository.getItemsByType(any(TYPE.class))).thenReturn(remainingItems);
        List <Item> items = itemService.getItem(null, null, cpu);
        verify(mockItemRepository).getItemsByType(cpu);
        assertTrue(items.size() == 2);
        assertTrue(items.stream().noneMatch(item -> item.getType().compareTo(TYPE.AUDIO) == 0));
    }

    @Test
    public void verifyThatItemsAreRetrievedByMatchingDescription(){
        String description = "item";
        List<Item> matchingDescriptions = createListOfDummyItems()
                .stream()
                .filter(item -> item.getDescription().contains(description))
                .collect(Collectors.toList());
        when(mockItemRepository.getItemsMatchingDescription(anyString())).thenReturn(matchingDescriptions);
        List <Item> returnedItems = itemService.getItem(null, description, null);
        verify(mockItemRepository).getItemsMatchingDescription(description);
        assertTrue(returnedItems.size() == 3);
    }

    private Item createDummyItem(){
        Item item = new Item();
        item.setSerialNumber("12345");
        item.setDescription("dummy item");
        item.setThumbnail(null);
        item.setType(TYPE.CPU);
        return item;
    }

    private List<Item> createListOfDummyItems(){
        List<Item> items = new ArrayList<>();
        Item item1 = new Item("11111",TYPE.CPU,"item1",null);
        items.add(item1);

        Item item2 = new Item("99999",TYPE.AUDIO,"item2",null);
        items.add(item2);

        Item item3 = new Item("55555",TYPE.CPU,"thing3",null);
        items.add(item3);

        Item item4 = new Item("75342",TYPE.MEMORY,"item4",null);
        items.add(item4);

        return items;
    }

}
