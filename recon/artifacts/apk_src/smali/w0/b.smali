.class public final Lw0/b;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lu0/n;


# direct methods
.method static constructor <clinit>()V
    .locals 0

    .line 1
    return-void
    .line 2
    .line 3
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


# virtual methods
.method public final a(Lz0/f;)Lu0/u;
    .locals 29

    .line 1
    move-object/from16 v0, p1

    .line 2
    .line 3
    const/4 v2, 0x0

    .line 4
    const/16 v3, 0x9

    .line 5
    .line 6
    const/4 v4, 0x1

    .line 7
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    .line 8
    .line 9
    .line 10
    iget-object v6, v0, Lz0/f;->e:LB0/h;

    .line 11
    .line 12
    new-instance v5, LE/c;

    .line 13
    .line 14
    invoke-direct {v5, v6, v2, v3}, LE/c;-><init>(Ljava/lang/Object;Ljava/lang/Object;I)V

    .line 15
    .line 16
    .line 17
    iget-object v7, v6, LB0/h;->g:Ljava/lang/Object;

    .line 18
    .line 19
    check-cast v7, Lu0/c;

    .line 20
    .line 21
    if-nez v7, :cond_1a

    .line 22
    .line 23
    sget v7, Lu0/c;->n:I

    .line 24
    .line 25
    iget-object v7, v6, LB0/h;->c:Ljava/lang/Object;

    .line 26
    .line 27
    check-cast v7, Lu0/k;

    .line 28
    .line 29
    invoke-virtual {v7}, Lu0/k;->size()I

    .line 30
    .line 31
    .line 32
    move-result v8

    .line 33
    move-object v12, v2

    .line 34
    move v11, v4

    .line 35
    const/4 v10, 0x0

    .line 36
    const/4 v13, 0x0

    .line 37
    const/4 v14, 0x0

    .line 38
    const/4 v15, -0x1

    .line 39
    const/16 v16, -0x1

    .line 40
    .line 41
    const/16 v17, 0x0

    .line 42
    .line 43
    const/16 v18, 0x0

    .line 44
    .line 45
    const/16 v19, 0x0

    .line 46
    .line 47
    const/16 v20, -0x1

    .line 48
    .line 49
    const/16 v21, -0x1

    .line 50
    .line 51
    const/16 v22, 0x0

    .line 52
    .line 53
    const/16 v23, 0x0

    .line 54
    .line 55
    const/16 v24, 0x0

    .line 56
    .line 57
    :goto_0
    if-ge v10, v8, :cond_18

    .line 58
    .line 59
    add-int/lit8 v25, v10, 0x1

    .line 60
    .line 61
    invoke-virtual {v7, v10}, Lu0/k;->b(I)Ljava/lang/String;

    .line 62
    .line 63
    .line 64
    move-result-object v1

    .line 65
    invoke-virtual {v7, v10}, Lu0/k;->d(I)Ljava/lang/String;

    .line 66
    .line 67
    .line 68
    move-result-object v10

    .line 69
    const-string v2, "Cache-Control"

    .line 70
    .line 71
    invoke-static {v1, v2}, Lq0/k;->x(Ljava/lang/String;Ljava/lang/String;)Z

    .line 72
    .line 73
    .line 74
    move-result v2

    .line 75
    if-eqz v2, :cond_1

    .line 76
    .line 77
    if-eqz v12, :cond_0

    .line 78
    .line 79
    :goto_1
    const/4 v11, 0x0

    .line 80
    goto :goto_2

    .line 81
    :cond_0
    move-object v12, v10

    .line 82
    goto :goto_2

    .line 83
    :cond_1
    const-string v2, "Pragma"

    .line 84
    .line 85
    invoke-static {v1, v2}, Lq0/k;->x(Ljava/lang/String;Ljava/lang/String;)Z

    .line 86
    .line 87
    .line 88
    move-result v1

    .line 89
    if-eqz v1, :cond_17

    .line 90
    .line 91
    goto :goto_1

    .line 92
    :goto_2
    const/4 v1, 0x0

    .line 93
    :goto_3
    invoke-virtual {v10}, Ljava/lang/String;->length()I

    .line 94
    .line 95
    .line 96
    move-result v2

    .line 97
    if-ge v1, v2, :cond_17

    .line 98
    .line 99
    invoke-virtual {v10}, Ljava/lang/String;->length()I

    .line 100
    .line 101
    .line 102
    move-result v2

    .line 103
    move v9, v1

    .line 104
    :goto_4
    if-ge v9, v2, :cond_3

    .line 105
    .line 106
    add-int/lit8 v26, v9, 0x1

    .line 107
    .line 108
    invoke-virtual {v10, v9}, Ljava/lang/String;->charAt(I)C

    .line 109
    .line 110
    .line 111
    move-result v3

    .line 112
    const-string v4, "=,;"

    .line 113
    .line 114
    invoke-static {v4, v3}, Lq0/d;->C(Ljava/lang/CharSequence;C)Z

    .line 115
    .line 116
    .line 117
    move-result v3

    .line 118
    if-eqz v3, :cond_2

    .line 119
    .line 120
    goto :goto_5

    .line 121
    :cond_2
    move/from16 v9, v26

    .line 122
    .line 123
    const/16 v3, 0x9

    .line 124
    .line 125
    const/4 v4, 0x1

    .line 126
    goto :goto_4

    .line 127
    :cond_3
    invoke-virtual {v10}, Ljava/lang/String;->length()I

    .line 128
    .line 129
    .line 130
    move-result v9

    .line 131
    :goto_5
    invoke-virtual {v10, v1, v9}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    .line 132
    .line 133
    .line 134
    move-result-object v1

    .line 135
    const-string v2, "this as java.lang.String\u2026ing(startIndex, endIndex)"

    .line 136
    .line 137
    invoke-static {v1, v2}, Lj0/d;->d(Ljava/lang/Object;Ljava/lang/String;)V

    .line 138
    .line 139
    .line 140
    invoke-static {v1}, Lq0/d;->P(Ljava/lang/String;)Ljava/lang/CharSequence;

    .line 141
    .line 142
    .line 143
    move-result-object v1

    .line 144
    invoke-virtual {v1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    .line 145
    .line 146
    .line 147
    move-result-object v1

    .line 148
    invoke-virtual {v10}, Ljava/lang/String;->length()I

    .line 149
    .line 150
    .line 151
    move-result v3

    .line 152
    if-eq v9, v3, :cond_4

    .line 153
    .line 154
    invoke-virtual {v10, v9}, Ljava/lang/String;->charAt(I)C

    .line 155
    .line 156
    .line 157
    move-result v3

    .line 158
    const/16 v4, 0x2c

    .line 159
    .line 160
    if-eq v3, v4, :cond_4

    .line 161
    .line 162
    invoke-virtual {v10, v9}, Ljava/lang/String;->charAt(I)C

    .line 163
    .line 164
    .line 165
    move-result v3

    .line 166
    const/16 v4, 0x3b

    .line 167
    .line 168
    if-ne v3, v4, :cond_5

    .line 169
    .line 170
    :cond_4
    const/4 v2, 0x1

    .line 171
    goto/16 :goto_a

    .line 172
    .line 173
    :cond_5
    const/4 v3, 0x1

    .line 174
    add-int/2addr v9, v3

    .line 175
    sget-object v4, Lv0/b;->a:[B

    .line 176
    .line 177
    invoke-virtual {v10}, Ljava/lang/String;->length()I

    .line 178
    .line 179
    .line 180
    move-result v4

    .line 181
    :goto_6
    if-ge v9, v4, :cond_7

    .line 182
    .line 183
    add-int/lit8 v26, v9, 0x1

    .line 184
    .line 185
    invoke-virtual {v10, v9}, Ljava/lang/String;->charAt(I)C

    .line 186
    .line 187
    .line 188
    move-result v3

    .line 189
    move/from16 v27, v4

    .line 190
    .line 191
    const/16 v4, 0x20

    .line 192
    .line 193
    if-eq v3, v4, :cond_6

    .line 194
    .line 195
    const/16 v4, 0x9

    .line 196
    .line 197
    if-eq v3, v4, :cond_6

    .line 198
    .line 199
    goto :goto_7

    .line 200
    :cond_6
    move/from16 v9, v26

    .line 201
    .line 202
    move/from16 v4, v27

    .line 203
    .line 204
    const/4 v3, 0x1

    .line 205
    goto :goto_6

    .line 206
    :cond_7
    invoke-virtual {v10}, Ljava/lang/String;->length()I

    .line 207
    .line 208
    .line 209
    move-result v9

    .line 210
    :goto_7
    invoke-virtual {v10}, Ljava/lang/String;->length()I

    .line 211
    .line 212
    .line 213
    move-result v3

    .line 214
    if-ge v9, v3, :cond_8

    .line 215
    .line 216
    invoke-virtual {v10, v9}, Ljava/lang/String;->charAt(I)C

    .line 217
    .line 218
    .line 219
    move-result v3

    .line 220
    const/16 v4, 0x22

    .line 221
    .line 222
    if-ne v3, v4, :cond_8

    .line 223
    .line 224
    const/4 v3, 0x1

    .line 225
    add-int/2addr v9, v3

    .line 226
    const/4 v3, 0x4

    .line 227
    invoke-static {v10, v4, v9, v3}, Lq0/d;->G(Ljava/lang/CharSequence;CII)I

    .line 228
    .line 229
    .line 230
    move-result v3

    .line 231
    invoke-virtual {v10, v9, v3}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    .line 232
    .line 233
    .line 234
    move-result-object v4

    .line 235
    invoke-static {v4, v2}, Lj0/d;->d(Ljava/lang/Object;Ljava/lang/String;)V

    .line 236
    .line 237
    .line 238
    const/16 v26, 0x1

    .line 239
    .line 240
    add-int/lit8 v3, v3, 0x1

    .line 241
    .line 242
    move-object v2, v4

    .line 243
    goto :goto_b

    .line 244
    :cond_8
    const/16 v26, 0x1

    .line 245
    .line 246
    invoke-virtual {v10}, Ljava/lang/String;->length()I

    .line 247
    .line 248
    .line 249
    move-result v3

    .line 250
    move v4, v9

    .line 251
    :goto_8
    if-ge v4, v3, :cond_a

    .line 252
    .line 253
    add-int/lit8 v27, v4, 0x1

    .line 254
    .line 255
    move/from16 v26, v3

    .line 256
    .line 257
    invoke-virtual {v10, v4}, Ljava/lang/String;->charAt(I)C

    .line 258
    .line 259
    .line 260
    move-result v3

    .line 261
    move/from16 v28, v4

    .line 262
    .line 263
    const-string v4, ",;"

    .line 264
    .line 265
    invoke-static {v4, v3}, Lq0/d;->C(Ljava/lang/CharSequence;C)Z

    .line 266
    .line 267
    .line 268
    move-result v3

    .line 269
    if-eqz v3, :cond_9

    .line 270
    .line 271
    move/from16 v4, v28

    .line 272
    .line 273
    goto :goto_9

    .line 274
    :cond_9
    move/from16 v3, v26

    .line 275
    .line 276
    move/from16 v4, v27

    .line 277
    .line 278
    const/16 v26, 0x1

    .line 279
    .line 280
    goto :goto_8

    .line 281
    :cond_a
    invoke-virtual {v10}, Ljava/lang/String;->length()I

    .line 282
    .line 283
    .line 284
    move-result v4

    .line 285
    :goto_9
    invoke-virtual {v10, v9, v4}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    .line 286
    .line 287
    .line 288
    move-result-object v3

    .line 289
    invoke-static {v3, v2}, Lj0/d;->d(Ljava/lang/Object;Ljava/lang/String;)V

    .line 290
    .line 291
    .line 292
    invoke-static {v3}, Lq0/d;->P(Ljava/lang/String;)Ljava/lang/CharSequence;

    .line 293
    .line 294
    .line 295
    move-result-object v2

    .line 296
    invoke-virtual {v2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    .line 297
    .line 298
    .line 299
    move-result-object v2

    .line 300
    move v3, v4

    .line 301
    goto :goto_b

    .line 302
    :goto_a
    add-int/2addr v9, v2

    .line 303
    move v3, v9

    .line 304
    const/4 v2, 0x0

    .line 305
    :goto_b
    const-string v4, "no-cache"

    .line 306
    .line 307
    invoke-virtual {v4, v1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 308
    .line 309
    .line 310
    move-result v4

    .line 311
    if-eqz v4, :cond_b

    .line 312
    .line 313
    move v1, v3

    .line 314
    const/16 v3, 0x9

    .line 315
    .line 316
    const/4 v4, 0x1

    .line 317
    const/4 v13, 0x1

    .line 318
    goto/16 :goto_3

    .line 319
    .line 320
    :cond_b
    const-string v4, "no-store"

    .line 321
    .line 322
    invoke-virtual {v4, v1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 323
    .line 324
    .line 325
    move-result v4

    .line 326
    if-eqz v4, :cond_c

    .line 327
    .line 328
    move v1, v3

    .line 329
    const/16 v3, 0x9

    .line 330
    .line 331
    const/4 v4, 0x1

    .line 332
    const/4 v14, 0x1

    .line 333
    goto/16 :goto_3

    .line 334
    .line 335
    :cond_c
    const-string v4, "max-age"

    .line 336
    .line 337
    invoke-virtual {v4, v1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 338
    .line 339
    .line 340
    move-result v4

    .line 341
    if-eqz v4, :cond_e

    .line 342
    .line 343
    const/4 v4, -0x1

    .line 344
    invoke-static {v2, v4}, Lv0/b;->x(Ljava/lang/String;I)I

    .line 345
    .line 346
    .line 347
    move-result v15

    .line 348
    :cond_d
    :goto_c
    move v1, v3

    .line 349
    const/16 v3, 0x9

    .line 350
    .line 351
    const/4 v4, 0x1

    .line 352
    goto/16 :goto_3

    .line 353
    .line 354
    :cond_e
    const/4 v4, -0x1

    .line 355
    const-string v9, "s-maxage"

    .line 356
    .line 357
    invoke-virtual {v9, v1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 358
    .line 359
    .line 360
    move-result v9

    .line 361
    if-eqz v9, :cond_f

    .line 362
    .line 363
    invoke-static {v2, v4}, Lv0/b;->x(Ljava/lang/String;I)I

    .line 364
    .line 365
    .line 366
    move-result v16

    .line 367
    goto :goto_c

    .line 368
    :cond_f
    const-string v4, "private"

    .line 369
    .line 370
    invoke-virtual {v4, v1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 371
    .line 372
    .line 373
    move-result v4

    .line 374
    if-eqz v4, :cond_10

    .line 375
    .line 376
    move v1, v3

    .line 377
    const/16 v3, 0x9

    .line 378
    .line 379
    const/4 v4, 0x1

    .line 380
    const/16 v17, 0x1

    .line 381
    .line 382
    goto/16 :goto_3

    .line 383
    .line 384
    :cond_10
    const-string v4, "public"

    .line 385
    .line 386
    invoke-virtual {v4, v1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 387
    .line 388
    .line 389
    move-result v4

    .line 390
    if-eqz v4, :cond_11

    .line 391
    .line 392
    move v1, v3

    .line 393
    const/16 v3, 0x9

    .line 394
    .line 395
    const/4 v4, 0x1

    .line 396
    const/16 v18, 0x1

    .line 397
    .line 398
    goto/16 :goto_3

    .line 399
    .line 400
    :cond_11
    const-string v4, "must-revalidate"

    .line 401
    .line 402
    invoke-virtual {v4, v1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 403
    .line 404
    .line 405
    move-result v4

    .line 406
    if-eqz v4, :cond_12

    .line 407
    .line 408
    move v1, v3

    .line 409
    const/16 v3, 0x9

    .line 410
    .line 411
    const/4 v4, 0x1

    .line 412
    const/16 v19, 0x1

    .line 413
    .line 414
    goto/16 :goto_3

    .line 415
    .line 416
    :cond_12
    const-string v4, "max-stale"

    .line 417
    .line 418
    invoke-virtual {v4, v1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 419
    .line 420
    .line 421
    move-result v4

    .line 422
    if-eqz v4, :cond_13

    .line 423
    .line 424
    const v1, 0x7fffffff

    .line 425
    .line 426
    .line 427
    invoke-static {v2, v1}, Lv0/b;->x(Ljava/lang/String;I)I

    .line 428
    .line 429
    .line 430
    move-result v20

    .line 431
    goto :goto_c

    .line 432
    :cond_13
    const-string v4, "min-fresh"

    .line 433
    .line 434
    invoke-virtual {v4, v1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 435
    .line 436
    .line 437
    move-result v4

    .line 438
    if-eqz v4, :cond_14

    .line 439
    .line 440
    const/4 v4, -0x1

    .line 441
    invoke-static {v2, v4}, Lv0/b;->x(Ljava/lang/String;I)I

    .line 442
    .line 443
    .line 444
    move-result v21

    .line 445
    goto :goto_c

    .line 446
    :cond_14
    const/4 v4, -0x1

    .line 447
    const-string v2, "only-if-cached"

    .line 448
    .line 449
    invoke-virtual {v2, v1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 450
    .line 451
    .line 452
    move-result v2

    .line 453
    if-eqz v2, :cond_15

    .line 454
    .line 455
    move v1, v3

    .line 456
    const/16 v3, 0x9

    .line 457
    .line 458
    const/4 v4, 0x1

    .line 459
    const/16 v22, 0x1

    .line 460
    .line 461
    goto/16 :goto_3

    .line 462
    .line 463
    :cond_15
    const-string v2, "no-transform"

    .line 464
    .line 465
    invoke-virtual {v2, v1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 466
    .line 467
    .line 468
    move-result v2

    .line 469
    if-eqz v2, :cond_16

    .line 470
    .line 471
    move v1, v3

    .line 472
    const/16 v3, 0x9

    .line 473
    .line 474
    const/4 v4, 0x1

    .line 475
    const/16 v23, 0x1

    .line 476
    .line 477
    goto/16 :goto_3

    .line 478
    .line 479
    :cond_16
    const-string v2, "immutable"

    .line 480
    .line 481
    invoke-virtual {v2, v1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 482
    .line 483
    .line 484
    move-result v1

    .line 485
    if-eqz v1, :cond_d

    .line 486
    .line 487
    move v1, v3

    .line 488
    const/16 v3, 0x9

    .line 489
    .line 490
    const/4 v4, 0x1

    .line 491
    const/16 v24, 0x1

    .line 492
    .line 493
    goto/16 :goto_3

    .line 494
    .line 495
    :cond_17
    move/from16 v10, v25

    .line 496
    .line 497
    const/4 v2, 0x0

    .line 498
    const/4 v4, 0x1

    .line 499
    goto/16 :goto_0

    .line 500
    .line 501
    :cond_18
    if-nez v11, :cond_19

    .line 502
    .line 503
    const/16 v25, 0x0

    .line 504
    .line 505
    goto :goto_d

    .line 506
    :cond_19
    move-object/from16 v25, v12

    .line 507
    .line 508
    :goto_d
    new-instance v7, Lu0/c;

    .line 509
    .line 510
    move-object v12, v7

    .line 511
    invoke-direct/range {v12 .. v25}, Lu0/c;-><init>(ZZIIZZZIIZZZLjava/lang/String;)V

    .line 512
    .line 513
    .line 514
    iput-object v7, v6, LB0/h;->g:Ljava/lang/Object;

    .line 515
    .line 516
    :cond_1a
    iget-boolean v1, v7, Lu0/c;->j:Z

    .line 517
    .line 518
    if-eqz v1, :cond_1b

    .line 519
    .line 520
    new-instance v5, LE/c;

    .line 521
    .line 522
    const/16 v1, 0x9

    .line 523
    .line 524
    const/4 v2, 0x0

    .line 525
    invoke-direct {v5, v2, v2, v1}, LE/c;-><init>(Ljava/lang/Object;Ljava/lang/Object;I)V

    .line 526
    .line 527
    .line 528
    :cond_1b
    const-string v1, "call"

    .line 529
    .line 530
    iget-object v2, v0, Lz0/f;->a:Ly0/h;

    .line 531
    .line 532
    iget-object v3, v5, LE/c;->b:Ljava/lang/Object;

    .line 533
    .line 534
    check-cast v3, LB0/h;

    .line 535
    .line 536
    iget-object v4, v5, LE/c;->c:Ljava/lang/Object;

    .line 537
    .line 538
    check-cast v4, Lu0/u;

    .line 539
    .line 540
    if-nez v3, :cond_1d

    .line 541
    .line 542
    if-nez v4, :cond_1d

    .line 543
    .line 544
    new-instance v0, Ljava/util/ArrayList;

    .line 545
    .line 546
    const/16 v3, 0x14

    .line 547
    .line 548
    invoke-direct {v0, v3}, Ljava/util/ArrayList;-><init>(I)V

    .line 549
    .line 550
    .line 551
    sget-object v7, Lu0/r;->c:Lu0/r;

    .line 552
    .line 553
    sget-object v12, Lv0/b;->c:Lu0/v;

    .line 554
    .line 555
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    .line 556
    .line 557
    .line 558
    move-result-wide v18

    .line 559
    new-instance v11, Lu0/k;

    .line 560
    .line 561
    const/4 v3, 0x0

    .line 562
    new-array v3, v3, [Ljava/lang/String;

    .line 563
    .line 564
    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;

    .line 565
    .line 566
    .line 567
    move-result-object v0

    .line 568
    if-eqz v0, :cond_1c

    .line 569
    .line 570
    check-cast v0, [Ljava/lang/String;

    .line 571
    .line 572
    invoke-direct {v11, v0}, Lu0/k;-><init>([Ljava/lang/String;)V

    .line 573
    .line 574
    .line 575
    new-instance v0, Lu0/u;

    .line 576
    .line 577
    const-wide/16 v16, -0x1

    .line 578
    .line 579
    const/16 v20, 0x0

    .line 580
    .line 581
    const-string v8, "Unsatisfiable Request (only-if-cached)"

    .line 582
    .line 583
    const/16 v9, 0x1f8

    .line 584
    .line 585
    const/4 v10, 0x0

    .line 586
    const/4 v13, 0x0

    .line 587
    const/4 v14, 0x0

    .line 588
    const/4 v15, 0x0

    .line 589
    move-object v5, v0

    .line 590
    invoke-direct/range {v5 .. v20}, Lu0/u;-><init>(LB0/h;Lu0/r;Ljava/lang/String;ILu0/j;Lu0/k;Lu0/w;Lu0/u;Lu0/u;Lu0/u;JJLM/v;)V

    .line 591
    .line 592
    .line 593
    invoke-static {v2, v1}, Lj0/d;->e(Ljava/lang/Object;Ljava/lang/String;)V

    .line 594
    .line 595
    .line 596
    return-object v0

    .line 597
    :cond_1c
    new-instance v0, Ljava/lang/NullPointerException;

    .line 598
    .line 599
    const-string v1, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>"

    .line 600
    .line 601
    invoke-direct {v0, v1}, Ljava/lang/NullPointerException;-><init>(Ljava/lang/String;)V

    .line 602
    .line 603
    .line 604
    throw v0

    .line 605
    :cond_1d
    const-string v5, "cacheResponse"

    .line 606
    .line 607
    if-nez v3, :cond_1e

    .line 608
    .line 609
    invoke-static {v4}, Lj0/d;->b(Ljava/lang/Object;)V

    .line 610
    .line 611
    .line 612
    invoke-virtual {v4}, Lu0/u;->i()Lu0/t;

    .line 613
    .line 614
    .line 615
    move-result-object v0

    .line 616
    invoke-static {v4}, Lw0/a;->a(Lu0/u;)Lu0/u;

    .line 617
    .line 618
    .line 619
    move-result-object v3

    .line 620
    invoke-static {v5, v3}, Lu0/t;->b(Ljava/lang/String;Lu0/u;)V

    .line 621
    .line 622
    .line 623
    iput-object v3, v0, Lu0/t;->i:Lu0/u;

    .line 624
    .line 625
    invoke-virtual {v0}, Lu0/t;->a()Lu0/u;

    .line 626
    .line 627
    .line 628
    move-result-object v0

    .line 629
    invoke-static {v2, v1}, Lj0/d;->e(Ljava/lang/Object;Ljava/lang/String;)V

    .line 630
    .line 631
    .line 632
    return-object v0

    .line 633
    :cond_1e
    if-eqz v4, :cond_1f

    .line 634
    .line 635
    invoke-static {v2, v1}, Lj0/d;->e(Ljava/lang/Object;Ljava/lang/String;)V

    .line 636
    .line 637
    .line 638
    :cond_1f
    invoke-virtual {v0, v3}, Lz0/f;->b(LB0/h;)Lu0/u;

    .line 639
    .line 640
    .line 641
    move-result-object v0

    .line 642
    const-string v1, "networkResponse"

    .line 643
    .line 644
    if-eqz v4, :cond_2b

    .line 645
    .line 646
    const/16 v2, 0x130

    .line 647
    .line 648
    iget v3, v0, Lu0/u;->d:I

    .line 649
    .line 650
    if-ne v3, v2, :cond_29

    .line 651
    .line 652
    invoke-virtual {v4}, Lu0/u;->i()Lu0/t;

    .line 653
    .line 654
    .line 655
    move-result-object v2

    .line 656
    new-instance v3, LA/f;

    .line 657
    .line 658
    const/16 v6, 0x1d

    .line 659
    .line 660
    invoke-direct {v3, v6}, LA/f;-><init>(I)V

    .line 661
    .line 662
    .line 663
    iget-object v6, v4, Lu0/u;->f:Lu0/k;

    .line 664
    .line 665
    invoke-virtual {v6}, Lu0/k;->size()I

    .line 666
    .line 667
    .line 668
    move-result v7

    .line 669
    const/4 v8, 0x0

    .line 670
    :goto_e
    iget-object v9, v0, Lu0/u;->f:Lu0/k;

    .line 671
    .line 672
    const-string v10, "Content-Type"

    .line 673
    .line 674
    const-string v11, "Content-Encoding"

    .line 675
    .line 676
    const-string v12, "Content-Length"

    .line 677
    .line 678
    if-ge v8, v7, :cond_25

    .line 679
    .line 680
    const/4 v13, 0x1

    .line 681
    add-int/lit8 v14, v8, 0x1

    .line 682
    .line 683
    invoke-virtual {v6, v8}, Lu0/k;->b(I)Ljava/lang/String;

    .line 684
    .line 685
    .line 686
    move-result-object v13

    .line 687
    invoke-virtual {v6, v8}, Lu0/k;->d(I)Ljava/lang/String;

    .line 688
    .line 689
    .line 690
    move-result-object v8

    .line 691
    const-string v15, "Warning"

    .line 692
    .line 693
    invoke-virtual {v15, v13}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 694
    .line 695
    .line 696
    move-result v15

    .line 697
    if-eqz v15, :cond_20

    .line 698
    .line 699
    const-string v15, "1"

    .line 700
    .line 701
    move-object/from16 v16, v6

    .line 702
    .line 703
    const/4 v6, 0x0

    .line 704
    invoke-static {v8, v6, v15}, Lq0/k;->B(Ljava/lang/String;ZLjava/lang/String;)Z

    .line 705
    .line 706
    .line 707
    move-result v15

    .line 708
    if-eqz v15, :cond_21

    .line 709
    .line 710
    goto :goto_10

    .line 711
    :cond_20
    move-object/from16 v16, v6

    .line 712
    .line 713
    const/4 v6, 0x0

    .line 714
    :cond_21
    invoke-virtual {v12, v13}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 715
    .line 716
    .line 717
    move-result v12

    .line 718
    if-nez v12, :cond_23

    .line 719
    .line 720
    invoke-virtual {v11, v13}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 721
    .line 722
    .line 723
    move-result v11

    .line 724
    if-nez v11, :cond_23

    .line 725
    .line 726
    invoke-virtual {v10, v13}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 727
    .line 728
    .line 729
    move-result v10

    .line 730
    if-eqz v10, :cond_22

    .line 731
    .line 732
    goto :goto_f

    .line 733
    :cond_22
    invoke-static {v13}, Lw0/a;->b(Ljava/lang/String;)Z

    .line 734
    .line 735
    .line 736
    move-result v10

    .line 737
    if-eqz v10, :cond_23

    .line 738
    .line 739
    invoke-virtual {v9, v13}, Lu0/k;->a(Ljava/lang/String;)Ljava/lang/String;

    .line 740
    .line 741
    .line 742
    move-result-object v9

    .line 743
    if-nez v9, :cond_24

    .line 744
    .line 745
    :cond_23
    :goto_f
    invoke-virtual {v3, v13, v8}, LA/f;->z(Ljava/lang/String;Ljava/lang/String;)V

    .line 746
    .line 747
    .line 748
    :cond_24
    :goto_10
    move v8, v14

    .line 749
    move-object/from16 v6, v16

    .line 750
    .line 751
    goto :goto_e

    .line 752
    :cond_25
    const/4 v6, 0x0

    .line 753
    invoke-virtual {v9}, Lu0/k;->size()I

    .line 754
    .line 755
    .line 756
    move-result v7

    .line 757
    :goto_11
    if-ge v6, v7, :cond_28

    .line 758
    .line 759
    const/4 v8, 0x1

    .line 760
    add-int/lit8 v13, v6, 0x1

    .line 761
    .line 762
    invoke-virtual {v9, v6}, Lu0/k;->b(I)Ljava/lang/String;

    .line 763
    .line 764
    .line 765
    move-result-object v14

    .line 766
    invoke-virtual {v12, v14}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 767
    .line 768
    .line 769
    move-result v15

    .line 770
    if-nez v15, :cond_27

    .line 771
    .line 772
    invoke-virtual {v11, v14}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 773
    .line 774
    .line 775
    move-result v15

    .line 776
    if-nez v15, :cond_27

    .line 777
    .line 778
    invoke-virtual {v10, v14}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    .line 779
    .line 780
    .line 781
    move-result v15

    .line 782
    if-eqz v15, :cond_26

    .line 783
    .line 784
    goto :goto_12

    .line 785
    :cond_26
    invoke-static {v14}, Lw0/a;->b(Ljava/lang/String;)Z

    .line 786
    .line 787
    .line 788
    move-result v15

    .line 789
    if-eqz v15, :cond_27

    .line 790
    .line 791
    invoke-virtual {v9, v6}, Lu0/k;->d(I)Ljava/lang/String;

    .line 792
    .line 793
    .line 794
    move-result-object v6

    .line 795
    invoke-virtual {v3, v14, v6}, LA/f;->z(Ljava/lang/String;Ljava/lang/String;)V

    .line 796
    .line 797
    .line 798
    :cond_27
    :goto_12
    move v6, v13

    .line 799
    goto :goto_11

    .line 800
    :cond_28
    invoke-virtual {v3}, LA/f;->A()Lu0/k;

    .line 801
    .line 802
    .line 803
    move-result-object v3

    .line 804
    invoke-virtual {v3}, Lu0/k;->c()LA/f;

    .line 805
    .line 806
    .line 807
    move-result-object v3

    .line 808
    iput-object v3, v2, Lu0/t;->f:LA/f;

    .line 809
    .line 810
    iget-wide v6, v0, Lu0/u;->k:J

    .line 811
    .line 812
    iput-wide v6, v2, Lu0/t;->k:J

    .line 813
    .line 814
    iget-wide v6, v0, Lu0/u;->l:J

    .line 815
    .line 816
    iput-wide v6, v2, Lu0/t;->l:J

    .line 817
    .line 818
    invoke-static {v4}, Lw0/a;->a(Lu0/u;)Lu0/u;

    .line 819
    .line 820
    .line 821
    move-result-object v3

    .line 822
    invoke-static {v5, v3}, Lu0/t;->b(Ljava/lang/String;Lu0/u;)V

    .line 823
    .line 824
    .line 825
    iput-object v3, v2, Lu0/t;->i:Lu0/u;

    .line 826
    .line 827
    invoke-static {v0}, Lw0/a;->a(Lu0/u;)Lu0/u;

    .line 828
    .line 829
    .line 830
    move-result-object v3

    .line 831
    invoke-static {v1, v3}, Lu0/t;->b(Ljava/lang/String;Lu0/u;)V

    .line 832
    .line 833
    .line 834
    iput-object v3, v2, Lu0/t;->h:Lu0/u;

    .line 835
    .line 836
    invoke-virtual {v2}, Lu0/t;->a()Lu0/u;

    .line 837
    .line 838
    .line 839
    iget-object v0, v0, Lu0/u;->g:Lu0/w;

    .line 840
    .line 841
    invoke-static {v0}, Lj0/d;->b(Ljava/lang/Object;)V

    .line 842
    .line 843
    .line 844
    invoke-virtual {v0}, Lu0/w;->close()V

    .line 845
    .line 846
    .line 847
    const/4 v0, 0x0

    .line 848
    invoke-static {v0}, Lj0/d;->b(Ljava/lang/Object;)V

    .line 849
    .line 850
    .line 851
    throw v0

    .line 852
    :cond_29
    iget-object v2, v4, Lu0/u;->g:Lu0/w;

    .line 853
    .line 854
    if-nez v2, :cond_2a

    .line 855
    .line 856
    goto :goto_13

    .line 857
    :cond_2a
    invoke-static {v2}, Lv0/b;->c(Ljava/io/Closeable;)V

    .line 858
    .line 859
    .line 860
    :cond_2b
    :goto_13
    invoke-virtual {v0}, Lu0/u;->i()Lu0/t;

    .line 861
    .line 862
    .line 863
    move-result-object v2

    .line 864
    invoke-static {v4}, Lw0/a;->a(Lu0/u;)Lu0/u;

    .line 865
    .line 866
    .line 867
    move-result-object v3

    .line 868
    invoke-static {v5, v3}, Lu0/t;->b(Ljava/lang/String;Lu0/u;)V

    .line 869
    .line 870
    .line 871
    iput-object v3, v2, Lu0/t;->i:Lu0/u;

    .line 872
    .line 873
    invoke-static {v0}, Lw0/a;->a(Lu0/u;)Lu0/u;

    .line 874
    .line 875
    .line 876
    move-result-object v0

    .line 877
    invoke-static {v1, v0}, Lu0/t;->b(Ljava/lang/String;Lu0/u;)V

    .line 878
    .line 879
    .line 880
    iput-object v0, v2, Lu0/t;->h:Lu0/u;

    .line 881
    .line 882
    invoke-virtual {v2}, Lu0/t;->a()Lu0/u;

    .line 883
    .line 884
    .line 885
    move-result-object v0

    .line 886
    return-object v0
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
.end method
