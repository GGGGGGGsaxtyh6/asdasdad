package androidx.fragment.app;

import a.AbstractActivityC0046m;
import a.InterfaceC0035b;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import androidx.lifecycle.EnumC0066m;
import androidx.lifecycle.EnumC0067n;
import androidx.lifecycle.S;
import b.InterfaceC0074b;
import g.AbstractActivityC0112j;
import j0.AbstractC0150d;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class i extends AbstractActivityC0046m {
    static final String LIFECYCLE_TAG = "android:support:lifecycle";
    boolean mCreated;
    final k mFragments;
    boolean mResumed;
    final androidx.lifecycle.v mFragmentLifecycleRegistry = new androidx.lifecycle.v(this);
    boolean mStopped = true;

    public i() {
        final AbstractActivityC0112j abstractActivityC0112j = (AbstractActivityC0112j) this;
        this.mFragments = new k(new h(abstractActivityC0112j));
        getSavedStateRegistry().c(LIFECYCLE_TAG, new e(0, abstractActivityC0112j));
        final int i2 = 0;
        addOnConfigurationChangedListener(new G.a() { // from class: androidx.fragment.app.f
            @Override // G.a
            public final void a(Object obj) {
                switch (i2) {
                    case 0:
                        abstractActivityC0112j.mFragments.a();
                        return;
                    default:
                        abstractActivityC0112j.mFragments.a();
                        return;
                }
            }
        });
        final int i3 = 1;
        addOnNewIntentListener(new G.a() { // from class: androidx.fragment.app.f
            @Override // G.a
            public final void a(Object obj) {
                switch (i3) {
                    case 0:
                        abstractActivityC0112j.mFragments.a();
                        return;
                    default:
                        abstractActivityC0112j.mFragments.a();
                        return;
                }
            }
        });
        addOnContextAvailableListener(new InterfaceC0074b() { // from class: androidx.fragment.app.g
            /* JADX WARN: Type inference failed for: r11v1, types: [java.lang.Object, androidx.fragment.app.z] */
            @Override // b.InterfaceC0074b
            public final void a(AbstractActivityC0046m abstractActivityC0046m) {
                int i4;
                Bundle bundle;
                Bundle bundle2;
                h hVar = AbstractActivityC0112j.this.mFragments.f1060a;
                u uVar = hVar.f1054c;
                if (uVar.f1100s == null) {
                    uVar.f1100s = hVar;
                    uVar.f1101t = hVar;
                    uVar.f1093l.add(hVar);
                    AbstractActivityC0112j abstractActivityC0112j2 = hVar.f1055d;
                    a.v onBackPressedDispatcher = abstractActivityC0112j2.getOnBackPressedDispatcher();
                    uVar.f1088f = onBackPressedDispatcher;
                    onBackPressedDispatcher.getClass();
                    o oVar = uVar.f1089g;
                    AbstractC0150d.e(oVar, "onBackPressedCallback");
                    androidx.lifecycle.v vVar = abstractActivityC0112j2.mFragmentLifecycleRegistry;
                    if (vVar.f1207c != EnumC0067n.f1196a) {
                        oVar.f1067b.add(new a.s(onBackPressedDispatcher, vVar, oVar));
                        onBackPressedDispatcher.d();
                        oVar.f1068c = new a.u(0, onBackPressedDispatcher);
                    }
                    w wVar = (w) new A.f(abstractActivityC0112j2.getViewModelStore(), w.f1114f).B(w.class);
                    uVar.f1081F = wVar;
                    y yVar = uVar.f1085c;
                    yVar.f1134d = wVar;
                    h hVar2 = uVar.f1100s;
                    if (hVar2 != null) {
                        U.e savedStateRegistry = hVar2.f1055d.getSavedStateRegistry();
                        savedStateRegistry.c("android:support:fragments", new e(1, uVar));
                        Bundle a2 = savedStateRegistry.a("android:support:fragments");
                        if (a2 != null) {
                            for (String str : a2.keySet()) {
                                if (str.startsWith("result_") && (bundle2 = a2.getBundle(str)) != null) {
                                    bundle2.setClassLoader(uVar.f1100s.f1052a.getClassLoader());
                                    uVar.f1091j.put(str.substring(7), bundle2);
                                }
                            }
                            ArrayList arrayList = new ArrayList();
                            for (String str2 : a2.keySet()) {
                                if (str2.startsWith("fragment_") && (bundle = a2.getBundle(str2)) != null) {
                                    bundle.setClassLoader(uVar.f1100s.f1052a.getClassLoader());
                                    arrayList.add((x) bundle.getParcelable("state"));
                                }
                            }
                            HashMap hashMap = yVar.f1133c;
                            hashMap.clear();
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                x xVar = (x) it.next();
                                hashMap.put(xVar.f1120b, xVar);
                            }
                            v vVar2 = (v) a2.getParcelable("state");
                            if (vVar2 != null) {
                                HashMap hashMap2 = yVar.f1132b;
                                hashMap2.clear();
                                Iterator it2 = vVar2.f1107a.iterator();
                                while (it2.hasNext()) {
                                    x xVar2 = (x) hashMap.remove((String) it2.next());
                                    if (xVar2 != null) {
                                        if (uVar.f1081F.f1115b.get(xVar2.f1120b) == null) {
                                            ClassLoader classLoader = uVar.f1100s.f1052a.getClassLoader();
                                            uVar.f1102u.a(xVar2.f1119a);
                                            Bundle bundle3 = xVar2.f1127j;
                                            if (bundle3 != null) {
                                                bundle3.setClassLoader(classLoader);
                                                throw null;
                                            }
                                            throw null;
                                        }
                                        throw new ClassCastException();
                                    }
                                }
                                w wVar2 = uVar.f1081F;
                                wVar2.getClass();
                                Iterator it3 = new ArrayList(wVar2.f1115b.values()).iterator();
                                if (!it3.hasNext()) {
                                    ArrayList arrayList2 = vVar2.f1108b;
                                    yVar.f1131a.clear();
                                    if (arrayList2 != null) {
                                        Iterator it4 = arrayList2.iterator();
                                        if (it4.hasNext()) {
                                            String str3 = (String) it4.next();
                                            A.e.f(hashMap2.get(str3));
                                            throw new IllegalStateException(A.e.d("No instantiated fragment for (", str3, ")"));
                                        }
                                    }
                                    if (vVar2.f1109c != null) {
                                        uVar.f1086d = new ArrayList(vVar2.f1109c.length);
                                        int i5 = 0;
                                        while (true) {
                                            C0053b[] c0053bArr = vVar2.f1109c;
                                            if (i5 >= c0053bArr.length) {
                                                break;
                                            }
                                            C0053b c0053b = c0053bArr[i5];
                                            c0053b.getClass();
                                            C0052a c0052a = new C0052a(uVar);
                                            int i6 = 0;
                                            int i7 = 0;
                                            while (true) {
                                                int[] iArr = c0053b.f1032a;
                                                boolean z2 = true;
                                                if (i6 >= iArr.length) {
                                                    break;
                                                }
                                                ?? obj = new Object();
                                                int i8 = i6 + 1;
                                                obj.f1135a = iArr[i6];
                                                if (Log.isLoggable("FragmentManager", 2)) {
                                                    Log.v("FragmentManager", "Instantiate " + c0052a + " op #" + i7 + " base fragment #" + iArr[i8]);
                                                }
                                                obj.f1141g = EnumC0067n.values()[c0053b.f1034c[i7]];
                                                obj.h = EnumC0067n.values()[c0053b.f1035d[i7]];
                                                int i9 = i6 + 2;
                                                if (iArr[i8] == 0) {
                                                    z2 = false;
                                                }
                                                obj.f1136b = z2;
                                                int i10 = iArr[i9];
                                                obj.f1137c = i10;
                                                int i11 = iArr[i6 + 3];
                                                obj.f1138d = i11;
                                                int i12 = i6 + 5;
                                                int i13 = iArr[i6 + 4];
                                                obj.f1139e = i13;
                                                i6 += 6;
                                                int i14 = iArr[i12];
                                                obj.f1140f = i14;
                                                c0052a.f1017b = i10;
                                                c0052a.f1018c = i11;
                                                c0052a.f1019d = i13;
                                                c0052a.f1020e = i14;
                                                c0052a.f1016a.add(obj);
                                                obj.f1137c = c0052a.f1017b;
                                                obj.f1138d = c0052a.f1018c;
                                                obj.f1139e = c0052a.f1019d;
                                                obj.f1140f = c0052a.f1020e;
                                                i7++;
                                            }
                                            c0052a.f1021f = c0053b.f1036e;
                                            c0052a.h = c0053b.f1037f;
                                            c0052a.f1022g = true;
                                            c0052a.f1023i = c0053b.h;
                                            c0052a.f1024j = c0053b.f1039i;
                                            c0052a.f1025k = c0053b.f1040j;
                                            c0052a.f1026l = c0053b.f1041k;
                                            c0052a.f1027m = c0053b.f1042l;
                                            c0052a.f1028n = c0053b.f1043m;
                                            c0052a.f1029o = c0053b.f1044n;
                                            c0052a.f1031q = c0053b.f1038g;
                                            int i15 = 0;
                                            while (true) {
                                                ArrayList arrayList3 = c0053b.f1033b;
                                                if (i15 >= arrayList3.size()) {
                                                    break;
                                                }
                                                String str4 = (String) arrayList3.get(i15);
                                                if (str4 != null) {
                                                    z zVar = (z) c0052a.f1016a.get(i15);
                                                    A.e.f(yVar.f1132b.get(str4));
                                                    zVar.getClass();
                                                }
                                                i15++;
                                            }
                                            c0052a.a(1);
                                            if (Log.isLoggable("FragmentManager", 2)) {
                                                Log.v("FragmentManager", "restoreAllState: back stack #" + i5 + " (index " + c0052a.f1031q + "): " + c0052a);
                                                PrintWriter printWriter = new PrintWriter(new A());
                                                c0052a.b("  ", printWriter, false);
                                                printWriter.close();
                                            }
                                            uVar.f1086d.add(c0052a);
                                            i5++;
                                        }
                                        i4 = 0;
                                    } else {
                                        i4 = 0;
                                        uVar.f1086d = null;
                                    }
                                    uVar.h.set(vVar2.f1110d);
                                    String str5 = vVar2.f1111e;
                                    if (str5 != null) {
                                        A.e.f(yVar.f1132b.get(str5));
                                    }
                                    ArrayList arrayList4 = vVar2.f1112f;
                                    if (arrayList4 != null) {
                                        for (int i16 = i4; i16 < arrayList4.size(); i16++) {
                                            uVar.f1090i.put((String) arrayList4.get(i16), (c) vVar2.f1113g.get(i16));
                                        }
                                    }
                                    uVar.y = new ArrayDeque(vVar2.h);
                                } else {
                                    it3.next().getClass();
                                    throw new ClassCastException();
                                }
                            }
                        }
                    }
                    h hVar3 = uVar.f1100s;
                    if (hVar3 != null) {
                        c.j activityResultRegistry = hVar3.f1055d.getActivityResultRegistry();
                        uVar.f1103v = activityResultRegistry.b("FragmentManager:StartActivityForResult", new r(2), new n(uVar, 1));
                        uVar.f1104w = activityResultRegistry.b("FragmentManager:StartIntentSenderForResult", new r(0), new n(uVar, 2));
                        uVar.f1105x = activityResultRegistry.b("FragmentManager:RequestPermissions", new r(1), new n(uVar, 0));
                    }
                    h hVar4 = uVar.f1100s;
                    if (hVar4 != null) {
                        hVar4.f1055d.addOnConfigurationChangedListener(uVar.f1094m);
                    }
                    h hVar5 = uVar.f1100s;
                    if (hVar5 != null) {
                        hVar5.f1055d.addOnTrimMemoryListener(uVar.f1095n);
                    }
                    h hVar6 = uVar.f1100s;
                    if (hVar6 != null) {
                        hVar6.f1055d.addOnMultiWindowModeChangedListener(uVar.f1096o);
                    }
                    h hVar7 = uVar.f1100s;
                    if (hVar7 != null) {
                        hVar7.f1055d.addOnPictureInPictureModeChangedListener(uVar.f1097p);
                    }
                    h hVar8 = uVar.f1100s;
                    if (hVar8 != null) {
                        hVar8.f1055d.addMenuProvider(uVar.f1098q);
                        return;
                    }
                    return;
                }
                throw new IllegalStateException("Already attached");
            }
        });
    }

    public final View dispatchFragmentsOnCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return this.mFragments.f1060a.f1054c.f1087e.onCreateView(view, str, context, attributeSet);
    }

    @Override // android.app.Activity
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        super.dump(str, fileDescriptor, printWriter, strArr);
        if (!shouldDumpInternalState(strArr)) {
            return;
        }
        printWriter.print(str);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        String str2 = str + "  ";
        printWriter.print(str2);
        printWriter.print("mCreated=");
        printWriter.print(this.mCreated);
        printWriter.print(" mResumed=");
        printWriter.print(this.mResumed);
        printWriter.print(" mStopped=");
        printWriter.print(this.mStopped);
        if (getApplication() != null) {
            n.l lVar = ((S.c) new A.f(getViewModelStore(), S.c.f617c).B(S.c.class)).f618b;
            if (lVar.f2130c > 0) {
                printWriter.print(str2);
                printWriter.println("Loaders:");
                if (lVar.f2130c > 0) {
                    if (lVar.f2129b[0] == null) {
                        printWriter.print(str2);
                        printWriter.print("  #");
                        printWriter.print(lVar.f2128a[0]);
                        printWriter.print(": ");
                        throw null;
                    }
                    throw new ClassCastException();
                }
            }
        }
        u uVar = this.mFragments.f1060a.f1054c;
        uVar.getClass();
        String c2 = A.e.c(str, "    ");
        y yVar = uVar.f1085c;
        yVar.getClass();
        HashMap hashMap = yVar.f1132b;
        if (!hashMap.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Active Fragments:");
            Iterator it = hashMap.values().iterator();
            while (it.hasNext()) {
                A.e.f(it.next());
                printWriter.print(str);
                printWriter.println("null");
            }
        }
        ArrayList arrayList = yVar.f1131a;
        int size2 = arrayList.size();
        if (size2 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            if (size2 > 0) {
                if (arrayList.get(0) == null) {
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(0);
                    printWriter.print(": ");
                    throw null;
                }
                throw new ClassCastException();
            }
        }
        ArrayList arrayList2 = uVar.f1086d;
        if (arrayList2 != null && (size = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i2 = 0; i2 < size; i2++) {
                C0052a c0052a = (C0052a) uVar.f1086d.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(c0052a.toString());
                c0052a.b(c2, printWriter, true);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + uVar.h.get());
        synchronized (uVar.f1083a) {
            try {
                int size3 = uVar.f1083a.size();
                if (size3 > 0) {
                    printWriter.print(str);
                    printWriter.println("Pending Actions:");
                    for (int i3 = 0; i3 < size3; i3++) {
                        C0052a c0052a2 = (C0052a) uVar.f1083a.get(i3);
                        printWriter.print(str);
                        printWriter.print("  #");
                        printWriter.print(i3);
                        printWriter.print(": ");
                        printWriter.println(c0052a2);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(uVar.f1100s);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(uVar.f1101t);
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(uVar.f1099r);
        printWriter.print(" mStateSaved=");
        printWriter.print(uVar.f1106z);
        printWriter.print(" mStopped=");
        printWriter.print(uVar.f1076A);
        printWriter.print(" mDestroyed=");
        printWriter.println(uVar.f1077B);
    }

    public t getSupportFragmentManager() {
        return this.mFragments.f1060a.f1054c;
    }

    @Deprecated
    public S.a getSupportLoaderManager() {
        return new S.d(this, getViewModelStore());
    }

    public void markFragmentsCreated() {
        Iterator it = getSupportFragmentManager().f1085c.c().iterator();
        while (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
        }
    }

    @Override // a.AbstractActivityC0046m, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        this.mFragments.a();
        super.onActivityResult(i2, i3, intent);
    }

    @Deprecated
    public void onAttachFragment(d dVar) {
    }

    @Override // a.AbstractActivityC0046m, x.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFragmentLifecycleRegistry.e(EnumC0066m.ON_CREATE);
        u uVar = this.mFragments.f1060a.f1054c;
        uVar.f1106z = false;
        uVar.f1076A = false;
        uVar.f1081F.getClass();
        uVar.c(1);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View dispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(view, str, context, attributeSet);
        return dispatchFragmentsOnCreateView == null ? super.onCreateView(view, str, context, attributeSet) : dispatchFragmentsOnCreateView;
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        u uVar = this.mFragments.f1060a.f1054c;
        boolean z2 = true;
        uVar.f1077B = true;
        uVar.e(true);
        Iterator it = uVar.b().iterator();
        if (!it.hasNext()) {
            h hVar = uVar.f1100s;
            y yVar = uVar.f1085c;
            if (hVar != null) {
                z2 = yVar.f1134d.f1118e;
            } else {
                i iVar = hVar.f1052a;
                if (iVar != null) {
                    z2 = true ^ iVar.isChangingConfigurations();
                }
            }
            if (z2) {
                Iterator it2 = uVar.f1090i.values().iterator();
                while (it2.hasNext()) {
                    for (String str : ((c) it2.next()).f1045a) {
                        w wVar = yVar.f1134d;
                        wVar.getClass();
                        if (Log.isLoggable("FragmentManager", 3)) {
                            Log.d("FragmentManager", "Clearing non-config state for saved state of Fragment " + str);
                        }
                        HashMap hashMap = wVar.f1116c;
                        w wVar2 = (w) hashMap.get(str);
                        if (wVar2 != null) {
                            wVar2.b();
                            hashMap.remove(str);
                        }
                        HashMap hashMap2 = wVar.f1117d;
                        S s2 = (S) hashMap2.get(str);
                        if (s2 != null) {
                            s2.a();
                            hashMap2.remove(str);
                        }
                    }
                }
            }
            uVar.c(-1);
            h hVar2 = uVar.f1100s;
            if (hVar2 != null) {
                hVar2.f1055d.removeOnTrimMemoryListener(uVar.f1095n);
            }
            h hVar3 = uVar.f1100s;
            if (hVar3 != null) {
                hVar3.f1055d.removeOnConfigurationChangedListener(uVar.f1094m);
            }
            h hVar4 = uVar.f1100s;
            if (hVar4 != null) {
                hVar4.f1055d.removeOnMultiWindowModeChangedListener(uVar.f1096o);
            }
            h hVar5 = uVar.f1100s;
            if (hVar5 != null) {
                hVar5.f1055d.removeOnPictureInPictureModeChangedListener(uVar.f1097p);
            }
            h hVar6 = uVar.f1100s;
            if (hVar6 != null) {
                hVar6.f1055d.removeMenuProvider(uVar.f1098q);
            }
            uVar.f1100s = null;
            uVar.f1101t = null;
            if (uVar.f1088f != null) {
                Iterator it3 = uVar.f1089g.f1067b.iterator();
                while (it3.hasNext()) {
                    ((InterfaceC0035b) it3.next()).cancel();
                }
                uVar.f1088f = null;
            }
            c.i iVar2 = uVar.f1103v;
            if (iVar2 != null) {
                iVar2.f1248a.d(iVar2.f1249b);
                c.i iVar3 = uVar.f1104w;
                iVar3.f1248a.d(iVar3.f1249b);
                c.i iVar4 = uVar.f1105x;
                iVar4.f1248a.d(iVar4.f1249b);
            }
            this.mFragmentLifecycleRegistry.e(EnumC0066m.ON_DESTROY);
            return;
        }
        ((B) it.next()).a();
        throw null;
    }

    @Override // a.AbstractActivityC0046m, android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i2, MenuItem menuItem) {
        if (super.onMenuItemSelected(i2, menuItem)) {
            return true;
        }
        if (i2 == 6) {
            u uVar = this.mFragments.f1060a.f1054c;
            if (uVar.f1099r >= 1) {
                Iterator it = uVar.f1085c.c().iterator();
                while (it.hasNext()) {
                    if (it.next() != null) {
                        throw new ClassCastException();
                    }
                }
            }
        }
        return false;
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.mResumed = false;
        this.mFragments.f1060a.f1054c.c(5);
        this.mFragmentLifecycleRegistry.e(EnumC0066m.ON_PAUSE);
    }

    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        onResumeFragments();
    }

    @Override // a.AbstractActivityC0046m, android.app.Activity
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        this.mFragments.a();
        super.onRequestPermissionsResult(i2, strArr, iArr);
    }

    @Override // android.app.Activity
    public void onResume() {
        this.mFragments.a();
        super.onResume();
        this.mResumed = true;
        this.mFragments.f1060a.f1054c.e(true);
    }

    public void onResumeFragments() {
        this.mFragmentLifecycleRegistry.e(EnumC0066m.ON_RESUME);
        u uVar = this.mFragments.f1060a.f1054c;
        uVar.f1106z = false;
        uVar.f1076A = false;
        uVar.f1081F.getClass();
        uVar.c(7);
    }

    @Override // android.app.Activity
    public void onStart() {
        this.mFragments.a();
        super.onStart();
        this.mStopped = false;
        if (!this.mCreated) {
            this.mCreated = true;
            u uVar = this.mFragments.f1060a.f1054c;
            uVar.f1106z = false;
            uVar.f1076A = false;
            uVar.f1081F.getClass();
            uVar.c(4);
        }
        this.mFragments.f1060a.f1054c.e(true);
        this.mFragmentLifecycleRegistry.e(EnumC0066m.ON_START);
        u uVar2 = this.mFragments.f1060a.f1054c;
        uVar2.f1106z = false;
        uVar2.f1076A = false;
        uVar2.f1081F.getClass();
        uVar2.c(5);
    }

    @Override // android.app.Activity
    public void onStateNotSaved() {
        this.mFragments.a();
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        this.mStopped = true;
        markFragmentsCreated();
        u uVar = this.mFragments.f1060a.f1054c;
        uVar.f1076A = true;
        uVar.f1081F.getClass();
        uVar.c(4);
        this.mFragmentLifecycleRegistry.e(EnumC0066m.ON_STOP);
    }

    public void setEnterSharedElementCallback(x.i iVar) {
        setEnterSharedElementCallback((SharedElementCallback) null);
    }

    public void setExitSharedElementCallback(x.i iVar) {
        setExitSharedElementCallback((SharedElementCallback) null);
    }

    public void startActivityFromFragment(d dVar, Intent intent, int i2, Bundle bundle) {
        if (i2 == -1) {
            startActivityForResult(intent, -1, bundle);
            return;
        }
        throw null;
    }

    @Deprecated
    public void startIntentSenderFromFragment(d dVar, IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5, Bundle bundle) {
        if (i2 == -1) {
            startIntentSenderForResult(intentSender, i2, intent, i3, i4, i5, bundle);
            return;
        }
        throw null;
    }

    public void supportFinishAfterTransition() {
        finishAfterTransition();
    }

    public void supportPostponeEnterTransition() {
        postponeEnterTransition();
    }

    public void supportStartPostponedEnterTransition() {
        startPostponedEnterTransition();
    }

    @Deprecated
    public final void validateRequestPermissionsRequestCode(int i2) {
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View dispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(null, str, context, attributeSet);
        return dispatchFragmentsOnCreateView == null ? super.onCreateView(str, context, attributeSet) : dispatchFragmentsOnCreateView;
    }

    public void startActivityFromFragment(d dVar, Intent intent, int i2) {
        startActivityFromFragment(dVar, intent, i2, (Bundle) null);
    }
}
