package com.pichillilorenzo.flutter_inappwebview_android.types;

import java.util.Map;
/* loaded from: classes.dex */
public class JsConfirmResponse {
    private Integer action;
    private String cancelButtonTitle;
    private String confirmButtonTitle;
    private boolean handledByClient;
    private String message;

    public JsConfirmResponse(String str, String str2, String str3, boolean z7, Integer num) {
        this.message = str;
        this.confirmButtonTitle = str2;
        this.cancelButtonTitle = str3;
        this.handledByClient = z7;
        this.action = num;
    }

    public static JsConfirmResponse fromMap(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        return new JsConfirmResponse((String) map.get("message"), (String) map.get("confirmButtonTitle"), (String) map.get("cancelButtonTitle"), ((Boolean) map.get("handledByClient")).booleanValue(), (Integer) map.get("action"));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        JsConfirmResponse jsConfirmResponse = (JsConfirmResponse) obj;
        if (this.handledByClient != jsConfirmResponse.handledByClient) {
            return false;
        }
        String str = this.message;
        if (str == null ? jsConfirmResponse.message == null : str.equals(jsConfirmResponse.message)) {
            String str2 = this.confirmButtonTitle;
            if (str2 == null ? jsConfirmResponse.confirmButtonTitle == null : str2.equals(jsConfirmResponse.confirmButtonTitle)) {
                String str3 = this.cancelButtonTitle;
                if (str3 == null ? jsConfirmResponse.cancelButtonTitle == null : str3.equals(jsConfirmResponse.cancelButtonTitle)) {
                    Integer num = this.action;
                    Integer num2 = jsConfirmResponse.action;
                    return num != null ? num.equals(num2) : num2 == null;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public Integer getAction() {
        return this.action;
    }

    public String getCancelButtonTitle() {
        return this.cancelButtonTitle;
    }

    public String getConfirmButtonTitle() {
        return this.confirmButtonTitle;
    }

    public String getMessage() {
        return this.message;
    }

    public int hashCode() {
        String str = this.message;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.confirmButtonTitle;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.cancelButtonTitle;
        int hashCode3 = (((hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + (this.handledByClient ? 1 : 0)) * 31;
        Integer num = this.action;
        return hashCode3 + (num != null ? num.hashCode() : 0);
    }

    public boolean isHandledByClient() {
        return this.handledByClient;
    }

    public void setAction(Integer num) {
        this.action = num;
    }

    public void setCancelButtonTitle(String str) {
        this.cancelButtonTitle = str;
    }

    public void setConfirmButtonTitle(String str) {
        this.confirmButtonTitle = str;
    }

    public void setHandledByClient(boolean z7) {
        this.handledByClient = z7;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String toString() {
        return "JsConfirmResponse{message='" + this.message + "', confirmButtonTitle='" + this.confirmButtonTitle + "', cancelButtonTitle='" + this.cancelButtonTitle + "', handledByClient=" + this.handledByClient + ", action=" + this.action + '}';
    }
}
