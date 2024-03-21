package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.pojo.Activity;
import generator.service.ActivityService;
import generator.mapper.ActivityMapper;
import org.springframework.stereotype.Service;

/**
* @author Pfeistorch
* @description 针对表【activity(活动)】的数据库操作Service实现
* @createDate 2024-03-21 16:47:11
*/
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>
    implements ActivityService{

}




