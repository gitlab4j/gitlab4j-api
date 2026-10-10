package org.gitlab4j.api;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * see <a href="https://docs.gitlab.com/user/project/integrations/webhooks/#verify-the-signature">Verify the signature</a>
 */
class HMACWebhookSignatureVerifier implements WebhookSignatureVerifier {

    private static final String ALGORITHM = "HmacSHA256";
    private static final String WHSEC_PREFIX = "whsec_";

    private final SecretKeySpec signingKey;

    HMACWebhookSignatureVerifier(String signingToken) {
        this.signingKey = new SecretKeySpec(Base64.getDecoder().decode(removePrefix(signingToken)), ALGORITHM);
    }

    @Override
    public boolean isSignatureValid(String messageId, String timestamp, String signatures, String body) {
        String message = messageId + "." + timestamp + "." + body;
        byte[] digest = getDigest(message);
        byte[] expected = ("v1," + Base64.getEncoder().encodeToString(digest)).getBytes(UTF_8);
        return Arrays.stream(signatures.split(" "))
                .map(signature -> signature.getBytes(UTF_8))
                .anyMatch(signature -> MessageDigest.isEqual(expected, signature));
    }

    private byte[] getDigest(String message) {
        try {
            Mac mac = Mac.getInstance(ALGORITHM);
            mac.init(signingKey);
            return mac.doFinal(message.getBytes(UTF_8));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    private String removePrefix(String value) {
        return value.startsWith(WHSEC_PREFIX) ? value.substring(WHSEC_PREFIX.length()) : value;
    }
}
