package com.whatsclone.muhammadfaizan.travelguard.FriendLocation;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.whatsclone.muhammadfaizan.travelguard.R;

public class MyClusterManagerRenderer extends DefaultClusterRenderer<ClusterMarker> {
    private IconGenerator iconGenerator;
    private ImageView imageView;
    private int width;
    private int height;

    public MyClusterManagerRenderer(Context context, GoogleMap googleMap,
                                    ClusterManager<ClusterMarker> clusterManager) {

        super(context, googleMap, clusterManager);

        // initialize cluster item icon generator
        iconGenerator = new IconGenerator(context.getApplicationContext());
        imageView = new ImageView(context.getApplicationContext());
        width = (int) context.getResources().getDimension(R.dimen.custom_marker_image);
        height = (int) context.getResources().getDimension(R.dimen.custom_marker_image);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(150, 150));
        int padding = (int) context.getResources().getDimension(R.dimen.custom_marker_padding);
        imageView.setPadding(2, 2, 2, 2);
        iconGenerator.setContentView(imageView);

    }


    @Override
    protected void onBeforeClusterItemRendered(ClusterMarker item, MarkerOptions markerOptions) {

        imageView.setImageBitmap(item.getIconPicture());
        Bitmap icon = iconGenerator.makeIcon();
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title(item.getTitle());
    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster cluster) {
        return false;
    }
}
