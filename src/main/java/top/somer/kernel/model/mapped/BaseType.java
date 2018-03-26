package top.somer.kernel.model.mapped;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Table;
import top.somer.kernel.model.mapped.system.IntegerPrototype;

import java.io.Serializable;

/**
 * 基础类别
 *
 * @author Somer
 * @date 2018-03-16 13:17
 **/
@Table("base_type")
public class BaseType extends IntegerPrototype implements Serializable {

    @Column("type")
    @Comment("类别")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
