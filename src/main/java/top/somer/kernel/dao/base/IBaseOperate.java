package top.somer.kernel.dao.base;

import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import top.somer.kernel.model.dto.AjaxResult;
import top.somer.kernel.model.dto.CndType;

import java.util.Map;

/**
 * 数据库基础操作
 *
 * @author Somer
 * @date 2018-01-09 10:53
 **/
public interface IBaseOperate {

    /**
     * 添加
     *
     * @param object
     * @return
     */
    AjaxResult add(Object object);

    /**
     * 更新
     *
     * @param object
     * @return
     */
    AjaxResult update(Object object);

    /**
     * 获取详情
     *
     * @param clazz
     * @param id
     * @return
     */
    AjaxResult get(Class<?> clazz, String id);

    /**
     * 获取详情
     *
     * @param clazz
     * @param id
     * @return
     */
    AjaxResult get(Class<?> clazz, Integer id);

    /**
     * 分页获取
     *
     * @param params
     * @param sql
     * @param clazz
     * @param pager
     * @return
     */
    public AjaxResult getPager(Map<String, CndType> params, String sql, Class<?> clazz, Pager pager);

    /**
     * 删除数据
     *
     * @param clazz
     * @param id
     * @return
     */
    AjaxResult delete(Class<?> clazz, String id);

    /**
     * 自定义SQL返回单条记录
     *
     * @param clazz
     * @param sql
     * @param params
     * @return
     */
    AjaxResult get(Class<?> clazz, String sql, Map<String, CndType> params);

    /**
     * 无分页获取
     *
     * @param params
     * @param sql
     * @param clazz
     * @return
     */
    AjaxResult getNoPager(Map<String, CndType> params, String sql, Class<?> clazz);

    /**
     * 根据条件返回唯一对象
     *
     * @param clazz
     * @param cnd
     * @return
     */
    Object getOneByColumn(Class<?> clazz, Cnd cnd);

    /**
     * 根据主键返回单个对象
     *
     * @param id
     * @param clazz
     * @return
     */
    Object get(Integer id, Class<?> clazz);

    /**
     * 根据主键返回单个对象
     *
     * @param id
     * @param clazz
     * @return
     */
    Object get(String id, Class<?> clazz);

    /**
     * 根据唯一标识判断某对象是否已经存在
     *
     * @param clazz
     * @param column
     * @param value
     * @return
     */
    Boolean judgeWhetherExist(Class<?> clazz, String column, String value);
}
