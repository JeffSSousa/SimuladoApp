package com.jeffssousa.SimuladoApp.controller;

import com.jeffssousa.SimuladoApp.dto.QuestionAlternativeResponseDTO;
import com.jeffssousa.SimuladoApp.service.ExamAttemptService;
import com.jeffssousa.SimuladoApp.usecase.session.StartAttemptUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("attempt")
@RequiredArgsConstructor
public class ExamAttemptController {

    private final ExamAttemptService service;

    private final StartAttemptUseCase useCase;

    @PostMapping("/start/{id}")
    public ResponseEntity<Void> startAttempt(@PathVariable Long id){
        useCase.startAttempt(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/current-question/{id}")
    public ResponseEntity<QuestionAlternativeResponseDTO> getCurrentQuestion(@PathVariable UUID id){
        QuestionAlternativeResponseDTO response = service.getCurrentQuestion(id);
        return ResponseEntity.ok().body(response);
    }

}
