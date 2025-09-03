package org.gitlab4j.models.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.gitlab4j.api.webhook.ChangeContainer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class OnlyDateChangeContainerSerializer extends StdSerializer<ChangeContainer<Date>> {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public OnlyDateChangeContainerSerializer() {
        super((Class<ChangeContainer<Date>>) null);
    }

    @Override
    public void serialize(ChangeContainer<Date> value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);

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
