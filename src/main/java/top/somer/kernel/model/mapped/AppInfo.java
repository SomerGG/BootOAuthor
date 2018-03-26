package top.somer.kernel.model.mapped;

import org.nutz.dao.entity.annotation.*;
import top.somer.kernel.model.mapped.system.IntegerPrototype;

import java.io.Serializable;
import java.util.Date;

/**
 * APP信息
 *
 * @author Somer
 * @date 2018-03-16 13:20
 **/
@Table("app_info")
public class AppInfo extends IntegerPrototype implements Serializable {

    @Column("app_name")
    @Comment("应用名称")
    private String appName;

    @Column("app_type")
    @Comment("应用类别")
    private String appType;

    @Column("user_id")
    @Comment("创建人ID")
    private Integer userId;

    @Column(value = "description")
    @Comment("描述")
    @ColDefine(type = ColType.TEXT)
    private String description;

    @Column("create_time")
    @Comment("创建时间")
    private Date createTime;

    @Column("update_time")
    @Comment("修改时间")
    private Date updateTime;

    @Column("check_state")
    @Comment("审核状态")
    private String checkState;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCheckState() {
        return checkState;
    }

    public void setCheckState(String checkState) {
        this.checkState = checkState;
    }
}
