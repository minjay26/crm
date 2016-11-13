package cn.tendata.crm.data.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by minjay on 2016/11/6.
 */
@Entity
public class User extends AbstractEntityAuditable<Integer> implements UserDetails{

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String stringAuthorities;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.stringAuthorities));
        return authorities;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return super.getId();
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
