.class public final LH0/b;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements LH0/s;


# instance fields
.field public final synthetic a:I

.field public final b:LB0/z;

.field public final c:Ljava/lang/Object;


# direct methods
.method public constructor <init>(LB0/z;LH0/b;)V
    .locals 1

    const/4 v0, 0x0

    iput v0, p0, LH0/b;->a:I

    .line 4
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 5
    iput-object p1, p0, LH0/b;->b:LB0/z;

    iput-object p2, p0, LH0/b;->c:Ljava/lang/Object;

    return-void
.end method

.method public constructor <init>(Ljava/io/OutputStream;LB0/z;)V
    .locals 1

    const/4 v0, 0x1

    iput v0, p0, LH0/b;->a:I

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 2
    iput-object p1, p0, LH0/b;->c:Ljava/lang/Object;

    .line 3
    iput-object p2, p0, LH0/b;->b:LB0/z;

    return-void
.end method


# virtual methods
.method public final a()LH0/v;
    .locals 1

    .line 1
    iget v0, p0, LH0/b;->a:I

    .line 2
    .line 3
    packed-switch v0, :pswitch_data_0

    .line 4
    .line 5
    .line 6
    iget-object v0, p0, LH0/b;->b:LB0/z;

    .line 7
    .line 8
    return-object v0

    .line 9
    :pswitch_0
    iget-object v0, p0, LH0/b;->b:LB0/z;

    .line 10
    .line 11
    return-object v0

    .line 12
    nop

    .line 13
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
    .end packed-switch
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

.method public final c(JLH0/e;)V
    .locals 8

    .line 1
    iget v0, p0, LH0/b;->a:I

    .line 2
    .line 3
    packed-switch v0, :pswitch_data_0

    .line 4
    .line 5
    .line 6
    iget-wide v1, p3, LH0/e;->b:J

    .line 7
    .line 8
    const-wide/16 v3, 0x0

    .line 9
    .line 10
    move-wide v5, p1

    .line 11
    invoke-static/range {v1 .. v6}, LC0/f;->e(JJJ)V

    .line 12
    .line 13
    .line 14
    :cond_0
    :goto_0
    const-wide/16 v0, 0x0

    .line 15
    .line 16
    cmp-long v0, p1, v0

    .line 17
    .line 18
    if-lez v0, :cond_1

    .line 19
    .line 20
    iget-object v0, p0, LH0/b;->b:LB0/z;

    .line 21
    .line 22
    invoke-virtual {v0}, LH0/v;->f()V

    .line 23
    .line 24
    .line 25
    iget-object v0, p3, LH0/e;->a:LH0/p;

    .line 26
    .line 27
    invoke-static {v0}, Lj0/d;->b(Ljava/lang/Object;)V

    .line 28
    .line 29
    .line 30
    iget v1, v0, LH0/p;->c:I

    .line 31
    .line 32
    iget v2, v0, LH0/p;->b:I

    .line 33
    .line 34
    sub-int/2addr v1, v2

    .line 35
    int-to-long v1, v1

    .line 36
    invoke-static {p1, p2, v1, v2}, Ljava/lang/Math;->min(JJ)J

    .line 37
    .line 38
    .line 39
    move-result-wide v1

    .line 40
    long-to-int v1, v1

    .line 41
    iget v2, v0, LH0/p;->b:I

    .line 42
    .line 43
    iget-object v3, p0, LH0/b;->c:Ljava/lang/Object;

    .line 44
    .line 45
    check-cast v3, Ljava/io/OutputStream;

    .line 46
    .line 47
    iget-object v4, v0, LH0/p;->a:[B

    .line 48
    .line 49
    invoke-virtual {v3, v4, v2, v1}, Ljava/io/OutputStream;->write([BII)V

    .line 50
    .line 51
    .line 52
    iget v2, v0, LH0/p;->b:I

    .line 53
    .line 54
    add-int/2addr v2, v1

    .line 55
    iput v2, v0, LH0/p;->b:I

    .line 56
    .line 57
    int-to-long v3, v1

    .line 58
    sub-long/2addr p1, v3

    .line 59
    iget-wide v5, p3, LH0/e;->b:J

    .line 60
    .line 61
    sub-long/2addr v5, v3

    .line 62
    iput-wide v5, p3, LH0/e;->b:J

    .line 63
    .line 64
    iget v1, v0, LH0/p;->c:I

    .line 65
    .line 66
    if-ne v2, v1, :cond_0

    .line 67
    .line 68
    invoke-virtual {v0}, LH0/p;->a()LH0/p;

    .line 69
    .line 70
    .line 71
    move-result-object v1

    .line 72
    iput-object v1, p3, LH0/e;->a:LH0/p;

    .line 73
    .line 74
    invoke-static {v0}, LH0/q;->a(LH0/p;)V

    .line 75
    .line 76
    .line 77
    goto :goto_0

    .line 78
    :cond_1
    return-void

    .line 79
    :pswitch_0
    iget-wide v2, p3, LH0/e;->b:J

    .line 80
    .line 81
    const-wide/16 v4, 0x0

    .line 82
    .line 83
    move-wide v6, p1

    .line 84
    invoke-static/range {v2 .. v7}, LC0/f;->e(JJJ)V

    .line 85
    .line 86
    .line 87
    :goto_1
    const-wide/16 v0, 0x0

    .line 88
    .line 89
    cmp-long v2, p1, v0

    .line 90
    .line 91
    if-lez v2, :cond_6

    .line 92
    .line 93
    iget-object v2, p3, LH0/e;->a:LH0/p;

    .line 94
    .line 95
    invoke-static {v2}, Lj0/d;->b(Ljava/lang/Object;)V

    .line 96
    .line 97
    .line 98
    :goto_2
    const-wide/32 v3, 0x10000

    .line 99
    .line 100
    .line 101
    cmp-long v3, v0, v3

    .line 102
    .line 103
    if-gez v3, :cond_3

    .line 104
    .line 105
    iget v3, v2, LH0/p;->c:I

    .line 106
    .line 107
    iget v4, v2, LH0/p;->b:I

    .line 108
    .line 109
    sub-int/2addr v3, v4

    .line 110
    int-to-long v3, v3

    .line 111
    add-long/2addr v0, v3

    .line 112
    cmp-long v3, v0, p1

    .line 113
    .line 114
    if-ltz v3, :cond_2

    .line 115
    .line 116
    move-wide v0, p1

    .line 117
    goto :goto_3

    .line 118
    :cond_2
    iget-object v2, v2, LH0/p;->f:LH0/p;

    .line 119
    .line 120
    invoke-static {v2}, Lj0/d;->b(Ljava/lang/Object;)V

    .line 121
    .line 122
    .line 123
    goto :goto_2

    .line 124
    :cond_3
    :goto_3
    iget-object v2, p0, LH0/b;->c:Ljava/lang/Object;

    .line 125
    .line 126
    check-cast v2, LH0/b;

    .line 127
    .line 128
    iget-object v3, p0, LH0/b;->b:LB0/z;

    .line 129
    .line 130
    invoke-virtual {v3}, LH0/d;->h()V

    .line 131
    .line 132
    .line 133
    :try_start_0
    invoke-virtual {v2, v0, v1, p3}, LH0/b;->c(JLH0/e;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 134
    .line 135
    .line 136
    invoke-virtual {v3}, LH0/d;->i()Z

    .line 137
    .line 138
    .line 139
    move-result v2

    .line 140
    if-nez v2, :cond_4

    .line 141
    .line 142
    sub-long/2addr p1, v0

    .line 143
    goto :goto_1

    .line 144
    :cond_4
    const/4 p1, 0x0

    .line 145
    invoke-virtual {v3, p1}, LB0/z;->l(Ljava/io/IOException;)Ljava/io/IOException;

    .line 146
    .line 147
    .line 148
    move-result-object p1

    .line 149
    throw p1

    .line 150
    :catchall_0
    move-exception p1

    .line 151
    goto :goto_5

    .line 152
    :catch_0
    move-exception p1

    .line 153
    :try_start_1
    invoke-virtual {v3}, LH0/d;->i()Z

    .line 154
    .line 155
    .line 156
    move-result p2

    .line 157
    if-nez p2, :cond_5

    .line 158
    .line 159
    goto :goto_4

    .line 160
    :cond_5
    invoke-virtual {v3, p1}, LB0/z;->l(Ljava/io/IOException;)Ljava/io/IOException;

    .line 161
    .line 162
    .line 163
    move-result-object p1

    .line 164
    :goto_4
    throw p1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 165
    :goto_5
    invoke-virtual {v3}, LH0/d;->i()Z

    .line 166
    .line 167
    .line 168
    throw p1

    .line 169
    :cond_6
    return-void

    .line 170
    nop

    .line 171
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
    .end packed-switch
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
    .line 255
    .line 256
    .line 257
    .line 258
    .line 259
    .line 260
    .line 261
    .line 262
    .line 263
    .line 264
    .line 265
    .line 266
    .line 267
    .line 268
    .line 269
    .line 270
    .line 271
    .line 272
    .line 273
    .line 274
    .line 275
    .line 276
.end method

.method public final close()V
    .locals 3

    .line 1
    iget v0, p0, LH0/b;->a:I

    .line 2
    .line 3
    packed-switch v0, :pswitch_data_0

    .line 4
    .line 5
    .line 6
    iget-object v0, p0, LH0/b;->c:Ljava/lang/Object;

    .line 7
    .line 8
    check-cast v0, Ljava/io/OutputStream;

    .line 9
    .line 10
    invoke-virtual {v0}, Ljava/io/OutputStream;->close()V

    .line 11
    .line 12
    .line 13
    return-void

    .line 14
    :pswitch_0
    iget-object v0, p0, LH0/b;->c:Ljava/lang/Object;

    .line 15
    .line 16
    check-cast v0, LH0/b;

    .line 17
    .line 18
    iget-object v1, p0, LH0/b;->b:LB0/z;

    .line 19
    .line 20
    invoke-virtual {v1}, LH0/d;->h()V

    .line 21
    .line 22
    .line 23
    :try_start_0
    invoke-virtual {v0}, LH0/b;->close()V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 24
    .line 25
    .line 26
    invoke-virtual {v1}, LH0/d;->i()Z

    .line 27
    .line 28
    .line 29
    move-result v0

    .line 30
    if-nez v0, :cond_0

    .line 31
    .line 32
    return-void

    .line 33
    :cond_0
    const/4 v0, 0x0

    .line 34
    invoke-virtual {v1, v0}, LB0/z;->l(Ljava/io/IOException;)Ljava/io/IOException;

    .line 35
    .line 36
    .line 37
    move-result-object v0

    .line 38
    throw v0

    .line 39
    :catchall_0
    move-exception v0

    .line 40
    goto :goto_1

    .line 41
    :catch_0
    move-exception v0

    .line 42
    :try_start_1
    invoke-virtual {v1}, LH0/d;->i()Z

    .line 43
    .line 44
    .line 45
    move-result v2

    .line 46
    if-nez v2, :cond_1

    .line 47
    .line 48
    goto :goto_0

    .line 49
    :cond_1
    invoke-virtual {v1, v0}, LB0/z;->l(Ljava/io/IOException;)Ljava/io/IOException;

    .line 50
    .line 51
    .line 52
    move-result-object v0

    .line 53
    :goto_0
    throw v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 54
    :goto_1
    invoke-virtual {v1}, LH0/d;->i()Z

    .line 55
    .line 56
    .line 57
    throw v0

    .line 58
    nop

    .line 59
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
    .end packed-switch
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

.method public final flush()V
    .locals 3

    .line 1
    iget v0, p0, LH0/b;->a:I

    .line 2
    .line 3
    packed-switch v0, :pswitch_data_0

    .line 4
    .line 5
    .line 6
    iget-object v0, p0, LH0/b;->c:Ljava/lang/Object;

    .line 7
    .line 8
    check-cast v0, Ljava/io/OutputStream;

    .line 9
    .line 10
    invoke-virtual {v0}, Ljava/io/OutputStream;->flush()V

    .line 11
    .line 12
    .line 13
    return-void

    .line 14
    :pswitch_0
    iget-object v0, p0, LH0/b;->c:Ljava/lang/Object;

    .line 15
    .line 16
    check-cast v0, LH0/b;

    .line 17
    .line 18
    iget-object v1, p0, LH0/b;->b:LB0/z;

    .line 19
    .line 20
    invoke-virtual {v1}, LH0/d;->h()V

    .line 21
    .line 22
    .line 23
    :try_start_0
    invoke-virtual {v0}, LH0/b;->flush()V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 24
    .line 25
    .line 26
    invoke-virtual {v1}, LH0/d;->i()Z

    .line 27
    .line 28
    .line 29
    move-result v0

    .line 30
    if-nez v0, :cond_0

    .line 31
    .line 32
    return-void

    .line 33
    :cond_0
    const/4 v0, 0x0

    .line 34
    invoke-virtual {v1, v0}, LB0/z;->l(Ljava/io/IOException;)Ljava/io/IOException;

    .line 35
    .line 36
    .line 37
    move-result-object v0

    .line 38
    throw v0

    .line 39
    :catchall_0
    move-exception v0

    .line 40
    goto :goto_1

    .line 41
    :catch_0
    move-exception v0

    .line 42
    :try_start_1
    invoke-virtual {v1}, LH0/d;->i()Z

    .line 43
    .line 44
    .line 45
    move-result v2

    .line 46
    if-nez v2, :cond_1

    .line 47
    .line 48
    goto :goto_0

    .line 49
    :cond_1
    invoke-virtual {v1, v0}, LB0/z;->l(Ljava/io/IOException;)Ljava/io/IOException;

    .line 50
    .line 51
    .line 52
    move-result-object v0

    .line 53
    :goto_0
    throw v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 54
    :goto_1
    invoke-virtual {v1}, LH0/d;->i()Z

    .line 55
    .line 56
    .line 57
    throw v0

    .line 58
    nop

    .line 59
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
    .end packed-switch
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

.method public final toString()Ljava/lang/String;
    .locals 2

    .line 1
    iget v0, p0, LH0/b;->a:I

    .line 2
    .line 3
    packed-switch v0, :pswitch_data_0

    .line 4
    .line 5
    .line 6
    new-instance v0, Ljava/lang/StringBuilder;

    .line 7
    .line 8
    const-string v1, "sink("

    .line 9
    .line 10
    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    .line 11
    .line 12
    .line 13
    iget-object v1, p0, LH0/b;->c:Ljava/lang/Object;

    .line 14
    .line 15
    check-cast v1, Ljava/io/OutputStream;

    .line 16
    .line 17
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    .line 18
    .line 19
    .line 20
    const/16 v1, 0x29

    .line 21
    .line 22
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 23
    .line 24
    .line 25
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    .line 26
    .line 27
    .line 28
    move-result-object v0

    .line 29
    return-object v0

    .line 30
    :pswitch_0
    new-instance v0, Ljava/lang/StringBuilder;

    .line 31
    .line 32
    const-string v1, "AsyncTimeout.sink("

    .line 33
    .line 34
    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    .line 35
    .line 36
    .line 37
    iget-object v1, p0, LH0/b;->c:Ljava/lang/Object;

    .line 38
    .line 39
    check-cast v1, LH0/b;

    .line 40
    .line 41
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    .line 42
    .line 43
    .line 44
    const/16 v1, 0x29

    .line 45
    .line 46
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 47
    .line 48
    .line 49
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    .line 50
    .line 51
    .line 52
    move-result-object v0

    .line 53
    return-object v0

    .line 54
    nop

    .line 55
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
    .end packed-switch
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
