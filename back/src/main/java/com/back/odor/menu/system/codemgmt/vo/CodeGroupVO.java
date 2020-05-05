package com.back.odor.menu.system.codemgmt.vo;

import com.back.odor.common.utils.vo.CommonVO;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class CodeGroupVO extends CommonVO implements Serializable {

    private static final long serialVersionUID = -5461521584364680854L;

    @NonNull
    private String codeGroupId;
    private String codeGroupMessage;

    @NonNull
    private String codeGroupName;

}
