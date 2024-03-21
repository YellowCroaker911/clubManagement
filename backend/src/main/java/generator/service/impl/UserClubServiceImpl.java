package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.pojo.UserClub;
import generator.service.UserClubService;
import generator.mapper.UserClubMapper;
import org.springframework.stereotype.Service;

/**
* @author Pfeistorch
* @description 针对表【user_club(社员和社团关系表)】的数据库操作Service实现
* @createDate 2024-03-21 16:47:31
*/
@Service
public class UserClubServiceImpl extends ServiceImpl<UserClubMapper, UserClub>
    implements UserClubService{

}




