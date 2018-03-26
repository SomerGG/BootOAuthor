package top.somer.kernel.utils;

import com.google.common.collect.Lists;
import org.nutz.json.Json;

import java.util.List;

/**
 * @author Somer
 * @date 2018-03-05 16:21
 **/
public class StringUtils {


    public static List<Integer> getRoleIdList(String roleIds) {
        List<Integer> list = Lists.newArrayList();
        if (roleIds.length() == 0) {
            return null;
        }
        String[] temps = roleIds.split(",");
        for (String temp : temps) {
            list.add(Integer.valueOf(temp));
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(Json.toJson(getRoleIdList("1,2,3,4,5,6")));
    }
}
