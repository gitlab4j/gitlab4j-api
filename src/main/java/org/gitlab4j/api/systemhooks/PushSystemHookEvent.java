package org.gitlab4j.api.systemhooks;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.gitlab4j.api.webhook.AbstractPushEvent;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PushSystemHookEvent extends AbstractPushEvent implements SystemHookEvent {
    public static final String PUSH_EVENT = "push";
}
