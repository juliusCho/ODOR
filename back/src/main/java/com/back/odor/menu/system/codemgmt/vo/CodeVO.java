package com.back.odor.menu.system.codemgmt.vo;

import com.back.odor.common.utils.vo.CommonVO;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class CodeVO extends CommonVO implements Serializable {

    private static final long serialVersionUID = -2980688358495044166L;

    @NonNull
    private String codeId;

    @NonNull
    private String codeGroupId;

    private String codeMessage;

    @NonNull
    private String codeName;

    private boolean useYn;

}
