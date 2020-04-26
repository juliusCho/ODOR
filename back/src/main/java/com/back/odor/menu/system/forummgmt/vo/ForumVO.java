package com.back.odor.menu.system.forummgmt.vo;

import com.back.odor.common.utils.vo.CommonVO;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class ForumVO extends CommonVO implements Serializable {

    private static final long serialVersionUID = -3569166259269792508L;

    @NonNull
    private Long forumKey;

    @NonNull
    private String categoryId;

    private String forumName;

    @NonNull
    private String forumTypeCode;

    private boolean reviewYn;
    private boolean useYn;

}
