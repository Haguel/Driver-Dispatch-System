package dev.haguel.dds.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import dev.haguel.dds.model.CargoOrder;

import java.io.IOException;

public class CargoOrderSerializer extends StdSerializer<CargoOrder> {
    public CargoOrderSerializer() {
        this(null);
    }

    public CargoOrderSerializer(Class<CargoOrder> t) {
        super(t);
    }

    @Override
    public void serialize(CargoOrder cargoOrder, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", cargoOrder.getId());
        jsonGenerator.writeStringField("cargoType", cargoOrder.getCargoType());
        jsonGenerator.writeNumberField("cargoAmount", cargoOrder.getCargoAmount());
        jsonGenerator.writeNumberField("payout", cargoOrder.getPayout());
        jsonGenerator.writeNumberField("daysTillComplete", cargoOrder.getDaysTillComplete());
        jsonGenerator.writeEndObject();
    }
}
