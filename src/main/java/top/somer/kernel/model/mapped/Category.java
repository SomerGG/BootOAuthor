package top.somer.kernel.model.mapped;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;
import top.somer.kernel.model.mapped.system.IntegerPrototype;

import java.io.Serializable;

/**
 * 类别信息
 *
 * @author Somer
 * @date 2018-03-16 13:18
 **/
@Table("category")
public class Category extends IntegerPrototype implements Serializable {

    @Column("type_id")
    private Integer typeId;

    @Column("category")
    private String category;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
