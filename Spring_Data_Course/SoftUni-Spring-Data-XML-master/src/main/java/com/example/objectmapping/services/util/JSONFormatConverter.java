package com.example.objectmapping.services.util;

import com.example.objectmapping.exception.UnableToConvertException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Component("json_format_converter")
public class JSONFormatConverter implements FormatConverter{

    private GsonBuilder gsonBuilder;
    private Gson gson;

    public JSONFormatConverter(GsonBuilder gsonBuilder) {
        this.gsonBuilder = gsonBuilder;

    }

    @Override
    public void setPrettyPrint(){
        this.gsonBuilder.setPrettyPrinting();
        this.gson = null;
    }



    @Override
    public String serialize(Object obj) throws UnableToConvertException {
        return this.getGson().toJson(obj);
    }

    @Override
    public void serialize(Object obj, String fileName) throws UnableToConvertException {
        try (FileWriter fw = new FileWriter(fileName)){
          this.getGson().toJson(
                    obj,
                    fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> T deserialize(String format, Class<T> toType) throws JAXBException {
        return this.getGson().fromJson(format, toType);
    }

    @Override
    public <T> T deserializeFromFile(String fileName, Class<T> toType) {
        try (FileReader fileReader = new FileReader(fileName)) {
            return this.getGson().fromJson(fileReader, toType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private  Gson getGson(){
        if (this.gson == null){
            this.gson = this.gsonBuilder.create();
        }
        return this.gson;
    }
}
