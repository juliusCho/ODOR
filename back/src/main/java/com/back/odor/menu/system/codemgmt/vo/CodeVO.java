package com.back.odor.menu.system.codemgmt.vo;

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
public class CodeVO extends CommonVO implements Serializable {

    private static final long serialVersionUID = -2980688358495044166L;

    private String codeId;

    private String codeGroupId;

    private String codeMessage;

    private String codeName;

}
