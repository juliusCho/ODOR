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
    @Setter
    private String creatorName;

    @Getter
    private String createDate;

    @Setter
    private String updaterId;
    @Setter
    private String updaterName;

    @Getter
    private String updateDate;

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
