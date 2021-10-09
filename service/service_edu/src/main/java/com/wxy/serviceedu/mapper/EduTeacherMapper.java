package com.wxy.serviceedu.mapper;

import com.wxy.serviceedu.bean.EduTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author wxy
 * @since 2021-09-21
 */
@Repository  // 搞不懂为啥要加这个
public interface EduTeacherMapper extends BaseMapper<EduTeacher> {

}
