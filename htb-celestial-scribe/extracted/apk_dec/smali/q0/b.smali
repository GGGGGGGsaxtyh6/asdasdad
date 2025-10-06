.class public final Lq0/b;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/Iterator;
.implements Lk0/a;


# instance fields
.field public a:I

.field public b:I

.field public c:I

.field public d:Ln0/c;

.field public final synthetic e:Lq0/c;


# direct methods
.method public constructor <init>(Lq0/c;)V
    .locals 3

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 2
    .line 3
    .line 4
    iput-object p1, p0, Lq0/b;->e:Lq0/c;

    .line 5
    .line 6
    const/4 v0, -0x1

    .line 7
    iput v0, p0, Lq0/b;->a:I

    .line 8
    .line 9
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 10
    .line 11
    .line 12
    iget-object p1, p1, Lq0/c;->a:Ljava/lang/CharSequence;

    .line 13
    .line 14
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    .line 15
    .line 16
    .line 17
    move-result p1

    .line 18
    if-ltz p1, :cond_1

    .line 19
    .line 20
    if-gez p1, :cond_0

    .line 21
    .line 22
    goto :goto_0

    .line 23
    :cond_0
    const/4 p1, 0x0

    .line 24
    :goto_0
    iput p1, p0, Lq0/b;->b:I

    .line 25
    .line 26
    iput p1, p0, Lq0/b;->c:I

    .line 27
    .line 28
    return-void

    .line 29
    :cond_1
    new-instance v0, Ljava/lang/IllegalArgumentException;

    .line 30
    .line 31
    const-string v1, "Cannot coerce value to an empty range: maximum "

    .line 32
    .line 33
    const-string v2, " is less than minimum 0."

    .line 34
    .line 35
    invoke-static {v1, p1, v2}, LA/e;->b(Ljava/lang/String;ILjava/lang/String;)Ljava/lang/String;

    .line 36
    .line 37
    .line 38
    move-result-object p1

    .line 39
    invoke-direct {v0, p1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    .line 40
    .line 41
    .line 42
    throw v0
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
.end method


# virtual methods
.method public final a()V
    .locals 22

    .line 1
    move-object/from16 v0, p0

    .line 2
    .line 3
    iget v1, v0, Lq0/b;->c:I

    .line 4
    .line 5
    const/4 v2, 0x0

    .line 6
    if-gez v1, :cond_0

    .line 7
    .line 8
    iput v2, v0, Lq0/b;->a:I

    .line 9
    .line 10
    const/4 v1, 0x0

    .line 11
    iput-object v1, v0, Lq0/b;->d:Ln0/c;

    .line 12
    .line 13
    goto/16 :goto_a

    .line 14
    .line 15
    :cond_0
    iget-object v3, v0, Lq0/b;->e:Lq0/c;

    .line 16
    .line 17
    invoke-virtual {v3}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 18
    .line 19
    .line 20
    iget-object v4, v3, Lq0/c;->a:Ljava/lang/CharSequence;

    .line 21
    .line 22
    invoke-interface {v4}, Ljava/lang/CharSequence;->length()I

    .line 23
    .line 24
    .line 25
    move-result v5

    .line 26
    const/4 v6, -0x1

    .line 27
    const/4 v7, 0x1

    .line 28
    if-le v1, v5, :cond_1

    .line 29
    .line 30
    new-instance v1, Ln0/c;

    .line 31
    .line 32
    iget v2, v0, Lq0/b;->b:I

    .line 33
    .line 34
    invoke-static {v4}, Lq0/d;->E(Ljava/lang/CharSequence;)I

    .line 35
    .line 36
    .line 37
    move-result v3

    .line 38
    invoke-direct {v1, v2, v3, v7}, Ln0/a;-><init>(III)V

    .line 39
    .line 40
    .line 41
    iput-object v1, v0, Lq0/b;->d:Ln0/c;

    .line 42
    .line 43
    iput v6, v0, Lq0/b;->c:I

    .line 44
    .line 45
    goto/16 :goto_9

    .line 46
    .line 47
    :cond_1
    iget-object v1, v3, Lq0/c;->b:Lj0/e;

    .line 48
    .line 49
    iget v3, v0, Lq0/b;->c:I

    .line 50
    .line 51
    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    .line 52
    .line 53
    .line 54
    move-result-object v3

    .line 55
    check-cast v1, Lq0/l;

    .line 56
    .line 57
    iget v5, v1, Lq0/l;->b:I

    .line 58
    .line 59
    packed-switch v5, :pswitch_data_0

    .line 60
    .line 61
    .line 62
    move-object v5, v4

    .line 63
    check-cast v5, Ljava/lang/CharSequence;

    .line 64
    .line 65
    invoke-virtual {v3}, Ljava/lang/Number;->intValue()I

    .line 66
    .line 67
    .line 68
    move-result v3

    .line 69
    const-string v8, "$this$$receiver"

    .line 70
    .line 71
    invoke-static {v5, v8}, Lj0/d;->e(Ljava/lang/Object;Ljava/lang/String;)V

    .line 72
    .line 73
    .line 74
    iget-object v1, v1, Lq0/l;->c:Ljava/lang/Object;

    .line 75
    .line 76
    check-cast v1, Ljava/util/List;

    .line 77
    .line 78
    invoke-interface {v1}, Ljava/util/Collection;->size()I

    .line 79
    .line 80
    .line 81
    move-result v8

    .line 82
    const/4 v14, 0x0

    .line 83
    const/4 v9, 0x0

    .line 84
    const/4 v10, 0x1

    .line 85
    if-ne v8, v10, :cond_5

    .line 86
    .line 87
    invoke-interface {v1}, Ljava/util/List;->size()I

    .line 88
    .line 89
    .line 90
    move-result v8

    .line 91
    if-eqz v8, :cond_4

    .line 92
    .line 93
    if-ne v8, v10, :cond_3

    .line 94
    .line 95
    invoke-interface {v1, v9}, Ljava/util/List;->get(I)Ljava/lang/Object;

    .line 96
    .line 97
    .line 98
    move-result-object v1

    .line 99
    check-cast v1, Ljava/lang/String;

    .line 100
    .line 101
    const/4 v8, 0x4

    .line 102
    invoke-static {v5, v1, v3, v8}, Lq0/d;->H(Ljava/lang/CharSequence;Ljava/lang/String;II)I

    .line 103
    .line 104
    .line 105
    move-result v3

    .line 106
    if-gez v3, :cond_2

    .line 107
    .line 108
    goto/16 :goto_5

    .line 109
    .line 110
    :cond_2
    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    .line 111
    .line 112
    .line 113
    move-result-object v3

    .line 114
    new-instance v5, La0/c;

    .line 115
    .line 116
    invoke-direct {v5, v3, v1}, La0/c;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 117
    .line 118
    .line 119
    goto/16 :goto_6

    .line 120
    .line 121
    :cond_3
    new-instance v1, Ljava/lang/IllegalArgumentException;

    .line 122
    .line 123
    const-string v2, "List has more than one element."

    .line 124
    .line 125
    invoke-direct {v1, v2}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    .line 126
    .line 127
    .line 128
    throw v1

    .line 129
    :cond_4
    new-instance v1, Ljava/util/NoSuchElementException;

    .line 130
    .line 131
    const-string v2, "List is empty."

    .line 132
    .line 133
    invoke-direct {v1, v2}, Ljava/util/NoSuchElementException;-><init>(Ljava/lang/String;)V

    .line 134
    .line 135
    .line 136
    throw v1

    .line 137
    :cond_5
    new-instance v8, Ln0/c;

    .line 138
    .line 139
    if-gez v3, :cond_6

    .line 140
    .line 141
    move v3, v9

    .line 142
    :cond_6
    invoke-interface {v5}, Ljava/lang/CharSequence;->length()I

    .line 143
    .line 144
    .line 145
    move-result v9

    .line 146
    invoke-direct {v8, v3, v9, v10}, Ln0/a;-><init>(III)V

    .line 147
    .line 148
    .line 149
    instance-of v9, v5, Ljava/lang/String;

    .line 150
    .line 151
    const/16 v21, 0x0

    .line 152
    .line 153
    iget v13, v8, Ln0/a;->c:I

    .line 154
    .line 155
    iget v12, v8, Ln0/a;->b:I

    .line 156
    .line 157
    if-eqz v9, :cond_c

    .line 158
    .line 159
    if-lez v13, :cond_7

    .line 160
    .line 161
    if-le v3, v12, :cond_8

    .line 162
    .line 163
    :cond_7
    if-gez v13, :cond_12

    .line 164
    .line 165
    if-gt v12, v3, :cond_12

    .line 166
    .line 167
    :cond_8
    :goto_0
    invoke-interface {v1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    .line 168
    .line 169
    .line 170
    move-result-object v8

    .line 171
    :cond_9
    invoke-interface {v8}, Ljava/util/Iterator;->hasNext()Z

    .line 172
    .line 173
    .line 174
    move-result v9

    .line 175
    if-eqz v9, :cond_a

    .line 176
    .line 177
    invoke-interface {v8}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    .line 178
    .line 179
    .line 180
    move-result-object v9

    .line 181
    move-object/from16 v18, v9

    .line 182
    .line 183
    check-cast v18, Ljava/lang/String;

    .line 184
    .line 185
    move-object/from16 v19, v5

    .line 186
    .line 187
    check-cast v19, Ljava/lang/String;

    .line 188
    .line 189
    invoke-virtual/range {v18 .. v18}, Ljava/lang/String;->length()I

    .line 190
    .line 191
    .line 192
    move-result v17

    .line 193
    const/4 v15, 0x0

    .line 194
    move/from16 v16, v3

    .line 195
    .line 196
    move/from16 v20, v21

    .line 197
    .line 198
    invoke-static/range {v15 .. v20}, Lq0/k;->y(IIILjava/lang/String;Ljava/lang/String;Z)Z

    .line 199
    .line 200
    .line 201
    move-result v10

    .line 202
    if-eqz v10, :cond_9

    .line 203
    .line 204
    goto :goto_1

    .line 205
    :cond_a
    move-object v9, v14

    .line 206
    :goto_1
    check-cast v9, Ljava/lang/String;

    .line 207
    .line 208
    if-eqz v9, :cond_b

    .line 209
    .line 210
    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    .line 211
    .line 212
    .line 213
    move-result-object v1

    .line 214
    new-instance v5, La0/c;

    .line 215
    .line 216
    invoke-direct {v5, v1, v9}, La0/c;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 217
    .line 218
    .line 219
    goto/16 :goto_6

    .line 220
    .line 221
    :cond_b
    if-eq v3, v12, :cond_12

    .line 222
    .line 223
    add-int/2addr v3, v13

    .line 224
    goto :goto_0

    .line 225
    :cond_c
    if-lez v13, :cond_d

    .line 226
    .line 227
    if-le v3, v12, :cond_e

    .line 228
    .line 229
    :cond_d
    if-gez v13, :cond_12

    .line 230
    .line 231
    if-gt v12, v3, :cond_12

    .line 232
    .line 233
    :cond_e
    :goto_2
    invoke-interface {v1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    .line 234
    .line 235
    .line 236
    move-result-object v15

    .line 237
    :goto_3
    invoke-interface {v15}, Ljava/util/Iterator;->hasNext()Z

    .line 238
    .line 239
    .line 240
    move-result v8

    .line 241
    if-eqz v8, :cond_10

    .line 242
    .line 243
    invoke-interface {v15}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    .line 244
    .line 245
    .line 246
    move-result-object v16

    .line 247
    move-object/from16 v8, v16

    .line 248
    .line 249
    check-cast v8, Ljava/lang/String;

    .line 250
    .line 251
    invoke-virtual {v8}, Ljava/lang/String;->length()I

    .line 252
    .line 253
    .line 254
    move-result v17

    .line 255
    const/4 v9, 0x0

    .line 256
    move-object v10, v5

    .line 257
    move v11, v3

    .line 258
    move v2, v12

    .line 259
    move/from16 v12, v17

    .line 260
    .line 261
    move/from16 v17, v13

    .line 262
    .line 263
    move/from16 v13, v21

    .line 264
    .line 265
    invoke-static/range {v8 .. v13}, Lq0/d;->K(Ljava/lang/String;ILjava/lang/CharSequence;IIZ)Z

    .line 266
    .line 267
    .line 268
    move-result v8

    .line 269
    if-eqz v8, :cond_f

    .line 270
    .line 271
    goto :goto_4

    .line 272
    :cond_f
    move v12, v2

    .line 273
    move/from16 v13, v17

    .line 274
    .line 275
    const/4 v2, 0x0

    .line 276
    goto :goto_3

    .line 277
    :cond_10
    move v2, v12

    .line 278
    move/from16 v17, v13

    .line 279
    .line 280
    move-object/from16 v16, v14

    .line 281
    .line 282
    :goto_4
    move-object/from16 v8, v16

    .line 283
    .line 284
    check-cast v8, Ljava/lang/String;

    .line 285
    .line 286
    if-eqz v8, :cond_11

    .line 287
    .line 288
    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    .line 289
    .line 290
    .line 291
    move-result-object v1

    .line 292
    new-instance v5, La0/c;

    .line 293
    .line 294
    invoke-direct {v5, v1, v8}, La0/c;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 295
    .line 296
    .line 297
    goto :goto_6

    .line 298
    :cond_11
    if-eq v3, v2, :cond_12

    .line 299
    .line 300
    add-int v3, v3, v17

    .line 301
    .line 302
    move v12, v2

    .line 303
    move/from16 v13, v17

    .line 304
    .line 305
    const/4 v2, 0x0

    .line 306
    goto :goto_2

    .line 307
    :cond_12
    :goto_5
    move-object v5, v14

    .line 308
    :goto_6
    if-eqz v5, :cond_14

    .line 309
    .line 310
    iget-object v1, v5, La0/c;->b:Ljava/lang/Object;

    .line 311
    .line 312
    check-cast v1, Ljava/lang/String;

    .line 313
    .line 314
    invoke-virtual {v1}, Ljava/lang/String;->length()I

    .line 315
    .line 316
    .line 317
    move-result v1

    .line 318
    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    .line 319
    .line 320
    .line 321
    move-result-object v1

    .line 322
    new-instance v14, La0/c;

    .line 323
    .line 324
    iget-object v2, v5, La0/c;->a:Ljava/lang/Object;

    .line 325
    .line 326
    invoke-direct {v14, v2, v1}, La0/c;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 327
    .line 328
    .line 329
    goto :goto_7

    .line 330
    :pswitch_0
    move-object v2, v4

    .line 331
    check-cast v2, Ljava/lang/CharSequence;

    .line 332
    .line 333
    invoke-virtual {v3}, Ljava/lang/Number;->intValue()I

    .line 334
    .line 335
    .line 336
    move-result v3

    .line 337
    const-string v5, "$this$$receiver"

    .line 338
    .line 339
    invoke-static {v2, v5}, Lj0/d;->e(Ljava/lang/Object;Ljava/lang/String;)V

    .line 340
    .line 341
    .line 342
    iget-object v1, v1, Lq0/l;->c:Ljava/lang/Object;

    .line 343
    .line 344
    check-cast v1, [C

    .line 345
    .line 346
    const/4 v5, 0x0

    .line 347
    invoke-static {v2, v1, v3, v5}, Lq0/d;->I(Ljava/lang/CharSequence;[CIZ)I

    .line 348
    .line 349
    .line 350
    move-result v1

    .line 351
    if-gez v1, :cond_13

    .line 352
    .line 353
    const/4 v1, 0x0

    .line 354
    move-object v14, v1

    .line 355
    goto :goto_7

    .line 356
    :cond_13
    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    .line 357
    .line 358
    .line 359
    move-result-object v1

    .line 360
    const/4 v2, 0x1

    .line 361
    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    .line 362
    .line 363
    .line 364
    move-result-object v2

    .line 365
    new-instance v3, La0/c;

    .line 366
    .line 367
    invoke-direct {v3, v1, v2}, La0/c;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 368
    .line 369
    .line 370
    move-object v14, v3

    .line 371
    :cond_14
    :goto_7
    if-nez v14, :cond_15

    .line 372
    .line 373
    new-instance v1, Ln0/c;

    .line 374
    .line 375
    iget v2, v0, Lq0/b;->b:I

    .line 376
    .line 377
    invoke-static {v4}, Lq0/d;->E(Ljava/lang/CharSequence;)I

    .line 378
    .line 379
    .line 380
    move-result v3

    .line 381
    invoke-direct {v1, v2, v3, v7}, Ln0/a;-><init>(III)V

    .line 382
    .line 383
    .line 384
    iput-object v1, v0, Lq0/b;->d:Ln0/c;

    .line 385
    .line 386
    iput v6, v0, Lq0/b;->c:I

    .line 387
    .line 388
    goto :goto_9

    .line 389
    :cond_15
    iget-object v1, v14, La0/c;->a:Ljava/lang/Object;

    .line 390
    .line 391
    check-cast v1, Ljava/lang/Number;

    .line 392
    .line 393
    invoke-virtual {v1}, Ljava/lang/Number;->intValue()I

    .line 394
    .line 395
    .line 396
    move-result v1

    .line 397
    iget-object v2, v14, La0/c;->b:Ljava/lang/Object;

    .line 398
    .line 399
    check-cast v2, Ljava/lang/Number;

    .line 400
    .line 401
    invoke-virtual {v2}, Ljava/lang/Number;->intValue()I

    .line 402
    .line 403
    .line 404
    move-result v2

    .line 405
    iget v3, v0, Lq0/b;->b:I

    .line 406
    .line 407
    invoke-static {v3, v1}, LC0/m;->v(II)Ln0/c;

    .line 408
    .line 409
    .line 410
    move-result-object v3

    .line 411
    iput-object v3, v0, Lq0/b;->d:Ln0/c;

    .line 412
    .line 413
    add-int/2addr v1, v2

    .line 414
    iput v1, v0, Lq0/b;->b:I

    .line 415
    .line 416
    if-nez v2, :cond_16

    .line 417
    .line 418
    move v2, v7

    .line 419
    goto :goto_8

    .line 420
    :cond_16
    const/4 v2, 0x0

    .line 421
    :goto_8
    add-int/2addr v1, v2

    .line 422
    iput v1, v0, Lq0/b;->c:I

    .line 423
    .line 424
    :goto_9
    iput v7, v0, Lq0/b;->a:I

    .line 425
    .line 426
    :goto_a
    return-void

    .line 427
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
    .end packed-switch
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

.method public final hasNext()Z
    .locals 2

    .line 1
    iget v0, p0, Lq0/b;->a:I

    .line 2
    .line 3
    const/4 v1, -0x1

    .line 4
    if-ne v0, v1, :cond_0

    .line 5
    .line 6
    invoke-virtual {p0}, Lq0/b;->a()V

    .line 7
    .line 8
    .line 9
    :cond_0
    iget v0, p0, Lq0/b;->a:I

    .line 10
    .line 11
    const/4 v1, 0x1

    .line 12
    if-ne v0, v1, :cond_1

    .line 13
    .line 14
    goto :goto_0

    .line 15
    :cond_1
    const/4 v1, 0x0

    .line 16
    :goto_0
    return v1
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

.method public final next()Ljava/lang/Object;
    .locals 3

    .line 1
    iget v0, p0, Lq0/b;->a:I

    .line 2
    .line 3
    const/4 v1, -0x1

    .line 4
    if-ne v0, v1, :cond_0

    .line 5
    .line 6
    invoke-virtual {p0}, Lq0/b;->a()V

    .line 7
    .line 8
    .line 9
    :cond_0
    iget v0, p0, Lq0/b;->a:I

    .line 10
    .line 11
    if-eqz v0, :cond_1

    .line 12
    .line 13
    iget-object v0, p0, Lq0/b;->d:Ln0/c;

    .line 14
    .line 15
    const-string v2, "null cannot be cast to non-null type kotlin.ranges.IntRange"

    .line 16
    .line 17
    invoke-static {v0, v2}, Lj0/d;->c(Ljava/lang/Object;Ljava/lang/String;)V

    .line 18
    .line 19
    .line 20
    const/4 v2, 0x0

    .line 21
    iput-object v2, p0, Lq0/b;->d:Ln0/c;

    .line 22
    .line 23
    iput v1, p0, Lq0/b;->a:I

    .line 24
    .line 25
    return-object v0

    .line 26
    :cond_1
    new-instance v0, Ljava/util/NoSuchElementException;

    .line 27
    .line 28
    invoke-direct {v0}, Ljava/util/NoSuchElementException;-><init>()V

    .line 29
    .line 30
    .line 31
    throw v0
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

.method public final remove()V
    .locals 2

    .line 1
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    .line 2
    .line 3
    const-string v1, "Operation is not supported for read-only collection"

    .line 4
    .line 5
    invoke-direct {v0, v1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    .line 6
    .line 7
    .line 8
    throw v0
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
