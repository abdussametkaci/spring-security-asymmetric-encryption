package com.example.springsecurityasymmetricencryption.configuration

import com.example.springsecurityasymmetricencryption.configuration.properties.DecodedKeyProperties
import com.example.springsecurityasymmetricencryption.configuration.properties.ECAuthenticationProperties
import com.example.springsecurityasymmetricencryption.configuration.properties.RSAAuthenticationProperties
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration
import java.security.KeyFactory
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.Base64

@Configuration
class KeyDecodeConfiguration(
    private val rsaAuthenticationProperties: RSAAuthenticationProperties,
    private val ecAuthenticationProperties: ECAuthenticationProperties
) {

    @PostConstruct
    fun decodeRSAPrivate() {
        val privateKeyByteArray = Base64.getDecoder().decode(rsaAuthenticationProperties.authPrivateKey)
        val privateKeySpec = PKCS8EncodedKeySpec(privateKeyByteArray)
        val keyFactory = KeyFactory.getInstance("RSA")
        DecodedKeyProperties.decodedRSAPrivateKey = keyFactory.generatePrivate(privateKeySpec)
    }

    @PostConstruct
    fun decodeRSAPublic() {
        val publicKeyByteArray = Base64.getDecoder().decode(rsaAuthenticationProperties.authPublicKey)
        val publicKeySpec = X509EncodedKeySpec(publicKeyByteArray)
        val keyFactory = KeyFactory.getInstance("RSA")
        DecodedKeyProperties.decodedRSAPublicKey = keyFactory.generatePublic(publicKeySpec)
    }

    @PostConstruct
    fun decodeECPrivate() {
        val privateKeyByteArray = Base64.getDecoder().decode(ecAuthenticationProperties.authPrivateKey)
        val privateKeySpec = PKCS8EncodedKeySpec(privateKeyByteArray)
        val keyFactory = KeyFactory.getInstance("EC")
        DecodedKeyProperties.decodedECPrivateKey = keyFactory.generatePrivate(privateKeySpec)
    }

    @PostConstruct
    fun decodeECPublic() {
        val publicKeyByteArray = Base64.getDecoder().decode(ecAuthenticationProperties.authPublicKey)
        val publicKeySpec = X509EncodedKeySpec(publicKeyByteArray)
        val keyFactory = KeyFactory.getInstance("EC")
        DecodedKeyProperties.decodedECPublicKey = keyFactory.generatePublic(publicKeySpec)
    }
}
