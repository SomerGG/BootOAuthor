package top.somer.kernel.utils;


import top.somer.kernel.model.dto.AjaxResult;
import top.somer.kernel.model.dto.AjaxResultState;

/**
 * @author Somer
 * @date 2017/10/12
 */

public class AjaxResultUtils {

    public static <T> AjaxResult addInfoMessage(T t) {
        AjaxResult ajaxResult = new AjaxResult();
        if (null != t) {
            ajaxResult.setCode(AjaxResultState.OK);
            ajaxResult.setData(t);
            ajaxResult.setMessage("添加成功！");
        } else {
            ajaxResult.setCode(AjaxResultState.INNERERROR);
            ajaxResult.setMessage("内部错误！");
        }
        return ajaxResult;
    }

    public static <T> AjaxResult updateInfoMessage(T t) {
        AjaxResult ajaxResult = new AjaxResult();
        if (null != t) {
            ajaxResult.setCode(AjaxResultState.OK);
            ajaxResult.setData(t);
            ajaxResult.setMessage("更新成功！");
        } else {
            ajaxResult.setCode(AjaxResultState.INNERERROR);
            ajaxResult.setMessage("内部错误！");
        }
        return ajaxResult;
    }

    public static <T> AjaxResult getInfoMessage(T t) {
        AjaxResult ajaxResult = new AjaxResult();
        if (null != t) {
            ajaxResult.setCode(AjaxResultState.OK);
            ajaxResult.setMessage("获取成功");
            ajaxResult.setData(t);
        } else {
            ajaxResult.setCode(AjaxResultState.CONTENTERROR);
            ajaxResult.setMessage("未获取到相关数据！");
        }
        return ajaxResult;
    }

    public static AjaxResult delInfoMessage(boolean flag) {
        AjaxResult ajaxResult = new AjaxResult();
        if (flag) {
            ajaxResult.setCode(AjaxResultState.OK);
            ajaxResult.setMessage("删除成功！");
        } else {
            ajaxResult.setCode(AjaxResultState.CONTENTERROR);
            ajaxResult.setMessage("请求错误！");
        }
        return ajaxResult;
    }

}
