package top.somer.kernel.model.mapped;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Table;
import top.somer.kernel.model.mapped.system.IntegerPrototype;

import java.io.Serializable;

/**
 * 班级信息
 *
 * @author Somer
 * @date 2018-03-16 17:04
 **/
@Table("clazz")
public class Clazz extends IntegerPrototype implements Serializable {

    @Column("school_id")
    @Comment("学校ID")
    private String schoolId;

    @Column("grade")
    @Comment("年级")
    private String grade;

    @Column("clazz")
    @Comment("班级")
    private String clazz;

    @Column("head_teacher")
    @Comment("班主任")
    private String headTeacher;

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getHeadTeacher() {
        return headTeacher;
    }

    public void setHeadTeacher(String headTeacher) {
        this.headTeacher = headTeacher;
    }
}
