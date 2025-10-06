package Y;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import n.f;

/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    public final f f679a;

    /* renamed from: b, reason: collision with root package name */
    public final f f680b;

    /* renamed from: c, reason: collision with root package name */
    public final f f681c;

    public a(f fVar, f fVar2, f fVar3) {
        this.f679a = fVar;
        this.f680b = fVar2;
        this.f681c = fVar3;
    }

    public abstract b a();

    public final Class b(Class cls) {
        String name = cls.getName();
        f fVar = this.f681c;
        Class cls2 = (Class) fVar.get(name);
        if (cls2 == null) {
            Class<?> cls3 = Class.forName(cls.getPackage().getName() + "." + cls.getSimpleName() + "Parcelizer", false, cls.getClassLoader());
            fVar.put(cls.getName(), cls3);
            return cls3;
        }
        return cls2;
    }

    public final Method c(String str) {
        f fVar = this.f679a;
        Method method = (Method) fVar.get(str);
        if (method == null) {
            System.currentTimeMillis();
            Method declaredMethod = Class.forName(str, true, a.class.getClassLoader()).getDeclaredMethod("read", a.class);
            fVar.put(str, declaredMethod);
            return declaredMethod;
        }
        return method;
    }

    public final Method d(Class cls) {
        String name = cls.getName();
        f fVar = this.f680b;
        Method method = (Method) fVar.get(name);
        if (method == null) {
            Class b2 = b(cls);
            System.currentTimeMillis();
            Method declaredMethod = b2.getDeclaredMethod("write", cls, a.class);
            fVar.put(cls.getName(), declaredMethod);
            return declaredMethod;
        }
        return method;
    }

    public abstract boolean e(int i2);

    public final Parcelable f(Parcelable parcelable, int i2) {
        if (!e(i2)) {
            return parcelable;
        }
        return ((b) this).f683e.readParcelable(b.class.getClassLoader());
    }

    public final c g() {
        String readString = ((b) this).f683e.readString();
        if (readString == null) {
            return null;
        }
        try {
            return (c) c(readString).invoke(null, a());
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e2);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e4);
        } catch (InvocationTargetException e5) {
            if (e5.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e5.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e5);
        }
    }

    public abstract void h(int i2);

    public final void i(c cVar) {
        if (cVar == null) {
            ((b) this).f683e.writeString(null);
            return;
        }
        try {
            ((b) this).f683e.writeString(b(cVar.getClass()).getName());
            b a2 = a();
            try {
                d(cVar.getClass()).invoke(null, cVar, a2);
                int i2 = a2.f686i;
                if (i2 >= 0) {
                    int i3 = a2.f682d.get(i2);
                    Parcel parcel = a2.f683e;
                    int dataPosition = parcel.dataPosition();
                    parcel.setDataPosition(i3);
                    parcel.writeInt(dataPosition - i3);
                    parcel.setDataPosition(dataPosition);
                }
            } catch (ClassNotFoundException e2) {
                throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e2);
            } catch (IllegalAccessException e3) {
                throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e3);
            } catch (NoSuchMethodException e4) {
                throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e4);
            } catch (InvocationTargetException e5) {
                if (e5.getCause() instanceof RuntimeException) {
                    throw ((RuntimeException) e5.getCause());
                }
                throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e5);
            }
        } catch (ClassNotFoundException e6) {
            throw new RuntimeException(cVar.getClass().getSimpleName().concat(" does not have a Parcelizer"), e6);
        }
    }
}
