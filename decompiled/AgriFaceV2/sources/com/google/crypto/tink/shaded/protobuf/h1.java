package com.google.crypto.tink.shaded.protobuf;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class h1 {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements b {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ h f6088a;

        a(h hVar) {
            this.f6088a = hVar;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.h1.b
        public byte a(int i8) {
            return this.f6088a.f(i8);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.h1.b
        public int size() {
            return this.f6088a.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface b {
        byte a(int i8);

        int size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(h hVar) {
        return b(new a(hVar));
    }

    static String b(b bVar) {
        String str;
        StringBuilder sb = new StringBuilder(bVar.size());
        for (int i8 = 0; i8 < bVar.size(); i8++) {
            int a8 = bVar.a(i8);
            if (a8 == 34) {
                str = "\\\"";
            } else if (a8 == 39) {
                str = "\\'";
            } else if (a8 != 92) {
                switch (a8) {
                    case 7:
                        str = "\\a";
                        break;
                    case 8:
                        str = "\\b";
                        break;
                    case 9:
                        str = "\\t";
                        break;
                    case 10:
                        str = "\\n";
                        break;
                    case 11:
                        str = "\\v";
                        break;
                    case 12:
                        str = "\\f";
                        break;
                    case 13:
                        str = "\\r";
                        break;
                    default:
                        if (a8 < 32 || a8 > 126) {
                            sb.append('\\');
                            sb.append((char) (((a8 >>> 6) & 3) + 48));
                            sb.append((char) (((a8 >>> 3) & 7) + 48));
                            a8 = (a8 & 7) + 48;
                        }
                        sb.append((char) a8);
                        continue;
                        break;
                }
            } else {
                str = "\\\\";
            }
            sb.append(str);
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String c(String str) {
        return a(h.n(str));
    }
}
