package com.compras.service;

import com.compras.controller.dto.FeedbackFornecedorRequest;
import com.compras.controller.dto.FornecedorRankingDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FornecedorFeedbackService {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String PYTHON_API = "http://localhost:5000";

    // ✅ Envia o feedback via requisição HTTP (não mais por ProcessBuilder)
    public void registrarFeedback(FeedbackFornecedorRequest req) {
        try {
            String url = PYTHON_API + "/api/feedback";

            Map<String, Object> payload = new HashMap<>();
            payload.put("fornecedor", req.getFornecedor());
            payload.put("reward", req.getReward());
            payload.put("motivo", req.getMotivo());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

            if (response.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("Falha ao enviar feedback: " + response.getStatusCode());
            }

            System.out.println("[PYTHON API] Feedback registrado: " + response.getBody());

        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar feedback para API Python: " + e.getMessage(), e);
        }
    }


    // ✅ Continua chamando script Python para recalcular scores
    public Object recalcularScores() {
        try {
            ProcessBuilder pb = new ProcessBuilder(
				//Chama API Python para recalcular
            );
            pb.redirectErrorStream(true);
            Process p = pb.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) json.append(line);
            p.waitFor();

            return json.toString();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao recalcular scores", e);
        }
    }


    // ✅ Mantém o ranking usando o recalculate
    public List<FornecedorRankingDto> getRanking(String produtoId, int top) {
        try {
            ProcessBuilder pb = new ProcessBuilder(
                //Chama API Python para recalcular
            );
            pb.redirectErrorStream(true);
            Process p = pb.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) sb.append(line);
            p.waitFor();

            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, Object>> rows = mapper.readValue(sb.toString(), List.class);

            List<FornecedorRankingDto> list = new ArrayList<>();
            for (Map<String, Object> r : rows) {
                //Chama Ranking dos fornecedores em Flask Python
            }

            list.sort(Comparator.comparingDouble(FornecedorRankingDto::score).reversed());
            return list.stream().limit(top).toList();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter ranking", e);
        }
    }
}
