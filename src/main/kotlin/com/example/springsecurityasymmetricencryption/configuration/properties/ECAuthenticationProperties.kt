package com.example.springsecurityasymmetricencryption.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "authentication.ec")
data class ECAuthenticationProperties(
    var authPrivateKey: String,
    var authPublicKey: String,
    var tokenIssuerId: String,
    var tokenExpirationTime: Long
)
