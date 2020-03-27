package com.john.controller;

import com.john.model.Article;
import com.john.service.ArticleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ArticleController {

    private Logger logger = LoggerFactory.getLogger("springboot");

    @Resource
    private ArticleMapper articleMapper;

    //添加頁面
    @ApiOperation(value="添加頁面")
    @GetMapping("/articles/new")
    @ResponseStatus(HttpStatus.OK)
    public String add() {
        return "add";
    }

    //文章列表
    @ApiOperation(value="文章列表")
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String listArticle(@RequestParam ( value = "title", defaultValue = "" ) String title,
                              Model model, @RequestParam ( value = "start", defaultValue = "1" ) int start,
                              @RequestParam ( value = "size", defaultValue = "5" ) int size) throws Exception {

        PageHelper.startPage(start, size);

        List<Article> articleList = articleMapper.findArtcleBytitle(title);
        PageInfo<Article> page = new PageInfo<>(articleList);
        logger.info("總數量：" + page.getTotal());
        logger.info("當前查詢記錄：" + page.getList().size());
        logger.info("當前頁碼：" + page.getPageNum());
        logger.info("每頁顯示數量：" + page.getPageSize());
        logger.info("總頁：" + page.getPages());
        model.addAttribute("pages", page);
        model.addAttribute("title", title);
        return "index";
    }

    //列出--修改
    @ApiOperation(value="列出--修改")
    @RequestMapping ( "articles/{id}/update" )
    @ResponseStatus(HttpStatus.OK)
    public String findArticle(@PathVariable(value = "id") int id, Model model) throws Exception {
        Article article = articleMapper.getArticleById(id);
        model.addAttribute("article", article);
        return "modify";
    }

    @ApiOperation(value="獲取文章列表", notes="獲取文章列表")
    @GetMapping("/articles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getArticle(@PathVariable(value = "id") int id, Model model) throws Exception {
        Article article = articleMapper.getArticleById(id);
        model.addAttribute("article", article);
        return "articleShow";
    }
}