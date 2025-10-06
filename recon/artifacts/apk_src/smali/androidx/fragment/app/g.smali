.class public final synthetic Landroidx/fragment/app/g;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lb/b;


# instance fields
.field public final synthetic a:Lg/j;


# direct methods
.method public synthetic constructor <init>(Lg/j;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/fragment/app/g;->a:Lg/j;

    return-void
.end method


# virtual methods
.method public final a(La/m;)V
    .locals 16

    .line 1
    move-object/from16 v0, p0

    .line 2
    .line 3
    iget-object v1, v0, Landroidx/fragment/app/g;->a:Lg/j;

    .line 4
    .line 5
    iget-object v1, v1, Landroidx/fragment/app/i;->mFragments:Landroidx/fragment/app/k;

    .line 6
    .line 7
    iget-object v1, v1, Landroidx/fragment/app/k;->a:Landroidx/fragment/app/h;

    .line 8
    .line 9
    iget-object v2, v1, Landroidx/fragment/app/h;->c:Landroidx/fragment/app/u;

    .line 10
    .line 11
    iget-object v3, v2, Landroidx/fragment/app/t;->s:Landroidx/fragment/app/h;

    .line 12
    .line 13
    if-nez v3, :cond_1f

    .line 14
    .line 15
    iput-object v1, v2, Landroidx/fragment/app/t;->s:Landroidx/fragment/app/h;

    .line 16
    .line 17
    iput-object v1, v2, Landroidx/fragment/app/t;->t:Landroidx/fragment/app/h;

    .line 18
    .line 19
    iget-object v3, v2, Landroidx/fragment/app/t;->l:Ljava/util/concurrent/CopyOnWriteArrayList;

    .line 20
    .line 21
    invoke-virtual {v3, v1}, Ljava/util/concurrent/CopyOnWriteArrayList;->add(Ljava/lang/Object;)Z

    .line 22
    .line 23
    .line 24
    iget-object v1, v1, Landroidx/fragment/app/h;->d:Lg/j;

    .line 25
    .line 26
    invoke-virtual {v1}, La/m;->getOnBackPressedDispatcher()La/v;

    .line 27
    .line 28
    .line 29
    move-result-object v3

    .line 30
    iput-object v3, v2, Landroidx/fragment/app/t;->f:La/v;

    .line 31
    .line 32
    invoke-virtual {v3}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 33
    .line 34
    .line 35
    const-string v4, "onBackPressedCallback"

    .line 36
    .line 37
    iget-object v5, v2, Landroidx/fragment/app/t;->g:Landroidx/fragment/app/o;

    .line 38
    .line 39
    invoke-static {v5, v4}, Lj0/d;->e(Ljava/lang/Object;Ljava/lang/String;)V

    .line 40
    .line 41
    .line 42
    iget-object v4, v1, Landroidx/fragment/app/i;->mFragmentLifecycleRegistry:Landroidx/lifecycle/v;

    .line 43
    .line 44
    iget-object v6, v4, Landroidx/lifecycle/v;->c:Landroidx/lifecycle/n;

    .line 45
    .line 46
    sget-object v7, Landroidx/lifecycle/n;->a:Landroidx/lifecycle/n;

    .line 47
    .line 48
    if-ne v6, v7, :cond_0

    .line 49
    .line 50
    goto :goto_0

    .line 51
    :cond_0
    new-instance v6, La/s;

    .line 52
    .line 53
    invoke-direct {v6, v3, v4, v5}, La/s;-><init>(La/v;Landroidx/lifecycle/o;Landroidx/fragment/app/o;)V

    .line 54
    .line 55
    .line 56
    iget-object v4, v5, Landroidx/fragment/app/o;->b:Ljava/util/concurrent/CopyOnWriteArrayList;

    .line 57
    .line 58
    invoke-virtual {v4, v6}, Ljava/util/concurrent/CopyOnWriteArrayList;->add(Ljava/lang/Object;)Z

    .line 59
    .line 60
    .line 61
    invoke-virtual {v3}, La/v;->d()V

    .line 62
    .line 63
    .line 64
    new-instance v4, La/u;

    .line 65
    .line 66
    const/4 v6, 0x0

    .line 67
    invoke-direct {v4, v6, v3}, La/u;-><init>(ILjava/lang/Object;)V

    .line 68
    .line 69
    .line 70
    iput-object v4, v5, Landroidx/fragment/app/o;->c:La/u;

    .line 71
    .line 72
    :goto_0
    invoke-virtual {v1}, La/m;->getViewModelStore()Landroidx/lifecycle/S;

    .line 73
    .line 74
    .line 75
    move-result-object v1

    .line 76
    new-instance v3, LA/f;

    .line 77
    .line 78
    sget-object v4, Landroidx/fragment/app/w;->f:LS/b;

    .line 79
    .line 80
    invoke-direct {v3, v1, v4}, LA/f;-><init>(Landroidx/lifecycle/S;Landroidx/lifecycle/P;)V

    .line 81
    .line 82
    .line 83
    const-class v1, Landroidx/fragment/app/w;

    .line 84
    .line 85
    invoke-virtual {v3, v1}, LA/f;->B(Ljava/lang/Class;)Landroidx/lifecycle/N;

    .line 86
    .line 87
    .line 88
    move-result-object v1

    .line 89
    check-cast v1, Landroidx/fragment/app/w;

    .line 90
    .line 91
    iput-object v1, v2, Landroidx/fragment/app/t;->F:Landroidx/fragment/app/w;

    .line 92
    .line 93
    iget-object v3, v2, Landroidx/fragment/app/t;->c:Landroidx/fragment/app/y;

    .line 94
    .line 95
    iput-object v1, v3, Landroidx/fragment/app/y;->d:Landroidx/fragment/app/w;

    .line 96
    .line 97
    iget-object v1, v2, Landroidx/fragment/app/t;->s:Landroidx/fragment/app/h;

    .line 98
    .line 99
    if-eqz v1, :cond_18

    .line 100
    .line 101
    iget-object v1, v1, Landroidx/fragment/app/h;->d:Lg/j;

    .line 102
    .line 103
    invoke-virtual {v1}, La/m;->getSavedStateRegistry()LU/e;

    .line 104
    .line 105
    .line 106
    move-result-object v1

    .line 107
    new-instance v4, Landroidx/fragment/app/e;

    .line 108
    .line 109
    const/4 v5, 0x1

    .line 110
    invoke-direct {v4, v5, v2}, Landroidx/fragment/app/e;-><init>(ILjava/lang/Object;)V

    .line 111
    .line 112
    .line 113
    const-string v5, "android:support:fragments"

    .line 114
    .line 115
    invoke-virtual {v1, v5, v4}, LU/e;->c(Ljava/lang/String;LU/d;)V

    .line 116
    .line 117
    .line 118
    invoke-virtual {v1, v5}, LU/e;->a(Ljava/lang/String;)Landroid/os/Bundle;

    .line 119
    .line 120
    .line 121
    move-result-object v1

    .line 122
    if-eqz v1, :cond_18

    .line 123
    .line 124
    invoke-virtual {v1}, Landroid/os/BaseBundle;->keySet()Ljava/util/Set;

    .line 125
    .line 126
    .line 127
    move-result-object v4

    .line 128
    invoke-interface {v4}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    .line 129
    .line 130
    .line 131
    move-result-object v4

    .line 132
    :cond_1
    :goto_1
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    .line 133
    .line 134
    .line 135
    move-result v5

    .line 136
    if-eqz v5, :cond_2

    .line 137
    .line 138
    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    .line 139
    .line 140
    .line 141
    move-result-object v5

    .line 142
    check-cast v5, Ljava/lang/String;

    .line 143
    .line 144
    const-string v6, "result_"

    .line 145
    .line 146
    invoke-virtual {v5, v6}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    .line 147
    .line 148
    .line 149
    move-result v6

    .line 150
    if-eqz v6, :cond_1

    .line 151
    .line 152
    invoke-virtual {v1, v5}, Landroid/os/Bundle;->getBundle(Ljava/lang/String;)Landroid/os/Bundle;

    .line 153
    .line 154
    .line 155
    move-result-object v6

    .line 156
    if-eqz v6, :cond_1

    .line 157
    .line 158
    iget-object v7, v2, Landroidx/fragment/app/t;->s:Landroidx/fragment/app/h;

    .line 159
    .line 160
    iget-object v7, v7, Landroidx/fragment/app/h;->a:Landroidx/fragment/app/i;

    .line 161
    .line 162
    invoke-virtual {v7}, Landroid/content/Context;->getClassLoader()Ljava/lang/ClassLoader;

    .line 163
    .line 164
    .line 165
    move-result-object v7

    .line 166
    invoke-virtual {v6, v7}, Landroid/os/Bundle;->setClassLoader(Ljava/lang/ClassLoader;)V

    .line 167
    .line 168
    .line 169
    const/4 v7, 0x7

    .line 170
    invoke-virtual {v5, v7}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    .line 171
    .line 172
    .line 173
    move-result-object v5

    .line 174
    iget-object v7, v2, Landroidx/fragment/app/t;->j:Ljava/util/Map;

    .line 175
    .line 176
    invoke-interface {v7, v5, v6}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 177
    .line 178
    .line 179
    goto :goto_1

    .line 180
    :cond_2
    new-instance v4, Ljava/util/ArrayList;

    .line 181
    .line 182
    invoke-direct {v4}, Ljava/util/ArrayList;-><init>()V

    .line 183
    .line 184
    .line 185
    invoke-virtual {v1}, Landroid/os/BaseBundle;->keySet()Ljava/util/Set;

    .line 186
    .line 187
    .line 188
    move-result-object v5

    .line 189
    invoke-interface {v5}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    .line 190
    .line 191
    .line 192
    move-result-object v5

    .line 193
    :cond_3
    :goto_2
    invoke-interface {v5}, Ljava/util/Iterator;->hasNext()Z

    .line 194
    .line 195
    .line 196
    move-result v6

    .line 197
    const-string v7, "state"

    .line 198
    .line 199
    if-eqz v6, :cond_4

    .line 200
    .line 201
    invoke-interface {v5}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    .line 202
    .line 203
    .line 204
    move-result-object v6

    .line 205
    check-cast v6, Ljava/lang/String;

    .line 206
    .line 207
    const-string v8, "fragment_"

    .line 208
    .line 209
    invoke-virtual {v6, v8}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    .line 210
    .line 211
    .line 212
    move-result v8

    .line 213
    if-eqz v8, :cond_3

    .line 214
    .line 215
    invoke-virtual {v1, v6}, Landroid/os/Bundle;->getBundle(Ljava/lang/String;)Landroid/os/Bundle;

    .line 216
    .line 217
    .line 218
    move-result-object v6

    .line 219
    if-eqz v6, :cond_3

    .line 220
    .line 221
    iget-object v8, v2, Landroidx/fragment/app/t;->s:Landroidx/fragment/app/h;

    .line 222
    .line 223
    iget-object v8, v8, Landroidx/fragment/app/h;->a:Landroidx/fragment/app/i;

    .line 224
    .line 225
    invoke-virtual {v8}, Landroid/content/Context;->getClassLoader()Ljava/lang/ClassLoader;

    .line 226
    .line 227
    .line 228
    move-result-object v8

    .line 229
    invoke-virtual {v6, v8}, Landroid/os/Bundle;->setClassLoader(Ljava/lang/ClassLoader;)V

    .line 230
    .line 231
    .line 232
    invoke-virtual {v6, v7}, Landroid/os/Bundle;->getParcelable(Ljava/lang/String;)Landroid/os/Parcelable;

    .line 233
    .line 234
    .line 235
    move-result-object v6

    .line 236
    check-cast v6, Landroidx/fragment/app/x;

    .line 237
    .line 238
    invoke-virtual {v4, v6}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 239
    .line 240
    .line 241
    goto :goto_2

    .line 242
    :cond_4
    iget-object v5, v3, Landroidx/fragment/app/y;->c:Ljava/util/HashMap;

    .line 243
    .line 244
    invoke-virtual {v5}, Ljava/util/HashMap;->clear()V

    .line 245
    .line 246
    .line 247
    invoke-virtual {v4}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    .line 248
    .line 249
    .line 250
    move-result-object v4

    .line 251
    :goto_3
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    .line 252
    .line 253
    .line 254
    move-result v6

    .line 255
    if-eqz v6, :cond_5

    .line 256
    .line 257
    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    .line 258
    .line 259
    .line 260
    move-result-object v6

    .line 261
    check-cast v6, Landroidx/fragment/app/x;

    .line 262
    .line 263
    iget-object v8, v6, Landroidx/fragment/app/x;->b:Ljava/lang/String;

    .line 264
    .line 265
    invoke-virtual {v5, v8, v6}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 266
    .line 267
    .line 268
    goto :goto_3

    .line 269
    :cond_5
    invoke-virtual {v1, v7}, Landroid/os/Bundle;->getParcelable(Ljava/lang/String;)Landroid/os/Parcelable;

    .line 270
    .line 271
    .line 272
    move-result-object v1

    .line 273
    check-cast v1, Landroidx/fragment/app/v;

    .line 274
    .line 275
    if-nez v1, :cond_6

    .line 276
    .line 277
    goto/16 :goto_d

    .line 278
    .line 279
    :cond_6
    iget-object v4, v3, Landroidx/fragment/app/y;->b:Ljava/util/HashMap;

    .line 280
    .line 281
    invoke-virtual {v4}, Ljava/util/HashMap;->clear()V

    .line 282
    .line 283
    .line 284
    iget-object v6, v1, Landroidx/fragment/app/v;->a:Ljava/util/ArrayList;

    .line 285
    .line 286
    invoke-virtual {v6}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    .line 287
    .line 288
    .line 289
    move-result-object v6

    .line 290
    :goto_4
    invoke-interface {v6}, Ljava/util/Iterator;->hasNext()Z

    .line 291
    .line 292
    .line 293
    move-result v7

    .line 294
    const/4 v8, 0x0

    .line 295
    if-eqz v7, :cond_a

    .line 296
    .line 297
    invoke-interface {v6}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    .line 298
    .line 299
    .line 300
    move-result-object v7

    .line 301
    check-cast v7, Ljava/lang/String;

    .line 302
    .line 303
    invoke-virtual {v5, v7}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 304
    .line 305
    .line 306
    move-result-object v7

    .line 307
    check-cast v7, Landroidx/fragment/app/x;

    .line 308
    .line 309
    if-nez v7, :cond_7

    .line 310
    .line 311
    goto :goto_4

    .line 312
    :cond_7
    iget-object v1, v2, Landroidx/fragment/app/t;->F:Landroidx/fragment/app/w;

    .line 313
    .line 314
    iget-object v1, v1, Landroidx/fragment/app/w;->b:Ljava/util/HashMap;

    .line 315
    .line 316
    iget-object v3, v7, Landroidx/fragment/app/x;->b:Ljava/lang/String;

    .line 317
    .line 318
    invoke-virtual {v1, v3}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    .line 319
    .line 320
    .line 321
    move-result-object v1

    .line 322
    if-nez v1, :cond_9

    .line 323
    .line 324
    iget-object v1, v2, Landroidx/fragment/app/t;->s:Landroidx/fragment/app/h;

    .line 325
    .line 326
    iget-object v1, v1, Landroidx/fragment/app/h;->a:Landroidx/fragment/app/i;

    .line 327
    .line 328
    invoke-virtual {v1}, Landroid/content/Context;->getClassLoader()Ljava/lang/ClassLoader;

    .line 329
    .line 330
    .line 331
    move-result-object v1

    .line 332
    iget-object v3, v7, Landroidx/fragment/app/x;->a:Ljava/lang/String;

    .line 333
    .line 334
    iget-object v2, v2, Landroidx/fragment/app/t;->u:Landroidx/fragment/app/q;

    .line 335
    .line 336
    invoke-virtual {v2, v3}, Landroidx/fragment/app/q;->a(Ljava/lang/String;)V

    .line 337
    .line 338
    .line 339
    iget-object v2, v7, Landroidx/fragment/app/x;->j:Landroid/os/Bundle;

    .line 340
    .line 341
    if-eqz v2, :cond_8

    .line 342
    .line 343
    invoke-virtual {v2, v1}, Landroid/os/Bundle;->setClassLoader(Ljava/lang/ClassLoader;)V

    .line 344
    .line 345
    .line 346
    :cond_8
    throw v8

    .line 347
    :cond_9
    new-instance v1, Ljava/lang/ClassCastException;

    .line 348
    .line 349
    invoke-direct {v1}, Ljava/lang/ClassCastException;-><init>()V

    .line 350
    .line 351
    .line 352
    throw v1

    .line 353
    :cond_a
    iget-object v5, v2, Landroidx/fragment/app/t;->F:Landroidx/fragment/app/w;

    .line 354
    .line 355
    invoke-virtual {v5}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 356
    .line 357
    .line 358
    new-instance v6, Ljava/util/ArrayList;

    .line 359
    .line 360
    iget-object v5, v5, Landroidx/fragment/app/w;->b:Ljava/util/HashMap;

    .line 361
    .line 362
    invoke-virtual {v5}, Ljava/util/HashMap;->values()Ljava/util/Collection;

    .line 363
    .line 364
    .line 365
    move-result-object v5

    .line 366
    invoke-direct {v6, v5}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    .line 367
    .line 368
    .line 369
    invoke-virtual {v6}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    .line 370
    .line 371
    .line 372
    move-result-object v5

    .line 373
    invoke-interface {v5}, Ljava/util/Iterator;->hasNext()Z

    .line 374
    .line 375
    .line 376
    move-result v6

    .line 377
    if-nez v6, :cond_17

    .line 378
    .line 379
    iget-object v5, v1, Landroidx/fragment/app/v;->b:Ljava/util/ArrayList;

    .line 380
    .line 381
    iget-object v6, v3, Landroidx/fragment/app/y;->a:Ljava/util/ArrayList;

    .line 382
    .line 383
    invoke-virtual {v6}, Ljava/util/ArrayList;->clear()V

    .line 384
    .line 385
    .line 386
    if-eqz v5, :cond_c

    .line 387
    .line 388
    invoke-interface {v5}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    .line 389
    .line 390
    .line 391
    move-result-object v5

    .line 392
    invoke-interface {v5}, Ljava/util/Iterator;->hasNext()Z

    .line 393
    .line 394
    .line 395
    move-result v6

    .line 396
    if-nez v6, :cond_b

    .line 397
    .line 398
    goto :goto_5

    .line 399
    :cond_b
    invoke-interface {v5}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    .line 400
    .line 401
    .line 402
    move-result-object v1

    .line 403
    check-cast v1, Ljava/lang/String;

    .line 404
    .line 405
    invoke-virtual {v4, v1}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    .line 406
    .line 407
    .line 408
    move-result-object v2

    .line 409
    invoke-static {v2}, LA/e;->f(Ljava/lang/Object;)V

    .line 410
    .line 411
    .line 412
    new-instance v2, Ljava/lang/IllegalStateException;

    .line 413
    .line 414
    const-string v3, "No instantiated fragment for ("

    .line 415
    .line 416
    const-string v4, ")"

    .line 417
    .line 418
    invoke-static {v3, v1, v4}, LA/e;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    .line 419
    .line 420
    .line 421
    move-result-object v1

    .line 422
    invoke-direct {v2, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    .line 423
    .line 424
    .line 425
    throw v2

    .line 426
    :cond_c
    :goto_5
    iget-object v4, v1, Landroidx/fragment/app/v;->c:[Landroidx/fragment/app/b;

    .line 427
    .line 428
    if-eqz v4, :cond_14

    .line 429
    .line 430
    new-instance v4, Ljava/util/ArrayList;

    .line 431
    .line 432
    iget-object v6, v1, Landroidx/fragment/app/v;->c:[Landroidx/fragment/app/b;

    .line 433
    .line 434
    array-length v6, v6

    .line 435
    invoke-direct {v4, v6}, Ljava/util/ArrayList;-><init>(I)V

    .line 436
    .line 437
    .line 438
    iput-object v4, v2, Landroidx/fragment/app/t;->d:Ljava/util/ArrayList;

    .line 439
    .line 440
    const/4 v4, 0x0

    .line 441
    :goto_6
    iget-object v6, v1, Landroidx/fragment/app/v;->c:[Landroidx/fragment/app/b;

    .line 442
    .line 443
    array-length v7, v6

    .line 444
    if-ge v4, v7, :cond_13

    .line 445
    .line 446
    aget-object v6, v6, v4

    .line 447
    .line 448
    invoke-virtual {v6}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 449
    .line 450
    .line 451
    new-instance v7, Landroidx/fragment/app/a;

    .line 452
    .line 453
    invoke-direct {v7, v2}, Landroidx/fragment/app/a;-><init>(Landroidx/fragment/app/u;)V

    .line 454
    .line 455
    .line 456
    const/4 v8, 0x0

    .line 457
    const/4 v9, 0x0

    .line 458
    :goto_7
    iget-object v10, v6, Landroidx/fragment/app/b;->a:[I

    .line 459
    .line 460
    array-length v11, v10

    .line 461
    const/4 v12, 0x2

    .line 462
    const/4 v13, 0x1

    .line 463
    const-string v14, "FragmentManager"

    .line 464
    .line 465
    if-ge v8, v11, :cond_f

    .line 466
    .line 467
    new-instance v11, Landroidx/fragment/app/z;

    .line 468
    .line 469
    invoke-direct {v11}, Ljava/lang/Object;-><init>()V

    .line 470
    .line 471
    .line 472
    add-int/lit8 v15, v8, 0x1

    .line 473
    .line 474
    aget v5, v10, v8

    .line 475
    .line 476
    iput v5, v11, Landroidx/fragment/app/z;->a:I

    .line 477
    .line 478
    invoke-static {v14, v12}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    .line 479
    .line 480
    .line 481
    move-result v5

    .line 482
    if-eqz v5, :cond_d

    .line 483
    .line 484
    new-instance v5, Ljava/lang/StringBuilder;

    .line 485
    .line 486
    const-string v12, "Instantiate "

    .line 487
    .line 488
    invoke-direct {v5, v12}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    .line 489
    .line 490
    .line 491
    invoke-virtual {v5, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    .line 492
    .line 493
    .line 494
    const-string v12, " op #"

    .line 495
    .line 496
    invoke-virtual {v5, v12}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 497
    .line 498
    .line 499
    invoke-virtual {v5, v9}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    .line 500
    .line 501
    .line 502
    const-string v12, " base fragment #"

    .line 503
    .line 504
    invoke-virtual {v5, v12}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 505
    .line 506
    .line 507
    aget v12, v10, v15

    .line 508
    .line 509
    invoke-virtual {v5, v12}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    .line 510
    .line 511
    .line 512
    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    .line 513
    .line 514
    .line 515
    move-result-object v5

    .line 516
    invoke-static {v14, v5}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 517
    .line 518
    .line 519
    :cond_d
    invoke-static {}, Landroidx/lifecycle/n;->values()[Landroidx/lifecycle/n;

    .line 520
    .line 521
    .line 522
    move-result-object v5

    .line 523
    iget-object v12, v6, Landroidx/fragment/app/b;->c:[I

    .line 524
    .line 525
    aget v12, v12, v9

    .line 526
    .line 527
    aget-object v5, v5, v12

    .line 528
    .line 529
    iput-object v5, v11, Landroidx/fragment/app/z;->g:Landroidx/lifecycle/n;

    .line 530
    .line 531
    invoke-static {}, Landroidx/lifecycle/n;->values()[Landroidx/lifecycle/n;

    .line 532
    .line 533
    .line 534
    move-result-object v5

    .line 535
    iget-object v12, v6, Landroidx/fragment/app/b;->d:[I

    .line 536
    .line 537
    aget v12, v12, v9

    .line 538
    .line 539
    aget-object v5, v5, v12

    .line 540
    .line 541
    iput-object v5, v11, Landroidx/fragment/app/z;->h:Landroidx/lifecycle/n;

    .line 542
    .line 543
    add-int/lit8 v5, v8, 0x2

    .line 544
    .line 545
    aget v12, v10, v15

    .line 546
    .line 547
    if-eqz v12, :cond_e

    .line 548
    .line 549
    goto :goto_8

    .line 550
    :cond_e
    const/4 v13, 0x0

    .line 551
    :goto_8
    iput-boolean v13, v11, Landroidx/fragment/app/z;->b:Z

    .line 552
    .line 553
    add-int/lit8 v12, v8, 0x3

    .line 554
    .line 555
    aget v5, v10, v5

    .line 556
    .line 557
    iput v5, v11, Landroidx/fragment/app/z;->c:I

    .line 558
    .line 559
    add-int/lit8 v13, v8, 0x4

    .line 560
    .line 561
    aget v12, v10, v12

    .line 562
    .line 563
    iput v12, v11, Landroidx/fragment/app/z;->d:I

    .line 564
    .line 565
    add-int/lit8 v14, v8, 0x5

    .line 566
    .line 567
    aget v13, v10, v13

    .line 568
    .line 569
    iput v13, v11, Landroidx/fragment/app/z;->e:I

    .line 570
    .line 571
    add-int/lit8 v8, v8, 0x6

    .line 572
    .line 573
    aget v10, v10, v14

    .line 574
    .line 575
    iput v10, v11, Landroidx/fragment/app/z;->f:I

    .line 576
    .line 577
    iput v5, v7, Landroidx/fragment/app/a;->b:I

    .line 578
    .line 579
    iput v12, v7, Landroidx/fragment/app/a;->c:I

    .line 580
    .line 581
    iput v13, v7, Landroidx/fragment/app/a;->d:I

    .line 582
    .line 583
    iput v10, v7, Landroidx/fragment/app/a;->e:I

    .line 584
    .line 585
    iget-object v5, v7, Landroidx/fragment/app/a;->a:Ljava/util/ArrayList;

    .line 586
    .line 587
    invoke-virtual {v5, v11}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 588
    .line 589
    .line 590
    iget v5, v7, Landroidx/fragment/app/a;->b:I

    .line 591
    .line 592
    iput v5, v11, Landroidx/fragment/app/z;->c:I

    .line 593
    .line 594
    iget v5, v7, Landroidx/fragment/app/a;->c:I

    .line 595
    .line 596
    iput v5, v11, Landroidx/fragment/app/z;->d:I

    .line 597
    .line 598
    iget v5, v7, Landroidx/fragment/app/a;->d:I

    .line 599
    .line 600
    iput v5, v11, Landroidx/fragment/app/z;->e:I

    .line 601
    .line 602
    iget v5, v7, Landroidx/fragment/app/a;->e:I

    .line 603
    .line 604
    iput v5, v11, Landroidx/fragment/app/z;->f:I

    .line 605
    .line 606
    add-int/lit8 v9, v9, 0x1

    .line 607
    .line 608
    goto/16 :goto_7

    .line 609
    .line 610
    :cond_f
    iget v5, v6, Landroidx/fragment/app/b;->e:I

    .line 611
    .line 612
    iput v5, v7, Landroidx/fragment/app/a;->f:I

    .line 613
    .line 614
    iget-object v5, v6, Landroidx/fragment/app/b;->f:Ljava/lang/String;

    .line 615
    .line 616
    iput-object v5, v7, Landroidx/fragment/app/a;->h:Ljava/lang/String;

    .line 617
    .line 618
    iput-boolean v13, v7, Landroidx/fragment/app/a;->g:Z

    .line 619
    .line 620
    iget v5, v6, Landroidx/fragment/app/b;->h:I

    .line 621
    .line 622
    iput v5, v7, Landroidx/fragment/app/a;->i:I

    .line 623
    .line 624
    iget-object v5, v6, Landroidx/fragment/app/b;->i:Ljava/lang/CharSequence;

    .line 625
    .line 626
    iput-object v5, v7, Landroidx/fragment/app/a;->j:Ljava/lang/CharSequence;

    .line 627
    .line 628
    iget v5, v6, Landroidx/fragment/app/b;->j:I

    .line 629
    .line 630
    iput v5, v7, Landroidx/fragment/app/a;->k:I

    .line 631
    .line 632
    iget-object v5, v6, Landroidx/fragment/app/b;->k:Ljava/lang/CharSequence;

    .line 633
    .line 634
    iput-object v5, v7, Landroidx/fragment/app/a;->l:Ljava/lang/CharSequence;

    .line 635
    .line 636
    iget-object v5, v6, Landroidx/fragment/app/b;->l:Ljava/util/ArrayList;

    .line 637
    .line 638
    iput-object v5, v7, Landroidx/fragment/app/a;->m:Ljava/util/ArrayList;

    .line 639
    .line 640
    iget-object v5, v6, Landroidx/fragment/app/b;->m:Ljava/util/ArrayList;

    .line 641
    .line 642
    iput-object v5, v7, Landroidx/fragment/app/a;->n:Ljava/util/ArrayList;

    .line 643
    .line 644
    iget-boolean v5, v6, Landroidx/fragment/app/b;->n:Z

    .line 645
    .line 646
    iput-boolean v5, v7, Landroidx/fragment/app/a;->o:Z

    .line 647
    .line 648
    iget v5, v6, Landroidx/fragment/app/b;->g:I

    .line 649
    .line 650
    iput v5, v7, Landroidx/fragment/app/a;->q:I

    .line 651
    .line 652
    const/4 v5, 0x0

    .line 653
    :goto_9
    iget-object v8, v6, Landroidx/fragment/app/b;->b:Ljava/util/ArrayList;

    .line 654
    .line 655
    invoke-virtual {v8}, Ljava/util/ArrayList;->size()I

    .line 656
    .line 657
    .line 658
    move-result v9

    .line 659
    if-ge v5, v9, :cond_11

    .line 660
    .line 661
    invoke-virtual {v8, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    .line 662
    .line 663
    .line 664
    move-result-object v8

    .line 665
    check-cast v8, Ljava/lang/String;

    .line 666
    .line 667
    if-eqz v8, :cond_10

    .line 668
    .line 669
    iget-object v9, v7, Landroidx/fragment/app/a;->a:Ljava/util/ArrayList;

    .line 670
    .line 671
    invoke-virtual {v9, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    .line 672
    .line 673
    .line 674
    move-result-object v9

    .line 675
    check-cast v9, Landroidx/fragment/app/z;

    .line 676
    .line 677
    iget-object v10, v3, Landroidx/fragment/app/y;->b:Ljava/util/HashMap;

    .line 678
    .line 679
    invoke-virtual {v10, v8}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    .line 680
    .line 681
    .line 682
    move-result-object v8

    .line 683
    invoke-static {v8}, LA/e;->f(Ljava/lang/Object;)V

    .line 684
    .line 685
    .line 686
    invoke-virtual {v9}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 687
    .line 688
    .line 689
    :cond_10
    add-int/lit8 v5, v5, 0x1

    .line 690
    .line 691
    goto :goto_9

    .line 692
    :cond_11
    invoke-virtual {v7, v13}, Landroidx/fragment/app/a;->a(I)V

    .line 693
    .line 694
    .line 695
    invoke-static {v14, v12}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    .line 696
    .line 697
    .line 698
    move-result v5

    .line 699
    if-eqz v5, :cond_12

    .line 700
    .line 701
    new-instance v5, Ljava/lang/StringBuilder;

    .line 702
    .line 703
    const-string v6, "restoreAllState: back stack #"

    .line 704
    .line 705
    invoke-direct {v5, v6}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    .line 706
    .line 707
    .line 708
    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    .line 709
    .line 710
    .line 711
    const-string v6, " (index "

    .line 712
    .line 713
    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 714
    .line 715
    .line 716
    iget v6, v7, Landroidx/fragment/app/a;->q:I

    .line 717
    .line 718
    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    .line 719
    .line 720
    .line 721
    const-string v6, "): "

    .line 722
    .line 723
    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 724
    .line 725
    .line 726
    invoke-virtual {v5, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    .line 727
    .line 728
    .line 729
    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    .line 730
    .line 731
    .line 732
    move-result-object v5

    .line 733
    invoke-static {v14, v5}, Landroid/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 734
    .line 735
    .line 736
    new-instance v5, Landroidx/fragment/app/A;

    .line 737
    .line 738
    invoke-direct {v5}, Landroidx/fragment/app/A;-><init>()V

    .line 739
    .line 740
    .line 741
    new-instance v6, Ljava/io/PrintWriter;

    .line 742
    .line 743
    invoke-direct {v6, v5}, Ljava/io/PrintWriter;-><init>(Ljava/io/Writer;)V

    .line 744
    .line 745
    .line 746
    const-string v5, "  "

    .line 747
    .line 748
    const/4 v9, 0x0

    .line 749
    invoke-virtual {v7, v5, v6, v9}, Landroidx/fragment/app/a;->b(Ljava/lang/String;Ljava/io/PrintWriter;Z)V

    .line 750
    .line 751
    .line 752
    invoke-virtual {v6}, Ljava/io/PrintWriter;->close()V

    .line 753
    .line 754
    .line 755
    goto :goto_a

    .line 756
    :cond_12
    const/4 v9, 0x0

    .line 757
    :goto_a
    iget-object v5, v2, Landroidx/fragment/app/t;->d:Ljava/util/ArrayList;

    .line 758
    .line 759
    invoke-virtual {v5, v7}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 760
    .line 761
    .line 762
    add-int/lit8 v4, v4, 0x1

    .line 763
    .line 764
    goto/16 :goto_6

    .line 765
    .line 766
    :cond_13
    const/4 v9, 0x0

    .line 767
    goto :goto_b

    .line 768
    :cond_14
    const/4 v9, 0x0

    .line 769
    iput-object v8, v2, Landroidx/fragment/app/t;->d:Ljava/util/ArrayList;

    .line 770
    .line 771
    :goto_b
    iget-object v4, v2, Landroidx/fragment/app/t;->h:Ljava/util/concurrent/atomic/AtomicInteger;

    .line 772
    .line 773
    iget v5, v1, Landroidx/fragment/app/v;->d:I

    .line 774
    .line 775
    invoke-virtual {v4, v5}, Ljava/util/concurrent/atomic/AtomicInteger;->set(I)V

    .line 776
    .line 777
    .line 778
    iget-object v4, v1, Landroidx/fragment/app/v;->e:Ljava/lang/String;

    .line 779
    .line 780
    if-eqz v4, :cond_15

    .line 781
    .line 782
    iget-object v3, v3, Landroidx/fragment/app/y;->b:Ljava/util/HashMap;

    .line 783
    .line 784
    invoke-virtual {v3, v4}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    .line 785
    .line 786
    .line 787
    move-result-object v3

    .line 788
    invoke-static {v3}, LA/e;->f(Ljava/lang/Object;)V

    .line 789
    .line 790
    .line 791
    :cond_15
    iget-object v3, v1, Landroidx/fragment/app/v;->f:Ljava/util/ArrayList;

    .line 792
    .line 793
    if-eqz v3, :cond_16

    .line 794
    .line 795
    move v5, v9

    .line 796
    :goto_c
    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    .line 797
    .line 798
    .line 799
    move-result v4

    .line 800
    if-ge v5, v4, :cond_16

    .line 801
    .line 802
    invoke-virtual {v3, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    .line 803
    .line 804
    .line 805
    move-result-object v4

    .line 806
    check-cast v4, Ljava/lang/String;

    .line 807
    .line 808
    iget-object v6, v1, Landroidx/fragment/app/v;->g:Ljava/util/ArrayList;

    .line 809
    .line 810
    invoke-virtual {v6, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    .line 811
    .line 812
    .line 813
    move-result-object v6

    .line 814
    check-cast v6, Landroidx/fragment/app/c;

    .line 815
    .line 816
    iget-object v7, v2, Landroidx/fragment/app/t;->i:Ljava/util/Map;

    .line 817
    .line 818
    invoke-interface {v7, v4, v6}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 819
    .line 820
    .line 821
    add-int/lit8 v5, v5, 0x1

    .line 822
    .line 823
    goto :goto_c

    .line 824
    :cond_16
    new-instance v3, Ljava/util/ArrayDeque;

    .line 825
    .line 826
    iget-object v1, v1, Landroidx/fragment/app/v;->h:Ljava/util/ArrayList;

    .line 827
    .line 828
    invoke-direct {v3, v1}, Ljava/util/ArrayDeque;-><init>(Ljava/util/Collection;)V

    .line 829
    .line 830
    .line 831
    iput-object v3, v2, Landroidx/fragment/app/t;->y:Ljava/util/ArrayDeque;

    .line 832
    .line 833
    goto :goto_d

    .line 834
    :cond_17
    invoke-interface {v5}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    .line 835
    .line 836
    .line 837
    move-result-object v1

    .line 838
    invoke-virtual {v1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 839
    .line 840
    .line 841
    new-instance v1, Ljava/lang/ClassCastException;

    .line 842
    .line 843
    invoke-direct {v1}, Ljava/lang/ClassCastException;-><init>()V

    .line 844
    .line 845
    .line 846
    throw v1

    .line 847
    :cond_18
    :goto_d
    iget-object v1, v2, Landroidx/fragment/app/t;->s:Landroidx/fragment/app/h;

    .line 848
    .line 849
    if-eqz v1, :cond_19

    .line 850
    .line 851
    iget-object v1, v1, Landroidx/fragment/app/h;->d:Lg/j;

    .line 852
    .line 853
    invoke-virtual {v1}, La/m;->getActivityResultRegistry()Lc/j;

    .line 854
    .line 855
    .line 856
    move-result-object v1

    .line 857
    new-instance v3, Landroidx/fragment/app/r;

    .line 858
    .line 859
    const/4 v4, 0x2

    .line 860
    invoke-direct {v3, v4}, Landroidx/fragment/app/r;-><init>(I)V

    .line 861
    .line 862
    .line 863
    new-instance v4, Landroidx/fragment/app/n;

    .line 864
    .line 865
    const/4 v5, 0x1

    .line 866
    invoke-direct {v4, v2, v5}, Landroidx/fragment/app/n;-><init>(Landroidx/fragment/app/u;I)V

    .line 867
    .line 868
    .line 869
    const-string v5, "FragmentManager:StartActivityForResult"

    .line 870
    .line 871
    invoke-virtual {v1, v5, v3, v4}, Lc/j;->b(Ljava/lang/String;Ld/a;Lc/b;)Lc/i;

    .line 872
    .line 873
    .line 874
    move-result-object v3

    .line 875
    iput-object v3, v2, Landroidx/fragment/app/t;->v:Lc/i;

    .line 876
    .line 877
    new-instance v3, Landroidx/fragment/app/r;

    .line 878
    .line 879
    const/4 v4, 0x0

    .line 880
    invoke-direct {v3, v4}, Landroidx/fragment/app/r;-><init>(I)V

    .line 881
    .line 882
    .line 883
    new-instance v4, Landroidx/fragment/app/n;

    .line 884
    .line 885
    const/4 v5, 0x2

    .line 886
    invoke-direct {v4, v2, v5}, Landroidx/fragment/app/n;-><init>(Landroidx/fragment/app/u;I)V

    .line 887
    .line 888
    .line 889
    const-string v5, "FragmentManager:StartIntentSenderForResult"

    .line 890
    .line 891
    invoke-virtual {v1, v5, v3, v4}, Lc/j;->b(Ljava/lang/String;Ld/a;Lc/b;)Lc/i;

    .line 892
    .line 893
    .line 894
    move-result-object v3

    .line 895
    iput-object v3, v2, Landroidx/fragment/app/t;->w:Lc/i;

    .line 896
    .line 897
    new-instance v3, Landroidx/fragment/app/r;

    .line 898
    .line 899
    const/4 v4, 0x1

    .line 900
    invoke-direct {v3, v4}, Landroidx/fragment/app/r;-><init>(I)V

    .line 901
    .line 902
    .line 903
    new-instance v4, Landroidx/fragment/app/n;

    .line 904
    .line 905
    const/4 v5, 0x0

    .line 906
    invoke-direct {v4, v2, v5}, Landroidx/fragment/app/n;-><init>(Landroidx/fragment/app/u;I)V

    .line 907
    .line 908
    .line 909
    const-string v5, "FragmentManager:RequestPermissions"

    .line 910
    .line 911
    invoke-virtual {v1, v5, v3, v4}, Lc/j;->b(Ljava/lang/String;Ld/a;Lc/b;)Lc/i;

    .line 912
    .line 913
    .line 914
    move-result-object v1

    .line 915
    iput-object v1, v2, Landroidx/fragment/app/t;->x:Lc/i;

    .line 916
    .line 917
    :cond_19
    iget-object v1, v2, Landroidx/fragment/app/t;->s:Landroidx/fragment/app/h;

    .line 918
    .line 919
    if-eqz v1, :cond_1a

    .line 920
    .line 921
    iget-object v1, v1, Landroidx/fragment/app/h;->d:Lg/j;

    .line 922
    .line 923
    iget-object v3, v2, Landroidx/fragment/app/t;->m:Landroidx/fragment/app/m;

    .line 924
    .line 925
    invoke-virtual {v1, v3}, La/m;->addOnConfigurationChangedListener(LG/a;)V

    .line 926
    .line 927
    .line 928
    :cond_1a
    iget-object v1, v2, Landroidx/fragment/app/t;->s:Landroidx/fragment/app/h;

    .line 929
    .line 930
    if-eqz v1, :cond_1b

    .line 931
    .line 932
    iget-object v1, v1, Landroidx/fragment/app/h;->d:Lg/j;

    .line 933
    .line 934
    iget-object v3, v2, Landroidx/fragment/app/t;->n:Landroidx/fragment/app/m;

    .line 935
    .line 936
    invoke-virtual {v1, v3}, La/m;->addOnTrimMemoryListener(LG/a;)V

    .line 937
    .line 938
    .line 939
    :cond_1b
    iget-object v1, v2, Landroidx/fragment/app/t;->s:Landroidx/fragment/app/h;

    .line 940
    .line 941
    if-eqz v1, :cond_1c

    .line 942
    .line 943
    iget-object v1, v1, Landroidx/fragment/app/h;->d:Lg/j;

    .line 944
    .line 945
    iget-object v3, v2, Landroidx/fragment/app/t;->o:Landroidx/fragment/app/m;

    .line 946
    .line 947
    invoke-virtual {v1, v3}, La/m;->addOnMultiWindowModeChangedListener(LG/a;)V

    .line 948
    .line 949
    .line 950
    :cond_1c
    iget-object v1, v2, Landroidx/fragment/app/t;->s:Landroidx/fragment/app/h;

    .line 951
    .line 952
    if-eqz v1, :cond_1d

    .line 953
    .line 954
    iget-object v1, v1, Landroidx/fragment/app/h;->d:Lg/j;

    .line 955
    .line 956
    iget-object v3, v2, Landroidx/fragment/app/t;->p:Landroidx/fragment/app/m;

    .line 957
    .line 958
    invoke-virtual {v1, v3}, La/m;->addOnPictureInPictureModeChangedListener(LG/a;)V

    .line 959
    .line 960
    .line 961
    :cond_1d
    iget-object v1, v2, Landroidx/fragment/app/t;->s:Landroidx/fragment/app/h;

    .line 962
    .line 963
    if-eqz v1, :cond_1e

    .line 964
    .line 965
    iget-object v1, v1, Landroidx/fragment/app/h;->d:Lg/j;

    .line 966
    .line 967
    iget-object v2, v2, Landroidx/fragment/app/t;->q:Landroidx/fragment/app/p;

    .line 968
    .line 969
    invoke-virtual {v1, v2}, La/m;->addMenuProvider(LH/q;)V

    .line 970
    .line 971
    .line 972
    :cond_1e
    return-void

    .line 973
    :cond_1f
    new-instance v1, Ljava/lang/IllegalStateException;

    .line 974
    .line 975
    const-string v2, "Already attached"

    .line 976
    .line 977
    invoke-direct {v1, v2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    .line 978
    .line 979
    .line 980
    throw v1
.end method
