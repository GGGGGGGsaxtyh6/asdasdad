.class public final Lt/c;
.super Lt/o;
.source "SourceFile"


# instance fields
.field public final k:Ljava/util/ArrayList;

.field public l:I


# direct methods
.method public constructor <init>(Ls/d;I)V
    .locals 4

    .line 1
    invoke-direct {p0, p1}, Lt/o;-><init>(Ls/d;)V

    .line 2
    .line 3
    .line 4
    new-instance p1, Ljava/util/ArrayList;

    .line 5
    .line 6
    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    .line 7
    .line 8
    .line 9
    iput-object p1, p0, Lt/c;->k:Ljava/util/ArrayList;

    .line 10
    .line 11
    iput p2, p0, Lt/o;->f:I

    .line 12
    .line 13
    iget-object p1, p0, Lt/o;->b:Ls/d;

    .line 14
    .line 15
    invoke-virtual {p1, p2}, Ls/d;->k(I)Ls/d;

    .line 16
    .line 17
    .line 18
    move-result-object p2

    .line 19
    :goto_0
    move-object v3, p2

    .line 20
    move-object p2, p1

    .line 21
    move-object p1, v3

    .line 22
    if-eqz p1, :cond_0

    .line 23
    .line 24
    iget p2, p0, Lt/o;->f:I

    .line 25
    .line 26
    invoke-virtual {p1, p2}, Ls/d;->k(I)Ls/d;

    .line 27
    .line 28
    .line 29
    move-result-object p2

    .line 30
    goto :goto_0

    .line 31
    :cond_0
    iput-object p2, p0, Lt/o;->b:Ls/d;

    .line 32
    .line 33
    iget p1, p0, Lt/o;->f:I

    .line 34
    .line 35
    const/4 v0, 0x0

    .line 36
    const/4 v1, 0x1

    .line 37
    if-nez p1, :cond_1

    .line 38
    .line 39
    iget-object p1, p2, Ls/d;->d:Lt/k;

    .line 40
    .line 41
    goto :goto_1

    .line 42
    :cond_1
    if-ne p1, v1, :cond_2

    .line 43
    .line 44
    iget-object p1, p2, Ls/d;->e:Lt/m;

    .line 45
    .line 46
    goto :goto_1

    .line 47
    :cond_2
    move-object p1, v0

    .line 48
    :goto_1
    iget-object v2, p0, Lt/c;->k:Ljava/util/ArrayList;

    .line 49
    .line 50
    invoke-virtual {v2, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 51
    .line 52
    .line 53
    iget p1, p0, Lt/o;->f:I

    .line 54
    .line 55
    invoke-virtual {p2, p1}, Ls/d;->j(I)Ls/d;

    .line 56
    .line 57
    .line 58
    move-result-object p1

    .line 59
    :goto_2
    if-eqz p1, :cond_5

    .line 60
    .line 61
    iget p2, p0, Lt/o;->f:I

    .line 62
    .line 63
    if-nez p2, :cond_3

    .line 64
    .line 65
    iget-object p2, p1, Ls/d;->d:Lt/k;

    .line 66
    .line 67
    goto :goto_3

    .line 68
    :cond_3
    if-ne p2, v1, :cond_4

    .line 69
    .line 70
    iget-object p2, p1, Ls/d;->e:Lt/m;

    .line 71
    .line 72
    goto :goto_3

    .line 73
    :cond_4
    move-object p2, v0

    .line 74
    :goto_3
    invoke-virtual {v2, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 75
    .line 76
    .line 77
    iget p2, p0, Lt/o;->f:I

    .line 78
    .line 79
    invoke-virtual {p1, p2}, Ls/d;->j(I)Ls/d;

    .line 80
    .line 81
    .line 82
    move-result-object p1

    .line 83
    goto :goto_2

    .line 84
    :cond_5
    invoke-virtual {v2}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    .line 85
    .line 86
    .line 87
    move-result-object p1

    .line 88
    :cond_6
    :goto_4
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    .line 89
    .line 90
    .line 91
    move-result p2

    .line 92
    if-eqz p2, :cond_8

    .line 93
    .line 94
    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    .line 95
    .line 96
    .line 97
    move-result-object p2

    .line 98
    check-cast p2, Lt/o;

    .line 99
    .line 100
    iget v0, p0, Lt/o;->f:I

    .line 101
    .line 102
    if-nez v0, :cond_7

    .line 103
    .line 104
    iget-object p2, p2, Lt/o;->b:Ls/d;

    .line 105
    .line 106
    iput-object p0, p2, Ls/d;->b:Lt/c;

    .line 107
    .line 108
    goto :goto_4

    .line 109
    :cond_7
    if-ne v0, v1, :cond_6

    .line 110
    .line 111
    iget-object p2, p2, Lt/o;->b:Ls/d;

    .line 112
    .line 113
    iput-object p0, p2, Ls/d;->c:Lt/c;

    .line 114
    .line 115
    goto :goto_4

    .line 116
    :cond_8
    iget p1, p0, Lt/o;->f:I

    .line 117
    .line 118
    if-nez p1, :cond_9

    .line 119
    .line 120
    iget-object p1, p0, Lt/o;->b:Ls/d;

    .line 121
    .line 122
    iget-object p1, p1, Ls/d;->S:Ls/d;

    .line 123
    .line 124
    check-cast p1, Ls/e;

    .line 125
    .line 126
    iget-boolean p1, p1, Ls/e;->u0:Z

    .line 127
    .line 128
    if-eqz p1, :cond_9

    .line 129
    .line 130
    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    .line 131
    .line 132
    .line 133
    move-result p1

    .line 134
    if-le p1, v1, :cond_9

    .line 135
    .line 136
    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    .line 137
    .line 138
    .line 139
    move-result p1

    .line 140
    sub-int/2addr p1, v1

    .line 141
    invoke-virtual {v2, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    .line 142
    .line 143
    .line 144
    move-result-object p1

    .line 145
    check-cast p1, Lt/o;

    .line 146
    .line 147
    iget-object p1, p1, Lt/o;->b:Ls/d;

    .line 148
    .line 149
    iput-object p1, p0, Lt/o;->b:Ls/d;

    .line 150
    .line 151
    :cond_9
    iget p1, p0, Lt/o;->f:I

    .line 152
    .line 153
    if-nez p1, :cond_a

    .line 154
    .line 155
    iget-object p1, p0, Lt/o;->b:Ls/d;

    .line 156
    .line 157
    iget p1, p1, Ls/d;->h0:I

    .line 158
    .line 159
    goto :goto_5

    .line 160
    :cond_a
    iget-object p1, p0, Lt/o;->b:Ls/d;

    .line 161
    .line 162
    iget p1, p1, Ls/d;->i0:I

    .line 163
    .line 164
    :goto_5
    iput p1, p0, Lt/c;->l:I

    .line 165
    .line 166
    return-void
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


# virtual methods
.method public final a(Lt/d;)V
    .locals 26

    .line 1
    move-object/from16 v0, p0

    .line 2
    .line 3
    iget-object v1, v0, Lt/o;->h:Lt/f;

    .line 4
    .line 5
    iget-boolean v2, v1, Lt/f;->j:Z

    .line 6
    .line 7
    if-eqz v2, :cond_56

    .line 8
    .line 9
    iget-object v2, v0, Lt/o;->i:Lt/f;

    .line 10
    .line 11
    iget-boolean v3, v2, Lt/f;->j:Z

    .line 12
    .line 13
    if-nez v3, :cond_0

    .line 14
    .line 15
    goto/16 :goto_33

    .line 16
    .line 17
    :cond_0
    iget-object v3, v0, Lt/o;->b:Ls/d;

    .line 18
    .line 19
    iget-object v3, v3, Ls/d;->S:Ls/d;

    .line 20
    .line 21
    instance-of v4, v3, Ls/e;

    .line 22
    .line 23
    if-eqz v4, :cond_1

    .line 24
    .line 25
    check-cast v3, Ls/e;

    .line 26
    .line 27
    iget-boolean v3, v3, Ls/e;->u0:Z

    .line 28
    .line 29
    goto :goto_0

    .line 30
    :cond_1
    const/4 v3, 0x0

    .line 31
    :goto_0
    iget v4, v2, Lt/f;->g:I

    .line 32
    .line 33
    iget v6, v1, Lt/f;->g:I

    .line 34
    .line 35
    sub-int/2addr v4, v6

    .line 36
    iget-object v6, v0, Lt/c;->k:Ljava/util/ArrayList;

    .line 37
    .line 38
    invoke-virtual {v6}, Ljava/util/ArrayList;->size()I

    .line 39
    .line 40
    .line 41
    move-result v7

    .line 42
    const/4 v8, 0x0

    .line 43
    :goto_1
    const/4 v9, -0x1

    .line 44
    const/16 v10, 0x8

    .line 45
    .line 46
    if-ge v8, v7, :cond_2

    .line 47
    .line 48
    invoke-virtual {v6, v8}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    .line 49
    .line 50
    .line 51
    move-result-object v11

    .line 52
    check-cast v11, Lt/o;

    .line 53
    .line 54
    iget-object v11, v11, Lt/o;->b:Ls/d;

    .line 55
    .line 56
    iget v11, v11, Ls/d;->f0:I

    .line 57
    .line 58
    if-ne v11, v10, :cond_3

    .line 59
    .line 60
    add-int/lit8 v8, v8, 0x1

    .line 61
    .line 62
    goto :goto_1

    .line 63
    :cond_2
    move v8, v9

    .line 64
    :cond_3
    add-int/lit8 v11, v7, -0x1

    .line 65
    .line 66
    move v12, v11

    .line 67
    :goto_2
    if-ltz v12, :cond_5

    .line 68
    .line 69
    invoke-virtual {v6, v12}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    .line 70
    .line 71
    .line 72
    move-result-object v13

    .line 73
    check-cast v13, Lt/o;

    .line 74
    .line 75
    iget-object v13, v13, Lt/o;->b:Ls/d;

    .line 76
    .line 77
    iget v13, v13, Ls/d;->f0:I

    .line 78
    .line 79
    if-ne v13, v10, :cond_4

    .line 80
    .line 81
    add-int/lit8 v12, v12, -0x1

    .line 82
    .line 83
    goto :goto_2

    .line 84
    :cond_4
    move v9, v12

    .line 85
    :cond_5
    const/4 v12, 0x0

    .line 86
    :goto_3
    const/4 v5, 0x2

    .line 87
    if-ge v12, v5, :cond_14

    .line 88
    .line 89
    const/4 v5, 0x0

    .line 90
    const/4 v14, 0x0

    .line 91
    const/16 v17, 0x0

    .line 92
    .line 93
    const/16 v18, 0x0

    .line 94
    .line 95
    const/16 v19, 0x0

    .line 96
    .line 97
    :goto_4
    if-ge v5, v7, :cond_11

    .line 98
    .line 99
    invoke-virtual {v6, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    .line 100
    .line 101
    .line 102
    move-result-object v20

    .line 103
    move-object/from16 v13, v20

    .line 104
    .line 105
    check-cast v13, Lt/o;

    .line 106
    .line 107
    iget-object v15, v13, Lt/o;->b:Ls/d;

    .line 108
    .line 109
    move-object/from16 v21, v6

    .line 110
    .line 111
    iget v6, v15, Ls/d;->f0:I

    .line 112
    .line 113
    if-ne v6, v10, :cond_6

    .line 114
    .line 115
    move/from16 v23, v8

    .line 116
    .line 117
    goto/16 :goto_a

    .line 118
    .line 119
    :cond_6
    add-int/lit8 v18, v18, 0x1

    .line 120
    .line 121
    if-lez v5, :cond_7

    .line 122
    .line 123
    if-lt v5, v8, :cond_7

    .line 124
    .line 125
    iget-object v6, v13, Lt/o;->h:Lt/f;

    .line 126
    .line 127
    iget v6, v6, Lt/f;->f:I

    .line 128
    .line 129
    add-int/2addr v14, v6

    .line 130
    :cond_7
    iget-object v6, v13, Lt/o;->e:Lt/g;

    .line 131
    .line 132
    iget v10, v6, Lt/f;->g:I

    .line 133
    .line 134
    move/from16 v22, v10

    .line 135
    .line 136
    iget v10, v13, Lt/o;->d:I

    .line 137
    .line 138
    move/from16 v23, v8

    .line 139
    .line 140
    const/4 v8, 0x3

    .line 141
    if-eq v10, v8, :cond_8

    .line 142
    .line 143
    const/4 v8, 0x1

    .line 144
    goto :goto_5

    .line 145
    :cond_8
    const/4 v8, 0x0

    .line 146
    :goto_5
    if-eqz v8, :cond_b

    .line 147
    .line 148
    iget v6, v0, Lt/o;->f:I

    .line 149
    .line 150
    if-nez v6, :cond_9

    .line 151
    .line 152
    iget-object v10, v15, Ls/d;->d:Lt/k;

    .line 153
    .line 154
    iget-object v10, v10, Lt/o;->e:Lt/g;

    .line 155
    .line 156
    iget-boolean v10, v10, Lt/f;->j:Z

    .line 157
    .line 158
    if-nez v10, :cond_9

    .line 159
    .line 160
    return-void

    .line 161
    :cond_9
    const/4 v10, 0x1

    .line 162
    if-ne v6, v10, :cond_a

    .line 163
    .line 164
    iget-object v6, v15, Ls/d;->e:Lt/m;

    .line 165
    .line 166
    iget-object v6, v6, Lt/o;->e:Lt/g;

    .line 167
    .line 168
    iget-boolean v6, v6, Lt/f;->j:Z

    .line 169
    .line 170
    if-nez v6, :cond_a

    .line 171
    .line 172
    return-void

    .line 173
    :cond_a
    move/from16 v24, v8

    .line 174
    .line 175
    goto :goto_7

    .line 176
    :cond_b
    move/from16 v24, v8

    .line 177
    .line 178
    const/4 v10, 0x1

    .line 179
    iget v8, v13, Lt/o;->a:I

    .line 180
    .line 181
    if-ne v8, v10, :cond_c

    .line 182
    .line 183
    if-nez v12, :cond_c

    .line 184
    .line 185
    iget v10, v6, Lt/g;->m:I

    .line 186
    .line 187
    add-int/lit8 v17, v17, 0x1

    .line 188
    .line 189
    :goto_6
    const/16 v24, 0x1

    .line 190
    .line 191
    goto :goto_8

    .line 192
    :cond_c
    iget-boolean v6, v6, Lt/f;->j:Z

    .line 193
    .line 194
    if-eqz v6, :cond_d

    .line 195
    .line 196
    move/from16 v10, v22

    .line 197
    .line 198
    goto :goto_6

    .line 199
    :cond_d
    :goto_7
    move/from16 v10, v22

    .line 200
    .line 201
    :goto_8
    if-nez v24, :cond_e

    .line 202
    .line 203
    add-int/lit8 v17, v17, 0x1

    .line 204
    .line 205
    iget-object v6, v15, Ls/d;->j0:[F

    .line 206
    .line 207
    iget v8, v0, Lt/o;->f:I

    .line 208
    .line 209
    aget v6, v6, v8

    .line 210
    .line 211
    const/4 v8, 0x0

    .line 212
    cmpl-float v10, v6, v8

    .line 213
    .line 214
    if-ltz v10, :cond_f

    .line 215
    .line 216
    add-float v19, v19, v6

    .line 217
    .line 218
    goto :goto_9

    .line 219
    :cond_e
    add-int/2addr v14, v10

    .line 220
    :cond_f
    :goto_9
    if-ge v5, v11, :cond_10

    .line 221
    .line 222
    if-ge v5, v9, :cond_10

    .line 223
    .line 224
    iget-object v6, v13, Lt/o;->i:Lt/f;

    .line 225
    .line 226
    iget v6, v6, Lt/f;->f:I

    .line 227
    .line 228
    neg-int v6, v6

    .line 229
    add-int/2addr v14, v6

    .line 230
    :cond_10
    :goto_a
    add-int/lit8 v5, v5, 0x1

    .line 231
    .line 232
    move-object/from16 v6, v21

    .line 233
    .line 234
    move/from16 v8, v23

    .line 235
    .line 236
    const/16 v10, 0x8

    .line 237
    .line 238
    goto/16 :goto_4

    .line 239
    .line 240
    :cond_11
    move-object/from16 v21, v6

    .line 241
    .line 242
    move/from16 v23, v8

    .line 243
    .line 244
    if-lt v14, v4, :cond_13

    .line 245
    .line 246
    if-nez v17, :cond_12

    .line 247
    .line 248
    goto :goto_b

    .line 249
    :cond_12
    add-int/lit8 v12, v12, 0x1

    .line 250
    .line 251
    move-object/from16 v6, v21

    .line 252
    .line 253
    move/from16 v8, v23

    .line 254
    .line 255
    const/16 v10, 0x8

    .line 256
    .line 257
    goto/16 :goto_3

    .line 258
    .line 259
    :cond_13
    :goto_b
    move/from16 v5, v17

    .line 260
    .line 261
    move/from16 v6, v18

    .line 262
    .line 263
    goto :goto_c

    .line 264
    :cond_14
    move-object/from16 v21, v6

    .line 265
    .line 266
    move/from16 v23, v8

    .line 267
    .line 268
    const/4 v5, 0x0

    .line 269
    const/4 v6, 0x0

    .line 270
    const/4 v14, 0x0

    .line 271
    const/16 v19, 0x0

    .line 272
    .line 273
    :goto_c
    iget v1, v1, Lt/f;->g:I

    .line 274
    .line 275
    if-eqz v3, :cond_15

    .line 276
    .line 277
    iget v1, v2, Lt/f;->g:I

    .line 278
    .line 279
    :cond_15
    const/high16 v2, 0x3f000000    # 0.5f

    .line 280
    .line 281
    if-le v14, v4, :cond_17

    .line 282
    .line 283
    const/high16 v8, 0x40000000    # 2.0f

    .line 284
    .line 285
    if-eqz v3, :cond_16

    .line 286
    .line 287
    sub-int v10, v14, v4

    .line 288
    .line 289
    int-to-float v10, v10

    .line 290
    div-float/2addr v10, v8

    .line 291
    add-float/2addr v10, v2

    .line 292
    float-to-int v8, v10

    .line 293
    add-int/2addr v1, v8

    .line 294
    goto :goto_d

    .line 295
    :cond_16
    sub-int v10, v14, v4

    .line 296
    .line 297
    int-to-float v10, v10

    .line 298
    div-float/2addr v10, v8

    .line 299
    add-float/2addr v10, v2

    .line 300
    float-to-int v8, v10

    .line 301
    sub-int/2addr v1, v8

    .line 302
    :cond_17
    :goto_d
    if-lez v5, :cond_26

    .line 303
    .line 304
    sub-int v8, v4, v14

    .line 305
    .line 306
    int-to-float v8, v8

    .line 307
    int-to-float v10, v5

    .line 308
    div-float v10, v8, v10

    .line 309
    .line 310
    add-float/2addr v10, v2

    .line 311
    float-to-int v10, v10

    .line 312
    const/4 v12, 0x0

    .line 313
    const/4 v13, 0x0

    .line 314
    :goto_e
    if-ge v12, v7, :cond_1f

    .line 315
    .line 316
    move-object/from16 v15, v21

    .line 317
    .line 318
    invoke-virtual {v15, v12}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    .line 319
    .line 320
    .line 321
    move-result-object v17

    .line 322
    move-object/from16 v2, v17

    .line 323
    .line 324
    check-cast v2, Lt/o;

    .line 325
    .line 326
    move/from16 v17, v10

    .line 327
    .line 328
    iget-object v10, v2, Lt/o;->b:Ls/d;

    .line 329
    .line 330
    move/from16 v21, v14

    .line 331
    .line 332
    iget v14, v10, Ls/d;->f0:I

    .line 333
    .line 334
    move/from16 v22, v1

    .line 335
    .line 336
    const/16 v1, 0x8

    .line 337
    .line 338
    if-ne v14, v1, :cond_19

    .line 339
    .line 340
    :cond_18
    move/from16 v24, v3

    .line 341
    .line 342
    move/from16 v25, v8

    .line 343
    .line 344
    goto :goto_12

    .line 345
    :cond_19
    iget v1, v2, Lt/o;->d:I

    .line 346
    .line 347
    const/4 v14, 0x3

    .line 348
    if-ne v1, v14, :cond_18

    .line 349
    .line 350
    iget-object v1, v2, Lt/o;->e:Lt/g;

    .line 351
    .line 352
    iget-boolean v14, v1, Lt/f;->j:Z

    .line 353
    .line 354
    if-nez v14, :cond_18

    .line 355
    .line 356
    const/4 v14, 0x0

    .line 357
    cmpl-float v16, v19, v14

    .line 358
    .line 359
    if-lez v16, :cond_1a

    .line 360
    .line 361
    iget-object v14, v10, Ls/d;->j0:[F

    .line 362
    .line 363
    move/from16 v24, v3

    .line 364
    .line 365
    iget v3, v0, Lt/o;->f:I

    .line 366
    .line 367
    aget v3, v14, v3

    .line 368
    .line 369
    mul-float/2addr v3, v8

    .line 370
    div-float v3, v3, v19

    .line 371
    .line 372
    const/high16 v14, 0x3f000000    # 0.5f

    .line 373
    .line 374
    add-float/2addr v3, v14

    .line 375
    float-to-int v3, v3

    .line 376
    goto :goto_f

    .line 377
    :cond_1a
    move/from16 v24, v3

    .line 378
    .line 379
    move/from16 v3, v17

    .line 380
    .line 381
    :goto_f
    iget v14, v0, Lt/o;->f:I

    .line 382
    .line 383
    if-nez v14, :cond_1b

    .line 384
    .line 385
    iget v14, v10, Ls/d;->v:I

    .line 386
    .line 387
    iget v10, v10, Ls/d;->u:I

    .line 388
    .line 389
    goto :goto_10

    .line 390
    :cond_1b
    iget v14, v10, Ls/d;->y:I

    .line 391
    .line 392
    iget v10, v10, Ls/d;->x:I

    .line 393
    .line 394
    :goto_10
    iget v2, v2, Lt/o;->a:I

    .line 395
    .line 396
    move/from16 v25, v8

    .line 397
    .line 398
    const/4 v8, 0x1

    .line 399
    if-ne v2, v8, :cond_1c

    .line 400
    .line 401
    iget v2, v1, Lt/g;->m:I

    .line 402
    .line 403
    invoke-static {v3, v2}, Ljava/lang/Math;->min(II)I

    .line 404
    .line 405
    .line 406
    move-result v2

    .line 407
    goto :goto_11

    .line 408
    :cond_1c
    move v2, v3

    .line 409
    :goto_11
    invoke-static {v10, v2}, Ljava/lang/Math;->max(II)I

    .line 410
    .line 411
    .line 412
    move-result v2

    .line 413
    if-lez v14, :cond_1d

    .line 414
    .line 415
    invoke-static {v14, v2}, Ljava/lang/Math;->min(II)I

    .line 416
    .line 417
    .line 418
    move-result v2

    .line 419
    :cond_1d
    if-eq v2, v3, :cond_1e

    .line 420
    .line 421
    add-int/lit8 v13, v13, 0x1

    .line 422
    .line 423
    move v3, v2

    .line 424
    :cond_1e
    invoke-virtual {v1, v3}, Lt/g;->d(I)V

    .line 425
    .line 426
    .line 427
    :goto_12
    add-int/lit8 v12, v12, 0x1

    .line 428
    .line 429
    move/from16 v10, v17

    .line 430
    .line 431
    move/from16 v14, v21

    .line 432
    .line 433
    move/from16 v1, v22

    .line 434
    .line 435
    move/from16 v3, v24

    .line 436
    .line 437
    move/from16 v8, v25

    .line 438
    .line 439
    const/high16 v2, 0x3f000000    # 0.5f

    .line 440
    .line 441
    move-object/from16 v21, v15

    .line 442
    .line 443
    goto/16 :goto_e

    .line 444
    .line 445
    :cond_1f
    move/from16 v22, v1

    .line 446
    .line 447
    move/from16 v24, v3

    .line 448
    .line 449
    move-object/from16 v15, v21

    .line 450
    .line 451
    move/from16 v21, v14

    .line 452
    .line 453
    if-lez v13, :cond_24

    .line 454
    .line 455
    sub-int/2addr v5, v13

    .line 456
    const/4 v1, 0x0

    .line 457
    const/4 v14, 0x0

    .line 458
    :goto_13
    if-ge v1, v7, :cond_23

    .line 459
    .line 460
    invoke-virtual {v15, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    .line 461
    .line 462
    .line 463
    move-result-object v2

    .line 464
    check-cast v2, Lt/o;

    .line 465
    .line 466
    iget-object v3, v2, Lt/o;->b:Ls/d;

    .line 467
    .line 468
    iget v3, v3, Ls/d;->f0:I

    .line 469
    .line 470
    const/16 v8, 0x8

    .line 471
    .line 472
    if-ne v3, v8, :cond_20

    .line 473
    .line 474
    move/from16 v8, v23

    .line 475
    .line 476
    goto :goto_14

    .line 477
    :cond_20
    move/from16 v8, v23

    .line 478
    .line 479
    if-lez v1, :cond_21

    .line 480
    .line 481
    if-lt v1, v8, :cond_21

    .line 482
    .line 483
    iget-object v3, v2, Lt/o;->h:Lt/f;

    .line 484
    .line 485
    iget v3, v3, Lt/f;->f:I

    .line 486
    .line 487
    add-int/2addr v14, v3

    .line 488
    :cond_21
    iget-object v3, v2, Lt/o;->e:Lt/g;

    .line 489
    .line 490
    iget v3, v3, Lt/f;->g:I

    .line 491
    .line 492
    add-int/2addr v14, v3

    .line 493
    if-ge v1, v11, :cond_22

    .line 494
    .line 495
    if-ge v1, v9, :cond_22

    .line 496
    .line 497
    iget-object v2, v2, Lt/o;->i:Lt/f;

    .line 498
    .line 499
    iget v2, v2, Lt/f;->f:I

    .line 500
    .line 501
    neg-int v2, v2

    .line 502
    add-int/2addr v14, v2

    .line 503
    :cond_22
    :goto_14
    add-int/lit8 v1, v1, 0x1

    .line 504
    .line 505
    move/from16 v23, v8

    .line 506
    .line 507
    goto :goto_13

    .line 508
    :cond_23
    move/from16 v8, v23

    .line 509
    .line 510
    goto :goto_15

    .line 511
    :cond_24
    move/from16 v8, v23

    .line 512
    .line 513
    move/from16 v14, v21

    .line 514
    .line 515
    :goto_15
    iget v1, v0, Lt/c;->l:I

    .line 516
    .line 517
    const/4 v2, 0x2

    .line 518
    if-ne v1, v2, :cond_25

    .line 519
    .line 520
    if-nez v13, :cond_25

    .line 521
    .line 522
    const/4 v1, 0x0

    .line 523
    iput v1, v0, Lt/c;->l:I

    .line 524
    .line 525
    goto :goto_16

    .line 526
    :cond_25
    const/4 v1, 0x0

    .line 527
    goto :goto_16

    .line 528
    :cond_26
    move/from16 v22, v1

    .line 529
    .line 530
    move/from16 v24, v3

    .line 531
    .line 532
    move-object/from16 v15, v21

    .line 533
    .line 534
    move/from16 v8, v23

    .line 535
    .line 536
    const/4 v1, 0x0

    .line 537
    const/4 v2, 0x2

    .line 538
    move/from16 v21, v14

    .line 539
    .line 540
    :goto_16
    if-le v14, v4, :cond_27

    .line 541
    .line 542
    iput v2, v0, Lt/c;->l:I

    .line 543
    .line 544
    :cond_27
    if-lez v6, :cond_28

    .line 545
    .line 546
    if-nez v5, :cond_28

    .line 547
    .line 548
    if-ne v8, v9, :cond_28

    .line 549
    .line 550
    iput v2, v0, Lt/c;->l:I

    .line 551
    .line 552
    :cond_28
    iget v2, v0, Lt/c;->l:I

    .line 553
    .line 554
    const/4 v3, 0x1

    .line 555
    if-ne v2, v3, :cond_38

    .line 556
    .line 557
    if-le v6, v3, :cond_29

    .line 558
    .line 559
    sub-int/2addr v4, v14

    .line 560
    sub-int/2addr v6, v3

    .line 561
    div-int/2addr v4, v6

    .line 562
    goto :goto_17

    .line 563
    :cond_29
    if-ne v6, v3, :cond_2a

    .line 564
    .line 565
    sub-int/2addr v4, v14

    .line 566
    const/4 v2, 0x2

    .line 567
    div-int/2addr v4, v2

    .line 568
    goto :goto_17

    .line 569
    :cond_2a
    move v4, v1

    .line 570
    :goto_17
    if-lez v5, :cond_2b

    .line 571
    .line 572
    move v4, v1

    .line 573
    :cond_2b
    move v5, v1

    .line 574
    move/from16 v1, v22

    .line 575
    .line 576
    :goto_18
    if-ge v5, v7, :cond_56

    .line 577
    .line 578
    if-eqz v24, :cond_2c

    .line 579
    .line 580
    add-int/lit8 v2, v5, 0x1

    .line 581
    .line 582
    sub-int v2, v7, v2

    .line 583
    .line 584
    goto :goto_19

    .line 585
    :cond_2c
    move v2, v5

    .line 586
    :goto_19
    invoke-virtual {v15, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    .line 587
    .line 588
    .line 589
    move-result-object v2

    .line 590
    check-cast v2, Lt/o;

    .line 591
    .line 592
    iget-object v3, v2, Lt/o;->b:Ls/d;

    .line 593
    .line 594
    iget v3, v3, Ls/d;->f0:I

    .line 595
    .line 596
    iget-object v6, v2, Lt/o;->i:Lt/f;

    .line 597
    .line 598
    iget-object v10, v2, Lt/o;->h:Lt/f;

    .line 599
    .line 600
    const/16 v12, 0x8

    .line 601
    .line 602
    if-ne v3, v12, :cond_2d

    .line 603
    .line 604
    invoke-virtual {v10, v1}, Lt/f;->d(I)V

    .line 605
    .line 606
    .line 607
    invoke-virtual {v6, v1}, Lt/f;->d(I)V

    .line 608
    .line 609
    .line 610
    goto :goto_20

    .line 611
    :cond_2d
    if-lez v5, :cond_2f

    .line 612
    .line 613
    if-eqz v24, :cond_2e

    .line 614
    .line 615
    sub-int/2addr v1, v4

    .line 616
    goto :goto_1a

    .line 617
    :cond_2e
    add-int/2addr v1, v4

    .line 618
    :cond_2f
    :goto_1a
    if-lez v5, :cond_31

    .line 619
    .line 620
    if-lt v5, v8, :cond_31

    .line 621
    .line 622
    if-eqz v24, :cond_30

    .line 623
    .line 624
    iget v3, v10, Lt/f;->f:I

    .line 625
    .line 626
    sub-int/2addr v1, v3

    .line 627
    goto :goto_1b

    .line 628
    :cond_30
    iget v3, v10, Lt/f;->f:I

    .line 629
    .line 630
    add-int/2addr v1, v3

    .line 631
    :cond_31
    :goto_1b
    if-eqz v24, :cond_32

    .line 632
    .line 633
    invoke-virtual {v6, v1}, Lt/f;->d(I)V

    .line 634
    .line 635
    .line 636
    goto :goto_1c

    .line 637
    :cond_32
    invoke-virtual {v10, v1}, Lt/f;->d(I)V

    .line 638
    .line 639
    .line 640
    :goto_1c
    iget-object v3, v2, Lt/o;->e:Lt/g;

    .line 641
    .line 642
    iget v12, v3, Lt/f;->g:I

    .line 643
    .line 644
    iget v13, v2, Lt/o;->d:I

    .line 645
    .line 646
    const/4 v14, 0x3

    .line 647
    if-ne v13, v14, :cond_33

    .line 648
    .line 649
    iget v13, v2, Lt/o;->a:I

    .line 650
    .line 651
    const/4 v14, 0x1

    .line 652
    if-ne v13, v14, :cond_33

    .line 653
    .line 654
    iget v12, v3, Lt/g;->m:I

    .line 655
    .line 656
    :cond_33
    if-eqz v24, :cond_34

    .line 657
    .line 658
    sub-int/2addr v1, v12

    .line 659
    goto :goto_1d

    .line 660
    :cond_34
    add-int/2addr v1, v12

    .line 661
    :goto_1d
    if-eqz v24, :cond_35

    .line 662
    .line 663
    invoke-virtual {v10, v1}, Lt/f;->d(I)V

    .line 664
    .line 665
    .line 666
    :goto_1e
    const/4 v3, 0x1

    .line 667
    goto :goto_1f

    .line 668
    :cond_35
    invoke-virtual {v6, v1}, Lt/f;->d(I)V

    .line 669
    .line 670
    .line 671
    goto :goto_1e

    .line 672
    :goto_1f
    iput-boolean v3, v2, Lt/o;->g:Z

    .line 673
    .line 674
    if-ge v5, v11, :cond_37

    .line 675
    .line 676
    if-ge v5, v9, :cond_37

    .line 677
    .line 678
    if-eqz v24, :cond_36

    .line 679
    .line 680
    iget v2, v6, Lt/f;->f:I

    .line 681
    .line 682
    neg-int v2, v2

    .line 683
    sub-int/2addr v1, v2

    .line 684
    goto :goto_20

    .line 685
    :cond_36
    iget v2, v6, Lt/f;->f:I

    .line 686
    .line 687
    neg-int v2, v2

    .line 688
    add-int/2addr v1, v2

    .line 689
    :cond_37
    :goto_20
    add-int/lit8 v5, v5, 0x1

    .line 690
    .line 691
    goto :goto_18

    .line 692
    :cond_38
    if-nez v2, :cond_45

    .line 693
    .line 694
    sub-int/2addr v4, v14

    .line 695
    const/4 v2, 0x1

    .line 696
    add-int/2addr v6, v2

    .line 697
    div-int/2addr v4, v6

    .line 698
    if-lez v5, :cond_39

    .line 699
    .line 700
    move v4, v1

    .line 701
    :cond_39
    move v5, v1

    .line 702
    move/from16 v1, v22

    .line 703
    .line 704
    :goto_21
    if-ge v5, v7, :cond_56

    .line 705
    .line 706
    if-eqz v24, :cond_3a

    .line 707
    .line 708
    add-int/lit8 v2, v5, 0x1

    .line 709
    .line 710
    sub-int v2, v7, v2

    .line 711
    .line 712
    goto :goto_22

    .line 713
    :cond_3a
    move v2, v5

    .line 714
    :goto_22
    invoke-virtual {v15, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    .line 715
    .line 716
    .line 717
    move-result-object v2

    .line 718
    check-cast v2, Lt/o;

    .line 719
    .line 720
    iget-object v3, v2, Lt/o;->b:Ls/d;

    .line 721
    .line 722
    iget v3, v3, Ls/d;->f0:I

    .line 723
    .line 724
    iget-object v6, v2, Lt/o;->i:Lt/f;

    .line 725
    .line 726
    iget-object v10, v2, Lt/o;->h:Lt/f;

    .line 727
    .line 728
    const/16 v12, 0x8

    .line 729
    .line 730
    if-ne v3, v12, :cond_3b

    .line 731
    .line 732
    invoke-virtual {v10, v1}, Lt/f;->d(I)V

    .line 733
    .line 734
    .line 735
    invoke-virtual {v6, v1}, Lt/f;->d(I)V

    .line 736
    .line 737
    .line 738
    goto :goto_28

    .line 739
    :cond_3b
    if-eqz v24, :cond_3c

    .line 740
    .line 741
    sub-int/2addr v1, v4

    .line 742
    goto :goto_23

    .line 743
    :cond_3c
    add-int/2addr v1, v4

    .line 744
    :goto_23
    if-lez v5, :cond_3e

    .line 745
    .line 746
    if-lt v5, v8, :cond_3e

    .line 747
    .line 748
    if-eqz v24, :cond_3d

    .line 749
    .line 750
    iget v3, v10, Lt/f;->f:I

    .line 751
    .line 752
    sub-int/2addr v1, v3

    .line 753
    goto :goto_24

    .line 754
    :cond_3d
    iget v3, v10, Lt/f;->f:I

    .line 755
    .line 756
    add-int/2addr v1, v3

    .line 757
    :cond_3e
    :goto_24
    if-eqz v24, :cond_3f

    .line 758
    .line 759
    invoke-virtual {v6, v1}, Lt/f;->d(I)V

    .line 760
    .line 761
    .line 762
    goto :goto_25

    .line 763
    :cond_3f
    invoke-virtual {v10, v1}, Lt/f;->d(I)V

    .line 764
    .line 765
    .line 766
    :goto_25
    iget-object v3, v2, Lt/o;->e:Lt/g;

    .line 767
    .line 768
    iget v12, v3, Lt/f;->g:I

    .line 769
    .line 770
    iget v13, v2, Lt/o;->d:I

    .line 771
    .line 772
    const/4 v14, 0x3

    .line 773
    if-ne v13, v14, :cond_40

    .line 774
    .line 775
    iget v2, v2, Lt/o;->a:I

    .line 776
    .line 777
    const/4 v13, 0x1

    .line 778
    if-ne v2, v13, :cond_40

    .line 779
    .line 780
    iget v2, v3, Lt/g;->m:I

    .line 781
    .line 782
    invoke-static {v12, v2}, Ljava/lang/Math;->min(II)I

    .line 783
    .line 784
    .line 785
    move-result v12

    .line 786
    :cond_40
    if-eqz v24, :cond_41

    .line 787
    .line 788
    sub-int/2addr v1, v12

    .line 789
    goto :goto_26

    .line 790
    :cond_41
    add-int/2addr v1, v12

    .line 791
    :goto_26
    if-eqz v24, :cond_42

    .line 792
    .line 793
    invoke-virtual {v10, v1}, Lt/f;->d(I)V

    .line 794
    .line 795
    .line 796
    goto :goto_27

    .line 797
    :cond_42
    invoke-virtual {v6, v1}, Lt/f;->d(I)V

    .line 798
    .line 799
    .line 800
    :goto_27
    if-ge v5, v11, :cond_44

    .line 801
    .line 802
    if-ge v5, v9, :cond_44

    .line 803
    .line 804
    if-eqz v24, :cond_43

    .line 805
    .line 806
    iget v2, v6, Lt/f;->f:I

    .line 807
    .line 808
    neg-int v2, v2

    .line 809
    sub-int/2addr v1, v2

    .line 810
    goto :goto_28

    .line 811
    :cond_43
    iget v2, v6, Lt/f;->f:I

    .line 812
    .line 813
    neg-int v2, v2

    .line 814
    add-int/2addr v1, v2

    .line 815
    :cond_44
    :goto_28
    add-int/lit8 v5, v5, 0x1

    .line 816
    .line 817
    goto :goto_21

    .line 818
    :cond_45
    const/4 v3, 0x2

    .line 819
    if-ne v2, v3, :cond_56

    .line 820
    .line 821
    iget v2, v0, Lt/o;->f:I

    .line 822
    .line 823
    if-nez v2, :cond_46

    .line 824
    .line 825
    iget-object v2, v0, Lt/o;->b:Ls/d;

    .line 826
    .line 827
    iget v2, v2, Ls/d;->c0:F

    .line 828
    .line 829
    goto :goto_29

    .line 830
    :cond_46
    iget-object v2, v0, Lt/o;->b:Ls/d;

    .line 831
    .line 832
    iget v2, v2, Ls/d;->d0:F

    .line 833
    .line 834
    :goto_29
    if-eqz v24, :cond_47

    .line 835
    .line 836
    const/high16 v3, 0x3f800000    # 1.0f

    .line 837
    .line 838
    sub-float v2, v3, v2

    .line 839
    .line 840
    :cond_47
    sub-int/2addr v4, v14

    .line 841
    int-to-float v3, v4

    .line 842
    mul-float/2addr v3, v2

    .line 843
    const/high16 v2, 0x3f000000    # 0.5f

    .line 844
    .line 845
    add-float/2addr v3, v2

    .line 846
    float-to-int v2, v3

    .line 847
    if-ltz v2, :cond_48

    .line 848
    .line 849
    if-lez v5, :cond_49

    .line 850
    .line 851
    :cond_48
    move v2, v1

    .line 852
    :cond_49
    if-eqz v24, :cond_4a

    .line 853
    .line 854
    sub-int v2, v22, v2

    .line 855
    .line 856
    goto :goto_2a

    .line 857
    :cond_4a
    add-int v2, v22, v2

    .line 858
    .line 859
    :goto_2a
    move v5, v1

    .line 860
    :goto_2b
    if-ge v5, v7, :cond_56

    .line 861
    .line 862
    if-eqz v24, :cond_4b

    .line 863
    .line 864
    add-int/lit8 v1, v5, 0x1

    .line 865
    .line 866
    sub-int v1, v7, v1

    .line 867
    .line 868
    goto :goto_2c

    .line 869
    :cond_4b
    move v1, v5

    .line 870
    :goto_2c
    invoke-virtual {v15, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    .line 871
    .line 872
    .line 873
    move-result-object v1

    .line 874
    check-cast v1, Lt/o;

    .line 875
    .line 876
    iget-object v3, v1, Lt/o;->b:Ls/d;

    .line 877
    .line 878
    iget v3, v3, Ls/d;->f0:I

    .line 879
    .line 880
    iget-object v4, v1, Lt/o;->i:Lt/f;

    .line 881
    .line 882
    iget-object v6, v1, Lt/o;->h:Lt/f;

    .line 883
    .line 884
    const/16 v10, 0x8

    .line 885
    .line 886
    if-ne v3, v10, :cond_4c

    .line 887
    .line 888
    invoke-virtual {v6, v2}, Lt/f;->d(I)V

    .line 889
    .line 890
    .line 891
    invoke-virtual {v4, v2}, Lt/f;->d(I)V

    .line 892
    .line 893
    .line 894
    const/4 v13, 0x1

    .line 895
    const/4 v14, 0x3

    .line 896
    goto :goto_32

    .line 897
    :cond_4c
    if-lez v5, :cond_4e

    .line 898
    .line 899
    if-lt v5, v8, :cond_4e

    .line 900
    .line 901
    if-eqz v24, :cond_4d

    .line 902
    .line 903
    iget v3, v6, Lt/f;->f:I

    .line 904
    .line 905
    sub-int/2addr v2, v3

    .line 906
    goto :goto_2d

    .line 907
    :cond_4d
    iget v3, v6, Lt/f;->f:I

    .line 908
    .line 909
    add-int/2addr v2, v3

    .line 910
    :cond_4e
    :goto_2d
    if-eqz v24, :cond_4f

    .line 911
    .line 912
    invoke-virtual {v4, v2}, Lt/f;->d(I)V

    .line 913
    .line 914
    .line 915
    goto :goto_2e

    .line 916
    :cond_4f
    invoke-virtual {v6, v2}, Lt/f;->d(I)V

    .line 917
    .line 918
    .line 919
    :goto_2e
    iget-object v3, v1, Lt/o;->e:Lt/g;

    .line 920
    .line 921
    iget v12, v3, Lt/f;->g:I

    .line 922
    .line 923
    iget v13, v1, Lt/o;->d:I

    .line 924
    .line 925
    const/4 v14, 0x3

    .line 926
    if-ne v13, v14, :cond_50

    .line 927
    .line 928
    iget v1, v1, Lt/o;->a:I

    .line 929
    .line 930
    const/4 v13, 0x1

    .line 931
    if-ne v1, v13, :cond_51

    .line 932
    .line 933
    iget v12, v3, Lt/g;->m:I

    .line 934
    .line 935
    goto :goto_2f

    .line 936
    :cond_50
    const/4 v13, 0x1

    .line 937
    :cond_51
    :goto_2f
    if-eqz v24, :cond_52

    .line 938
    .line 939
    sub-int/2addr v2, v12

    .line 940
    goto :goto_30

    .line 941
    :cond_52
    add-int/2addr v2, v12

    .line 942
    :goto_30
    if-eqz v24, :cond_53

    .line 943
    .line 944
    invoke-virtual {v6, v2}, Lt/f;->d(I)V

    .line 945
    .line 946
    .line 947
    goto :goto_31

    .line 948
    :cond_53
    invoke-virtual {v4, v2}, Lt/f;->d(I)V

    .line 949
    .line 950
    .line 951
    :goto_31
    if-ge v5, v11, :cond_55

    .line 952
    .line 953
    if-ge v5, v9, :cond_55

    .line 954
    .line 955
    if-eqz v24, :cond_54

    .line 956
    .line 957
    iget v1, v4, Lt/f;->f:I

    .line 958
    .line 959
    neg-int v1, v1

    .line 960
    sub-int/2addr v2, v1

    .line 961
    goto :goto_32

    .line 962
    :cond_54
    iget v1, v4, Lt/f;->f:I

    .line 963
    .line 964
    neg-int v1, v1

    .line 965
    add-int/2addr v2, v1

    .line 966
    :cond_55
    :goto_32
    add-int/lit8 v5, v5, 0x1

    .line 967
    .line 968
    goto :goto_2b

    .line 969
    :cond_56
    :goto_33
    return-void
    .line 970
    .line 971
    .line 972
    .line 973
    .line 974
    .line 975
    .line 976
    .line 977
    .line 978
    .line 979
    .line 980
.end method

.method public final d()V
    .locals 7

    .line 1
    iget-object v0, p0, Lt/c;->k:Ljava/util/ArrayList;

    .line 2
    .line 3
    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    .line 4
    .line 5
    .line 6
    move-result-object v1

    .line 7
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    .line 8
    .line 9
    .line 10
    move-result v2

    .line 11
    if-eqz v2, :cond_0

    .line 12
    .line 13
    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    .line 14
    .line 15
    .line 16
    move-result-object v2

    .line 17
    check-cast v2, Lt/o;

    .line 18
    .line 19
    invoke-virtual {v2}, Lt/o;->d()V

    .line 20
    .line 21
    .line 22
    goto :goto_0

    .line 23
    :cond_0
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    .line 24
    .line 25
    .line 26
    move-result v1

    .line 27
    const/4 v2, 0x1

    .line 28
    if-ge v1, v2, :cond_1

    .line 29
    .line 30
    return-void

    .line 31
    :cond_1
    const/4 v3, 0x0

    .line 32
    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    .line 33
    .line 34
    .line 35
    move-result-object v4

    .line 36
    check-cast v4, Lt/o;

    .line 37
    .line 38
    iget-object v4, v4, Lt/o;->b:Ls/d;

    .line 39
    .line 40
    sub-int/2addr v1, v2

    .line 41
    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    .line 42
    .line 43
    .line 44
    move-result-object v0

    .line 45
    check-cast v0, Lt/o;

    .line 46
    .line 47
    iget-object v0, v0, Lt/o;->b:Ls/d;

    .line 48
    .line 49
    iget v1, p0, Lt/o;->f:I

    .line 50
    .line 51
    iget-object v5, p0, Lt/o;->i:Lt/f;

    .line 52
    .line 53
    iget-object v6, p0, Lt/o;->h:Lt/f;

    .line 54
    .line 55
    if-nez v1, :cond_5

    .line 56
    .line 57
    iget-object v1, v4, Ls/d;->H:Ls/c;

    .line 58
    .line 59
    iget-object v0, v0, Ls/d;->J:Ls/c;

    .line 60
    .line 61
    invoke-static {v1, v3}, Lt/o;->i(Ls/c;I)Lt/f;

    .line 62
    .line 63
    .line 64
    move-result-object v2

    .line 65
    invoke-virtual {v1}, Ls/c;->d()I

    .line 66
    .line 67
    .line 68
    move-result v1

    .line 69
    invoke-virtual {p0}, Lt/c;->m()Ls/d;

    .line 70
    .line 71
    .line 72
    move-result-object v4

    .line 73
    if-eqz v4, :cond_2

    .line 74
    .line 75
    iget-object v1, v4, Ls/d;->H:Ls/c;

    .line 76
    .line 77
    invoke-virtual {v1}, Ls/c;->d()I

    .line 78
    .line 79
    .line 80
    move-result v1

    .line 81
    :cond_2
    if-eqz v2, :cond_3

    .line 82
    .line 83
    invoke-static {v6, v2, v1}, Lt/o;->b(Lt/f;Lt/f;I)V

    .line 84
    .line 85
    .line 86
    :cond_3
    invoke-static {v0, v3}, Lt/o;->i(Ls/c;I)Lt/f;

    .line 87
    .line 88
    .line 89
    move-result-object v1

    .line 90
    invoke-virtual {v0}, Ls/c;->d()I

    .line 91
    .line 92
    .line 93
    move-result v0

    .line 94
    invoke-virtual {p0}, Lt/c;->n()Ls/d;

    .line 95
    .line 96
    .line 97
    move-result-object v2

    .line 98
    if-eqz v2, :cond_4

    .line 99
    .line 100
    iget-object v0, v2, Ls/d;->J:Ls/c;

    .line 101
    .line 102
    invoke-virtual {v0}, Ls/c;->d()I

    .line 103
    .line 104
    .line 105
    move-result v0

    .line 106
    :cond_4
    if-eqz v1, :cond_9

    .line 107
    .line 108
    neg-int v0, v0

    .line 109
    invoke-static {v5, v1, v0}, Lt/o;->b(Lt/f;Lt/f;I)V

    .line 110
    .line 111
    .line 112
    goto :goto_1

    .line 113
    :cond_5
    iget-object v1, v4, Ls/d;->I:Ls/c;

    .line 114
    .line 115
    iget-object v0, v0, Ls/d;->K:Ls/c;

    .line 116
    .line 117
    invoke-static {v1, v2}, Lt/o;->i(Ls/c;I)Lt/f;

    .line 118
    .line 119
    .line 120
    move-result-object v3

    .line 121
    invoke-virtual {v1}, Ls/c;->d()I

    .line 122
    .line 123
    .line 124
    move-result v1

    .line 125
    invoke-virtual {p0}, Lt/c;->m()Ls/d;

    .line 126
    .line 127
    .line 128
    move-result-object v4

    .line 129
    if-eqz v4, :cond_6

    .line 130
    .line 131
    iget-object v1, v4, Ls/d;->I:Ls/c;

    .line 132
    .line 133
    invoke-virtual {v1}, Ls/c;->d()I

    .line 134
    .line 135
    .line 136
    move-result v1

    .line 137
    :cond_6
    if-eqz v3, :cond_7

    .line 138
    .line 139
    invoke-static {v6, v3, v1}, Lt/o;->b(Lt/f;Lt/f;I)V

    .line 140
    .line 141
    .line 142
    :cond_7
    invoke-static {v0, v2}, Lt/o;->i(Ls/c;I)Lt/f;

    .line 143
    .line 144
    .line 145
    move-result-object v1

    .line 146
    invoke-virtual {v0}, Ls/c;->d()I

    .line 147
    .line 148
    .line 149
    move-result v0

    .line 150
    invoke-virtual {p0}, Lt/c;->n()Ls/d;

    .line 151
    .line 152
    .line 153
    move-result-object v2

    .line 154
    if-eqz v2, :cond_8

    .line 155
    .line 156
    iget-object v0, v2, Ls/d;->K:Ls/c;

    .line 157
    .line 158
    invoke-virtual {v0}, Ls/c;->d()I

    .line 159
    .line 160
    .line 161
    move-result v0

    .line 162
    :cond_8
    if-eqz v1, :cond_9

    .line 163
    .line 164
    neg-int v0, v0

    .line 165
    invoke-static {v5, v1, v0}, Lt/o;->b(Lt/f;Lt/f;I)V

    .line 166
    .line 167
    .line 168
    :cond_9
    :goto_1
    iput-object p0, v6, Lt/f;->a:Lt/o;

    .line 169
    .line 170
    iput-object p0, v5, Lt/f;->a:Lt/o;

    .line 171
    .line 172
    return-void
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
    .line 277
    .line 278
    .line 279
    .line 280
    .line 281
    .line 282
    .line 283
    .line 284
    .line 285
    .line 286
    .line 287
    .line 288
    .line 289
    .line 290
    .line 291
    .line 292
    .line 293
    .line 294
    .line 295
    .line 296
    .line 297
    .line 298
    .line 299
    .line 300
    .line 301
    .line 302
    .line 303
    .line 304
    .line 305
    .line 306
    .line 307
    .line 308
    .line 309
    .line 310
    .line 311
    .line 312
    .line 313
    .line 314
    .line 315
    .line 316
    .line 317
    .line 318
    .line 319
    .line 320
    .line 321
    .line 322
    .line 323
    .line 324
    .line 325
    .line 326
    .line 327
    .line 328
    .line 329
    .line 330
    .line 331
    .line 332
    .line 333
    .line 334
    .line 335
    .line 336
    .line 337
    .line 338
    .line 339
    .line 340
    .line 341
    .line 342
    .line 343
    .line 344
    .line 345
    .line 346
    .line 347
    .line 348
    .line 349
    .line 350
    .line 351
    .line 352
    .line 353
    .line 354
    .line 355
    .line 356
    .line 357
    .line 358
    .line 359
    .line 360
    .line 361
    .line 362
    .line 363
    .line 364
    .line 365
    .line 366
    .line 367
    .line 368
    .line 369
    .line 370
    .line 371
    .line 372
    .line 373
    .line 374
    .line 375
    .line 376
    .line 377
    .line 378
    .line 379
    .line 380
    .line 381
    .line 382
    .line 383
    .line 384
    .line 385
    .line 386
    .line 387
    .line 388
    .line 389
    .line 390
    .line 391
    .line 392
    .line 393
    .line 394
    .line 395
    .line 396
    .line 397
    .line 398
    .line 399
    .line 400
    .line 401
    .line 402
    .line 403
    .line 404
    .line 405
    .line 406
    .line 407
    .line 408
    .line 409
    .line 410
    .line 411
    .line 412
    .line 413
    .line 414
    .line 415
    .line 416
    .line 417
    .line 418
    .line 419
    .line 420
    .line 421
    .line 422
    .line 423
    .line 424
    .line 425
    .line 426
    .line 427
    .line 428
    .line 429
    .line 430
    .line 431
    .line 432
    .line 433
    .line 434
    .line 435
    .line 436
    .line 437
    .line 438
    .line 439
    .line 440
    .line 441
    .line 442
    .line 443
    .line 444
    .line 445
    .line 446
    .line 447
    .line 448
    .line 449
    .line 450
    .line 451
    .line 452
    .line 453
    .line 454
    .line 455
    .line 456
    .line 457
    .line 458
    .line 459
    .line 460
    .line 461
    .line 462
    .line 463
    .line 464
    .line 465
    .line 466
    .line 467
    .line 468
    .line 469
    .line 470
    .line 471
    .line 472
    .line 473
    .line 474
    .line 475
    .line 476
    .line 477
    .line 478
    .line 479
    .line 480
    .line 481
    .line 482
    .line 483
    .line 484
    .line 485
    .line 486
    .line 487
    .line 488
    .line 489
    .line 490
    .line 491
    .line 492
    .line 493
    .line 494
    .line 495
    .line 496
    .line 497
    .line 498
    .line 499
    .line 500
    .line 501
    .line 502
    .line 503
    .line 504
    .line 505
    .line 506
    .line 507
    .line 508
    .line 509
    .line 510
    .line 511
    .line 512
    .line 513
    .line 514
    .line 515
    .line 516
    .line 517
    .line 518
    .line 519
    .line 520
    .line 521
    .line 522
    .line 523
    .line 524
    .line 525
    .line 526
    .line 527
    .line 528
    .line 529
    .line 530
    .line 531
    .line 532
    .line 533
    .line 534
    .line 535
    .line 536
    .line 537
    .line 538
    .line 539
    .line 540
    .line 541
    .line 542
    .line 543
    .line 544
    .line 545
    .line 546
    .line 547
    .line 548
    .line 549
    .line 550
    .line 551
    .line 552
    .line 553
    .line 554
    .line 555
    .line 556
    .line 557
    .line 558
    .line 559
    .line 560
    .line 561
    .line 562
    .line 563
    .line 564
    .line 565
    .line 566
    .line 567
    .line 568
    .line 569
    .line 570
    .line 571
    .line 572
    .line 573
    .line 574
    .line 575
    .line 576
    .line 577
    .line 578
    .line 579
    .line 580
    .line 581
    .line 582
    .line 583
    .line 584
    .line 585
    .line 586
    .line 587
    .line 588
    .line 589
    .line 590
    .line 591
    .line 592
    .line 593
    .line 594
    .line 595
    .line 596
    .line 597
    .line 598
    .line 599
    .line 600
    .line 601
    .line 602
    .line 603
    .line 604
    .line 605
    .line 606
    .line 607
    .line 608
    .line 609
    .line 610
    .line 611
    .line 612
    .line 613
    .line 614
    .line 615
    .line 616
    .line 617
    .line 618
    .line 619
    .line 620
    .line 621
    .line 622
    .line 623
    .line 624
    .line 625
    .line 626
    .line 627
    .line 628
    .line 629
    .line 630
    .line 631
    .line 632
    .line 633
    .line 634
    .line 635
    .line 636
    .line 637
    .line 638
    .line 639
    .line 640
    .line 641
    .line 642
    .line 643
    .line 644
    .line 645
    .line 646
    .line 647
    .line 648
    .line 649
    .line 650
    .line 651
    .line 652
    .line 653
    .line 654
    .line 655
    .line 656
    .line 657
    .line 658
    .line 659
    .line 660
    .line 661
    .line 662
    .line 663
    .line 664
    .line 665
    .line 666
    .line 667
    .line 668
    .line 669
    .line 670
    .line 671
    .line 672
    .line 673
    .line 674
    .line 675
    .line 676
    .line 677
    .line 678
    .line 679
    .line 680
    .line 681
    .line 682
    .line 683
    .line 684
    .line 685
    .line 686
    .line 687
    .line 688
    .line 689
    .line 690
    .line 691
    .line 692
    .line 693
    .line 694
    .line 695
    .line 696
    .line 697
    .line 698
    .line 699
    .line 700
    .line 701
    .line 702
    .line 703
    .line 704
    .line 705
    .line 706
    .line 707
    .line 708
    .line 709
    .line 710
    .line 711
    .line 712
    .line 713
    .line 714
    .line 715
    .line 716
    .line 717
    .line 718
    .line 719
    .line 720
    .line 721
    .line 722
    .line 723
    .line 724
    .line 725
    .line 726
    .line 727
    .line 728
    .line 729
    .line 730
    .line 731
    .line 732
    .line 733
    .line 734
    .line 735
    .line 736
    .line 737
    .line 738
    .line 739
    .line 740
    .line 741
    .line 742
    .line 743
    .line 744
    .line 745
    .line 746
    .line 747
    .line 748
    .line 749
    .line 750
    .line 751
    .line 752
    .line 753
    .line 754
    .line 755
    .line 756
    .line 757
    .line 758
    .line 759
    .line 760
    .line 761
    .line 762
    .line 763
    .line 764
    .line 765
    .line 766
    .line 767
    .line 768
    .line 769
    .line 770
    .line 771
    .line 772
    .line 773
    .line 774
    .line 775
    .line 776
    .line 777
    .line 778
    .line 779
    .line 780
    .line 781
    .line 782
    .line 783
    .line 784
    .line 785
    .line 786
    .line 787
    .line 788
    .line 789
    .line 790
    .line 791
    .line 792
    .line 793
    .line 794
    .line 795
    .line 796
    .line 797
    .line 798
    .line 799
    .line 800
    .line 801
    .line 802
    .line 803
    .line 804
    .line 805
    .line 806
    .line 807
    .line 808
    .line 809
    .line 810
    .line 811
    .line 812
    .line 813
    .line 814
    .line 815
    .line 816
    .line 817
    .line 818
    .line 819
    .line 820
    .line 821
    .line 822
    .line 823
    .line 824
    .line 825
    .line 826
    .line 827
    .line 828
    .line 829
    .line 830
    .line 831
    .line 832
    .line 833
    .line 834
    .line 835
    .line 836
    .line 837
    .line 838
    .line 839
    .line 840
    .line 841
    .line 842
    .line 843
    .line 844
    .line 845
    .line 846
    .line 847
    .line 848
    .line 849
    .line 850
    .line 851
    .line 852
    .line 853
    .line 854
    .line 855
    .line 856
    .line 857
    .line 858
    .line 859
    .line 860
    .line 861
    .line 862
    .line 863
    .line 864
    .line 865
    .line 866
    .line 867
    .line 868
    .line 869
    .line 870
    .line 871
    .line 872
    .line 873
    .line 874
    .line 875
    .line 876
    .line 877
    .line 878
    .line 879
    .line 880
    .line 881
    .line 882
    .line 883
    .line 884
    .line 885
    .line 886
    .line 887
    .line 888
    .line 889
    .line 890
    .line 891
    .line 892
    .line 893
    .line 894
    .line 895
    .line 896
    .line 897
    .line 898
    .line 899
    .line 900
    .line 901
    .line 902
    .line 903
    .line 904
    .line 905
    .line 906
    .line 907
    .line 908
    .line 909
    .line 910
    .line 911
    .line 912
    .line 913
    .line 914
    .line 915
    .line 916
    .line 917
    .line 918
    .line 919
    .line 920
    .line 921
    .line 922
    .line 923
    .line 924
    .line 925
    .line 926
    .line 927
    .line 928
    .line 929
    .line 930
    .line 931
    .line 932
    .line 933
    .line 934
    .line 935
    .line 936
    .line 937
    .line 938
    .line 939
    .line 940
    .line 941
    .line 942
    .line 943
    .line 944
    .line 945
    .line 946
    .line 947
    .line 948
    .line 949
    .line 950
    .line 951
    .line 952
    .line 953
    .line 954
    .line 955
    .line 956
    .line 957
    .line 958
    .line 959
    .line 960
    .line 961
    .line 962
    .line 963
    .line 964
    .line 965
    .line 966
    .line 967
    .line 968
    .line 969
    .line 970
    .line 971
    .line 972
    .line 973
    .line 974
    .line 975
    .line 976
    .line 977
    .line 978
    .line 979
    .line 980
    .line 981
    .line 982
    .line 983
    .line 984
    .line 985
    .line 986
    .line 987
    .line 988
    .line 989
    .line 990
    .line 991
    .line 992
    .line 993
    .line 994
    .line 995
    .line 996
    .line 997
    .line 998
    .line 999
    .line 1000
    .line 1001
    .line 1002
    .line 1003
    .line 1004
    .line 1005
    .line 1006
    .line 1007
    .line 1008
    .line 1009
    .line 1010
    .line 1011
    .line 1012
    .line 1013
    .line 1014
    .line 1015
    .line 1016
    .line 1017
    .line 1018
    .line 1019
    .line 1020
    .line 1021
    .line 1022
    .line 1023
    .line 1024
    .line 1025
    .line 1026
    .line 1027
    .line 1028
    .line 1029
    .line 1030
    .line 1031
    .line 1032
    .line 1033
    .line 1034
    .line 1035
    .line 1036
    .line 1037
    .line 1038
    .line 1039
    .line 1040
    .line 1041
    .line 1042
    .line 1043
    .line 1044
    .line 1045
    .line 1046
    .line 1047
    .line 1048
    .line 1049
    .line 1050
    .line 1051
    .line 1052
    .line 1053
    .line 1054
    .line 1055
    .line 1056
    .line 1057
    .line 1058
    .line 1059
    .line 1060
    .line 1061
    .line 1062
    .line 1063
    .line 1064
    .line 1065
    .line 1066
    .line 1067
    .line 1068
    .line 1069
    .line 1070
    .line 1071
    .line 1072
    .line 1073
    .line 1074
    .line 1075
    .line 1076
    .line 1077
    .line 1078
    .line 1079
    .line 1080
    .line 1081
    .line 1082
    .line 1083
    .line 1084
    .line 1085
    .line 1086
    .line 1087
    .line 1088
    .line 1089
    .line 1090
    .line 1091
    .line 1092
    .line 1093
    .line 1094
    .line 1095
    .line 1096
    .line 1097
    .line 1098
    .line 1099
    .line 1100
    .line 1101
    .line 1102
    .line 1103
    .line 1104
    .line 1105
    .line 1106
    .line 1107
    .line 1108
    .line 1109
    .line 1110
    .line 1111
    .line 1112
    .line 1113
    .line 1114
    .line 1115
    .line 1116
    .line 1117
    .line 1118
    .line 1119
    .line 1120
    .line 1121
    .line 1122
    .line 1123
    .line 1124
    .line 1125
    .line 1126
    .line 1127
    .line 1128
    .line 1129
    .line 1130
    .line 1131
    .line 1132
    .line 1133
    .line 1134
    .line 1135
    .line 1136
.end method

.method public final e()V
    .locals 3

    .line 1
    const/4 v0, 0x0

    .line 2
    :goto_0
    iget-object v1, p0, Lt/c;->k:Ljava/util/ArrayList;

    .line 3
    .line 4
    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    .line 5
    .line 6
    .line 7
    move-result v2

    .line 8
    if-ge v0, v2, :cond_0

    .line 9
    .line 10
    invoke-virtual {v1, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    .line 11
    .line 12
    .line 13
    move-result-object v1

    .line 14
    check-cast v1, Lt/o;

    .line 15
    .line 16
    invoke-virtual {v1}, Lt/o;->e()V

    .line 17
    .line 18
    .line 19
    add-int/lit8 v0, v0, 0x1

    .line 20
    .line 21
    goto :goto_0

    .line 22
    :cond_0
    return-void
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

.method public final f()V
    .locals 2

    .line 1
    const/4 v0, 0x0

    .line 2
    iput-object v0, p0, Lt/o;->c:Lt/l;

    .line 3
    .line 4
    iget-object v0, p0, Lt/c;->k:Ljava/util/ArrayList;

    .line 5
    .line 6
    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    .line 7
    .line 8
    .line 9
    move-result-object v0

    .line 10
    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    .line 11
    .line 12
    .line 13
    move-result v1

    .line 14
    if-eqz v1, :cond_0

    .line 15
    .line 16
    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    .line 17
    .line 18
    .line 19
    move-result-object v1

    .line 20
    check-cast v1, Lt/o;

    .line 21
    .line 22
    invoke-virtual {v1}, Lt/o;->f()V

    .line 23
    .line 24
    .line 25
    goto :goto_0

    .line 26
    :cond_0
    return-void
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

.method public final j()J
    .locals 8

    .line 1
    iget-object v0, p0, Lt/c;->k:Ljava/util/ArrayList;

    .line 2
    .line 3
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    .line 4
    .line 5
    .line 6
    move-result v1

    .line 7
    const-wide/16 v2, 0x0

    .line 8
    .line 9
    const/4 v4, 0x0

    .line 10
    :goto_0
    if-ge v4, v1, :cond_0

    .line 11
    .line 12
    invoke-virtual {v0, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    .line 13
    .line 14
    .line 15
    move-result-object v5

    .line 16
    check-cast v5, Lt/o;

    .line 17
    .line 18
    iget-object v6, v5, Lt/o;->h:Lt/f;

    .line 19
    .line 20
    iget v6, v6, Lt/f;->f:I

    .line 21
    .line 22
    int-to-long v6, v6

    .line 23
    add-long/2addr v2, v6

    .line 24
    invoke-virtual {v5}, Lt/o;->j()J

    .line 25
    .line 26
    .line 27
    move-result-wide v6

    .line 28
    add-long/2addr v6, v2

    .line 29
    iget-object v2, v5, Lt/o;->i:Lt/f;

    .line 30
    .line 31
    iget v2, v2, Lt/f;->f:I

    .line 32
    .line 33
    int-to-long v2, v2

    .line 34
    add-long/2addr v2, v6

    .line 35
    add-int/lit8 v4, v4, 0x1

    .line 36
    .line 37
    goto :goto_0

    .line 38
    :cond_0
    return-wide v2
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

.method public final k()Z
    .locals 5

    .line 1
    iget-object v0, p0, Lt/c;->k:Ljava/util/ArrayList;

    .line 2
    .line 3
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    .line 4
    .line 5
    .line 6
    move-result v1

    .line 7
    const/4 v2, 0x0

    .line 8
    move v3, v2

    .line 9
    :goto_0
    if-ge v3, v1, :cond_1

    .line 10
    .line 11
    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    .line 12
    .line 13
    .line 14
    move-result-object v4

    .line 15
    check-cast v4, Lt/o;

    .line 16
    .line 17
    invoke-virtual {v4}, Lt/o;->k()Z

    .line 18
    .line 19
    .line 20
    move-result v4

    .line 21
    if-nez v4, :cond_0

    .line 22
    .line 23
    return v2

    .line 24
    :cond_0
    add-int/lit8 v3, v3, 0x1

    .line 25
    .line 26
    goto :goto_0

    .line 27
    :cond_1
    const/4 v0, 0x1

    .line 28
    return v0
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

.method public final m()Ls/d;
    .locals 4

    .line 1
    const/4 v0, 0x0

    .line 2
    :goto_0
    iget-object v1, p0, Lt/c;->k:Ljava/util/ArrayList;

    .line 3
    .line 4
    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    .line 5
    .line 6
    .line 7
    move-result v2

    .line 8
    if-ge v0, v2, :cond_1

    .line 9
    .line 10
    invoke-virtual {v1, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    .line 11
    .line 12
    .line 13
    move-result-object v1

    .line 14
    check-cast v1, Lt/o;

    .line 15
    .line 16
    iget-object v1, v1, Lt/o;->b:Ls/d;

    .line 17
    .line 18
    iget v2, v1, Ls/d;->f0:I

    .line 19
    .line 20
    const/16 v3, 0x8

    .line 21
    .line 22
    if-eq v2, v3, :cond_0

    .line 23
    .line 24
    return-object v1

    .line 25
    :cond_0
    add-int/lit8 v0, v0, 0x1

    .line 26
    .line 27
    goto :goto_0

    .line 28
    :cond_1
    const/4 v0, 0x0

    .line 29
    return-object v0
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

.method public final n()Ls/d;
    .locals 5

    .line 1
    iget-object v0, p0, Lt/c;->k:Ljava/util/ArrayList;

    .line 2
    .line 3
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    .line 4
    .line 5
    .line 6
    move-result v1

    .line 7
    add-int/lit8 v1, v1, -0x1

    .line 8
    .line 9
    :goto_0
    if-ltz v1, :cond_1

    .line 10
    .line 11
    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    .line 12
    .line 13
    .line 14
    move-result-object v2

    .line 15
    check-cast v2, Lt/o;

    .line 16
    .line 17
    iget-object v2, v2, Lt/o;->b:Ls/d;

    .line 18
    .line 19
    iget v3, v2, Ls/d;->f0:I

    .line 20
    .line 21
    const/16 v4, 0x8

    .line 22
    .line 23
    if-eq v3, v4, :cond_0

    .line 24
    .line 25
    return-object v2

    .line 26
    :cond_0
    add-int/lit8 v1, v1, -0x1

    .line 27
    .line 28
    goto :goto_0

    .line 29
    :cond_1
    const/4 v0, 0x0

    .line 30
    return-object v0
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

.method public final toString()Ljava/lang/String;
    .locals 4

    .line 1
    new-instance v0, Ljava/lang/StringBuilder;

    .line 2
    .line 3
    const-string v1, "ChainRun "

    .line 4
    .line 5
    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    .line 6
    .line 7
    .line 8
    iget v1, p0, Lt/o;->f:I

    .line 9
    .line 10
    if-nez v1, :cond_0

    .line 11
    .line 12
    const-string v1, "horizontal : "

    .line 13
    .line 14
    goto :goto_0

    .line 15
    :cond_0
    const-string v1, "vertical : "

    .line 16
    .line 17
    :goto_0
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 18
    .line 19
    .line 20
    iget-object v1, p0, Lt/c;->k:Ljava/util/ArrayList;

    .line 21
    .line 22
    invoke-virtual {v1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    .line 23
    .line 24
    .line 25
    move-result-object v1

    .line 26
    :goto_1
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    .line 27
    .line 28
    .line 29
    move-result v2

    .line 30
    if-eqz v2, :cond_1

    .line 31
    .line 32
    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    .line 33
    .line 34
    .line 35
    move-result-object v2

    .line 36
    check-cast v2, Lt/o;

    .line 37
    .line 38
    const-string v3, "<"

    .line 39
    .line 40
    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 41
    .line 42
    .line 43
    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    .line 44
    .line 45
    .line 46
    const-string v2, "> "

    .line 47
    .line 48
    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 49
    .line 50
    .line 51
    goto :goto_1

    .line 52
    :cond_1
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    .line 53
    .line 54
    .line 55
    move-result-object v0

    .line 56
    return-object v0
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
