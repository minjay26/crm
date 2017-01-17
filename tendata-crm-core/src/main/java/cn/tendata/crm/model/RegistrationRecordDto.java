package cn.tendata.crm.model;

import cn.tendata.crm.data.domain.RegistrationRegulation;
import org.joda.time.DateTime;

/**
 * Created by Luo Min on 2017/1/13.
 */
public class RegistrationRecordDto {

    private RegistrationRegulation regulation;

    private DateTime registrationDate;

    private boolean registered;

    public RegistrationRegulation getRegulation() {
        return regulation;
    }

    public void setRegulation(RegistrationRegulation regulation) {
        this.regulation = regulation;
    }

    public DateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(DateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }
}
