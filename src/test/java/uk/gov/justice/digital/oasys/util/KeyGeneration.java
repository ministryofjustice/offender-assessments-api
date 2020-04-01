package uk.gov.justice.digital.oasys.util;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;

/**
 * Tool used to generate key pairs and signed tokens for use in the integration tests
 */
public class KeyGeneration {

    public KeyGeneration() throws ParseException, JOSEException, URISyntaxException, IOException {
        ClassLoader classLoader = getClass().getClassLoader();

        RSAKey rsaJWK = RSAKey.parse(Files.readString(Path.of(classLoader.getResource("jwt/rsaJWK.json").getFile())));

        System.out.println("Public JWK: " + rsaJWK.toPublicJWK().toJSONString());

        System.out.println("Signed token: " + getSignedToken(rsaJWK, Files.readString(Path.of(classLoader.getResource("jwt/token.json").getFile()))));

        System.out.println("Signed invalid role token: " + getSignedToken(rsaJWK, Files.readString(Path.of(classLoader.getResource("jwt/token_invalid_role.json").getFile()))));

        System.out.println("Signed auth role token: " + getSignedToken(rsaJWK, Files.readString(Path.of(classLoader.getResource("jwt/token_auth_role.json").getFile()))));

    }

    private String getSignedToken(RSAKey rsaJWK, String token) throws JOSEException {
        // Create RSA-signer with the private key
        JWSSigner signer = new RSASSASigner(rsaJWK);

        // Prepare JWS object with simple string as payload
        JWSObject jwsObject = new JWSObject(
                new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaJWK.getKeyID()).build(),
                new Payload(token));

        // Compute the RSA signature
        jwsObject.sign(signer);

        return jwsObject.serialize();
    }

    private void generateRSAKey(Path path) throws JOSEException, IOException {
        RSAKey rsaJWK = new RSAKeyGenerator(2048)
                .keyID("dev-jwk-kid")
                .generate();

        Files.writeString(path, rsaJWK.toJSONString());
    }

    public static void main(String[] args) throws URISyntaxException, IOException, ParseException, JOSEException {
        new KeyGeneration();
    }
}