package org.gitlab4j.api.services;

import org.gitlab4j.api.GitLabApiForm;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EmailOnPushService extends NotificationService {

    public static final String RECIPIENT_PROP = "recipients";
    public static final String DISABLE_DIFFS_PROP = "disable_diffs";
    public static final String SEND_FROM_COMMITTER_EMAIL = "send_from_committer_email";
    public static final String BRANCHES_TO_BE_NOTIFIED = "branches_to_be_notified";

	@Override
	public GitLabApiForm servicePropertiesForm() {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam(RECIPIENT_PROP, getRecipients(), true)
                .withParam(DISABLE_DIFFS_PROP, getDisableDiffs())
                .withParam(SEND_FROM_COMMITTER_EMAIL, getSendFromCommitterEmail())
                .withParam(PUSH_EVENTS_PROP, getPushEvents())
                .withParam("tag_push_events", getTagPushEvents())
                .withParam(BRANCHES_TO_BE_NOTIFIED, getBranchesToBeNotified());
            return formData;
	}

    public EmailOnPushService withPushEvents(Boolean pushEvents) {
        return withPushEvents(pushEvents, this);
    }
    public EmailOnPushService withTagPushEvents(Boolean pushEvents) {
        return withTagPushEvents(pushEvents, this);
    }

    @JsonIgnore
	public String getRecipients() {
		return ((String)getProperty(RECIPIENT_PROP));
	}
	public void setRecipients(String recipients) {
		setProperty(RECIPIENT_PROP, recipients);
	}
    public EmailOnPushService withRecipients(String recipients) {
    	setRecipients(recipients);
    	return this;
    }


    @JsonIgnore
    public Boolean getDisableDiffs() {
		return Boolean.valueOf(getProperty(DISABLE_DIFFS_PROP,"false"));
	}
	public void setDisableDiffs(Boolean disableDiffs) {
		setProperty(DISABLE_DIFFS_PROP, disableDiffs);
	}
	public EmailOnPushService withDisableDiffs(Boolean disableDiffs) {
		setDisableDiffs(disableDiffs);
		return this;
	}
	
    @JsonIgnore
	public Boolean getSendFromCommitterEmail() {
		return Boolean.valueOf(getProperty(SEND_FROM_COMMITTER_EMAIL,"false"));
	}
	public void setSendFromCommitterEmail(Boolean sendFromCommitterEmail) {
		setProperty(SEND_FROM_COMMITTER_EMAIL, sendFromCommitterEmail);
	}
	public EmailOnPushService withSendFromCommitterEmail(Boolean sendFromCommitterEmail) {
		setSendFromCommitterEmail(sendFromCommitterEmail);
		return this;
	}

    @JsonIgnore
	public String getBranchesToBeNotified() {
		return ((String)getProperty(BRANCHES_TO_BE_NOTIFIED));
	}
	public void setBranchesToBeNotified(String branchesToBeNotified) {
		setProperty(BRANCHES_TO_BE_NOTIFIED, branchesToBeNotified);
	}
	public EmailOnPushService withBranchesToBeNotified(String branchesToBeNotified) {
		setBranchesToBeNotified(branchesToBeNotified);
		return this;
	}


}
