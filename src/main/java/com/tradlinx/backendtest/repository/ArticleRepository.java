package com.tradlinx.backendtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tradlinx.backendtest.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

	int deleteByArticleId(long articleId);

	Article findByArticleId(long articleId);

}
