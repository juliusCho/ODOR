package com.back.odor.menu.system.usermgmt.vo;

import com.back.odor.common.utils.vo.CommonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserVO extends CommonVO {

    private String userId;
    private String password;

    private String email;
    private String nickname;

    private String genderCode;
    private String genderName;

    private Integer age;
    private boolean useYn;
    private boolean sysMngrYn;
    private Integer membershipKey;
}
