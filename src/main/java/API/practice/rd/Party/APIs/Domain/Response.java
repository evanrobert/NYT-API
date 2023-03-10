package API.practice.rd.Party.APIs.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Response {


    private List<Multimedia> multimedia;
    private List<Doc> docs;
}
