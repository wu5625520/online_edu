package com.wxy.serviceedu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wxy.serviceedu.bean.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wxy.serviceedu.bean.query.TeacherQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author wxy
 * @since 2021-09-21
 */
public interface EduTeacherService extends IService<EduTeacher> {
    IPage<EduTeacher> pageQuery(Page<EduTeacher> page, TeacherQuery teacherQuery);
}
