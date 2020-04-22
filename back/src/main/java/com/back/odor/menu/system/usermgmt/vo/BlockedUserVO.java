package com.back.odor.menu.system.usermgmt.vo;

import com.back.odor.common.utils.vo.CommonVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class BlockedUserVO extends CommonVO implements Serializable {

    private static final long serialVersionUID = -8237449947217625573L;

    private String userId;
    private String email;

    private String reasonCode;
    private String reason;

}
