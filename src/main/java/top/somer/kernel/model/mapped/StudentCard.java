package top.somer.kernel.model.mapped;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Table;
import top.somer.kernel.model.mapped.system.IntegerPrototype;

import java.io.Serializable;

/**
 * 学生卡信息
 *
 * @author Somer
 * @date 2018-03-16 13:19
 **/
@Table("student_card")
public class StudentCard extends IntegerPrototype implements Serializable {

    @Column("student_id")
    @Comment("学生ID")
    private Integer studentId;

    @Column("card_id")
    @Comment("卡ID")
    private Integer cardId;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }
}
