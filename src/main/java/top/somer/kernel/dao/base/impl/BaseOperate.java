package top.somer.kernel.dao.base.impl;

import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.json.Json;
import org.springframework.stereotype.Service;
import top.somer.kernel.dao.base.BaseDao;
import top.somer.kernel.dao.base.IBaseOperate;
import top.somer.kernel.model.dto.*;
import top.somer.kernel.utils.AjaxResultUtils;
import top.somer.kernel.utils.CopyBeanUtils;
import top.somer.kernel.utils.FunctionUtils;
import top.somer.kernel.utils.ResultUtils;

import java.util.List;
import java.util.Map;

/**
 * 数据库基础操作实现类
 *
 * @author Somer
 * @date 2018-01-09 11:00
 **/

@Service
public class BaseOperate extends BaseDao implements IBaseOperate {

    @Override
    public AjaxResult add(Object object) {
        return AjaxResultUtils.addInfoMessage(null != dao.insert(object) ? object : null);
    }

    @Override
    public AjaxResult update(Object object, String id) {
        Object old = dao.fetch(object.getClass(), Cnd.where("id", "=", id));
        if (null != old) {
            CopyBeanUtils.copyProperties(object, old);
            object = Json.fromJson(object.getClass(), Json.toJson(old));
            if (dao.updateIgnoreNull(old) > 0) {
                return AjaxResultUtils.updateInfoMessage(object);
            } else {
                return AjaxResultUtils.updateInfoMessage(old);
            }
        } else {
            return AjaxResultUtils.updateInfoMessage(null);
        }
    }

    @Override
    public AjaxResult update(Object object, Integer id) {
        Object old = dao.fetch(object.getClass(), Cnd.where("id", "=", id));
        if (null != old) {
            CopyBeanUtils.copyProperties(object, old);
            object = Json.fromJson(object.getClass(), Json.toJson(old));
            if (dao.updateIgnoreNull(old) > 0) {
                return AjaxResultUtils.updateInfoMessage(object);
            } else {
                return AjaxResultUtils.updateInfoMessage(old);
            }
        } else {
            return AjaxResultUtils.updateInfoMessage(null);
        }
    }

    @Override
    public AjaxResult get(Class<?> clazz, String id) {
        return AjaxResultUtils.getInfoMessage(dao.fetch(clazz, Cnd.where("id", "=", id)));
    }

    @Override
    public AjaxResult get(Class<?> clazz, Integer id) {
        return AjaxResultUtils.getInfoMessage(dao.fetch(clazz, Cnd.where("id", "=", id)));
    }

    @Override
    public AjaxResult getPager(Map<String, CndType> params, String sql, Class<?> clazz, Pager pager) {
        ResultData<Object> resultData = new ResultData<>();
        Cnd cnd = null;
        if (null != params) {
            cnd = FunctionUtils.MapToCnd(params);
        }
        Object list = null;
        Integer totalNumber = null;
        if (null == sql || "".equals(sql)) {
            list = dao.query(clazz, cnd, pager);
            totalNumber = dao.count(clazz, cnd);
        } else {
            list = ResultUtils.getSqlList(clazz, dao, sql, cnd, pager);
            String sqlSize = sql.substring(0, sql.indexOf(".")).concat(".totalSize");
            totalNumber = ResultUtils.getSql(TotalCount.class, dao, sqlSize, cnd).getTotalNumber();
        }
        resultData.setRow((List<Object>) list);
        resultData.setTotal(ResultUtils.getResultTotal(pager.getPageNumber(), pager.getPageSize(), totalNumber));
        return AjaxResultUtils.getInfoMessage(resultData);
    }

    @Override
    public AjaxResult delete(Class<?> clazz, String id) {
        int state = dao.clear(clazz, Cnd.where("id", "in", id));
        return AjaxResultUtils.delInfoMessage(state > 0 ? true : false);
    }

    @Override
    public AjaxResult get(Class<?> clazz, String sql, Map<String, CndType> params) {
        Cnd cnd = FunctionUtils.MapToCnd(params);
        return AjaxResultUtils.getInfoMessage(ResultUtils.getSql(clazz, dao, sql, cnd));
    }

    @Override
    public AjaxResult getNoPager(Map<String, CndType> params, String sql, Class<?> clazz) {
        Cnd cnd = null;
        if (null != params) {
            cnd = FunctionUtils.MapToCnd(params);
        }
        Object list = null;
        if (null == sql || "".equals(sql)) {
            list = dao.query(clazz, cnd);
        } else {
            list = ResultUtils.getSqlListNoPager(clazz, dao, sql, cnd);
        }
        if (null == list) {
            return new AjaxResult(AjaxResultState.CONTENTERROR, "暂无数据！");
        } else {
            return new AjaxResult(AjaxResultState.OK, "获取成功！", list);
        }
    }

    @Override
    public Object getOneByColumn(Class<?> clazz, Cnd cnd) {
        return ResultUtils.getByOneColumn(clazz, dao, cnd);
    }

    @Override
    public Object get(Integer id, Class<?> clazz) {
        return dao.fetch(clazz, Cnd.where("id", "=", id));
    }

    @Override
    public Object get(String id, Class<?> clazz) {
        return dao.fetch(clazz, Cnd.where("id", "=", id));
    }

    @Override
    public Boolean judgeWhetherExist(Class<?> clazz, String column, String value) {
        int size = dao.query(clazz, Cnd.where(column, "=", value)).size();
        return size > 0 ? true : false;
    }
}
