package cn.tendata.crm.data.domain;

import javax.persistence.*;

/**
 * Created by Luo Min on 2017/1/16.
 */
@Entity
public class EvectionRecord extends AbstractEntityAuditable<Long>{

    private static final long serialVersionUID = 1L;

    private MatterRecord matterRecord;

    private String place;

    @Id
    @GeneratedValue
    public Long getId() {
        return super.getId();
    }

    @Embedded
    public MatterRecord getMatterRecord() {
        return matterRecord;
    }

    public void setMatterRecord(MatterRecord matterRecord) {
        this.matterRecord = matterRecord;
    }

    @Column(name = "place",nullable = false)
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
