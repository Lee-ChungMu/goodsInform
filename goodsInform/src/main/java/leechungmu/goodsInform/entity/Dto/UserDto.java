package leechungmu.goodsInform.entity.Dto;

import lombok.Builder;

@Builder
public class UserDto {
    private Long userId;
    private String userName;
    private String userType;
    private boolean userState;

}
