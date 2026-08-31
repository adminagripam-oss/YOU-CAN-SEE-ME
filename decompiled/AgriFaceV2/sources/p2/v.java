package p2;

import android.location.Location;
import android.os.Build;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class v {
    private static boolean a(Location location) {
        boolean isMock;
        if (Build.VERSION.SDK_INT >= 31) {
            isMock = location.isMock();
            return isMock;
        }
        return location.isFromMockProvider();
    }

    public static Map b(Location location) {
        boolean hasMslAltitude;
        double mslAltitudeMeters;
        boolean hasMslAltitudeAccuracy;
        float mslAltitudeAccuracyMeters;
        if (location == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("latitude", Double.valueOf(location.getLatitude()));
        hashMap.put("longitude", Double.valueOf(location.getLongitude()));
        hashMap.put("timestamp", Long.valueOf(location.getTime()));
        hashMap.put("is_mocked", Boolean.valueOf(a(location)));
        if (location.hasAltitude()) {
            hashMap.put("altitude", Double.valueOf(location.getAltitude()));
        }
        int i8 = Build.VERSION.SDK_INT;
        if (location.hasVerticalAccuracy()) {
            hashMap.put("altitude_accuracy", Float.valueOf(location.getVerticalAccuracyMeters()));
        }
        if (location.hasAccuracy()) {
            hashMap.put("accuracy", Double.valueOf(location.getAccuracy()));
        }
        if (location.hasBearing()) {
            hashMap.put("heading", Double.valueOf(location.getBearing()));
        }
        if (location.hasBearingAccuracy()) {
            hashMap.put("heading_accuracy", Float.valueOf(location.getBearingAccuracyDegrees()));
        }
        if (location.hasSpeed()) {
            hashMap.put("speed", Double.valueOf(location.getSpeed()));
        }
        if (location.hasSpeedAccuracy()) {
            hashMap.put("speed_accuracy", Double.valueOf(location.getSpeedAccuracyMetersPerSecond()));
        }
        if (location.getExtras() != null) {
            if (location.getExtras().containsKey("geolocator_mslAltitude")) {
                hashMap.put("altitude", Double.valueOf(location.getExtras().getDouble("geolocator_mslAltitude")));
            }
            if (location.getExtras().containsKey("geolocator_mslSatelliteCount")) {
                hashMap.put("gnss_satellite_count", Double.valueOf(location.getExtras().getDouble("geolocator_mslSatelliteCount")));
            }
            if (location.getExtras().containsKey("geolocator_mslSatellitesUsedInFix")) {
                hashMap.put("gnss_satellites_used_in_fix", Double.valueOf(location.getExtras().getDouble("geolocator_mslSatellitesUsedInFix")));
            }
            if (i8 >= 34) {
                hasMslAltitude = location.hasMslAltitude();
                if (hasMslAltitude) {
                    mslAltitudeMeters = location.getMslAltitudeMeters();
                    hashMap.put("altitude", Double.valueOf(mslAltitudeMeters));
                    hasMslAltitudeAccuracy = location.hasMslAltitudeAccuracy();
                    if (hasMslAltitudeAccuracy) {
                        mslAltitudeAccuracyMeters = location.getMslAltitudeAccuracyMeters();
                        hashMap.put("altitude_accuracy", Float.valueOf(mslAltitudeAccuracyMeters));
                    }
                }
            }
        }
        return hashMap;
    }
}
