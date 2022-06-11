package leechungmu.goodsInform.service;

import leechungmu.goodsInform.entity.Dto.UserDto;
import leechungmu.goodsInform.entity.User;
import leechungmu.goodsInform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository repository;

    @Override
    public Long register(UserDto dto) {
        User user = dtoToEntity(dto);
        repository.save(user);
        return user.getUserId();
    }

    @Override
    public void delete(Long userId) {
        Optional<User> result = repository.findById(userId);
        if(result.isPresent()){
            User user = result.get();
            repository.delete(user);
        }

    }


}
