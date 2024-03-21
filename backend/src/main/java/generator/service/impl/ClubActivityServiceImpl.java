package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.pojo.ClubActivity;
import generator.service.ClubActivityService;
import generator.mapper.ClubActivityMapper;
import org.springframework.stereotype.Service;

/**
* @author Pfeistorch
* @description 针对表【club_activity(社团和活动关系表)】的数据库操作Service实现
* @createDate 2024-03-21 16:47:25
*/
@Service
public class ClubActivityServiceImpl extends ServiceImpl<ClubActivityMapper, ClubActivity>
    implements ClubActivityService{

}




