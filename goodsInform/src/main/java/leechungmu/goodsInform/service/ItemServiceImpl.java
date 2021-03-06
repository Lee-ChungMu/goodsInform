package leechungmu.goodsInform.service;

import leechungmu.goodsInform.entity.Dto.ItemDto;
import leechungmu.goodsInform.entity.Item;
import leechungmu.goodsInform.entity.Promotion;
import leechungmu.goodsInform.repository.ItemRepository;
import leechungmu.goodsInform.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{
    private final ItemRepository repository;
    private final PromotionRepository promotionRepository;
    @Override
    public Long register(ItemDto dto) {
        Item item = dtoToEntity(dto);
        repository.save(item);
        return item.getItemId();
    }

    @Override
    public boolean delete(Long itemId) {
        Optional<Item> result = repository.findById(itemId);
        if(result.isPresent()){
            Item item = result.get();
            List<Promotion> promotionList = promotionRepository.findAllByItem_ItemId(itemId);
            for(Promotion promotion : promotionList){
                promotionRepository.delete(promotion);
            }
            repository.delete(item);

            return true;
        }
        else return false;
    }

    @Override
    public List<ItemDto> read(String userType) {
        Date now = new Date();
        if(userType.equals("일반") ){
            List<ItemDto> dtoList = new ArrayList<>();
            List<Item> itemList = repository.findAllNormal(now);
            for(Item item : itemList){
                ItemDto dto = entityToDto(item);
                dtoList.add(dto);
            }
            return dtoList;
        }
        else if(userType.equals("기업회원")){
            List<ItemDto> dtoList = new ArrayList<>();
            List<Item> itemList= repository.findAllEnterprise(now);
            for(Item item : itemList){
                ItemDto dto = entityToDto(item);
                dtoList.add(dto);
            }
            return dtoList;
       }
        return null;
    }
}
