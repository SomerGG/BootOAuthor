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
import top.somer.kernel.model.mapped.Organization;
import top.somer.kernel.utils.FunctionUtils;
import top.somer.kernel.utils.RuntimeUtils;

import java.util.Map;

/**
 * 机构管理
 *
 * @author Somer
 * @date 2018-03-16 13:53
 **/
@RestController
@RequestMapping("/api")
public class OrganizationRest extends AbstractController {

    @Autowired
    private IBaseOperate baseOperate;

    /**
     * 添加组织机构信息
     *
     * @param organization
     * @return
     */
    @PostMapping("/organization")
    @RequiresPermissions("sys:org:add")
    public AjaxResult addOrganization(Organization organization) {
        organization.setId(RuntimeUtils.getObjectId());
        return baseOperate.add(organization);
    }

    /**
     * 更新组织机构信息
     *
     * @param organization
     * @return
     */
    @PutMapping("/organization")
    @RequiresPermissions("sys:org:update")
    public AjaxResult updateOrganization(Organization organization) {
        return baseOperate.update(organization, organization.getId());
    }

    /**
     * 分页获取全部组织机构信息
     *
     * @param pager
     * @param name
     * @param orgType
     * @return
     */
    @GetMapping("/organization")
    @RequiresPermissions("sys:org:list")
    public AjaxResult getOrganization(Pager pager, String name, String orgType) {
        Map<String, CndType> params = Maps.newHashMap();
        if (FunctionUtils.checkParam(name)) {
            params.put("name", FunctionUtils.Cond("like", name));
        }
        if (FunctionUtils.checkParam(orgType)) {
            params.put("org_type", FunctionUtils.Cond("=", orgType));
        }
        return baseOperate.getPager(params, null, Organization.class, pager);
    }

    /**
     * 查看组织机构详情
     *
     * @param id
     * @return
     */
    @GetMapping("/organization/{id}")
    @RequiresPermissions("sys:org:info")
    public AjaxResult getOrganization(@PathVariable String id) {
        return baseOperate.get(Organization.class, id);
    }

    /**
     * 删除组织机构信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/organization")
    @RequiresPermissions("sys:org:delete")
    public AjaxResult delOrganization(String id) {
        return baseOperate.delete(Organization.class, id);
    }
}