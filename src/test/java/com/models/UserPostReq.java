package com.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

//@Builder
//@ToString
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@JsonDeserialize(builder = UserPostReq.UserPostReqBuilder.class)
@Builder(builderClassName = "UserPostReqBuilder", toBuilder = true)
public class UserPostReq {
    String name;
    String job;

    @JsonPOJOBuilder(withPrefix = "")
    public static class UserPostReqBuilder {
    }
}
