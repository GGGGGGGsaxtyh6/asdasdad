.class public Lorg/conscrypt/ct/SignedCertificateTimestamp;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/conscrypt/ct/SignedCertificateTimestamp$Origin;,
        Lorg/conscrypt/ct/SignedCertificateTimestamp$SignatureType;,
        Lorg/conscrypt/ct/SignedCertificateTimestamp$Version;
    }
.end annotation


# instance fields
.field private final extensions:[B

.field private final logId:[B

.field private final origin:Lorg/conscrypt/ct/SignedCertificateTimestamp$Origin;

.field private final signature:Lorg/conscrypt/ct/DigitallySigned;

.field private final timestamp:J

.field private final version:Lorg/conscrypt/ct/SignedCertificateTimestamp$Version;


# direct methods
.method public constructor <init>(Lorg/conscrypt/ct/SignedCertificateTimestamp$Version;[BJ[BLorg/conscrypt/ct/DigitallySigned;Lorg/conscrypt/ct/SignedCertificateTimestamp$Origin;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 2
    .line 3
    .line 4
    iput-object p1, p0, Lorg/conscrypt/ct/SignedCertificateTimestamp;->version:Lorg/conscrypt/ct/SignedCertificateTimestamp$Version;

    .line 5
    .line 6
    iput-object p2, p0, Lorg/conscrypt/ct/SignedCertificateTimestamp;->logId:[B

    .line 7
    .line 8
    iput-wide p3, p0, Lorg/conscrypt/ct/SignedCertificateTimestamp;->timestamp:J

    .line 9
    .line 10
    iput-object p5, p0, Lorg/conscrypt/ct/SignedCertificateTimestamp;->extensions:[B

    .line 11
    .line 12
    iput-object p6, p0, Lorg/conscrypt/ct/SignedCertificateTimestamp;->signature:Lorg/conscrypt/ct/DigitallySigned;

    .line 13
    .line 14
    iput-object p7, p0, Lorg/conscrypt/ct/SignedCertificateTimestamp;->origin:Lorg/conscrypt/ct/SignedCertificateTimestamp$Origin;

    .line 15
    .line 16
    return-void
    .line 17
    .line 18
    .line 19
    .line 20
    .line 21
    .line 22
    .line 23
    .line 24
    .line 25
    .line 26
    .line 27
    .line 28
    .line 29
    .line 30
    .line 31
    .line 32
    .line 33
    .line 34
    .line 35
    .line 36
    .line 37
    .line 38
    .line 39
    .line 40
    .line 41
    .line 42
    .line 43
    .line 44
    .line 45
    .line 46
    .line 47
    .line 48
    .line 49
    .line 50
    .line 51
    .line 52
    .line 53
    .line 54
    .line 55
    .line 56
    .line 57
    .line 58
    .line 59
    .line 60
    .line 61
    .line 62
    .line 63
    .line 64
    .line 65
    .line 66
    .line 67
    .line 68
    .line 69
    .line 70
    .line 71
    .line 72
    .line 73
    .line 74
    .line 75
    .line 76
    .line 77
    .line 78
    .line 79
    .line 80
    .line 81
    .line 82
    .line 83
    .line 84
    .line 85
    .line 86
    .line 87
    .line 88
    .line 89
    .line 90
    .line 91
    .line 92
    .line 93
    .line 94
    .line 95
    .line 96
    .line 97
    .line 98
    .line 99
    .line 100
    .line 101
    .line 102
    .line 103
    .line 104
    .line 105
    .line 106
    .line 107
    .line 108
    .line 109
    .line 110
    .line 111
    .line 112
    .line 113
    .line 114
    .line 115
    .line 116
    .line 117
    .line 118
    .line 119
    .line 120
    .line 121
    .line 122
    .line 123
    .line 124
    .line 125
    .line 126
    .line 127
    .line 128
    .line 129
    .line 130
    .line 131
    .line 132
    .line 133
    .line 134
    .line 135
    .line 136
    .line 137
    .line 138
    .line 139
    .line 140
    .line 141
    .line 142
    .line 143
    .line 144
    .line 145
    .line 146
    .line 147
    .line 148
    .line 149
    .line 150
    .line 151
    .line 152
    .line 153
    .line 154
    .line 155
    .line 156
    .line 157
    .line 158
    .line 159
    .line 160
    .line 161
    .line 162
    .line 163
    .line 164
    .line 165
    .line 166
    .line 167
    .line 168
    .line 169
    .line 170
    .line 171
    .line 172
    .line 173
    .line 174
    .line 175
    .line 176
    .line 177
    .line 178
    .line 179
    .line 180
    .line 181
    .line 182
    .line 183
    .line 184
    .line 185
    .line 186
    .line 187
    .line 188
    .line 189
    .line 190
    .line 191
    .line 192
    .line 193
    .line 194
    .line 195
    .line 196
    .line 197
    .line 198
    .line 199
    .line 200
    .line 201
    .line 202
    .line 203
    .line 204
    .line 205
    .line 206
    .line 207
    .line 208
    .line 209
    .line 210
    .line 211
    .line 212
    .line 213
    .line 214
    .line 215
    .line 216
    .line 217
    .line 218
    .line 219
    .line 220
    .line 221
    .line 222
    .line 223
    .line 224
    .line 225
    .line 226
    .line 227
    .line 228
    .line 229
    .line 230
    .line 231
    .line 232
    .line 233
    .line 234
    .line 235
    .line 236
    .line 237
    .line 238
    .line 239
    .line 240
    .line 241
    .line 242
    .line 243
    .line 244
    .line 245
    .line 246
    .line 247
    .line 248
    .line 249
    .line 250
    .line 251
    .line 252
    .line 253
    .line 254
.end method

.method public static decode(Ljava/io/InputStream;Lorg/conscrypt/ct/SignedCertificateTimestamp$Origin;)Lorg/conscrypt/ct/SignedCertificateTimestamp;
    .locals 9

    const/4 v0, 0x1

    .line 1
    invoke-static {p0, v0}, Lorg/conscrypt/ct/Serialization;->readNumber(Ljava/io/InputStream;I)I

    move-result v0

    .line 2
    sget-object v2, Lorg/conscrypt/ct/SignedCertificateTimestamp$Version;->V1:Lorg/conscrypt/ct/SignedCertificateTimestamp$Version;

    invoke-virtual {v2}, Ljava/lang/Enum;->ordinal()I

    move-result v1

    if-ne v0, v1, :cond_0

    .line 3
    new-instance v0, Lorg/conscrypt/ct/SignedCertificateTimestamp;

    const/16 v1, 0x20

    .line 4
    invoke-static {p0, v1}, Lorg/conscrypt/ct/Serialization;->readFixedBytes(Ljava/io/InputStream;I)[B

    move-result-object v3

    const/16 v1, 0x8

    .line 5
    invoke-static {p0, v1}, Lorg/conscrypt/ct/Serialization;->readLong(Ljava/io/InputStream;I)J

    move-result-wide v4

    const/4 v1, 0x2

    .line 6
    invoke-static {p0, v1}, Lorg/conscrypt/ct/Serialization;->readVariableBytes(Ljava/io/InputStream;I)[B

    move-result-object v6

    .line 7
    invoke-static {p0}, Lorg/conscrypt/ct/DigitallySigned;->decode(Ljava/io/InputStream;)Lorg/conscrypt/ct/DigitallySigned;

    move-result-object v7

    move-object v1, v0

    move-object v8, p1

    invoke-direct/range {v1 .. v8}, Lorg/conscrypt/ct/SignedCertificateTimestamp;-><init>(Lorg/conscrypt/ct/SignedCertificateTimestamp$Version;[BJ[BLorg/conscrypt/ct/DigitallySigned;Lorg/conscrypt/ct/SignedCertificateTimestamp$Origin;)V

    return-object v0

    .line 8
    :cond_0
    new-instance p0, Lorg/conscrypt/ct/SerializationException;

    const-string p1, "Unsupported SCT version "

    .line 9
    invoke-static {p1, v0}, LA/e;->a(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object p1

    .line 10
    invoke-direct {p0, p1}, Lorg/conscrypt/ct/SerializationException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method public static decode([BLorg/conscrypt/ct/SignedCertificateTimestamp$Origin;)Lorg/conscrypt/ct/SignedCertificateTimestamp;
    .locals 1

    .line 15
    new-instance v0, Ljava/io/ByteArrayInputStream;

    invoke-direct {v0, p0}, Ljava/io/ByteArrayInputStream;-><init>([B)V

    invoke-static {v0, p1}, Lorg/conscrypt/ct/SignedCertificateTimestamp;->decode(Ljava/io/InputStream;Lorg/conscrypt/ct/SignedCertificateTimestamp$Origin;)Lorg/conscrypt/ct/SignedCertificateTimestamp;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public encodeTBS(Ljava/io/OutputStream;Lorg/conscrypt/ct/CertificateEntry;)V
    .locals 3

    .line 1
    iget-object v0, p0, Lorg/conscrypt/ct/SignedCertificateTimestamp;->version:Lorg/conscrypt/ct/SignedCertificateTimestamp$Version;

    invoke-virtual {v0}, Ljava/lang/Enum;->ordinal()I

    move-result v0

    int-to-long v0, v0

    const/4 v2, 0x1

    invoke-static {p1, v0, v1, v2}, Lorg/conscrypt/ct/Serialization;->writeNumber(Ljava/io/OutputStream;JI)V

    .line 2
    sget-object v0, Lorg/conscrypt/ct/SignedCertificateTimestamp$SignatureType;->CERTIFICATE_TIMESTAMP:Lorg/conscrypt/ct/SignedCertificateTimestamp$SignatureType;

    invoke-virtual {v0}, Ljava/lang/Enum;->ordinal()I

    move-result v0

    int-to-long v0, v0

    invoke-static {p1, v0, v1, v2}, Lorg/conscrypt/ct/Serialization;->writeNumber(Ljava/io/OutputStream;JI)V

    .line 3
    iget-wide v0, p0, Lorg/conscrypt/ct/SignedCertificateTimestamp;->timestamp:J

    const/16 v2, 0x8

    invoke-static {p1, v0, v1, v2}, Lorg/conscrypt/ct/Serialization;->writeNumber(Ljava/io/OutputStream;JI)V

    .line 4
    invoke-virtual {p2, p1}, Lorg/conscrypt/ct/CertificateEntry;->encode(Ljava/io/OutputStream;)V

    .line 5
    iget-object p2, p0, Lorg/conscrypt/ct/SignedCertificateTimestamp;->extensions:[B

    const/4 v0, 0x2

    invoke-static {p1, p2, v0}, Lorg/conscrypt/ct/Serialization;->writeVariableBytes(Ljava/io/OutputStream;[BI)V

    return-void
.end method

.method public encodeTBS(Lorg/conscrypt/ct/CertificateEntry;)[B
    .locals 1

    .line 6
    new-instance v0, Ljava/io/ByteArrayOutputStream;

    invoke-direct {v0}, Ljava/io/ByteArrayOutputStream;-><init>()V

    .line 7
    invoke-virtual {p0, v0, p1}, Lorg/conscrypt/ct/SignedCertificateTimestamp;->encodeTBS(Ljava/io/OutputStream;Lorg/conscrypt/ct/CertificateEntry;)V

    .line 8
    invoke-virtual {v0}, Ljava/io/ByteArrayOutputStream;->toByteArray()[B

    move-result-object p1

    return-object p1
.end method

.method public getExtensions()[B
    .locals 1

    .line 1
    iget-object v0, p0, Lorg/conscrypt/ct/SignedCertificateTimestamp;->extensions:[B

    .line 2
    .line 3
    return-object v0
    .line 4
    .line 5
    .line 6
    .line 7
    .line 8
    .line 9
    .line 10
    .line 11
    .line 12
    .line 13
    .line 14
    .line 15
    .line 16
    .line 17
    .line 18
    .line 19
    .line 20
    .line 21
    .line 22
    .line 23
    .line 24
    .line 25
    .line 26
    .line 27
    .line 28
    .line 29
    .line 30
    .line 31
    .line 32
    .line 33
    .line 34
    .line 35
    .line 36
    .line 37
    .line 38
    .line 39
    .line 40
    .line 41
    .line 42
    .line 43
    .line 44
    .line 45
    .line 46
    .line 47
    .line 48
    .line 49
    .line 50
    .line 51
    .line 52
    .line 53
    .line 54
    .line 55
    .line 56
    .line 57
    .line 58
    .line 59
    .line 60
    .line 61
    .line 62
    .line 63
    .line 64
    .line 65
    .line 66
    .line 67
    .line 68
    .line 69
    .line 70
    .line 71
    .line 72
    .line 73
    .line 74
    .line 75
    .line 76
    .line 77
    .line 78
    .line 79
    .line 80
    .line 81
    .line 82
    .line 83
    .line 84
    .line 85
    .line 86
    .line 87
    .line 88
    .line 89
.end method

.method public getLogID()[B
    .locals 1

    .line 1
    iget-object v0, p0, Lorg/conscrypt/ct/SignedCertificateTimestamp;->logId:[B

    .line 2
    .line 3
    return-object v0
    .line 4
    .line 5
    .line 6
    .line 7
    .line 8
    .line 9
    .line 10
    .line 11
    .line 12
    .line 13
    .line 14
    .line 15
    .line 16
    .line 17
    .line 18
    .line 19
    .line 20
    .line 21
    .line 22
    .line 23
    .line 24
    .line 25
    .line 26
    .line 27
    .line 28
    .line 29
    .line 30
    .line 31
    .line 32
    .line 33
    .line 34
    .line 35
    .line 36
    .line 37
    .line 38
    .line 39
    .line 40
    .line 41
    .line 42
    .line 43
    .line 44
    .line 45
    .line 46
    .line 47
    .line 48
    .line 49
    .line 50
    .line 51
    .line 52
    .line 53
    .line 54
    .line 55
    .line 56
    .line 57
    .line 58
    .line 59
    .line 60
    .line 61
    .line 62
    .line 63
    .line 64
    .line 65
    .line 66
    .line 67
    .line 68
    .line 69
    .line 70
    .line 71
    .line 72
    .line 73
    .line 74
    .line 75
    .line 76
    .line 77
    .line 78
    .line 79
    .line 80
    .line 81
    .line 82
    .line 83
    .line 84
    .line 85
    .line 86
    .line 87
    .line 88
    .line 89
.end method

.method public getOrigin()Lorg/conscrypt/ct/SignedCertificateTimestamp$Origin;
    .locals 1

    .line 1
    iget-object v0, p0, Lorg/conscrypt/ct/SignedCertificateTimestamp;->origin:Lorg/conscrypt/ct/SignedCertificateTimestamp$Origin;

    .line 2
    .line 3
    return-object v0
    .line 4
    .line 5
    .line 6
    .line 7
    .line 8
    .line 9
    .line 10
    .line 11
    .line 12
    .line 13
    .line 14
    .line 15
    .line 16
    .line 17
    .line 18
    .line 19
    .line 20
    .line 21
    .line 22
    .line 23
    .line 24
    .line 25
    .line 26
    .line 27
    .line 28
    .line 29
    .line 30
    .line 31
    .line 32
    .line 33
    .line 34
    .line 35
    .line 36
    .line 37
    .line 38
    .line 39
    .line 40
    .line 41
    .line 42
    .line 43
    .line 44
    .line 45
    .line 46
    .line 47
    .line 48
    .line 49
    .line 50
    .line 51
    .line 52
    .line 53
    .line 54
    .line 55
    .line 56
    .line 57
    .line 58
    .line 59
    .line 60
    .line 61
    .line 62
    .line 63
    .line 64
    .line 65
    .line 66
    .line 67
    .line 68
    .line 69
    .line 70
    .line 71
    .line 72
    .line 73
    .line 74
    .line 75
    .line 76
    .line 77
    .line 78
    .line 79
    .line 80
    .line 81
    .line 82
    .line 83
    .line 84
    .line 85
    .line 86
    .line 87
    .line 88
    .line 89
.end method

.method public getSignature()Lorg/conscrypt/ct/DigitallySigned;
    .locals 1

    .line 1
    iget-object v0, p0, Lorg/conscrypt/ct/SignedCertificateTimestamp;->signature:Lorg/conscrypt/ct/DigitallySigned;

    .line 2
    .line 3
    return-object v0
    .line 4
    .line 5
    .line 6
    .line 7
    .line 8
    .line 9
    .line 10
    .line 11
    .line 12
    .line 13
    .line 14
    .line 15
    .line 16
    .line 17
    .line 18
    .line 19
    .line 20
    .line 21
    .line 22
    .line 23
    .line 24
    .line 25
    .line 26
    .line 27
    .line 28
    .line 29
    .line 30
    .line 31
    .line 32
    .line 33
    .line 34
    .line 35
    .line 36
    .line 37
    .line 38
    .line 39
    .line 40
    .line 41
    .line 42
    .line 43
    .line 44
    .line 45
    .line 46
    .line 47
    .line 48
    .line 49
    .line 50
    .line 51
    .line 52
    .line 53
    .line 54
    .line 55
    .line 56
    .line 57
    .line 58
    .line 59
    .line 60
    .line 61
    .line 62
    .line 63
    .line 64
    .line 65
    .line 66
    .line 67
    .line 68
    .line 69
    .line 70
    .line 71
    .line 72
    .line 73
    .line 74
    .line 75
    .line 76
    .line 77
    .line 78
    .line 79
    .line 80
    .line 81
    .line 82
    .line 83
    .line 84
    .line 85
    .line 86
    .line 87
    .line 88
    .line 89
.end method

.method public getTimestamp()J
    .locals 2

    .line 1
    iget-wide v0, p0, Lorg/conscrypt/ct/SignedCertificateTimestamp;->timestamp:J

    .line 2
    .line 3
    return-wide v0
    .line 4
    .line 5
    .line 6
    .line 7
    .line 8
    .line 9
    .line 10
    .line 11
    .line 12
    .line 13
    .line 14
    .line 15
    .line 16
    .line 17
    .line 18
    .line 19
    .line 20
    .line 21
    .line 22
    .line 23
    .line 24
    .line 25
    .line 26
    .line 27
    .line 28
    .line 29
    .line 30
    .line 31
    .line 32
    .line 33
    .line 34
    .line 35
    .line 36
    .line 37
    .line 38
    .line 39
    .line 40
    .line 41
    .line 42
    .line 43
    .line 44
    .line 45
    .line 46
    .line 47
    .line 48
    .line 49
    .line 50
    .line 51
    .line 52
    .line 53
    .line 54
    .line 55
    .line 56
    .line 57
    .line 58
    .line 59
    .line 60
    .line 61
    .line 62
    .line 63
    .line 64
    .line 65
    .line 66
    .line 67
    .line 68
    .line 69
    .line 70
    .line 71
    .line 72
    .line 73
    .line 74
    .line 75
    .line 76
    .line 77
    .line 78
    .line 79
    .line 80
    .line 81
    .line 82
    .line 83
    .line 84
    .line 85
    .line 86
    .line 87
    .line 88
    .line 89
.end method

.method public getVersion()Lorg/conscrypt/ct/SignedCertificateTimestamp$Version;
    .locals 1

    .line 1
    iget-object v0, p0, Lorg/conscrypt/ct/SignedCertificateTimestamp;->version:Lorg/conscrypt/ct/SignedCertificateTimestamp$Version;

    .line 2
    .line 3
    return-object v0
    .line 4
    .line 5
    .line 6
    .line 7
    .line 8
    .line 9
    .line 10
    .line 11
    .line 12
    .line 13
    .line 14
    .line 15
    .line 16
    .line 17
    .line 18
    .line 19
    .line 20
    .line 21
    .line 22
    .line 23
    .line 24
    .line 25
    .line 26
    .line 27
    .line 28
    .line 29
    .line 30
    .line 31
    .line 32
    .line 33
    .line 34
    .line 35
    .line 36
    .line 37
    .line 38
    .line 39
    .line 40
    .line 41
    .line 42
    .line 43
    .line 44
    .line 45
    .line 46
    .line 47
    .line 48
    .line 49
    .line 50
    .line 51
    .line 52
    .line 53
    .line 54
    .line 55
    .line 56
    .line 57
    .line 58
    .line 59
    .line 60
    .line 61
    .line 62
    .line 63
    .line 64
    .line 65
    .line 66
    .line 67
    .line 68
    .line 69
    .line 70
    .line 71
    .line 72
    .line 73
    .line 74
    .line 75
    .line 76
    .line 77
    .line 78
    .line 79
    .line 80
    .line 81
    .line 82
    .line 83
    .line 84
    .line 85
    .line 86
    .line 87
    .line 88
    .line 89
.end method
