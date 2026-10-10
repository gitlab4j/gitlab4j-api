package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestHMACWebhookSignatureVerifier {

    private static final String SIGNING_TOKEN = "MfKQ9r8GKYqrTwjUPD8ILPZIo2LaLaSw";
    private static final String MESSAGE_ID = "msg_p5jXN8AQM9LWM0D4loKWxJek";
    private static final String TIMESTAMP = "1614265330";
    private static final String BODY = "{\"test\": 2432232314}";
    private static final String SIGNATURE = "v1,g0hM9SsE+OTPJTGt/tmIKtSyZlE3uFJELVlNIOLJ1OE=";

    private HMACWebhookSignatureVerifier verifier = new HMACWebhookSignatureVerifier(SIGNING_TOKEN);

    @Test
    public void validSignature() {
        assertTrue(verifier.isSignatureValid(MESSAGE_ID, TIMESTAMP, SIGNATURE, BODY));
    }

    @Test
    public void validSignatureWithTokenAsGeneratedByGitLab() {
        verifier = new HMACWebhookSignatureVerifier("whsec_" + SIGNING_TOKEN);
        assertTrue(verifier.isSignatureValid(MESSAGE_ID, TIMESTAMP, SIGNATURE, BODY));
    }

    @Test
    public void validSignatureAmongMultipleSignatures() {
        assertTrue(verifier.isSignatureValid(MESSAGE_ID, TIMESTAMP, "v1,bogus= " + SIGNATURE + " v2,other=", BODY));
    }

    @Test
    public void invalidSignature() {
        assertFalse(verifier.isSignatureValid(
                MESSAGE_ID, TIMESTAMP, "v1,AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=", BODY));
    }

    @Test
    public void signatureWithoutVersionPrefixIsRejected() {
        assertFalse(verifier.isSignatureValid(MESSAGE_ID, TIMESTAMP, SIGNATURE.substring("v1,".length()), BODY));
    }

    @Test
    public void emptySignatureIsRejected() {
        assertFalse(verifier.isSignatureValid(MESSAGE_ID, TIMESTAMP, "", BODY));
    }

    @Test
    public void signatureOfAnotherSigningTokenIsRejected() {
        verifier = new HMACWebhookSignatureVerifier("T1hFRHwzL0h6ZUxKcW5kR1VYbWZ3QT09");
        assertFalse(verifier.isSignatureValid(MESSAGE_ID, TIMESTAMP, SIGNATURE, BODY));
    }

    @Test
    public void tamperedMessageIdIsRejected() {
        assertFalse(verifier.isSignatureValid("msg_other", TIMESTAMP, SIGNATURE, BODY));
    }

    @Test
    public void tamperedTimestampIsRejected() {
        assertFalse(verifier.isSignatureValid(MESSAGE_ID, "1614265331", SIGNATURE, BODY));
    }

    @Test
    public void tamperedBodyIsRejected() {
        assertFalse(verifier.isSignatureValid(MESSAGE_ID, TIMESTAMP, SIGNATURE, "{}"));
    }

    @Test
    public void nonBase64SigningTokenFails() {
        assertThrows(IllegalArgumentException.class, () -> new HMACWebhookSignatureVerifier("not base64!"));
    }
}
