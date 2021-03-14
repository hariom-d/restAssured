package com.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

//@Builder
//@ToString
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Data
public class UserPostResp {
    String name;
    String job;
    String id;
    String createdAt;
}
