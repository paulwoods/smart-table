package org.mrpaulwoods.filtering.search;

import lombok.Data;

import java.util.List;

@Data
public class SmartListCriteria {
    private String columnName;
    private String condition;
    private List<String> listFilterValue;

}
