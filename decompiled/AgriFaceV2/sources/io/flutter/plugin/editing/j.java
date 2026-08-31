package io.flutter.plugin.editing;

import android.view.textservice.SentenceSuggestionsInfo;
import android.view.textservice.SpellCheckerSession;
import android.view.textservice.SuggestionsInfo;
import android.view.textservice.TextInfo;
import android.view.textservice.TextServicesManager;
import h6.u;
import i6.j;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
/* loaded from: classes.dex */
public class j implements u.b, SpellCheckerSession.SpellCheckerSessionListener {

    /* renamed from: a  reason: collision with root package name */
    private final u f7884a;

    /* renamed from: b  reason: collision with root package name */
    private final TextServicesManager f7885b;

    /* renamed from: c  reason: collision with root package name */
    private SpellCheckerSession f7886c;

    /* renamed from: d  reason: collision with root package name */
    j.d f7887d;

    public j(TextServicesManager textServicesManager, u uVar) {
        this.f7885b = textServicesManager;
        this.f7884a = uVar;
        uVar.b(this);
    }

    @Override // h6.u.b
    public void a(String str, String str2, j.d dVar) {
        if (this.f7887d != null) {
            dVar.error("error", "Previous spell check request still pending.", null);
            return;
        }
        this.f7887d = dVar;
        c(str, str2);
    }

    public void b() {
        this.f7884a.b(null);
        SpellCheckerSession spellCheckerSession = this.f7886c;
        if (spellCheckerSession != null) {
            spellCheckerSession.close();
        }
    }

    public void c(String str, String str2) {
        Locale b8 = j6.a.b(str);
        if (this.f7886c == null) {
            this.f7886c = this.f7885b.newSpellCheckerSession(null, b8, this, true);
        }
        this.f7886c.getSentenceSuggestions(new TextInfo[]{new TextInfo(str2)}, 5);
    }

    @Override // android.view.textservice.SpellCheckerSession.SpellCheckerSessionListener
    public void onGetSentenceSuggestions(SentenceSuggestionsInfo[] sentenceSuggestionsInfoArr) {
        ArrayList arrayList;
        j.d dVar;
        if (sentenceSuggestionsInfoArr.length == 0) {
            dVar = this.f7887d;
            arrayList = new ArrayList();
        } else {
            arrayList = new ArrayList();
            SentenceSuggestionsInfo sentenceSuggestionsInfo = sentenceSuggestionsInfoArr[0];
            if (sentenceSuggestionsInfo == null) {
                dVar = this.f7887d;
                arrayList = new ArrayList();
            } else {
                for (int i8 = 0; i8 < sentenceSuggestionsInfo.getSuggestionsCount(); i8++) {
                    SuggestionsInfo suggestionsInfoAt = sentenceSuggestionsInfo.getSuggestionsInfoAt(i8);
                    int suggestionsCount = suggestionsInfoAt.getSuggestionsCount();
                    if (suggestionsCount > 0) {
                        HashMap hashMap = new HashMap();
                        int offsetAt = sentenceSuggestionsInfo.getOffsetAt(i8);
                        int lengthAt = sentenceSuggestionsInfo.getLengthAt(i8) + offsetAt;
                        hashMap.put("startIndex", Integer.valueOf(offsetAt));
                        hashMap.put("endIndex", Integer.valueOf(lengthAt));
                        ArrayList arrayList2 = new ArrayList();
                        boolean z7 = false;
                        for (int i9 = 0; i9 < suggestionsCount; i9++) {
                            String suggestionAt = suggestionsInfoAt.getSuggestionAt(i9);
                            if (!suggestionAt.equals("")) {
                                arrayList2.add(suggestionAt);
                                z7 = true;
                            }
                        }
                        if (z7) {
                            hashMap.put("suggestions", arrayList2);
                            arrayList.add(hashMap);
                        }
                    }
                }
                dVar = this.f7887d;
            }
        }
        dVar.success(arrayList);
        this.f7887d = null;
    }

    @Override // android.view.textservice.SpellCheckerSession.SpellCheckerSessionListener
    public void onGetSuggestions(SuggestionsInfo[] suggestionsInfoArr) {
    }
}
