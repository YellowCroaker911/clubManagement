package generator.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 社团
 * @TableName club
 */
@TableName(value ="club")
@Data
public class Club implements Serializable {
    /**
     * 社团id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 社团描述
     */
    private String info;

    /**
     * 活动场地
     */
    private String address;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 成员数量
     */
    private Integer member;

    /**
     * 社长id
     */
    private Long presidentId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 0表示在申请注册状态，1表示管理员已通过，正式成为社团
     */
    private Integer isAdmitted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}