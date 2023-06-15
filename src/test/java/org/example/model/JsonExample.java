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
        "detectives",
        "success"
})
public class JsonExample {

    @Getter
    @Setter
    @JsonProperty("detectives")
    public List<Detective> detectives;

    @Getter
    @Setter
    @JsonProperty("success")
    public Boolean success;
}