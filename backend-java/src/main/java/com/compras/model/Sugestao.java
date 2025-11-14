package com.compras.model;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.time.LocalDateTime;

@DynamoDbBean
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sugestao {

    private String id;
    private String produtoId;
    private LocalDateTime dataSugestao;
    private String motivo;
    private Double precoSugerido;
    private String fornecedorEscolhido;

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }
}
