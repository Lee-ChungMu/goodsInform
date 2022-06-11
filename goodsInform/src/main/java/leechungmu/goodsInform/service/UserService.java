package leechungmu.goodsInform.service;

import leechungmu.goodsInform.entity.Dto.UserDto;
import leechungmu.goodsInform.entity.Type;
import leechungmu.goodsInform.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {
    Long register(UserDto dto);

    void delete(Long userId);
    default User dtoToEntity(UserDto dto){
        User user = User.builder().userId(dto.getUserId())
                .userName(dto.getUserName())
                .userType(Type.from(dto.getUserType()))
                .userStat(dto.isUserState())
                .build();

        return user;
    }
}
