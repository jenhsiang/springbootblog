package com.john.controller;

import com.john.model.Article;
import com.john.service.ArtcileMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CrudController {

    @Resource
    private ArtcileMapper artcileMapper;

    //添加頁面
    @RequestMapping ( "add" )
    public String add() {
        return "add";
    }

    //列出--查詢
    @RequestMapping ( "getArticle" )
    public String getArticle(int id, Model model) throws Exception {
        Article article = artcileMapper.getArticleById(id);
        model.addAttribute("article", article);
        return "articleShow";
    }

    //添加
    @RequestMapping ( "addArticle" )
    public String listArticle(Article article, BindingResult bindingResult) throws Exception {
        boolean flag = artcileMapper.insertArtcile(article) > 0;
        return "redirect:listArticle";
    }

    //删除
    @RequestMapping ( "deleteArticle" )
    public String deleteArticle(Article article) throws Exception {
        artcileMapper.deleteArtcile(article.getId());
        return "redirect:listArticle";
    }

    //修改
    @RequestMapping ( "updateArticle" )
    public String updateArticle(Article article) throws Exception {
        boolean flag = artcileMapper.updateArtcile(article) > 0;
        return "redirect:listArticle";
    }

    //列出--修改
    @RequestMapping ( "findArticle" )
    public String findArticle(int id, Model model) throws Exception {
        Article article = artcileMapper.getArticleById(id);
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

        List<Article> articleList = artcileMapper.findArtcleBytitle(title);
        PageInfo<Article> page = new PageInfo<>(articleList);
        System.out.println("總數量：" + page.getTotal());
        System.out.println("當前查詢記錄：" + page.getList().size());
        System.out.println("當前頁碼：" + page.getPageNum());
        System.out.println("每頁顯示數量：" + page.getPageSize());
        System.out.println("總頁：" + page.getPages());
        model.addAttribute("pages", page);
        model.addAttribute("title", title);
        return "index";
    }


}
