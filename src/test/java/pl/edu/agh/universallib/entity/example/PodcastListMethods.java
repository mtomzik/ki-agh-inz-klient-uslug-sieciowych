package pl.edu.agh.universallib.entity.example;

import pl.edu.agh.universallib.entitylist.EntityListMethods;
import pl.edu.agh.universallib.url.WebServiceType;

public class PodcastListMethods extends EntityListMethods {

	public PodcastListMethods(String webServiceUrl, WebServiceType webServiceType) {
		super(webServiceUrl, webServiceType);
	}

	public String idPrefix = "";
	public String urlPart = "podcasts";
}
