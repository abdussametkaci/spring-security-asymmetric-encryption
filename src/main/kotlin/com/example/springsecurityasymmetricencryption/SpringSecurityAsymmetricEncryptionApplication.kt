package com.example.springsecurityasymmetricencryption

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class SpringSecurityAsymmetricEncryptionApplication

fun main(args: Array<String>) {
	runApplication<SpringSecurityAsymmetricEncryptionApplication>(*args)
}
