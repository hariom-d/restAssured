package com.models;

import lombok.*;

import java.util.List;

@Builder
//@ToString
//@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Root{
    public int page;
    public int per_page;
    public int total;
    public int total_pages;
    public List<Datum> data;
    public Support support;
}