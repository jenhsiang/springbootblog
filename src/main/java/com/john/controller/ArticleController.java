package com.john.controller;

import com.john.model.Article;
import com.john.service.ArticleMapper;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ArticleController {

    @Resource
    private ArticleMapper articleMapper;

    @ApiOperation(value="添加文章", notes="添加文章")
    @PostMapping(value = "/addArticle")
    @ResponseStatus(HttpStatus.OK)
    public int addArticle(@RequestParam("title") String title,@RequestParam("summary") String summary,@RequestParam("mdContent") String mdContent,@RequestParam("state") int state){
        Article article = new Article();
        article.setTitle(title);
        article.setSummary(summary);
        article.setMdContent(mdContent);
        article.setState(state);
        article.setPageView(0);
        return articleMapper.insertArtcile(article);
    }

    @ApiOperation(value="獲取文章信息", notes="根據id獲取文章信息")
    @PostMapping(value = "/article/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Article findArticle(@PathVariable("id") int id) throws NotFoundException
    {
        return articleMapper.getArticleById(id);
    }

    @ApiOperation(value="删除文章", notes="根據id删除文章")
    @PostMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public int deleteUser(@PathVariable("id") int id)
    {
       return articleMapper.deleteArtcile(id);
    }

    @ApiOperation(value="更新文章", notes="更新文章")
    @PostMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public int updateUser(@RequestParam("id") int id,@RequestParam("title") String title,@RequestParam("summary") String summary,@RequestParam("mdContent") String mdContent,@RequestParam("state") int state)
    {
        Article article = articleMapper.getArticleById(id);
        article.setTitle(title);
        article.setSummary(summary);
        article.setMdContent(mdContent);
        article.setState(state);
        article.setPageView(0);
        return articleMapper.updateArtcile(article);
    }

}
