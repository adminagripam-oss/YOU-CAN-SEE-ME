package b3;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.SystemClock;
import android.util.Base64;
import c3.b;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import u2.h;
/* loaded from: classes.dex */
public class b0 implements b3.c, c3.b {

    /* renamed from: i  reason: collision with root package name */
    private static final s2.b f4569i = s2.b.b("proto");

    /* renamed from: e  reason: collision with root package name */
    private final h0 f4570e;

    /* renamed from: f  reason: collision with root package name */
    private final d3.a f4571f;

    /* renamed from: g  reason: collision with root package name */
    private final d3.a f4572g;

    /* renamed from: h  reason: collision with root package name */
    private final b3.d f4573h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface b {
        Object apply(Object obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        final String f4574a;

        /* renamed from: b  reason: collision with root package name */
        final String f4575b;

        private c(String str, String str2) {
            this.f4574a = str;
            this.f4575b = str2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface d {
        Object a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b0(d3.a aVar, d3.a aVar2, b3.d dVar, h0 h0Var) {
        this.f4570e = h0Var;
        this.f4571f = aVar;
        this.f4572g = aVar2;
        this.f4573h = dVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object A(Throwable th) {
        throw new c3.a("Timed out while trying to acquire the lock.", th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SQLiteDatabase C(Throwable th) {
        throw new c3.a("Timed out while trying to open db.", th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Long E(Cursor cursor) {
        if (cursor.moveToNext()) {
            return Long.valueOf(cursor.getLong(0));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Boolean G(b0 b0Var, u2.m mVar, SQLiteDatabase sQLiteDatabase) {
        Long n8 = b0Var.n(sQLiteDatabase, mVar);
        return n8 == null ? Boolean.FALSE : (Boolean) e0(b0Var.f().rawQuery("SELECT 1 FROM events WHERE context_id = ? LIMIT 1", new String[]{n8.toString()}), u.a());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List M(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            arrayList.add(u2.m.a().b(cursor.getString(1)).d(e3.a.b(cursor.getInt(2))).c(Z(cursor.getString(3))).a());
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List P(SQLiteDatabase sQLiteDatabase) {
        return (List) e0(sQLiteDatabase.rawQuery("SELECT distinct t._id, t.backend_name, t.priority, t.extras FROM transport_contexts AS t, events AS e WHERE e.context_id = t._id", new String[0]), t.a());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List Q(b0 b0Var, u2.m mVar, SQLiteDatabase sQLiteDatabase) {
        List X = b0Var.X(sQLiteDatabase, mVar);
        return b0Var.v(X, b0Var.Y(sQLiteDatabase, X));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object R(b0 b0Var, List list, u2.m mVar, Cursor cursor) {
        while (cursor.moveToNext()) {
            long j8 = cursor.getLong(0);
            boolean z7 = cursor.getInt(7) != 0;
            h.a k8 = u2.h.a().j(cursor.getString(1)).i(cursor.getLong(2)).k(cursor.getLong(3));
            k8.h(z7 ? new u2.g(c0(cursor.getString(4)), cursor.getBlob(5)) : new u2.g(c0(cursor.getString(4)), b0Var.a0(j8)));
            if (!cursor.isNull(6)) {
                k8.g(Integer.valueOf(cursor.getInt(6)));
            }
            list.add(i.a(j8, mVar, k8.d()));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object S(Map map, Cursor cursor) {
        while (cursor.moveToNext()) {
            long j8 = cursor.getLong(0);
            Set set = (Set) map.get(Long.valueOf(j8));
            if (set == null) {
                set = new HashSet();
                map.put(Long.valueOf(j8), set);
            }
            set.add(new c(cursor.getString(1), cursor.getString(2)));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Long T(b0 b0Var, u2.m mVar, u2.h hVar, SQLiteDatabase sQLiteDatabase) {
        if (b0Var.t()) {
            return -1L;
        }
        long d8 = b0Var.d(sQLiteDatabase, mVar);
        int e8 = b0Var.f4573h.e();
        byte[] a8 = hVar.e().a();
        boolean z7 = a8.length <= e8;
        ContentValues contentValues = new ContentValues();
        contentValues.put("context_id", Long.valueOf(d8));
        contentValues.put("transport_name", hVar.j());
        contentValues.put("timestamp_ms", Long.valueOf(hVar.f()));
        contentValues.put("uptime_ms", Long.valueOf(hVar.k()));
        contentValues.put("payload_encoding", hVar.e().b().a());
        contentValues.put("code", hVar.d());
        contentValues.put("num_attempts", (Integer) 0);
        contentValues.put("inline", Boolean.valueOf(z7));
        contentValues.put("payload", z7 ? a8 : new byte[0]);
        long insert = sQLiteDatabase.insert("events", null, contentValues);
        if (!z7) {
            int ceil = (int) Math.ceil(a8.length / e8);
            for (int i8 = 1; i8 <= ceil; i8++) {
                byte[] copyOfRange = Arrays.copyOfRange(a8, (i8 - 1) * e8, Math.min(i8 * e8, a8.length));
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("event_id", Long.valueOf(insert));
                contentValues2.put("sequence_num", Integer.valueOf(i8));
                contentValues2.put("bytes", copyOfRange);
                sQLiteDatabase.insert("event_payloads", null, contentValues2);
            }
        }
        for (Map.Entry entry : hVar.i().entrySet()) {
            ContentValues contentValues3 = new ContentValues();
            contentValues3.put("event_id", Long.valueOf(insert));
            contentValues3.put("name", (String) entry.getKey());
            contentValues3.put("value", (String) entry.getValue());
            sQLiteDatabase.insert("event_metadata", null, contentValues3);
        }
        return Long.valueOf(insert);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ byte[] U(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        int i8 = 0;
        while (cursor.moveToNext()) {
            byte[] blob = cursor.getBlob(0);
            arrayList.add(blob);
            i8 += blob.length;
        }
        byte[] bArr = new byte[i8];
        int i9 = 0;
        for (int i10 = 0; i10 < arrayList.size(); i10++) {
            byte[] bArr2 = (byte[]) arrayList.get(i10);
            System.arraycopy(bArr2, 0, bArr, i9, bArr2.length);
            i9 += bArr2.length;
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object V(String str, SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.compileStatement(str).execute();
        sQLiteDatabase.compileStatement("DELETE FROM events WHERE num_attempts >= 16").execute();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object W(long j8, u2.m mVar, SQLiteDatabase sQLiteDatabase) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("next_request_ms", Long.valueOf(j8));
        if (sQLiteDatabase.update("transport_contexts", contentValues, "backend_name = ? and priority = ?", new String[]{mVar.b(), String.valueOf(e3.a.a(mVar.d()))}) < 1) {
            contentValues.put("backend_name", mVar.b());
            contentValues.put("priority", Integer.valueOf(e3.a.a(mVar.d())));
            sQLiteDatabase.insert("transport_contexts", null, contentValues);
        }
        return null;
    }

    private List X(SQLiteDatabase sQLiteDatabase, u2.m mVar) {
        ArrayList arrayList = new ArrayList();
        Long n8 = n(sQLiteDatabase, mVar);
        if (n8 == null) {
            return arrayList;
        }
        e0(sQLiteDatabase.query("events", new String[]{"_id", "transport_name", "timestamp_ms", "uptime_ms", "payload_encoding", "payload", "code", "inline"}, "context_id = ?", new String[]{n8.toString()}, null, null, null, String.valueOf(this.f4573h.d())), n.a(this, arrayList, mVar));
        return arrayList;
    }

    private Map Y(SQLiteDatabase sQLiteDatabase, List list) {
        HashMap hashMap = new HashMap();
        StringBuilder sb = new StringBuilder("event_id IN (");
        for (int i8 = 0; i8 < list.size(); i8++) {
            sb.append(((i) list.get(i8)).c());
            if (i8 < list.size() - 1) {
                sb.append(',');
            }
        }
        sb.append(')');
        e0(sQLiteDatabase.query("event_metadata", new String[]{"event_id", "name", "value"}, sb.toString(), null, null, null, null), p.a(hashMap));
        return hashMap;
    }

    private static byte[] Z(String str) {
        if (str == null) {
            return null;
        }
        return Base64.decode(str, 0);
    }

    private byte[] a0(long j8) {
        return (byte[]) e0(f().query("event_payloads", new String[]{"bytes"}, "event_id = ?", new String[]{String.valueOf(j8)}, null, null, "sequence_num"), o.a());
    }

    private Object b0(d dVar, b bVar) {
        long a8 = this.f4572g.a();
        while (true) {
            try {
                return dVar.a();
            } catch (SQLiteDatabaseLockedException e8) {
                if (this.f4572g.a() >= this.f4573h.b() + a8) {
                    return bVar.apply(e8);
                }
                SystemClock.sleep(50L);
            }
        }
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        b0(q.b(sQLiteDatabase), r.a());
    }

    private static s2.b c0(String str) {
        return str == null ? f4569i : s2.b.b(str);
    }

    private long d(SQLiteDatabase sQLiteDatabase, u2.m mVar) {
        Long n8 = n(sQLiteDatabase, mVar);
        if (n8 != null) {
            return n8.longValue();
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("backend_name", mVar.b());
        contentValues.put("priority", Integer.valueOf(e3.a.a(mVar.d())));
        contentValues.put("next_request_ms", (Integer) 0);
        if (mVar.c() != null) {
            contentValues.put("extras", Base64.encodeToString(mVar.c(), 0));
        }
        return sQLiteDatabase.insert("transport_contexts", null, contentValues);
    }

    private static String d0(Iterable iterable) {
        StringBuilder sb = new StringBuilder("(");
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            sb.append(((i) it.next()).c());
            if (it.hasNext()) {
                sb.append(',');
            }
        }
        sb.append(')');
        return sb.toString();
    }

    static Object e0(Cursor cursor, b bVar) {
        try {
            return bVar.apply(cursor);
        } finally {
            cursor.close();
        }
    }

    private long g() {
        return f().compileStatement("PRAGMA page_count").simpleQueryForLong();
    }

    private long j() {
        return f().compileStatement("PRAGMA page_size").simpleQueryForLong();
    }

    private Long n(SQLiteDatabase sQLiteDatabase, u2.m mVar) {
        StringBuilder sb = new StringBuilder("backend_name = ? and priority = ?");
        ArrayList arrayList = new ArrayList(Arrays.asList(mVar.b(), String.valueOf(e3.a.a(mVar.d()))));
        if (mVar.c() != null) {
            sb.append(" and extras = ?");
            arrayList.add(Base64.encodeToString(mVar.c(), 0));
        } else {
            sb.append(" and extras is null");
        }
        return (Long) e0(sQLiteDatabase.query("transport_contexts", new String[]{"_id"}, sb.toString(), (String[]) arrayList.toArray(new String[0]), null, null, null), x.a());
    }

    private boolean t() {
        return g() * j() >= this.f4573h.f();
    }

    private List v(List list, Map map) {
        ListIterator listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            i iVar = (i) listIterator.next();
            if (map.containsKey(Long.valueOf(iVar.c()))) {
                h.a l8 = iVar.b().l();
                for (c cVar : (Set) map.get(Long.valueOf(iVar.c()))) {
                    l8.c(cVar.f4574a, cVar.f4575b);
                }
                listIterator.set(i.a(iVar.c(), iVar.d(), l8.d()));
            }
        }
        return list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object x(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        return null;
    }

    @Override // b3.c
    public Iterable B() {
        return (Iterable) q(l.a());
    }

    @Override // b3.c
    public i H(u2.m mVar, u2.h hVar) {
        x2.a.b("SQLiteEventStore", "Storing event with priority=%s, name=%s for destination %s", mVar.d(), hVar.j(), mVar.b());
        long longValue = ((Long) q(w.a(this, mVar, hVar))).longValue();
        if (longValue < 1) {
            return null;
        }
        return i.a(longValue, mVar, hVar);
    }

    @Override // b3.c
    public void J(Iterable iterable) {
        if (iterable.iterator().hasNext()) {
            q(y.a("UPDATE events SET num_attempts = num_attempts + 1 WHERE _id in " + d0(iterable)));
        }
    }

    @Override // b3.c
    public void K(u2.m mVar, long j8) {
        q(j.a(j8, mVar));
    }

    @Override // b3.c
    public Iterable L(u2.m mVar) {
        return (Iterable) q(k.a(this, mVar));
    }

    @Override // c3.b
    public Object b(b.a aVar) {
        SQLiteDatabase f8 = f();
        c(f8);
        try {
            Object a8 = aVar.a();
            f8.setTransactionSuccessful();
            return a8;
        } finally {
            f8.endTransaction();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f4570e.close();
    }

    SQLiteDatabase f() {
        h0 h0Var = this.f4570e;
        h0Var.getClass();
        return (SQLiteDatabase) b0(s.b(h0Var), v.a());
    }

    @Override // b3.c
    public int h() {
        return ((Integer) q(m.a(this.f4571f.a() - this.f4573h.c()))).intValue();
    }

    @Override // b3.c
    public void k(Iterable iterable) {
        if (iterable.iterator().hasNext()) {
            f().compileStatement("DELETE FROM events WHERE _id in " + d0(iterable)).execute();
        }
    }

    @Override // b3.c
    public boolean m(u2.m mVar) {
        return ((Boolean) q(a0.a(this, mVar))).booleanValue();
    }

    Object q(b bVar) {
        SQLiteDatabase f8 = f();
        f8.beginTransaction();
        try {
            Object apply = bVar.apply(f8);
            f8.setTransactionSuccessful();
            return apply;
        } finally {
            f8.endTransaction();
        }
    }

    @Override // b3.c
    public long u(u2.m mVar) {
        return ((Long) e0(f().rawQuery("SELECT next_request_ms FROM transport_contexts WHERE backend_name = ? and priority = ?", new String[]{mVar.b(), String.valueOf(e3.a.a(mVar.d()))}), z.a())).longValue();
    }
}
