package net.dubrouski.fams.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.model.AccommodationComposite;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Place;
import net.dubrouski.fams.model.Price;
import net.dubrouski.fams.model.Room;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class AccommodationDeserializer extends JsonDeserializer<AccommodationUnit>{
	
	@Override
	public AccommodationUnit deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = parser.getCodec();
		JsonNode node = oc.readTree(parser);
		String type = node.get("type").getTextValue();
		AccommodationUnit u;
		
		if(type.equals("room")){
			u = new Room();
		} 
		else if (type.equals("place")) {
			u = new Place();
		} 
		else {
			throw new FmsException("Deserialization error: unknown Accommodation type: " + type);
		}
		u.setType(node.get("type").asText());
		
		if(node.get("id") != null){
			u.setId(node.get("id").asLong());
		}		
		if(node.get("depositAmount") != null){
			u.setDepositAmount(BigDecimal.valueOf(node.get("depositAmount").asDouble()));
		}	
		if(node.get("isActive") != null){
			u.setIsActive(node.get("isActive").asBoolean());	
		}
		if(node.get("name") != null){
			u.setName(node.get("name").asText());
		}
		
//		ObjectMapper mapper = new ObjectMapper();
		
//		if(node.get("address") != null){
//			u.setAddress(mapper.readValue(node.get("address"), Address.class));	
//		}
//		if(node.get("price") != null){
//			u.setPrice(mapper.readValue(node.get("price"), Price.class));	
//		}
//		
//		if(u.getType() != "place" && node.get("children") != null){
//			AccommodationComposite c =  (AccommodationComposite) u;
//			String s = node.get("children").toString();
//			List<AccommodationUnit> asList = mapper.readValue(s, new TypeReference<List<AccommodationUnit>>() { });
//			c.setChildren(asList);
//			u = c;
//		}
		return u;
	}
}
