package C0;

import B0.z;
import H.D;
import H.M;
import H.N;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import c.C0095a;
import j0.AbstractC0150d;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.IDN;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.WeakHashMap;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.conscrypt.R;

/* loaded from: classes.dex */
public abstract class m {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f230a = false;

    /* renamed from: b, reason: collision with root package name */
    public static Method f231b = null;

    /* renamed from: c, reason: collision with root package name */
    public static boolean f232c = false;

    /* renamed from: d, reason: collision with root package name */
    public static Field f233d;

    public static void a(StringBuilder sb, Object obj, a.o oVar) {
        boolean z2;
        if (oVar != null) {
            sb.append((CharSequence) oVar.c(obj));
            return;
        }
        if (obj == null) {
            z2 = true;
        } else {
            z2 = obj instanceof CharSequence;
        }
        if (z2) {
            sb.append((CharSequence) obj);
        } else if (obj instanceof Character) {
            sb.append(((Character) obj).charValue());
        } else {
            sb.append((CharSequence) String.valueOf(obj));
        }
    }

    public static final void b(H0.g gVar, Throwable th) {
        if (gVar != null) {
            if (th == null) {
                gVar.close();
                return;
            }
            try {
                gVar.close();
            } catch (Throwable th2) {
                d.e(th, th2);
            }
        }
    }

    public static final void c(int i2, int i3) {
        if (i2 <= i3) {
            return;
        }
        throw new IndexOutOfBoundsException("toIndex (" + i2 + ") is greater than size (" + i3 + ").");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static M.t d(android.content.Context r8) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r0 < r1) goto Ld
            M.c r0 = new M.c
            r1 = 4
            r0.<init>(r1)
            goto L13
        Ld:
            A.m r0 = new A.m
            r1 = 4
            r0.<init>(r1)
        L13:
            android.content.pm.PackageManager r1 = r8.getPackageManager()
            java.lang.String r2 = "Package manager required to locate emoji font provider"
            C0.d.j(r1, r2)
            android.content.Intent r2 = new android.content.Intent
            java.lang.String r3 = "androidx.content.action.LOAD_EMOJI_FONT"
            r2.<init>(r3)
            r3 = 0
            java.util.List r2 = r1.queryIntentContentProviders(r2, r3)
            java.util.Iterator r2 = r2.iterator()
        L2c:
            boolean r4 = r2.hasNext()
            r5 = 0
            if (r4 == 0) goto L48
            java.lang.Object r4 = r2.next()
            android.content.pm.ResolveInfo r4 = (android.content.pm.ResolveInfo) r4
            android.content.pm.ProviderInfo r4 = r4.providerInfo
            if (r4 == 0) goto L2c
            android.content.pm.ApplicationInfo r6 = r4.applicationInfo
            if (r6 == 0) goto L2c
            int r6 = r6.flags
            r7 = 1
            r6 = r6 & r7
            if (r6 != r7) goto L2c
            goto L49
        L48:
            r4 = r5
        L49:
            if (r4 != 0) goto L4d
        L4b:
            r1 = r5
            goto L7c
        L4d:
            java.lang.String r2 = r4.authority     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L75
            java.lang.String r4 = r4.packageName     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L75
            android.content.pm.Signature[] r0 = r0.f(r1, r4)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L75
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L75
            r1.<init>()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L75
            int r6 = r0.length     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L75
        L5b:
            if (r3 >= r6) goto L69
            r7 = r0[r3]     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L75
            byte[] r7 = r7.toByteArray()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L75
            r1.add(r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L75
            int r3 = r3 + 1
            goto L5b
        L69:
            java.util.List r0 = java.util.Collections.singletonList(r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L75
            E.g r1 = new E.g     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L75
            java.lang.String r3 = "emojicompat-emoji-font"
            r1.<init>(r2, r4, r3, r0)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L75
            goto L7c
        L75:
            r0 = move-exception
            java.lang.String r1 = "emoji2.text.DefaultEmojiConfig"
            android.util.Log.wtf(r1, r0)
            goto L4b
        L7c:
            if (r1 != 0) goto L7f
            goto L89
        L7f:
            M.t r5 = new M.t
            M.s r0 = new M.s
            r0.<init>(r8, r1)
            r5.<init>(r0)
        L89:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: C0.m.d(android.content.Context):M.t");
    }

    public static int e(String str, int i2, int i3, boolean z2) {
        boolean z3;
        while (i2 < i3) {
            int i4 = i2 + 1;
            char charAt = str.charAt(i2);
            if ((charAt >= ' ' || charAt == '\t') && charAt < 127 && ((charAt > '9' || '0' > charAt) && ((charAt > 'z' || 'a' > charAt) && ((charAt > 'Z' || 'A' > charAt) && charAt != ':')))) {
                z3 = false;
            } else {
                z3 = true;
            }
            if (z3 == (!z2)) {
                return i2;
            }
            i2 = i4;
        }
        return i3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x00d6, code lost:
    
        if (r7 == r0) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d8, code lost:
    
        if (r8 != (-1)) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00da, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00db, code lost:
    
        r1 = r7 - r8;
        java.lang.System.arraycopy(r3, r8, r3, 16 - r1, r1);
        java.util.Arrays.fill(r3, r8, (16 - r7) + r8, (byte) 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00ed, code lost:
    
        return java.net.InetAddress.getByAddress(r3);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00a7  */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1, types: [int] */
    /* JADX WARN: Type inference failed for: r14v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.net.InetAddress f(java.lang.String r17, int r18, int r19) {
        /*
            Method dump skipped, instructions count: 238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: C0.m.f(java.lang.String, int, int):java.net.InetAddress");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v5, types: [H.M, java.lang.Object] */
    public static boolean g(View view, KeyEvent keyEvent) {
        ArrayList arrayList;
        int size;
        int indexOfKey;
        WeakHashMap weakHashMap = N.f327a;
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        ArrayList arrayList2 = M.f323d;
        M m2 = (M) view.getTag(R.id.tag_unhandled_key_event_manager);
        WeakReference weakReference = null;
        M m3 = m2;
        if (m2 == null) {
            ?? obj = new Object();
            obj.f324a = null;
            obj.f325b = null;
            obj.f326c = null;
            view.setTag(R.id.tag_unhandled_key_event_manager, obj);
            m3 = obj;
        }
        WeakReference weakReference2 = m3.f326c;
        if (weakReference2 != null && weakReference2.get() == keyEvent) {
            return false;
        }
        m3.f326c = new WeakReference(keyEvent);
        if (m3.f325b == null) {
            m3.f325b = new SparseArray();
        }
        SparseArray sparseArray = m3.f325b;
        if (keyEvent.getAction() == 1 && (indexOfKey = sparseArray.indexOfKey(keyEvent.getKeyCode())) >= 0) {
            weakReference = (WeakReference) sparseArray.valueAt(indexOfKey);
            sparseArray.removeAt(indexOfKey);
        }
        if (weakReference == null) {
            weakReference = (WeakReference) sparseArray.get(keyEvent.getKeyCode());
        }
        if (weakReference == null) {
            return false;
        }
        View view2 = (View) weakReference.get();
        if (view2 == null || !view2.isAttachedToWindow() || (arrayList = (ArrayList) view2.getTag(R.id.tag_unhandled_key_listeners)) == null || (size = arrayList.size() - 1) < 0) {
            return true;
        }
        arrayList.get(size).getClass();
        throw new ClassCastException();
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean h(H.InterfaceC0017k r6, android.view.View r7, android.view.Window.Callback r8, android.view.KeyEvent r9) {
        /*
            r0 = 0
            if (r6 != 0) goto L4
            return r0
        L4:
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 28
            if (r1 < r2) goto Lf
            boolean r6 = r6.superDispatchKeyEvent(r9)
            return r6
        Lf:
            boolean r1 = r8 instanceof android.app.Activity
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L81
            android.app.Activity r8 = (android.app.Activity) r8
            r8.onUserInteraction()
            android.view.Window r6 = r8.getWindow()
            r7 = 8
            boolean r7 = r6.hasFeature(r7)
            if (r7 == 0) goto L64
            android.app.ActionBar r7 = r8.getActionBar()
            int r1 = r9.getKeyCode()
            r4 = 82
            if (r1 != r4) goto L64
            if (r7 == 0) goto L64
            boolean r1 = C0.m.f230a
            if (r1 != 0) goto L4c
            java.lang.Class r1 = r7.getClass()     // Catch: java.lang.NoSuchMethodException -> L4a
            java.lang.String r4 = "onMenuKeyEvent"
            java.lang.Class<android.view.KeyEvent> r5 = android.view.KeyEvent.class
            java.lang.Class[] r5 = new java.lang.Class[]{r5}     // Catch: java.lang.NoSuchMethodException -> L4a
            java.lang.reflect.Method r1 = r1.getMethod(r4, r5)     // Catch: java.lang.NoSuchMethodException -> L4a
            C0.m.f231b = r1     // Catch: java.lang.NoSuchMethodException -> L4a
        L4a:
            C0.m.f230a = r3
        L4c:
            java.lang.reflect.Method r1 = C0.m.f231b
            if (r1 == 0) goto L61
            java.lang.Object[] r4 = new java.lang.Object[]{r9}     // Catch: java.lang.Throwable -> L61
            java.lang.Object r7 = r1.invoke(r7, r4)     // Catch: java.lang.Throwable -> L61
            if (r7 != 0) goto L5b
            goto L61
        L5b:
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch: java.lang.Throwable -> L61
            boolean r0 = r7.booleanValue()     // Catch: java.lang.Throwable -> L61
        L61:
            if (r0 == 0) goto L64
            goto L80
        L64:
            boolean r7 = r6.superDispatchKeyEvent(r9)
            if (r7 == 0) goto L6b
            goto L80
        L6b:
            android.view.View r6 = r6.getDecorView()
            boolean r7 = H.N.b(r6, r9)
            if (r7 == 0) goto L76
            goto L80
        L76:
            if (r6 == 0) goto L7c
            android.view.KeyEvent$DispatcherState r2 = r6.getKeyDispatcherState()
        L7c:
            boolean r3 = r9.dispatch(r8, r2, r8)
        L80:
            return r3
        L81:
            boolean r1 = r8 instanceof android.app.Dialog
            if (r1 == 0) goto Ld4
            android.app.Dialog r8 = (android.app.Dialog) r8
            boolean r6 = C0.m.f232c
            if (r6 != 0) goto L9a
            java.lang.Class<android.app.Dialog> r6 = android.app.Dialog.class
            java.lang.String r7 = "mOnKeyListener"
            java.lang.reflect.Field r6 = r6.getDeclaredField(r7)     // Catch: java.lang.NoSuchFieldException -> L98
            C0.m.f233d = r6     // Catch: java.lang.NoSuchFieldException -> L98
            r6.setAccessible(r3)     // Catch: java.lang.NoSuchFieldException -> L98
        L98:
            C0.m.f232c = r3
        L9a:
            java.lang.reflect.Field r6 = C0.m.f233d
            if (r6 == 0) goto La5
            java.lang.Object r6 = r6.get(r8)     // Catch: java.lang.IllegalAccessException -> La5
            android.content.DialogInterface$OnKeyListener r6 = (android.content.DialogInterface.OnKeyListener) r6     // Catch: java.lang.IllegalAccessException -> La5
            goto La6
        La5:
            r6 = r2
        La6:
            if (r6 == 0) goto Lb3
            int r7 = r9.getKeyCode()
            boolean r6 = r6.onKey(r8, r7, r9)
            if (r6 == 0) goto Lb3
            goto Ld3
        Lb3:
            android.view.Window r6 = r8.getWindow()
            boolean r7 = r6.superDispatchKeyEvent(r9)
            if (r7 == 0) goto Lbe
            goto Ld3
        Lbe:
            android.view.View r6 = r6.getDecorView()
            boolean r7 = H.N.b(r6, r9)
            if (r7 == 0) goto Lc9
            goto Ld3
        Lc9:
            if (r6 == 0) goto Lcf
            android.view.KeyEvent$DispatcherState r2 = r6.getKeyDispatcherState()
        Lcf:
            boolean r3 = r9.dispatch(r8, r2, r8)
        Ld3:
            return r3
        Ld4:
            if (r7 == 0) goto Ldc
            boolean r7 = H.N.b(r7, r9)
            if (r7 != 0) goto Le2
        Ldc:
            boolean r6 = r6.superDispatchKeyEvent(r9)
            if (r6 == 0) goto Le3
        Le2:
            r0 = r3
        Le3:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: C0.m.h(H.k, android.view.View, android.view.Window$Callback, android.view.KeyEvent):boolean");
    }

    public static u0.o i(String str) {
        AbstractC0150d.e(str, "<this>");
        Matcher matcher = u0.o.f2491c.matcher(str);
        if (matcher.lookingAt()) {
            String group = matcher.group(1);
            AbstractC0150d.d(group, "typeSubtype.group(1)");
            Locale locale = Locale.US;
            AbstractC0150d.d(locale, "US");
            AbstractC0150d.d(group.toLowerCase(locale), "this as java.lang.String).toLowerCase(locale)");
            String group2 = matcher.group(2);
            AbstractC0150d.d(group2, "typeSubtype.group(2)");
            AbstractC0150d.d(group2.toLowerCase(locale), "this as java.lang.String).toLowerCase(locale)");
            ArrayList arrayList = new ArrayList();
            Matcher matcher2 = u0.o.f2492d.matcher(str);
            int end = matcher.end();
            while (end < str.length()) {
                matcher2.region(end, str.length());
                if (matcher2.lookingAt()) {
                    String group3 = matcher2.group(1);
                    if (group3 == null) {
                        end = matcher2.end();
                    } else {
                        String group4 = matcher2.group(2);
                        if (group4 == null) {
                            group4 = matcher2.group(3);
                        } else if (q0.k.B(group4, false, "'") && group4.endsWith("'") && group4.length() > 2) {
                            group4 = group4.substring(1, group4.length() - 1);
                            AbstractC0150d.d(group4, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        arrayList.add(group3);
                        arrayList.add(group4);
                        end = matcher2.end();
                    }
                } else {
                    StringBuilder sb = new StringBuilder("Parameter is not formatted correctly: \"");
                    String substring = str.substring(end);
                    AbstractC0150d.d(substring, "this as java.lang.String).substring(startIndex)");
                    sb.append(substring);
                    sb.append("\" for: \"");
                    sb.append(str);
                    sb.append('\"');
                    throw new IllegalArgumentException(sb.toString().toString());
                }
            }
            Object[] array = arrayList.toArray(new String[0]);
            if (array != null) {
                return new u0.o((String[]) array, str);
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        throw new IllegalArgumentException(("No subtype found for: \"" + str + '\"').toString());
    }

    public static Object j(String str, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 34) {
            return D.c.b(str, bundle);
        }
        Parcelable parcelable = bundle.getParcelable(str);
        if (!C0095a.class.isInstance(parcelable)) {
            return null;
        }
        return parcelable;
    }

    public static final int k(int i2, int i3, int i4) {
        if (i4 > 0) {
            if (i2 < i3) {
                int i5 = i3 % i4;
                if (i5 < 0) {
                    i5 += i4;
                }
                int i6 = i2 % i4;
                if (i6 < 0) {
                    i6 += i4;
                }
                int i7 = (i5 - i6) % i4;
                if (i7 < 0) {
                    i7 += i4;
                }
                return i3 - i7;
            }
            return i3;
        }
        if (i4 < 0) {
            if (i2 > i3) {
                int i8 = -i4;
                int i9 = i2 % i8;
                if (i9 < 0) {
                    i9 += i8;
                }
                int i10 = i3 % i8;
                if (i10 < 0) {
                    i10 += i8;
                }
                int i11 = (i9 - i10) % i8;
                if (i11 < 0) {
                    i11 += i8;
                }
                return i3 + i11;
            }
            return i3;
        }
        throw new IllegalArgumentException("Step is zero.");
    }

    public static final boolean l(AssertionError assertionError) {
        boolean D2;
        Logger logger = H0.l.f427a;
        if (assertionError.getCause() == null) {
            return false;
        }
        String message = assertionError.getMessage();
        if (message == null) {
            D2 = false;
        } else {
            D2 = q0.d.D(message, "getsockname failed");
        }
        if (!D2) {
            return false;
        }
        return true;
    }

    public static void m(EditorInfo editorInfo, InputConnection inputConnection, TextView textView) {
        if (inputConnection != null && editorInfo.hintText == null) {
            for (ViewParent parent = textView.getParent(); parent instanceof View; parent = parent.getParent()) {
            }
        }
    }

    public static u0.o n(String str) {
        AbstractC0150d.e(str, "<this>");
        try {
            return i(str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static long o(String str, int i2) {
        int e2 = e(str, 0, i2, false);
        Matcher matcher = u0.i.f2461m.matcher(str);
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        int i6 = -1;
        int i7 = -1;
        int i8 = -1;
        while (e2 < i2) {
            int e3 = e(str, e2 + 1, i2, true);
            matcher.region(e2, e3);
            if (i4 == -1 && matcher.usePattern(u0.i.f2461m).matches()) {
                String group = matcher.group(1);
                AbstractC0150d.d(group, "matcher.group(1)");
                i4 = Integer.parseInt(group);
                String group2 = matcher.group(2);
                AbstractC0150d.d(group2, "matcher.group(2)");
                i7 = Integer.parseInt(group2);
                String group3 = matcher.group(3);
                AbstractC0150d.d(group3, "matcher.group(3)");
                i8 = Integer.parseInt(group3);
            } else if (i5 == -1 && matcher.usePattern(u0.i.f2460l).matches()) {
                String group4 = matcher.group(1);
                AbstractC0150d.d(group4, "matcher.group(1)");
                i5 = Integer.parseInt(group4);
            } else {
                if (i6 == -1) {
                    Pattern pattern = u0.i.f2459k;
                    if (matcher.usePattern(pattern).matches()) {
                        String group5 = matcher.group(1);
                        AbstractC0150d.d(group5, "matcher.group(1)");
                        Locale locale = Locale.US;
                        AbstractC0150d.d(locale, "US");
                        String lowerCase = group5.toLowerCase(locale);
                        AbstractC0150d.d(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                        String pattern2 = pattern.pattern();
                        AbstractC0150d.d(pattern2, "MONTH_PATTERN.pattern()");
                        i6 = q0.d.H(pattern2, lowerCase, 0, 6) / 4;
                    }
                }
                if (i3 == -1 && matcher.usePattern(u0.i.f2458j).matches()) {
                    String group6 = matcher.group(1);
                    AbstractC0150d.d(group6, "matcher.group(1)");
                    i3 = Integer.parseInt(group6);
                }
            }
            e2 = e(str, e3 + 1, i2, false);
        }
        if (70 <= i3 && i3 < 100) {
            i3 += 1900;
        }
        if (i3 >= 0 && i3 < 70) {
            i3 += 2000;
        }
        if (i3 >= 1601) {
            if (i6 != -1) {
                if (1 <= i5 && i5 < 32) {
                    if (i4 >= 0 && i4 < 24) {
                        if (i7 >= 0 && i7 < 60) {
                            if (i8 >= 0 && i8 < 60) {
                                GregorianCalendar gregorianCalendar = new GregorianCalendar(v0.b.f2813f);
                                gregorianCalendar.setLenient(false);
                                gregorianCalendar.set(1, i3);
                                gregorianCalendar.set(2, i6 - 1);
                                gregorianCalendar.set(5, i5);
                                gregorianCalendar.set(11, i4);
                                gregorianCalendar.set(12, i7);
                                gregorianCalendar.set(13, i8);
                                gregorianCalendar.set(14, 0);
                                return gregorianCalendar.getTimeInMillis();
                            }
                            throw new IllegalArgumentException("Failed requirement.");
                        }
                        throw new IllegalArgumentException("Failed requirement.");
                    }
                    throw new IllegalArgumentException("Failed requirement.");
                }
                throw new IllegalArgumentException("Failed requirement.");
            }
            throw new IllegalArgumentException("Failed requirement.");
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    public static final boolean p(String str) {
        AbstractC0150d.e(str, "method");
        if (!str.equals("GET") && !str.equals("HEAD")) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v14, types: [N.b, H.D] */
    public static N.b q(MappedByteBuffer mappedByteBuffer) {
        long j2;
        ByteBuffer duplicate = mappedByteBuffer.duplicate();
        duplicate.order(ByteOrder.BIG_ENDIAN);
        duplicate.position(duplicate.position() + 4);
        int i2 = duplicate.getShort() & 65535;
        if (i2 <= 100) {
            duplicate.position(duplicate.position() + 6);
            int i3 = 0;
            while (true) {
                if (i3 < i2) {
                    int i4 = duplicate.getInt();
                    duplicate.position(duplicate.position() + 4);
                    j2 = duplicate.getInt() & 4294967295L;
                    duplicate.position(duplicate.position() + 4);
                    if (1835365473 == i4) {
                        break;
                    }
                    i3++;
                } else {
                    j2 = -1;
                    break;
                }
            }
            if (j2 != -1) {
                duplicate.position(duplicate.position() + ((int) (j2 - duplicate.position())));
                duplicate.position(duplicate.position() + 12);
                long j3 = duplicate.getInt() & 4294967295L;
                for (int i5 = 0; i5 < j3; i5++) {
                    int i6 = duplicate.getInt();
                    long j4 = duplicate.getInt() & 4294967295L;
                    duplicate.getInt();
                    if (1164798569 == i6 || 1701669481 == i6) {
                        duplicate.position((int) (j4 + j2));
                        ?? d2 = new D();
                        duplicate.order(ByteOrder.LITTLE_ENDIAN);
                        int position = duplicate.position() + duplicate.getInt(duplicate.position());
                        d2.f319d = duplicate;
                        d2.f316a = position;
                        int i7 = position - duplicate.getInt(position);
                        d2.f317b = i7;
                        d2.f318c = ((ByteBuffer) d2.f319d).getShort(i7);
                        return d2;
                    }
                }
            }
            throw new IOException("Cannot read metadata.");
        }
        throw new IOException("Cannot read metadata.");
    }

    public static final H0.b r(Socket socket) {
        Logger logger = H0.l.f427a;
        z zVar = new z(1, socket);
        OutputStream outputStream = socket.getOutputStream();
        AbstractC0150d.d(outputStream, "getOutputStream()");
        return new H0.b(zVar, new H0.b(outputStream, zVar));
    }

    public static final H0.c s(Socket socket) {
        int i2 = 1;
        Logger logger = H0.l.f427a;
        z zVar = new z(i2, socket);
        InputStream inputStream = socket.getInputStream();
        AbstractC0150d.d(inputStream, "getInputStream()");
        return new H0.c(zVar, new H0.c(inputStream, zVar, i2), 0);
    }

    public static n0.a t(n0.c cVar, int i2) {
        boolean z2;
        AbstractC0150d.e(cVar, "<this>");
        if (i2 > 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        Integer valueOf = Integer.valueOf(i2);
        if (z2) {
            if (cVar.f2133c <= 0) {
                i2 = -i2;
            }
            return new n0.a(cVar.f2131a, cVar.f2132b, i2);
        }
        throw new IllegalArgumentException("Step must be positive, was: " + valueOf + '.');
    }

    /* JADX WARN: Type inference failed for: r9v8, types: [java.lang.Object, H0.e] */
    public static final String u(String str) {
        InetAddress f2;
        AbstractC0150d.e(str, "<this>");
        int i2 = 0;
        int i3 = -1;
        if (q0.d.D(str, ":")) {
            if (q0.k.B(str, false, "[") && str.endsWith("]")) {
                f2 = f(str, 1, str.length() - 1);
            } else {
                f2 = f(str, 0, str.length());
            }
            if (f2 == null) {
                return null;
            }
            byte[] address = f2.getAddress();
            if (address.length == 16) {
                int i4 = 0;
                int i5 = 0;
                while (i4 < address.length) {
                    int i6 = i4;
                    while (i6 < 16 && address[i6] == 0 && address[i6 + 1] == 0) {
                        i6 += 2;
                    }
                    int i7 = i6 - i4;
                    if (i7 > i5 && i7 >= 4) {
                        i3 = i4;
                        i5 = i7;
                    }
                    i4 = i6 + 2;
                }
                ?? obj = new Object();
                while (i2 < address.length) {
                    if (i2 == i3) {
                        obj.u(58);
                        i2 += i5;
                        if (i2 == 16) {
                            obj.u(58);
                        }
                    } else {
                        if (i2 > 0) {
                            obj.u(58);
                        }
                        byte b2 = address[i2];
                        byte[] bArr = v0.b.f2808a;
                        obj.v(((b2 & 255) << 8) | (address[i2 + 1] & 255));
                        i2 += 2;
                    }
                }
                return obj.n(obj.f412b, q0.a.f2219a);
            }
            if (address.length == 4) {
                return f2.getHostAddress();
            }
            throw new AssertionError("Invalid IPv6 address: '" + str + '\'');
        }
        try {
            String ascii = IDN.toASCII(str);
            AbstractC0150d.d(ascii, "toASCII(host)");
            Locale locale = Locale.US;
            AbstractC0150d.d(locale, "US");
            String lowerCase = ascii.toLowerCase(locale);
            AbstractC0150d.d(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            if (lowerCase.length() == 0) {
                return null;
            }
            int length = lowerCase.length();
            int i8 = 0;
            while (i8 < length) {
                int i9 = i8 + 1;
                char charAt = lowerCase.charAt(i8);
                if (AbstractC0150d.f(charAt, 31) <= 0 || AbstractC0150d.f(charAt, 127) >= 0 || q0.d.G(" #%/:?@[\\]", charAt, 0, 6) != -1) {
                    return null;
                }
                i8 = i9;
            }
            return lowerCase;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [n0.c, n0.a] */
    public static n0.c v(int i2, int i3) {
        if (i3 <= Integer.MIN_VALUE) {
            n0.c cVar = n0.c.f2138d;
            return n0.c.f2138d;
        }
        return new n0.a(i2, i3 - 1, 1);
    }
}
