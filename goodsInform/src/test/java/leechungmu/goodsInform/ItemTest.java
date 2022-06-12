package leechungmu.goodsInform;


import leechungmu.goodsInform.entity.Dto.ItemDto;
import leechungmu.goodsInform.entity.Item;
import leechungmu.goodsInform.repository.ItemRepository;
import leechungmu.goodsInform.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class ItemTest {
    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;
    @Test
    void 상품등록(){
        Date end = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(end);
        cal.add(Calendar.YEAR,1);
        end = cal.getTime();
        ItemDto dto = null;

        dto = ItemDto.builder()
                .price(5000)
                .itemId(1L)
                .content("연필")
                .enterpriseItem(false)
                .startDate(new Date())
                .endDate(end)
                .stockQuantity(500)
                .build();


        Long saveId =itemService.register(dto);
        Optional<Item> find = itemRepository.findById(saveId);
        assertEquals(saveId, find.get().getItemId());
    }

    @Test
    void 상품삭제(){
        Date end = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(end);
        cal.add(Calendar.YEAR,1);
        end = cal.getTime();
        ItemDto dto = null;

        dto = ItemDto.builder()
                .price(5000)
                .itemId(1L)
                .content("연필")
                .enterpriseItem(false)
                .startDate(new Date())
                .endDate(end)
                .stockQuantity(500)
                .build();


        Long saveId =itemService.register(dto);

        Optional<Item> find = itemRepository.findById(saveId);
        Item item = find.get();
        itemRepository.delete(item);
    }
}
