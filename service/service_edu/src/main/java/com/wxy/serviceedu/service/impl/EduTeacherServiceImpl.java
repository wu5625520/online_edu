package com.wxy.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wxy.serviceedu.bean.EduTeacher;
import com.wxy.serviceedu.mapper.EduTeacherMapper;
import com.wxy.serviceedu.bean.query.TeacherQuery;
import com.wxy.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author wxy
 * @since 2021-09-21
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    @ApiOperation(value = "query one page teachers information by name. level, begin time and end time")
    public IPage<EduTeacher> pageQuery(Page<EduTeacher> page, TeacherQuery teacherQuery) {
        if(teacherQuery == null){
            return page(page, null);
        }
        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        // 类似于动态sql，拼接条件
        if (teacherQuery.getName() != null) {
            teacherQueryWrapper.like("name", teacherQuery.getName()); // 模糊查询
        }
        if (teacherQuery.getLevel() != null) {
            teacherQueryWrapper.eq("level", teacherQuery.getLevel());
        }
        if (teacherQuery.getBegin() != null) {
            teacherQueryWrapper.ge("gmt_create", teacherQuery.getBegin());
        }
        if (teacherQuery.getEnd() != null) {
            teacherQueryWrapper.le("gmt_create", teacherQuery.getEnd());
        }

        return page(page, teacherQueryWrapper);
    }
}
