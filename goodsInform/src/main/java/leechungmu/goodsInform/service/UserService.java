package leechungmu.goodsInform.service;

import leechungmu.goodsInform.entity.Dto.UserDto;
import leechungmu.goodsInform.entity.User;


public interface UserService {
    Long register(UserDto dto);

    boolean delete(Long userId);

    default User dtoToEntity(UserDto dto){
        User user = User.builder().userId(dto.getUserId())
                .userName(dto.getUserName())
                .userType(dto.getUserType())
                .userStat(dto.isUserStat())
                .build();

        return user;
    }
}
