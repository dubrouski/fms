package net.dubrouski.fams.util;

import java.io.IOException;

import net.dubrouski.fams.model.Address;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class AddressSerializer extends JsonSerializer<Address>{

	@Override
	public void serialize(Address a, JsonGenerator generator,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		generator.writeStartObject();
		generator.writeNumberField("id", a.getId());
		generator.writeStringField("city", a.getCity());
		generator.writeStringField("streetName", a.getStreetName());
		generator.writeStringField("streetNumber", a.getStreetNumber());
		generator.writeStringField("flatNumber", a.getFlatNumber());
		generator.writeNumber(a.getLatitude());
		generator.writeNumber(a.getLongitude());
		generator.writeFieldName("country");
		generator.writeObject(a.getCountry());
		generator.writeEndObject();
	}

}
