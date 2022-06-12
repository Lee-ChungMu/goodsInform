package leechungmu.goodsInform;

import leechungmu.goodsInform.entity.Dto.ItemDto;
import leechungmu.goodsInform.entity.Dto.PromotionDto;
import leechungmu.goodsInform.entity.Dto.PromotionResponseDto;
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
    @Autowired
    ItemRepository itemRepository;
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


    @Test
    void 상품에존재하는프로모션정보반환(){
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
        PromotionDto promotionDto1 = PromotionDto.builder()
                .promotionId(1L)
                .startDate(new Date())
                .endDate(end)
                .itemId(1L)
                .ratio(true)
                .sale(60)
                .build();
        PromotionDto promotionDto2 = PromotionDto.builder()
                .promotionId(2L)
                .startDate(new Date())
                .endDate(end)
                .itemId(1L)
                .ratio(false)
                .sale(3500)
                .build();
        cal.add(Calendar.YEAR,-2);
        end = cal.getTime();
        PromotionDto promotionDto3 = PromotionDto.builder()
                .promotionId(3L)
                .startDate(end)
                .endDate(new Date())
                .itemId(1L)
                .ratio(false)
                .sale(4900)
                .build();
        Long itemSaveId =itemService.register(itemDto);
        Optional<Item> item = itemRepository.findById(itemSaveId);
        Long saveId1= promotionService.register(promotionDto1);
        Long saveId2= promotionService.register(promotionDto2);
        Long saveId3= promotionService.register(promotionDto3);

        PromotionResponseDto dto = promotionService.optimalPromotion(item.get());
        assertEquals(dto.getPromotionId(),promotionDto2.getPromotionId());

    }
}
