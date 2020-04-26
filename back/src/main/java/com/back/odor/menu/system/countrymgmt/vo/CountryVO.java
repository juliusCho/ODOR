package com.back.odor.menu.system.countrymgmt.vo;

import com.back.odor.common.utils.vo.CommonVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class CountryVO extends CommonVO implements Serializable {

    private String countryCode;
    private String countryMessage;
    private String countryName;
    private boolean useYn;

}
