package edu.utsa.cs3773.thebestyou.model;

public class DashboardItem {
    private final int imageResId;
    private final String title;

    public DashboardItem(int imageResId, String title) {
        this.imageResId = imageResId;
        this.title = title;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }
}

