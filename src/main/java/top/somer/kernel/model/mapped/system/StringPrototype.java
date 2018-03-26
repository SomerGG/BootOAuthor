package top.somer.kernel.model.mapped.system;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;

/**
 * ID为String类型
 *
 * @author Somer
 * @date 2018-03-09 16:28
 **/
public class StringPrototype {

    @Name
    @Column(value = "id", wrap = true)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
