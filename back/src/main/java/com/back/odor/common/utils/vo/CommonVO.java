package com.back.odor.common.utils.vo;

import com.back.odor.common.etc.GlobalConst;
import com.back.odor.common.utils.LocaleUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
public class CommonVO implements Serializable {

    private static final long serialVersionUID = 8417229056976377890L;


    @Getter
    @Setter
    private String resultMsg;

    @Setter
    private String creatorId;

    @Getter
    private String creatorName;

    @Getter
    private String createDate;

    @Setter
    private String updaterId;

    @Getter
    private String updaterName;

    @Getter
    private String updateDate;

    @Getter
    private boolean useYn;

    public void setUseYn(Object useYn) {
        if (useYn instanceof Boolean) {
            this.useYn = (Boolean) useYn;
        } else if (useYn instanceof Integer) {
            this.useYn = "1".equals(useYn) ? true : false;
        } else if (useYn instanceof String) {
            this.useYn = "1".equals(useYn) || "true".equals(useYn) ? true : false;
        }
    }



    private String countryCode = LocaleUtil.getLocale();

    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
    }


    public String getCreatorId() {

        return null;
    }

    public String getUpdaterId() {
        return null;
    }

    public void setCreateDate() {

    }

    public void setUpdateDate() {

    }

}
