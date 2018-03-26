package top.somer.kernel.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Somer
 * @date 2017/10/12
 */

public class ResultData<T> implements Serializable {

    private List<T> row;

    private ResultTotal total;

    public List<T> getRow() {
        return row;
    }

    public void setRow(List<T> row) {
        this.row = row;
    }

    public ResultTotal getTotal() {
        return total;
    }

    public void setTotal(ResultTotal total) {
        this.total = total;
    }
}
