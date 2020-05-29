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
public class ProductCreatorMapperVO extends CommonVO implements Serializable {

    private static final long serialVersionUID = 3509716938173538007L;

    private Long productKey;

    private Long creatorKey;

    private String categoryId;

}
