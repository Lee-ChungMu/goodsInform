package leechungmu.goodsInform.controller;

import leechungmu.goodsInform.entity.Dto.UserDto;
import leechungmu.goodsInform.entity.User;
import leechungmu.goodsInform.repository.UserRepository;
import leechungmu.goodsInform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/User")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody UserDto dto){
        userService.register(dto);
        return new ResponseEntity<Long>(userService.register(dto), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestBody Long userId){
        userService.delete(userId);
        return new ResponseEntity<String>("삭제 완료", HttpStatus.OK);
    }

}
