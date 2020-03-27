package uk.gov.justice.digital.oasys.util;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class MockOAuthServer extends WireMockServer {

    public MockOAuthServer() {
        super(9010);
    }

    private static final String JWKS_RESPONSE = "{\"keys\": [{\n" +
            "\"kty\": \"RSA\",\n" +
            "\"e\": \"AQAB\",\n" +
            "\"kid\": \"dev-jwk-kid\",\n" +
            "\"n\": \"x7MkpuxUofy1Xw0GCPJrxLHdZd7IPPkpw67emT6-PMkmAcbPB2WqIc_vnKU-ala_vguk3kCOKYQA-choYZWX_9KbLSWwxr-FojNZehs35rNnqeKayhXyQc4g5VnatA6vAOv1D_jWeTQQH8-hvxJAbxCHiGSif-l2Waf3S-dExuAhq_02bWE89QKZXm6SJtII3MzkVhkc5G6c289KKU_t66pTD_peWQb77875ATFt1zz5MxJFcLfaKkNdmPzVNihDsav0yKDUlGr8pQyfuRsn8ZHZOmIuh4FAs6-M9vMGC_2P8jxyFXYfut_C_0lRvj7Y7tcR2pSYu05k_iF_A9m3JQ\"\n" +
            "}]}";

    private static final String OIDC_RESPONSE = "{\n" +
            "    \"issuer\": \"http://localhost:9010/auth/issuer\",\n" +
            "    \"jwks_uri\": \"http://localhost:9010/.well-known/jwks.json\"\n" +
            "}";

    public void stubJwksSet() {
        stubFor(
                get(urlEqualTo("/.well-known/jwks.json"))
                        .willReturn(aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBody(JWKS_RESPONSE)));

        stubFor(
                get(urlEqualTo("/auth/issuer/.well-known/openid-configuration"))
                        .willReturn(aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBody(OIDC_RESPONSE)));
    }
}