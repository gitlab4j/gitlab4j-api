package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GpgSignature implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The ID of the GPG key.
     */
    @JsonProperty("gpg_key_id")
    private Long gpgKeyId;

    /**
     * The primary key ID of the GPG key.
     */
    @JsonProperty("gpg_key_primary_keyid")
    private String gpgKeyPrimaryKeyid;

    /**
     * The username associated with the GPG key.
     */
    @JsonProperty("gpg_key_user_name")
    private String gpgKeyUserName;

    /**
     * The email address associated with the GPG key.
     */
    @JsonProperty("gpg_key_user_email")
    private String gpgKeyUserEmail;

    /**
     * The verification status of the GPG key.
     */
    @JsonProperty("verification_status")
    private String verificationStatus;

    /**
     * The subkey ID of the GPG key.
     */
    @JsonProperty("gpg_key_subkey_id")
    private String gpgKeySubkeyId;

    public Long getGpgKeyId() {
        return gpgKeyId;
    }

    public void setGpgKeyId(Long gpgKeyId) {
        this.gpgKeyId = gpgKeyId;
    }

    public String getGpgKeyPrimaryKeyid() {
        return gpgKeyPrimaryKeyid;
    }

    public void setGpgKeyPrimaryKeyid(String gpgKeyPrimaryKeyid) {
        this.gpgKeyPrimaryKeyid = gpgKeyPrimaryKeyid;
    }

    public String getGpgKeyUserName() {
        return gpgKeyUserName;
    }

    public void setGpgKeyUserName(String gpgKeyUserName) {
        this.gpgKeyUserName = gpgKeyUserName;
    }

    public String getGpgKeyUserEmail() {
        return gpgKeyUserEmail;
    }

    public void setGpgKeyUserEmail(String gpgKeyUserEmail) {
        this.gpgKeyUserEmail = gpgKeyUserEmail;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getGpgKeySubkeyId() {
        return gpgKeySubkeyId;
    }

    public void setGpgKeySubkeyId(String gpgKeySubkeyId) {
        this.gpgKeySubkeyId = gpgKeySubkeyId;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
