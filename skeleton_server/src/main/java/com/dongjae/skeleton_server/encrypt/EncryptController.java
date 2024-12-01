//package com.dongjae.skeleton_server.encrypt;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.crypto.Cipher;
//import java.nio.charset.StandardCharsets;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.util.Base64;
//
//@RestController("/encrypt")
//public class EncryptController {
//
//    private final PublicKey publicKey;
//    private final PrivateKey privateKey;
//
//    public EncryptController(PublicKey publicKey, PrivateKey privateKey) {
//        this.publicKey = publicKey;
//        this.privateKey = privateKey;
//    }
//
//    @GetMapping("/get/encode")
//    public String getPublicKey() {
//        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
//    }
//
//    @PostMapping("/decrypt")
//    public String decrypt(@RequestBody String encryptedData) throws Exception {
//        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
//
//        Cipher cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//        byte[] decryptedData = cipher.doFinal(decodedData);
//
//        return new String(decryptedData, StandardCharsets.UTF_8);
//    }
//}
