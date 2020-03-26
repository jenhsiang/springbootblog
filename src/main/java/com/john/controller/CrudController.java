package com.john.controller;

import com.john.model.Article;
import com.john.service.ArticleMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class CrudController {

    @Resource
    private ArticleMapper articleMapper;

    //列出--查詢
    @ApiOperation(value="獲取文章列表", notes="獲取文章列表")
    @GetMapping( "/article/{id}" )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Article> getArticle(@PathVariable(value = "id") int id) throws Exception {
        Article article = articleMapper.getArticleById(id);
        return ResponseEntity.ok().body(article);
    }

    //添加
    @ApiOperation(value="添加文章", notes="添加文章")
    @PostMapping ( "/article" )
    @ResponseStatus(HttpStatus.CREATED)
    public int addArticle(Article article, BindingResult bindingResult) throws Exception {
        return articleMapper.insertArtcile(article);
    }

    //删除
    @ApiOperation(value="删除文章", notes="根據id删除文章")
    @DeleteMapping ( "/article/{id}" )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public int deleteArticle(@PathVariable(value = "id") int id) throws Exception {
        return articleMapper.deleteArtcile(id);
    }

    //修改
    @ApiOperation(value="修改文章", notes="修改文章")
    @PatchMapping ( "/article" )
    @ResponseStatus(HttpStatus.CREATED)
    public int updateArticle( Article article, BindingResult bindingResult) throws Exception {
        return articleMapper.updateArtcile(article);
    }


}
