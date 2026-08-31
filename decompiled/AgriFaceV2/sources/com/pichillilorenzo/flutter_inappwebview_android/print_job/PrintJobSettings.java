package com.pichillilorenzo.flutter_inappwebview_android.print_job;

import androidx.browser.customtabs.b;
import com.pichillilorenzo.flutter_inappwebview_android.ISettings;
import com.pichillilorenzo.flutter_inappwebview_android.types.MediaSizeExt;
import com.pichillilorenzo.flutter_inappwebview_android.types.ResolutionExt;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class PrintJobSettings implements ISettings<PrintJobController> {
    public static final String LOG_TAG = "PrintJobSettings";
    public Integer colorMode;
    public Integer duplexMode;
    public Boolean handledByClient = Boolean.FALSE;
    public String jobName;
    public MediaSizeExt mediaSize;
    public Integer orientation;
    public ResolutionExt resolution;

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public Map<String, Object> getRealSettings(PrintJobController printJobController) {
        return toMap();
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public /* bridge */ /* synthetic */ ISettings<PrintJobController> parse(Map map) {
        return parse2((Map<String, Object>) map);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("handledByClient", this.handledByClient);
        hashMap.put("jobName", this.jobName);
        hashMap.put("orientation", this.orientation);
        MediaSizeExt mediaSizeExt = this.mediaSize;
        hashMap.put("mediaSize", mediaSizeExt != null ? mediaSizeExt.toMap() : null);
        hashMap.put("colorMode", this.colorMode);
        hashMap.put("duplexMode", this.duplexMode);
        ResolutionExt resolutionExt = this.resolution;
        hashMap.put("resolution", resolutionExt != null ? resolutionExt.toMap() : null);
        return hashMap;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    /* renamed from: parse  reason: avoid collision after fix types in other method */
    public ISettings<PrintJobController> parse2(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                key.hashCode();
                char c8 = 65535;
                switch (key.hashCode()) {
                    case -1600030548:
                        if (key.equals("resolution")) {
                            c8 = 0;
                            break;
                        }
                        break;
                    case -1498106493:
                        if (key.equals("duplexMode")) {
                            c8 = 1;
                            break;
                        }
                        break;
                    case -1439500848:
                        if (key.equals("orientation")) {
                            c8 = 2;
                            break;
                        }
                        break;
                    case -1438096408:
                        if (key.equals("jobName")) {
                            c8 = 3;
                            break;
                        }
                        break;
                    case -244986274:
                        if (key.equals("handledByClient")) {
                            c8 = 4;
                            break;
                        }
                        break;
                    case 1980724134:
                        if (key.equals("colorMode")) {
                            c8 = 5;
                            break;
                        }
                        break;
                    case 2140418565:
                        if (key.equals("mediaSize")) {
                            c8 = 6;
                            break;
                        }
                        break;
                }
                switch (c8) {
                    case 0:
                        this.resolution = ResolutionExt.fromMap((Map) value);
                        continue;
                    case 1:
                        this.duplexMode = (Integer) value;
                        continue;
                    case 2:
                        this.orientation = (Integer) value;
                        continue;
                    case 3:
                        this.jobName = (String) value;
                        continue;
                    case 4:
                        this.handledByClient = (Boolean) value;
                        continue;
                    case 5:
                        this.colorMode = (Integer) value;
                        continue;
                    case b.TAB_HIDDEN /* 6 */:
                        this.mediaSize = MediaSizeExt.fromMap((Map) value);
                        continue;
                }
            }
        }
        return this;
    }
}
