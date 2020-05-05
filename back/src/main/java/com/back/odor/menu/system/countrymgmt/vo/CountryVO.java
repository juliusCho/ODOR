package com.back.odor.menu.system.countrymgmt.vo;

import com.back.odor.common.utils.vo.CommonVO;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class CountryVO extends CommonVO implements Serializable {

    @NonNull
    private String countryCode;

    private String countryMessage;

    @NonNull
    private String countryName;

}
