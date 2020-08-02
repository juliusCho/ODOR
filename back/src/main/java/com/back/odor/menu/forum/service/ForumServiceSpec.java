package com.back.odor.menu.forum.service;

import com.back.odor.menu.forum.vo.ArticleVO;

import java.util.List;

public interface ForumServiceSpec {

    List<ArticleVO> articleList(ArticleVO vo);
    ArticleVO article(ArticleVO vo);
    void createArticle(ArticleVO vo);
    void updateArticle(ArticleVO vo);
    void deleteArticle(ArticleVO vo);

}
