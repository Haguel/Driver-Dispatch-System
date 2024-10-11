package dev.haguel.dds.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import dev.haguel.dds.model.AppUserRole;

import java.io.IOException;
import java.util.Set;

public class AppUserRoleSerializer extends StdSerializer<Set<AppUserRole>> {

    public AppUserRoleSerializer() {
        this(null);
    }

    public AppUserRoleSerializer(Class<Set<AppUserRole>> t) {
        super(t);
    }

    @Override
    public void serialize(Set<AppUserRole> roles, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartArray();
        for (AppUserRole role : roles) {
            gen.writeStartObject();
            gen.writeNumberField("id", role.getId());
            gen.writeStringField("roleName", role.getRole().getRoleName().name());
            gen.writeEndObject();
        }
        gen.writeEndArray();
    }
}
