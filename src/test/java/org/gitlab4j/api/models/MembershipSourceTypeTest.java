package org.gitlab4j.api.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MembershipSourceTypeTest {

    @Test
    public void forValue() {
        final MembershipSourceType namespace = MembershipSourceType.forValue("Namespace");
        assertEquals(MembershipSourceType.NAMESPACE, namespace);
        assertEquals("Namespace", namespace.toValue());
        assertEquals("Namespace", namespace.toString());
        final MembershipSourceType project = MembershipSourceType.forValue("Project");
        assertEquals(MembershipSourceType.PROJECT, project);
        assertEquals("Project", project.toValue());
        assertEquals("Project", project.toString());
    }
}
