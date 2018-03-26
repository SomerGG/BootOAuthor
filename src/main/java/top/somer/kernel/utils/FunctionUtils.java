package top.somer.kernel.utils;

import org.nutz.dao.Cnd;
import top.somer.kernel.model.dto.CndType;

import java.util.Map;

/**
 * @author Somer
 * @date 2017/10/12
 */

public class FunctionUtils {

    /**
     * @param map
     * @return
     * @Name 将Map转换为查询条件Cnd
     */
    public static Cnd MapToCnd(Map<String, CndType> map) {
        Cnd result = Cnd.where("1", "=", 1);
        map.forEach((k, v) -> {
            if (v.getType().equals("likeRight")) {
                result.and(k, "like", v.getDate() + "%");
            } else if (v.getType().equals("like")) {
                result.and(k, "like", "%" + v.getDate() + "%");
            } else if (v.getType().equals("desc")) {
                result.desc(k);
            } else if (v.getType().equals("asc")) {
                result.asc(k);
            } else if (v.getType().equals("today")) {
                result.and(k, ">=", v.getDate().toString() + " 00:00:00").and(k, "<=",
                        v.getDate().toString() + " 23:59:59");
            } else if (v.getType().equals("in")) {
                result.and(k, "in", v.getDate());
            } else if (v.getType().equals("or")) {
                result.or(k, "=", v.getDate());
            } else if (v.getType().equals("between")) {
                result.and(k, ">=", "1").and(k, "<=", v.getDate());
            } else if (v.getType().equals("lessThan")) {
                result.and(k, "<=", v.getDate());
            } else if (v.getType().equals("not in")) {
                result.and(k, "not in", v.getDate());
            } else {
                result.and(k, v.getType(), v.getDate());
            }
        });
        return result;
    }

    /**
     * @param type
     * @param obj
     * @return
     * @name 生成新的查询条件
     */
    public static CndType Cond(String type, Object obj) {
        return new CndType(type, obj);
    }

    /**
     * @return
     * @name 判断参数是否为空
     * @since 2017/4/17
     */
    public static Boolean checkParam(String... param) {
        if (param.length <= 0) {
            return false;
        }
        for (String temp : param) {
            if (null == temp || "".equals(temp) || "undefined".equals(temp)) {
                return false;
            }
        }
        return true;
    }
}
