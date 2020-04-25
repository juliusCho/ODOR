package com.back.odor.common.utils.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class CommonVO implements Serializable {

    private static final long serialVersionUID = 8417229056976377890L;

    private String resultMsg;

    private String creatorId;
    private String creatorName;
    private String createDate;
    private String updaterId;
    private String updaterName;
    private String updateDate;

}
