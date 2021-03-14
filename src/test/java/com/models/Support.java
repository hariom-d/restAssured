package com.models;

import lombok.*;

@Builder
//@ToString
//@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Support{
    public String url;
    public String text;
}