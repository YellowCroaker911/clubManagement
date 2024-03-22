package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.pojo.Club;
import generator.service.ClubService;
import generator.mapper.ClubMapper;
import org.springframework.stereotype.Service;

/**
* @author Pfeistorch
* @description 针对表【club(社团)】的数据库操作Service实现
* @createDate 2024-03-21 22:49:53
*/
@Service
public class ClubServiceImpl extends ServiceImpl<ClubMapper, Club>
    implements ClubService{

}




