package cn.tendata.crm.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;
import java.util.List;

/**
 * Created by minjay on 2016/11/6.
 */
@Entity
public class User extends AbstractEntityAuditable<Integer> implements UserDetails{

    private static final long serialVersionUID = 1L;

    public static class UserStatus{
        public static final int ONLINE = 1;
        public static final int OFFLINE = 0;
    }

    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private String password;

    private String stringAuthorities;

    private String mailBox;

    private int loginCount;

    private DateTime lastLoginAt;

    private int status;

    public User() {
    }

    public User(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(this.getStringAuthorities());
        return authorities;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStringAuthorities() {
        return stringAuthorities;
    }

    public void setStringAuthorities(String stringAuthorities) {
        this.stringAuthorities = stringAuthorities;
    }

    @Email
    @Column(name = "mail_box",nullable = false)
    public String getMailBox() {
        return mailBox;
    }

    public void setMailBox(String mailBox) {
        this.mailBox = mailBox;
    }

    @Column(name="login_count", nullable=false)
    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    @Column(name="last_login_at")
    public DateTime getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(DateTime lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    @Column(name="status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
