package com.back.odor.menu.system.usermgmt.vo;

import com.back.odor.common.utils.vo.CommonVO;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserVO extends CommonVO {

    private String userId;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String password;

    private String email;
    private String nickname;

    private String genderCode;
    private String genderName;

    private Integer age;
    private boolean useYn;
    private boolean sysMngrYn;
    private Integer membershipKey;




    public String getPassword() {
        return password;
    }

//    public void setPassword(String password) {
//        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
//    }
    public void setPassword(String password) {
        this.password = password;
    }
}
