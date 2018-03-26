package top.somer.kernel.model.dto;

import java.io.Serializable;

/**
 * 总数统计
 *
 * @author Somer
 * @create 2018-01-03 11:08
 **/
public class TotalCount implements Serializable {

    private Integer totalNumber;

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }
}
