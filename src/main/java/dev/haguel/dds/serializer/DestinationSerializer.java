package dev.haguel.dds.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import dev.haguel.dds.model.Destination;

import java.io.IOException;

public class DestinationSerializer extends StdSerializer<Destination> {
    public DestinationSerializer() {
        this(null);
    }

    public DestinationSerializer(Class<Destination> t) {
        super(t);
    }

    @Override
    public void serialize(Destination destination, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", destination.getId());
        jsonGenerator.writeStringField("country", destination.getCountry());
        jsonGenerator.writeStringField("city", destination.getCity());
        jsonGenerator.writeStringField("address", destination.getAddress());
        jsonGenerator.writeEndObject();
    }


}
