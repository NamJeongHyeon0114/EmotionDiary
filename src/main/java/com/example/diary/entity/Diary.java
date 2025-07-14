package com.example.diary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class Diary {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private LocalDateTime createdDate;
    private int emotionId;
    private String content;
}
