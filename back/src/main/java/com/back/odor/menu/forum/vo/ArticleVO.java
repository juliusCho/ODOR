package com.back.odor.menu.forum.vo;

import com.back.odor.common.utils.vo.CommonVO;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class ArticleVO extends CommonVO implements Serializable {

    private static final long serialVersionUID = 2888118560014221701L;

    private Long articleKey;
    private String categoryId;
    private Long forumKey;

    private String title;
    private String content;

    private String attachment;

    private Long likeCnt;

    private boolean useYn;
    private boolean noticeYn;
    
    private Long productKey;

    private String brandName;
    private String productName;

}
