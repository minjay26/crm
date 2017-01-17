package cn.tendata.crm.data.domain;

import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Created by Luo Min on 2017/1/13.
 */
@Entity
public class RegistrationRecord extends AbstractEntityAuditable<Long> {

    private User user;

    private DateTime registrationDate;

    private RegistrationRegulation regulation;

    @Id
    @GeneratedValue
    public Long getId() {
        return super.getId();
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "registration_date",nullable = false)
    public DateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(DateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    @OneToOne
    @JoinColumn(name = "regulation_id",nullable = false)
    public RegistrationRegulation getRegulation() {
        return regulation;
    }

    public void setRegulation(RegistrationRegulation regulation) {
        this.regulation = regulation;
    }
}
