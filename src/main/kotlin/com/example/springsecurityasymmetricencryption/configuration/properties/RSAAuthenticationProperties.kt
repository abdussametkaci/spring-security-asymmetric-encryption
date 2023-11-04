package com.example.springsecurityasymmetricencryption.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "authentication.rsa")
data class RSAAuthenticationProperties(
    var authPrivateKey: String,
    var authPublicKey: String,
    var tokenIssuerId: String,
    var tokenExpirationTime: Long
)
