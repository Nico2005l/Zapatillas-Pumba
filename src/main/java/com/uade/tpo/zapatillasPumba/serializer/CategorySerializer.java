package com.uade.tpo.zapatillasPumba.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.uade.tpo.zapatillasPumba.entity.Category;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class CategorySerializer extends JsonSerializer<Category> {
    @Override
    public void serialize(Category category, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", category.getId());
        
        if (category.getSexo() != null) {
            gen.writeStringField("sexo", category.getSexo().name());
            gen.writeFieldName("children");
            gen.writeStartArray();
            List<Category> children = category.getChildren();
            if (children != null) {
                for (Category child : children) {
                    provider.defaultSerializeValue(child, gen);
                }
            }
            gen.writeEndArray();
        } else if (category.getTipo() != null) {
            gen.writeStringField("tipo", category.getTipo().name());
            gen.writeFieldName("products");
            gen.writeStartArray();
            if (category.getProducts() != null) {
                provider.defaultSerializeValue(category.getProducts(), gen);
            }
            gen.writeEndArray();
        }
        
        gen.writeEndObject();
    }
}