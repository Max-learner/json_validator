package org.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "MainId",
        "firstName",
        "lastName",
        "violinPlayer",
        "categories"
})
public class Detective {

    @Getter
    @Setter
    @JsonProperty("MainId")
    public Integer mainId;

    @Getter
    @Setter
    @JsonProperty("firstName")
    public String firstName;

    @Getter
    @Setter
    @JsonProperty("lastName")
    public String lastName;

    @Getter
    @Setter
    @JsonProperty("violinPlayer")
    public Boolean violinPlayer;

    @Getter
    @Setter
    @JsonProperty("categories")
    public List<DetectiveCategory> categories;
}
