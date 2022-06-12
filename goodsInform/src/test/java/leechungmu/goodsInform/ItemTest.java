package leechungmu.goodsInform;


import leechungmu.goodsInform.entity.Dto.ItemDto;
import leechungmu.goodsInform.entity.Dto.PromotionDto;
import leechungmu.goodsInform.entity.Item;
import leechungmu.goodsInform.entity.Promotion;
import leechungmu.goodsInform.repository.ItemRepository;
import leechungmu.goodsInform.repository.PromotionRepository;
import leechungmu.goodsInform.service.ItemService;
import leechungmu.goodsInform.service.PromotionService;
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
    @Autowired
    PromotionService promotionService;
    @Autowired
    PromotionRepository promotionRepository;
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
        PromotionDto promotionDto = PromotionDto.builder()
                .promotionId(1L)
                .startDate(new Date())
                .endDate(end)
                .itemId(1L)
                .ratio(false)
                .sale(3000)
                .build();

        Long saveId =itemService.register(dto);
        Long promotionSaveId= promotionService.register(promotionDto);

        itemService.delete(saveId);

        Optional<Promotion> findPromotion = promotionRepository.findById(promotionSaveId);
        assertEquals(findPromotion.isEmpty(), true);

    }
}
