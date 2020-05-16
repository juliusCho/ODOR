package com.back.odor.menu.system.membershipmgmt.vo;

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
public class MembershipForumMapperVO extends CommonVO implements Serializable {

    private static final long serialVersionUID = 740046087395503815L;

    private Long membershipKey;
    private String categoryId;
    private Long forumKey;

}
