package leechungmu.goodsInform.service;

import leechungmu.goodsInform.entity.Dto.PromotionDto;
import leechungmu.goodsInform.entity.Item;
import leechungmu.goodsInform.entity.Promotion;
import leechungmu.goodsInform.repository.ItemRepository;
import leechungmu.goodsInform.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
