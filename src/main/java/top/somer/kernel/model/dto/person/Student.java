package top.somer.kernel.model.dto.person;

import java.io.Serializable;

/**
 * 学生信息
 *
 * @author Somer
 * @date 2018-03-20 12:35
 **/
public class Student implements Serializable {

    /**
     * 学籍ID
     */
    private String id;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 学号
     */
    private String identityNumber;

    /**
     * 性别
     */
    private String gender;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 班级名称
     */
    private String clazzName;

    /**
     * 就读方式
     */
    private String studyWay;

    /**
     * 是否在读
     */
    private String isStudy;

    /**
     * 入学时间
     */
    private String studyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
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

    public String getIsStudy() {
        return isStudy;
    }

    public void setIsStudy(String isStudy) {
        this.isStudy = isStudy;
    }

    public String getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(String studyTime) {
        this.studyTime = studyTime;
    }

}
