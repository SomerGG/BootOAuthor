package top.somer.kernel.model.dto.manager;

import java.io.Serializable;

/**
 * @author Somer
 * @date 2018-03-05 19:38
 **/
public class Perms implements Serializable {

    private Integer id;

    private String permName;

    private String perms;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }
}
