//package com.compras.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
//import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
//import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
//
//import java.net.URI;
//import java.util.Optional;
//
///**
// * Configuração do DynamoDB (AWS SDK v2).
// *
// * - Use aws.dynamodb.region para definir a região (ex: sa-east-1).
// * - Para usar DynamoDB local / LocalStack, configure aws.dynamodb.endpointOverride como http://localhost:8000
// *
// * Certifique-se de que as dependências do AWS SDK no pom.xml usam a mesma versão
// * (por exemplo, software.amazon.awssdk:dynamodb e software.amazon.awssdk:dynamodb-enhanced).
// */
//
//
//@Configuration
//public class DynamoDBConfig {
//
//    /**
//     * Região AWS. Ex: sa-east-1. Pode ser sobrescrito via application.properties ou variável de ambiente.
//     */
//    @Value("${aws.dynamodb.region:sa-east-1}")
//    private String awsRegion;
//
//    /**
//     * Endpoint override (opcional). Ex: http://localhost:8000 para DynamoDB Local ou LocalStack.
//     * Deixe vazio para usar o endpoint padrão da AWS na região informada.
//     */
//    @Value("${aws.dynamodb.endpointOverride:}")
//    private String endpointOverride;
//
//    @Bean
//    public AwsCredentialsProvider awsCredentialsProvider() {
//        // DefaultCredentialsProvider busca credenciais nas fontes padrão (env vars, profile, EC2/ECS role)
//        return DefaultCredentialsProvider.create();
//    }
//
//    @Bean
//    public DynamoDbClient dynamoDbClient(AwsCredentialsProvider credentialsProvider) {
//        Region region = Region.of(awsRegion);
//
//        var builder = DynamoDbClient.builder()
//                .region(region)
//                .credentialsProvider(credentialsProvider);
//
//        // Se endpointOverride estiver preenchido, aplica (útil para DynamoDB local / LocalStack)
//        if (endpointOverride != null && !endpointOverride.isBlank()) {
//            // endpointOverride deve ser uma URI válida, ex: http://localhost:8000
//            builder.endpointOverride(URI.create(endpointOverride));
//        }
//
//        return builder.build();
//    }
//
//    @Bean
//    public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
//        return DynamoDbEnhancedClient.builder()
//                .dynamoDbClient(dynamoDbClient)
//                .build();
//    }
//}

package com.compras.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;

import java.net.URI;

@Configuration
public class DynamoDBConfig {

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient() {
        var dynamoDbClient = DynamoDbClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(
                        StaticCredentialsProvider.create(AwsBasicCredentials.create("dummy-key", "dummy-secret"))
                )
                .endpointOverride(URI.create("http://localhost:8000")) // DynamoDB Local
                .build();

        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }
}
