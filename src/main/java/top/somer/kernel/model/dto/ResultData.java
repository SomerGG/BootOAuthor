package top.somer.kernel.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Somer
 * @date 2017/10/12
 */

public class ResultData<T> implements Serializable {

    private List<T> row;

    private Integer total;

    public List<T> getRow() {
        return row;
    }

    public void setRow(List<T> row) {
        this.row = row;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
