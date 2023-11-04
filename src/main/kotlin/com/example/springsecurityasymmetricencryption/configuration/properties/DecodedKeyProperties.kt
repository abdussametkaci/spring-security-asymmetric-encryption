package com.example.springsecurityasymmetricencryption.configuration.properties

import java.security.PrivateKey
import java.security.PublicKey

object DecodedKeyProperties {
    var decodedRSAPrivateKey: PrivateKey? = null
    var decodedRSAPublicKey: PublicKey? = null
    var decodedECPrivateKey: PrivateKey? = null
    var decodedECPublicKey: PublicKey? = null
}
