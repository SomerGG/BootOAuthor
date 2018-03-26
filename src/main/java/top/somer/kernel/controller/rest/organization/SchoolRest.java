package top.somer.kernel.controller.rest.organization;

import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.pager.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.somer.kernel.controller.action.AbstractController;
import top.somer.kernel.dao.base.IBaseOperate;
import top.somer.kernel.model.dto.AjaxResult;
import top.somer.kernel.model.dto.CndType;
import top.somer.kernel.model.mapped.Clazz;
import top.somer.kernel.model.mapped.School;
import top.somer.kernel.utils.FunctionUtils;
import top.somer.kernel.utils.RuntimeUtils;

import java.util.Map;

/**
 * 学校信息管理
 *
 * @author Somer
 * @date 2018-03-16 14:10
 **/
@RestController
@RequestMapping("/api")
public class SchoolRest extends AbstractController {

    @Autowired
    private IBaseOperate baseOperate;

    /**
     * 添加学校信息
     *
     * @param school
     * @return
     */
    @PostMapping("/school")
    @RequiresPermissions("sys:school:add")
    public AjaxResult addSchool(School school) {
        school.setId(RuntimeUtils.getObjectId());
        return baseOperate.add(school);
    }

    /**
     * 更新学校信息
     *
     * @param school
     * @return
     */
    @PutMapping("/school")
    @RequiresPermissions("sys:school:update")
    public AjaxResult updateSchool(School school) {
        return baseOperate.update(school, school.getId());
    }

    /**
     * 分页获取学校信息
     *
     * @param pager
     * @param name
     * @return
     */
    @GetMapping("/school")
    @RequiresPermissions("sys:school:list")
    public AjaxResult getSchool(Pager pager, String name) {
        Map<String, CndType> params = Maps.newHashMap();
        //TODO 获取所属机构下的学校
//        params.put("belong_id", FunctionUtils.Cond("=",));
        if (FunctionUtils.checkParam(name)) {
            params.put("name", FunctionUtils.Cond("like", name));
        }
        return baseOperate.getPager(params, null, School.class, pager);
    }

    /**
     * 获取学校信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/school/{id}")
    @RequiresPermissions("sys:school:info")
    public AjaxResult getSchool(@PathVariable String id) {
        return baseOperate.get(School.class, id);
    }

    /**
     * 删除学校信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/school")
    @RequiresPermissions("sys:school:delete")
    public AjaxResult delSchool(String id) {
        return baseOperate.delete(School.class, id);
    }

    /**
     * 添加班级信息
     *
     * @param clazz
     * @return
     */
    @PostMapping("/clazz")
    @RequiresPermissions("sys:clazz:add")
    public AjaxResult addClazz(Clazz clazz) {
        return baseOperate.add(clazz);
    }

    /**
     * 更新班级信息
     *
     * @param clazz
     * @return
     */
    @PutMapping("/clazz")
    @RequiresPermissions("sys:clazz:update")
    public AjaxResult updateClazz(Clazz clazz) {
        return baseOperate.update(clazz, clazz.getId());
    }

    /**
     * 分页获取班级信息
     *
     * @param pager
     * @param schoolId
     * @return
     */
    @GetMapping("/clazz")
    @RequiresPermissions("sys:clazz:list")
    public AjaxResult getClazz(Pager pager, String schoolId) {
        Map<String, CndType> params = Maps.newHashMap();
        params.put("school_id", FunctionUtils.Cond("=", schoolId));
        return baseOperate.getPager(params, null, Clazz.class, pager);
    }

    /**
     * 查看班级信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/clazz/{id}")
    @RequiresPermissions("sys:clazz:info")
    public AjaxResult getClazz(@PathVariable Integer id) {
        return baseOperate.get(Clazz.class, id);
    }

    /**
     * 删除班级信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/clazz")
    @RequiresPermissions("sys:clazz:delete")
    public AjaxResult delClazz(String id) {
        return baseOperate.delete(Clazz.class, id);
    }
}