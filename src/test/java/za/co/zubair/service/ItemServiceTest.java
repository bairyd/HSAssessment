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

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private ItemRepository mockItemRepository;

    @Before
    public void setup(){
        when(mockItemRepository.getItemBySerialNumber(any())).thenReturn(createDummyItem());
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

    private Item createDummyItem(){
        Item item = new Item();
        item.setSerialNumber("12345");
        item.setDescription("dummy item");
        item.setThumbnail(null);
        item.setType(TYPE.CPU);
        return item;
    }
}
