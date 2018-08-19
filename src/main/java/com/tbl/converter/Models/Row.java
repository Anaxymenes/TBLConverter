package com.tbl.converter.Models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Row {
    private String from;
    private String to;
    private String type;
    private List<Detail> details;
}
