package com.back.odor.common.utils.vo;

import com.back.odor.common.etc.GlobalConst;
import com.back.odor.common.utils.AuthUtil;
import com.back.odor.common.utils.DateUtil;
import com.back.odor.common.utils.LocaleUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.servlet.http.HttpSession;
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

    @Setter
    private Date createDate;
    private String createDt;
    private String createDtTime;
    @Setter
    private Date updateDate;
    private String updateDt;
    private String updateDtTime;

    @Getter
    private boolean useYn;

    @Setter
    private String countryCode;

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
            this.useYn = "1".equals(useYn) ? true : false;
        } else if (useYn instanceof String) {
            this.useYn = "1".equals(useYn) || "true".equals(useYn) ? true : false;
        }
    }

    public String getCountryCode() {
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
            return DateUtil.getLocalDate(this.createDate);
        } catch (Exception e) {
            return "";
        }
    }

    public String getCreateDtTime() {
        try {
            return DateUtil.getLocalDateTime(this.createDate);
        } catch (Exception e) {
            return "";
        }
    }

    public String getUpdateDt() {
        try {
            return DateUtil.getLocalDate(this.updateDate);
        } catch (Exception e) {
            return "";
        }
    }

    public String getUpdateDtTime() {
        try {
            return DateUtil.getLocalDateTime(this.updateDate);
        } catch (Exception e) {
            return "";
        }
    }

}
