package leechungmu.goodsInform.controller;

import leechungmu.goodsInform.entity.Dto.ItemDto;
import leechungmu.goodsInform.entity.User;
import leechungmu.goodsInform.repository.ItemRepository;
import leechungmu.goodsInform.repository.UserRepository;
import leechungmu.goodsInform.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @PostMapping("")
    public ResponseEntity registerItem(@RequestBody ItemDto dto){
        Long itemId = itemService.register(dto);

        return new ResponseEntity<Long>(itemId, HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity deleteItem(@PathVariable("itemId") Long itemId){
        if(!itemService.delete(itemId)){
            return new ResponseEntity<String>("삭제할 상품이 없습니다.", HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<String>("삭제 완료", HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity readItem(@PathVariable("userId") Long userId){
        Optional<User> result = userRepository.findById(userId);
        if(!result.isPresent()){
            return new ResponseEntity<String>("가입하지 않은 아이디입니다.", HttpStatus.BAD_REQUEST);
        }
        User user = result.get();

        if(user.isUserStat() == false){
            return new ResponseEntity<String>("탈퇴한 회원은 이용할 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
        List<ItemDto> itemDtoList = itemService.read(user.getUserType());

        if(itemDtoList == null){
            return new ResponseEntity<String>("맞지않는 UserType입니다.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<List<ItemDto>>(itemDtoList, HttpStatus.OK);

    }

}
