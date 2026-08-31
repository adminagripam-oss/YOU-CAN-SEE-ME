package com.pichillilorenzo.flutter_inappwebview_android.types;

import com.pichillilorenzo.flutter_inappwebview_android.credential_database.URLCredentialContract;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class URLCredential {
    private Long id;
    private String password;
    private Long protectionSpaceId;
    private String username;

    public URLCredential(Long l8, String str, String str2, Long l9) {
        this.id = l8;
        this.username = str;
        this.password = str2;
        this.protectionSpaceId = l9;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        URLCredential uRLCredential = (URLCredential) obj;
        String str = this.username;
        if (str == null ? uRLCredential.username == null : str.equals(uRLCredential.username)) {
            String str2 = this.password;
            String str3 = uRLCredential.password;
            return str2 != null ? str2.equals(str3) : str3 == null;
        }
        return false;
    }

    public Long getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public Long getProtectionSpaceId() {
        return this.protectionSpaceId;
    }

    public String getUsername() {
        return this.username;
    }

    public int hashCode() {
        String str = this.username;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.password;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public void setId(Long l8) {
        this.id = l8;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void setProtectionSpaceId(Long l8) {
        this.protectionSpaceId = l8;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put(URLCredentialContract.FeedEntry.COLUMN_NAME_USERNAME, this.username);
        hashMap.put(URLCredentialContract.FeedEntry.COLUMN_NAME_PASSWORD, this.password);
        hashMap.put("certificates", null);
        hashMap.put("persistence", null);
        return hashMap;
    }

    public String toString() {
        return "URLCredential{username='" + this.username + "', password='" + this.password + "'}";
    }

    public URLCredential(String str, String str2) {
        this.username = str;
        this.password = str2;
    }
}
