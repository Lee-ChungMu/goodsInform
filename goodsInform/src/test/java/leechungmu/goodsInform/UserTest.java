package leechungmu.goodsInform;


import leechungmu.goodsInform.entity.Dto.UserDto;
import leechungmu.goodsInform.entity.User;
import leechungmu.goodsInform.repository.UserRepository;
import leechungmu.goodsInform.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class UserTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    void 유저등록(){
        UserDto dto = UserDto.builder()
                .userId(1L)
                .userName("이수경")
                .userType("기업회원")
                .userStat(true)
                .build();

        Long saveId = userService.register(dto);

        Optional<User> find = userRepository.findById(saveId);
        assertEquals(saveId, find.get().getUserId());
    }

    @Test
    void 유저삭제(){
        UserDto dto = UserDto.builder()
                .userId(1L)
                .userName("이수경")
                .userType("기업회원")
                .userStat(true)
                .build();

        Long saveId = userService.register(dto);

        Optional<User> find = userRepository.findById(saveId);

        userRepository.delete(find.get());
    }

}

