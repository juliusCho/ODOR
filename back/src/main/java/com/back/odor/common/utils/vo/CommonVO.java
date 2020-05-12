package com.back.odor.common.utils.vo;

import com.back.odor.common.utils.AuthUtil;
import com.back.odor.common.utils.DateUtil;
import com.back.odor.common.utils.LocaleUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ToString
public class CommonVO implements Serializable {

    private static final long serialVersionUID = 8417229056976377890L;


    @Getter
    @Setter
    private String resultMsg;

    @Setter
    private String creatorId;
    @Getter
    @Setter
    private String creatorName;
    @Setter
    private String updaterId;
    @Getter
    @Setter
    private String updaterName;

    @Getter
    @Setter
    private Date createDate;
    @Setter
    private String createDt;
    @Setter
    private String createDtTime;
    @Getter
    @Setter
    private Date updateDate;
    @Setter
    private String updateDt;
    @Setter
    private String updateDtTime;

    @Getter
    private boolean useYn;

    @Setter
    private String locale;

    @Getter
    @Setter
    private String localeMessage;
    @Getter
    @Setter
    private String koMessage;
    @Getter
    @Setter
    private String engMessage;




    public void setUseYn(Object useYn) {
        if (useYn instanceof Boolean) {
            this.useYn = (Boolean) useYn;
        } else if (useYn instanceof Integer) {
            this.useYn = Integer.parseInt(String.valueOf(useYn)) == 1;
        } else if (useYn instanceof String) {
            this.useYn = "1".equals(useYn) || "true".equals(useYn);
        }
    }

    public String getLocale() {
        return LocaleUtil.getLocale();
    }

    public String getCreatorId() {
        return AuthUtil.getCurrentUserId();
    }

    public String getUpdaterId() {
        return AuthUtil.getCurrentUserId();
    }

    public String getCreateDt() {
        try {
            return DateUtil.getLocalDate(getCreateDate());
        } catch (Exception e) {
            return "";
        }
    }

    public String getCreateDtTime() {
        try {
            return DateUtil.getLocalDateTime(getCreateDate());
        } catch (Exception e) {
            return "";
        }
    }

    public String getUpdateDt() {
        try {
            return DateUtil.getLocalDate(getUpdateDate());
        } catch (Exception e) {
            return "";
        }
    }

    public String getUpdateDtTime() {
        try {
            return DateUtil.getLocalDateTime(getUpdateDate());
        } catch (Exception e) {
            return "";
        }
    }

}
