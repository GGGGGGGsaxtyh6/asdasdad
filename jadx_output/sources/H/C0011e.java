package H;

import android.content.ClipData;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContentInfo;
import java.util.Locale;
import org.conscrypt.BuildConfig;

/* renamed from: H.e, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0011e implements InterfaceC0010d, InterfaceC0012f {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f357a = 0;

    /* renamed from: b, reason: collision with root package name */
    public ClipData f358b;

    /* renamed from: c, reason: collision with root package name */
    public int f359c;

    /* renamed from: d, reason: collision with root package name */
    public int f360d;

    /* renamed from: e, reason: collision with root package name */
    public Uri f361e;

    /* renamed from: f, reason: collision with root package name */
    public Bundle f362f;

    public /* synthetic */ C0011e() {
    }

    @Override // H.InterfaceC0010d
    public void b(Bundle bundle) {
        this.f362f = bundle;
    }

    @Override // H.InterfaceC0010d
    public void e(Uri uri) {
        this.f361e = uri;
    }

    @Override // H.InterfaceC0012f
    public int g() {
        return this.f359c;
    }

    @Override // H.InterfaceC0012f
    public ClipData j() {
        return this.f358b;
    }

    @Override // H.InterfaceC0010d
    public C0013g o() {
        return new C0013g(new C0011e(this));
    }

    @Override // H.InterfaceC0012f
    public int q() {
        return this.f360d;
    }

    public String toString() {
        String str;
        String valueOf;
        String str2;
        switch (this.f357a) {
            case 1:
                StringBuilder sb = new StringBuilder("ContentInfoCompat{clip=");
                sb.append(this.f358b.getDescription());
                sb.append(", source=");
                int i2 = this.f359c;
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (i2 != 2) {
                            if (i2 != 3) {
                                if (i2 != 4) {
                                    if (i2 != 5) {
                                        str = String.valueOf(i2);
                                    } else {
                                        str = "SOURCE_PROCESS_TEXT";
                                    }
                                } else {
                                    str = "SOURCE_AUTOFILL";
                                }
                            } else {
                                str = "SOURCE_DRAG_AND_DROP";
                            }
                        } else {
                            str = "SOURCE_INPUT_METHOD";
                        }
                    } else {
                        str = "SOURCE_CLIPBOARD";
                    }
                } else {
                    str = "SOURCE_APP";
                }
                sb.append(str);
                sb.append(", flags=");
                int i3 = this.f360d;
                if ((i3 & 1) != 0) {
                    valueOf = "FLAG_CONVERT_TO_PLAIN_TEXT";
                } else {
                    valueOf = String.valueOf(i3);
                }
                sb.append(valueOf);
                String str3 = BuildConfig.FLAVOR;
                Uri uri = this.f361e;
                if (uri == null) {
                    str2 = BuildConfig.FLAVOR;
                } else {
                    str2 = ", hasLinkUri(" + uri.toString().length() + ")";
                }
                sb.append(str2);
                if (this.f362f != null) {
                    str3 = ", hasExtras";
                }
                sb.append(str3);
                sb.append("}");
                return sb.toString();
            default:
                return super.toString();
        }
    }

    @Override // H.InterfaceC0012f
    public ContentInfo v() {
        return null;
    }

    @Override // H.InterfaceC0010d
    public void y(int i2) {
        this.f360d = i2;
    }

    public C0011e(C0011e c0011e) {
        ClipData clipData = c0011e.f358b;
        clipData.getClass();
        this.f358b = clipData;
        int i2 = c0011e.f359c;
        if (i2 < 0) {
            Locale locale = Locale.US;
            throw new IllegalArgumentException("source is out of range of [0, 5] (too low)");
        }
        if (i2 <= 5) {
            this.f359c = i2;
            int i3 = c0011e.f360d;
            if ((i3 & 1) == i3) {
                this.f360d = i3;
                this.f361e = c0011e.f361e;
                this.f362f = c0011e.f362f;
                return;
            } else {
                throw new IllegalArgumentException("Requested flags 0x" + Integer.toHexString(i3) + ", but only 0x" + Integer.toHexString(1) + " are allowed");
            }
        }
        Locale locale2 = Locale.US;
        throw new IllegalArgumentException("source is out of range of [0, 5] (too high)");
    }
}
