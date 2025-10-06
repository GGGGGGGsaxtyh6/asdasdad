package v;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import org.conscrypt.FileClientSessionCache;
import org.conscrypt.ct.CTConstants;
import org.xmlpull.v1.XmlPullParserException;
import r.AbstractC0233a;
import u.AbstractC0250a;

/* loaded from: classes.dex */
public final class n {

    /* renamed from: d, reason: collision with root package name */
    public static final int[] f2790d = {0, 4, 8};

    /* renamed from: e, reason: collision with root package name */
    public static final SparseIntArray f2791e;

    /* renamed from: f, reason: collision with root package name */
    public static final SparseIntArray f2792f;

    /* renamed from: a, reason: collision with root package name */
    public final HashMap f2793a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    public final boolean f2794b = true;

    /* renamed from: c, reason: collision with root package name */
    public final HashMap f2795c = new HashMap();

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        f2791e = sparseIntArray;
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        f2792f = sparseIntArray2;
        sparseIntArray.append(82, 25);
        sparseIntArray.append(83, 26);
        sparseIntArray.append(85, 29);
        sparseIntArray.append(86, 30);
        sparseIntArray.append(92, 36);
        sparseIntArray.append(91, 35);
        sparseIntArray.append(63, 4);
        sparseIntArray.append(62, 3);
        sparseIntArray.append(58, 1);
        sparseIntArray.append(60, 91);
        sparseIntArray.append(59, 92);
        sparseIntArray.append(101, 6);
        sparseIntArray.append(102, 7);
        sparseIntArray.append(70, 17);
        sparseIntArray.append(71, 18);
        sparseIntArray.append(72, 19);
        sparseIntArray.append(54, 99);
        sparseIntArray.append(0, 27);
        sparseIntArray.append(87, 32);
        sparseIntArray.append(88, 33);
        sparseIntArray.append(69, 10);
        sparseIntArray.append(68, 9);
        sparseIntArray.append(106, 13);
        sparseIntArray.append(109, 16);
        sparseIntArray.append(107, 14);
        sparseIntArray.append(104, 11);
        sparseIntArray.append(108, 15);
        sparseIntArray.append(105, 12);
        sparseIntArray.append(95, 40);
        sparseIntArray.append(80, 39);
        sparseIntArray.append(79, 41);
        sparseIntArray.append(94, 42);
        sparseIntArray.append(78, 20);
        sparseIntArray.append(93, 37);
        sparseIntArray.append(67, 5);
        sparseIntArray.append(81, 87);
        sparseIntArray.append(90, 87);
        sparseIntArray.append(84, 87);
        sparseIntArray.append(61, 87);
        sparseIntArray.append(57, 87);
        sparseIntArray.append(5, 24);
        sparseIntArray.append(7, 28);
        sparseIntArray.append(23, 31);
        sparseIntArray.append(24, 8);
        sparseIntArray.append(6, 34);
        sparseIntArray.append(8, 2);
        sparseIntArray.append(3, 23);
        sparseIntArray.append(4, 21);
        sparseIntArray.append(96, 95);
        sparseIntArray.append(73, 96);
        sparseIntArray.append(2, 22);
        sparseIntArray.append(13, 43);
        sparseIntArray.append(26, 44);
        sparseIntArray.append(21, 45);
        sparseIntArray.append(22, 46);
        sparseIntArray.append(20, 60);
        sparseIntArray.append(18, 47);
        sparseIntArray.append(19, 48);
        sparseIntArray.append(14, 49);
        sparseIntArray.append(15, 50);
        sparseIntArray.append(16, 51);
        sparseIntArray.append(17, 52);
        sparseIntArray.append(25, 53);
        sparseIntArray.append(97, 54);
        sparseIntArray.append(74, 55);
        sparseIntArray.append(98, 56);
        sparseIntArray.append(75, 57);
        sparseIntArray.append(99, 58);
        sparseIntArray.append(76, 59);
        sparseIntArray.append(64, 61);
        sparseIntArray.append(66, 62);
        sparseIntArray.append(65, 63);
        sparseIntArray.append(28, 64);
        sparseIntArray.append(121, 65);
        sparseIntArray.append(35, 66);
        sparseIntArray.append(122, 67);
        sparseIntArray.append(113, 79);
        sparseIntArray.append(1, 38);
        sparseIntArray.append(112, 68);
        sparseIntArray.append(100, 69);
        sparseIntArray.append(77, 70);
        sparseIntArray.append(111, 97);
        sparseIntArray.append(32, 71);
        sparseIntArray.append(30, 72);
        sparseIntArray.append(31, 73);
        sparseIntArray.append(33, 74);
        sparseIntArray.append(29, 75);
        sparseIntArray.append(114, 76);
        sparseIntArray.append(89, 77);
        sparseIntArray.append(123, 78);
        sparseIntArray.append(56, 80);
        sparseIntArray.append(55, 81);
        sparseIntArray.append(116, 82);
        sparseIntArray.append(120, 83);
        sparseIntArray.append(119, 84);
        sparseIntArray.append(118, 85);
        sparseIntArray.append(117, 86);
        sparseIntArray2.append(85, 6);
        sparseIntArray2.append(85, 7);
        sparseIntArray2.append(0, 27);
        sparseIntArray2.append(89, 13);
        sparseIntArray2.append(92, 16);
        sparseIntArray2.append(90, 14);
        sparseIntArray2.append(87, 11);
        sparseIntArray2.append(91, 15);
        sparseIntArray2.append(88, 12);
        sparseIntArray2.append(78, 40);
        sparseIntArray2.append(71, 39);
        sparseIntArray2.append(70, 41);
        sparseIntArray2.append(77, 42);
        sparseIntArray2.append(69, 20);
        sparseIntArray2.append(76, 37);
        sparseIntArray2.append(60, 5);
        sparseIntArray2.append(72, 87);
        sparseIntArray2.append(75, 87);
        sparseIntArray2.append(73, 87);
        sparseIntArray2.append(57, 87);
        sparseIntArray2.append(56, 87);
        sparseIntArray2.append(5, 24);
        sparseIntArray2.append(7, 28);
        sparseIntArray2.append(23, 31);
        sparseIntArray2.append(24, 8);
        sparseIntArray2.append(6, 34);
        sparseIntArray2.append(8, 2);
        sparseIntArray2.append(3, 23);
        sparseIntArray2.append(4, 21);
        sparseIntArray2.append(79, 95);
        sparseIntArray2.append(64, 96);
        sparseIntArray2.append(2, 22);
        sparseIntArray2.append(13, 43);
        sparseIntArray2.append(26, 44);
        sparseIntArray2.append(21, 45);
        sparseIntArray2.append(22, 46);
        sparseIntArray2.append(20, 60);
        sparseIntArray2.append(18, 47);
        sparseIntArray2.append(19, 48);
        sparseIntArray2.append(14, 49);
        sparseIntArray2.append(15, 50);
        sparseIntArray2.append(16, 51);
        sparseIntArray2.append(17, 52);
        sparseIntArray2.append(25, 53);
        sparseIntArray2.append(80, 54);
        sparseIntArray2.append(65, 55);
        sparseIntArray2.append(81, 56);
        sparseIntArray2.append(66, 57);
        sparseIntArray2.append(82, 58);
        sparseIntArray2.append(67, 59);
        sparseIntArray2.append(59, 62);
        sparseIntArray2.append(58, 63);
        sparseIntArray2.append(28, 64);
        sparseIntArray2.append(105, 65);
        sparseIntArray2.append(34, 66);
        sparseIntArray2.append(106, 67);
        sparseIntArray2.append(96, 79);
        sparseIntArray2.append(1, 38);
        sparseIntArray2.append(97, 98);
        sparseIntArray2.append(95, 68);
        sparseIntArray2.append(83, 69);
        sparseIntArray2.append(68, 70);
        sparseIntArray2.append(32, 71);
        sparseIntArray2.append(30, 72);
        sparseIntArray2.append(31, 73);
        sparseIntArray2.append(33, 74);
        sparseIntArray2.append(29, 75);
        sparseIntArray2.append(98, 76);
        sparseIntArray2.append(74, 77);
        sparseIntArray2.append(107, 78);
        sparseIntArray2.append(55, 80);
        sparseIntArray2.append(54, 81);
        sparseIntArray2.append(100, 82);
        sparseIntArray2.append(104, 83);
        sparseIntArray2.append(103, 84);
        sparseIntArray2.append(102, 85);
        sparseIntArray2.append(101, 86);
        sparseIntArray2.append(94, 97);
    }

    public static int[] b(C0251a c0251a, String str) {
        int i2;
        String[] split = str.split(",");
        Context context = c0251a.getContext();
        int[] iArr = new int[split.length];
        int i3 = 0;
        int i4 = 0;
        while (i3 < split.length) {
            String trim = split[i3].trim();
            Object obj = null;
            try {
                i2 = q.class.getField(trim).getInt(null);
            } catch (Exception unused) {
                i2 = 0;
            }
            if (i2 == 0) {
                i2 = context.getResources().getIdentifier(trim, "id", context.getPackageName());
            }
            if (i2 == 0 && c0251a.isInEditMode() && (c0251a.getParent() instanceof ConstraintLayout)) {
                ConstraintLayout constraintLayout = (ConstraintLayout) c0251a.getParent();
                if (trim != null) {
                    HashMap hashMap = constraintLayout.f965m;
                    if (hashMap != null && hashMap.containsKey(trim)) {
                        obj = constraintLayout.f965m.get(trim);
                    }
                } else {
                    constraintLayout.getClass();
                }
                if (obj != null && (obj instanceof Integer)) {
                    i2 = ((Integer) obj).intValue();
                }
            }
            iArr[i4] = i2;
            i3++;
            i4++;
        }
        if (i4 != split.length) {
            return Arrays.copyOf(iArr, i4);
        }
        return iArr;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x0088. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:128:0x0600. Please report as an issue. */
    /* JADX WARN: Type inference failed for: r3v120, types: [v.h, java.lang.Object] */
    public static i c(Context context, AttributeSet attributeSet, boolean z2) {
        int i2;
        String str;
        String str2;
        String str3;
        int i3;
        String str4;
        int i4;
        i iVar = new i();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, z2 ? r.f2799c : r.f2797a);
        int[] iArr = f2790d;
        SparseIntArray sparseIntArray = f2791e;
        l lVar = iVar.f2695b;
        String[] strArr = AbstractC0233a.f2231a;
        m mVar = iVar.f2698e;
        k kVar = iVar.f2696c;
        j jVar = iVar.f2697d;
        String str5 = "Unknown attribute 0x";
        String str6 = "ConstraintSet";
        if (z2) {
            int indexCount = obtainStyledAttributes.getIndexCount();
            ?? obj = new Object();
            obj.f2683a = new int[10];
            obj.f2684b = new int[10];
            obj.f2685c = 0;
            obj.f2686d = new int[10];
            obj.f2687e = new float[10];
            obj.f2688f = 0;
            obj.f2689g = new int[5];
            obj.h = new String[5];
            obj.f2690i = 0;
            obj.f2691j = new int[4];
            obj.f2692k = new boolean[4];
            obj.f2693l = 0;
            kVar.getClass();
            jVar.getClass();
            mVar.getClass();
            int i5 = 0;
            while (i5 < indexCount) {
                int index = obtainStyledAttributes.getIndex(i5);
                int i6 = indexCount;
                switch (f2792f.get(index)) {
                    case 2:
                        str4 = str5;
                        obj.b(2, obtainStyledAttributes.getDimensionPixelSize(index, jVar.f2709I));
                        i4 = 1;
                        break;
                    case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                    case 4:
                    case 9:
                    case 10:
                    case 25:
                    case 26:
                    case 29:
                    case 30:
                    case 32:
                    case 33:
                    case 35:
                    case 36:
                    case 61:
                    case 88:
                    case 89:
                    case 90:
                    case 91:
                    case 92:
                    default:
                        StringBuilder sb = new StringBuilder(str5);
                        str4 = str5;
                        sb.append(Integer.toHexString(index));
                        sb.append("   ");
                        sb.append(sparseIntArray.get(index));
                        Log.w("ConstraintSet", sb.toString());
                        i4 = 1;
                        break;
                    case 5:
                        str4 = str5;
                        obj.d(obtainStyledAttributes.getString(index), 5);
                        i4 = 1;
                        break;
                    case 6:
                        str4 = str5;
                        obj.b(6, obtainStyledAttributes.getDimensionPixelOffset(index, jVar.f2703C));
                        i4 = 1;
                        break;
                    case 7:
                        str4 = str5;
                        obj.b(7, obtainStyledAttributes.getDimensionPixelOffset(index, jVar.f2704D));
                        i4 = 1;
                        break;
                    case CTConstants.TIMESTAMP_LENGTH /* 8 */:
                        str4 = str5;
                        obj.b(8, obtainStyledAttributes.getDimensionPixelSize(index, jVar.f2710J));
                        i4 = 1;
                        break;
                    case 11:
                        str4 = str5;
                        obj.b(11, obtainStyledAttributes.getDimensionPixelSize(index, jVar.f2716P));
                        i4 = 1;
                        break;
                    case FileClientSessionCache.MAX_SIZE /* 12 */:
                        str4 = str5;
                        obj.b(12, obtainStyledAttributes.getDimensionPixelSize(index, jVar.f2717Q));
                        i4 = 1;
                        break;
                    case 13:
                        str4 = str5;
                        obj.b(13, obtainStyledAttributes.getDimensionPixelSize(index, jVar.f2713M));
                        i4 = 1;
                        break;
                    case 14:
                        str4 = str5;
                        obj.b(14, obtainStyledAttributes.getDimensionPixelSize(index, jVar.f2715O));
                        i4 = 1;
                        break;
                    case 15:
                        str4 = str5;
                        obj.b(15, obtainStyledAttributes.getDimensionPixelSize(index, jVar.f2718R));
                        i4 = 1;
                        break;
                    case 16:
                        str4 = str5;
                        obj.b(16, obtainStyledAttributes.getDimensionPixelSize(index, jVar.f2714N));
                        i4 = 1;
                        break;
                    case 17:
                        str4 = str5;
                        obj.b(17, obtainStyledAttributes.getDimensionPixelOffset(index, jVar.f2733d));
                        i4 = 1;
                        break;
                    case 18:
                        str4 = str5;
                        obj.b(18, obtainStyledAttributes.getDimensionPixelOffset(index, jVar.f2735e));
                        i4 = 1;
                        break;
                    case 19:
                        str4 = str5;
                        obj.a(19, obtainStyledAttributes.getFloat(index, jVar.f2737f));
                        i4 = 1;
                        break;
                    case 20:
                        str4 = str5;
                        obj.a(20, obtainStyledAttributes.getFloat(index, jVar.f2761w));
                        i4 = 1;
                        break;
                    case 21:
                        str4 = str5;
                        obj.b(21, obtainStyledAttributes.getLayoutDimension(index, jVar.f2731c));
                        i4 = 1;
                        break;
                    case 22:
                        str4 = str5;
                        obj.b(22, iArr[obtainStyledAttributes.getInt(index, lVar.f2773a)]);
                        i4 = 1;
                        break;
                    case 23:
                        str4 = str5;
                        obj.b(23, obtainStyledAttributes.getLayoutDimension(index, jVar.f2729b));
                        i4 = 1;
                        break;
                    case 24:
                        str4 = str5;
                        obj.b(24, obtainStyledAttributes.getDimensionPixelSize(index, jVar.f2706F));
                        i4 = 1;
                        break;
                    case 27:
                        str4 = str5;
                        obj.b(27, obtainStyledAttributes.getInt(index, jVar.f2705E));
                        i4 = 1;
                        break;
                    case 28:
                        str4 = str5;
                        obj.b(28, obtainStyledAttributes.getDimensionPixelSize(index, jVar.f2707G));
                        i4 = 1;
                        break;
                    case 31:
                        str4 = str5;
                        obj.b(31, obtainStyledAttributes.getDimensionPixelSize(index, jVar.f2711K));
                        i4 = 1;
                        break;
                    case 34:
                        str4 = str5;
                        obj.b(34, obtainStyledAttributes.getDimensionPixelSize(index, jVar.f2708H));
                        i4 = 1;
                        break;
                    case 37:
                        str4 = str5;
                        obj.a(37, obtainStyledAttributes.getFloat(index, jVar.f2762x));
                        i4 = 1;
                        break;
                    case 38:
                        str4 = str5;
                        int resourceId = obtainStyledAttributes.getResourceId(index, iVar.f2694a);
                        iVar.f2694a = resourceId;
                        obj.b(38, resourceId);
                        i4 = 1;
                        break;
                    case 39:
                        str4 = str5;
                        obj.a(39, obtainStyledAttributes.getFloat(index, jVar.f2721U));
                        i4 = 1;
                        break;
                    case 40:
                        str4 = str5;
                        obj.a(40, obtainStyledAttributes.getFloat(index, jVar.f2720T));
                        i4 = 1;
                        break;
                    case 41:
                        str4 = str5;
                        obj.b(41, obtainStyledAttributes.getInt(index, jVar.f2722V));
                        i4 = 1;
                        break;
                    case 42:
                        str4 = str5;
                        obj.b(42, obtainStyledAttributes.getInt(index, jVar.f2723W));
                        i4 = 1;
                        break;
                    case 43:
                        str4 = str5;
                        obj.a(43, obtainStyledAttributes.getFloat(index, lVar.f2775c));
                        i4 = 1;
                        break;
                    case 44:
                        str4 = str5;
                        obj.c(44, true);
                        obj.a(44, obtainStyledAttributes.getDimension(index, mVar.f2789m));
                        i4 = 1;
                        break;
                    case 45:
                        str4 = str5;
                        obj.a(45, obtainStyledAttributes.getFloat(index, mVar.f2779b));
                        i4 = 1;
                        break;
                    case 46:
                        str4 = str5;
                        obj.a(46, obtainStyledAttributes.getFloat(index, mVar.f2780c));
                        i4 = 1;
                        break;
                    case 47:
                        str4 = str5;
                        obj.a(47, obtainStyledAttributes.getFloat(index, mVar.f2781d));
                        i4 = 1;
                        break;
                    case 48:
                        str4 = str5;
                        obj.a(48, obtainStyledAttributes.getFloat(index, mVar.f2782e));
                        i4 = 1;
                        break;
                    case 49:
                        str4 = str5;
                        obj.a(49, obtainStyledAttributes.getDimension(index, mVar.f2783f));
                        i4 = 1;
                        break;
                    case 50:
                        str4 = str5;
                        obj.a(50, obtainStyledAttributes.getDimension(index, mVar.f2784g));
                        i4 = 1;
                        break;
                    case 51:
                        str4 = str5;
                        obj.a(51, obtainStyledAttributes.getDimension(index, mVar.f2785i));
                        i4 = 1;
                        break;
                    case 52:
                        str4 = str5;
                        obj.a(52, obtainStyledAttributes.getDimension(index, mVar.f2786j));
                        i4 = 1;
                        break;
                    case 53:
                        str4 = str5;
                        obj.a(53, obtainStyledAttributes.getDimension(index, mVar.f2787k));
                        i4 = 1;
                        break;
                    case 54:
                        str4 = str5;
                        obj.b(54, obtainStyledAttributes.getInt(index, jVar.f2724X));
                        i4 = 1;
                        break;
                    case 55:
                        str4 = str5;
                        obj.b(55, obtainStyledAttributes.getInt(index, jVar.f2725Y));
                        i4 = 1;
                        break;
                    case 56:
                        str4 = str5;
                        obj.b(56, obtainStyledAttributes.getDimensionPixelSize(index, jVar.f2726Z));
                        i4 = 1;
                        break;
                    case 57:
                        str4 = str5;
                        obj.b(57, obtainStyledAttributes.getDimensionPixelSize(index, jVar.f2728a0));
                        i4 = 1;
                        break;
                    case 58:
                        str4 = str5;
                        obj.b(58, obtainStyledAttributes.getDimensionPixelSize(index, jVar.f2730b0));
                        i4 = 1;
                        break;
                    case 59:
                        str4 = str5;
                        obj.b(59, obtainStyledAttributes.getDimensionPixelSize(index, jVar.f2732c0));
                        i4 = 1;
                        break;
                    case 60:
                        str4 = str5;
                        obj.a(60, obtainStyledAttributes.getFloat(index, mVar.f2778a));
                        i4 = 1;
                        break;
                    case 62:
                        str4 = str5;
                        obj.b(62, obtainStyledAttributes.getDimensionPixelSize(index, jVar.f2701A));
                        i4 = 1;
                        break;
                    case 63:
                        str4 = str5;
                        obj.a(63, obtainStyledAttributes.getFloat(index, jVar.f2702B));
                        i4 = 1;
                        break;
                    case 64:
                        str4 = str5;
                        obj.b(64, e(obtainStyledAttributes, index, kVar.f2765a));
                        i4 = 1;
                        break;
                    case 65:
                        str4 = str5;
                        if (obtainStyledAttributes.peekValue(index).type == 3) {
                            obj.d(obtainStyledAttributes.getString(index), 65);
                        } else {
                            obj.d(strArr[obtainStyledAttributes.getInteger(index, 0)], 65);
                        }
                        i4 = 1;
                        break;
                    case 66:
                        str4 = str5;
                        obj.b(66, obtainStyledAttributes.getInt(index, 0));
                        i4 = 1;
                        break;
                    case 67:
                        str4 = str5;
                        obj.a(67, obtainStyledAttributes.getFloat(index, kVar.f2769e));
                        i4 = 1;
                        break;
                    case 68:
                        str4 = str5;
                        obj.a(68, obtainStyledAttributes.getFloat(index, lVar.f2776d));
                        i4 = 1;
                        break;
                    case 69:
                        str4 = str5;
                        obj.a(69, obtainStyledAttributes.getFloat(index, 1.0f));
                        i4 = 1;
                        break;
                    case 70:
                        str4 = str5;
                        obj.a(70, obtainStyledAttributes.getFloat(index, 1.0f));
                        i4 = 1;
                        break;
                    case 71:
                        str4 = str5;
                        Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                        i4 = 1;
                        break;
                    case 72:
                        str4 = str5;
                        obj.b(72, obtainStyledAttributes.getInt(index, jVar.f2738f0));
                        i4 = 1;
                        break;
                    case 73:
                        str4 = str5;
                        obj.b(73, obtainStyledAttributes.getDimensionPixelSize(index, jVar.g0));
                        i4 = 1;
                        break;
                    case 74:
                        str4 = str5;
                        obj.d(obtainStyledAttributes.getString(index), 74);
                        i4 = 1;
                        break;
                    case 75:
                        str4 = str5;
                        obj.c(75, obtainStyledAttributes.getBoolean(index, jVar.f2751n0));
                        i4 = 1;
                        break;
                    case 76:
                        str4 = str5;
                        obj.b(76, obtainStyledAttributes.getInt(index, kVar.f2767c));
                        i4 = 1;
                        break;
                    case 77:
                        str4 = str5;
                        obj.d(obtainStyledAttributes.getString(index), 77);
                        i4 = 1;
                        break;
                    case 78:
                        str4 = str5;
                        obj.b(78, obtainStyledAttributes.getInt(index, lVar.f2774b));
                        i4 = 1;
                        break;
                    case 79:
                        str4 = str5;
                        obj.a(79, obtainStyledAttributes.getFloat(index, kVar.f2768d));
                        i4 = 1;
                        break;
                    case 80:
                        str4 = str5;
                        obj.c(80, obtainStyledAttributes.getBoolean(index, jVar.f2747l0));
                        i4 = 1;
                        break;
                    case 81:
                        str4 = str5;
                        obj.c(81, obtainStyledAttributes.getBoolean(index, jVar.f2749m0));
                        i4 = 1;
                        break;
                    case 82:
                        str4 = str5;
                        obj.b(82, obtainStyledAttributes.getInteger(index, kVar.f2766b));
                        i4 = 1;
                        break;
                    case 83:
                        str4 = str5;
                        obj.b(83, e(obtainStyledAttributes, index, mVar.h));
                        i4 = 1;
                        break;
                    case 84:
                        str4 = str5;
                        obj.b(84, obtainStyledAttributes.getInteger(index, kVar.f2771g));
                        i4 = 1;
                        break;
                    case 85:
                        str4 = str5;
                        obj.a(85, obtainStyledAttributes.getFloat(index, kVar.f2770f));
                        i4 = 1;
                        break;
                    case 86:
                        str4 = str5;
                        int i7 = obtainStyledAttributes.peekValue(index).type;
                        if (i7 == 1) {
                            int resourceId2 = obtainStyledAttributes.getResourceId(index, -1);
                            kVar.f2772i = resourceId2;
                            obj.b(89, resourceId2);
                            if (kVar.f2772i != -1) {
                                obj.b(88, -2);
                            }
                        } else if (i7 == 3) {
                            String string = obtainStyledAttributes.getString(index);
                            kVar.h = string;
                            obj.d(string, 90);
                            if (kVar.h.indexOf("/") > 0) {
                                int resourceId3 = obtainStyledAttributes.getResourceId(index, -1);
                                kVar.f2772i = resourceId3;
                                obj.b(89, resourceId3);
                                obj.b(88, -2);
                            } else {
                                obj.b(88, -1);
                            }
                        } else {
                            obj.b(88, obtainStyledAttributes.getInteger(index, kVar.f2772i));
                        }
                        i4 = 1;
                        break;
                    case 87:
                        str4 = str5;
                        Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray.get(index));
                        i4 = 1;
                        break;
                    case 93:
                        str4 = str5;
                        obj.b(93, obtainStyledAttributes.getDimensionPixelSize(index, jVar.f2712L));
                        i4 = 1;
                        break;
                    case 94:
                        str4 = str5;
                        obj.b(94, obtainStyledAttributes.getDimensionPixelSize(index, jVar.f2719S));
                        i4 = 1;
                        break;
                    case 95:
                        str4 = str5;
                        f(obj, obtainStyledAttributes, index, 0);
                        i4 = 1;
                        break;
                    case 96:
                        str4 = str5;
                        i4 = 1;
                        f(obj, obtainStyledAttributes, index, 1);
                        break;
                    case 97:
                        str4 = str5;
                        obj.b(97, obtainStyledAttributes.getInt(index, jVar.f2753o0));
                        i4 = 1;
                        break;
                    case 98:
                        str4 = str5;
                        int i8 = AbstractC0250a.f2404s;
                        if (obtainStyledAttributes.peekValue(index).type == 3) {
                            obtainStyledAttributes.getString(index);
                        } else {
                            iVar.f2694a = obtainStyledAttributes.getResourceId(index, iVar.f2694a);
                        }
                        i4 = 1;
                        break;
                    case 99:
                        str4 = str5;
                        obj.c(99, obtainStyledAttributes.getBoolean(index, jVar.f2739g));
                        i4 = 1;
                        break;
                }
                i5 += i4;
                indexCount = i6;
                str5 = str4;
            }
        } else {
            String str7 = "CURRENTLY UNSUPPORTED";
            String str8 = "Unknown attribute 0x";
            int i9 = 1;
            int indexCount2 = obtainStyledAttributes.getIndexCount();
            int i10 = 0;
            while (i10 < indexCount2) {
                int index2 = obtainStyledAttributes.getIndex(i10);
                if (index2 != i9 && 23 != index2 && 24 != index2) {
                    kVar.getClass();
                    jVar.getClass();
                    mVar.getClass();
                }
                switch (sparseIntArray.get(index2)) {
                    case 1:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2754p = e(obtainStyledAttributes, index2, jVar.f2754p);
                        i3 = 1;
                        break;
                    case 2:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2709I = obtainStyledAttributes.getDimensionPixelSize(index2, jVar.f2709I);
                        i3 = 1;
                        break;
                    case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2752o = e(obtainStyledAttributes, index2, jVar.f2752o);
                        i3 = 1;
                        break;
                    case 4:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2750n = e(obtainStyledAttributes, index2, jVar.f2750n);
                        i3 = 1;
                        break;
                    case 5:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.y = obtainStyledAttributes.getString(index2);
                        i3 = 1;
                        break;
                    case 6:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2703C = obtainStyledAttributes.getDimensionPixelOffset(index2, jVar.f2703C);
                        i3 = 1;
                        break;
                    case 7:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2704D = obtainStyledAttributes.getDimensionPixelOffset(index2, jVar.f2704D);
                        i3 = 1;
                        break;
                    case CTConstants.TIMESTAMP_LENGTH /* 8 */:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2710J = obtainStyledAttributes.getDimensionPixelSize(index2, jVar.f2710J);
                        i3 = 1;
                        break;
                    case 9:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2760v = e(obtainStyledAttributes, index2, jVar.f2760v);
                        i3 = 1;
                        break;
                    case 10:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2759u = e(obtainStyledAttributes, index2, jVar.f2759u);
                        i3 = 1;
                        break;
                    case 11:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2716P = obtainStyledAttributes.getDimensionPixelSize(index2, jVar.f2716P);
                        i3 = 1;
                        break;
                    case FileClientSessionCache.MAX_SIZE /* 12 */:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2717Q = obtainStyledAttributes.getDimensionPixelSize(index2, jVar.f2717Q);
                        i3 = 1;
                        break;
                    case 13:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2713M = obtainStyledAttributes.getDimensionPixelSize(index2, jVar.f2713M);
                        i3 = 1;
                        break;
                    case 14:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2715O = obtainStyledAttributes.getDimensionPixelSize(index2, jVar.f2715O);
                        i3 = 1;
                        break;
                    case 15:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2718R = obtainStyledAttributes.getDimensionPixelSize(index2, jVar.f2718R);
                        i3 = 1;
                        break;
                    case 16:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2714N = obtainStyledAttributes.getDimensionPixelSize(index2, jVar.f2714N);
                        i3 = 1;
                        break;
                    case 17:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2733d = obtainStyledAttributes.getDimensionPixelOffset(index2, jVar.f2733d);
                        i3 = 1;
                        break;
                    case 18:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2735e = obtainStyledAttributes.getDimensionPixelOffset(index2, jVar.f2735e);
                        i3 = 1;
                        break;
                    case 19:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2737f = obtainStyledAttributes.getFloat(index2, jVar.f2737f);
                        i3 = 1;
                        break;
                    case 20:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2761w = obtainStyledAttributes.getFloat(index2, jVar.f2761w);
                        i3 = 1;
                        break;
                    case 21:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2731c = obtainStyledAttributes.getLayoutDimension(index2, jVar.f2731c);
                        i3 = 1;
                        break;
                    case 22:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        int i11 = obtainStyledAttributes.getInt(index2, lVar.f2773a);
                        lVar.f2773a = i11;
                        lVar.f2773a = iArr[i11];
                        i3 = 1;
                        break;
                    case 23:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2729b = obtainStyledAttributes.getLayoutDimension(index2, jVar.f2729b);
                        i3 = 1;
                        break;
                    case 24:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2706F = obtainStyledAttributes.getDimensionPixelSize(index2, jVar.f2706F);
                        i3 = 1;
                        break;
                    case 25:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.h = e(obtainStyledAttributes, index2, jVar.h);
                        i3 = 1;
                        break;
                    case 26:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2740i = e(obtainStyledAttributes, index2, jVar.f2740i);
                        i3 = 1;
                        break;
                    case 27:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2705E = obtainStyledAttributes.getInt(index2, jVar.f2705E);
                        i3 = 1;
                        break;
                    case 28:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2707G = obtainStyledAttributes.getDimensionPixelSize(index2, jVar.f2707G);
                        i3 = 1;
                        break;
                    case 29:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2742j = e(obtainStyledAttributes, index2, jVar.f2742j);
                        i3 = 1;
                        break;
                    case 30:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2744k = e(obtainStyledAttributes, index2, jVar.f2744k);
                        i3 = 1;
                        break;
                    case 31:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2711K = obtainStyledAttributes.getDimensionPixelSize(index2, jVar.f2711K);
                        i3 = 1;
                        break;
                    case 32:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2757s = e(obtainStyledAttributes, index2, jVar.f2757s);
                        i3 = 1;
                        break;
                    case 33:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2758t = e(obtainStyledAttributes, index2, jVar.f2758t);
                        i3 = 1;
                        break;
                    case 34:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2708H = obtainStyledAttributes.getDimensionPixelSize(index2, jVar.f2708H);
                        i3 = 1;
                        break;
                    case 35:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2748m = e(obtainStyledAttributes, index2, jVar.f2748m);
                        i3 = 1;
                        break;
                    case 36:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2746l = e(obtainStyledAttributes, index2, jVar.f2746l);
                        i3 = 1;
                        break;
                    case 37:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2762x = obtainStyledAttributes.getFloat(index2, jVar.f2762x);
                        i3 = 1;
                        break;
                    case 38:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        iVar.f2694a = obtainStyledAttributes.getResourceId(index2, iVar.f2694a);
                        i3 = 1;
                        break;
                    case 39:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2721U = obtainStyledAttributes.getFloat(index2, jVar.f2721U);
                        i3 = 1;
                        break;
                    case 40:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2720T = obtainStyledAttributes.getFloat(index2, jVar.f2720T);
                        i3 = 1;
                        break;
                    case 41:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2722V = obtainStyledAttributes.getInt(index2, jVar.f2722V);
                        i3 = 1;
                        break;
                    case 42:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2723W = obtainStyledAttributes.getInt(index2, jVar.f2723W);
                        i3 = 1;
                        break;
                    case 43:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        lVar.f2775c = obtainStyledAttributes.getFloat(index2, lVar.f2775c);
                        i3 = 1;
                        break;
                    case 44:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        mVar.f2788l = true;
                        mVar.f2789m = obtainStyledAttributes.getDimension(index2, mVar.f2789m);
                        i3 = 1;
                        break;
                    case 45:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        mVar.f2779b = obtainStyledAttributes.getFloat(index2, mVar.f2779b);
                        i3 = 1;
                        break;
                    case 46:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        mVar.f2780c = obtainStyledAttributes.getFloat(index2, mVar.f2780c);
                        i3 = 1;
                        break;
                    case 47:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        mVar.f2781d = obtainStyledAttributes.getFloat(index2, mVar.f2781d);
                        i3 = 1;
                        break;
                    case 48:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        mVar.f2782e = obtainStyledAttributes.getFloat(index2, mVar.f2782e);
                        i3 = 1;
                        break;
                    case 49:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        mVar.f2783f = obtainStyledAttributes.getDimension(index2, mVar.f2783f);
                        i3 = 1;
                        break;
                    case 50:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        mVar.f2784g = obtainStyledAttributes.getDimension(index2, mVar.f2784g);
                        i3 = 1;
                        break;
                    case 51:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        mVar.f2785i = obtainStyledAttributes.getDimension(index2, mVar.f2785i);
                        i3 = 1;
                        break;
                    case 52:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        mVar.f2786j = obtainStyledAttributes.getDimension(index2, mVar.f2786j);
                        i3 = 1;
                        break;
                    case 53:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        mVar.f2787k = obtainStyledAttributes.getDimension(index2, mVar.f2787k);
                        i3 = 1;
                        break;
                    case 54:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2724X = obtainStyledAttributes.getInt(index2, jVar.f2724X);
                        i3 = 1;
                        break;
                    case 55:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2725Y = obtainStyledAttributes.getInt(index2, jVar.f2725Y);
                        i3 = 1;
                        break;
                    case 56:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2726Z = obtainStyledAttributes.getDimensionPixelSize(index2, jVar.f2726Z);
                        i3 = 1;
                        break;
                    case 57:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2728a0 = obtainStyledAttributes.getDimensionPixelSize(index2, jVar.f2728a0);
                        i3 = 1;
                        break;
                    case 58:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2730b0 = obtainStyledAttributes.getDimensionPixelSize(index2, jVar.f2730b0);
                        i3 = 1;
                        break;
                    case 59:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2732c0 = obtainStyledAttributes.getDimensionPixelSize(index2, jVar.f2732c0);
                        i3 = 1;
                        break;
                    case 60:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        mVar.f2778a = obtainStyledAttributes.getFloat(index2, mVar.f2778a);
                        i3 = 1;
                        break;
                    case 61:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2763z = e(obtainStyledAttributes, index2, jVar.f2763z);
                        i3 = 1;
                        break;
                    case 62:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2701A = obtainStyledAttributes.getDimensionPixelSize(index2, jVar.f2701A);
                        i3 = 1;
                        break;
                    case 63:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        jVar.f2702B = obtainStyledAttributes.getFloat(index2, jVar.f2702B);
                        i3 = 1;
                        break;
                    case 64:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        kVar.f2765a = e(obtainStyledAttributes, index2, kVar.f2765a);
                        i3 = 1;
                        break;
                    case 65:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        if (obtainStyledAttributes.peekValue(index2).type == 3) {
                            obtainStyledAttributes.getString(index2);
                            kVar.getClass();
                            i3 = 1;
                            break;
                        } else {
                            String str9 = strArr[obtainStyledAttributes.getInteger(index2, 0)];
                            kVar.getClass();
                            i3 = 1;
                            break;
                        }
                    case 66:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        obtainStyledAttributes.getInt(index2, 0);
                        kVar.getClass();
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 67:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        kVar.f2769e = obtainStyledAttributes.getFloat(index2, kVar.f2769e);
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 68:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        lVar.f2776d = obtainStyledAttributes.getFloat(index2, lVar.f2776d);
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 69:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        jVar.f2734d0 = obtainStyledAttributes.getFloat(index2, 1.0f);
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 70:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        jVar.f2736e0 = obtainStyledAttributes.getFloat(index2, 1.0f);
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 71:
                        i2 = indexCount2;
                        str = str7;
                        str2 = str8;
                        Log.e(str6, str);
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 72:
                        i2 = indexCount2;
                        str2 = str8;
                        jVar.f2738f0 = obtainStyledAttributes.getInt(index2, jVar.f2738f0);
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 73:
                        i2 = indexCount2;
                        str2 = str8;
                        jVar.g0 = obtainStyledAttributes.getDimensionPixelSize(index2, jVar.g0);
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 74:
                        i2 = indexCount2;
                        str2 = str8;
                        jVar.f2743j0 = obtainStyledAttributes.getString(index2);
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 75:
                        i2 = indexCount2;
                        str2 = str8;
                        jVar.f2751n0 = obtainStyledAttributes.getBoolean(index2, jVar.f2751n0);
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 76:
                        i2 = indexCount2;
                        str2 = str8;
                        kVar.f2767c = obtainStyledAttributes.getInt(index2, kVar.f2767c);
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 77:
                        i2 = indexCount2;
                        str2 = str8;
                        jVar.f2745k0 = obtainStyledAttributes.getString(index2);
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 78:
                        i2 = indexCount2;
                        str2 = str8;
                        lVar.f2774b = obtainStyledAttributes.getInt(index2, lVar.f2774b);
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 79:
                        i2 = indexCount2;
                        str2 = str8;
                        kVar.f2768d = obtainStyledAttributes.getFloat(index2, kVar.f2768d);
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 80:
                        i2 = indexCount2;
                        str2 = str8;
                        jVar.f2747l0 = obtainStyledAttributes.getBoolean(index2, jVar.f2747l0);
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 81:
                        i2 = indexCount2;
                        str2 = str8;
                        jVar.f2749m0 = obtainStyledAttributes.getBoolean(index2, jVar.f2749m0);
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 82:
                        i2 = indexCount2;
                        str2 = str8;
                        kVar.f2766b = obtainStyledAttributes.getInteger(index2, kVar.f2766b);
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 83:
                        i2 = indexCount2;
                        str2 = str8;
                        mVar.h = e(obtainStyledAttributes, index2, mVar.h);
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 84:
                        i2 = indexCount2;
                        str2 = str8;
                        kVar.f2771g = obtainStyledAttributes.getInteger(index2, kVar.f2771g);
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 85:
                        i2 = indexCount2;
                        str2 = str8;
                        kVar.f2770f = obtainStyledAttributes.getFloat(index2, kVar.f2770f);
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 86:
                        i2 = indexCount2;
                        str2 = str8;
                        int i12 = obtainStyledAttributes.peekValue(index2).type;
                        if (i12 == 1) {
                            kVar.f2772i = obtainStyledAttributes.getResourceId(index2, -1);
                        } else if (i12 == 3) {
                            String string2 = obtainStyledAttributes.getString(index2);
                            kVar.h = string2;
                            if (string2.indexOf("/") > 0) {
                                kVar.f2772i = obtainStyledAttributes.getResourceId(index2, -1);
                            }
                        } else {
                            obtainStyledAttributes.getInteger(index2, kVar.f2772i);
                        }
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 87:
                        i2 = indexCount2;
                        str2 = str8;
                        Log.w(str6, "unused attribute 0x" + Integer.toHexString(index2) + "   " + sparseIntArray.get(index2));
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 88:
                    case 89:
                    case 90:
                    default:
                        str2 = str8;
                        StringBuilder sb2 = new StringBuilder(str2);
                        i2 = indexCount2;
                        sb2.append(Integer.toHexString(index2));
                        sb2.append("   ");
                        sb2.append(sparseIntArray.get(index2));
                        Log.w(str6, sb2.toString());
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 91:
                        i2 = indexCount2;
                        str2 = str8;
                        jVar.f2755q = e(obtainStyledAttributes, index2, jVar.f2755q);
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 92:
                        i2 = indexCount2;
                        str2 = str8;
                        jVar.f2756r = e(obtainStyledAttributes, index2, jVar.f2756r);
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 93:
                        i2 = indexCount2;
                        str2 = str8;
                        jVar.f2712L = obtainStyledAttributes.getDimensionPixelSize(index2, jVar.f2712L);
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 94:
                        i2 = indexCount2;
                        str2 = str8;
                        jVar.f2719S = obtainStyledAttributes.getDimensionPixelSize(index2, jVar.f2719S);
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 95:
                        i2 = indexCount2;
                        str2 = str8;
                        f(jVar, obtainStyledAttributes, index2, 0);
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 96:
                        i2 = indexCount2;
                        str2 = str8;
                        f(jVar, obtainStyledAttributes, index2, 1);
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                    case 97:
                        i2 = indexCount2;
                        str2 = str8;
                        jVar.f2753o0 = obtainStyledAttributes.getInt(index2, jVar.f2753o0);
                        str = str7;
                        str3 = str6;
                        i3 = 1;
                        break;
                }
                i10 += i3;
                indexCount2 = i2;
                str8 = str2;
                String str10 = str;
                i9 = i3;
                str6 = str3;
                str7 = str10;
            }
            if (jVar.f2743j0 != null) {
                jVar.f2741i0 = null;
            }
        }
        obtainStyledAttributes.recycle();
        return iVar;
    }

    public static int e(TypedArray typedArray, int i2, int i3) {
        int resourceId = typedArray.getResourceId(i2, i3);
        if (resourceId == -1) {
            return typedArray.getInt(i2, -1);
        }
        return resourceId;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void f(java.lang.Object r7, android.content.res.TypedArray r8, int r9, int r10) {
        /*
            Method dump skipped, instructions count: 376
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: v.n.f(java.lang.Object, android.content.res.TypedArray, int, int):void");
    }

    public static void g(e eVar, String str) {
        if (str != null) {
            int length = str.length();
            int indexOf = str.indexOf(44);
            int i2 = 0;
            int i3 = -1;
            if (indexOf > 0 && indexOf < length - 1) {
                String substring = str.substring(0, indexOf);
                if (!substring.equalsIgnoreCase("W")) {
                    if (substring.equalsIgnoreCase("H")) {
                        i2 = 1;
                    } else {
                        i2 = -1;
                    }
                }
                i3 = i2;
                i2 = indexOf + 1;
            }
            int indexOf2 = str.indexOf(58);
            try {
                if (indexOf2 >= 0 && indexOf2 < length - 1) {
                    String substring2 = str.substring(i2, indexOf2);
                    String substring3 = str.substring(indexOf2 + 1);
                    if (substring2.length() > 0 && substring3.length() > 0) {
                        float parseFloat = Float.parseFloat(substring2);
                        float parseFloat2 = Float.parseFloat(substring3);
                        if (parseFloat > 0.0f && parseFloat2 > 0.0f) {
                            if (i3 == 1) {
                                Math.abs(parseFloat2 / parseFloat);
                            } else {
                                Math.abs(parseFloat / parseFloat2);
                            }
                        }
                    }
                } else {
                    String substring4 = str.substring(i2);
                    if (substring4.length() > 0) {
                        Float.parseFloat(substring4);
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
        eVar.f2613G = str;
    }

    public final void a(Context context, int i2) {
        n nVar = this;
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(context).inflate(i2, (ViewGroup) null);
        int childCount = constraintLayout.getChildCount();
        HashMap hashMap = nVar.f2795c;
        hashMap.clear();
        int i3 = 0;
        while (i3 < childCount) {
            View childAt = constraintLayout.getChildAt(i3);
            e eVar = (e) childAt.getLayoutParams();
            int id = childAt.getId();
            if (nVar.f2794b && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!hashMap.containsKey(Integer.valueOf(id))) {
                hashMap.put(Integer.valueOf(id), new i());
            }
            i iVar = (i) hashMap.get(Integer.valueOf(id));
            if (iVar != null) {
                HashMap hashMap2 = nVar.f2793a;
                HashMap hashMap3 = new HashMap();
                Class<?> cls = childAt.getClass();
                for (String str : hashMap2.keySet()) {
                    C0252b c0252b = (C0252b) hashMap2.get(str);
                    try {
                        if (str.equals("BackgroundColor")) {
                            hashMap3.put(str, new C0252b(c0252b, Integer.valueOf(((ColorDrawable) childAt.getBackground()).getColor())));
                        } else {
                            hashMap3.put(str, new C0252b(c0252b, cls.getMethod("getMap" + str, null).invoke(childAt, null)));
                        }
                    } catch (IllegalAccessException e2) {
                        e2.printStackTrace();
                    } catch (NoSuchMethodException e3) {
                        e3.printStackTrace();
                    } catch (InvocationTargetException e4) {
                        e4.printStackTrace();
                    }
                }
                iVar.f2699f = hashMap3;
                iVar.f2694a = id;
                int i4 = eVar.f2641e;
                j jVar = iVar.f2697d;
                jVar.h = i4;
                jVar.f2740i = eVar.f2643f;
                jVar.f2742j = eVar.f2645g;
                jVar.f2744k = eVar.h;
                jVar.f2746l = eVar.f2646i;
                jVar.f2748m = eVar.f2648j;
                jVar.f2750n = eVar.f2650k;
                jVar.f2752o = eVar.f2652l;
                jVar.f2754p = eVar.f2654m;
                jVar.f2755q = eVar.f2656n;
                jVar.f2756r = eVar.f2658o;
                jVar.f2757s = eVar.f2664s;
                jVar.f2758t = eVar.f2665t;
                jVar.f2759u = eVar.f2666u;
                jVar.f2760v = eVar.f2667v;
                jVar.f2761w = eVar.f2611E;
                jVar.f2762x = eVar.f2612F;
                jVar.y = eVar.f2613G;
                jVar.f2763z = eVar.f2660p;
                jVar.f2701A = eVar.f2662q;
                jVar.f2702B = eVar.f2663r;
                jVar.f2703C = eVar.f2626T;
                jVar.f2704D = eVar.f2627U;
                jVar.f2705E = eVar.f2628V;
                jVar.f2737f = eVar.f2637c;
                jVar.f2733d = eVar.f2633a;
                jVar.f2735e = eVar.f2635b;
                jVar.f2729b = ((ViewGroup.MarginLayoutParams) eVar).width;
                jVar.f2731c = ((ViewGroup.MarginLayoutParams) eVar).height;
                jVar.f2706F = ((ViewGroup.MarginLayoutParams) eVar).leftMargin;
                jVar.f2707G = ((ViewGroup.MarginLayoutParams) eVar).rightMargin;
                jVar.f2708H = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
                jVar.f2709I = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
                jVar.f2712L = eVar.f2610D;
                jVar.f2720T = eVar.f2615I;
                jVar.f2721U = eVar.f2614H;
                jVar.f2723W = eVar.f2617K;
                jVar.f2722V = eVar.f2616J;
                jVar.f2747l0 = eVar.f2629W;
                jVar.f2749m0 = eVar.f2630X;
                jVar.f2724X = eVar.f2618L;
                jVar.f2725Y = eVar.f2619M;
                jVar.f2726Z = eVar.f2622P;
                jVar.f2728a0 = eVar.f2623Q;
                jVar.f2730b0 = eVar.f2620N;
                jVar.f2732c0 = eVar.f2621O;
                jVar.f2734d0 = eVar.f2624R;
                jVar.f2736e0 = eVar.f2625S;
                jVar.f2745k0 = eVar.f2631Y;
                jVar.f2714N = eVar.f2669x;
                jVar.f2716P = eVar.f2670z;
                jVar.f2713M = eVar.f2668w;
                jVar.f2715O = eVar.y;
                jVar.f2718R = eVar.f2607A;
                jVar.f2717Q = eVar.f2608B;
                jVar.f2719S = eVar.f2609C;
                jVar.f2753o0 = eVar.f2632Z;
                jVar.f2710J = eVar.getMarginEnd();
                jVar.f2711K = eVar.getMarginStart();
                int visibility = childAt.getVisibility();
                l lVar = iVar.f2695b;
                lVar.f2773a = visibility;
                lVar.f2775c = childAt.getAlpha();
                float rotation = childAt.getRotation();
                m mVar = iVar.f2698e;
                mVar.f2778a = rotation;
                mVar.f2779b = childAt.getRotationX();
                mVar.f2780c = childAt.getRotationY();
                mVar.f2781d = childAt.getScaleX();
                mVar.f2782e = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (pivotX != 0.0d || pivotY != 0.0d) {
                    mVar.f2783f = pivotX;
                    mVar.f2784g = pivotY;
                }
                mVar.f2785i = childAt.getTranslationX();
                mVar.f2786j = childAt.getTranslationY();
                mVar.f2787k = childAt.getTranslationZ();
                if (mVar.f2788l) {
                    mVar.f2789m = childAt.getElevation();
                }
                if (childAt instanceof C0251a) {
                    C0251a c0251a = (C0251a) childAt;
                    jVar.f2751n0 = c0251a.getAllowsGoneWidget();
                    jVar.f2741i0 = c0251a.getReferencedIds();
                    jVar.f2738f0 = c0251a.getType();
                    jVar.g0 = c0251a.getMargin();
                }
            }
            i3++;
            nVar = this;
        }
    }

    public final void d(Context context, int i2) {
        XmlResourceParser xml = context.getResources().getXml(i2);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType != 0) {
                    if (eventType == 2) {
                        String name = xml.getName();
                        i c2 = c(context, Xml.asAttributeSet(xml), false);
                        if (name.equalsIgnoreCase("Guideline")) {
                            c2.f2697d.f2727a = true;
                        }
                        this.f2795c.put(Integer.valueOf(c2.f2694a), c2);
                    }
                } else {
                    xml.getName();
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }
}
