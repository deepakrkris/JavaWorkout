package com.samsung.project.model;

public class InventoryItem {
    String item_id;
    String item_type;

    public InventoryItem(String id, String type) {
        this.item_id = id;
        this.item_type = type;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }
}
