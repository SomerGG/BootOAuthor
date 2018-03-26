package top.somer.kernel.model.mapped.system;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;

/**
 * ID为Integer类型
 *
 * @author Somer
 * @date 2018-03-09 16:24
 **/
public class IntegerPrototype {

    @Id
    @Column(value = "id", wrap = true)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
