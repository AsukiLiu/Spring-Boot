package org.asuki.springboot.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static com.google.common.base.Strings.isNullOrEmpty;

public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper(String dateFormatPattern) {

        setSerializationInclusion(JsonInclude.Include.NON_NULL);

        configure(SerializationFeature.INDENT_OUTPUT, true);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

        if (!isNullOrEmpty(dateFormatPattern)) {
            DateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
            setDateFormat(dateFormat);
        }
    }
}
