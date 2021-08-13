package com.example.football.util.impl;

import com.example.football.exception.UnableToConvertException;
import com.example.football.util.FormatConverter;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component("xml_format_converter")
public class XMLFormatConverter implements FormatConverter {

    private Map<String, Marshaller> marshallers = new HashMap<>();

    private boolean prettyPrint = false;

    @Override
    public void setPrettyPrint() {
        prettyPrint = true;
    }

    @Override
    public String serialize(Object obj) throws UnableToConvertException {
        try {
            StringWriter sw = new StringWriter();
            this.getMarshaller(obj).marshal(
                    obj,
                    sw
            );

            return sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new UnableToConvertException();
        }
    }

    @Override
    public void serialize(Object obj, String fileName) throws UnableToConvertException {
        try {
            FileWriter fw = new FileWriter(fileName);
            this.getMarshaller(obj).marshal(
                    obj,
                    fw
            );
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            throw new UnableToConvertException();
        }
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public <T> T deserialize(String format, Class<T> toType) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(toType);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            ByteArrayInputStream inputStream = new ByteArrayInputStream(format.getBytes());
            return  (T) unmarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public <T> T deserializeFromFile(String fileName, Class<T> toType) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(toType);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            return  (T) unmarshaller.unmarshal(new File(fileName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Marshaller getMarshaller(Object obj){
        try {
            if (this.marshallers.containsKey(obj.getClass().getName())){
                return this.marshallers.get(obj.getClass().getName());
            }

            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, this.prettyPrint);

            this.marshallers.put(
                    obj.getClass().getName(),
                    marshaller
            );

            return marshaller;
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }
}
