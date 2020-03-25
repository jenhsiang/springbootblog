package com.john.controller;

import com.john.model.Article;
import com.john.service.ArticleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CrudController {
    private Logger logger = LoggerFactory.getLogger("springboot");
    @Resource
    private ArticleMapper articleMapper;

    //添加頁面
    @RequestMapping ( "add" )
    public String add() {
        return "add";
    }

    //列出--查詢
    @RequestMapping ( "getArticle" )
    public String getArticle(Article article, BindingResult bindingResult, Model model) throws Exception {
        model.addAttribute("article", article);
        return "articleShow";
    }

    //列出--修改
    @RequestMapping ( "findArticle" )
    public String findArticle(Article article, BindingResult bindingResult, Model model) throws Exception {
        model.addAttribute("article", article);
        return "modify";
    }

    //文章列表
    @RequestMapping(value = {
            "/",
            "/listArticle"
    })
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


}
