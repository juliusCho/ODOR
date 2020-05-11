package com.back.odor.menu.system.messagemgmt.vo;

import com.back.odor.common.utils.LocaleUtil;
import com.back.odor.common.utils.vo.CommonVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class MessageVO extends CommonVO implements Serializable {

    private static final long serialVersionUID = 404249874653688307L;

    private String messageId;

    private String countryCode = LocaleUtil.getLocale();

    private String message;

}
