package E;

import java.util.List;
import java.util.Objects;

/* loaded from: classes.dex */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    public String f273a;

    /* renamed from: b, reason: collision with root package name */
    public String f274b;

    /* renamed from: c, reason: collision with root package name */
    public List f275c;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        if (Objects.equals(this.f273a, eVar.f273a) && Objects.equals(this.f274b, eVar.f274b) && Objects.equals(this.f275c, eVar.f275c)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.f273a, this.f274b, this.f275c);
    }
}
