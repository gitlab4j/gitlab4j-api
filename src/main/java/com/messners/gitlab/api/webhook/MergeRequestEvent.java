package com.messners.gitlab.api.webhook;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class MergeRequestEvent extends EventObject {

    public static final String OBJECT_KIND = "merge_request";

    private EventObjectAttributes objectAttributes;

    public EventObjectAttributes getObjectAttributes() {
        return this.objectAttributes;
    }

    public void setObjectAttributes(EventObjectAttributes objectAttributes) {
        this.objectAttributes = objectAttributes;
    }
}
