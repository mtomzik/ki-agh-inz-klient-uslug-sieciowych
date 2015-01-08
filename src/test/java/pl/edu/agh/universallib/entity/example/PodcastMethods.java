package pl.edu.agh.universallib.entity.example;

import pl.edu.agh.universallib.entity.EntityMethods;
import pl.edu.agh.universallib.url.WebServiceType;

public class PodcastMethods extends EntityMethods<Podcast> {
	
	public PodcastMethods(String serviceUrl, WebServiceType webServiceType) {
		super(Podcast.class, serviceUrl, webServiceType);
	}
	public String idPrefix = "";
	public String urlPart = "podcasts";
}
