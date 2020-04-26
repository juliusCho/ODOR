package com.back.odor.menu.system.codemgmt.vo;

import com.back.odor.common.utils.vo.CommonVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
public class CodeVO extends CommonVO implements Serializable {

    private static final long serialVersionUID = -2980688358495044166L;

    @NonNull
    @Getter
    @Setter
    private String codeId;

    @NonNull
    @Getter
    @Setter
    private String codeGroupId;

    @Getter
    @Setter
    private String codeMessage;

    @NonNull
    @Getter
    @Setter
    private String codeName;

    @Getter
    @Setter
    private boolean useYn;

}
