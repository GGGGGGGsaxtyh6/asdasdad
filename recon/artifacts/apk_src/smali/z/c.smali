.class public abstract Lz/c;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final a:Ljava/lang/ThreadLocal;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 1
    new-instance v0, Ljava/lang/ThreadLocal;

    .line 2
    .line 3
    invoke-direct {v0}, Ljava/lang/ThreadLocal;-><init>()V

    .line 4
    .line 5
    .line 6
    sput-object v0, Lz/c;->a:Ljava/lang/ThreadLocal;

    .line 7
    .line 8
    return-void
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

.method public static a(Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;Landroid/content/res/Resources$Theme;)Landroid/content/res/ColorStateList;
    .locals 34

    move-object/from16 v0, p0

    move-object/from16 v1, p2

    .line 1
    invoke-static/range {p1 .. p1}, Landroid/util/Xml;->asAttributeSet(Lorg/xmlpull/v1/XmlPullParser;)Landroid/util/AttributeSet;

    move-result-object v2

    .line 2
    :goto_0
    invoke-interface/range {p1 .. p1}, Lorg/xmlpull/v1/XmlPullParser;->next()I

    move-result v3

    const/4 v4, 0x2

    if-eq v3, v4, :cond_0

    const/4 v5, 0x1

    if-eq v3, v5, :cond_0

    goto :goto_0

    :cond_0
    if-ne v3, v4, :cond_27

    .line 3
    invoke-interface/range {p1 .. p1}, Lorg/xmlpull/v1/XmlPullParser;->getName()Ljava/lang/String;

    move-result-object v3

    .line 4
    const-string v4, "selector"

    invoke-virtual {v3, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_26

    .line 5
    invoke-interface/range {p1 .. p1}, Lorg/xmlpull/v1/XmlPullParser;->getDepth()I

    move-result v3

    const/4 v4, 0x1

    add-int/2addr v3, v4

    const/16 v5, 0x14

    .line 6
    new-array v6, v5, [[I

    .line 7
    new-array v5, v5, [I

    const/4 v7, 0x0

    move v8, v7

    .line 8
    :goto_1
    invoke-interface/range {p1 .. p1}, Lorg/xmlpull/v1/XmlPullParser;->next()I

    move-result v9

    if-eq v9, v4, :cond_25

    .line 9
    invoke-interface/range {p1 .. p1}, Lorg/xmlpull/v1/XmlPullParser;->getDepth()I

    move-result v10

    const/4 v11, 0x3

    if-ge v10, v3, :cond_1

    if-eq v9, v11, :cond_25

    :cond_1
    const/4 v12, 0x2

    if-ne v9, v12, :cond_2

    if-gt v10, v3, :cond_2

    .line 10
    invoke-interface/range {p1 .. p1}, Lorg/xmlpull/v1/XmlPullParser;->getName()Ljava/lang/String;

    move-result-object v9

    const-string v10, "item"

    invoke-virtual {v9, v10}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v9

    if-nez v9, :cond_3

    :cond_2
    move-object/from16 v28, v2

    move/from16 v29, v3

    move/from16 v16, v4

    goto/16 :goto_1e

    .line 11
    :cond_3
    sget-object v9, Lw/a;->a:[I

    if-nez v1, :cond_4

    .line 12
    invoke-virtual {v0, v2, v9}, Landroid/content/res/Resources;->obtainAttributes(Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;

    move-result-object v9

    goto :goto_2

    .line 13
    :cond_4
    invoke-virtual {v1, v2, v9, v7, v7}, Landroid/content/res/Resources$Theme;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v9

    :goto_2
    const/4 v10, -0x1

    .line 14
    invoke-virtual {v9, v7, v10}, Landroid/content/res/TypedArray;->getResourceId(II)I

    move-result v13

    const v14, -0xff01

    const/16 v15, 0x1f

    if-eq v13, v10, :cond_7

    .line 15
    sget-object v10, Lz/c;->a:Ljava/lang/ThreadLocal;

    invoke-virtual {v10}, Ljava/lang/ThreadLocal;->get()Ljava/lang/Object;

    move-result-object v16

    check-cast v16, Landroid/util/TypedValue;

    if-nez v16, :cond_5

    .line 16
    new-instance v12, Landroid/util/TypedValue;

    invoke-direct {v12}, Landroid/util/TypedValue;-><init>()V

    .line 17
    invoke-virtual {v10, v12}, Ljava/lang/ThreadLocal;->set(Ljava/lang/Object;)V

    goto :goto_3

    :cond_5
    move-object/from16 v12, v16

    .line 18
    :goto_3
    invoke-virtual {v0, v13, v12, v4}, Landroid/content/res/Resources;->getValue(ILandroid/util/TypedValue;Z)V

    .line 19
    iget v10, v12, Landroid/util/TypedValue;->type:I

    const/16 v12, 0x1c

    if-lt v10, v12, :cond_6

    if-gt v10, v15, :cond_6

    goto :goto_4

    .line 20
    :cond_6
    :try_start_0
    invoke-virtual {v0, v13}, Landroid/content/res/Resources;->getXml(I)Landroid/content/res/XmlResourceParser;

    move-result-object v10

    invoke-static {v0, v10, v1}, Lz/c;->a(Landroid/content/res/Resources;Landroid/content/res/XmlResourceParser;Landroid/content/res/Resources$Theme;)Landroid/content/res/ColorStateList;

    move-result-object v10

    invoke-virtual {v10}, Landroid/content/res/ColorStateList;->getDefaultColor()I

    move-result v10
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_5

    .line 21
    :catch_0
    invoke-virtual {v9, v7, v14}, Landroid/content/res/TypedArray;->getColor(II)I

    move-result v10

    goto :goto_5

    .line 22
    :cond_7
    :goto_4
    invoke-virtual {v9, v7, v14}, Landroid/content/res/TypedArray;->getColor(II)I

    move-result v10

    .line 23
    :goto_5
    invoke-virtual {v9, v4}, Landroid/content/res/TypedArray;->hasValue(I)Z

    move-result v12

    const/high16 v13, 0x3f800000    # 1.0f

    if-eqz v12, :cond_8

    .line 24
    invoke-virtual {v9, v4, v13}, Landroid/content/res/TypedArray;->getFloat(IF)F

    move-result v11

    goto :goto_6

    .line 25
    :cond_8
    invoke-virtual {v9, v11}, Landroid/content/res/TypedArray;->hasValue(I)Z

    move-result v12

    if-eqz v12, :cond_9

    .line 26
    invoke-virtual {v9, v11, v13}, Landroid/content/res/TypedArray;->getFloat(IF)F

    move-result v11

    goto :goto_6

    :cond_9
    move v11, v13

    .line 27
    :goto_6
    sget v12, Landroid/os/Build$VERSION;->SDK_INT:I

    const/4 v14, 0x4

    const/high16 v4, -0x40800000    # -1.0f

    if-lt v12, v15, :cond_a

    const/4 v12, 0x2

    .line 28
    invoke-virtual {v9, v12}, Landroid/content/res/TypedArray;->hasValue(I)Z

    move-result v15

    if-eqz v15, :cond_a

    .line 29
    invoke-virtual {v9, v12, v4}, Landroid/content/res/TypedArray;->getFloat(IF)F

    move-result v4

    goto :goto_7

    .line 30
    :cond_a
    invoke-virtual {v9, v14, v4}, Landroid/content/res/TypedArray;->getFloat(IF)F

    move-result v4

    .line 31
    :goto_7
    invoke-virtual {v9}, Landroid/content/res/TypedArray;->recycle()V

    .line 32
    invoke-interface {v2}, Landroid/util/AttributeSet;->getAttributeCount()I

    move-result v9

    .line 33
    new-array v12, v9, [I

    move v14, v7

    move v15, v14

    :goto_8
    if-ge v15, v9, :cond_d

    .line 34
    invoke-interface {v2, v15}, Landroid/util/AttributeSet;->getAttributeNameResource(I)I

    move-result v13

    const v7, 0x10101a5

    if-eq v13, v7, :cond_c

    const v7, 0x101031f

    if-eq v13, v7, :cond_c

    const v7, 0x7f03002b

    if-eq v13, v7, :cond_c

    const v7, 0x7f0300fc

    if-eq v13, v7, :cond_c

    add-int/lit8 v7, v14, 0x1

    const/4 v0, 0x0

    .line 35
    invoke-interface {v2, v15, v0}, Landroid/util/AttributeSet;->getAttributeBooleanValue(IZ)Z

    move-result v19

    if-eqz v19, :cond_b

    goto :goto_9

    :cond_b
    neg-int v13, v13

    .line 36
    :goto_9
    aput v13, v12, v14

    move v14, v7

    :cond_c
    add-int/lit8 v15, v15, 0x1

    move-object/from16 v0, p0

    const/4 v7, 0x0

    const/high16 v13, 0x3f800000    # 1.0f

    goto :goto_8

    .line 37
    :cond_d
    invoke-static {v12, v14}, Landroid/util/StateSet;->trimStateSet([II)[I

    move-result-object v0

    const/4 v7, 0x0

    cmpl-float v9, v4, v7

    const/high16 v12, 0x42c80000    # 100.0f

    if-ltz v9, :cond_e

    cmpg-float v9, v4, v12

    if-gtz v9, :cond_e

    const/4 v9, 0x1

    :goto_a
    const/high16 v13, 0x3f800000    # 1.0f

    goto :goto_b

    :cond_e
    const/4 v9, 0x0

    goto :goto_a

    :goto_b
    cmpl-float v14, v11, v13

    if-nez v14, :cond_f

    if-nez v9, :cond_f

    move-object v7, v0

    move-object/from16 v28, v2

    move/from16 v29, v3

    const/16 v16, 0x1

    goto/16 :goto_1b

    .line 38
    :cond_f
    invoke-static {v10}, Landroid/graphics/Color;->alpha(I)I

    move-result v13

    int-to-float v13, v13

    mul-float/2addr v13, v11

    const/high16 v11, 0x3f000000    # 0.5f

    add-float/2addr v13, v11

    float-to-int v11, v13

    if-gez v11, :cond_10

    const/4 v13, 0x0

    goto :goto_c

    :cond_10
    const/16 v13, 0xff

    if-le v11, v13, :cond_11

    goto :goto_c

    :cond_11
    move v13, v11

    :goto_c
    if-eqz v9, :cond_20

    .line 39
    invoke-static {v10}, Lz/a;->a(I)Lz/a;

    move-result-object v9

    .line 40
    sget-object v10, Lz/l;->k:Lz/l;

    .line 41
    iget v11, v9, Lz/a;->b:F

    float-to-double v14, v11

    const-wide/high16 v19, 0x3ff0000000000000L    # 1.0

    cmpg-double v14, v14, v19

    if-ltz v14, :cond_12

    invoke-static {v4}, Ljava/lang/Math;->round(F)I

    move-result v14

    int-to-double v14, v14

    const-wide/16 v19, 0x0

    cmpg-double v14, v14, v19

    if-lez v14, :cond_12

    invoke-static {v4}, Ljava/lang/Math;->round(F)I

    move-result v14

    int-to-double v14, v14

    const-wide/high16 v19, 0x4059000000000000L    # 100.0

    cmpl-double v14, v14, v19

    if-ltz v14, :cond_13

    :cond_12
    move-object v7, v0

    move-object/from16 v28, v2

    move/from16 v29, v3

    const/16 v16, 0x1

    goto/16 :goto_19

    .line 42
    :cond_13
    iget v9, v9, Lz/a;->a:F

    cmpg-float v14, v9, v7

    if-gez v14, :cond_14

    move v9, v7

    goto :goto_d

    :cond_14
    const/high16 v14, 0x43b40000    # 360.0f

    invoke-static {v14, v9}, Ljava/lang/Math;->min(FF)F

    move-result v9

    :goto_d
    move/from16 v20, v7

    move v15, v11

    const/4 v14, 0x0

    const/16 v19, 0x1

    :goto_e
    sub-float v21, v20, v11

    .line 43
    invoke-static/range {v21 .. v21}, Ljava/lang/Math;->abs(F)F

    move-result v21

    const v22, 0x3ecccccd    # 0.4f

    cmpl-float v21, v21, v22

    if-ltz v21, :cond_1e

    const/high16 v21, 0x447a0000    # 1000.0f

    move/from16 v23, v7

    move/from16 v24, v12

    move/from16 v22, v21

    const/16 v25, 0x0

    :goto_f
    sub-float v26, v23, v24

    .line 44
    invoke-static/range {v26 .. v26}, Ljava/lang/Math;->abs(F)F

    move-result v26

    const v27, 0x3c23d70a    # 0.01f

    cmpl-float v26, v26, v27

    const/high16 v27, 0x40000000    # 2.0f

    if-lez v26, :cond_1a

    sub-float v26, v24, v23

    div-float v26, v26, v27

    add-float v7, v26, v23

    .line 45
    invoke-static {v7, v15, v9}, Lz/a;->b(FFF)Lz/a;

    move-result-object v12

    .line 46
    sget-object v1, Lz/l;->k:Lz/l;

    invoke-virtual {v12, v1}, Lz/a;->c(Lz/l;)I

    move-result v1

    .line 47
    invoke-static {v1}, Landroid/graphics/Color;->red(I)I

    move-result v12

    invoke-static {v12}, Lz/b;->b(I)F

    move-result v12

    .line 48
    invoke-static {v1}, Landroid/graphics/Color;->green(I)I

    move-result v28

    invoke-static/range {v28 .. v28}, Lz/b;->b(I)F

    move-result v28

    .line 49
    invoke-static {v1}, Landroid/graphics/Color;->blue(I)I

    move-result v29

    invoke-static/range {v29 .. v29}, Lz/b;->b(I)F

    move-result v29

    .line 50
    sget-object v30, Lz/b;->d:[[F

    const/16 v16, 0x1

    .line 51
    aget-object v30, v30, v16

    const/16 v18, 0x0

    aget v31, v30, v18

    mul-float v12, v12, v31

    aget v31, v30, v16

    mul-float v28, v28, v31

    add-float v28, v28, v12

    const/4 v12, 0x2

    aget v17, v30, v12

    mul-float v29, v29, v17

    add-float v29, v29, v28

    const/high16 v17, 0x42c80000    # 100.0f

    div-float v12, v29, v17

    const v28, 0x3c111aa7

    cmpg-float v28, v12, v28

    if-gtz v28, :cond_15

    const v28, 0x4461d2f7

    mul-float v12, v12, v28

    move-object/from16 v28, v2

    move/from16 v29, v3

    goto :goto_10

    :cond_15
    move-object/from16 v28, v2

    move/from16 v29, v3

    float-to-double v2, v12

    .line 52
    invoke-static {v2, v3}, Ljava/lang/Math;->cbrt(D)D

    move-result-wide v2

    double-to-float v2, v2

    const/high16 v3, 0x42e80000    # 116.0f

    mul-float/2addr v2, v3

    const/high16 v3, 0x41800000    # 16.0f

    sub-float v12, v2, v3

    :goto_10
    sub-float v2, v4, v12

    .line 53
    invoke-static {v2}, Ljava/lang/Math;->abs(F)F

    move-result v2

    const v3, 0x3e4ccccd    # 0.2f

    cmpg-float v3, v2, v3

    if-gez v3, :cond_17

    .line 54
    invoke-static {v1}, Lz/a;->a(I)Lz/a;

    move-result-object v1

    .line 55
    iget v3, v1, Lz/a;->c:F

    move/from16 v30, v2

    iget v2, v1, Lz/a;->b:F

    invoke-static {v3, v2, v9}, Lz/a;->b(FFF)Lz/a;

    move-result-object v2

    .line 56
    iget v3, v1, Lz/a;->d:F

    move/from16 v31, v7

    iget v7, v2, Lz/a;->d:F

    sub-float/2addr v3, v7

    .line 57
    iget v7, v1, Lz/a;->e:F

    move/from16 v32, v9

    iget v9, v2, Lz/a;->e:F

    sub-float/2addr v7, v9

    .line 58
    iget v9, v1, Lz/a;->f:F

    iget v2, v2, Lz/a;->f:F

    sub-float/2addr v9, v2

    mul-float/2addr v3, v3

    mul-float/2addr v7, v7

    add-float/2addr v7, v3

    mul-float/2addr v9, v9

    add-float/2addr v9, v7

    float-to-double v2, v9

    .line 59
    invoke-static {v2, v3}, Ljava/lang/Math;->sqrt(D)D

    move-result-wide v2

    move-object v7, v0

    move-object v9, v1

    const-wide v0, 0x3fe428f5c28f5c29L    # 0.63

    .line 60
    invoke-static {v2, v3, v0, v1}, Ljava/lang/Math;->pow(DD)D

    move-result-wide v0

    const-wide v2, 0x3ff68f5c28f5c28fL    # 1.41

    mul-double/2addr v0, v2

    double-to-float v0, v0

    const/high16 v1, 0x3f800000    # 1.0f

    cmpg-float v2, v0, v1

    if-gtz v2, :cond_16

    move/from16 v22, v0

    move-object/from16 v25, v9

    move/from16 v21, v30

    :cond_16
    :goto_11
    const/4 v0, 0x0

    goto :goto_12

    :cond_17
    move/from16 v31, v7

    move/from16 v32, v9

    const/high16 v1, 0x3f800000    # 1.0f

    move-object v7, v0

    goto :goto_11

    :goto_12
    cmpl-float v2, v21, v0

    if-nez v2, :cond_18

    cmpl-float v2, v22, v0

    if-nez v2, :cond_18

    :goto_13
    move-object/from16 v2, v25

    goto :goto_15

    :cond_18
    cmpg-float v2, v12, v4

    if-gez v2, :cond_19

    move/from16 v23, v31

    goto :goto_14

    :cond_19
    move/from16 v24, v31

    :goto_14
    move-object/from16 v1, p2

    move/from16 v12, v17

    move-object/from16 v2, v28

    move/from16 v3, v29

    move/from16 v9, v32

    move-object/from16 v33, v7

    move v7, v0

    move-object/from16 v0, v33

    goto/16 :goto_f

    :cond_1a
    move-object/from16 v28, v2

    move/from16 v29, v3

    move/from16 v32, v9

    move/from16 v17, v12

    const/high16 v1, 0x3f800000    # 1.0f

    const/16 v16, 0x1

    move/from16 v33, v7

    move-object v7, v0

    move/from16 v0, v33

    goto :goto_13

    :goto_15
    if-eqz v19, :cond_1c

    if-eqz v2, :cond_1b

    .line 61
    invoke-virtual {v2, v10}, Lz/a;->c(Lz/l;)I

    move-result v0

    :goto_16
    move v10, v0

    goto :goto_1a

    :cond_1b
    sub-float v2, v11, v20

    div-float v2, v2, v27

    add-float v15, v2, v20

    move-object/from16 v1, p2

    move/from16 v12, v17

    move-object/from16 v2, v28

    move/from16 v3, v29

    move/from16 v9, v32

    const/16 v19, 0x0

    :goto_17
    move-object/from16 v33, v7

    move v7, v0

    move-object/from16 v0, v33

    goto/16 :goto_e

    :cond_1c
    if-nez v2, :cond_1d

    move v11, v15

    goto :goto_18

    :cond_1d
    move-object v14, v2

    move/from16 v20, v15

    :goto_18
    sub-float v2, v11, v20

    div-float v2, v2, v27

    add-float v15, v2, v20

    move-object/from16 v1, p2

    move/from16 v12, v17

    move-object/from16 v2, v28

    move/from16 v3, v29

    move/from16 v9, v32

    goto :goto_17

    :cond_1e
    move-object v7, v0

    move-object/from16 v28, v2

    move/from16 v29, v3

    const/16 v16, 0x1

    if-nez v14, :cond_1f

    .line 62
    invoke-static {v4}, Lz/b;->a(F)I

    move-result v0

    goto :goto_16

    .line 63
    :cond_1f
    invoke-virtual {v14, v10}, Lz/a;->c(Lz/l;)I

    move-result v0

    goto :goto_16

    .line 64
    :goto_19
    invoke-static {v4}, Lz/b;->a(F)I

    move-result v0

    goto :goto_16

    :cond_20
    move-object v7, v0

    move-object/from16 v28, v2

    move/from16 v29, v3

    const/16 v16, 0x1

    :goto_1a
    const v0, 0xffffff

    and-int/2addr v0, v10

    shl-int/lit8 v1, v13, 0x18

    or-int v10, v0, v1

    :goto_1b
    add-int/lit8 v0, v8, 0x1

    .line 65
    array-length v1, v5

    const/16 v2, 0x8

    if-le v0, v1, :cond_22

    const/4 v1, 0x4

    if-gt v8, v1, :cond_21

    move v1, v2

    goto :goto_1c

    :cond_21
    mul-int/lit8 v1, v8, 0x2

    .line 66
    :goto_1c
    new-array v1, v1, [I

    const/4 v3, 0x0

    .line 67
    invoke-static {v5, v3, v1, v3, v8}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    move-object v5, v1

    .line 68
    :cond_22
    aput v10, v5, v8

    .line 69
    array-length v1, v6

    if-le v0, v1, :cond_24

    .line 70
    invoke-virtual {v6}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Class;->getComponentType()Ljava/lang/Class;

    move-result-object v1

    const/4 v3, 0x4

    if-gt v8, v3, :cond_23

    goto :goto_1d

    :cond_23
    mul-int/lit8 v2, v8, 0x2

    :goto_1d
    invoke-static {v1, v2}, Ljava/lang/reflect/Array;->newInstance(Ljava/lang/Class;I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, [Ljava/lang/Object;

    const/4 v2, 0x0

    .line 71
    invoke-static {v6, v2, v1, v2, v8}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    move-object v6, v1

    .line 72
    :cond_24
    aput-object v7, v6, v8

    .line 73
    check-cast v6, [[I

    move-object/from16 v1, p2

    move v8, v0

    move/from16 v4, v16

    move-object/from16 v2, v28

    move/from16 v3, v29

    const/4 v7, 0x0

    move-object/from16 v0, p0

    goto/16 :goto_1

    :goto_1e
    move-object/from16 v0, p0

    move-object/from16 v1, p2

    move/from16 v4, v16

    move-object/from16 v2, v28

    move/from16 v3, v29

    const/4 v7, 0x0

    goto/16 :goto_1

    .line 74
    :cond_25
    new-array v0, v8, [I

    .line 75
    new-array v1, v8, [[I

    const/4 v2, 0x0

    .line 76
    invoke-static {v5, v2, v0, v2, v8}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 77
    invoke-static {v6, v2, v1, v2, v8}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 78
    new-instance v2, Landroid/content/res/ColorStateList;

    invoke-direct {v2, v1, v0}, Landroid/content/res/ColorStateList;-><init>([[I[I)V

    return-object v2

    .line 79
    :cond_26
    new-instance v0, Lorg/xmlpull/v1/XmlPullParserException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    .line 80
    invoke-interface/range {p1 .. p1}, Lorg/xmlpull/v1/XmlPullParser;->getPositionDescription()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, ": invalid color state list tag "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Lorg/xmlpull/v1/XmlPullParserException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 81
    :cond_27
    new-instance v0, Lorg/xmlpull/v1/XmlPullParserException;

    const-string v1, "No start tag found"

    invoke-direct {v0, v1}, Lorg/xmlpull/v1/XmlPullParserException;-><init>(Ljava/lang/String;)V

    throw v0
.end method
