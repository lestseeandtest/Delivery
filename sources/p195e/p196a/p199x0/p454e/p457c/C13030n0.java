package p195e.p196a.p199x0.p454e.p457c;

import p195e.p196a.C12321v;
import p195e.p196a.C5941y;
import p195e.p196a.p199x0.p450a.C12347d;
import p195e.p196a.p447u0.C12314c;

/* renamed from: e.a.x0.e.c.n0 */
/* compiled from: MaybeHide */
public final class C13030n0<T> extends C12943a<T, T> {

    /* renamed from: e.a.x0.e.c.n0$a */
    /* compiled from: MaybeHide */
    static final class C13031a<T> implements C12321v<T>, C12314c {

        /* renamed from: a */
        final C12321v<? super T> f37524a;

        /* renamed from: b */
        C12314c f37525b;

        C13031a(C12321v<? super T> vVar) {
            this.f37524a = vVar;
        }

        /* renamed from: a */
        public void mo41931a(C12314c cVar) {
            if (C12347d.m55464a(this.f37525b, cVar)) {
                this.f37525b = cVar;
                this.f37524a.mo41931a(this);
            }
        }

        /* renamed from: d */
        public boolean mo41878d() {
            return this.f37525b.mo41878d();
        }

        public void dispose() {
            this.f37525b.dispose();
            this.f37525b = C12347d.DISPOSED;
        }

        public void onComplete() {
            this.f37524a.onComplete();
        }

        public void onError(Throwable th) {
            this.f37524a.onError(th);
        }

        public void onSuccess(T t) {
            this.f37524a.onSuccess(t);
        }
    }

    public C13030n0(C5941y<T> yVar) {
        super(yVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo24653b(C12321v<? super T> vVar) {
        this.f37333a.mo24639a(new C13031a(vVar));
    }
}
