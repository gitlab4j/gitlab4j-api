package org.gitlab4j.models.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class MultiDateFormatDeserializer extends StdDeserializer<Date> {
    public static final String[] DATE_FORMATS =
            new String[] {"yyyy-MM-dd HH:mm:ss Z", "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", "yyyy-MM-dd'T'HH:mm:ssXXX"};
    private static final long serialVersionUID = 1L;

    public MultiDateFormatDeserializer() {
        this(null);
    }

    public MultiDateFormatDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        final String date = ((JsonNode) jp.getCodec().readTree(jp)).textValue();
        if (date == null || date.isEmpty()) {
            return null;
        }
        for (String dateFormat : DATE_FORMATS) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
                Date parsedDate = formatter.parse(date);
                return parsedDate;
            } catch (ParseException e) {
            }
        }
        throw new JsonParseException(
                jp, "Unparseable date: \"" + date + "\". Supported formats: " + Arrays.toString(DATE_FORMATS));
    }
}
