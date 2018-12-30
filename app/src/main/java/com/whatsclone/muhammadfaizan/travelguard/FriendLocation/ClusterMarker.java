package com.whatsclone.muhammadfaizan.travelguard.FriendLocation;

import android.graphics.Bitmap;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

public class ClusterMarker implements ClusterItem {
    private LatLng position; // required field
    private String title; // required field
    private String snippet; // required field
    private Bitmap iconPicture;

    public ClusterMarker(LatLng position, String title, String snippet, Bitmap iconPicture) {
        this.position = position;
        this.title = title;
        this.snippet = snippet;
        this.iconPicture = iconPicture;
    }

    @Override
    public LatLng getPosition() {
        return position;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public Bitmap getIconPicture() {
        return iconPicture;
    }

    public void setIconPicture(Bitmap iconPicture) {
        this.iconPicture = iconPicture;
    }
}
