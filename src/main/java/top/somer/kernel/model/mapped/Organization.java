package top.somer.kernel.model.mapped;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Table;
import top.somer.kernel.model.mapped.system.StringPrototype;

import java.io.Serializable;

/**
 * 机构信息
 *
 * @author Somer
 * @date 2018-03-16 11:28
 **/
@Table("organization")
public class Organization extends StringPrototype implements Serializable {

    @Column("name")
    @Comment("单位名称")
    private String name;

    @Column("code")
    @Comment("单位代码")
    private String code;

    @Column("org_property")
    @Comment("单位性质")
    private String orgProperty;

    @Column("org_type")
    @Comment("单位类别")
    private String orgType;

    @Column("address")
    @Comment("单位地址")
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrgProperty() {
        return orgProperty;
    }

    public void setOrgProperty(String orgProperty) {
        this.orgProperty = orgProperty;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
