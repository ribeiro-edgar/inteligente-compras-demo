package com.compras.controller;

import com.compras.controller.dto.*;
import com.compras.service.FornecedorFeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/fornecedores")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Permite chamadas do Angular (localhost:4200 ou CloudFront)
public class FornecedorController {

    private final FornecedorFeedbackService fornecedorFeedbackService;

    @PostMapping("/feedback")
    public ResponseEntity<?> registrarFeedback(@RequestBody FeedbackFornecedorRequest request) {
        fornecedorFeedbackService.registrarFeedback(request);
        return ResponseEntity.ok(Map.of("mensagem", "Feedback registrado e modelo atualizado ✅"));
    }

    @PostMapping("/avaliar")
    public ResponseEntity<?> recalcularScores() {
        var result = fornecedorFeedbackService.recalcularScores();
        return ResponseEntity.ok(result);
    }

    // GET /api/fornecedores/ranking?produtoId=AA1234&top=5
    @GetMapping("/ranking")
    public List<FornecedorRankingDto> ranking(@RequestParam String produtoId,
                                              @RequestParam(required=false, defaultValue="3") int top) {
        return fornecedorFeedbackService.getRanking(produtoId, top);
    }
}
