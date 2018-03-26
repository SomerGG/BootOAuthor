package top.somer.kernel.controller.rest.app;

import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.pager.Pager;
import org.nutz.mvc.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.somer.kernel.controller.action.AbstractController;
import top.somer.kernel.dao.base.IBaseOperate;
import top.somer.kernel.model.dto.AjaxResult;
import top.somer.kernel.model.dto.AjaxResultState;
import top.somer.kernel.model.dto.CndType;
import top.somer.kernel.model.dto.manager.AppInfoDTO;
import top.somer.kernel.model.mapped.AppInfo;
import top.somer.kernel.utils.FunctionUtils;

import java.util.Map;

/**
 * APP管理API
 *
 * @author Somer
 * @date 2018-03-16 14:20
 **/
@RestController
@RequestMapping("/api")
public class AppInfoRest extends AbstractController {

    @Autowired
    private IBaseOperate baseOperate;

    /**
     * 申请APP
     *
     * @param appInfo
     * @return
     */
    @PostMapping("/appInfo")
    @RequiresPermissions("sys:app:add")
    public AjaxResult applyAppInfo(AppInfo appInfo) {
        appInfo.setUserId(getUserId());
        return baseOperate.add(appInfo);
    }

    /**
     * 修改APP
     *
     * @param appInfo
     * @return
     */
    @PutMapping("/appInfo")
    @RequiresPermissions("sys:app:update")
    public AjaxResult updateAppInfo(AppInfo appInfo) {
        return baseOperate.update(appInfo, appInfo.getId());
    }

    /**
     * 获取当前登录用户申请的APP
     *
     * @param pager
     * @param appName
     * @param checkState
     * @return
     */
    @GetMapping("/app")
    @RequiresPermissions("sys:app:list")
    public AjaxResult getAppInfo(@Param("..") Pager pager, String appName, String checkState) {
        Map<String, CndType> params = Maps.newHashMap();
        params.put("ap.user_id", FunctionUtils.Cond("=", getUserId()));
        if (FunctionUtils.checkParam(appName)) {
            params.put("ap.app_name", FunctionUtils.Cond("like", appName));
        }
        if (FunctionUtils.checkParam(checkState)) {
            params.put("ap.check_state", FunctionUtils.Cond("=", checkState));
        }
        return baseOperate.getPager(params, "app.all", AppInfoDTO.class, pager);
    }

    /**
     * 查看APP详情
     *
     * @param id
     * @return
     */
    @GetMapping("/app/{id}")
    @RequiresPermissions("sys:app:info")
    public AjaxResult getAppInfo(@PathVariable("id") Integer id) {
        return baseOperate.get(AppInfo.class, id);
    }

    /**
     * 删除APP
     *
     * @param id
     * @return
     */
    @DeleteMapping("/app")
    @RequiresPermissions("sys:app:delete")
    public AjaxResult delAppInfo(String id) {
        return baseOperate.delete(AppInfo.class, id);
    }

    /**
     * 审核通过APP
     *
     * @param id
     * @return
     */
    @PutMapping("/passApp")
    @RequiresPermissions("sys:app:check")
    public AjaxResult setPassApp(Integer id) {
        AppInfo appInfo = (AppInfo) baseOperate.get(id, AppInfo.class);
        if (null == appInfo) {
            return new AjaxResult(AjaxResultState.CONTENTERROR, "请选择要审核的数据!");
        } else {
            appInfo.setCheckState("1");
            return baseOperate.update(appInfo, id);
        }
    }

    /**
     * 审核驳回APP
     *
     * @param id
     * @return
     */
    @PutMapping("/rejectApp")
    @RequiresPermissions("sys:app:check")
    public AjaxResult setRejectApp(Integer id) {
        AppInfo appInfo = (AppInfo) baseOperate.get(id, AppInfo.class);
        if (null == appInfo) {
            return new AjaxResult(AjaxResultState.CONTENTERROR, "请选择要审核的数据!");
        } else {
            appInfo.setCheckState("2");
            return baseOperate.update(appInfo, id);
        }
    }
}
