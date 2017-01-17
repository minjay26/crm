package cn.tendata.crm.data.domain;

import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Created by Luo Min on 2017/1/13.
 */
@Entity
public class RegistrationRegulation extends AbstractEntityAuditable<Integer>{

    private static final long serialVersionUID = 1L;

    private int sort;

    private String registrationType;

    private String regulationDate;

    @Id
    @GeneratedValue
    public Integer getId() {
        return super.getId();
    }

    @Column(name = "sort",nullable = false)
    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Column(name = "registration_type",nullable = false)
    public String getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(String registrationType) {
        this.registrationType = registrationType;
    }

    @Column(name = "regulation_date",nullable = false)
    public String getRegulationDate() {
        return regulationDate;
    }

    public void setRegulationDate(String regulationDate) {
        this.regulationDate = regulationDate;
    }
}
