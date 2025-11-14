package com.compras.service;

import com.compras.model.Sugestao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import org.springframework.http.*;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class SugestaoService {

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> gerarSugestaoComoMapa(String produtoId, Integer quantidade) {
        try {
            String url = "http://localhost:5000/api/sugestao";

            Map<String, Object> payload = new HashMap<>();
            payload.put("produtoId", produtoId);
            payload.put("quantidade", quantidade);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

            if (response.getBody() == null) {
                throw new RuntimeException("Resposta vazia do serviço Python");
            }

            return response.getBody(); // 👈 devolve exatamente o que o Python respondeu

        } catch (Exception e) {
            throw new RuntimeException("Falha ao chamar API Python: " + e.getMessage(), e);
        }
    }
}
