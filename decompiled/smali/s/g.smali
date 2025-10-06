.class public abstract Ls/g;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final a:[Z


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 1
    const/4 v0, 0x3

    .line 2
    new-array v0, v0, [Z

    .line 3
    .line 4
    sput-object v0, Ls/g;->a:[Z

    .line 5
    .line 6
    return-void
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

.method public static a(Ls/e;Lq/c;Ljava/util/ArrayList;I)V
    .locals 36

    move-object/from16 v0, p0

    move-object/from16 v10, p1

    move-object/from16 v11, p2

    const/4 v12, 0x2

    if-nez p3, :cond_0

    .line 1
    iget v1, v0, Ls/e;->y0:I

    .line 2
    iget-object v2, v0, Ls/e;->B0:[Ls/b;

    move v14, v1

    move-object v15, v2

    const/16 v16, 0x0

    goto :goto_0

    .line 3
    :cond_0
    iget v1, v0, Ls/e;->z0:I

    .line 4
    iget-object v2, v0, Ls/e;->A0:[Ls/b;

    move v14, v1

    move-object v15, v2

    move/from16 v16, v12

    :goto_0
    const/4 v9, 0x0

    :goto_1
    if-ge v9, v14, :cond_70

    .line 5
    aget-object v1, v15, v9

    .line 6
    iget-boolean v2, v1, Ls/b;->q:Z

    .line 7
    iget-object v8, v1, Ls/b;->a:Ls/d;

    const/4 v3, 0x3

    const/4 v4, 0x1

    const/16 v7, 0x8

    const/16 v17, 0x0

    if-nez v2, :cond_19

    .line 8
    iget v2, v1, Ls/b;->l:I

    mul-int/lit8 v6, v2, 0x2

    move-object v13, v8

    move-object/from16 v20, v13

    const/16 v18, 0x0

    :goto_2
    if-nez v18, :cond_14

    .line 9
    iget v5, v1, Ls/b;->i:I

    add-int/2addr v5, v4

    iput v5, v1, Ls/b;->i:I

    .line 10
    iget-object v5, v13, Ls/d;->l0:[Ls/d;

    aput-object v17, v5, v2

    .line 11
    iget-object v5, v13, Ls/d;->k0:[Ls/d;

    aput-object v17, v5, v2

    .line 12
    iget v5, v13, Ls/d;->f0:I

    .line 13
    iget-object v4, v13, Ls/d;->P:[Ls/c;

    if-eq v5, v7, :cond_f

    .line 14
    invoke-virtual {v13, v2}, Ls/d;->h(I)I

    .line 15
    aget-object v5, v4, v6

    invoke-virtual {v5}, Ls/c;->d()I

    add-int/lit8 v5, v6, 0x1

    .line 16
    aget-object v23, v4, v5

    invoke-virtual/range {v23 .. v23}, Ls/c;->d()I

    .line 17
    aget-object v23, v4, v6

    invoke-virtual/range {v23 .. v23}, Ls/c;->d()I

    .line 18
    aget-object v5, v4, v5

    invoke-virtual {v5}, Ls/c;->d()I

    .line 19
    iget-object v5, v1, Ls/b;->b:Ls/d;

    if-nez v5, :cond_1

    .line 20
    iput-object v13, v1, Ls/b;->b:Ls/d;

    .line 21
    :cond_1
    iput-object v13, v1, Ls/b;->d:Ls/d;

    .line 22
    iget-object v5, v13, Ls/d;->o0:[I

    aget v5, v5, v2

    if-ne v5, v3, :cond_f

    .line 23
    iget-object v7, v13, Ls/d;->t:[I

    aget v7, v7, v2

    if-eqz v7, :cond_3

    if-eq v7, v3, :cond_3

    if-ne v7, v12, :cond_2

    goto :goto_3

    :cond_2
    move/from16 v25, v9

    goto :goto_5

    .line 24
    :cond_3
    :goto_3
    iget v12, v1, Ls/b;->j:I

    const/16 v22, 0x1

    add-int/lit8 v12, v12, 0x1

    iput v12, v1, Ls/b;->j:I

    .line 25
    iget-object v12, v13, Ls/d;->j0:[F

    aget v12, v12, v2

    const/16 v21, 0x0

    cmpl-float v24, v12, v21

    if-lez v24, :cond_4

    .line 26
    iget v3, v1, Ls/b;->k:F

    add-float/2addr v3, v12

    iput v3, v1, Ls/b;->k:F

    .line 27
    :cond_4
    iget v3, v13, Ls/d;->f0:I

    move/from16 v25, v9

    const/16 v9, 0x8

    if-eq v3, v9, :cond_8

    const/4 v3, 0x3

    if-ne v5, v3, :cond_8

    if-eqz v7, :cond_5

    if-ne v7, v3, :cond_8

    :cond_5
    const/4 v3, 0x0

    cmpg-float v5, v12, v3

    if-gez v5, :cond_6

    const/4 v3, 0x1

    .line 28
    iput-boolean v3, v1, Ls/b;->n:Z

    goto :goto_4

    :cond_6
    const/4 v3, 0x1

    .line 29
    iput-boolean v3, v1, Ls/b;->o:Z

    .line 30
    :goto_4
    iget-object v3, v1, Ls/b;->h:Ljava/util/ArrayList;

    if-nez v3, :cond_7

    .line 31
    new-instance v3, Ljava/util/ArrayList;

    invoke-direct {v3}, Ljava/util/ArrayList;-><init>()V

    iput-object v3, v1, Ls/b;->h:Ljava/util/ArrayList;

    .line 32
    :cond_7
    iget-object v3, v1, Ls/b;->h:Ljava/util/ArrayList;

    invoke-virtual {v3, v13}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 33
    :cond_8
    iget-object v3, v1, Ls/b;->f:Ls/d;

    if-nez v3, :cond_9

    .line 34
    iput-object v13, v1, Ls/b;->f:Ls/d;

    .line 35
    :cond_9
    iget-object v3, v1, Ls/b;->g:Ls/d;

    if-eqz v3, :cond_a

    .line 36
    iget-object v3, v3, Ls/d;->k0:[Ls/d;

    aput-object v13, v3, v2

    .line 37
    :cond_a
    iput-object v13, v1, Ls/b;->g:Ls/d;

    :goto_5
    if-nez v2, :cond_c

    .line 38
    iget v3, v13, Ls/d;->r:I

    if-eqz v3, :cond_b

    goto :goto_6

    .line 39
    :cond_b
    iget v3, v13, Ls/d;->u:I

    if-nez v3, :cond_e

    iget v3, v13, Ls/d;->v:I

    goto :goto_6

    .line 40
    :cond_c
    iget v3, v13, Ls/d;->s:I

    if-eqz v3, :cond_d

    goto :goto_6

    .line 41
    :cond_d
    iget v3, v13, Ls/d;->x:I

    if-nez v3, :cond_e

    iget v3, v13, Ls/d;->y:I

    :cond_e
    :goto_6
    move-object/from16 v3, v20

    goto :goto_7

    :cond_f
    move/from16 v25, v9

    goto :goto_6

    :goto_7
    if-eq v3, v13, :cond_10

    .line 42
    iget-object v3, v3, Ls/d;->l0:[Ls/d;

    aput-object v13, v3, v2

    :cond_10
    add-int/lit8 v3, v6, 0x1

    .line 43
    aget-object v3, v4, v3

    iget-object v3, v3, Ls/c;->f:Ls/c;

    if-eqz v3, :cond_11

    .line 44
    iget-object v3, v3, Ls/c;->d:Ls/d;

    iget-object v4, v3, Ls/d;->P:[Ls/c;

    aget-object v4, v4, v6

    iget-object v4, v4, Ls/c;->f:Ls/c;

    if-eqz v4, :cond_11

    iget-object v4, v4, Ls/c;->d:Ls/d;

    if-eq v4, v13, :cond_12

    :cond_11
    move-object/from16 v3, v17

    :cond_12
    if-eqz v3, :cond_13

    goto :goto_8

    :cond_13
    move-object v3, v13

    const/16 v18, 0x1

    :goto_8
    move-object/from16 v20, v13

    move/from16 v9, v25

    const/4 v4, 0x1

    const/16 v7, 0x8

    const/4 v12, 0x2

    move-object v13, v3

    const/4 v3, 0x3

    goto/16 :goto_2

    :cond_14
    move/from16 v25, v9

    .line 45
    iget-object v3, v1, Ls/b;->b:Ls/d;

    if-eqz v3, :cond_15

    .line 46
    iget-object v3, v3, Ls/d;->P:[Ls/c;

    aget-object v3, v3, v6

    invoke-virtual {v3}, Ls/c;->d()I

    .line 47
    :cond_15
    iget-object v3, v1, Ls/b;->d:Ls/d;

    if-eqz v3, :cond_16

    add-int/lit8 v6, v6, 0x1

    .line 48
    iget-object v3, v3, Ls/d;->P:[Ls/c;

    aget-object v3, v3, v6

    invoke-virtual {v3}, Ls/c;->d()I

    .line 49
    :cond_16
    iput-object v13, v1, Ls/b;->c:Ls/d;

    if-nez v2, :cond_17

    .line 50
    iget-boolean v2, v1, Ls/b;->m:Z

    if-eqz v2, :cond_17

    .line 51
    iput-object v13, v1, Ls/b;->e:Ls/d;

    goto :goto_9

    .line 52
    :cond_17
    iput-object v8, v1, Ls/b;->e:Ls/d;

    .line 53
    :goto_9
    iget-boolean v2, v1, Ls/b;->o:Z

    if-eqz v2, :cond_18

    iget-boolean v2, v1, Ls/b;->n:Z

    if-eqz v2, :cond_18

    const/4 v2, 0x1

    goto :goto_a

    :cond_18
    const/4 v2, 0x0

    :goto_a
    iput-boolean v2, v1, Ls/b;->p:Z

    const/4 v2, 0x1

    goto :goto_b

    :cond_19
    move/from16 v25, v9

    move v2, v4

    .line 54
    :goto_b
    iput-boolean v2, v1, Ls/b;->q:Z

    if-eqz v11, :cond_1b

    .line 55
    invoke-virtual {v11, v8}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_1a

    goto :goto_c

    :cond_1a
    move/from16 v30, v14

    move-object/from16 v31, v15

    move/from16 v23, v25

    const/16 v19, 0x0

    goto/16 :goto_4b

    .line 56
    :cond_1b
    :goto_c
    iget-object v12, v1, Ls/b;->c:Ls/d;

    .line 57
    iget-object v13, v1, Ls/b;->b:Ls/d;

    .line 58
    iget-object v9, v1, Ls/b;->d:Ls/d;

    .line 59
    iget-object v2, v1, Ls/b;->e:Ls/d;

    .line 60
    iget v3, v1, Ls/b;->k:F

    .line 61
    iget-object v4, v0, Ls/d;->o0:[I

    aget v4, v4, p3

    const/4 v7, 0x2

    if-ne v4, v7, :cond_1c

    const/4 v4, 0x1

    goto :goto_d

    :cond_1c
    const/4 v4, 0x0

    :goto_d
    if-nez p3, :cond_20

    .line 62
    iget v5, v2, Ls/d;->h0:I

    const/4 v6, 0x1

    if-nez v5, :cond_1d

    const/16 v22, 0x1

    goto :goto_e

    :cond_1d
    const/16 v22, 0x0

    :goto_e
    if-ne v5, v6, :cond_1e

    move/from16 v18, v6

    goto :goto_f

    :cond_1e
    const/16 v18, 0x0

    :goto_f
    if-ne v5, v7, :cond_1f

    move v5, v6

    goto :goto_10

    :cond_1f
    const/4 v5, 0x0

    :goto_10
    move/from16 v26, v3

    move-object v7, v8

    move/from16 v20, v22

    :goto_11
    const/4 v6, 0x0

    goto :goto_15

    :cond_20
    const/4 v6, 0x1

    .line 63
    iget v5, v2, Ls/d;->i0:I

    if-nez v5, :cond_21

    move/from16 v18, v6

    goto :goto_12

    :cond_21
    const/16 v18, 0x0

    :goto_12
    if-ne v5, v6, :cond_22

    const/4 v6, 0x1

    goto :goto_13

    :cond_22
    const/4 v6, 0x0

    :goto_13
    if-ne v5, v7, :cond_23

    const/4 v5, 0x1

    goto :goto_14

    :cond_23
    const/4 v5, 0x0

    :goto_14
    move/from16 v26, v3

    move-object v7, v8

    move/from16 v20, v18

    move/from16 v18, v6

    goto :goto_11

    .line 64
    :goto_15
    iget-object v3, v0, Ls/d;->P:[Ls/c;

    if-nez v6, :cond_31

    .line 65
    iget-object v11, v7, Ls/d;->P:[Ls/c;

    aget-object v11, v11, v16

    if-eqz v5, :cond_24

    const/16 v27, 0x1

    goto :goto_16

    :cond_24
    const/16 v27, 0x4

    .line 66
    :goto_16
    invoke-virtual {v11}, Ls/c;->d()I

    move-result v28

    move/from16 v29, v6

    .line 67
    iget-object v6, v7, Ls/d;->o0:[I

    move/from16 v30, v14

    aget v14, v6, p3

    move-object/from16 v31, v15

    const/4 v15, 0x3

    if-ne v14, v15, :cond_25

    iget-object v14, v7, Ls/d;->t:[I

    aget v14, v14, p3

    if-nez v14, :cond_25

    const/4 v14, 0x1

    goto :goto_17

    :cond_25
    const/4 v14, 0x0

    .line 68
    :goto_17
    iget-object v15, v11, Ls/c;->f:Ls/c;

    if-eqz v15, :cond_26

    if-eq v7, v8, :cond_26

    .line 69
    invoke-virtual {v15}, Ls/c;->d()I

    move-result v15

    add-int v28, v15, v28

    :cond_26
    move/from16 v15, v28

    if-eqz v5, :cond_27

    if-eq v7, v8, :cond_27

    if-eq v7, v13, :cond_27

    move-object/from16 v28, v2

    const/16 v27, 0x8

    goto :goto_18

    :cond_27
    move-object/from16 v28, v2

    .line 70
    :goto_18
    iget-object v2, v11, Ls/c;->f:Ls/c;

    if-eqz v2, :cond_2b

    if-ne v7, v13, :cond_28

    move-object/from16 v32, v8

    .line 71
    iget-object v8, v11, Ls/c;->i:Lq/g;

    iget-object v2, v2, Ls/c;->i:Lq/g;

    move-object/from16 v33, v1

    const/4 v1, 0x6

    invoke-virtual {v10, v8, v2, v15, v1}, Lq/c;->f(Lq/g;Lq/g;II)V

    goto :goto_19

    :cond_28
    move-object/from16 v33, v1

    move-object/from16 v32, v8

    .line 72
    iget-object v1, v11, Ls/c;->i:Lq/g;

    iget-object v2, v2, Ls/c;->i:Lq/g;

    const/16 v8, 0x8

    invoke-virtual {v10, v1, v2, v15, v8}, Lq/c;->f(Lq/g;Lq/g;II)V

    :goto_19
    if-eqz v14, :cond_29

    if-nez v5, :cond_29

    const/16 v27, 0x5

    :cond_29
    if-ne v7, v13, :cond_2a

    if-eqz v5, :cond_2a

    .line 73
    iget-object v1, v7, Ls/d;->R:[Z

    aget-boolean v1, v1, p3

    if-eqz v1, :cond_2a

    const/4 v1, 0x5

    goto :goto_1a

    :cond_2a
    move/from16 v1, v27

    .line 74
    :goto_1a
    iget-object v2, v11, Ls/c;->i:Lq/g;

    iget-object v8, v11, Ls/c;->f:Ls/c;

    iget-object v8, v8, Ls/c;->i:Lq/g;

    invoke-virtual {v10, v2, v8, v15, v1}, Lq/c;->e(Lq/g;Lq/g;II)V

    goto :goto_1b

    :cond_2b
    move-object/from16 v33, v1

    move-object/from16 v32, v8

    .line 75
    :goto_1b
    iget-object v1, v7, Ls/d;->P:[Ls/c;

    if-eqz v4, :cond_2d

    .line 76
    iget v2, v7, Ls/d;->f0:I

    const/16 v8, 0x8

    if-eq v2, v8, :cond_2c

    .line 77
    aget v2, v6, p3

    const/4 v6, 0x3

    if-ne v2, v6, :cond_2c

    add-int/lit8 v2, v16, 0x1

    .line 78
    aget-object v2, v1, v2

    iget-object v2, v2, Ls/c;->i:Lq/g;

    aget-object v6, v1, v16

    iget-object v6, v6, Ls/c;->i:Lq/g;

    const/4 v8, 0x0

    const/4 v11, 0x5

    invoke-virtual {v10, v2, v6, v8, v11}, Lq/c;->f(Lq/g;Lq/g;II)V

    goto :goto_1c

    :cond_2c
    const/4 v8, 0x0

    .line 79
    :goto_1c
    aget-object v2, v1, v16

    iget-object v2, v2, Ls/c;->i:Lq/g;

    aget-object v3, v3, v16

    iget-object v3, v3, Ls/c;->i:Lq/g;

    const/16 v6, 0x8

    invoke-virtual {v10, v2, v3, v8, v6}, Lq/c;->f(Lq/g;Lq/g;II)V

    :cond_2d
    add-int/lit8 v2, v16, 0x1

    .line 80
    aget-object v1, v1, v2

    iget-object v1, v1, Ls/c;->f:Ls/c;

    if-eqz v1, :cond_2e

    .line 81
    iget-object v1, v1, Ls/c;->d:Ls/d;

    iget-object v2, v1, Ls/d;->P:[Ls/c;

    aget-object v2, v2, v16

    iget-object v2, v2, Ls/c;->f:Ls/c;

    if-eqz v2, :cond_2e

    iget-object v2, v2, Ls/c;->d:Ls/d;

    if-eq v2, v7, :cond_2f

    :cond_2e
    move-object/from16 v1, v17

    :cond_2f
    if-eqz v1, :cond_30

    move-object v7, v1

    move/from16 v6, v29

    goto :goto_1d

    :cond_30
    const/4 v6, 0x1

    :goto_1d
    move-object/from16 v11, p2

    move-object/from16 v2, v28

    move/from16 v14, v30

    move-object/from16 v15, v31

    move-object/from16 v8, v32

    move-object/from16 v1, v33

    goto/16 :goto_15

    :cond_31
    move-object/from16 v33, v1

    move-object/from16 v28, v2

    move-object/from16 v32, v8

    move/from16 v30, v14

    move-object/from16 v31, v15

    if-eqz v9, :cond_34

    .line 82
    iget-object v1, v12, Ls/d;->P:[Ls/c;

    add-int/lit8 v2, v16, 0x1

    aget-object v1, v1, v2

    iget-object v1, v1, Ls/c;->f:Ls/c;

    if-eqz v1, :cond_34

    .line 83
    iget-object v1, v9, Ls/d;->P:[Ls/c;

    aget-object v1, v1, v2

    .line 84
    iget-object v6, v9, Ls/d;->o0:[I

    aget v6, v6, p3

    const/4 v7, 0x3

    if-ne v6, v7, :cond_32

    iget-object v6, v9, Ls/d;->t:[I

    aget v6, v6, p3

    if-nez v6, :cond_32

    if-nez v5, :cond_32

    .line 85
    iget-object v6, v1, Ls/c;->f:Ls/c;

    iget-object v7, v6, Ls/c;->d:Ls/d;

    if-ne v7, v0, :cond_32

    .line 86
    iget-object v7, v1, Ls/c;->i:Lq/g;

    iget-object v6, v6, Ls/c;->i:Lq/g;

    invoke-virtual {v1}, Ls/c;->d()I

    move-result v8

    neg-int v8, v8

    const/4 v11, 0x5

    invoke-virtual {v10, v7, v6, v8, v11}, Lq/c;->e(Lq/g;Lq/g;II)V

    goto :goto_1e

    :cond_32
    const/4 v11, 0x5

    if-eqz v5, :cond_33

    .line 87
    iget-object v6, v1, Ls/c;->f:Ls/c;

    iget-object v7, v6, Ls/c;->d:Ls/d;

    if-ne v7, v0, :cond_33

    .line 88
    iget-object v7, v1, Ls/c;->i:Lq/g;

    iget-object v6, v6, Ls/c;->i:Lq/g;

    invoke-virtual {v1}, Ls/c;->d()I

    move-result v8

    neg-int v8, v8

    const/4 v14, 0x4

    invoke-virtual {v10, v7, v6, v8, v14}, Lq/c;->e(Lq/g;Lq/g;II)V

    .line 89
    :cond_33
    :goto_1e
    iget-object v6, v1, Ls/c;->i:Lq/g;

    iget-object v7, v12, Ls/d;->P:[Ls/c;

    aget-object v2, v7, v2

    iget-object v2, v2, Ls/c;->f:Ls/c;

    iget-object v2, v2, Ls/c;->i:Lq/g;

    .line 90
    invoke-virtual {v1}, Ls/c;->d()I

    move-result v1

    neg-int v1, v1

    const/4 v7, 0x6

    .line 91
    invoke-virtual {v10, v6, v2, v1, v7}, Lq/c;->g(Lq/g;Lq/g;II)V

    goto :goto_1f

    :cond_34
    const/4 v11, 0x5

    :goto_1f
    if-eqz v4, :cond_35

    add-int/lit8 v1, v16, 0x1

    .line 92
    aget-object v2, v3, v1

    iget-object v2, v2, Ls/c;->i:Lq/g;

    iget-object v3, v12, Ls/d;->P:[Ls/c;

    aget-object v1, v3, v1

    iget-object v3, v1, Ls/c;->i:Lq/g;

    .line 93
    invoke-virtual {v1}, Ls/c;->d()I

    move-result v1

    const/16 v4, 0x8

    .line 94
    invoke-virtual {v10, v2, v3, v1, v4}, Lq/c;->f(Lq/g;Lq/g;II)V

    :cond_35
    move-object/from16 v1, v33

    .line 95
    iget-object v2, v1, Ls/b;->h:Ljava/util/ArrayList;

    if-eqz v2, :cond_3f

    .line 96
    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v3

    const/4 v4, 0x1

    if-le v3, v4, :cond_3f

    .line 97
    iget-boolean v6, v1, Ls/b;->n:Z

    if-eqz v6, :cond_36

    iget-boolean v6, v1, Ls/b;->p:Z

    if-nez v6, :cond_36

    .line 98
    iget v6, v1, Ls/b;->j:I

    int-to-float v6, v6

    goto :goto_20

    :cond_36
    move/from16 v6, v26

    :goto_20
    move-object/from16 v14, v17

    const/4 v7, 0x0

    const/4 v8, 0x0

    :goto_21
    if-ge v8, v3, :cond_3f

    .line 99
    invoke-virtual {v2, v8}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v15

    check-cast v15, Ls/d;

    .line 100
    iget-object v4, v15, Ls/d;->j0:[F

    aget v4, v4, p3

    const/16 v21, 0x0

    cmpg-float v24, v4, v21

    .line 101
    iget-object v11, v15, Ls/d;->P:[Ls/c;

    if-gez v24, :cond_38

    .line 102
    iget-boolean v4, v1, Ls/b;->p:Z

    if-eqz v4, :cond_37

    add-int/lit8 v0, v16, 0x1

    .line 103
    aget-object v0, v11, v0

    iget-object v0, v0, Ls/c;->i:Lq/g;

    aget-object v4, v11, v16

    iget-object v4, v4, Ls/c;->i:Lq/g;

    const/4 v11, 0x0

    const/4 v15, 0x4

    invoke-virtual {v10, v0, v4, v11, v15}, Lq/c;->e(Lq/g;Lq/g;II)V

    move/from16 v24, v15

    goto :goto_24

    :cond_37
    const/16 v24, 0x4

    const/high16 v4, 0x3f800000    # 1.0f

    :goto_22
    const/16 v21, 0x0

    goto :goto_23

    :cond_38
    const/16 v24, 0x4

    goto :goto_22

    :goto_23
    cmpl-float v26, v4, v21

    if-nez v26, :cond_39

    add-int/lit8 v0, v16, 0x1

    .line 104
    aget-object v0, v11, v0

    iget-object v0, v0, Ls/c;->i:Lq/g;

    aget-object v4, v11, v16

    iget-object v4, v4, Ls/c;->i:Lq/g;

    const/4 v11, 0x0

    const/16 v15, 0x8

    invoke-virtual {v10, v0, v4, v11, v15}, Lq/c;->e(Lq/g;Lq/g;II)V

    :goto_24
    move-object/from16 v29, v2

    move/from16 v27, v3

    move/from16 v19, v11

    const/16 v21, 0x0

    goto/16 :goto_29

    :cond_39
    const/16 v19, 0x0

    if-eqz v14, :cond_3e

    .line 105
    iget-object v14, v14, Ls/d;->P:[Ls/c;

    aget-object v0, v14, v16

    iget-object v0, v0, Ls/c;->i:Lq/g;

    add-int/lit8 v27, v16, 0x1

    .line 106
    aget-object v14, v14, v27

    iget-object v14, v14, Ls/c;->i:Lq/g;

    move-object/from16 v29, v2

    .line 107
    aget-object v2, v11, v16

    iget-object v2, v2, Ls/c;->i:Lq/g;

    .line 108
    aget-object v11, v11, v27

    iget-object v11, v11, Ls/c;->i:Lq/g;

    move/from16 v27, v3

    .line 109
    invoke-virtual/range {p1 .. p1}, Lq/c;->l()Lq/b;

    move-result-object v3

    move-object/from16 v33, v15

    const/4 v15, 0x0

    .line 110
    iput v15, v3, Lq/b;->b:F

    cmpl-float v21, v6, v15

    const/high16 v15, -0x40800000    # -1.0f

    if-eqz v21, :cond_3a

    cmpl-float v21, v7, v4

    if-nez v21, :cond_3b

    :cond_3a
    move/from16 v26, v4

    move v4, v15

    const/high16 v15, 0x3f800000    # 1.0f

    const/16 v21, 0x0

    goto :goto_26

    :cond_3b
    const/16 v21, 0x0

    cmpl-float v34, v7, v21

    if-nez v34, :cond_3c

    .line 111
    iget-object v2, v3, Lq/b;->d:Lq/a;

    const/high16 v7, 0x3f800000    # 1.0f

    invoke-virtual {v2, v0, v7}, Lq/a;->g(Lq/g;F)V

    .line 112
    iget-object v0, v3, Lq/b;->d:Lq/a;

    invoke-virtual {v0, v14, v15}, Lq/a;->g(Lq/g;F)V

    :goto_25
    move/from16 v26, v4

    goto :goto_27

    :cond_3c
    const/high16 v15, 0x3f800000    # 1.0f

    if-nez v26, :cond_3d

    .line 113
    iget-object v0, v3, Lq/b;->d:Lq/a;

    invoke-virtual {v0, v2, v15}, Lq/a;->g(Lq/g;F)V

    .line 114
    iget-object v0, v3, Lq/b;->d:Lq/a;

    const/high16 v2, -0x40800000    # -1.0f

    invoke-virtual {v0, v11, v2}, Lq/a;->g(Lq/g;F)V

    goto :goto_25

    :cond_3d
    div-float/2addr v7, v6

    div-float v26, v4, v6

    div-float v7, v7, v26

    move/from16 v26, v4

    .line 115
    iget-object v4, v3, Lq/b;->d:Lq/a;

    invoke-virtual {v4, v0, v15}, Lq/a;->g(Lq/g;F)V

    .line 116
    iget-object v0, v3, Lq/b;->d:Lq/a;

    const/high16 v4, -0x40800000    # -1.0f

    invoke-virtual {v0, v14, v4}, Lq/a;->g(Lq/g;F)V

    .line 117
    iget-object v0, v3, Lq/b;->d:Lq/a;

    invoke-virtual {v0, v11, v7}, Lq/a;->g(Lq/g;F)V

    .line 118
    iget-object v0, v3, Lq/b;->d:Lq/a;

    neg-float v4, v7

    invoke-virtual {v0, v2, v4}, Lq/a;->g(Lq/g;F)V

    goto :goto_27

    .line 119
    :goto_26
    iget-object v7, v3, Lq/b;->d:Lq/a;

    invoke-virtual {v7, v0, v15}, Lq/a;->g(Lq/g;F)V

    .line 120
    iget-object v0, v3, Lq/b;->d:Lq/a;

    invoke-virtual {v0, v14, v4}, Lq/a;->g(Lq/g;F)V

    .line 121
    iget-object v0, v3, Lq/b;->d:Lq/a;

    invoke-virtual {v0, v11, v15}, Lq/a;->g(Lq/g;F)V

    .line 122
    iget-object v0, v3, Lq/b;->d:Lq/a;

    invoke-virtual {v0, v2, v4}, Lq/a;->g(Lq/g;F)V

    .line 123
    :goto_27
    invoke-virtual {v10, v3}, Lq/c;->c(Lq/b;)V

    goto :goto_28

    :cond_3e
    move-object/from16 v29, v2

    move/from16 v27, v3

    move/from16 v26, v4

    move-object/from16 v33, v15

    const/16 v21, 0x0

    :goto_28
    move/from16 v7, v26

    move-object/from16 v14, v33

    :goto_29
    add-int/lit8 v8, v8, 0x1

    move/from16 v3, v27

    move-object/from16 v2, v29

    const/4 v4, 0x1

    const/4 v11, 0x5

    move-object/from16 v0, p0

    goto/16 :goto_21

    :cond_3f
    const/16 v19, 0x0

    const/16 v24, 0x4

    if-eqz v13, :cond_41

    if-eq v13, v9, :cond_40

    if-eqz v5, :cond_41

    :cond_40
    move-object/from16 v0, v32

    goto :goto_2a

    :cond_41
    move-object v14, v9

    move/from16 v15, v25

    move-object/from16 v0, v32

    const/4 v11, 0x2

    goto/16 :goto_30

    .line 124
    :goto_2a
    iget-object v0, v0, Ls/d;->P:[Ls/c;

    aget-object v0, v0, v16

    .line 125
    iget-object v1, v12, Ls/d;->P:[Ls/c;

    add-int/lit8 v2, v16, 0x1

    aget-object v1, v1, v2

    .line 126
    iget-object v0, v0, Ls/c;->f:Ls/c;

    if-eqz v0, :cond_42

    iget-object v0, v0, Ls/c;->i:Lq/g;

    move-object v3, v0

    goto :goto_2b

    :cond_42
    move-object/from16 v3, v17

    .line 127
    :goto_2b
    iget-object v0, v1, Ls/c;->f:Ls/c;

    if-eqz v0, :cond_43

    iget-object v0, v0, Ls/c;->i:Lq/g;

    move-object v6, v0

    goto :goto_2c

    :cond_43
    move-object/from16 v6, v17

    .line 128
    :goto_2c
    iget-object v0, v13, Ls/d;->P:[Ls/c;

    aget-object v0, v0, v16

    if-eqz v9, :cond_44

    .line 129
    iget-object v1, v9, Ls/d;->P:[Ls/c;

    aget-object v1, v1, v2

    :cond_44
    if-eqz v3, :cond_46

    if-eqz v6, :cond_46

    if-nez p3, :cond_45

    move-object/from16 v2, v28

    .line 130
    iget v2, v2, Ls/d;->c0:F

    :goto_2d
    move v5, v2

    goto :goto_2e

    :cond_45
    move-object/from16 v2, v28

    .line 131
    iget v2, v2, Ls/d;->d0:F

    goto :goto_2d

    .line 132
    :goto_2e
    invoke-virtual {v0}, Ls/c;->d()I

    move-result v4

    .line 133
    invoke-virtual {v1}, Ls/c;->d()I

    move-result v8

    .line 134
    iget-object v2, v0, Ls/c;->i:Lq/g;

    iget-object v7, v1, Ls/c;->i:Lq/g;

    const/4 v0, 0x7

    move-object/from16 v1, p1

    const/4 v11, 0x2

    move-object v14, v9

    move/from16 v15, v25

    move v9, v0

    invoke-virtual/range {v1 .. v9}, Lq/c;->b(Lq/g;Lq/g;IFLq/g;Lq/g;II)V

    goto :goto_2f

    :cond_46
    move-object v14, v9

    move/from16 v15, v25

    const/4 v11, 0x2

    :cond_47
    :goto_2f
    move/from16 v23, v15

    goto/16 :goto_47

    :goto_30
    if-eqz v20, :cond_58

    if-eqz v13, :cond_58

    .line 135
    iget v2, v1, Ls/b;->j:I

    if-lez v2, :cond_48

    iget v1, v1, Ls/b;->i:I

    if-ne v1, v2, :cond_48

    const/16 v22, 0x1

    goto :goto_31

    :cond_48
    move/from16 v22, v19

    :goto_31
    move-object v8, v13

    move-object v9, v8

    :goto_32
    if-eqz v9, :cond_47

    .line 136
    iget-object v1, v9, Ls/d;->l0:[Ls/d;

    aget-object v1, v1, p3

    move-object v7, v1

    :goto_33
    if-eqz v7, :cond_49

    .line 137
    iget v1, v7, Ls/d;->f0:I

    const/16 v6, 0x8

    if-ne v1, v6, :cond_4a

    .line 138
    iget-object v1, v7, Ls/d;->l0:[Ls/d;

    aget-object v7, v1, p3

    goto :goto_33

    :cond_49
    const/16 v6, 0x8

    :cond_4a
    if-nez v7, :cond_4c

    if-ne v9, v14, :cond_4b

    goto :goto_34

    :cond_4b
    move-object/from16 v21, v7

    move-object/from16 v23, v8

    move-object v11, v9

    goto/16 :goto_39

    .line 139
    :cond_4c
    :goto_34
    iget-object v1, v9, Ls/d;->P:[Ls/c;

    aget-object v2, v1, v16

    .line 140
    iget-object v3, v2, Ls/c;->i:Lq/g;

    .line 141
    iget-object v4, v2, Ls/c;->f:Ls/c;

    if-eqz v4, :cond_4d

    iget-object v4, v4, Ls/c;->i:Lq/g;

    goto :goto_35

    :cond_4d
    move-object/from16 v4, v17

    :goto_35
    if-eq v8, v9, :cond_4e

    .line 142
    iget-object v4, v8, Ls/d;->P:[Ls/c;

    add-int/lit8 v5, v16, 0x1

    aget-object v4, v4, v5

    iget-object v4, v4, Ls/c;->i:Lq/g;

    goto :goto_36

    :cond_4e
    if-ne v9, v13, :cond_50

    .line 143
    iget-object v4, v0, Ls/d;->P:[Ls/c;

    aget-object v4, v4, v16

    iget-object v4, v4, Ls/c;->f:Ls/c;

    if-eqz v4, :cond_4f

    iget-object v4, v4, Ls/c;->i:Lq/g;

    goto :goto_36

    :cond_4f
    move-object/from16 v4, v17

    .line 144
    :cond_50
    :goto_36
    invoke-virtual {v2}, Ls/c;->d()I

    move-result v2

    add-int/lit8 v5, v16, 0x1

    .line 145
    aget-object v21, v1, v5

    invoke-virtual/range {v21 .. v21}, Ls/c;->d()I

    move-result v21

    if-eqz v7, :cond_51

    .line 146
    iget-object v6, v7, Ls/d;->P:[Ls/c;

    aget-object v6, v6, v16

    .line 147
    iget-object v11, v6, Ls/c;->i:Lq/g;

    goto :goto_37

    .line 148
    :cond_51
    iget-object v6, v12, Ls/d;->P:[Ls/c;

    aget-object v6, v6, v5

    iget-object v6, v6, Ls/c;->f:Ls/c;

    if-eqz v6, :cond_52

    .line 149
    iget-object v11, v6, Ls/c;->i:Lq/g;

    goto :goto_37

    :cond_52
    move-object/from16 v11, v17

    .line 150
    :goto_37
    aget-object v1, v1, v5

    iget-object v1, v1, Ls/c;->i:Lq/g;

    if-eqz v6, :cond_53

    .line 151
    invoke-virtual {v6}, Ls/c;->d()I

    move-result v6

    add-int v21, v6, v21

    .line 152
    :cond_53
    iget-object v6, v8, Ls/d;->P:[Ls/c;

    aget-object v6, v6, v5

    invoke-virtual {v6}, Ls/c;->d()I

    move-result v6

    add-int/2addr v6, v2

    if-eqz v3, :cond_4b

    if-eqz v4, :cond_4b

    if-eqz v11, :cond_4b

    if-eqz v1, :cond_4b

    if-ne v9, v13, :cond_54

    .line 153
    iget-object v2, v13, Ls/d;->P:[Ls/c;

    aget-object v2, v2, v16

    invoke-virtual {v2}, Ls/c;->d()I

    move-result v2

    move v6, v2

    :cond_54
    if-ne v9, v14, :cond_55

    .line 154
    iget-object v2, v14, Ls/d;->P:[Ls/c;

    aget-object v2, v2, v5

    invoke-virtual {v2}, Ls/c;->d()I

    move-result v2

    move/from16 v21, v2

    :cond_55
    if-eqz v22, :cond_56

    const/16 v24, 0x8

    goto :goto_38

    :cond_56
    const/16 v24, 0x5

    :goto_38
    const/high16 v5, 0x3f000000    # 0.5f

    move-object/from16 v25, v1

    move-object/from16 v1, p1

    move-object v2, v3

    move-object v3, v4

    move v4, v6

    const/16 v23, 0x8

    move-object v6, v11

    move-object v11, v7

    move-object/from16 v7, v25

    move-object/from16 v23, v8

    move/from16 v8, v21

    move-object/from16 v21, v11

    move-object v11, v9

    move/from16 v9, v24

    .line 155
    invoke-virtual/range {v1 .. v9}, Lq/c;->b(Lq/g;Lq/g;IFLq/g;Lq/g;II)V

    .line 156
    :goto_39
    iget v1, v11, Ls/d;->f0:I

    const/16 v9, 0x8

    if-eq v1, v9, :cond_57

    move-object v8, v11

    goto :goto_3a

    :cond_57
    move-object/from16 v8, v23

    :goto_3a
    move-object/from16 v9, v21

    const/4 v11, 0x2

    goto/16 :goto_32

    :cond_58
    const/16 v9, 0x8

    if-eqz v18, :cond_47

    if-eqz v13, :cond_47

    .line 157
    iget v2, v1, Ls/b;->j:I

    if-lez v2, :cond_59

    iget v1, v1, Ls/b;->i:I

    if-ne v1, v2, :cond_59

    const/16 v22, 0x1

    goto :goto_3b

    :cond_59
    move/from16 v22, v19

    :goto_3b
    move-object v8, v13

    move-object v11, v8

    :goto_3c
    if-eqz v11, :cond_64

    .line 158
    iget-object v1, v11, Ls/d;->l0:[Ls/d;

    aget-object v1, v1, p3

    :goto_3d
    if-eqz v1, :cond_5a

    .line 159
    iget v2, v1, Ls/d;->f0:I

    if-ne v2, v9, :cond_5a

    .line 160
    iget-object v1, v1, Ls/d;->l0:[Ls/d;

    aget-object v1, v1, p3

    goto :goto_3d

    :cond_5a
    if-eq v11, v13, :cond_62

    if-eq v11, v14, :cond_62

    if-eqz v1, :cond_62

    if-ne v1, v14, :cond_5b

    move-object/from16 v7, v17

    goto :goto_3e

    :cond_5b
    move-object v7, v1

    .line 161
    :goto_3e
    iget-object v1, v11, Ls/d;->P:[Ls/c;

    aget-object v2, v1, v16

    .line 162
    iget-object v3, v2, Ls/c;->i:Lq/g;

    .line 163
    iget-object v4, v8, Ls/d;->P:[Ls/c;

    add-int/lit8 v5, v16, 0x1

    aget-object v4, v4, v5

    iget-object v4, v4, Ls/c;->i:Lq/g;

    .line 164
    invoke-virtual {v2}, Ls/c;->d()I

    move-result v2

    .line 165
    aget-object v6, v1, v5

    invoke-virtual {v6}, Ls/c;->d()I

    move-result v6

    if-eqz v7, :cond_5d

    .line 166
    iget-object v1, v7, Ls/d;->P:[Ls/c;

    aget-object v1, v1, v16

    .line 167
    iget-object v9, v1, Ls/c;->i:Lq/g;

    move-object/from16 v21, v7

    .line 168
    iget-object v7, v1, Ls/c;->f:Ls/c;

    if-eqz v7, :cond_5c

    iget-object v7, v7, Ls/c;->i:Lq/g;

    goto :goto_40

    :cond_5c
    move-object/from16 v7, v17

    goto :goto_40

    :cond_5d
    move-object/from16 v21, v7

    .line 169
    iget-object v7, v14, Ls/d;->P:[Ls/c;

    aget-object v7, v7, v16

    if-eqz v7, :cond_5e

    .line 170
    iget-object v9, v7, Ls/c;->i:Lq/g;

    goto :goto_3f

    :cond_5e
    move-object/from16 v9, v17

    .line 171
    :goto_3f
    aget-object v1, v1, v5

    iget-object v1, v1, Ls/c;->i:Lq/g;

    move-object/from16 v35, v7

    move-object v7, v1

    move-object/from16 v1, v35

    :goto_40
    if-eqz v1, :cond_5f

    .line 172
    invoke-virtual {v1}, Ls/c;->d()I

    move-result v1

    add-int/2addr v1, v6

    move/from16 v23, v1

    goto :goto_41

    :cond_5f
    move/from16 v23, v6

    .line 173
    :goto_41
    iget-object v1, v8, Ls/d;->P:[Ls/c;

    aget-object v1, v1, v5

    invoke-virtual {v1}, Ls/c;->d()I

    move-result v1

    add-int v5, v1, v2

    if-eqz v22, :cond_60

    const/16 v25, 0x8

    goto :goto_42

    :cond_60
    move/from16 v25, v24

    :goto_42
    if-eqz v3, :cond_61

    if-eqz v4, :cond_61

    if-eqz v9, :cond_61

    if-eqz v7, :cond_61

    const/high16 v6, 0x3f000000    # 0.5f

    move-object/from16 v1, p1

    move-object v2, v3

    move-object v3, v4

    move v4, v5

    move v5, v6

    move-object v6, v9

    move-object/from16 v26, v8

    move/from16 v8, v23

    move/from16 v23, v15

    const/16 v15, 0x8

    move/from16 v9, v25

    .line 174
    invoke-virtual/range {v1 .. v9}, Lq/c;->b(Lq/g;Lq/g;IFLq/g;Lq/g;II)V

    goto :goto_43

    :cond_61
    move-object/from16 v26, v8

    move/from16 v23, v15

    const/16 v15, 0x8

    :goto_43
    move-object/from16 v1, v21

    goto :goto_44

    :cond_62
    move-object/from16 v26, v8

    move/from16 v23, v15

    move v15, v9

    .line 175
    :goto_44
    iget v2, v11, Ls/d;->f0:I

    if-eq v2, v15, :cond_63

    move-object v8, v11

    goto :goto_45

    :cond_63
    move-object/from16 v8, v26

    :goto_45
    move-object v11, v1

    move v9, v15

    move/from16 v15, v23

    goto/16 :goto_3c

    :cond_64
    move/from16 v23, v15

    .line 176
    iget-object v1, v13, Ls/d;->P:[Ls/c;

    aget-object v1, v1, v16

    .line 177
    iget-object v0, v0, Ls/d;->P:[Ls/c;

    aget-object v0, v0, v16

    iget-object v0, v0, Ls/c;->f:Ls/c;

    .line 178
    iget-object v2, v14, Ls/d;->P:[Ls/c;

    add-int/lit8 v3, v16, 0x1

    aget-object v11, v2, v3

    .line 179
    iget-object v2, v12, Ls/d;->P:[Ls/c;

    aget-object v2, v2, v3

    iget-object v15, v2, Ls/c;->f:Ls/c;

    const/4 v9, 0x5

    if-eqz v0, :cond_65

    if-eq v13, v14, :cond_66

    .line 180
    iget-object v2, v1, Ls/c;->i:Lq/g;

    iget-object v0, v0, Ls/c;->i:Lq/g;

    invoke-virtual {v1}, Ls/c;->d()I

    move-result v1

    invoke-virtual {v10, v2, v0, v1, v9}, Lq/c;->e(Lq/g;Lq/g;II)V

    :cond_65
    move v0, v9

    goto :goto_46

    :cond_66
    if-eqz v15, :cond_65

    .line 181
    iget-object v2, v1, Ls/c;->i:Lq/g;

    iget-object v3, v0, Ls/c;->i:Lq/g;

    invoke-virtual {v1}, Ls/c;->d()I

    move-result v4

    iget-object v6, v11, Ls/c;->i:Lq/g;

    iget-object v7, v15, Ls/c;->i:Lq/g;

    .line 182
    invoke-virtual {v11}, Ls/c;->d()I

    move-result v8

    const/high16 v5, 0x3f000000    # 0.5f

    move-object/from16 v1, p1

    move v0, v9

    .line 183
    invoke-virtual/range {v1 .. v9}, Lq/c;->b(Lq/g;Lq/g;IFLq/g;Lq/g;II)V

    :goto_46
    if-eqz v15, :cond_67

    if-eq v13, v14, :cond_67

    .line 184
    iget-object v1, v11, Ls/c;->i:Lq/g;

    iget-object v2, v15, Ls/c;->i:Lq/g;

    invoke-virtual {v11}, Ls/c;->d()I

    move-result v3

    neg-int v3, v3

    invoke-virtual {v10, v1, v2, v3, v0}, Lq/c;->e(Lq/g;Lq/g;II)V

    :cond_67
    :goto_47
    if-nez v20, :cond_68

    if-eqz v18, :cond_6f

    :cond_68
    if-eqz v13, :cond_6f

    if-eq v13, v14, :cond_6f

    .line 185
    iget-object v0, v13, Ls/d;->P:[Ls/c;

    aget-object v1, v0, v16

    if-nez v14, :cond_69

    move-object v9, v13

    goto :goto_48

    :cond_69
    move-object v9, v14

    :goto_48
    add-int/lit8 v2, v16, 0x1

    .line 186
    iget-object v3, v9, Ls/d;->P:[Ls/c;

    aget-object v4, v3, v2

    .line 187
    iget-object v5, v1, Ls/c;->f:Ls/c;

    if-eqz v5, :cond_6a

    iget-object v5, v5, Ls/c;->i:Lq/g;

    goto :goto_49

    :cond_6a
    move-object/from16 v5, v17

    .line 188
    :goto_49
    iget-object v6, v4, Ls/c;->f:Ls/c;

    if-eqz v6, :cond_6b

    iget-object v6, v6, Ls/c;->i:Lq/g;

    goto :goto_4a

    :cond_6b
    move-object/from16 v6, v17

    :goto_4a
    if-eq v12, v9, :cond_6d

    .line 189
    iget-object v6, v12, Ls/d;->P:[Ls/c;

    aget-object v6, v6, v2

    .line 190
    iget-object v6, v6, Ls/c;->f:Ls/c;

    if-eqz v6, :cond_6c

    iget-object v6, v6, Ls/c;->i:Lq/g;

    move-object/from16 v17, v6

    :cond_6c
    move-object/from16 v6, v17

    :cond_6d
    if-ne v13, v9, :cond_6e

    .line 191
    aget-object v4, v0, v2

    :cond_6e
    if-eqz v5, :cond_6f

    if-eqz v6, :cond_6f

    .line 192
    invoke-virtual {v1}, Ls/c;->d()I

    move-result v0

    .line 193
    aget-object v2, v3, v2

    invoke-virtual {v2}, Ls/c;->d()I

    move-result v8

    .line 194
    iget-object v2, v1, Ls/c;->i:Lq/g;

    iget-object v7, v4, Ls/c;->i:Lq/g;

    const/4 v9, 0x5

    const/high16 v11, 0x3f000000    # 0.5f

    move-object/from16 v1, p1

    move-object v3, v5

    move v4, v0

    move v5, v11

    invoke-virtual/range {v1 .. v9}, Lq/c;->b(Lq/g;Lq/g;IFLq/g;Lq/g;II)V

    :cond_6f
    :goto_4b
    add-int/lit8 v9, v23, 0x1

    const/4 v12, 0x2

    move-object/from16 v0, p0

    move-object/from16 v11, p2

    move/from16 v14, v30

    move-object/from16 v15, v31

    goto/16 :goto_1

    :cond_70
    return-void
.end method

.method public static b(Ls/e;Lq/c;Ls/d;)V
    .locals 8

    .line 1
    const/4 v0, -0x1

    .line 2
    iput v0, p2, Ls/d;->o:I

    .line 3
    .line 4
    iput v0, p2, Ls/d;->p:I

    .line 5
    .line 6
    iget-object v0, p0, Ls/d;->o0:[I

    .line 7
    .line 8
    const/4 v1, 0x0

    .line 9
    aget v0, v0, v1

    .line 10
    .line 11
    iget-object v2, p2, Ls/d;->o0:[I

    .line 12
    .line 13
    const/4 v3, 0x2

    .line 14
    const/4 v4, 0x4

    .line 15
    if-eq v0, v3, :cond_0

    .line 16
    .line 17
    aget v0, v2, v1

    .line 18
    .line 19
    if-ne v0, v4, :cond_0

    .line 20
    .line 21
    iget-object v0, p2, Ls/d;->H:Ls/c;

    .line 22
    .line 23
    iget v1, v0, Ls/c;->g:I

    .line 24
    .line 25
    invoke-virtual {p0}, Ls/d;->o()I

    .line 26
    .line 27
    .line 28
    move-result v5

    .line 29
    iget-object v6, p2, Ls/d;->J:Ls/c;

    .line 30
    .line 31
    iget v7, v6, Ls/c;->g:I

    .line 32
    .line 33
    sub-int/2addr v5, v7

    .line 34
    invoke-virtual {p1, v0}, Lq/c;->k(Ljava/lang/Object;)Lq/g;

    .line 35
    .line 36
    .line 37
    move-result-object v7

    .line 38
    iput-object v7, v0, Ls/c;->i:Lq/g;

    .line 39
    .line 40
    invoke-virtual {p1, v6}, Lq/c;->k(Ljava/lang/Object;)Lq/g;

    .line 41
    .line 42
    .line 43
    move-result-object v7

    .line 44
    iput-object v7, v6, Ls/c;->i:Lq/g;

    .line 45
    .line 46
    iget-object v0, v0, Ls/c;->i:Lq/g;

    .line 47
    .line 48
    invoke-virtual {p1, v0, v1}, Lq/c;->d(Lq/g;I)V

    .line 49
    .line 50
    .line 51
    iget-object v0, v6, Ls/c;->i:Lq/g;

    .line 52
    .line 53
    invoke-virtual {p1, v0, v5}, Lq/c;->d(Lq/g;I)V

    .line 54
    .line 55
    .line 56
    iput v3, p2, Ls/d;->o:I

    .line 57
    .line 58
    iput v1, p2, Ls/d;->X:I

    .line 59
    .line 60
    sub-int/2addr v5, v1

    .line 61
    iput v5, p2, Ls/d;->T:I

    .line 62
    .line 63
    iget v0, p2, Ls/d;->a0:I

    .line 64
    .line 65
    if-ge v5, v0, :cond_0

    .line 66
    .line 67
    iput v0, p2, Ls/d;->T:I

    .line 68
    .line 69
    :cond_0
    iget-object v0, p0, Ls/d;->o0:[I

    .line 70
    .line 71
    const/4 v1, 0x1

    .line 72
    aget v0, v0, v1

    .line 73
    .line 74
    if-eq v0, v3, :cond_3

    .line 75
    .line 76
    aget v0, v2, v1

    .line 77
    .line 78
    if-ne v0, v4, :cond_3

    .line 79
    .line 80
    iget-object v0, p2, Ls/d;->I:Ls/c;

    .line 81
    .line 82
    iget v1, v0, Ls/c;->g:I

    .line 83
    .line 84
    invoke-virtual {p0}, Ls/d;->i()I

    .line 85
    .line 86
    .line 87
    move-result p0

    .line 88
    iget-object v2, p2, Ls/d;->K:Ls/c;

    .line 89
    .line 90
    iget v4, v2, Ls/c;->g:I

    .line 91
    .line 92
    sub-int/2addr p0, v4

    .line 93
    invoke-virtual {p1, v0}, Lq/c;->k(Ljava/lang/Object;)Lq/g;

    .line 94
    .line 95
    .line 96
    move-result-object v4

    .line 97
    iput-object v4, v0, Ls/c;->i:Lq/g;

    .line 98
    .line 99
    invoke-virtual {p1, v2}, Lq/c;->k(Ljava/lang/Object;)Lq/g;

    .line 100
    .line 101
    .line 102
    move-result-object v4

    .line 103
    iput-object v4, v2, Ls/c;->i:Lq/g;

    .line 104
    .line 105
    iget-object v0, v0, Ls/c;->i:Lq/g;

    .line 106
    .line 107
    invoke-virtual {p1, v0, v1}, Lq/c;->d(Lq/g;I)V

    .line 108
    .line 109
    .line 110
    iget-object v0, v2, Ls/c;->i:Lq/g;

    .line 111
    .line 112
    invoke-virtual {p1, v0, p0}, Lq/c;->d(Lq/g;I)V

    .line 113
    .line 114
    .line 115
    iget v0, p2, Ls/d;->Z:I

    .line 116
    .line 117
    if-gtz v0, :cond_1

    .line 118
    .line 119
    iget v0, p2, Ls/d;->f0:I

    .line 120
    .line 121
    const/16 v2, 0x8

    .line 122
    .line 123
    if-ne v0, v2, :cond_2

    .line 124
    .line 125
    :cond_1
    iget-object v0, p2, Ls/d;->L:Ls/c;

    .line 126
    .line 127
    invoke-virtual {p1, v0}, Lq/c;->k(Ljava/lang/Object;)Lq/g;

    .line 128
    .line 129
    .line 130
    move-result-object v2

    .line 131
    iput-object v2, v0, Ls/c;->i:Lq/g;

    .line 132
    .line 133
    iget v0, p2, Ls/d;->Z:I

    .line 134
    .line 135
    add-int/2addr v0, v1

    .line 136
    invoke-virtual {p1, v2, v0}, Lq/c;->d(Lq/g;I)V

    .line 137
    .line 138
    .line 139
    :cond_2
    iput v3, p2, Ls/d;->p:I

    .line 140
    .line 141
    iput v1, p2, Ls/d;->Y:I

    .line 142
    .line 143
    sub-int/2addr p0, v1

    .line 144
    iput p0, p2, Ls/d;->U:I

    .line 145
    .line 146
    iget p1, p2, Ls/d;->b0:I

    .line 147
    .line 148
    if-ge p0, p1, :cond_3

    .line 149
    .line 150
    iput p1, p2, Ls/d;->U:I

    .line 151
    .line 152
    :cond_3
    return-void
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
.end method

.method public static final c(II)Z
    .locals 0

    .line 1
    and-int/2addr p0, p1

    .line 2
    if-ne p0, p1, :cond_0

    .line 3
    .line 4
    const/4 p0, 0x1

    .line 5
    goto :goto_0

    .line 6
    :cond_0
    const/4 p0, 0x0

    .line 7
    :goto_0
    return p0
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
