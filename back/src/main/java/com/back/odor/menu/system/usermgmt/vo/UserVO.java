package com.back.odor.menu.system.usermgmt.vo;

import com.back.odor.common.utils.vo.CommonVO;
import lombok.*;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserVO extends CommonVO implements Serializable {

    private static final long serialVersionUID = -6611753036370466951L;

    public UserVO(String userId, String password) {
        this.userId = userId;
        this.password = password;
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
    @Getter
    @Setter
    private String genderName;

    @Setter
    private Integer age;
    @Getter
    @Setter
    private Integer ageFr;
    @Getter
    @Setter
    private Integer ageTo;

    @Getter
    @Setter
    private String image;

    @Getter
    @Setter
    private String blocked;

    @Setter
    private boolean sysMngrYn;

    @Getter
    @Setter
    private Integer membershipKey;
    @Setter
    private String lvlCode;
    @Getter
    @Setter
    private String membershipName;


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
