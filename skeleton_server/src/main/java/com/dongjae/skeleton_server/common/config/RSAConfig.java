//package com.dongjae.skeleton_server.common.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//
//import java.security.KeyFactory;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//import java.util.Base64;
//
//@Configuration
//public class RSAConfig {
//
//    @Value("${rsa.public-key}")
//    private String publicKey;
//
//    @Value("${rsa.private-key}")
//    private String privateKey;
//
//    @Bean
//    public PublicKey publicKey() throws Exception {
//        String publicKeyPEM = publicKey
//                .replace("-----BEGIN PUBLIC KEY-----", "")
//                .replace("-----END PUBLIC KEY-----", "")
//                .replaceAll("\\s+", "");
//        System.out.println("public : " + publicKey);
//        byte[] decodedPublicKey = Base64.getDecoder().decode(publicKeyPEM);
//        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decodedPublicKey);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        return keyFactory.generatePublic(publicKeySpec);
//    }
//
//    @Bean
//    public PrivateKey privateKey() throws Exception{
//        String privateKeyPEM = privateKey
//                .replace("-----BEGIN PRIVATE KEY-----", "")
//                .replace("-----END PRIVATE KEY-----", "")
//                .replaceAll("\\s+", "");
//        System.out.println("private : " + privateKey);
//        byte[] decodedPrivateKey = Base64.getDecoder().decode(privateKeyPEM);
//        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decodedPrivateKey);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        return keyFactory.generatePrivate(privateKeySpec);
//    }
//}
