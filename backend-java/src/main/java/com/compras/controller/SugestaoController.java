package com.compras.controller;

import com.compras.model.Sugestao;
import com.compras.service.SugestaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/compras")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SugestaoController {

    private final SugestaoService sugestaoService;

    @PostMapping("/sugestao")
    public Map<String, Object> gerarSugestao(@RequestBody Map<String, Object> payload) {
        String produtoId = (String) payload.get("produtoId");
        Integer quantidade = (Integer) payload.getOrDefault("quantidade", 1);

        return sugestaoService.gerarSugestaoComoMapa(produtoId, quantidade);
    }
}
