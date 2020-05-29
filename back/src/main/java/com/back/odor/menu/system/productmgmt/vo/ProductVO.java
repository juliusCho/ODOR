package com.back.odor.menu.system.productmgmt.vo;

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
public class ProductVO extends CommonVO implements Serializable {

    private static final long serialVersionUID = 2888118560014221701L;

    private Long productKey;

    private String countryCode;
    private String countryName;

    private String categoryId;
    private String categoryName;

    private String name;
    private String engName;

    private String desc;

    private String image;

    private Integer year;
    private Integer yearFr;
    private Integer yearTo;

    private String genderCode;
    private String genderName;

}
