package androidx.core.graphics.drawable;

import B.a;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.lang.reflect.InvocationTargetException;
import org.conscrypt.ct.CTConstants;

/* loaded from: classes.dex */
public class IconCompat extends CustomVersionedParcelable {

    /* renamed from: k, reason: collision with root package name */
    public static final PorterDuff.Mode f976k = PorterDuff.Mode.SRC_IN;

    /* renamed from: b, reason: collision with root package name */
    public Object f978b;

    /* renamed from: j, reason: collision with root package name */
    public String f985j;

    /* renamed from: a, reason: collision with root package name */
    public int f977a = -1;

    /* renamed from: c, reason: collision with root package name */
    public byte[] f979c = null;

    /* renamed from: d, reason: collision with root package name */
    public Parcelable f980d = null;

    /* renamed from: e, reason: collision with root package name */
    public int f981e = 0;

    /* renamed from: f, reason: collision with root package name */
    public int f982f = 0;

    /* renamed from: g, reason: collision with root package name */
    public ColorStateList f983g = null;
    public PorterDuff.Mode h = f976k;

    /* renamed from: i, reason: collision with root package name */
    public String f984i = null;

    public final String toString() {
        String str;
        int i2;
        if (this.f977a == -1) {
            return String.valueOf(this.f978b);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        switch (this.f977a) {
            case 1:
                str = "BITMAP";
                break;
            case 2:
                str = "RESOURCE";
                break;
            case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                str = "DATA";
                break;
            case 4:
                str = "URI";
                break;
            case 5:
                str = "BITMAP_MASKABLE";
                break;
            case 6:
                str = "URI_MASKABLE";
                break;
            default:
                str = "UNKNOWN";
                break;
        }
        sb.append(str);
        switch (this.f977a) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.f978b).getWidth());
                sb.append("x");
                sb.append(((Bitmap) this.f978b).getHeight());
                break;
            case 2:
                sb.append(" pkg=");
                sb.append(this.f985j);
                sb.append(" id=");
                int i3 = this.f977a;
                if (i3 == -1) {
                    int i4 = Build.VERSION.SDK_INT;
                    Object obj = this.f978b;
                    if (i4 >= 28) {
                        i2 = a.b(obj);
                    } else {
                        i2 = 0;
                        try {
                            i2 = ((Integer) obj.getClass().getMethod("getResId", null).invoke(obj, null)).intValue();
                        } catch (IllegalAccessException e2) {
                            Log.e("IconCompat", "Unable to get icon resource", e2);
                        } catch (NoSuchMethodException e3) {
                            Log.e("IconCompat", "Unable to get icon resource", e3);
                        } catch (InvocationTargetException e4) {
                            Log.e("IconCompat", "Unable to get icon resource", e4);
                        }
                    }
                } else if (i3 == 2) {
                    i2 = this.f981e;
                } else {
                    throw new IllegalStateException("called getResId() on " + this);
                }
                sb.append(String.format("0x%08x", Integer.valueOf(i2)));
                break;
            case CTConstants.CERTIFICATE_LENGTH_BYTES /* 3 */:
                sb.append(" len=");
                sb.append(this.f981e);
                if (this.f982f != 0) {
                    sb.append(" off=");
                    sb.append(this.f982f);
                    break;
                }
                break;
            case 4:
            case 6:
                sb.append(" uri=");
                sb.append(this.f978b);
                break;
        }
        if (this.f983g != null) {
            sb.append(" tint=");
            sb.append(this.f983g);
        }
        if (this.h != f976k) {
            sb.append(" mode=");
            sb.append(this.h);
        }
        sb.append(")");
        return sb.toString();
    }
}
