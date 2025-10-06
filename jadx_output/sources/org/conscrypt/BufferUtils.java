package org.conscrypt;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
final class BufferUtils {
    private BufferUtils() {
    }

    public static void checkNotNull(ByteBuffer[] byteBufferArr) {
        for (ByteBuffer byteBuffer : byteBufferArr) {
            if (byteBuffer == null) {
                throw new IllegalArgumentException("Null buffer in array");
            }
        }
    }

    public static void consume(ByteBuffer[] byteBufferArr, int i2) {
        for (ByteBuffer byteBuffer : byteBufferArr) {
            int min = Math.min(byteBuffer.remaining(), i2);
            if (min > 0) {
                byteBuffer.position(byteBuffer.position() + min);
                i2 -= min;
                if (i2 == 0) {
                    break;
                }
            }
        }
        if (i2 > 0) {
            throw new IllegalArgumentException("toConsume > data size");
        }
    }

    public static ByteBuffer copyNoConsume(ByteBuffer[] byteBufferArr, ByteBuffer byteBuffer, int i2) {
        boolean z2;
        if (byteBuffer.remaining() >= i2) {
            z2 = true;
        } else {
            z2 = false;
        }
        Preconditions.checkArgument(z2, "Destination buffer too small");
        for (ByteBuffer byteBuffer2 : byteBufferArr) {
            int remaining = byteBuffer2.remaining();
            if (remaining > 0) {
                int position = byteBuffer2.position();
                if (remaining <= i2) {
                    byteBuffer.put(byteBuffer2);
                    i2 -= remaining;
                } else {
                    int limit = byteBuffer2.limit();
                    byteBuffer2.limit(byteBuffer2.position() + i2);
                    byteBuffer.put(byteBuffer2);
                    byteBuffer2.limit(limit);
                    i2 = 0;
                }
                byteBuffer2.position(position);
                if (i2 == 0) {
                    break;
                }
            }
        }
        byteBuffer.flip();
        return byteBuffer;
    }

    public static ByteBuffer getBufferLargerThan(ByteBuffer[] byteBufferArr, int i2) {
        int length = byteBufferArr.length;
        int i3 = 0;
        while (i3 < length) {
            ByteBuffer byteBuffer = byteBufferArr[i3];
            int remaining = byteBuffer.remaining();
            if (remaining > 0) {
                if (remaining >= i2) {
                    return byteBuffer;
                }
                do {
                    i3++;
                    if (i3 >= length) {
                        return byteBuffer;
                    }
                } while (byteBufferArr[i3].remaining() <= 0);
                return null;
            }
            i3++;
        }
        return null;
    }

    public static long remaining(ByteBuffer[] byteBufferArr) {
        long j2 = 0;
        for (ByteBuffer byteBuffer : byteBufferArr) {
            j2 += byteBuffer.remaining();
        }
        return j2;
    }
}
