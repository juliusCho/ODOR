package com.back.odor.menu.system.categorymgmt.vo;

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
public class CategoryVO extends CommonVO implements Serializable {

    private static final long serialVersionUID = 5232833674673502424L;


    private String categoryId;

    private String categoryMessage;

    private String categoryName;

}
