package API.practice.rd.Party.APIs.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor

public class Byline {

    private String original;
    private List<Person>person;
    private String organization;

}
