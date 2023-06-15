package org.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
                    "CategoryID",
                    "CategoryName",
                    "extra"
                    })
public class DetectiveCategory {

    @Getter
    @Setter
    @JsonProperty("CategoryID")
    public Integer categoryID;

    @Getter
    @Setter
    @JsonProperty("CategoryName")
    public String categoryName;

    @Getter
    @Setter
    @JsonProperty("extra")
    public Extra extra;
}