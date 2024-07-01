package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j  // 로깅 기능을 위한 어노테이션 추가
@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
//        System.out.println(form.toString() );   // DTO에 폼 데이터가 잘 담겼는지 확인
        log.info(form.toString() );
        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
//        System.out.println(article.toString() );    // DTO가 엔티티로 잘 변환되는지 확인 출력
        log.info(article.toString());

        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);    // article 엔티티를 저장해 saved 객체에 반환
//        System.out.println(saved.toString() );      // article이 DB에 잘 저장되는지 확인 출력
        log.info(saved.toString());
        return "";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);
        // 1. id를 조회해 db에서 해당 데이터 가져오기
//        Optional<Article> articleEntity = articleRepository.findById(id);
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 가져온 데이터를 모델에 등록하기
        model.addAttribute("article", articleEntity);

        // 3. 조회한 데이터를 사용자에게 보여주기 위한 뷰 페이지 만들고 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {  // model 객체 가져오기
        // 1. DB에서 모든 Article 데이터 가져오기
        List<Article> articleEntityList = articleRepository.findAll();
        
        // 2. 가져온 Article 묶음을 모델에 등록하기
        model.addAttribute("articleList", articleEntityList);   // articleEntityList 등록
        
        // 3. 사용자에게 보여 줄 뷰 페이지 설정하기
        return "articles/index";
    }
}
