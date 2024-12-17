package org.mrpaulwoods.filtering.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmartResult<T> {

    private final List<SmartColumn> columns = new ArrayList<>();

    private Page<T> page;

    public void addColumn(SmartColumn smartColumn) {
        columns.add(smartColumn);
    }

    public void addColumns(List<SmartColumn> smartColumns) {
        this.columns.addAll(smartColumns);
    }

}
