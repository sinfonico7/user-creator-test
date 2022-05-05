package org.bci.app.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhoneDTO {

    private String number;
    private String citycode;
    private String countrycode;
}