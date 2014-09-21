package pl.edu.agh.universallib.entity.example;

import java.util.Date;

import pl.edu.agh.universallib.entity.Entity;

public class Podcast extends Entity {
	public Long id;
	public String title;
	public String linkOnPodcastpedia;
	public String feed;
	public String description; 
	public Date insertionDate;		
}
