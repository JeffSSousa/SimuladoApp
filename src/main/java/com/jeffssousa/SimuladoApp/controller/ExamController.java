package com.jeffssousa.SimuladoApp.controller;

import com.jeffssousa.SimuladoApp.dto.ExamRequestDTO;
import com.jeffssousa.SimuladoApp.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @PostMapping
    public ResponseEntity<Void> createExam(@RequestBody ExamRequestDTO dto){
        examService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
