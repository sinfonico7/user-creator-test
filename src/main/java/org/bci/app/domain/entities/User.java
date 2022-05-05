package org.bci.app.domain.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="USERS")
public class User {

    @Id
    @Column(name = "user_id")
    private String id;

    private String name;

    private String email;

    private String password;

    @Column(length = 500)
    private String token;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private List<Phone> phones;

    @Column(name = "is_active")
    private Boolean isActive;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date modified;

    @Column(name = "last_login")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date lastLogin;


}