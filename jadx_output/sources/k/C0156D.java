package k;

import android.R;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AbsSeekBar;
import android.widget.EditText;
import f.AbstractC0101a;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: k.D, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0156D {

    /* renamed from: d, reason: collision with root package name */
    public static final int[] f1750d = {R.attr.indeterminateDrawable, R.attr.progressDrawable};

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1751a = 2;

    /* renamed from: b, reason: collision with root package name */
    public View f1752b;

    /* renamed from: c, reason: collision with root package name */
    public Object f1753c;

    public /* synthetic */ C0156D() {
    }

    public KeyListener a(KeyListener keyListener) {
        if (!(keyListener instanceof NumberKeyListener)) {
            ((E.c) ((A.f) this.f1753c).f8b).getClass();
            if (!(keyListener instanceof O.e)) {
                if (keyListener == null) {
                    return null;
                }
                if (!(keyListener instanceof NumberKeyListener)) {
                    return new O.e(keyListener);
                }
                return keyListener;
            }
            return keyListener;
        }
        return keyListener;
    }

    public void b(AttributeSet attributeSet, int i2) {
        switch (this.f1751a) {
            case 0:
                AbsSeekBar absSeekBar = (AbsSeekBar) this.f1752b;
                D0.h p2 = D0.h.p(absSeekBar.getContext(), attributeSet, f1750d, i2);
                Drawable k2 = p2.k(0);
                if (k2 != null) {
                    if (k2 instanceof AnimationDrawable) {
                        AnimationDrawable animationDrawable = (AnimationDrawable) k2;
                        int numberOfFrames = animationDrawable.getNumberOfFrames();
                        AnimationDrawable animationDrawable2 = new AnimationDrawable();
                        animationDrawable2.setOneShot(animationDrawable.isOneShot());
                        for (int i3 = 0; i3 < numberOfFrames; i3++) {
                            Drawable e2 = e(animationDrawable.getFrame(i3), true);
                            e2.setLevel(10000);
                            animationDrawable2.addFrame(e2, animationDrawable.getDuration(i3));
                        }
                        animationDrawable2.setLevel(10000);
                        k2 = animationDrawable2;
                    }
                    absSeekBar.setIndeterminateDrawable(k2);
                }
                Drawable k3 = p2.k(1);
                if (k3 != null) {
                    absSeekBar.setProgressDrawable(e(k3, false));
                }
                p2.r();
                return;
            default:
                TypedArray obtainStyledAttributes = ((EditText) this.f1752b).getContext().obtainStyledAttributes(attributeSet, AbstractC0101a.f1269i, i2, 0);
                try {
                    boolean z2 = true;
                    if (obtainStyledAttributes.hasValue(14)) {
                        z2 = obtainStyledAttributes.getBoolean(14, true);
                    }
                    obtainStyledAttributes.recycle();
                    d(z2);
                    return;
                } catch (Throwable th) {
                    obtainStyledAttributes.recycle();
                    throw th;
                }
        }
    }

    public O.b c(InputConnection inputConnection, EditorInfo editorInfo) {
        A.f fVar = (A.f) this.f1753c;
        if (inputConnection == null) {
            fVar.getClass();
            inputConnection = null;
        } else {
            E.c cVar = (E.c) fVar.f8b;
            cVar.getClass();
            if (!(inputConnection instanceof O.b)) {
                inputConnection = new O.b((EditText) cVar.f271b, inputConnection, editorInfo);
            }
        }
        return (O.b) inputConnection;
    }

    public void d(boolean z2) {
        O.i iVar = (O.i) ((E.c) ((A.f) this.f1753c).f8b).f272c;
        if (iVar.f605c != z2) {
            if (iVar.f604b != null) {
                M.j a2 = M.j.a();
                O.h hVar = iVar.f604b;
                a2.getClass();
                C0.d.j(hVar, "initCallback cannot be null");
                ReentrantReadWriteLock reentrantReadWriteLock = a2.f534a;
                reentrantReadWriteLock.writeLock().lock();
                try {
                    a2.f535b.remove(hVar);
                } finally {
                    reentrantReadWriteLock.writeLock().unlock();
                }
            }
            iVar.f605c = z2;
            if (z2) {
                O.i.a(iVar.f603a, M.j.a().b());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Drawable e(Drawable drawable, boolean z2) {
        boolean z3;
        if (drawable instanceof B.b) {
            ((B.c) ((B.b) drawable)).getClass();
        } else {
            if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                Drawable[] drawableArr = new Drawable[numberOfLayers];
                for (int i2 = 0; i2 < numberOfLayers; i2++) {
                    int id = layerDrawable.getId(i2);
                    Drawable drawable2 = layerDrawable.getDrawable(i2);
                    if (id != 16908301 && id != 16908303) {
                        z3 = false;
                    } else {
                        z3 = true;
                    }
                    drawableArr[i2] = e(drawable2, z3);
                }
                LayerDrawable layerDrawable2 = new LayerDrawable(drawableArr);
                for (int i3 = 0; i3 < numberOfLayers; i3++) {
                    layerDrawable2.setId(i3, layerDrawable.getId(i3));
                    layerDrawable2.setLayerGravity(i3, layerDrawable.getLayerGravity(i3));
                    layerDrawable2.setLayerWidth(i3, layerDrawable.getLayerWidth(i3));
                    layerDrawable2.setLayerHeight(i3, layerDrawable.getLayerHeight(i3));
                    layerDrawable2.setLayerInsetLeft(i3, layerDrawable.getLayerInsetLeft(i3));
                    layerDrawable2.setLayerInsetRight(i3, layerDrawable.getLayerInsetRight(i3));
                    layerDrawable2.setLayerInsetTop(i3, layerDrawable.getLayerInsetTop(i3));
                    layerDrawable2.setLayerInsetBottom(i3, layerDrawable.getLayerInsetBottom(i3));
                    layerDrawable2.setLayerInsetStart(i3, layerDrawable.getLayerInsetStart(i3));
                    layerDrawable2.setLayerInsetEnd(i3, layerDrawable.getLayerInsetEnd(i3));
                }
                return layerDrawable2;
            }
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap bitmap = bitmapDrawable.getBitmap();
                if (((Bitmap) this.f1753c) == null) {
                    this.f1753c = bitmap;
                }
                ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null));
                shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
                shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
                if (z2) {
                    return new ClipDrawable(shapeDrawable, 3, 1);
                }
                return shapeDrawable;
            }
        }
        return drawable;
    }

    public C0156D(AbsSeekBar absSeekBar) {
        this.f1752b = absSeekBar;
    }

    public C0156D(EditText editText) {
        this.f1752b = editText;
        this.f1753c = new A.f(editText);
    }
}
