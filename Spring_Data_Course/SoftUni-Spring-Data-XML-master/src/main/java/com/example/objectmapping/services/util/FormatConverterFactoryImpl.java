package com.example.objectmapping.services.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class FormatConverterFactoryImpl implements FormatConverterFactory {

    private final FormatConverter xml;
    private final FormatConverter json;
    private final ApplicationContext ctx;

    public FormatConverterFactoryImpl(@Qualifier("xml_format_converter")FormatConverter xml, @Qualifier("json_format_converter") FormatConverter json, ApplicationContext ctx) {
        this.xml = xml;
        this.json = json;
        this.ctx = ctx;
    }

    @Override
    public FormatConverter create(String formatType) {
        return switch (formatType.toUpperCase(Locale.ROOT)) {
            case "xml" -> this.xml;
            case "json" -> this.json;
            default -> null;
        };
    }
}
