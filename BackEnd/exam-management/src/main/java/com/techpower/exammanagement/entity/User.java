package com.techpower.exammanagement.entity;

import com.techpower.exammanagement.constant.Role;
import com.techpower.exammanagement.constant.Status;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@Table( name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements UserDetails {


    public User(String userName, String password, Status status, Role role) {
        this.userName = userName;
        this.password = password;
        this.status = status;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String userName;
    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private TeacherEntity teacher;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private StudentEntity student;

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, status, role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
