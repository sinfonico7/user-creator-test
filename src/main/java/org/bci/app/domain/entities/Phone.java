package org.bci.app.domain.entities;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name="PHONES")
public class Phone {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id")
    private Long id;
    private String number;
    private String cityCode;
    private String countryCode;

    public Phone(String number, String cityCode, String countryCode, User user){
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
        this.user = user;

    }

}
