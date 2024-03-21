package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.pojo.UserActivity;
import generator.service.UserActivityService;
import generator.mapper.UserActivityMapper;
import org.springframework.stereotype.Service;

/**
* @author Pfeistorch
* @description 针对表【user_activity(社员和活动关系表)】的数据库操作Service实现
* @createDate 2024-03-21 16:47:28
*/
@Service
public class UserActivityServiceImpl extends ServiceImpl<UserActivityMapper, UserActivity>
    implements UserActivityService{

}




