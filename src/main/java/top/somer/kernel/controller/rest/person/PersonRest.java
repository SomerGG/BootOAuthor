package top.somer.kernel.controller.rest.person;

import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.pager.Pager;
import org.nutz.mvc.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.somer.kernel.controller.action.AbstractController;
import top.somer.kernel.dao.base.IBaseOperate;
import top.somer.kernel.model.dto.AjaxResult;
import top.somer.kernel.model.dto.CndType;
import top.somer.kernel.model.mapped.Person;
import top.somer.kernel.utils.FunctionUtils;
import top.somer.kernel.utils.RuntimeUtils;

import java.util.Map;

/**
 * 人员管理API
 *
 * @author Somer
 * @date 2018-03-16 13:23
 **/
@RestController
@RequestMapping("/api")
public class PersonRest extends AbstractController {

    @Autowired
    private IBaseOperate baseOperate;

    /**
     * 添加人员信息
     *
     * @param person
     * @return
     */
    @PostMapping("/person")
    @RequiresPermissions("sys:person:add")
    public AjaxResult addPerson(Person person) {
        person.setPersonId(RuntimeUtils.getObjectId());
        return baseOperate.add(person);
    }

    /**
     * 更新人员信息
     *
     * @param person
     * @return
     */
    @PutMapping("/person")
    @RequiresPermissions("sys:person:update")
    public AjaxResult updatePerson(Person person) {
        return baseOperate.update(person, person.getId());
    }

    /**
     * 分页获取人员信息
     *
     * @param pager
     * @param name
     * @return
     */
    @GetMapping("/person")
    @RequiresPermissions("sys:person:list")
    public AjaxResult getPerson(@Param("..") Pager pager, String name, Integer type) {
        Map<String, CndType> params = Maps.newHashMap();
        if (FunctionUtils.checkParam(name)) {
            params.put("name", FunctionUtils.Cond("like", name));
        }
        if (FunctionUtils.checkParam(String.valueOf(type))) {
            params.put("type", FunctionUtils.Cond("=", type));
        }
        return baseOperate.getPager(params, null, Person.class, pager);
    }

    /**
     * 获取人员详情
     *
     * @param id
     * @return
     */
    @GetMapping("/person/{id}")
    public AjaxResult getPerson(@PathVariable Integer id) {
        return baseOperate.get(Person.class, id);
    }

    /**
     * 删除人员信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/person")
    public AjaxResult delPerson(String id) {
        return baseOperate.delete(Person.class, id);
    }
}