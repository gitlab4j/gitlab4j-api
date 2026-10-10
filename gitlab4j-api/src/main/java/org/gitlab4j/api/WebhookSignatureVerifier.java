package org.gitlab4j.api;

/**
 * Verifies the authenticity and integrity of incoming webhook requests.
 * <p>
 * Implementations validate one or more webhook signatures against the
 * received payload and associated metadata. Multiple signatures may be
 * provided to support signature rotation or future protocol extensions.
 * Signature values are expected to be separated by whitespace.
 * </p>
 */
public interface WebhookSignatureVerifier {

    /**
     * Validates the signature(s) of a webhook request.
     *
     * @param messageId  the unique identifier of the webhook message
     * @param timestamp  the timestamp supplied with the webhook request
     * @param signatures one or more signatures provided by the webhook sender,
     *                   separated by whitespace
     * @param body       the raw request body used for signature verification
     * @return {@code true} if at least one signature can be successfully verified
     *         and the request is considered authentic; {@code false} otherwise
     */
    boolean isSignatureValid(String messageId, String timestamp, String signatures, String body);
}
