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
        "violin",
        "cap"
})
public class ExtraArray {

    @Getter
    @Setter
    @JsonProperty("violin")
    public Integer violin;

    @Getter
    @Setter
    @JsonProperty("cap")
    public Integer cap;

}
