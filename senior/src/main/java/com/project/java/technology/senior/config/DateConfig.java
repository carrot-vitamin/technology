package com.project.java.technology.senior.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author za-yinshaobo at 2020/12/8 15:56
 */
@Configuration
public class DateConfig {

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Bean
    @Primary
    public ObjectMapper serializingObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(Date.class, new DateSerializer());
//        javaTimeModule.addDeserializer(Date.class, new DateDeserializer());
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }

    static class DateSerializer extends JsonSerializer<Date> {

        @Override
        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            DateFormat dateFormat = new SimpleDateFormat(PATTERN);
            //以指定的格式统一输出
            jsonGenerator.writeString(dateFormat.format(date));
        }
    }

//    static class DateDeserializer extends JsonDeserializer<Date> {
//
//        @Override
//        public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
//            DateFormat dateFormat = new SimpleDateFormat(PATTERN);
//            String date = jsonParser.getText();
//            try {
//                return dateFormat.parse(date);
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
}
