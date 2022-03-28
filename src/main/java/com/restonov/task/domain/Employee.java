package com.restonov.task.domain;

import java.util.Collection;
import java.util.Collections;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements UserDetails {

  private static final long serialVersionUID = 2456937460041489613L;

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column
  private String name;

  @Column
  private String password;

  @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "category_id", referencedColumnName = "id")
  private EmployeeCategory category;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(category);
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return name;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Employee employee = (Employee) o;

    if (id != employee.id) {
      return false;
    }
    if (name != null ? !name.equals(employee.name) : employee.name != null) {
      return false;
    }
    if (password != null ? !password.equals(employee.password) : employee.password != null) {
      return false;
    }
    return category != null ? category.equals(employee.category) : employee.category == null;
  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    result = 31 * result + (category != null ? category.hashCode() : 0);
    return result;
  }
}
