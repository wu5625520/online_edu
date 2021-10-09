package com.wxy.serviceedu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxy.serviceedu.bean.EduTeacher;
import com.wxy.serviceedu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.wxy.serviceedu.bean.query.TeacherQuery;
import com.wxy.utils.*;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author wxy
 * @since 2021-09-21
 */
@RestController
@RequestMapping("/serviceedu/edu-teacher")
@Api(tags = "讲师管理模块")
public class EduTeacherController {
    @Autowired
    EduTeacherService eduTeacherService;

    @ApiOperation(value = "add a teacher's information")
    @PostMapping
    public StandardResult addTeacher(EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return StandardResult.ok().message("add the teacher's information success");
        } else {
            return StandardResult.error().message("add the teacher's information failed");
        }
    }

    @ApiOperation(value = "根据id逻辑删除讲师方法")
    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public StandardResult deleteTeacherById(
            @ApiParam(name = "id", value = "讲师id", required = true)
            @PathVariable("id") String id){
        boolean falg = eduTeacherService.removeById(id);
        if (falg) {
            return StandardResult.ok();
        } else {
            return StandardResult.error();
        }
    }

    @ApiOperation(value = "update teacher's information by id")
    @PutMapping(value = "/{id}")
    public StandardResult updateById(@PathVariable String id,
                                     EduTeacher eduTeacher) {
        eduTeacher.setId(id);
        boolean update = eduTeacherService.updateById(eduTeacher);
        if (update) {
            return StandardResult.ok().message("update information success");
        } else {
            return StandardResult.error().message("update information failed");
        }
    }

    @ApiOperation(value = "get a teacher's information by id")
    @GetMapping(value = "/{id}")
    public StandardResult getById(@PathVariable Integer id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return StandardResult.ok().data("item", eduTeacher);
    }
    @ApiOperation(value = "查询所有讲师")
    @RequestMapping(method = RequestMethod.GET)
    public StandardResult SelectAll(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return StandardResult.ok().data("items", list);
    }

    @ApiOperation(value = "get teacher list by page number and limit number per page")
    @GetMapping(value = "/{pageNum}/{limit}")
    public StandardResult pageList(@ApiParam(value = "the current page number",required = true)
                                   @PathVariable Integer pageNum,
                                   @ApiParam(value = "the teacher number of per page", required = true)
                                   @PathVariable(value = "limit") Integer limit){
        Page<EduTeacher> eduTeacherPage = new Page<>(pageNum, limit);
        IPage<EduTeacher> eduTeacherIPage = eduTeacherService.page(eduTeacherPage, null);
        List<EduTeacher> records = eduTeacherIPage.getRecords();
        long totalPage = eduTeacherIPage.getPages();
        long total = eduTeacherIPage.getTotal();
        return StandardResult.ok().data("totalPage", totalPage).data("totalItem", total).data("items", records);
    }

    @GetMapping(value = "/teacherQuery/{pageNum}/{limit}")
    @ApiOperation(value = "query one page teachers by teacherQuery")
    public StandardResult pageListByTeacherQuery(@PathVariable Integer pageNum,
                                                 @ApiParam(value = "the teacher number of per page", required = true)
                                                 @PathVariable Integer limit,
                                                 TeacherQuery teacherQuery) {
        Page<EduTeacher> eduTeacherPage = new Page<>(pageNum, limit);
        IPage<EduTeacher> eduTeacherIPage = eduTeacherService.pageQuery(eduTeacherPage, teacherQuery);
        return StandardResult.ok().data("totalItems", eduTeacherIPage.getTotal()).data("totalPage", eduTeacherIPage.getPages())
                .data("items", eduTeacherIPage.getRecords());
    }





}

