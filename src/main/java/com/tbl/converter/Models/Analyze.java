package com.tbl.converter.Models;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;

@Builder
@Data
public class Analyze {
    private HashSet<String> atributes;
}
