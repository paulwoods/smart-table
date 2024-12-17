package org.mrpaulwoods.filtering.search;

import java.util.Objects;

public class SmartColumn {
    private String id;
    private String title;
    private boolean enableFilter = false;

    public SmartColumn() {
    }

    public SmartColumn(String id, String title) {
        this.id = id;
        this.title = title;

    }

    public SmartColumn(String id, String title, boolean enableFilter) {
        this.id = id;
        this.title = title;
        this.enableFilter = enableFilter;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isEnableFilter() {
        return enableFilter;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SmartColumn that = (SmartColumn) o;
        return enableFilter == that.enableFilter &&
                Objects.equals(id, that.id) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, enableFilter);
    }

    @Override
    public String toString() {
        return "SmartColumn{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", enableFilter=" + enableFilter +
                '}';
    }

}
