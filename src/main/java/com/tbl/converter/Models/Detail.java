package com.tbl.converter.Models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Detail {
    private String name;
    private String value;
}
