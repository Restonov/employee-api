package com.restonov.task.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class EmployeeCategory implements GrantedAuthority {

  private static final long serialVersionUID = -8009952451001282057L;

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(unique = true)
  private String name;

  @OneToOne(mappedBy = "category")
  private Employee employee;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    EmployeeCategory that = (EmployeeCategory) o;

    if (id != that.id) {
      return false;
    }
    return name != null ? name.equals(that.name) : that.name == null;
  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }

  @Override
  public String getAuthority() {
    return name;
  }
}
