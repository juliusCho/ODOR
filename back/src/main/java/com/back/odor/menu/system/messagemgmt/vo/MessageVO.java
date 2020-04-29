package com.back.odor.menu.system.messagemgmt.vo;

import com.back.odor.common.etc.GlobalConst;
import com.back.odor.common.utils.LocaleUtil;
import com.back.odor.common.utils.vo.CommonVO;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class MessageVO extends CommonVO implements Serializable {

    private static final long serialVersionUID = 404249874653688307L;

    @NonNull
    private String messageId;

    @NonNull
    private String countryCode = LocaleUtil.getLocale();

    @NonNull
    private String message;

}
