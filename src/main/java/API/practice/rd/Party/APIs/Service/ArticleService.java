package API.practice.rd.Party.APIs.Service;

import API.practice.rd.Party.APIs.Domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Value("${api_key}")
    private String apikey;

    @Value("${mostPopularUrl}")
    private String mostPopularUrl;

    @Autowired
    RestTemplate restTemplate;


    public List<Article> getMostPopular() {
        NytResponse response = restTemplate.getForObject(mostPopularUrl + "api-key=" + apikey, NytResponse.class);
        List<Article> articles = new ArrayList<>();
        if (response != null && response.getStatus().equals("OK")) {
            articles = response.getResults();
            for (Article article : articles) {
                List<Media> media = article.getMedia();
                if (media != null && !media.isEmpty()) {
                    List<Thumbnail> thumbnails = media.get(0).getMediaMetadata();
                    if (!thumbnails.isEmpty()) {
                        article.setImageUrl(thumbnails.get(0).getUrl());

                    }
                }
            }
        }
        return articles;
    }

    public List<Doc> getSearchResults(String searchText) {
        String url = mostPopularUrl + "q=" + searchText + "&api-key=" + apikey;
        ResponseEntity<NytSearchResponse> searchResponse = restTemplate.getForEntity(url, NytSearchResponse.class);
        NytSearchResponse searchResponseBody = searchResponse.getBody();
        List<Doc> docs = new ArrayList<>();
        if (searchResponseBody != null && searchResponseBody.getStatus().equals("OK")) {
            docs = searchResponseBody.getDocs();
            for (Doc doc : docs) {
                List<Media> mediaList = doc.getMedia();
                for (Media media : mediaList) {
                    List<Multimedia> multimediaList = media.getMultimedia();
                    for (Multimedia multimedia : multimediaList) {
                        if (multimedia.getSubType().equals("largeHorizontal375")) {
                            String imageUrl = "https://www.nytimes.com/" + multimedia.getUrl();
                            doc.setImageUrl(imageUrl);
                            doc.setWeb_url(doc.getWeb_url());
                        }
                    }
                }
            }
        }
        return docs;
    }
}





















