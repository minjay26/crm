package cn.tendata.crm.data.domain;

import cn.tendata.crm.model.AuditStatus;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Luo Min on 2017/1/16.
 */
@Embeddable
public class MatterRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private User user;

    private DateTime startDate;

    private DateTime endDate;

    private String reason;

    private User approver;

    private AuditStatus status = AuditStatus.AUDITING;

    public MatterRecord() {
    }

    public MatterRecord(DateTime startDate, DateTime endDate, String reason, User approver) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.approver = approver;
    }

    public MatterRecord(User user, DateTime startDate, DateTime endDate, String reason, User approver, AuditStatus status) {
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.approver = approver;
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "start_date", nullable = false)
    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date", nullable = false)
    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    @Column(name = "reason", nullable = false)
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @ManyToOne
    @JoinColumn(name = "approver_id")
    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    public AuditStatus getStatus() {
        return status;
    }

    public void setStatus(AuditStatus status) {
        this.status = status;
    }
}
