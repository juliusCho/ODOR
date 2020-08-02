package com.back.odor.menu.forum.mapper;

import com.back.odor.menu.forum.vo.ArticleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ForumMapper {

    List<ArticleVO> articleList(ArticleVO vo);
    ArticleVO article(ArticleVO vo);
    void createArticle(ArticleVO vo);
    void updateArticle(ArticleVO vo);
    void deleteArticle(ArticleVO vo);

}
