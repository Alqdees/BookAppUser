package com.alqdees.bookapp.Model;

import java.util.HashMap;

public class ModelCategory {
    String id, category,timestamp;

    public ModelCategory() {

    }

    public ModelCategory(String id, String category, String timestamp) {
        this.id = id;
        this.category = category;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}