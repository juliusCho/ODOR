package com.back.odor.menu.forum.mapper;

import com.back.odor.menu.forum.vo.ArticleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ForumMapper {

    List<ArticleVO> reviewList(ArticleVO vo);
    ArticleVO review(ArticleVO vo);
    void createReview(ArticleVO vo);
    void updateReview(ArticleVO vo);
    void deleteReview(ArticleVO vo);

}
