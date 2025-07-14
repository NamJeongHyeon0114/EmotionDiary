package com.example.diary.dto;

import com.example.diary.entity.Diary;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
public class DiaryResponse {
    private Long id;
    private long createdDate;
    private int emotionID;
    private String content;

    private long convertToMs(LocalDateTime dateTime) {
        // 2. ZonedDateTime을 Instant로 바꿔줌 (UTC 기준 시점으로)
        Instant instant = dateTime.atZone(ZoneId.of("Asia/Seoul")).toInstant();
        // 3. Instant를 밀리초(Long)로 바꿔줌
        long timestamp = instant.toEpochMilli();
        // 4. 최종 밀리초 값을 리턴
        return timestamp;
    }


    public DiaryResponse(Diary diary){
        this.id = diary.getId();
        this.emotionID = diary.getEmotionId();
        this.content = diary.getContent();
        this.createdDate= convertToMs(diary.getCreatedDate());
    }




}
