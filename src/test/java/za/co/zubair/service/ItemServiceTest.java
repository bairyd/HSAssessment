package za.co.zubair.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import za.co.zubair.model.Item;
import za.co.zubair.respository.ItemRepository;
import za.co.zubair.service.impl.ItemServiceImpl;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
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
    public void setup() throws IOException {
        when(mockItemRepository.findOne(any())).thenReturn(createDummyItem());
    }

    @Test
    public void verifyThat_GetItemBySerialNumber_ReturnsAnItem(){
        String serialNumber = "12345";
        Item returnedItem = null;
        assertNull(returnedItem);
        returnedItem = itemService.getItemBySerialNumber(serialNumber);
        assertNotNull(returnedItem);
        assertEquals(Item.TYPE.CPU,returnedItem.getType());
    }

    @Test
    public void verifyThatItemIsRetrievedBySerialNumber() throws IOException {
        when(mockItemRepository.findOne(anyString())).thenReturn(createDummyItem());
        String serialNumber = "12345";
        List <Item> items = itemService.getItem(serialNumber, null, null);
        verify(mockItemRepository).findOne(serialNumber);
        assertTrue(items.size() > 0);
        assertEquals("dummy item", items.get(0).getDescription());
    }

    @Test
    public void verifyThatItemsAreRetrievedByType(){
        Item.TYPE cpu = Item.TYPE.CPU;
        List<Item> remainingItems = createListOfDummyItems()
                                        .stream()
                                        .filter(item -> item.getType().compareTo(cpu) == 0)
                                        .collect(Collectors.toList());
        when(mockItemRepository.getItemsByType(any(Item.TYPE.class))).thenReturn(remainingItems);
        List <Item> items = itemService.getItem(null, null, cpu);
        verify(mockItemRepository).getItemsByType(cpu);
        assertTrue(items.size() == 2);
        assertTrue(items.stream().noneMatch(item -> item.getType().compareTo(Item.TYPE.AUDIO) == 0));
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

    @Test
    public void verifyThatImageCorrectlyConvertedToByteArray() throws IOException {
        BufferedImage image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("test_keyboard.jpg"));
        byte[] convertedImage = itemService.convertImageToByteArray(image);
        assertEquals(getExpectedByteArrayBase64String(),Base64.getEncoder().encodeToString(convertedImage));
    }

    private Item createDummyItem() throws IOException {
        Item item = new Item();
        item.setSerialNumber("12345");
        item.setDescription("dummy item");
        item.setThumbnail(null);
        item.setType(Item.TYPE.CPU);
        return item;
    }

    private List<Item> createListOfDummyItems(){
        List<Item> items = new ArrayList<>();
        Item item1 = new Item("11111",Item.TYPE.CPU,"item1",null);
        items.add(item1);

        Item item2 = new Item("99999",Item.TYPE.AUDIO,"item2",null);
        items.add(item2);

        Item item3 = new Item("55555",Item.TYPE.CPU,"thing3",null);
        items.add(item3);

        Item item4 = new Item("75342",Item.TYPE.MEMORY,"item4",null);
        items.add(item4);

        return items;
    }

    private String getExpectedByteArrayBase64String(){
        return "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GrcX7nAGQDx9K3yKxtaQieJ/7yY/I/8A16qMFHYyqYidVWk9DKq/o7+XqUZ55yKoVZ09gt/DnoWAqjFHZCJz/AcfUU/yHP8ACPzqEzzdPMIHbFMMsneRj+NYctV9UeqquXRXwN/NIs/ZpO5UfnR9mI5MiD8Kpkk9SfxNA6UezqveX4D+uYGPw0L+rZb8lB1nX9KTZbjrOT9P/wBVVaXtR7GXWbD+06MfgoR+d2Wc2o6s7fnSeZaj/lkx/wA/Wq+M0mKPYLq394f2vNfBTivkix9ohH3bcfjil+2EfdiQVWApcU/YQ7EPOMX0dvRL/InN7L22j8KYbmY/x4+gFRYpce1NUaa6GMszxct6j+8cZpT1kf8AOmEk9ST9TS4oxVqMVsjCWJqz+KTfzYmM9qKcBS4qjG7Yzbzk8g/pU8UAY/wrzjJqMDtT45DG4IxuHQmpnzW93c2w7pqovar3SaS1dbQT4xhyjKRggiq2KleZ5FAZicdB9e9MIqaaml77KxUqMpJ0lZW/ETFJinUYrQ5huKKdSUAJijFLRQA3FJin7TjofrUMlxBF/rJ4l9iwzQApFYeszo8qQpy0edx9z2q3e6vCkBW1k3yNxnBAUev1rnycnNACVNA224jb0cH9ahpQcHPpQB2h6mkxTxyit6qD+lJigBuKMU7FGKAExxQBT8UYoAbilxS4pcdqAG4pMU/FFADMUuKdijFADcUUuKMUAJRS7SenNKw2DLkKPVjigBtBGelQSX9lHnfdRfRTu/lVZ9bsUHy+a57YXH86ANBeuT2/nTsViyeIscRWw+rvn+VVZNevW+6Y4/8AdT/HNAHSYJ6A/hSOVjGZHVB/tMBXJSajeS/fuZSPQNgfpVYksckkn1NAHWSajYxfeukJ9Ey38qrSa7aLwiSyfgFFc1RQBtyeIZM/uraNf94lv8Kqya1fPx5uweiKBWdRQBNJcTTf6yV3/wB5iahoooAKKKKACiiigDq7DU7SeCKIy+VKqBSJOASBjg9Pzq+ykdRXC1cttSurTAilO0fwNyv5UAdbilxxWZa67bzYWdfJb+91X/EVak1Owj63SH2QFqALOBQBWdJr1irfKJn+igfzNV38Rpn93aE/7z/4CgDaxRXOv4humPyxQoP90n+Zqs+s6g//AC8Mv+4AP5UAdZtY9FNRvJFH/rJY0/3nArjZLqeX/WTSN/vMTUNAHXyapYR9blD/ALgJqu+vWS/cSZ/wArmKKAN6TxFz+7tlH++5P8qrPrt6x+Uxx/7qD+tZVFAFuTUb2Xh7mQj0DYH6VWZmY5Ykn3OabRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFAH/2Q==";
    }

}
