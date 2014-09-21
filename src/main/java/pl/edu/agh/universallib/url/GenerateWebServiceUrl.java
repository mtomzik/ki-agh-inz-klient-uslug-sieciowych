package pl.edu.agh.universallib.url;

import java.lang.reflect.Field;

import pl.edu.agh.universallib.api.exception.ProcessingException;
import pl.edu.agh.universallib.entity.Entity;
import pl.edu.agh.universallib.entity.EntityMethods;

public class GenerateWebServiceUrl {

	public GenerateWebServiceUrl() {
		this.webServiceType = WebServiceType.REST;
	}

	private WebServiceType webServiceType;

	public WebServiceType getWebServiceType() {
		return webServiceType;
	}

	public void setWebServiceType(WebServiceType webServiceType) {
		this.webServiceType = webServiceType;
	}

	public String generateUrl(Entity e, EntityMethods operation)
			throws ProcessingException {
		StringBuilder sb = new StringBuilder();
		try {
			
			String separator;
			if (webServiceType.equals(WebServiceType.CLASSIC)) {
				sb.append("/");
				sb.append(operation.getUrlPart());
				separator = "?";
				sb.append(separator);
				sb.append(operation.getClass().getSimpleName());
				for (Field f : operation.getClass().getFields()) {
					sb.append("&");
					sb.append(f.getName());
					sb.append("=");
				}

			} else if (webServiceType.equals(WebServiceType.REST)) {
				separator = "/";
				sb.append(separator);
				sb.append(operation.getUrlPart());
				sb.append(separator);
				try {
					sb.append(operation.getIdPrefix());
				} catch (SecurityException e1) {
					throw new ProcessingException(e1);
				}
			}
		} catch (Exception e2) {
			throw new ProcessingException(e2);
		}
		return sb.toString();

	}

}
