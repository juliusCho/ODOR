package com.back.odor.menu.system.ratingitemmgmt.vo;

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
public class RatingItemMasterVO extends CommonVO implements Serializable {

    private static final long serialVersionUID = -3569166259269792508L;

    private Long masterKey;

    private String categoryId;
    private String categoryName;

    private int colSeq = 0;

    private String itemMessage;
    private String itemName;

    private String dataTypeCode;
    private String dataTypeName;

    private String displayTypeCode;
    private String displayTypeName;

    private int sortSeq = 0;

    private String displayOptionCode;
    private String displayOptionName;

    private String inputOptionCode;
    private String inputOptionName;

    private boolean requiredYn;

    private String minValue;
    private String maxValue;

    private String rankCode;
    private String rankName;

}
