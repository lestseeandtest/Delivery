package p195e.p196a.p199x0.p454e.p455a;

import p195e.p196a.C12268f;
import p195e.p196a.C5925c;
import p195e.p196a.p447u0.C12314c;
import p195e.p196a.p447u0.C12315d;
import p195e.p196a.p449w0.C12325a;
import p212io.reactivex.exceptions.C14398a;

/* renamed from: e.a.x0.e.a.p */
/* compiled from: CompletableFromAction */
public final class C12493p extends C5925c {

    /* renamed from: a */
    final C12325a f35784a;

    public C12493p(C12325a aVar) {
        this.f35784a = aVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo24134b(C12268f fVar) {
        C12314c b = C12315d.m55419b();
        fVar.mo41921a(b);
        try {
            this.f35784a.run();
            if (!b.mo41878d()) {
                fVar.onComplete();
            }
        } catch (Throwable th) {
            C14398a.m62357b(th);
            if (!b.mo41878d()) {
                fVar.onError(th);
            }
        }
    }
}
