package com.pichillilorenzo.flutter_inappwebview_android.proxy;

import c2.d;
import com.pichillilorenzo.flutter_inappwebview_android.ISettings;
import com.pichillilorenzo.flutter_inappwebview_android.types.ProxyRuleExt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class ProxySettings implements ISettings<d> {
    List<String> bypassRules = new ArrayList();
    List<String> directs = new ArrayList();
    List<ProxyRuleExt> proxyRules = new ArrayList();
    Boolean bypassSimpleHostnames = null;
    Boolean removeImplicitRules = null;
    Boolean reverseBypassEnabled = Boolean.FALSE;

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public Map<String, Object> getRealSettings(d dVar) {
        Map<String, Object> map = toMap();
        ArrayList arrayList = new ArrayList();
        for (d.b bVar : dVar.b()) {
            HashMap hashMap = new HashMap();
            hashMap.put("url", bVar.b());
            hashMap.put("schemeFilter", bVar.a());
            arrayList.add(hashMap);
        }
        map.put("bypassRules", dVar.a());
        map.put("proxyRules", arrayList);
        map.put("reverseBypassEnabled", Boolean.valueOf(dVar.c()));
        return map;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public /* bridge */ /* synthetic */ ISettings<d> parse(Map map) {
        return parse2((Map<String, Object>) map);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public Map<String, Object> toMap() {
        ArrayList arrayList = new ArrayList();
        for (ProxyRuleExt proxyRuleExt : this.proxyRules) {
            arrayList.add(proxyRuleExt.toMap());
        }
        HashMap hashMap = new HashMap();
        hashMap.put("bypassRules", this.bypassRules);
        hashMap.put("directs", this.directs);
        hashMap.put("proxyRules", arrayList);
        hashMap.put("bypassSimpleHostnames", this.bypassSimpleHostnames);
        hashMap.put("removeImplicitRules", this.removeImplicitRules);
        hashMap.put("reverseBypassEnabled", this.reverseBypassEnabled);
        return hashMap;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    /* renamed from: parse  reason: avoid collision after fix types in other method */
    public ISettings<d> parse2(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                key.hashCode();
                char c8 = 65535;
                switch (key.hashCode()) {
                    case -2059288826:
                        if (key.equals("bypassSimpleHostnames")) {
                            c8 = 0;
                            break;
                        }
                        break;
                    case -1872895191:
                        if (key.equals("proxyRules")) {
                            c8 = 1;
                            break;
                        }
                        break;
                    case -1033831049:
                        if (key.equals("reverseBypassEnabled")) {
                            c8 = 2;
                            break;
                        }
                        break;
                    case -940646418:
                        if (key.equals("removeImplicitRules")) {
                            c8 = 3;
                            break;
                        }
                        break;
                    case 1021951215:
                        if (key.equals("bypassRules")) {
                            c8 = 4;
                            break;
                        }
                        break;
                    case 1670504874:
                        if (key.equals("directs")) {
                            c8 = 5;
                            break;
                        }
                        break;
                }
                switch (c8) {
                    case 0:
                        this.bypassSimpleHostnames = (Boolean) value;
                        continue;
                    case 1:
                        this.proxyRules = new ArrayList();
                        for (Map map2 : (List) value) {
                            ProxyRuleExt fromMap = ProxyRuleExt.fromMap(map2);
                            if (fromMap != null) {
                                this.proxyRules.add(fromMap);
                            }
                        }
                        continue;
                    case 2:
                        this.reverseBypassEnabled = (Boolean) value;
                        continue;
                    case 3:
                        this.removeImplicitRules = (Boolean) value;
                        continue;
                    case 4:
                        this.bypassRules = (List) value;
                        continue;
                    case 5:
                        this.directs = (List) value;
                        continue;
                }
            }
        }
        return this;
    }
}
