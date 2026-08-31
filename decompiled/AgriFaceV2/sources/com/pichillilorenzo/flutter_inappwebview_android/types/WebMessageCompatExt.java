package com.pichillilorenzo.flutter_inappwebview_android.types;

import c2.m;
import c2.t;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes.dex */
public class WebMessageCompatExt {
    private Object data;
    private List<WebMessagePortCompatExt> ports;
    private int type;

    public WebMessageCompatExt(Object obj, int i8, List<WebMessagePortCompatExt> list) {
        this.data = obj;
        this.type = i8;
        this.ports = list;
    }

    public static WebMessageCompatExt fromMap(Map<String, Object> map) {
        ArrayList arrayList = null;
        if (map == null) {
            return null;
        }
        Object obj = map.get("data");
        Integer num = (Integer) map.get("type");
        List<Map> list = (List) map.get("ports");
        if (list != null && !list.isEmpty()) {
            arrayList = new ArrayList();
            for (Map map2 : list) {
                arrayList.add(WebMessagePortCompatExt.fromMap(map2));
            }
        }
        return new WebMessageCompatExt(obj, num.intValue(), arrayList);
    }

    public static WebMessageCompatExt fromMapWebMessageCompat(m mVar) {
        return new WebMessageCompatExt((t.a("WEB_MESSAGE_ARRAY_BUFFER") && mVar.e() == 1) ? mVar.b() : mVar.c(), mVar.e(), null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WebMessageCompatExt webMessageCompatExt = (WebMessageCompatExt) obj;
        if (this.type == webMessageCompatExt.type && Objects.equals(this.data, webMessageCompatExt.data)) {
            return Objects.equals(this.ports, webMessageCompatExt.ports);
        }
        return false;
    }

    public Object getData() {
        return this.data;
    }

    public List<WebMessagePortCompatExt> getPorts() {
        return this.ports;
    }

    public int getType() {
        return this.type;
    }

    public int hashCode() {
        Object obj = this.data;
        int hashCode = (((obj != null ? obj.hashCode() : 0) * 31) + this.type) * 31;
        List<WebMessagePortCompatExt> list = this.ports;
        return hashCode + (list != null ? list.hashCode() : 0);
    }

    public void setData(Object obj) {
        this.data = obj;
    }

    public void setPorts(List<WebMessagePortCompatExt> list) {
        this.ports = list;
    }

    public void setType(int i8) {
        this.type = i8;
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("data", this.data);
        hashMap.put("type", Integer.valueOf(this.type));
        return hashMap;
    }

    public String toString() {
        return "WebMessageCompatExt{data=" + this.data + ", type=" + this.type + ", ports=" + this.ports + '}';
    }
}
