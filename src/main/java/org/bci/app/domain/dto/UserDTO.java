package org.bci.app.domain.dto;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {

    private String id;
    private Date created;
    private Date modifed;
    private Date lastLogin;
    private String token;
    private Boolean isActive;

}
