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

    @Value("${apikey}")
    private String apikey;


    @Value("${mostPopularUrl}")
    private String mostPopularUrl;

    @Value("${search}")
    private String search;

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
        String searchUrl = search + "q=" + searchText + "&api-key=" + apikey;
        String mostSearchedUrl = "https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=" + apikey + "&sort=popular";
        List<Doc> docs = new ArrayList<>();

        // Get search results
        ResponseEntity<NytSearchResponse> searchResponse = restTemplate.getForEntity(searchUrl, NytSearchResponse.class);
        NytSearchResponse searchResponseBody = searchResponse.getBody();

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

        // Get most searched articles
        ResponseEntity<NytSearchResponse> mostSearchedResponse = restTemplate.getForEntity(mostSearchedUrl, NytSearchResponse.class);
        NytSearchResponse mostSearchedResponseBody = mostSearchedResponse.getBody();

        if (mostSearchedResponseBody != null && mostSearchedResponseBody.getStatus().equals("OK")) {
            List<Doc> mostSearchedDocs = mostSearchedResponseBody.getDocs();
            docs.addAll(mostSearchedDocs);
            for (Doc doc : mostSearchedDocs) {
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

















