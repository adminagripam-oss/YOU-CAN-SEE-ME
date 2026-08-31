package com.pichillilorenzo.flutter_inappwebview_android.content_blocker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
/* loaded from: classes.dex */
public class ContentBlockerTrigger {
    private List<String> ifDomain;
    private List<String> ifTopUrl;
    private List<String> loadType;
    private List<ContentBlockerTriggerResourceType> resourceType;
    private List<String> unlessDomain;
    private List<String> unlessTopUrl;
    private String urlFilter;
    private Boolean urlFilterIsCaseSensitive;
    private Pattern urlFilterPatternCompiled;

    public ContentBlockerTrigger(String str, Boolean bool, List<ContentBlockerTriggerResourceType> list, List<String> list2, List<String> list3, List<String> list4, List<String> list5, List<String> list6) {
        this.resourceType = new ArrayList();
        this.ifDomain = new ArrayList();
        this.unlessDomain = new ArrayList();
        this.loadType = new ArrayList();
        this.ifTopUrl = new ArrayList();
        this.unlessTopUrl = new ArrayList();
        boolean z7 = false;
        Boolean valueOf = Boolean.valueOf(bool != null ? bool.booleanValue() : false);
        this.urlFilterIsCaseSensitive = valueOf;
        this.urlFilter = str;
        this.urlFilterPatternCompiled = Pattern.compile(str, valueOf.booleanValue() ? 0 : 2);
        this.resourceType = list == null ? this.resourceType : list;
        list2 = list2 == null ? this.ifDomain : list2;
        this.ifDomain = list2;
        this.unlessDomain = list3 == null ? this.unlessDomain : list3;
        if ((list2.isEmpty() || this.unlessDomain.isEmpty()) ? false : true) {
            throw new AssertionError();
        }
        list4 = list4 == null ? this.loadType : list4;
        this.loadType = list4;
        if (list4.size() > 2) {
            throw new AssertionError();
        }
        list5 = list5 == null ? this.ifTopUrl : list5;
        this.ifTopUrl = list5;
        this.unlessTopUrl = list6 == null ? this.unlessTopUrl : list6;
        if (!list5.isEmpty() && !this.unlessTopUrl.isEmpty()) {
            z7 = true;
        }
        if (z7) {
            throw new AssertionError();
        }
    }

    public static ContentBlockerTrigger fromMap(Map<String, Object> map) {
        String str = (String) map.get("url-filter");
        Boolean bool = (Boolean) map.get("url-filter-is-case-sensitive");
        List<String> list = (List) map.get("resource-type");
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (String str2 : list) {
                arrayList.add(ContentBlockerTriggerResourceType.fromValue(str2));
            }
        } else {
            arrayList.addAll(Arrays.asList(ContentBlockerTriggerResourceType.values()));
        }
        return new ContentBlockerTrigger(str, bool, arrayList, (List) map.get("if-domain"), (List) map.get("unless-domain"), (List) map.get("load-type"), (List) map.get("if-top-url"), (List) map.get("unless-top-url"));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ContentBlockerTrigger contentBlockerTrigger = (ContentBlockerTrigger) obj;
        if (this.urlFilter.equals(contentBlockerTrigger.urlFilter) && this.urlFilterPatternCompiled.equals(contentBlockerTrigger.urlFilterPatternCompiled) && this.urlFilterIsCaseSensitive.equals(contentBlockerTrigger.urlFilterIsCaseSensitive) && this.resourceType.equals(contentBlockerTrigger.resourceType) && this.ifDomain.equals(contentBlockerTrigger.ifDomain) && this.unlessDomain.equals(contentBlockerTrigger.unlessDomain) && this.loadType.equals(contentBlockerTrigger.loadType) && this.ifTopUrl.equals(contentBlockerTrigger.ifTopUrl)) {
            return this.unlessTopUrl.equals(contentBlockerTrigger.unlessTopUrl);
        }
        return false;
    }

    public List<String> getIfDomain() {
        return this.ifDomain;
    }

    public List<String> getIfTopUrl() {
        return this.ifTopUrl;
    }

    public List<String> getLoadType() {
        return this.loadType;
    }

    public List<ContentBlockerTriggerResourceType> getResourceType() {
        return this.resourceType;
    }

    public List<String> getUnlessDomain() {
        return this.unlessDomain;
    }

    public List<String> getUnlessTopUrl() {
        return this.unlessTopUrl;
    }

    public String getUrlFilter() {
        return this.urlFilter;
    }

    public Boolean getUrlFilterIsCaseSensitive() {
        return this.urlFilterIsCaseSensitive;
    }

    public Pattern getUrlFilterPatternCompiled() {
        return this.urlFilterPatternCompiled;
    }

    public int hashCode() {
        return (((((((((((((((this.urlFilter.hashCode() * 31) + this.urlFilterPatternCompiled.hashCode()) * 31) + this.urlFilterIsCaseSensitive.hashCode()) * 31) + this.resourceType.hashCode()) * 31) + this.ifDomain.hashCode()) * 31) + this.unlessDomain.hashCode()) * 31) + this.loadType.hashCode()) * 31) + this.ifTopUrl.hashCode()) * 31) + this.unlessTopUrl.hashCode();
    }

    public void setIfDomain(List<String> list) {
        this.ifDomain = list;
    }

    public void setIfTopUrl(List<String> list) {
        this.ifTopUrl = list;
    }

    public void setLoadType(List<String> list) {
        this.loadType = list;
    }

    public void setResourceType(List<ContentBlockerTriggerResourceType> list) {
        this.resourceType = list;
    }

    public void setUnlessDomain(List<String> list) {
        this.unlessDomain = list;
    }

    public void setUnlessTopUrl(List<String> list) {
        this.unlessTopUrl = list;
    }

    public void setUrlFilter(String str) {
        this.urlFilter = str;
    }

    public void setUrlFilterIsCaseSensitive(Boolean bool) {
        this.urlFilterIsCaseSensitive = bool;
    }

    public void setUrlFilterPatternCompiled(Pattern pattern) {
        this.urlFilterPatternCompiled = pattern;
    }

    public String toString() {
        return "ContentBlockerTrigger{urlFilter='" + this.urlFilter + "', urlFilterPatternCompiled=" + this.urlFilterPatternCompiled + ", urlFilterIsCaseSensitive=" + this.urlFilterIsCaseSensitive + ", resourceType=" + this.resourceType + ", ifDomain=" + this.ifDomain + ", unlessDomain=" + this.unlessDomain + ", loadType=" + this.loadType + ", ifTopUrl=" + this.ifTopUrl + ", unlessTopUrl=" + this.unlessTopUrl + '}';
    }
}
