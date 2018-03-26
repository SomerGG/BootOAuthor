package top.somer.kernel.model.mapped.system;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单
 *
 * @author Somer
 * @date 2018-03-02 10:56
 **/
@Table("sys_menu")
public class SysMenu extends IntegerPrototype implements Serializable {

    /**
     * 父菜单ID
     */
    @Column("parent_id")
    private Integer parentId;

    /**
     * 菜单类别(0：目录   1：菜单   2：按钮)
     */
    @Column("type")
    private Integer type;

    /**
     * 菜单名称
     */
    @Column("name")
    private String name;

    /**
     * 菜单URL
     */
    @Column("url")
    private String url;

    /**
     * 菜单图标
     */
    @Column("icon")
    private String icon;

    /**
     * 菜单排序
     */
    @Column("order_num")
    private Integer orderNum;

    private List<?> list;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
