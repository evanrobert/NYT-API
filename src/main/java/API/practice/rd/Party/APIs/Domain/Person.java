package API.practice.rd.Party.APIs.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
    @JsonProperty("firstname")
    private String first_name;
    @JsonProperty("middle_name")
    private String middle_name;
    @JsonProperty("lastname")
    private String last_name;
    private String qualifier;
    private String title;
    private String role;
    private String organization;
    private int rank;
}
