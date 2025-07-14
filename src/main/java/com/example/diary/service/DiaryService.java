package com.example.diary.service;


import com.example.diary.dto.DiaryResponse;
import com.example.diary.dto.DiaryRequest;
import com.example.diary.entity.Diary;
import com.example.diary.repository.DiaryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.time.*;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {




    private final DiaryRepository diaryRepository;


    private LocalDateTime convertToLocalDateTime(Long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Seoul"));
    }



    public DiaryResponse createDiary(DiaryRequest request) {
        Diary diary = new Diary();
        diary.setEmotionId(request.getEmotionId());

        diary.setCreatedDate(convertToLocalDateTime(request.getCreatedDate()));

        diary.setContent(request.getContent());

        return new DiaryResponse(diaryRepository.save(diary));
    }



    public DiaryResponse getDiaryById(Long id) {
        Diary diary = diaryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("일기를 찾을 수 없습니다."));
        return new DiaryResponse(diary);
    }



    public DiaryResponse updateDiary(Long id, DiaryRequest request) {

        // 실제 존재하는지 검사하는 절차
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("일기를 찾을 수 없습니다."));

            diary.setEmotionId(request.getEmotionId());
            diary.setContent(request.getContent());
            diary.setCreatedDate(convertToLocalDateTime(request.getCreatedDate()));

            return new DiaryResponse(diaryRepository.save(diary));
    }


    public Map<String, String> deleteDiary(Long id) {


        Diary diary = diaryRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("일기를 찾을 수 없습니다."));

        diaryRepository.delete(diary);

        Map<String, String> result = new HashMap<>();
        result.put("message", "일기 삭제 완료");
        return result;
    }


    public List<DiaryResponse> getDiariesByMonth(YearMonth yearMonth){

        LocalDateTime start = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime end = yearMonth.atEndOfMonth().atTime(23, 59, 59);

        List<Diary> diaries = diaryRepository.findAllByCreatedDateBetween(start, end);
        List<DiaryResponse> responseList = new ArrayList<>();

        for(Diary diary : diaries){

            // diary에서 꺼내서 dto로 받고 배열에 dto로 받은다음에 출력
            DiaryResponse response = new DiaryResponse(diary);
            responseList.add(response);
        }
        return responseList;

    }

}
