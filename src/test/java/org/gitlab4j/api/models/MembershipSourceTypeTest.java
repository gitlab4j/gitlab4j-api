package org.gitlab4j.api.models;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MembershipSourceTypeTest {

    @Test
    public void forValue() {
        final MembershipSourceType namespace = MembershipSourceType.forValue("Namespace");
        Assert.assertEquals(MembershipSourceType.NAMESPACE, namespace);
        Assert.assertEquals("Namespace", namespace.toValue());
        Assert.assertEquals("Namespace", namespace.toString());
        final MembershipSourceType project = MembershipSourceType.forValue("Project");
        Assert.assertEquals(MembershipSourceType.PROJECT, project);
        Assert.assertEquals("Project", project.toValue());
        Assert.assertEquals("Project", project.toString());
    }
}
