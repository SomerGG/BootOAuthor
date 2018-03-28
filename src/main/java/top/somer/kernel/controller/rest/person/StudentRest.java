package top.somer.kernel.controller.rest.person;

import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.pager.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.somer.kernel.controller.action.AbstractController;
import top.somer.kernel.dao.base.IBaseOperate;
import top.somer.kernel.model.dto.AjaxResult;
import top.somer.kernel.model.dto.CndType;
import top.somer.kernel.model.dto.person.Student;
import top.somer.kernel.model.mapped.Clazz;
import top.somer.kernel.model.mapped.SchoolRoll;
import top.somer.kernel.utils.FunctionUtils;

import java.util.Map;

/**
 * 学生信息管理
 *
 * @author Somer
 * @date 2018-03-20 12:25
 **/
@RestController
@RequestMapping("/api")
public class StudentRest extends AbstractController {

    @Autowired
    private IBaseOperate baseOperate;

    /**
     * 添加学生学籍信息
     *
     * @param schoolRoll
     * @return
     */
    @PostMapping("/schoolRole")
    @RequiresPermissions("sys:schoolRoll:add")
    public AjaxResult addSchoolRoll(SchoolRoll schoolRoll) {
        Clazz clazz = (Clazz) baseOperate.get(schoolRoll.getClazz(), Clazz.class);
        schoolRoll.setSchoolName(clazz.getSchoolId());// 设置所在学校名称
        schoolRoll.setClazz(clazz.getClazz());// 设置所在班级名称
        return baseOperate.add(schoolRoll);
    }

    /**
     * 更新学生学籍信息
     *
     * @param schoolRoll
     * @return
     */
    @PutMapping("/schoolRole")
    @RequiresPermissions("sys:schoolRoll:update")
    public AjaxResult updateSchoolRoll(SchoolRoll schoolRoll) {
        Clazz clazz = (Clazz) baseOperate.get(schoolRoll.getClazz(), Clazz.class);
        schoolRoll.setSchoolName(clazz.getSchoolId());// 设置所在学校名称
        schoolRoll.setClazz(clazz.getClazz());// 设置所在班级名称
        return baseOperate.update(schoolRoll);
    }

    /**
     * 获取学籍信息
     *
     * @param pager
     * @param name
     * @param school
     * @return
     */
    @GetMapping("/schoolRoll")
    @RequiresPermissions("sys:schoolRoll:list")
    public AjaxResult getSchoolRoll(Pager pager, String name, String school, String gender) {
        Map<String, CndType> params = Maps.newHashMap();
        if (FunctionUtils.checkParam(name)) {
            params.put("st.name", FunctionUtils.Cond("like", name));
        }
        if (FunctionUtils.checkParam(school)) {
            params.put("sr.school", FunctionUtils.Cond("=", name));
        }
        if (FunctionUtils.checkParam(gender)) {
            params.put("st.gender", FunctionUtils.Cond("=", gender));
        }
        return baseOperate.getPager(params, "studentInfo.get", Student.class, pager);
    }

    /**
     * 查看学籍详情
     *
     * @param id
     * @return
     */
    @GetMapping("/schoolRoll/{id}")
    @RequiresPermissions("sys:schoolRoll:info")
    public AjaxResult getSchoolRoll(@PathVariable String id) {
        return baseOperate.get(SchoolRoll.class, id);
    }

    /**
     * 删除学籍信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/schoolRoll")
    @RequiresPermissions("sys:schoolRoll:delete")
    public AjaxResult delSchoolRoll(String id) {
        return baseOperate.delete(SchoolRoll.class, id);
    }

    /**
     * 获取学生信息
     *
     * @param pager
     * @param clazz
     * @param gender
     * @return
     */
    @GetMapping("/getStudent")
    @RequiresPermissions("sys:student:list")
    public AjaxResult getStudent(Pager pager, String clazz, String gender) {
        Map<String, CndType> params = Maps.newHashMap();
        if (FunctionUtils.checkParam(clazz)) {
            params.put("sr.clazz", FunctionUtils.Cond("=", clazz));
        }
        if (FunctionUtils.checkParam(gender)) {
            params.put("st.gender", FunctionUtils.Cond("=", gender));
        }
        return baseOperate.getPager(params, "studentInfo.get", Student.class, pager);
    }
}
