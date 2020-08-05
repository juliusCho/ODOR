package com.back.odor.menu.forum.service;

import com.back.odor.menu.forum.vo.ArticleVO;

import java.util.List;

public interface ForumServiceSpec {

    List<ArticleVO> reviewList(ArticleVO vo);
    ArticleVO review(ArticleVO vo);
    void createReview(ArticleVO vo);
    void updateReview(ArticleVO vo);
    void deleteReview(ArticleVO vo);

}
