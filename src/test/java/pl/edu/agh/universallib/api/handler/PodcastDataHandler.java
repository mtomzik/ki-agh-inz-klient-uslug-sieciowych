package pl.edu.agh.universallib.api.handler;

import pl.edu.agh.universallib.api.handler.WebServiceDataHandler;
import pl.edu.agh.universallib.entity.Entity;
import pl.edu.agh.universallib.entity.example.Podcast;
import pl.edu.agh.universallib.entity.example.PodcastList;

public class PodcastDataHandler implements WebServiceDataHandler {

	private Exception exception;
	private Podcast result;
	
	@Override
	public void processData(Entity data, Exception e) {
		if (data != null ) {
			if (data instanceof Podcast){
				System.out.println("Successfully received Podcast entity");
				result = (Podcast) data;
			}
			if (data instanceof PodcastList){
				System.out.println("Successfully received Podcast List entity");
				PodcastList list = (PodcastList) data;
				if (list.getEntities().size() > 0){
					result = (Podcast) list.getEntities().get(0);
				}
			}
			System.out.println(data);
		}
		if (e != null) {
			exception = e;
			e.printStackTrace();
		}
	}

	public Exception getError(){
		return exception;
	}

	public Podcast getResult() {
		return result;
	}
	
	@Deprecated
	public String getData(){
		return null;
	}
}
