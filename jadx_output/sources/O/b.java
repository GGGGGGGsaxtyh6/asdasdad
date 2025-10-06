package O;

import A.m;
import android.os.Bundle;
import android.text.Editable;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class b extends InputConnectionWrapper {

    /* renamed from: a, reason: collision with root package name */
    public final EditText f590a;

    /* renamed from: b, reason: collision with root package name */
    public final m f591b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(EditText editText, InputConnection inputConnection, EditorInfo editorInfo) {
        super(inputConnection, false);
        int i2;
        m mVar = new m(9);
        this.f590a = editText;
        this.f591b = mVar;
        if (M.j.f533k != null) {
            M.j a2 = M.j.a();
            if (a2.b() == 1 && editorInfo != null) {
                if (editorInfo.extras == null) {
                    editorInfo.extras = new Bundle();
                }
                M.f fVar = a2.f538e;
                fVar.getClass();
                Bundle bundle = editorInfo.extras;
                N.b bVar = (N.b) fVar.f529c.f570a;
                int a3 = bVar.a(4);
                if (a3 != 0) {
                    i2 = ((ByteBuffer) bVar.f319d).getInt(a3 + bVar.f316a);
                } else {
                    i2 = 0;
                }
                bundle.putInt("android.support.text.emoji.emojiCompat_metadataVersion", i2);
                Bundle bundle2 = editorInfo.extras;
                fVar.f527a.getClass();
                bundle2.putBoolean("android.support.text.emoji.emojiCompat_replaceAll", false);
            }
        }
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingText(int i2, int i3) {
        Editable editableText = this.f590a.getEditableText();
        this.f591b.getClass();
        if (!m.g(this, editableText, i2, i3, false) && !super.deleteSurroundingText(i2, i3)) {
            return false;
        }
        return true;
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingTextInCodePoints(int i2, int i3) {
        Editable editableText = this.f590a.getEditableText();
        this.f591b.getClass();
        if (m.g(this, editableText, i2, i3, true) || super.deleteSurroundingTextInCodePoints(i2, i3)) {
            return true;
        }
        return false;
    }
}
