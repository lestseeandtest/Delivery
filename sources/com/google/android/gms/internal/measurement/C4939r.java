package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

/* renamed from: com.google.android.gms.internal.measurement.r */
/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.1.0 */
final class C4939r extends C4747a {

    /* renamed from: P */
    private final /* synthetic */ C4779g9 f14380P;

    /* renamed from: Q */
    private final /* synthetic */ C4746ec f14381Q;

    C4939r(C4746ec ecVar, C4779g9 g9Var) {
        this.f14381Q = ecVar;
        this.f14380P = g9Var;
        super(ecVar);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo18823a() throws RemoteException {
        this.f14381Q.f14080i.getCurrentScreenClass(this.f14380P);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo18903b() {
        this.f14380P.mo19016c(null);
    }
}