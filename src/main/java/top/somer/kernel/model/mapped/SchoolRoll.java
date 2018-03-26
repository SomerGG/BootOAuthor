package top.somer.kernel.model.mapped;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Table;
import top.somer.kernel.model.mapped.system.StringPrototype;

import java.io.Serializable;

/**
 * 学籍信息
 *
 * @author Somer
 * @date 2018-03-16 11:30
 **/
@Table("school_roll")
public class SchoolRoll extends StringPrototype implements Serializable {

    @Column("belong_id")
    @Comment("所属学生ID")
    private Integer belongId;

    @Column("student_number")
    @Comment("学籍号")
    private String studentNumber;

    @Column("school")
    @Comment("就读学校")
    private String school;

    @Column("school_name")
    @Comment("学校名称")
    private String schoolName;

    @Column("clazz")
    @Comment("所在班级")
    private String clazz;

    @Column("clazz_name")
    @Comment("班级名称")
    private String clazzName;

    @Column("study_way")
    @Comment("就读方式")
    private String studyWay;

    @Column("is_study")
    @Comment("是否在读")
    private Integer isStudy;

    @Column("study_time")
    @Comment("入学时间")
    private String studyTime;

    public Integer getBelongId() {
        return belongId;
    }

    public void setBelongId(Integer belongId) {
        this.belongId = belongId;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getStudyWay() {
        return studyWay;
    }

    public void setStudyWay(String studyWay) {
        this.studyWay = studyWay;
    }

    public Integer getIsStudy() {
        return isStudy;
    }

    public void setIsStudy(Integer isStudy) {
        this.isStudy = isStudy;
    }

    public String getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(String studyTime) {
        this.studyTime = studyTime;
    }

}
