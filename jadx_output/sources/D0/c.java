package D0;

import android.util.Log;
import j0.AbstractC0150d;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/* loaded from: classes.dex */
public final class c extends Handler {

    /* renamed from: a, reason: collision with root package name */
    public static final c f247a = new Handler();

    @Override // java.util.logging.Handler
    public final void publish(LogRecord logRecord) {
        int i2;
        int min;
        AbstractC0150d.e(logRecord, "record");
        CopyOnWriteArraySet copyOnWriteArraySet = b.f245a;
        String loggerName = logRecord.getLoggerName();
        AbstractC0150d.d(loggerName, "record.loggerName");
        int intValue = logRecord.getLevel().intValue();
        Level level = Level.INFO;
        if (intValue > level.intValue()) {
            i2 = 5;
        } else if (logRecord.getLevel().intValue() == level.intValue()) {
            i2 = 4;
        } else {
            i2 = 3;
        }
        String message = logRecord.getMessage();
        AbstractC0150d.d(message, "record.message");
        Throwable thrown = logRecord.getThrown();
        String str = (String) b.f246b.get(loggerName);
        if (str == null) {
            str = q0.d.O(loggerName, 23);
        }
        if (Log.isLoggable(str, i2)) {
            if (thrown != null) {
                message = message + '\n' + ((Object) Log.getStackTraceString(thrown));
            }
            int length = message.length();
            int i3 = 0;
            while (i3 < length) {
                int G2 = q0.d.G(message, '\n', i3, 4);
                if (G2 == -1) {
                    G2 = length;
                }
                while (true) {
                    min = Math.min(G2, i3 + 4000);
                    String substring = message.substring(i3, min);
                    AbstractC0150d.d(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                    Log.println(i2, str, substring);
                    if (min >= G2) {
                        break;
                    } else {
                        i3 = min;
                    }
                }
                i3 = min + 1;
            }
        }
    }

    @Override // java.util.logging.Handler
    public final void close() {
    }

    @Override // java.util.logging.Handler
    public final void flush() {
    }
}
