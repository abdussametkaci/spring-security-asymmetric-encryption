package com.example.springsecurityasymmetricencryption.service

import com.example.springsecurityasymmetricencryption.configuration.properties.DecodedKeyProperties
import com.example.springsecurityasymmetricencryption.configuration.properties.ECAuthenticationProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ECTokenService(private val ecAuthenticationProperties: ECAuthenticationProperties) {

    fun generateToken(): String {
        val issuedAt = LocalDateTime.now()
        val expiresAt = issuedAt.plusSeconds(ecAuthenticationProperties.tokenExpirationTime)

        /*
        val privateKeyByteArray = Base64.getDecoder().decode(ECAuthenticationProperties.authPrivateKey)
        val privateKeySpec = PKCS8EncodedKeySpec(privateKeyByteArray)
        val keyFactory = KeyFactory.getInstance("EC")
        val privateKey = keyFactory.generatePrivate(privateKeySpec)
        */

        return Jwts.builder()
            .setHeaderParam("kid", "1")
            .setHeaderParam("typ", "JWT")
            .setHeaderParam("alg", "ES256")
            .setSubject("ba28c1dd-df05-4d79-a8ce-cfcf0be8707b")
            .setIssuedAt(issuedAt.toDate())
            .setExpiration(expiresAt.toDate())
            .setIssuer(ecAuthenticationProperties.tokenIssuerId)
            .signWith(DecodedKeyProperties.decodedECPrivateKey, SignatureAlgorithm.ES256)
            .setId("ba28c1dd-df05-4d79-a8ce-cfcf0be8707b")
            .compact()
    }

    fun getClaims(token: String): Claims {
        /*
        val publicKeyByteArray = Base64.getDecoder().decode(ECAuthenticationProperties.authPublicKey)
        val publicKeySpec = X509EncodedKeySpec(publicKeyByteArray)
        val keyFactory = KeyFactory.getInstance("EC")
        val publicKey = keyFactory.generatePublic(publicKeySpec)
        */

        return try {
            Jwts.parserBuilder()
                .setSigningKey(DecodedKeyProperties.decodedECPublicKey)
                .requireIssuer(ecAuthenticationProperties.tokenIssuerId)
                .build()
                .parseClaimsJws(token)
                .body ?: throw IllegalStateException("illegal")
        } catch (ex: Exception) {
            throw IllegalStateException("invalid")
        }
    }
}
