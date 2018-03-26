package top.somer.kernel.model.mapped;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Table;
import top.somer.kernel.model.mapped.system.StringPrototype;

import java.io.Serializable;

/**
 * 学校信息
 *
 * @author Somer
 * @date 2018-03-16 11:26
 **/
@Table("school")
public class School extends StringPrototype implements Serializable {

    @Column("belong_id")
    @Comment("所属机构ID")
    private String belongId;

    @Column("name")
    @Comment("学校名称")
    private String name;

    @Column("mark_code")
    @Comment("标识码")
    private String markCode;

    @Column("address")
    @Comment("详细地址")
    private String address;

    @Column("school_type")
    @Comment("办学类型")
    private String schoolType;

    @Column("organizer_type")
    @Comment("学校举办者类型")
    private String organizerType;

    @Column("principal_name")
    @Comment("校长姓名")
    private String principalName;

    @Column("contact_number")
    @Comment("联系电话")
    private String contactNumber;

    public String getBelongId() {
        return belongId;
    }

    public void setBelongId(String belongId) {
        this.belongId = belongId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarkCode() {
        return markCode;
    }

    public void setMarkCode(String markCode) {
        this.markCode = markCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public String getOrganizerType() {
        return organizerType;
    }

    public void setOrganizerType(String organizerType) {
        this.organizerType = organizerType;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

}
