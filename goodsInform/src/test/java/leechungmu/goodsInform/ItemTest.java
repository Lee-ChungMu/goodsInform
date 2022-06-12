package leechungmu.goodsInform;


import leechungmu.goodsInform.entity.Dto.ItemDto;
import leechungmu.goodsInform.entity.Dto.PromotionDto;
import leechungmu.goodsInform.entity.Dto.UserDto;
import leechungmu.goodsInform.entity.Item;
import leechungmu.goodsInform.entity.Promotion;
import leechungmu.goodsInform.entity.User;
import leechungmu.goodsInform.repository.ItemRepository;
import leechungmu.goodsInform.repository.PromotionRepository;
import leechungmu.goodsInform.repository.UserRepository;
import leechungmu.goodsInform.service.ItemService;
import leechungmu.goodsInform.service.PromotionService;
import leechungmu.goodsInform.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
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



    @Test
    void 사용자가구매할수있는상품정보(){
        UserDto userDto1 = UserDto.builder()
                .userId(1L)
                .userName("이수경")
                .userType("기업회원")
                .userStat(true)
                .build();
        UserDto userDto2 = UserDto.builder()
                .userId(2L)
                .userName("최강석")
                .userType("일반")
                .userStat(true)
                .build();

        Long userId1 = userService.register(userDto1);
        Long userId2 = userService.register(userDto2);
        Optional<User> user1 = userRepository.findById(userId1);
        Optional<User> user2 = userRepository.findById(userId2);
        Date start = new Date();
        Date end = new Date();
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(start);
        cal2.add(Calendar.MONTH, -1);
        cal.setTime(end);
        cal.add(Calendar.YEAR,1);
        start = cal2.getTime();
        end = cal.getTime();
        ItemDto dto1 = ItemDto.builder()
                .price(1000)
                .itemId(1L)
                .content("연필")
                .enterpriseItem(false)
                .startDate(start)
                .endDate(end)
                .stockQuantity(500)
                .build();

        ItemDto dto12 = ItemDto.builder()
                .price(1000)
                .itemId(12L)
                .content("연필2")
                .enterpriseItem(false)
                .startDate(start)
                .endDate(end)
                .stockQuantity(0)
                .build();
        ItemDto dto2 = ItemDto.builder()
                .price(2000)
                .itemId(2L)
                .content("지우개")
                .enterpriseItem(true)
                .startDate(start)
                .endDate(end)
                .stockQuantity(500)
                .build();
        ItemDto dto22 = ItemDto.builder()
                .price(2000)
                .itemId(22L)
                .content("지우개2")
                .enterpriseItem(true)
                .startDate(start)
                .endDate(end)
                .stockQuantity(50)
                .build();

        cal.add(Calendar.YEAR,-2);
        end = cal.getTime();

        ItemDto dto3 = ItemDto.builder()
                .price(4000)
                .itemId(4L)
                .content("볼펜")
                .enterpriseItem(false)
                .startDate(end)
                .endDate(start)
                .stockQuantity(500)
                .build();


        itemService.register(dto1);
        itemService.register(dto12);
        itemService.register(dto22);
        itemService.register(dto2);
        itemService.register(dto3);

        List<ItemDto> enterpriseDtoList = itemService.read(user1.get().getUserType());
        List<ItemDto> normalDtoList = itemService.read(user2.get().getUserType());

        assertEquals(normalDtoList.size(), 1);
        assertEquals(enterpriseDtoList.size(), 3);

    }
}
