package androidx.fragment.app;

import android.util.Log;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.conscrypt.ct.CTConstants;

/* renamed from: androidx.fragment.app.a, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0052a {

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f1016a;

    /* renamed from: b, reason: collision with root package name */
    public int f1017b;

    /* renamed from: c, reason: collision with root package name */
    public int f1018c;

    /* renamed from: d, reason: collision with root package name */
    public int f1019d;

    /* renamed from: e, reason: collision with root package name */
    public int f1020e;

    /* renamed from: f, reason: collision with root package name */
    public int f1021f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f1022g;
    public String h;

    /* renamed from: i, reason: collision with root package name */
    public int f1023i;

    /* renamed from: j, reason: collision with root package name */
    public CharSequence f1024j;

    /* renamed from: k, reason: collision with root package name */
    public int f1025k;

    /* renamed from: l, reason: collision with root package name */
    public CharSequence f1026l;

    /* renamed from: m, reason: collision with root package name */
    public ArrayList f1027m;

    /* renamed from: n, reason: collision with root package name */
    public ArrayList f1028n;

    /* renamed from: o, reason: collision with root package name */
    public boolean f1029o;

    /* renamed from: p, reason: collision with root package name */
    public final u f1030p;

    /* renamed from: q, reason: collision with root package name */
    public int f1031q;

    public C0052a(u uVar) {
        q qVar = uVar.f1102u;
        h hVar = uVar.f1100s;
        if (hVar != null) {
            hVar.f1052a.getClassLoader();
        }
        this.f1016a = new ArrayList();
        this.f1029o = false;
        this.f1031q = -1;
        this.f1030p = uVar;
    }

    public final void a(int i2) {
        if (!this.f1022g) {
            return;
        }
        if (Log.isLoggable("FragmentManager", 2)) {
            Log.v("FragmentManager", "Bump nesting in " + this + " by " + i2);
        }
        ArrayList arrayList = this.f1016a;
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            ((z) arrayList.get(i3)).getClass();
        }
    }

    public final void b(String str, PrintWriter printWriter, boolean z2) {
        String str2;
        if (z2) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.h);
            printWriter.print(" mIndex=");
            printWriter.print(this.f1031q);
            printWriter.print(" mCommitted=");
            printWriter.println(false);
            if (this.f1021f != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f1021f));
            }
            if (this.f1017b != 0 || this.f1018c != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f1017b));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f1018c));
            }
            if (this.f1019d != 0 || this.f1020e != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f1019d));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f1020e));
            }
            if (this.f1023i != 0 || this.f1024j != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.f1023i));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.f1024j);
            }
            if (this.f1025k != 0 || this.f1026l != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.f1025k));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.f1026l);
            }
        }
        ArrayList arrayList = this.f1016a;
        if (!arrayList.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Operations:");
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                z zVar = (z) arrayList.get(i2);
                switch (zVar.f1135a) {
                    case 0:
                        str2 = "NULL";
                        break;
                    case 1:
                        str2 = "ADD";
                        break;
                    case 2:
                        str2 = "REPLACE";
                        break;
                    case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                        str2 = "REMOVE";
                        break;
                    case 4:
                        str2 = "HIDE";
                        break;
                    case 5:
                        str2 = "SHOW";
                        break;
                    case 6:
                        str2 = "DETACH";
                        break;
                    case 7:
                        str2 = "ATTACH";
                        break;
                    case CTConstants.TIMESTAMP_LENGTH /* 8 */:
                        str2 = "SET_PRIMARY_NAV";
                        break;
                    case 9:
                        str2 = "UNSET_PRIMARY_NAV";
                        break;
                    case 10:
                        str2 = "OP_SET_MAX_LIFECYCLE";
                        break;
                    default:
                        str2 = "cmd=" + zVar.f1135a;
                        break;
                }
                printWriter.print(str);
                printWriter.print("  Op #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.print(str2);
                printWriter.print(" ");
                printWriter.println((Object) null);
                if (z2) {
                    if (zVar.f1137c != 0 || zVar.f1138d != 0) {
                        printWriter.print(str);
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(zVar.f1137c));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(zVar.f1138d));
                    }
                    if (zVar.f1139e != 0 || zVar.f1140f != 0) {
                        printWriter.print(str);
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(zVar.f1139e));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(zVar.f1140f));
                    }
                }
            }
        }
    }

    public final void c(ArrayList arrayList, ArrayList arrayList2) {
        if (Log.isLoggable("FragmentManager", 2)) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (this.f1022g) {
            u uVar = this.f1030p;
            if (uVar.f1086d == null) {
                uVar.f1086d = new ArrayList();
            }
            uVar.f1086d.add(this);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.f1031q >= 0) {
            sb.append(" #");
            sb.append(this.f1031q);
        }
        if (this.h != null) {
            sb.append(" ");
            sb.append(this.h);
        }
        sb.append("}");
        return sb.toString();
    }
}
