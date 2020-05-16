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
public class MembershipVO extends CommonVO implements Serializable {

    private static final long serialVersionUID = -3569166259269792508L;

    private Long membershipKey;

    private String membershipMessage;
    private String membershipName;

    private String lvlCode;
    private String lvlName;

}
