package leechungmu.goodsInform.service;

import leechungmu.goodsInform.entity.Dto.PromotionDto;
import leechungmu.goodsInform.entity.Dto.PromotionResponseDto;
import leechungmu.goodsInform.entity.Item;
import leechungmu.goodsInform.entity.Promotion;
import leechungmu.goodsInform.repository.ItemRepository;
import leechungmu.goodsInform.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService{

    private final ItemRepository itemRepository;
    private final PromotionRepository repository;


    @Override
    public Long register(PromotionDto dto) {
        Optional<Item> result = itemRepository.findById(dto.getItemId());
        if(result.isPresent()) {
            Promotion promotion = dtoToEntity(dto);
            repository.save(promotion);
            return promotion.getPromotionId();
        }
        //item이 존재하지 않으면 null 리턴
        else return null;
    }

    @Override
    public boolean delete(Long promotionId) {
        Optional<Promotion> result = repository.findById(promotionId);
        if(result.isPresent()) {
            Promotion promotion = result.get();
            repository.delete(promotion);
            return true;
        }
        return false;
    }

    @Override
    public PromotionResponseDto optimalPromotion(Item item) {
        Date now = new Date();
        //List<Promotion> promotionList = repository.findAllByItem_ItemId(item.getItemId());
        List<Promotion> promotionList = repository.findAllByItem_ItemIdAndDate(item.getItemId(),now);
        PromotionResponseDto dto = null;
        if(promotionList== null) return null;
        int minPrice = Integer.MAX_VALUE;
        for(Promotion promotion : promotionList){
            int pre = item.getPrice();
            int next;
            if(promotion.isRatio() == true){
                next = pre*(100-promotion.getSale())/100;
            }
            else{
                if(pre-promotion.getSale() > 0){
                    next = pre-promotion.getSale();
                }
                else continue;
            }
            if(next < minPrice) {
                dto = PromotionResponseDto.builder()
                        .promotionId(promotion.getPromotionId())
                        .itemId(item.getItemId())
                        .prePrice(pre)
                        .nextPrice(next)
                        .sale(promotion.getSale())
                        .ratio(promotion.isRatio())
                        .startDate(promotion.getStartDate())
                        .endDate(promotion.getEndDate())
                        .build();
                minPrice= next;
            }
        }
        if(minPrice == Integer.MAX_VALUE) return null;
        else{
            return dto;
        }
    }
}
