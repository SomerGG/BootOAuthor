package top.somer.kernel.model.mapped;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Table;
import top.somer.kernel.model.mapped.system.IntegerPrototype;

import java.io.Serializable;

/**
 * 人员信息
 *
 * @author Somer
 * @date 2018-03-16 11:23
 **/
@Table("person")
public class Person extends IntegerPrototype implements Serializable {

    @Column("username")
    @Comment("登录名")
    private String username;

    @Column("person_id")
    @Comment("人员ID")
    private String personId;

    @Column("type")
    @Comment("人员类别")
    private Integer type;

    @Column("name")
    @Comment("人员姓名")
    private String name;

    @Column("gender")
    @Comment("性别")
    private Integer gender;

    @Column("ethnic")
    @Comment("民族")
    private String ethnic;

    @Column("birthday")
    @Comment("出生日期")
    private String birthday;

    @Column("native_place")
    @Comment("籍贯")
    private String nativePlace;

    @Column("id_card")
    @Comment("身份证号码")
    private String idCard;

    @Column("political_status")
    @Comment("政治面貌")
    private String politicalStatus;

    @Column("contact_number")
    @Comment("联系电话")
    private String contactNumber;

    @Column("identity_number")
    @Comment("学号或工号")
    private String identityNumber;

    @Column("school_id")
    @Comment("学校ID")
    private String schoolId;

    @Column("position")
    @Comment("职务")
    private String position;

    @Column("department")
    @Comment("部门")
    private String department;

    @Column("guardian")
    @Comment("监护人")
    private String guardian;

    @Column("guardian_phone")
    @Comment("监护人电话")
    private String guardianPhone;

    @Column("is_only_child")
    @Comment("是否独生子女")
    private Integer isOnlyChild;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGuardian() {
        return guardian;
    }

    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }

    public String getGuardianPhone() {
        return guardianPhone;
    }

    public void setGuardianPhone(String guardianPhone) {
        this.guardianPhone = guardianPhone;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public Integer getIsOnlyChild() {
        return isOnlyChild;
    }

    public void setIsOnlyChild(Integer isOnlyChild) {
        this.isOnlyChild = isOnlyChild;
    }
}
