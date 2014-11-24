package pl.edu.agh.universallib.entitylist;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import pl.edu.agh.universallib.entity.Entity;
import pl.edu.agh.universallib.entity.exception.EntityException;

public class EntityList extends Entity {

	Entity entityType;

	public EntityList(Entity entityType) {
		this.entityType = entityType;
	}

	private List<Entity> entities;

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	public List<Entity> mapEntities(String inputString) throws EntityException {
		try {
			if (inputString.charAt(0) == '<') {
				return mapEntitiesFromXml(inputString);
			} else if (inputString.charAt(0) == '{') {
				List<Entity> listToReturn = new ArrayList<Entity>();
				listToReturn.add(mapEntityFromJson(inputString));
				return listToReturn;
			} else if (inputString.charAt(0) == '['){
				return mapEntitiesFromJson(inputString);
			}
			else {
				throw new EntityException("Bad data, cannot map entity");
			}
		} catch (JsonParseException e) {
			throw new EntityException(e);
		} catch (JsonMappingException e) {
			throw new EntityException(e);
		} catch (IOException e) {
			throw new EntityException(e);
		}
	}

	private List<Entity> mapEntitiesFromJson(String inputString) throws EntityException, JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		TypeFactory typeFactory = objectMapper.getTypeFactory();
		return new ObjectMapper().readValue(inputString, typeFactory.constructCollectionType(List.class, entityType.getClass()));
	}

	private List<Entity> mapEntitiesFromXml(String inputString) throws EntityException {
		List<Entity> entities = new ArrayList<Entity>();
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = builderFactory.newDocumentBuilder();
			Document xmlDocument = builder.parse(new InputSource(new StringReader(inputString)));
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xPath = xPathfactory.newXPath();
			XPathExpression expr = xPath.compile("*/*");
			NodeList nodeList = (NodeList) expr.evaluate(xmlDocument, XPathConstants.NODESET);
			for (int i = 0; i < nodeList.getLength(); i++) {
				String node = nodeToString(nodeList.item(i));
				Entity entityToInsert = entityType.mapEntity(node);
				entities.add(entityToInsert);
			}
		} catch (ParserConfigurationException e) {
			throw new EntityException(e);
		} catch (SAXException e) {
			throw new EntityException(e);
		} catch (IOException e) {
			throw new EntityException(e);
		} catch (XPathExpressionException e) {
			throw new EntityException(e);
		} catch (TransformerException e) {
			throw new EntityException(e);
		}
		return entities;
	}

	private static String nodeToString(Node node) throws TransformerException {
		StringWriter buf = new StringWriter();
		Transformer xform = TransformerFactory.newInstance().newTransformer();
		xform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		xform.transform(new DOMSource(node), new StreamResult(buf));
		return (buf.toString());
	}
}
