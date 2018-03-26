package top.somer.kernel.dao.base;

import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础Dao
 *
 * @author Somer
 * @date 2018-03-02 14:14
 **/
public class BaseDao {

    @Autowired
    public Dao dao;
}
