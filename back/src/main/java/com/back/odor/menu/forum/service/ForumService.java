package com.back.odor.menu.forum.service;

import com.back.odor.menu.forum.mapper.ForumMapper;
import com.back.odor.menu.forum.vo.ArticleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ForumService implements ForumServiceSpec {

    @Autowired
    private ForumMapper forumMapper;

    @Override
    public List<ArticleVO> reviewList(ArticleVO vo) {
        return forumMapper.reviewList(vo);
    }

    @Override
    public ArticleVO review(ArticleVO vo) {
        return null;
    }

    @Override
    public void createReview(ArticleVO vo) {

    }

    @Override
    public void updateReview(ArticleVO vo) {

    }

    @Override
    public void deleteReview(ArticleVO vo) {

    }

}
