package u0;

import b0.AbstractC0084j;
import j0.AbstractC0150d;
import java.util.ArrayList;
import java.util.Iterator;
import org.conscrypt.BuildConfig;

/* loaded from: classes.dex */
public final class l {

    /* renamed from: a, reason: collision with root package name */
    public String f2475a;

    /* renamed from: d, reason: collision with root package name */
    public String f2478d;

    /* renamed from: f, reason: collision with root package name */
    public final ArrayList f2480f;

    /* renamed from: g, reason: collision with root package name */
    public ArrayList f2481g;
    public String h;

    /* renamed from: b, reason: collision with root package name */
    public String f2476b = BuildConfig.FLAVOR;

    /* renamed from: c, reason: collision with root package name */
    public String f2477c = BuildConfig.FLAVOR;

    /* renamed from: e, reason: collision with root package name */
    public int f2479e = -1;

    public l() {
        ArrayList arrayList = new ArrayList();
        this.f2480f = arrayList;
        arrayList.add(BuildConfig.FLAVOR);
    }

    public final m a() {
        ArrayList arrayList;
        String e2;
        String str = this.f2475a;
        if (str != null) {
            String e3 = b.e(this.f2476b, 0, 0, 7);
            String e4 = b.e(this.f2477c, 0, 0, 7);
            String str2 = this.f2478d;
            if (str2 != null) {
                int b2 = b();
                ArrayList arrayList2 = this.f2480f;
                ArrayList arrayList3 = new ArrayList(AbstractC0084j.I(arrayList2));
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    arrayList3.add(b.e((String) it.next(), 0, 0, 7));
                }
                ArrayList<String> arrayList4 = this.f2481g;
                String str3 = null;
                if (arrayList4 == null) {
                    arrayList = null;
                } else {
                    arrayList = new ArrayList(AbstractC0084j.I(arrayList4));
                    for (String str4 : arrayList4) {
                        if (str4 == null) {
                            e2 = null;
                        } else {
                            e2 = b.e(str4, 0, 0, 3);
                        }
                        arrayList.add(e2);
                    }
                }
                String str5 = this.h;
                if (str5 != null) {
                    str3 = b.e(str5, 0, 0, 7);
                }
                return new m(str, e3, e4, str2, b2, arrayList3, arrayList, str3, toString());
            }
            throw new IllegalStateException("host == null");
        }
        throw new IllegalStateException("scheme == null");
    }

    public final int b() {
        int i2 = this.f2479e;
        if (i2 == -1) {
            String str = this.f2475a;
            AbstractC0150d.b(str);
            if (str.equals("http")) {
                return 80;
            }
            if (!str.equals("https")) {
                return -1;
            }
            return 443;
        }
        return i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:153:0x020c, code lost:
    
        if (r6 < 65536) goto L125;
     */
    /* JADX WARN: Code restructure failed: missing block: B:224:0x007c, code lost:
    
        if (r11 == ':') goto L38;
     */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0358  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x01ea A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0322 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x031e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x013c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(u0.m r18, java.lang.String r19) {
        /*
            Method dump skipped, instructions count: 920
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: u0.l.c(u0.m, java.lang.String):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x0089, code lost:
    
        if (r1 != r3) goto L34;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String toString() {
        /*
            Method dump skipped, instructions count: 284
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: u0.l.toString():java.lang.String");
    }
}
