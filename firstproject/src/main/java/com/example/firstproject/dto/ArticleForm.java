package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    
    private Long id;        // id 필드 추가
    private String title;   // 제목을 받을 필드
    private String content; // 내용을 받을 필드

// @AllArgsConstructor 추가하면서 주석
//    public ArticleForm(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }

// @ToString 추가하면서 주석
//// 데이터를 잘 받았는지 확인한 toString() 메서드 추가
//@Override
//public String toString() {
//    return "ArticleForm{" +
//            "title='" + title + '\'' +
//            ", content='" + content + '\'' +
//            '}';
//}

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
