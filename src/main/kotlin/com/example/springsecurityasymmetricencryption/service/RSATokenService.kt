package com.example.springsecurityasymmetricencryption.service

import com.example.springsecurityasymmetricencryption.configuration.properties.DecodedKeyProperties
import com.example.springsecurityasymmetricencryption.configuration.properties.RSAAuthenticationProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

@Service
class RSATokenService(private val rsaAuthenticationProperties: RSAAuthenticationProperties) {

    fun generateToken(): String {
        val issuedAt = LocalDateTime.now()
        val expiresAt = issuedAt.plusSeconds(rsaAuthenticationProperties.tokenExpirationTime)

        /*
        val privateKeyByteArray = Base64.getDecoder().decode(RSAAuthenticationProperties.authPrivateKey)
        val privateKeySpec = PKCS8EncodedKeySpec(privateKeyByteArray)
        val keyFactory = KeyFactory.getInstance("RSA")
        val privateKey = keyFactory.generatePrivate(privateKeySpec)
        */

        return Jwts.builder()
            .setSubject("ba28c1dd-df05-4d79-a8ce-cfcf0be8707b")
            .setIssuedAt(issuedAt.toDate())
            .setExpiration(expiresAt.toDate())
            .setIssuer(rsaAuthenticationProperties.tokenIssuerId)
            .signWith(DecodedKeyProperties.decodedRSAPrivateKey, SignatureAlgorithm.RS256)
            .setId("ba28c1dd-df05-4d79-a8ce-cfcf0be8707b")
            .compact()
    }

    fun getClaims(token: String): Claims {
        /*
        val publicKeyByteArray = Base64.getDecoder().decode(RSAAuthenticationProperties.authPublicKey)
        val publicKeySpec = X509EncodedKeySpec(publicKeyByteArray)
        val keyFactory = KeyFactory.getInstance("RSA")
        val publicKey = keyFactory.generatePublic(publicKeySpec)
        */

        return try {
            Jwts.parserBuilder()
                .setSigningKey(DecodedKeyProperties.decodedRSAPublicKey)
                .requireIssuer(rsaAuthenticationProperties.tokenIssuerId)
                .build()
                .parseClaimsJws(token)
                .body ?: throw IllegalStateException("illegal")
        } catch (ex: Exception) {
            throw IllegalStateException("invalid")
        }
    }
}

fun LocalDateTime.toDate(): Date {
    return Date.from(this.atZone(ZoneId.systemDefault()).toInstant())
}
