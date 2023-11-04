package com.example.springsecurityasymmetricencryption.controller

import com.example.springsecurityasymmetricencryption.service.ECTokenService
import com.example.springsecurityasymmetricencryption.service.RSATokenService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/token")
class TokenController(
    private val rsaTokenService: RSATokenService,
    private val ecTokenService: ECTokenService
) {

    @PostMapping("/rsa")
    fun getRSAToken() = rsaTokenService.generateToken()

    @PostMapping("/rsa/claims")
    fun parseRSAToken(@RequestBody token: String) = rsaTokenService.getClaims(token)

    @PostMapping("/ec")
    fun getECToken() = ecTokenService.generateToken()

    @PostMapping("/ec/claims")
    fun parseECToken(@RequestBody token: String) = ecTokenService.getClaims(token)
}
