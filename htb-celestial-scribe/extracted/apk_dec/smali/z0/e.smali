.class public abstract Lz0/e;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 1
    sget-object v0, LH0/h;->d:LH0/h;

    .line 2
    .line 3
    const-string v0, "\"\\"

    .line 4
    .line 5
    invoke-static {v0}, LA/m;->e(Ljava/lang/String;)LH0/h;

    .line 6
    .line 7
    .line 8
    const-string v0, "\t ,="

    .line 9
    .line 10
    invoke-static {v0}, LA/m;->e(Ljava/lang/String;)LH0/h;

    .line 11
    .line 12
    .line 13
    return-void
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

.method public static final a(Lu0/u;)Z
    .locals 7

    .line 1
    iget-object v0, p0, Lu0/u;->a:LB0/h;

    .line 2
    .line 3
    iget-object v0, v0, LB0/h;->d:Ljava/io/Serializable;

    .line 4
    .line 5
    check-cast v0, Ljava/lang/String;

    .line 6
    .line 7
    const-string v1, "HEAD"

    .line 8
    .line 9
    invoke-static {v0, v1}, Lj0/d;->a(Ljava/lang/Object;Ljava/lang/Object;)Z

    .line 10
    .line 11
    .line 12
    move-result v0

    .line 13
    const/4 v1, 0x0

    .line 14
    if-eqz v0, :cond_0

    .line 15
    .line 16
    return v1

    .line 17
    :cond_0
    const/16 v0, 0x64

    .line 18
    .line 19
    const/4 v2, 0x1

    .line 20
    iget v3, p0, Lu0/u;->d:I

    .line 21
    .line 22
    if-lt v3, v0, :cond_1

    .line 23
    .line 24
    const/16 v0, 0xc8

    .line 25
    .line 26
    if-lt v3, v0, :cond_2

    .line 27
    .line 28
    :cond_1
    const/16 v0, 0xcc

    .line 29
    .line 30
    if-eq v3, v0, :cond_2

    .line 31
    .line 32
    const/16 v0, 0x130

    .line 33
    .line 34
    if-eq v3, v0, :cond_2

    .line 35
    .line 36
    return v2

    .line 37
    :cond_2
    invoke-static {p0}, Lv0/b;->j(Lu0/u;)J

    .line 38
    .line 39
    .line 40
    move-result-wide v3

    .line 41
    const-wide/16 v5, -0x1

    .line 42
    .line 43
    cmp-long v0, v3, v5

    .line 44
    .line 45
    if-nez v0, :cond_4

    .line 46
    .line 47
    const-string v0, "Transfer-Encoding"

    .line 48
    .line 49
    invoke-static {v0, p0}, Lu0/u;->f(Ljava/lang/String;Lu0/u;)Ljava/lang/String;

    .line 50
    .line 51
    .line 52
    move-result-object p0

    .line 53
    const-string v0, "chunked"

    .line 54
    .line 55
    invoke-virtual {v0, p0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 56
    .line 57
    .line 58
    move-result p0

    .line 59
    if-eqz p0, :cond_3

    .line 60
    .line 61
    goto :goto_0

    .line 62
    :cond_3
    return v1

    .line 63
    :cond_4
    :goto_0
    return v2
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
.end method

.method public static final b(Lu0/b;Lu0/m;Lu0/k;)V
    .locals 36

    .line 1
    move-object/from16 v0, p0

    .line 2
    .line 3
    move-object/from16 v1, p1

    .line 4
    .line 5
    move-object/from16 v2, p2

    .line 6
    .line 7
    const/4 v3, 0x1

    .line 8
    const-string v4, "<this>"

    .line 9
    .line 10
    invoke-static {v0, v4}, Lj0/d;->e(Ljava/lang/Object;Ljava/lang/String;)V

    .line 11
    .line 12
    .line 13
    const-string v5, "url"

    .line 14
    .line 15
    invoke-static {v1, v5}, Lj0/d;->e(Ljava/lang/Object;Ljava/lang/String;)V

    .line 16
    .line 17
    .line 18
    const-string v5, "headers"

    .line 19
    .line 20
    invoke-static {v2, v5}, Lj0/d;->e(Ljava/lang/Object;Ljava/lang/String;)V

    .line 21
    .line 22
    .line 23
    sget-object v5, Lu0/b;->b:Lu0/b;

    .line 24
    .line 25
    if-ne v0, v5, :cond_0

    .line 26
    .line 27
    return-void

    .line 28
    :cond_0
    sget-object v0, Lu0/i;->j:Ljava/util/regex/Pattern;

    .line 29
    .line 30
    invoke-virtual/range {p2 .. p2}, Lu0/k;->size()I

    .line 31
    .line 32
    .line 33
    move-result v0

    .line 34
    const/4 v5, 0x0

    .line 35
    move v7, v5

    .line 36
    const/4 v8, 0x0

    .line 37
    :goto_0
    if-ge v7, v0, :cond_3

    .line 38
    .line 39
    add-int/lit8 v9, v7, 0x1

    .line 40
    .line 41
    invoke-virtual {v2, v7}, Lu0/k;->b(I)Ljava/lang/String;

    .line 42
    .line 43
    .line 44
    move-result-object v10

    .line 45
    const-string v11, "Set-Cookie"

    .line 46
    .line 47
    invoke-virtual {v11, v10}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 48
    .line 49
    .line 50
    move-result v10

    .line 51
    if-eqz v10, :cond_2

    .line 52
    .line 53
    if-nez v8, :cond_1

    .line 54
    .line 55
    new-instance v8, Ljava/util/ArrayList;

    .line 56
    .line 57
    const/4 v10, 0x2

    .line 58
    invoke-direct {v8, v10}, Ljava/util/ArrayList;-><init>(I)V

    .line 59
    .line 60
    .line 61
    :cond_1
    invoke-virtual {v2, v7}, Lu0/k;->d(I)Ljava/lang/String;

    .line 62
    .line 63
    .line 64
    move-result-object v7

    .line 65
    invoke-interface {v8, v7}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 66
    .line 67
    .line 68
    :cond_2
    move v7, v9

    .line 69
    goto :goto_0

    .line 70
    :cond_3
    sget-object v2, Lb0/q;->a:Lb0/q;

    .line 71
    .line 72
    if-eqz v8, :cond_4

    .line 73
    .line 74
    invoke-static {v8}, Ljava/util/Collections;->unmodifiableList(Ljava/util/List;)Ljava/util/List;

    .line 75
    .line 76
    .line 77
    move-result-object v0

    .line 78
    const-string v7, "{\n      Collections.unmodifiableList(result)\n    }"

    .line 79
    .line 80
    invoke-static {v0, v7}, Lj0/d;->d(Ljava/lang/Object;Ljava/lang/String;)V

    .line 81
    .line 82
    .line 83
    move-object v7, v0

    .line 84
    goto :goto_1

    .line 85
    :cond_4
    move-object v7, v2

    .line 86
    :goto_1
    invoke-interface {v7}, Ljava/util/List;->size()I

    .line 87
    .line 88
    .line 89
    move-result v8

    .line 90
    move v0, v5

    .line 91
    const/4 v9, 0x0

    .line 92
    :goto_2
    if-ge v0, v8, :cond_25

    .line 93
    .line 94
    add-int/lit8 v10, v0, 0x1

    .line 95
    .line 96
    invoke-interface {v7, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    .line 97
    .line 98
    .line 99
    move-result-object v0

    .line 100
    move-object v11, v0

    .line 101
    check-cast v11, Ljava/lang/String;

    .line 102
    .line 103
    const-string v0, "setCookie"

    .line 104
    .line 105
    invoke-static {v11, v0}, Lj0/d;->e(Ljava/lang/Object;Ljava/lang/String;)V

    .line 106
    .line 107
    .line 108
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    .line 109
    .line 110
    .line 111
    move-result-wide v12

    .line 112
    sget-object v0, Lv0/b;->a:[B

    .line 113
    .line 114
    invoke-virtual {v11}, Ljava/lang/String;->length()I

    .line 115
    .line 116
    .line 117
    move-result v0

    .line 118
    const/16 v14, 0x3b

    .line 119
    .line 120
    invoke-static {v11, v14, v5, v0}, Lv0/b;->e(Ljava/lang/String;CII)I

    .line 121
    .line 122
    .line 123
    move-result v0

    .line 124
    const/16 v15, 0x3d

    .line 125
    .line 126
    invoke-static {v11, v15, v5, v0}, Lv0/b;->e(Ljava/lang/String;CII)I

    .line 127
    .line 128
    .line 129
    move-result v6

    .line 130
    if-ne v6, v0, :cond_5

    .line 131
    .line 132
    move-object/from16 v35, v2

    .line 133
    .line 134
    :goto_3
    const/4 v0, 0x0

    .line 135
    goto/16 :goto_12

    .line 136
    .line 137
    :cond_5
    invoke-static {v11, v5, v6}, Lv0/b;->y(Ljava/lang/String;II)Ljava/lang/String;

    .line 138
    .line 139
    .line 140
    move-result-object v17

    .line 141
    invoke-virtual/range {v17 .. v17}, Ljava/lang/String;->length()I

    .line 142
    .line 143
    .line 144
    move-result v16

    .line 145
    if-nez v16, :cond_6

    .line 146
    .line 147
    goto :goto_4

    .line 148
    :cond_6
    invoke-static/range {v17 .. v17}, Lv0/b;->l(Ljava/lang/String;)I

    .line 149
    .line 150
    .line 151
    move-result v5

    .line 152
    const/4 v15, -0x1

    .line 153
    if-eq v5, v15, :cond_7

    .line 154
    .line 155
    :goto_4
    move-object/from16 v35, v2

    .line 156
    .line 157
    :goto_5
    const/4 v0, 0x0

    .line 158
    const/4 v5, 0x0

    .line 159
    goto/16 :goto_12

    .line 160
    .line 161
    :cond_7
    add-int/2addr v6, v3

    .line 162
    invoke-static {v11, v6, v0}, Lv0/b;->y(Ljava/lang/String;II)Ljava/lang/String;

    .line 163
    .line 164
    .line 165
    move-result-object v18

    .line 166
    invoke-static/range {v18 .. v18}, Lv0/b;->l(Ljava/lang/String;)I

    .line 167
    .line 168
    .line 169
    move-result v5

    .line 170
    if-eq v5, v15, :cond_8

    .line 171
    .line 172
    goto :goto_4

    .line 173
    :cond_8
    add-int/2addr v0, v3

    .line 174
    invoke-virtual {v11}, Ljava/lang/String;->length()I

    .line 175
    .line 176
    .line 177
    move-result v5

    .line 178
    const-wide v19, 0xe677d21fdbffL

    .line 179
    .line 180
    .line 181
    .line 182
    .line 183
    move/from16 v26, v3

    .line 184
    .line 185
    move-wide/from16 v28, v19

    .line 186
    .line 187
    const/4 v6, 0x0

    .line 188
    const-wide/16 v21, -0x1

    .line 189
    .line 190
    const/16 v23, 0x0

    .line 191
    .line 192
    const/16 v24, 0x0

    .line 193
    .line 194
    const/16 v25, 0x0

    .line 195
    .line 196
    const/16 v27, 0x0

    .line 197
    .line 198
    :goto_6
    const-wide v30, 0x7fffffffffffffffL

    .line 199
    .line 200
    .line 201
    .line 202
    .line 203
    const-wide/high16 v32, -0x8000000000000000L

    .line 204
    .line 205
    if-ge v0, v5, :cond_15

    .line 206
    .line 207
    invoke-static {v11, v14, v0, v5}, Lv0/b;->e(Ljava/lang/String;CII)I

    .line 208
    .line 209
    .line 210
    move-result v15

    .line 211
    const/16 v14, 0x3d

    .line 212
    .line 213
    invoke-static {v11, v14, v0, v15}, Lv0/b;->e(Ljava/lang/String;CII)I

    .line 214
    .line 215
    .line 216
    move-result v3

    .line 217
    invoke-static {v11, v0, v3}, Lv0/b;->y(Ljava/lang/String;II)Ljava/lang/String;

    .line 218
    .line 219
    .line 220
    move-result-object v0

    .line 221
    if-ge v3, v15, :cond_9

    .line 222
    .line 223
    const/16 v34, 0x1

    .line 224
    .line 225
    add-int/lit8 v3, v3, 0x1

    .line 226
    .line 227
    invoke-static {v11, v3, v15}, Lv0/b;->y(Ljava/lang/String;II)Ljava/lang/String;

    .line 228
    .line 229
    .line 230
    move-result-object v3

    .line 231
    goto :goto_7

    .line 232
    :cond_9
    const-string v3, ""

    .line 233
    .line 234
    :goto_7
    const-string v14, "expires"

    .line 235
    .line 236
    invoke-virtual {v0, v14}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 237
    .line 238
    .line 239
    move-result v14

    .line 240
    if-eqz v14, :cond_a

    .line 241
    .line 242
    :try_start_0
    invoke-virtual {v3}, Ljava/lang/String;->length()I

    .line 243
    .line 244
    .line 245
    move-result v0

    .line 246
    invoke-static {v3, v0}, LC0/m;->o(Ljava/lang/String;I)J

    .line 247
    .line 248
    .line 249
    move-result-wide v28
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0

    .line 250
    move-object/from16 v35, v2

    .line 251
    .line 252
    :goto_8
    const/4 v2, 0x1

    .line 253
    const/16 v25, 0x1

    .line 254
    .line 255
    goto/16 :goto_b

    .line 256
    .line 257
    :catch_0
    move-object/from16 v35, v2

    .line 258
    .line 259
    goto/16 :goto_a

    .line 260
    .line 261
    :cond_a
    const-string v14, "max-age"

    .line 262
    .line 263
    invoke-virtual {v0, v14}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 264
    .line 265
    .line 266
    move-result v14

    .line 267
    if-eqz v14, :cond_e

    .line 268
    .line 269
    :try_start_1
    invoke-static {v3}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    .line 270
    .line 271
    .line 272
    move-result-wide v21
    :try_end_1
    .catch Ljava/lang/NumberFormatException; {:try_start_1 .. :try_end_1} :catch_1

    .line 273
    const-wide/16 v30, 0x0

    .line 274
    .line 275
    cmp-long v0, v21, v30

    .line 276
    .line 277
    if-gtz v0, :cond_b

    .line 278
    .line 279
    goto :goto_9

    .line 280
    :cond_b
    move-wide/from16 v32, v21

    .line 281
    .line 282
    :goto_9
    move-object/from16 v35, v2

    .line 283
    .line 284
    move-wide/from16 v21, v32

    .line 285
    .line 286
    goto :goto_8

    .line 287
    :catch_1
    move-exception v0

    .line 288
    move-object v14, v0

    .line 289
    :try_start_2
    const-string v0, "-?\\d+"

    .line 290
    .line 291
    invoke-static {v0}, Ljava/util/regex/Pattern;->compile(Ljava/lang/String;)Ljava/util/regex/Pattern;

    .line 292
    .line 293
    .line 294
    move-result-object v0
    :try_end_2
    .catch Ljava/lang/NumberFormatException; {:try_start_2 .. :try_end_2} :catch_0

    .line 295
    move-object/from16 v35, v2

    .line 296
    .line 297
    :try_start_3
    const-string v2, "compile(...)"

    .line 298
    .line 299
    invoke-static {v0, v2}, Lj0/d;->d(Ljava/lang/Object;Ljava/lang/String;)V

    .line 300
    .line 301
    .line 302
    invoke-virtual {v0, v3}, Ljava/util/regex/Pattern;->matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;

    .line 303
    .line 304
    .line 305
    move-result-object v0

    .line 306
    invoke-virtual {v0}, Ljava/util/regex/Matcher;->matches()Z

    .line 307
    .line 308
    .line 309
    move-result v0

    .line 310
    if-eqz v0, :cond_d

    .line 311
    .line 312
    const-string v0, "-"

    .line 313
    .line 314
    const/4 v2, 0x0

    .line 315
    invoke-static {v3, v2, v0}, Lq0/k;->B(Ljava/lang/String;ZLjava/lang/String;)Z

    .line 316
    .line 317
    .line 318
    move-result v0

    .line 319
    if-eqz v0, :cond_c

    .line 320
    .line 321
    move-wide/from16 v30, v32

    .line 322
    .line 323
    :cond_c
    move-wide/from16 v21, v30

    .line 324
    .line 325
    goto :goto_8

    .line 326
    :cond_d
    throw v14
    :try_end_3
    .catch Ljava/lang/NumberFormatException; {:try_start_3 .. :try_end_3} :catch_2

    .line 327
    :cond_e
    move-object/from16 v35, v2

    .line 328
    .line 329
    const-string v2, "domain"

    .line 330
    .line 331
    invoke-virtual {v0, v2}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 332
    .line 333
    .line 334
    move-result v2

    .line 335
    if-eqz v2, :cond_11

    .line 336
    .line 337
    :try_start_4
    const-string v0, "."

    .line 338
    .line 339
    invoke-virtual {v3, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    .line 340
    .line 341
    .line 342
    move-result v2

    .line 343
    if-nez v2, :cond_10

    .line 344
    .line 345
    invoke-static {v3, v0}, Lq0/d;->L(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    .line 346
    .line 347
    .line 348
    move-result-object v0

    .line 349
    invoke-static {v0}, LC0/m;->u(Ljava/lang/String;)Ljava/lang/String;

    .line 350
    .line 351
    .line 352
    move-result-object v0

    .line 353
    if-eqz v0, :cond_f

    .line 354
    .line 355
    move-object v6, v0

    .line 356
    const/4 v2, 0x1

    .line 357
    const/16 v26, 0x0

    .line 358
    .line 359
    goto :goto_b

    .line 360
    :cond_f
    new-instance v0, Ljava/lang/IllegalArgumentException;

    .line 361
    .line 362
    invoke-direct {v0}, Ljava/lang/IllegalArgumentException;-><init>()V

    .line 363
    .line 364
    .line 365
    throw v0

    .line 366
    :cond_10
    new-instance v0, Ljava/lang/IllegalArgumentException;

    .line 367
    .line 368
    const-string v2, "Failed requirement."

    .line 369
    .line 370
    invoke-direct {v0, v2}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    .line 371
    .line 372
    .line 373
    throw v0
    :try_end_4
    .catch Ljava/lang/IllegalArgumentException; {:try_start_4 .. :try_end_4} :catch_2

    .line 374
    :cond_11
    const-string v2, "path"

    .line 375
    .line 376
    invoke-virtual {v0, v2}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 377
    .line 378
    .line 379
    move-result v2

    .line 380
    if-eqz v2, :cond_13

    .line 381
    .line 382
    move-object/from16 v27, v3

    .line 383
    .line 384
    :catch_2
    :cond_12
    :goto_a
    const/4 v2, 0x1

    .line 385
    goto :goto_b

    .line 386
    :cond_13
    const-string v2, "secure"

    .line 387
    .line 388
    invoke-virtual {v0, v2}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 389
    .line 390
    .line 391
    move-result v2

    .line 392
    if-eqz v2, :cond_14

    .line 393
    .line 394
    const/4 v2, 0x1

    .line 395
    const/16 v23, 0x1

    .line 396
    .line 397
    goto :goto_b

    .line 398
    :cond_14
    const-string v2, "httponly"

    .line 399
    .line 400
    invoke-virtual {v0, v2}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 401
    .line 402
    .line 403
    move-result v0

    .line 404
    if-eqz v0, :cond_12

    .line 405
    .line 406
    const/4 v2, 0x1

    .line 407
    const/16 v24, 0x1

    .line 408
    .line 409
    :goto_b
    add-int/lit8 v0, v15, 0x1

    .line 410
    .line 411
    move v3, v2

    .line 412
    move-object/from16 v2, v35

    .line 413
    .line 414
    const/16 v14, 0x3b

    .line 415
    .line 416
    goto/16 :goto_6

    .line 417
    .line 418
    :cond_15
    move-object/from16 v35, v2

    .line 419
    .line 420
    cmp-long v0, v21, v32

    .line 421
    .line 422
    if-nez v0, :cond_16

    .line 423
    .line 424
    move-wide/from16 v19, v32

    .line 425
    .line 426
    goto :goto_c

    .line 427
    :cond_16
    const-wide/16 v2, -0x1

    .line 428
    .line 429
    cmp-long v0, v21, v2

    .line 430
    .line 431
    if-eqz v0, :cond_19

    .line 432
    .line 433
    const-wide v2, 0x20c49ba5e353f7L

    .line 434
    .line 435
    .line 436
    .line 437
    .line 438
    cmp-long v0, v21, v2

    .line 439
    .line 440
    if-gtz v0, :cond_17

    .line 441
    .line 442
    const/16 v0, 0x3e8

    .line 443
    .line 444
    int-to-long v2, v0

    .line 445
    mul-long v30, v21, v2

    .line 446
    .line 447
    :cond_17
    add-long v30, v12, v30

    .line 448
    .line 449
    cmp-long v0, v30, v12

    .line 450
    .line 451
    if-ltz v0, :cond_1a

    .line 452
    .line 453
    cmp-long v0, v30, v19

    .line 454
    .line 455
    if-lez v0, :cond_18

    .line 456
    .line 457
    goto :goto_c

    .line 458
    :cond_18
    move-wide/from16 v19, v30

    .line 459
    .line 460
    goto :goto_c

    .line 461
    :cond_19
    move-wide/from16 v19, v28

    .line 462
    .line 463
    :cond_1a
    :goto_c
    iget-object v0, v1, Lu0/m;->d:Ljava/lang/String;

    .line 464
    .line 465
    if-nez v6, :cond_1b

    .line 466
    .line 467
    move-object v6, v0

    .line 468
    :goto_d
    const/4 v3, 0x1

    .line 469
    goto :goto_e

    .line 470
    :cond_1b
    invoke-static {v0, v6}, Lj0/d;->a(Ljava/lang/Object;Ljava/lang/Object;)Z

    .line 471
    .line 472
    .line 473
    move-result v2

    .line 474
    if-eqz v2, :cond_1c

    .line 475
    .line 476
    goto :goto_d

    .line 477
    :cond_1c
    invoke-static {v0, v4}, Lj0/d;->e(Ljava/lang/Object;Ljava/lang/String;)V

    .line 478
    .line 479
    .line 480
    invoke-virtual {v0, v6}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    .line 481
    .line 482
    .line 483
    move-result v2

    .line 484
    if-eqz v2, :cond_22

    .line 485
    .line 486
    invoke-virtual {v0}, Ljava/lang/String;->length()I

    .line 487
    .line 488
    .line 489
    move-result v2

    .line 490
    invoke-virtual {v6}, Ljava/lang/String;->length()I

    .line 491
    .line 492
    .line 493
    move-result v3

    .line 494
    sub-int/2addr v2, v3

    .line 495
    const/4 v3, 0x1

    .line 496
    sub-int/2addr v2, v3

    .line 497
    invoke-virtual {v0, v2}, Ljava/lang/String;->charAt(I)C

    .line 498
    .line 499
    .line 500
    move-result v2

    .line 501
    const/16 v5, 0x2e

    .line 502
    .line 503
    if-ne v2, v5, :cond_21

    .line 504
    .line 505
    sget-object v2, Lv0/b;->g:Lj0/g;

    .line 506
    .line 507
    invoke-virtual {v2}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 508
    .line 509
    .line 510
    iget-object v2, v2, Lj0/g;->b:Ljava/lang/Object;

    .line 511
    .line 512
    check-cast v2, Ljava/util/regex/Pattern;

    .line 513
    .line 514
    invoke-virtual {v2, v0}, Ljava/util/regex/Pattern;->matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;

    .line 515
    .line 516
    .line 517
    move-result-object v2

    .line 518
    invoke-virtual {v2}, Ljava/util/regex/Matcher;->matches()Z

    .line 519
    .line 520
    .line 521
    move-result v2

    .line 522
    if-nez v2, :cond_21

    .line 523
    .line 524
    :goto_e
    invoke-virtual {v0}, Ljava/lang/String;->length()I

    .line 525
    .line 526
    .line 527
    move-result v0

    .line 528
    invoke-virtual {v6}, Ljava/lang/String;->length()I

    .line 529
    .line 530
    .line 531
    move-result v2

    .line 532
    if-eq v0, v2, :cond_1d

    .line 533
    .line 534
    sget-object v0, Lokhttp3/internal/publicsuffix/PublicSuffixDatabase;->g:Lokhttp3/internal/publicsuffix/PublicSuffixDatabase;

    .line 535
    .line 536
    invoke-virtual {v0, v6}, Lokhttp3/internal/publicsuffix/PublicSuffixDatabase;->a(Ljava/lang/String;)Ljava/lang/String;

    .line 537
    .line 538
    .line 539
    move-result-object v0

    .line 540
    if-nez v0, :cond_1d

    .line 541
    .line 542
    goto/16 :goto_5

    .line 543
    .line 544
    :cond_1d
    const-string v0, "/"

    .line 545
    .line 546
    move-object/from16 v2, v27

    .line 547
    .line 548
    const/4 v5, 0x0

    .line 549
    if-eqz v2, :cond_1f

    .line 550
    .line 551
    invoke-static {v2, v5, v0}, Lq0/k;->B(Ljava/lang/String;ZLjava/lang/String;)Z

    .line 552
    .line 553
    .line 554
    move-result v11

    .line 555
    if-nez v11, :cond_1e

    .line 556
    .line 557
    goto :goto_f

    .line 558
    :cond_1e
    move-object/from16 v22, v2

    .line 559
    .line 560
    goto :goto_10

    .line 561
    :cond_1f
    :goto_f
    invoke-virtual/range {p1 .. p1}, Lu0/m;->b()Ljava/lang/String;

    .line 562
    .line 563
    .line 564
    move-result-object v2

    .line 565
    const/16 v11, 0x2f

    .line 566
    .line 567
    const/4 v12, 0x6

    .line 568
    invoke-static {v2, v11, v5, v12}, Lq0/d;->J(Ljava/lang/String;CII)I

    .line 569
    .line 570
    .line 571
    move-result v11

    .line 572
    if-eqz v11, :cond_20

    .line 573
    .line 574
    invoke-virtual {v2, v5, v11}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    .line 575
    .line 576
    .line 577
    move-result-object v0

    .line 578
    const-string v2, "this as java.lang.String\u2026ing(startIndex, endIndex)"

    .line 579
    .line 580
    invoke-static {v0, v2}, Lj0/d;->d(Ljava/lang/Object;Ljava/lang/String;)V

    .line 581
    .line 582
    .line 583
    :cond_20
    move-object/from16 v22, v0

    .line 584
    .line 585
    :goto_10
    new-instance v0, Lu0/i;

    .line 586
    .line 587
    move-object/from16 v16, v0

    .line 588
    .line 589
    move-object/from16 v21, v6

    .line 590
    .line 591
    invoke-direct/range {v16 .. v26}, Lu0/i;-><init>(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;ZZZZ)V

    .line 592
    .line 593
    .line 594
    goto :goto_12

    .line 595
    :cond_21
    :goto_11
    const/4 v5, 0x0

    .line 596
    goto/16 :goto_3

    .line 597
    .line 598
    :cond_22
    const/4 v3, 0x1

    .line 599
    goto :goto_11

    .line 600
    :goto_12
    if-nez v0, :cond_23

    .line 601
    .line 602
    :goto_13
    move v0, v10

    .line 603
    move-object/from16 v2, v35

    .line 604
    .line 605
    goto/16 :goto_2

    .line 606
    .line 607
    :cond_23
    if-nez v9, :cond_24

    .line 608
    .line 609
    new-instance v2, Ljava/util/ArrayList;

    .line 610
    .line 611
    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    .line 612
    .line 613
    .line 614
    move-object v9, v2

    .line 615
    :cond_24
    invoke-interface {v9, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 616
    .line 617
    .line 618
    goto :goto_13

    .line 619
    :cond_25
    move-object/from16 v35, v2

    .line 620
    .line 621
    if-eqz v9, :cond_26

    .line 622
    .line 623
    invoke-static {v9}, Ljava/util/Collections;->unmodifiableList(Ljava/util/List;)Ljava/util/List;

    .line 624
    .line 625
    .line 626
    move-result-object v2

    .line 627
    const-string v0, "{\n        Collections.un\u2026ableList(cookies)\n      }"

    .line 628
    .line 629
    invoke-static {v2, v0}, Lj0/d;->d(Ljava/lang/Object;Ljava/lang/String;)V

    .line 630
    .line 631
    .line 632
    goto :goto_14

    .line 633
    :cond_26
    move-object/from16 v2, v35

    .line 634
    .line 635
    :goto_14
    invoke-interface {v2}, Ljava/util/List;->isEmpty()Z

    .line 636
    .line 637
    .line 638
    return-void
    .line 639
    .line 640
    .line 641
    .line 642
    .line 643
    .line 644
    .line 645
    .line 646
.end method
