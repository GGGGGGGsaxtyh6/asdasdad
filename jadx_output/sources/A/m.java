package A;

import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;
import g.InterfaceC0104b;
import j.n;
import j.y;
import j0.AbstractC0150d;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import org.conscrypt.BuildConfig;
import org.conscrypt.ct.CTConstants;

/* loaded from: classes.dex */
public class m implements T.e, InterfaceC0104b, y {

    /* renamed from: b, reason: collision with root package name */
    public static m f26b;

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f27a;

    public /* synthetic */ m(int i2) {
        this.f27a = i2;
    }

    public static final String b(byte[] bArr, byte[][] bArr2, int i2) {
        int i3;
        boolean z2;
        int i4;
        int i5;
        int i6 = -1;
        byte[] bArr3 = PublicSuffixDatabase.f2141e;
        int length = bArr.length;
        int i7 = 0;
        while (i7 < length) {
            int i8 = (i7 + length) / 2;
            while (i8 > i6 && bArr[i8] != 10) {
                i8 += i6;
            }
            int i9 = i8 + 1;
            int i10 = 1;
            while (true) {
                i3 = i9 + i10;
                if (bArr[i3] == 10) {
                    break;
                }
                i10++;
            }
            int i11 = i3 - i9;
            int i12 = i2;
            boolean z3 = false;
            int i13 = 0;
            int i14 = 0;
            while (true) {
                if (z3) {
                    i4 = 46;
                    z2 = false;
                } else {
                    byte b2 = bArr2[i12][i13];
                    byte[] bArr4 = v0.b.f2808a;
                    int i15 = b2 & 255;
                    z2 = z3;
                    i4 = i15;
                }
                byte b3 = bArr[i9 + i14];
                byte[] bArr5 = v0.b.f2808a;
                i5 = i4 - (b3 & 255);
                if (i5 != 0) {
                    break;
                }
                i14++;
                i13++;
                if (i14 == i11) {
                    break;
                }
                if (bArr2[i12].length == i13) {
                    if (i12 == bArr2.length - 1) {
                        break;
                    }
                    i12++;
                    z3 = true;
                    i13 = -1;
                } else {
                    z3 = z2;
                }
            }
            if (i5 >= 0) {
                if (i5 <= 0) {
                    int i16 = i11 - i14;
                    int length2 = bArr2[i12].length - i13;
                    int length3 = bArr2.length;
                    for (int i17 = i12 + 1; i17 < length3; i17++) {
                        length2 += bArr2[i17].length;
                    }
                    if (length2 >= i16) {
                        if (length2 <= i16) {
                            Charset charset = StandardCharsets.UTF_8;
                            AbstractC0150d.d(charset, "UTF_8");
                            return new String(bArr, i9, i11, charset);
                        }
                    }
                }
                i7 = i3 + 1;
                i6 = -1;
            }
            length = i8;
            i6 = -1;
        }
        return null;
    }

    public static H0.h d(String str) {
        if (str.length() % 2 == 0) {
            int length = str.length() / 2;
            byte[] bArr = new byte[length];
            int i2 = length - 1;
            if (i2 >= 0) {
                int i3 = 0;
                while (true) {
                    int i4 = i3 + 1;
                    int i5 = i3 * 2;
                    bArr[i3] = (byte) (I0.b.a(str.charAt(i5 + 1)) + (I0.b.a(str.charAt(i5)) << 4));
                    if (i4 > i2) {
                        break;
                    }
                    i3 = i4;
                }
            }
            return new H0.h(bArr);
        }
        throw new IllegalArgumentException(AbstractC0150d.h(str, "Unexpected hex string: ").toString());
    }

    public static H0.h e(String str) {
        AbstractC0150d.e(str, "<this>");
        byte[] bytes = str.getBytes(q0.a.f2219a);
        AbstractC0150d.d(bytes, "(this as java.lang.String).getBytes(charset)");
        H0.h hVar = new H0.h(bytes);
        hVar.f416c = str;
        return hVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0045, code lost:
    
        if (java.lang.Character.isHighSurrogate(r5) != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0082, code lost:
    
        if (java.lang.Character.isLowSurrogate(r5) != false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0075, code lost:
    
        if (r11 != false) goto L46;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean g(O.b r7, android.text.Editable r8, int r9, int r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: A.m.g(O.b, android.text.Editable, int, int, boolean):boolean");
    }

    @Override // j.y
    public boolean c(n nVar) {
        return false;
    }

    public Object clone() {
        switch (this.f27a) {
            case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                return this;
            default:
                return super.clone();
        }
    }

    public boolean equals(Object obj) {
        switch (this.f27a) {
            case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                return obj == null || obj == this;
            default:
                return super.equals(obj);
        }
    }

    public Signature[] f(PackageManager packageManager, String str) {
        return packageManager.getPackageInfo(str, 64).signatures;
    }

    public boolean h(CharSequence charSequence) {
        return false;
    }

    public int hashCode() {
        switch (this.f27a) {
            case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                return 0;
            default:
                return super.hashCode();
        }
    }

    @Override // T.e
    public void p(int i2, Serializable serializable) {
        String str;
        switch (this.f27a) {
            case 11:
                return;
            default:
                switch (i2) {
                    case 1:
                        str = "RESULT_INSTALL_SUCCESS";
                        break;
                    case 2:
                        str = "RESULT_ALREADY_INSTALLED";
                        break;
                    case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                        str = "RESULT_UNSUPPORTED_ART_VERSION";
                        break;
                    case 4:
                        str = "RESULT_NOT_WRITABLE";
                        break;
                    case 5:
                        str = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                        break;
                    case 6:
                        str = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                        break;
                    case 7:
                        str = "RESULT_IO_EXCEPTION";
                        break;
                    case CTConstants.TIMESTAMP_LENGTH /* 8 */:
                        str = "RESULT_PARSE_EXCEPTION";
                        break;
                    case 9:
                    default:
                        str = BuildConfig.FLAVOR;
                        break;
                    case 10:
                        str = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                        break;
                    case 11:
                        str = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                        break;
                }
                if (i2 != 6 && i2 != 7 && i2 != 8) {
                    Log.d("ProfileInstaller", str);
                    return;
                } else {
                    Log.e("ProfileInstaller", str, (Throwable) serializable);
                    return;
                }
        }
    }

    @Override // T.e
    public void t() {
        switch (this.f27a) {
            case 11:
                return;
            default:
                Log.d("ProfileInstaller", "DIAGNOSTIC_PROFILE_IS_COMPRESSED");
                return;
        }
    }

    public String toString() {
        switch (this.f27a) {
            case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                return "null";
            case 23:
                return "<NULL>";
            default:
                return super.toString();
        }
    }

    private final void i() {
    }

    private final void j(int i2, Serializable serializable) {
    }

    @Override // j.y
    public void a(n nVar, boolean z2) {
    }
}
