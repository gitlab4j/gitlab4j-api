package org.gitlab4j.api.models;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MembershipSourceTypeTest {

    @Test
    public void forValue() {
        final MembershipSourceType namespace = MembershipSourceType.forValue(MembershipSourceType.NAMESPACE.name);
        Assert.assertEquals(MembershipSourceType.NAMESPACE, namespace);
        Assert.assertEquals("Namespace", namespace.name);
        final MembershipSourceType project = MembershipSourceType.forValue(MembershipSourceType.PROJECT.name);
        Assert.assertEquals(MembershipSourceType.PROJECT, project);
        Assert.assertEquals("Project", project.name);
    }
}
