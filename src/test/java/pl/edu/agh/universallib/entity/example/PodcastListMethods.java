package pl.edu.agh.universallib.entity.example;

import pl.edu.agh.universallib.entitylist.EntityListMethods;
import pl.edu.agh.universallib.url.WebServiceType;

public class PodcastListMethods extends EntityListMethods<PodcastList> {

	public PodcastListMethods(String webServiceUrl, WebServiceType webServiceType) {
		super(PodcastList.class, webServiceUrl, webServiceType);
	}

	public String idPrefix = "";
	public String urlPart = "podcasts";
}
