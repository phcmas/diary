package diary.utility;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import diary.dto.enums.ProjectType;

import java.io.IOException;

// 아직 사용하지 않은 클래스. 언젠가 필요할지도 몰라서 남겨둠
public class EnumDeserializer extends StdSerializer {

    protected EnumDeserializer(Class t) {
        super(t);
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value instanceof ProjectType) {
            ProjectType type = (ProjectType) value;
            gen.writeStartObject();
            gen.writeFieldName("projectType");
            gen.writeString(type.getString());
            gen.writeEndObject();
        }

    }
}
