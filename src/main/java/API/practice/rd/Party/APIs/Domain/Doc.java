package API.practice.rd.Party.APIs.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class Doc {
    @JsonProperty("abstract")
    private String abs;
    private String web_url;
    private String snippet;
    private String lead_paragraph;
    private String source;
    private String imageUrl;

    public List<Media> media;


    }

