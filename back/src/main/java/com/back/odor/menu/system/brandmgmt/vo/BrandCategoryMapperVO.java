package com.back.odor.menu.system.brandmgmt.vo;

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
public class BrandCategoryMapperVO extends CommonVO implements Serializable {

    private static final long serialVersionUID = 3509716938173538007L;

    private Long brandKey;

    private String categoryId;

}
