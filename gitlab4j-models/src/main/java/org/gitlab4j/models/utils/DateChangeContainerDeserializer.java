package org.gitlab4j.models.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import org.gitlab4j.api.webhook.ChangeContainer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class DateChangeContainerDeserializer extends StdDeserializer<ChangeContainer<Date>> {

    private static final long serialVersionUID = 1L;

    public static final String[] DATE_FORMATS = new String[] {
        "yyyy-MM-dd HH:mm:ss Z", "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", "yyyy-MM-dd'T'HH:mm:ssXXX", "yyyy-MM-dd"
    };

    public DateChangeContainerDeserializer() {
        super((Class<?>) null);
    }

    @Override
    public ChangeContainer<Date> deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);

        Date previous = parseDate(node.get("previous"));
        Date current = parseDate(node.get("current"));

        ChangeContainer<Date> container = new ChangeContainer<>();
        container.setPrevious(previous);
        container.setCurrent(current);

        return container;
    }

    private Date parseDate(JsonNode dateNode) throws JsonParseException {
        if (dateNode == null || dateNode.isNull()) {
            return null;
        }

        String dateStr = dateNode.asText();
        if (dateStr.isEmpty()) {
            return null;
        }

        for (String format : DATE_FORMATS) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
                sdf.setLenient(false);
                return sdf.parse(dateStr);
            } catch (ParseException e) {
                // optional: Logging
            }
        }

        throw new JsonParseException(
                null, "Unparseable date: \"" + dateStr + "\". Supported formats: " + Arrays.toString(DATE_FORMATS));
    }
}
