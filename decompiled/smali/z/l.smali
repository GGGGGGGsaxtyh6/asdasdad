.class public final Lz/l;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final k:Lz/l;


# instance fields
.field public final a:F

.field public final b:F

.field public final c:F

.field public final d:F

.field public final e:F

.field public final f:F

.field public final g:[F

.field public final h:F

.field public final i:F

.field public final j:F


# direct methods
.method static constructor <clinit>()V
    .locals 25

    .line 1
    const/4 v0, 0x3

    .line 2
    const/4 v1, 0x2

    .line 3
    const/4 v2, 0x1

    .line 4
    const/4 v3, 0x0

    .line 5
    sget-object v4, Lz/b;->c:[F

    .line 6
    .line 7
    invoke-static {}, Lz/b;->f()F

    .line 8
    .line 9
    .line 10
    move-result v5

    .line 11
    float-to-double v5, v5

    .line 12
    const-wide v7, 0x404fd4bbab8b494cL    # 63.66197723675813

    .line 13
    .line 14
    .line 15
    .line 16
    .line 17
    mul-double/2addr v5, v7

    .line 18
    const-wide/high16 v7, 0x4059000000000000L    # 100.0

    .line 19
    .line 20
    div-double/2addr v5, v7

    .line 21
    double-to-float v5, v5

    .line 22
    sget-object v6, Lz/b;->a:[[F

    .line 23
    .line 24
    aget v9, v4, v3

    .line 25
    .line 26
    aget-object v10, v6, v3

    .line 27
    .line 28
    aget v11, v10, v3

    .line 29
    .line 30
    mul-float/2addr v11, v9

    .line 31
    aget v12, v4, v2

    .line 32
    .line 33
    aget v13, v10, v2

    .line 34
    .line 35
    mul-float/2addr v13, v12

    .line 36
    add-float/2addr v13, v11

    .line 37
    aget v11, v4, v1

    .line 38
    .line 39
    aget v10, v10, v1

    .line 40
    .line 41
    mul-float/2addr v10, v11

    .line 42
    add-float/2addr v10, v13

    .line 43
    aget-object v13, v6, v2

    .line 44
    .line 45
    aget v14, v13, v3

    .line 46
    .line 47
    mul-float/2addr v14, v9

    .line 48
    aget v15, v13, v2

    .line 49
    .line 50
    mul-float/2addr v15, v12

    .line 51
    add-float/2addr v15, v14

    .line 52
    aget v13, v13, v1

    .line 53
    .line 54
    mul-float/2addr v13, v11

    .line 55
    add-float/2addr v13, v15

    .line 56
    aget-object v6, v6, v1

    .line 57
    .line 58
    aget v14, v6, v3

    .line 59
    .line 60
    mul-float/2addr v9, v14

    .line 61
    aget v14, v6, v2

    .line 62
    .line 63
    mul-float/2addr v12, v14

    .line 64
    add-float/2addr v12, v9

    .line 65
    aget v6, v6, v1

    .line 66
    .line 67
    mul-float/2addr v11, v6

    .line 68
    add-float/2addr v11, v12

    .line 69
    const/high16 v6, 0x3f800000    # 1.0f

    .line 70
    .line 71
    float-to-double v14, v6

    .line 72
    const-wide v16, 0x3feccccccccccccdL    # 0.9

    .line 73
    .line 74
    .line 75
    .line 76
    .line 77
    cmpl-double v9, v14, v16

    .line 78
    .line 79
    if-ltz v9, :cond_0

    .line 80
    .line 81
    const v9, 0x3f30a3d7    # 0.69f

    .line 82
    .line 83
    .line 84
    :goto_0
    move/from16 v19, v9

    .line 85
    .line 86
    goto :goto_1

    .line 87
    :cond_0
    const v9, 0x3f27ae14    # 0.655f

    .line 88
    .line 89
    .line 90
    goto :goto_0

    .line 91
    :goto_1
    neg-float v9, v5

    .line 92
    const/high16 v12, 0x42280000    # 42.0f

    .line 93
    .line 94
    sub-float/2addr v9, v12

    .line 95
    const/high16 v12, 0x42b80000    # 92.0f

    .line 96
    .line 97
    div-float/2addr v9, v12

    .line 98
    float-to-double v14, v9

    .line 99
    invoke-static {v14, v15}, Ljava/lang/Math;->exp(D)D

    .line 100
    .line 101
    .line 102
    move-result-wide v14

    .line 103
    double-to-float v9, v14

    .line 104
    const v12, 0x3e8e38e4

    .line 105
    .line 106
    .line 107
    mul-float/2addr v9, v12

    .line 108
    const/high16 v12, 0x3f800000    # 1.0f

    .line 109
    .line 110
    sub-float v9, v12, v9

    .line 111
    .line 112
    mul-float/2addr v9, v6

    .line 113
    float-to-double v14, v9

    .line 114
    const-wide/high16 v16, 0x3ff0000000000000L    # 1.0

    .line 115
    .line 116
    cmpl-double v16, v14, v16

    .line 117
    .line 118
    if-lez v16, :cond_1

    .line 119
    .line 120
    move v9, v12

    .line 121
    goto :goto_2

    .line 122
    :cond_1
    const-wide/16 v16, 0x0

    .line 123
    .line 124
    cmpg-double v14, v14, v16

    .line 125
    .line 126
    if-gez v14, :cond_2

    .line 127
    .line 128
    const/4 v9, 0x0

    .line 129
    :cond_2
    :goto_2
    const/high16 v14, 0x42c80000    # 100.0f

    .line 130
    .line 131
    div-float v15, v14, v10

    .line 132
    .line 133
    mul-float/2addr v15, v9

    .line 134
    add-float/2addr v15, v12

    .line 135
    sub-float/2addr v15, v9

    .line 136
    div-float v16, v14, v13

    .line 137
    .line 138
    mul-float v16, v16, v9

    .line 139
    .line 140
    add-float v16, v16, v12

    .line 141
    .line 142
    sub-float v16, v16, v9

    .line 143
    .line 144
    div-float/2addr v14, v11

    .line 145
    mul-float/2addr v14, v9

    .line 146
    add-float/2addr v14, v12

    .line 147
    sub-float/2addr v14, v9

    .line 148
    new-array v9, v0, [F

    .line 149
    .line 150
    aput v15, v9, v3

    .line 151
    .line 152
    aput v16, v9, v2

    .line 153
    .line 154
    aput v14, v9, v1

    .line 155
    .line 156
    const/high16 v14, 0x40a00000    # 5.0f

    .line 157
    .line 158
    mul-float/2addr v14, v5

    .line 159
    add-float/2addr v14, v12

    .line 160
    div-float v14, v12, v14

    .line 161
    .line 162
    mul-float v15, v14, v14

    .line 163
    .line 164
    mul-float/2addr v15, v14

    .line 165
    mul-float/2addr v15, v14

    .line 166
    sub-float/2addr v12, v15

    .line 167
    mul-float/2addr v15, v5

    .line 168
    const v14, 0x3dcccccd    # 0.1f

    .line 169
    .line 170
    .line 171
    mul-float/2addr v14, v12

    .line 172
    mul-float/2addr v14, v12

    .line 173
    const-wide/high16 v16, 0x4014000000000000L    # 5.0

    .line 174
    .line 175
    float-to-double v0, v5

    .line 176
    mul-double v0, v0, v16

    .line 177
    .line 178
    invoke-static {v0, v1}, Ljava/lang/Math;->cbrt(D)D

    .line 179
    .line 180
    .line 181
    move-result-wide v0

    .line 182
    double-to-float v0, v0

    .line 183
    mul-float/2addr v14, v0

    .line 184
    add-float v0, v14, v15

    .line 185
    .line 186
    invoke-static {}, Lz/b;->f()F

    .line 187
    .line 188
    .line 189
    move-result v1

    .line 190
    aget v4, v4, v2

    .line 191
    .line 192
    div-float v15, v1, v4

    .line 193
    .line 194
    float-to-double v4, v15

    .line 195
    move v1, v13

    .line 196
    invoke-static {v4, v5}, Ljava/lang/Math;->sqrt(D)D

    .line 197
    .line 198
    .line 199
    move-result-wide v12

    .line 200
    double-to-float v12, v12

    .line 201
    const v13, 0x3fbd70a4    # 1.48f

    .line 202
    .line 203
    .line 204
    add-float v24, v12, v13

    .line 205
    .line 206
    const-wide v12, 0x3fc999999999999aL    # 0.2

    .line 207
    .line 208
    .line 209
    .line 210
    .line 211
    invoke-static {v4, v5, v12, v13}, Ljava/lang/Math;->pow(DD)D

    .line 212
    .line 213
    .line 214
    move-result-wide v4

    .line 215
    double-to-float v4, v4

    .line 216
    const v5, 0x3f39999a    # 0.725f

    .line 217
    .line 218
    .line 219
    div-float v4, v5, v4

    .line 220
    .line 221
    aget v5, v9, v3

    .line 222
    .line 223
    mul-float/2addr v5, v0

    .line 224
    mul-float/2addr v5, v10

    .line 225
    float-to-double v12, v5

    .line 226
    div-double/2addr v12, v7

    .line 227
    move v5, v15

    .line 228
    const-wide v14, 0x3fdae147ae147ae1L    # 0.42

    .line 229
    .line 230
    .line 231
    .line 232
    .line 233
    invoke-static {v12, v13, v14, v15}, Ljava/lang/Math;->pow(DD)D

    .line 234
    .line 235
    .line 236
    move-result-wide v12

    .line 237
    double-to-float v13, v12

    .line 238
    aget v12, v9, v2

    .line 239
    .line 240
    mul-float/2addr v12, v0

    .line 241
    mul-float/2addr v12, v1

    .line 242
    float-to-double v2, v12

    .line 243
    div-double/2addr v2, v7

    .line 244
    invoke-static {v2, v3, v14, v15}, Ljava/lang/Math;->pow(DD)D

    .line 245
    .line 246
    .line 247
    move-result-wide v2

    .line 248
    double-to-float v2, v2

    .line 249
    const/4 v3, 0x2

    .line 250
    aget v12, v9, v3

    .line 251
    .line 252
    mul-float/2addr v12, v0

    .line 253
    mul-float/2addr v12, v11

    .line 254
    float-to-double v11, v12

    .line 255
    div-double/2addr v11, v7

    .line 256
    invoke-static {v11, v12, v14, v15}, Ljava/lang/Math;->pow(DD)D

    .line 257
    .line 258
    .line 259
    move-result-wide v7

    .line 260
    double-to-float v7, v7

    .line 261
    const/4 v8, 0x3

    .line 262
    new-array v10, v8, [F

    .line 263
    .line 264
    const/4 v8, 0x0

    .line 265
    aput v13, v10, v8

    .line 266
    .line 267
    const/4 v1, 0x1

    .line 268
    aput v2, v10, v1

    .line 269
    .line 270
    aput v7, v10, v3

    .line 271
    .line 272
    aget v2, v10, v8

    .line 273
    .line 274
    const/high16 v7, 0x43c80000    # 400.0f

    .line 275
    .line 276
    mul-float v8, v2, v7

    .line 277
    .line 278
    const v11, 0x41d90a3d    # 27.13f

    .line 279
    .line 280
    .line 281
    add-float/2addr v2, v11

    .line 282
    div-float/2addr v8, v2

    .line 283
    aget v2, v10, v1

    .line 284
    .line 285
    mul-float v13, v2, v7

    .line 286
    .line 287
    add-float/2addr v2, v11

    .line 288
    div-float/2addr v13, v2

    .line 289
    aget v2, v10, v3

    .line 290
    .line 291
    mul-float/2addr v7, v2

    .line 292
    add-float/2addr v2, v11

    .line 293
    div-float/2addr v7, v2

    .line 294
    const/4 v2, 0x3

    .line 295
    new-array v2, v2, [F

    .line 296
    .line 297
    const/4 v10, 0x0

    .line 298
    aput v8, v2, v10

    .line 299
    .line 300
    aput v13, v2, v1

    .line 301
    .line 302
    aput v7, v2, v3

    .line 303
    .line 304
    const/high16 v7, 0x40000000    # 2.0f

    .line 305
    .line 306
    aget v8, v2, v10

    .line 307
    .line 308
    mul-float/2addr v8, v7

    .line 309
    aget v1, v2, v1

    .line 310
    .line 311
    add-float/2addr v8, v1

    .line 312
    const v1, 0x3d4ccccd    # 0.05f

    .line 313
    .line 314
    .line 315
    aget v2, v2, v3

    .line 316
    .line 317
    mul-float/2addr v2, v1

    .line 318
    add-float/2addr v2, v8

    .line 319
    mul-float v16, v2, v4

    .line 320
    .line 321
    new-instance v1, Lz/l;

    .line 322
    .line 323
    float-to-double v2, v0

    .line 324
    const-wide/high16 v7, 0x3fd0000000000000L    # 0.25

    .line 325
    .line 326
    invoke-static {v2, v3, v7, v8}, Ljava/lang/Math;->pow(DD)D

    .line 327
    .line 328
    .line 329
    move-result-wide v2

    .line 330
    double-to-float v2, v2

    .line 331
    move-object v14, v1

    .line 332
    move v15, v5

    .line 333
    move/from16 v17, v4

    .line 334
    .line 335
    move/from16 v18, v4

    .line 336
    .line 337
    move/from16 v20, v6

    .line 338
    .line 339
    move-object/from16 v21, v9

    .line 340
    .line 341
    move/from16 v22, v0

    .line 342
    .line 343
    move/from16 v23, v2

    .line 344
    .line 345
    invoke-direct/range {v14 .. v24}, Lz/l;-><init>(FFFFFF[FFFF)V

    .line 346
    .line 347
    .line 348
    sput-object v1, Lz/l;->k:Lz/l;

    .line 349
    .line 350
    return-void
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

.method public constructor <init>(FFFFFF[FFFF)V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 2
    .line 3
    .line 4
    iput p1, p0, Lz/l;->f:F

    .line 5
    .line 6
    iput p2, p0, Lz/l;->a:F

    .line 7
    .line 8
    iput p3, p0, Lz/l;->b:F

    .line 9
    .line 10
    iput p4, p0, Lz/l;->c:F

    .line 11
    .line 12
    iput p5, p0, Lz/l;->d:F

    .line 13
    .line 14
    iput p6, p0, Lz/l;->e:F

    .line 15
    .line 16
    iput-object p7, p0, Lz/l;->g:[F

    .line 17
    .line 18
    iput p8, p0, Lz/l;->h:F

    .line 19
    .line 20
    iput p9, p0, Lz/l;->i:F

    .line 21
    .line 22
    iput p10, p0, Lz/l;->j:F

    .line 23
    .line 24
    return-void
.end method
