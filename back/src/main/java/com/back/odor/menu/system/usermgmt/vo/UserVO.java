package com.back.odor.menu.system.usermgmt.vo;

import com.back.odor.common.utils.vo.CommonVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class UserVO extends CommonVO implements Serializable {

    private static final long serialVersionUID = -6611753036370466951L;

    public UserVO(String userId, String password, String role) {
        this.userId = userId;
        this.password = password;
        this.membershipKey = role;
    }

    @Setter
    private String userId;

    private String password;

    @Setter
    private String email;

    @Setter
    private String nickname;

    @Setter
    private String genderCode;

    @Setter
    private Integer age;

    @Setter
    private boolean sysMngrYn;

    @Setter
    private String membershipKey;


    public void setPassword(Object password) {
        if (password instanceof byte[]) {
            this.password = new String((byte[])password, StandardCharsets.UTF_8);
        } else {
            this.password = (String)password;
        }
    }

    public boolean match(String name, String password) {
        return this.userId.equals(name) && this.password.equals(password);
    }
}
