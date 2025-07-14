package com.example.diary.controller;


import com.example.diary.dto.DiaryRequest;
import com.example.diary.dto.DiaryResponse;
import com.example.diary.entity.Diary;
import com.example.diary.service.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diaries")
@Tag(name = "Diary API", description = "감정일기장 API")
public class DiaryController {

    private final DiaryService dairyService;


    @PostMapping
    @Operation(summary = "감정일기장 추가", description = "날짜, 감정, 내용 추가")
    public DiaryResponse createDiary(@RequestBody DiaryRequest request) {
        return dairyService.createDiary(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "감정일기장 조회", description = "일기장Id로 조회")
    public DiaryResponse getDiaryById(@PathVariable Long id) {
        return dairyService.getDiaryById(id);

    }

    @PutMapping("{id}")
    @Operation(summary = "감정일기장 수정", description = "감정일기장 수정")
    public DiaryResponse updateDiary(@PathVariable Long id, @RequestBody DiaryRequest request) {
        return dairyService.updateDiary(id, request);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "감정일기장 삭제", description = "감정일기장 삭제")
    public Map<String, String> deleteDiary(@PathVariable Long id) {
        return dairyService.deleteDiary(id);
    }


    @GetMapping("/month")
    @Operation(summary = "감정일기장 조회", description = "감정일기장 월별 조회")
    public List<DiaryResponse> getDiariesByMonth(@RequestParam("month") YearMonth yearMonth){
        return dairyService.getDiariesByMonth(yearMonth);
    }


}




