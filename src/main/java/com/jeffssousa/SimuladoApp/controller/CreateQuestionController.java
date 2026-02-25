package com.jeffssousa.SimuladoApp.controller;

import com.jeffssousa.SimuladoApp.dto.CreateQuestionDTO;
import com.jeffssousa.SimuladoApp.usecase.question.CreateQuestionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("question")
@RequiredArgsConstructor
public class CreateQuestionController {

    private final CreateQuestionUseCase useCase;

    @PostMapping
    public ResponseEntity<Void> createQuestionWithAlternatives(@RequestBody CreateQuestionDTO dto){
        useCase.createQuestionForExam(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
