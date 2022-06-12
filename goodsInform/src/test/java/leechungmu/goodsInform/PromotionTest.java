package leechungmu.goodsInform;

import leechungmu.goodsInform.entity.Dto.ItemDto;
import leechungmu.goodsInform.entity.Dto.PromotionDto;
import leechungmu.goodsInform.entity.Promotion;
import leechungmu.goodsInform.repository.PromotionRepository;
import leechungmu.goodsInform.service.ItemService;
import leechungmu.goodsInform.service.PromotionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class PromotionTest {
    @Autowired
    PromotionService promotionService;
    @Autowired
    PromotionRepository promotionRepository;
    @Autowired
    ItemService itemService;
    @Test
    void 프로모션등록(){
        Date end = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(end);
        cal.add(Calendar.YEAR,1);
        end = cal.getTime();
        ItemDto itemDto = ItemDto.builder()
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

        Long itemSaveId =itemService.register(itemDto);
        Long saveId= promotionService.register(promotionDto);
        Optional<Promotion> find = promotionRepository.findById(saveId);
        assertEquals(saveId, find.get().getPromotionId());
    }
    @Test
    void 프로모션삭제(){
        Date end = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(end);
        cal.add(Calendar.YEAR,1);
        end = cal.getTime();
        ItemDto itemDto = ItemDto.builder()
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

        Long itemSaveId =itemService.register(itemDto);
        Long saveId= promotionService.register(promotionDto);
        Optional<Promotion> find = promotionRepository.findById(saveId);
        Promotion promotion = find.get();
        promotionRepository.delete(promotion);
    }
}
