package com.compras.controller;

import com.compras.controller.dto.CompraRealizadaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/compras")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CompraRealizadaController {


    private final RestTemplate restTemplate;
    private static final String PYTHON_API = "http://localhost:5000";

    @PostMapping("/realizadas")
    public ResponseEntity<?> registrarCompra(@RequestBody CompraRealizadaDTO dto) {
        String url = PYTHON_API + "/api/registrar-compra";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CompraRealizadaDTO> entity = new HttpEntity<>(dto, headers);

        restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
        return ResponseEntity.ok(Map.of("mensagem", "Compra registrada com sucesso ✅"));
    }

    @GetMapping("/historico")
    public ResponseEntity<?> listarHistorico() {
        String url = PYTHON_API + "/api/historico";
        ResponseEntity<Object[]> response = restTemplate.getForEntity(url, Object[].class);
        return ResponseEntity.ok(response.getBody());
    }

    @PostMapping("/atualizar-historico")
    public ResponseEntity<?> atualizarHistorico() {
        String url = PYTHON_API + "/api/atualizar-historico";
        restTemplate.postForEntity(url, null, Map.class);
        return ResponseEntity.ok(Map.of("mensagem", "Histórico processado pelo agente ✅"));
    }

    @PostMapping("/atualizar-score-tendencia")
    public ResponseEntity<?> atualizarScoreTendencia() {
        String url = PYTHON_API + "/api/atualizar-score-tendencia";
        restTemplate.postForEntity(url, null, Map.class);
        return ResponseEntity.ok(Map.of("mensagem", "Score e tendência atualizados com sucesso ✅"));
    }

}
