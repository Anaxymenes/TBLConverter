package com.tbl.converter.Models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Sequence {
    private String name;
    List<Row> rows;
}
