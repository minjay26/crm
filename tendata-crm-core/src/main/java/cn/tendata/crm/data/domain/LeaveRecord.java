package cn.tendata.crm.data.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Luo Min on 2017/1/16.
 */
@Entity
public class LeaveRecord extends AbstractEntityAuditable<Long>{

    private static final long serialVersionUID = 1L;

    private MatterRecord matterRecord;

    @GeneratedValue
    @Id
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
}
