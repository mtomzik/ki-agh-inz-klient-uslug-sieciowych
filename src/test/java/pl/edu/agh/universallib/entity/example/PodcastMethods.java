package pl.edu.agh.universallib.entity.example;

import pl.edu.agh.universallib.entity.EntityMethods;
import pl.edu.agh.universallib.url.WebServiceType;

public class PodcastMethods extends EntityMethods {
	
	public PodcastMethods(String serviceUrl, WebServiceType webServiceType) {
		super(serviceUrl, webServiceType);
	}
	public String idPrefix = "";
	public String urlPart = "podcasts";
}
