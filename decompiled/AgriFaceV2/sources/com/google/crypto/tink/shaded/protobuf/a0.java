package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
/* loaded from: classes.dex */
public class a0 extends IOException {

    /* renamed from: e  reason: collision with root package name */
    private p0 f6032e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f6033f;

    /* loaded from: classes.dex */
    public static class a extends a0 {
        public a(String str) {
            super(str);
        }
    }

    public a0(IOException iOException) {
        super(iOException.getMessage(), iOException);
        this.f6032e = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a0 b() {
        return new a0("Protocol message end-group tag did not match expected tag.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a0 c() {
        return new a0("Protocol message contained an invalid tag (zero).");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a0 d() {
        return new a0("Protocol message had invalid UTF-8.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a e() {
        return new a("Protocol message tag had invalid wire type.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a0 f() {
        return new a0("CodedInputStream encountered a malformed varint.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a0 g() {
        return new a0("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a0 h() {
        return new a0("Failed to parse the message.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a0 i() {
        return new a0("Protocol message had too many levels of nesting.  May be malicious.  Use setRecursionLimit() to increase the recursion depth limit.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a0 l() {
        return new a0("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit. If reading multiple messages, consider resetting the counter between each message using CodedInputStream.resetSizeCounter().");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a0 m() {
        return new a0("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a() {
        return this.f6033f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j() {
        this.f6033f = true;
    }

    public a0 k(p0 p0Var) {
        this.f6032e = p0Var;
        return this;
    }

    public a0(String str) {
        super(str);
        this.f6032e = null;
    }
}
