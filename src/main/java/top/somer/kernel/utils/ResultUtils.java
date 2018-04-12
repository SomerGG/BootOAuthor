package top.somer.kernel.utils;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class ResultUtils {

    /**
     * 自定义Sql查询返回多条记录
     *
     * @param clazz
     * @param dao
     * @param sqlStr
     * @param cnd
     * @param pager
     * @return
     */
    public static <T> List<T> getSqlList(Class<T> clazz, Dao dao, String sqlStr, Cnd cnd, Pager pager) {
        Sql sql = dao.sqls().create(sqlStr);
        sql.params().set("pageNumber", ((pager.getPageNumber()) - 1) * pager.getPageSize());
        sql.params().set("pageSize", pager.getPageSize());
        sql.setCondition(cnd);
        sql.setCallback(Sqls.callback.entities());
        Entity<T> entity = dao.getEntity(clazz);
        sql.setEntity(entity);
        dao.execute(sql);
        List<T> lists = sql.getList(clazz);
        return null != lists && lists.size() > 0 ? lists : null;
    }

    /**
     * 无分页返回
     *
     * @param clazz
     * @param dao
     * @param sqlStr
     * @param cnd
     * @return
     */
    public static <T> List<T> getSqlListNoPager(Class<T> clazz, Dao dao, String sqlStr, Cnd cnd) {
        Sql sql = dao.sqls().create(sqlStr);
        sql.setCondition(cnd);
        sql.setCallback(Sqls.callback.entities());
        Entity<T> entity = dao.getEntity(clazz);
        sql.setEntity(entity);
        dao.execute(sql);
        List<T> lists = sql.getList(clazz);
        return null != lists && lists.size() > 0 ? lists : null;
    }

    /**
     * 获取数据记录条数
     *
     * @param clazz
     * @param dao
     * @param sqlStr
     * @param cnd
     * @return
     */
    public static <T> Integer getSqlListSize(Class<T> clazz, Dao dao, String sqlStr, Cnd cnd) {
        Sql sql = dao.sqls().create(sqlStr);
        sql.setCondition(cnd);
        sql.setCallback(Sqls.callback.entities());
        Entity<T> entity = dao.getEntity(clazz);
        sql.setEntity(entity);
        dao.execute(sql);
        List<T> lists = sql.getList(clazz);
        return null != lists ? lists.size() : null;
    }


    /**
     * 总记录数统计
     *
     * @param clazz
     * @param dao
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> Integer getSqlListSize(Class<T> clazz, Dao dao, Sql sql, Map<String, String> params) {
        for (Map.Entry<String, String> param : params.entrySet()) {
            sql.setParam(param.getKey(), param.getValue());
        }
        sql.setCallback(Sqls.callback.entities());
        Entity<T> entity = dao.getEntity(clazz);
        sql.setEntity(entity);
        dao.execute(sql);
        List<T> lists = sql.getList(clazz);
        return lists == null ? null : lists.size();
    }

    /**
     * 自定义Sql查询返回单条记录
     *
     * @param clazz
     * @param dao
     * @param sqlStr
     * @param cnd
     * @return
     */
    public static <T> T getSql(Class<T> clazz, Dao dao, String sqlStr, Cnd cnd) {
        Sql sql = dao.sqls().create(sqlStr);
        sql.setCondition(cnd);
        sql.setCallback(Sqls.callback.entities());
        Entity<T> entity = dao.getEntity(clazz);
        sql.setEntity(entity);
        dao.execute(sql);
        List<T> lists = sql.getList(clazz);
        return null != lists && lists.size() > 0 ? lists.get(0) : null;
    }

    /**
     * 根据单个字段返回单个对象
     *
     * @param clazz
     * @param dao
     * @param cnd
     * @param <T>
     * @return
     */
    public static <T> T getByOneColumn(Class<T> clazz, Dao dao, Cnd cnd) {
        List<T> list = dao.query(clazz, cnd);
        return null != list && list.size() > 0 ? list.get(0) : null;
    }

    /**
     * 自定义Sql查询返回单条记录,可传单个参数
     *
     * @param clazz
     * @param dao
     * @param sqlStr
     * @param cnd
     * @return
     */
    public static <T> T getSql(Class<T> clazz, Dao dao, String sqlStr, Cnd cnd, Map<String, String> params) {
        Sql sql = dao.sqls().create(sqlStr);
        sql.setCondition(cnd);
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (param.getKey() != "" && param.getValue() != null) {
                sql.setParam(param.getKey(), param.getValue());
            }
        }
        sql.setCallback(Sqls.callback.entities());
        Entity<T> entity = dao.getEntity(clazz);
        sql.setEntity(entity);
        dao.execute(sql);
        List<T> lists = sql.getList(clazz);
        return null != lists && lists.size() > 0 ? lists.get(0) : null;
    }


    /**
     * 查询多条记录，可传单个参数
     *
     * @param clazz
     * @param dao
     * @param sqlStr
     * @param cnd
     * @param pager
     * @param condition
     * @param <T>
     * @return
     */
    public static <T> List<T> getSqlList(Class<T> clazz, Dao dao, String sqlStr, Cnd cnd, Pager pager, Map<String, String> condition) {
        Sql sql = dao.sqls().create(sqlStr);
        if (cnd != null) {
            sql.setCondition(cnd);
        }
        for (Map.Entry<String, String> params : condition.entrySet()) {
            if (params.getKey() != "" && params.getValue() != null) {
                sql.setParam(params.getKey(), params.getValue());
            }
        }
        sql.params().set("pageNumber", ((pager.getPageNumber()) - 1) * pager.getPageSize());
        sql.params().set("pageSize", pager.getPageSize());
        sql.setCondition(cnd);
        sql.setCallback(Sqls.callback.entities());
        Entity<T> entity = dao.getEntity(clazz);
        sql.setEntity(entity);
        dao.execute(sql);
        List<T> lists = sql.getList(clazz);
        return null != lists && lists.size() > 0 ? lists : null;
    }

    /**
     * 无分页返回多条记录，可传单个参数
     *
     * @param clazz
     * @param dao
     * @param sqlLocation
     * @param cnd
     * @param condition
     * @param <T>
     * @return
     */
    public static <T> List<T> getSqlList(Class<T> clazz, Dao dao, String sqlLocation, Cnd cnd, Map<String, String>
            condition) {
        Sql sql = dao.sqls().create(sqlLocation);
        if (cnd != null) {
            sql.setCondition(cnd);
        }
        for (Map.Entry<String, String> params : condition.entrySet()) {
            if (params.getKey() != "" && params.getValue() != null) {
                sql.setParam(params.getKey(), params.getValue());
            }
        }
        sql.setCondition(cnd);
        sql.setCallback(Sqls.callback.entities());
        Entity<T> entity = dao.getEntity(clazz);
        sql.setEntity(entity);
        dao.execute(sql);
        List<T> lists = sql.getList(clazz);
        return null != lists && lists.size() > 0 ? lists : null;
    }

    /**
     * 获取可传单个参数数据记录条数
     *
     * @param clazz
     * @param dao
     * @param sqlStr
     * @param cnd
     * @param condition
     * @return
     */
    public static <T> Integer getSqlListSize(Class<T> clazz, Dao dao, String sqlStr, Cnd cnd, Map<String, String>
            condition) {
        Sql sql = dao.sqls().create(sqlStr);
        if (cnd != null) {
            sql.setCondition(cnd);
        }
        for (Map.Entry<String, String> pramas : condition.entrySet()) {
            if (pramas.getKey() != "" && pramas.getValue() != null) {
                sql.setParam(pramas.getKey(), pramas.getValue());
            }
        }
        sql.setCallback(Sqls.callback.entities());
        Entity<T> entity = dao.getEntity(clazz);
        sql.setEntity(entity);
        dao.execute(sql);
        List<T> lists = sql.getList(clazz);
        return null != lists ? lists.size() : null;
    }

    /**
     * 自定义Sql查询返回单条记录,可传单个参数,多条件占位符,无分页
     *
     * @param clazz
     * @param dao
     * @return
     */
    public static <T> List<T> getSqlList(Class<T> clazz, Dao dao, Sql sql, Map<String, String> params) {
        for (Map.Entry<String, String> param : params.entrySet()) {
            sql.setParam(param.getKey(), param.getValue());
        }
        sql.setCallback(Sqls.callback.entities());
        Entity<T> entity = dao.getEntity(clazz);
        sql.setEntity(entity);
        dao.execute(sql);
        List<T> lists = sql.getList(clazz);
        return null != lists && lists.size() > 0 ? lists : null;
    }

    /**
     * 自定义Sql查询返回单条记录,可传单个参数,多条件占位符,带分页
     *
     * @param clazz
     * @param dao
     * @return
     */
    public static <T> List<T> getSqlList(Class<T> clazz, Pager pager, Dao dao, Sql sql, Map<String, String> params) {

        for (Map.Entry<String, String> param : params.entrySet()) {
            sql.setParam(param.getKey(), param.getValue());
        }
        sql.params().set("pageNumber", ((pager.getPageNumber()) - 1) * pager.getPageSize());
        sql.params().set("pageSize", pager.getPageSize());
        sql.setCallback(Sqls.callback.entities());
        Entity<T> entity = dao.getEntity(clazz);
        sql.setEntity(entity);
        dao.execute(sql);
        List<T> lists = sql.getList(clazz);
        return null != lists && lists.size() > 0 ? lists : null;
    }

    /**
     * 自定义Sql查询返回单条记录,可传单个参数,多条件占位符
     *
     * @param clazz
     * @param dao
     * @return
     */
    public static <T> T getSqlMultiple(Class<T> clazz, Dao dao, Sql sql, Map<String, String> condition) {
        for (Map.Entry<String, String> param : condition.entrySet()) {
            sql.setParam(param.getKey(), param.getValue());
        }
        sql.setCallback(Sqls.callback.entities());
        Entity<T> entity = dao.getEntity(clazz);
        sql.setEntity(entity);
        dao.execute(sql);
        List<T> lists = sql.getList(clazz);
        return null != lists && lists.size() > 0 ? lists.get(0) : null;
    }


    /**
     * 判断百分比
     *
     * @param s
     * @return
     */
    public static String getResultPercentage(String s) {
        if (null != s && !"".equals(s)) {
            if (s.indexOf(".") != -1) {
                return s.substring(0, s.indexOf(".")).concat("%");
            } else {
                return s.concat("%");
            }
        } else {
            return "0%";
        }
    }

    public static Double getKeepDecimal(Double d) {
        DecimalFormat df = new DecimalFormat("######0.00");
        if (null != d) {
            return Double.valueOf(df.format(d));
        } else {
            return Double.valueOf("0");
        }
    }
}
