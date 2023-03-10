package API.practice.rd.Party.APIs.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NytSearchResponse {
    private String status;
   private String copyright;



    private List<Doc> docs;
}
