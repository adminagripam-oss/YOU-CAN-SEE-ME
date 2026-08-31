package com.pichillilorenzo.flutter_inappwebview_android.pull_to_refresh;

import com.pichillilorenzo.flutter_inappwebview_android.ISettings;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class PullToRefreshSettings implements ISettings<PullToRefreshLayout> {
    public static final String LOG_TAG = "PullToRefreshSettings";
    public String backgroundColor;
    public String color;
    public Integer distanceToTriggerSync;
    public Boolean enabled = Boolean.TRUE;
    public Integer size;
    public Integer slingshotDistance;

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public Map<String, Object> getRealSettings(PullToRefreshLayout pullToRefreshLayout) {
        return toMap();
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public /* bridge */ /* synthetic */ ISettings<PullToRefreshLayout> parse(Map map) {
        return parse2((Map<String, Object>) map);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("enabled", this.enabled);
        hashMap.put("color", this.color);
        hashMap.put("backgroundColor", this.backgroundColor);
        hashMap.put("distanceToTriggerSync", this.distanceToTriggerSync);
        hashMap.put("slingshotDistance", this.slingshotDistance);
        hashMap.put("size", this.size);
        return hashMap;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    /* renamed from: parse  reason: avoid collision after fix types in other method */
    public ISettings<PullToRefreshLayout> parse2(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                key.hashCode();
                char c8 = 65535;
                switch (key.hashCode()) {
                    case -1878912765:
                        if (key.equals("distanceToTriggerSync")) {
                            c8 = 0;
                            break;
                        }
                        break;
                    case -1609594047:
                        if (key.equals("enabled")) {
                            c8 = 1;
                            break;
                        }
                        break;
                    case 3530753:
                        if (key.equals("size")) {
                            c8 = 2;
                            break;
                        }
                        break;
                    case 94842723:
                        if (key.equals("color")) {
                            c8 = 3;
                            break;
                        }
                        break;
                    case 1287124693:
                        if (key.equals("backgroundColor")) {
                            c8 = 4;
                            break;
                        }
                        break;
                    case 1719097976:
                        if (key.equals("slingshotDistance")) {
                            c8 = 5;
                            break;
                        }
                        break;
                }
                switch (c8) {
                    case 0:
                        this.distanceToTriggerSync = (Integer) value;
                        continue;
                    case 1:
                        this.enabled = (Boolean) value;
                        continue;
                    case 2:
                        this.size = (Integer) value;
                        continue;
                    case 3:
                        this.color = (String) value;
                        continue;
                    case 4:
                        this.backgroundColor = (String) value;
                        continue;
                    case 5:
                        this.slingshotDistance = (Integer) value;
                        continue;
                }
            }
        }
        return this;
    }
}
