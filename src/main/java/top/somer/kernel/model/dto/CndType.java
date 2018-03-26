package top.somer.kernel.model.dto;

/**
 * @author Somer
 * @date 2017/10/12
 */

public class CndType {

    private String type;
    private Object Date;

    public CndType(String type, Object date) {
        super();
        this.type = type;
        Date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getDate() {
        return Date;
    }

    public void setDate(Object date) {
        Date = date;
    }
}
