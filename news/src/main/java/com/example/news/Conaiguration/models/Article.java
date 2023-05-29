package com.example.news.Conaiguration.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

/**
Сущность базы данных
 */

@Entity
@Table(name = "ARTICLES")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "news_site")
    private String newsSite;
    @Column(name = "published_date")
    private String publishedAt;
    @Column(name = "article", columnDefinition = "text")
    private String article;

}
