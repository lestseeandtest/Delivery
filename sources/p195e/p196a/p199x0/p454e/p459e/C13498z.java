package p195e.p196a.p199x0.p454e.p459e;

import java.util.concurrent.atomic.AtomicReference;
import p195e.p196a.C12280i0;
import p195e.p196a.C12292n0;
import p195e.p196a.C5923b0;
import p195e.p196a.C5930q0;
import p195e.p196a.p199x0.p450a.C12347d;
import p195e.p196a.p447u0.C12314c;

/* renamed from: e.a.x0.e.e.z */
/* compiled from: ObservableConcatWithSingle */
public final class C13498z<T> extends C13131a<T, T> {

    /* renamed from: b */
    final C5930q0<? extends T> f39092b;

    /* renamed from: e.a.x0.e.e.z$a */
    /* compiled from: ObservableConcatWithSingle */
    static final class C13499a<T> extends AtomicReference<C12314c> implements C12280i0<T>, C12292n0<T>, C12314c {
        private static final long serialVersionUID = -1953724749712440952L;

        /* renamed from: N */
        boolean f39093N;

        /* renamed from: a */
        final C12280i0<? super T> f39094a;

        /* renamed from: b */
        C5930q0<? extends T> f39095b;

        C13499a(C12280i0<? super T> i0Var, C5930q0<? extends T> q0Var) {
            this.f39094a = i0Var;
            this.f39095b = q0Var;
        }

        /* renamed from: a */
        public void mo34123a(C12314c cVar) {
            if (C12347d.m55468c(this, cVar) && !this.f39093N) {
                this.f39094a.mo34123a((C12314c) this);
            }
        }

        /* renamed from: d */
        public boolean mo41878d() {
            return C12347d.m55463a((C12314c) get());
        }

        public void dispose() {
            C12347d.m55465a((AtomicReference<C12314c>) this);
        }

        public void onComplete() {
            this.f39093N = true;
            C12347d.m55466a((AtomicReference<C12314c>) this, (C12314c) null);
            C5930q0<? extends T> q0Var = this.f39095b;
            this.f39095b = null;
            q0Var.mo24192a(this);
        }

        public void onError(Throwable th) {
            this.f39094a.onError(th);
        }

        public void onSuccess(T t) {
            this.f39094a.mo33453a(t);
            this.f39094a.onComplete();
        }

        /* renamed from: a */
        public void mo33453a(T t) {
            this.f39094a.mo33453a(t);
        }
    }

    public C13498z(C5923b0<T> b0Var, C5930q0<? extends T> q0Var) {
        super(b0Var);
        this.f39092b = q0Var;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public void mo23995e(C12280i0<? super T> i0Var) {
        this.f37851a.mo23893a(new C13499a(i0Var, this.f39092b));
    }
}
