package org.gitlab4j.models.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.gitlab4j.api.webhook.ChangeContainer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class DateWithTimeChangeContainerSerializer extends StdSerializer<ChangeContainer<Date>> {

    public DateWithTimeChangeContainerSerializer() {
        super((Class<ChangeContainer<Date>>) null);
    }

    @Override
    public void serialize(ChangeContainer<Date> value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        gen.writeStartObject();
        if (value.getPrevious() != null) {
            gen.writeStringField("previous", sdf.format(value.getPrevious()));
        }
        if (value.getCurrent() != null) {
            gen.writeStringField("current", sdf.format(value.getCurrent()));
        }
        gen.writeEndObject();
    }
}
