package cn.tendata.crm.data.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by minjay on 2017/2/24.
 */
@Entity
public class Department extends AbstractEntityAuditable<Integer> {

    private static final long serialVersionUID = 1L;

    private String name;

    private int total;

    @Id
    @GeneratedValue
    public Integer getId() {
        return super.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void increaseTotal() {
        this.total += 1;
    }

    public void decreaseTotal() {
        this.total -= 1;
    }
}
