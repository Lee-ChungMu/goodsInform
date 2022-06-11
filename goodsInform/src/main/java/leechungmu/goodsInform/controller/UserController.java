package leechungmu.goodsInform.controller;

import leechungmu.goodsInform.entity.Dto.UserDto;
import leechungmu.goodsInform.repository.UserRepository;
import leechungmu.goodsInform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("")
    public ResponseEntity registerUser(@RequestBody UserDto dto){
        if(userService.register(dto)==null){
            return new ResponseEntity<String>("중복된 아이디가 있습니다.", HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<Long>(userService.register(dto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") Long userId){
        if(!userService.delete(userId)){
            return new ResponseEntity<String>("삭제할 아이디가 없습니다.", HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<String>("삭제 완료", HttpStatus.OK);
    }

}
