package com.back.odor.menu.system.brandmgmt.vo;

import com.back.odor.common.utils.vo.CommonVO;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class BrandVO extends CommonVO implements Serializable {

    private static final long serialVersionUID = 2888118560014221701L;

    private Long brandKey;

    @NonNull
    private String countryCode;

    @NonNull
    private String name;
    private String engName;
    private String desc;
    private String link;

}
