package pl.edu.agh.universallib.entity;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import pl.edu.agh.universallib.entity.exception.EntityException;

abstract public class Entity {

	private ObjectMapper objectMapper = new ObjectMapper();

	protected Entity mapEntityFromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		return objectMapper.readValue(json, this.getClass());
	}

	public String mapToJson(boolean includeNulls) throws JsonGenerationException, JsonMappingException, IOException {
		objectMapper.setSerializationInclusion(includeNulls ? Inclusion.ALWAYS : Inclusion.NON_NULL);
		ObjectWriter ow = objectMapper.writer();
		return ow.writeValueAsString(this);
	}
	
	private Entity mapEntityFromXml(String xml) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(this.getClass());
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Source streamSource = new StreamSource(new StringReader(xml));
		JAXBElement<?> je1 = jaxbUnmarshaller.unmarshal(streamSource, this.getClass());
		return (Entity) je1.getValue();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String mapToXml() throws JAXBException{
		JAXBContext jaxbContext = JAXBContext.newInstance(this.getClass());
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
		StringWriter writer = new StringWriter();
		JAXBElement jaxbElement = new JAXBElement(new QName(this.getClass().getSimpleName()), this.getClass(), this);
		jaxbMarshaller.marshal(jaxbElement, writer);
		String result = writer.toString();
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	public Entity mapEntity(String inputString) {
		try {
			try {
				if (inputString.charAt(0) == '<')
					return mapEntityFromXml(inputString);
				else if (inputString.charAt(0) == '{')
					return mapEntityFromJson(inputString);
				else
					throw new EntityException("Bad data, cannot map entity");
			} catch (JAXBException e) {
				throw new EntityException("Could not bind XML to Entity", e);
			} catch (JsonParseException e) {
				throw new EntityException("Could not parse JSON", e);
			} catch (JsonMappingException e) {
				throw new EntityException("Could not bind JSON to Entity", e);
			} catch (IOException e) {
				throw new EntityException("IO Exception", e);
			}
		} catch (EntityException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}


}
