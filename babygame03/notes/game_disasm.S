
/workspace/babygame03/game:     file format elf32-i386


Disassembly of section .init:

08049000 <_init>:
 8049000:	f3 0f 1e fb          	endbr32
 8049004:	53                   	push   ebx
 8049005:	83 ec 08             	sub    esp,0x8
 8049008:	e8 43 01 00 00       	call   8049150 <__x86.get_pc_thunk.bx>
 804900d:	81 c3 f3 2f 00 00    	add    ebx,0x2ff3
 8049013:	8b 83 f8 ff ff ff    	mov    eax,DWORD PTR [ebx-0x8]
 8049019:	85 c0                	test   eax,eax
 804901b:	74 02                	je     804901f <_init+0x1f>
 804901d:	ff d0                	call   eax
 804901f:	83 c4 08             	add    esp,0x8
 8049022:	5b                   	pop    ebx
 8049023:	c3                   	ret

Disassembly of section .plt:

08049030 <__libc_start_main@plt-0x10>:
 8049030:	ff 35 04 c0 04 08    	push   DWORD PTR ds:0x804c004
 8049036:	ff 25 08 c0 04 08    	jmp    DWORD PTR ds:0x804c008
 804903c:	00 00                	add    BYTE PTR [eax],al
	...

08049040 <__libc_start_main@plt>:
 8049040:	ff 25 0c c0 04 08    	jmp    DWORD PTR ds:0x804c00c
 8049046:	68 00 00 00 00       	push   0x0
 804904b:	e9 e0 ff ff ff       	jmp    8049030 <_init+0x30>

08049050 <printf@plt>:
 8049050:	ff 25 10 c0 04 08    	jmp    DWORD PTR ds:0x804c010
 8049056:	68 08 00 00 00       	push   0x8
 804905b:	e9 d0 ff ff ff       	jmp    8049030 <_init+0x30>

08049060 <fflush@plt>:
 8049060:	ff 25 14 c0 04 08    	jmp    DWORD PTR ds:0x804c014
 8049066:	68 10 00 00 00       	push   0x10
 804906b:	e9 c0 ff ff ff       	jmp    8049030 <_init+0x30>

08049070 <getchar@plt>:
 8049070:	ff 25 18 c0 04 08    	jmp    DWORD PTR ds:0x804c018
 8049076:	68 18 00 00 00       	push   0x18
 804907b:	e9 b0 ff ff ff       	jmp    8049030 <_init+0x30>

08049080 <fgets@plt>:
 8049080:	ff 25 1c c0 04 08    	jmp    DWORD PTR ds:0x804c01c
 8049086:	68 20 00 00 00       	push   0x20
 804908b:	e9 a0 ff ff ff       	jmp    8049030 <_init+0x30>

08049090 <signal@plt>:
 8049090:	ff 25 20 c0 04 08    	jmp    DWORD PTR ds:0x804c020
 8049096:	68 28 00 00 00       	push   0x28
 804909b:	e9 90 ff ff ff       	jmp    8049030 <_init+0x30>

080490a0 <sleep@plt>:
 80490a0:	ff 25 24 c0 04 08    	jmp    DWORD PTR ds:0x804c024
 80490a6:	68 30 00 00 00       	push   0x30
 80490ab:	e9 80 ff ff ff       	jmp    8049030 <_init+0x30>

080490b0 <puts@plt>:
 80490b0:	ff 25 28 c0 04 08    	jmp    DWORD PTR ds:0x804c028
 80490b6:	68 38 00 00 00       	push   0x38
 80490bb:	e9 70 ff ff ff       	jmp    8049030 <_init+0x30>

080490c0 <exit@plt>:
 80490c0:	ff 25 2c c0 04 08    	jmp    DWORD PTR ds:0x804c02c
 80490c6:	68 40 00 00 00       	push   0x40
 80490cb:	e9 60 ff ff ff       	jmp    8049030 <_init+0x30>

080490d0 <fopen@plt>:
 80490d0:	ff 25 30 c0 04 08    	jmp    DWORD PTR ds:0x804c030
 80490d6:	68 48 00 00 00       	push   0x48
 80490db:	e9 50 ff ff ff       	jmp    8049030 <_init+0x30>

080490e0 <putchar@plt>:
 80490e0:	ff 25 34 c0 04 08    	jmp    DWORD PTR ds:0x804c034
 80490e6:	68 50 00 00 00       	push   0x50
 80490eb:	e9 40 ff ff ff       	jmp    8049030 <_init+0x30>

080490f0 <rand@plt>:
 80490f0:	ff 25 38 c0 04 08    	jmp    DWORD PTR ds:0x804c038
 80490f6:	68 58 00 00 00       	push   0x58
 80490fb:	e9 30 ff ff ff       	jmp    8049030 <_init+0x30>

Disassembly of section .text:

08049100 <_start>:
 8049100:	f3 0f 1e fb          	endbr32
 8049104:	31 ed                	xor    ebp,ebp
 8049106:	5e                   	pop    esi
 8049107:	89 e1                	mov    ecx,esp
 8049109:	83 e4 f0             	and    esp,0xfffffff0
 804910c:	50                   	push   eax
 804910d:	54                   	push   esp
 804910e:	52                   	push   edx
 804910f:	e8 19 00 00 00       	call   804912d <_start+0x2d>
 8049114:	81 c3 ec 2e 00 00    	add    ebx,0x2eec
 804911a:	6a 00                	push   0x0
 804911c:	6a 00                	push   0x0
 804911e:	51                   	push   ecx
 804911f:	56                   	push   esi
 8049120:	c7 c0 71 98 04 08    	mov    eax,0x8049871
 8049126:	50                   	push   eax
 8049127:	e8 14 ff ff ff       	call   8049040 <__libc_start_main@plt>
 804912c:	f4                   	hlt
 804912d:	8b 1c 24             	mov    ebx,DWORD PTR [esp]
 8049130:	c3                   	ret
 8049131:	66 90                	xchg   ax,ax
 8049133:	66 90                	xchg   ax,ax
 8049135:	66 90                	xchg   ax,ax
 8049137:	66 90                	xchg   ax,ax
 8049139:	66 90                	xchg   ax,ax
 804913b:	66 90                	xchg   ax,ax
 804913d:	66 90                	xchg   ax,ax
 804913f:	90                   	nop

08049140 <_dl_relocate_static_pie>:
 8049140:	f3 0f 1e fb          	endbr32
 8049144:	c3                   	ret
 8049145:	66 90                	xchg   ax,ax
 8049147:	66 90                	xchg   ax,ax
 8049149:	66 90                	xchg   ax,ax
 804914b:	66 90                	xchg   ax,ax
 804914d:	66 90                	xchg   ax,ax
 804914f:	90                   	nop

08049150 <__x86.get_pc_thunk.bx>:
 8049150:	8b 1c 24             	mov    ebx,DWORD PTR [esp]
 8049153:	c3                   	ret
 8049154:	66 90                	xchg   ax,ax
 8049156:	66 90                	xchg   ax,ax
 8049158:	66 90                	xchg   ax,ax
 804915a:	66 90                	xchg   ax,ax
 804915c:	66 90                	xchg   ax,ax
 804915e:	66 90                	xchg   ax,ax

08049160 <deregister_tm_clones>:
 8049160:	b8 48 c0 04 08       	mov    eax,0x804c048
 8049165:	3d 48 c0 04 08       	cmp    eax,0x804c048
 804916a:	74 24                	je     8049190 <deregister_tm_clones+0x30>
 804916c:	b8 00 00 00 00       	mov    eax,0x0
 8049171:	85 c0                	test   eax,eax
 8049173:	74 1b                	je     8049190 <deregister_tm_clones+0x30>
 8049175:	55                   	push   ebp
 8049176:	89 e5                	mov    ebp,esp
 8049178:	83 ec 14             	sub    esp,0x14
 804917b:	68 48 c0 04 08       	push   0x804c048
 8049180:	ff d0                	call   eax
 8049182:	83 c4 10             	add    esp,0x10
 8049185:	c9                   	leave
 8049186:	c3                   	ret
 8049187:	8d b4 26 00 00 00 00 	lea    esi,[esi+eiz*1+0x0]
 804918e:	66 90                	xchg   ax,ax
 8049190:	c3                   	ret
 8049191:	8d b4 26 00 00 00 00 	lea    esi,[esi+eiz*1+0x0]
 8049198:	8d b4 26 00 00 00 00 	lea    esi,[esi+eiz*1+0x0]
 804919f:	90                   	nop

080491a0 <register_tm_clones>:
 80491a0:	b8 48 c0 04 08       	mov    eax,0x804c048
 80491a5:	2d 48 c0 04 08       	sub    eax,0x804c048
 80491aa:	89 c2                	mov    edx,eax
 80491ac:	c1 e8 1f             	shr    eax,0x1f
 80491af:	c1 fa 02             	sar    edx,0x2
 80491b2:	01 d0                	add    eax,edx
 80491b4:	d1 f8                	sar    eax,1
 80491b6:	74 20                	je     80491d8 <register_tm_clones+0x38>
 80491b8:	ba 00 00 00 00       	mov    edx,0x0
 80491bd:	85 d2                	test   edx,edx
 80491bf:	74 17                	je     80491d8 <register_tm_clones+0x38>
 80491c1:	55                   	push   ebp
 80491c2:	89 e5                	mov    ebp,esp
 80491c4:	83 ec 10             	sub    esp,0x10
 80491c7:	50                   	push   eax
 80491c8:	68 48 c0 04 08       	push   0x804c048
 80491cd:	ff d2                	call   edx
 80491cf:	83 c4 10             	add    esp,0x10
 80491d2:	c9                   	leave
 80491d3:	c3                   	ret
 80491d4:	8d 74 26 00          	lea    esi,[esi+eiz*1+0x0]
 80491d8:	c3                   	ret
 80491d9:	8d b4 26 00 00 00 00 	lea    esi,[esi+eiz*1+0x0]

080491e0 <__do_global_dtors_aux>:
 80491e0:	f3 0f 1e fb          	endbr32
 80491e4:	80 3d 45 c0 04 08 00 	cmp    BYTE PTR ds:0x804c045,0x0
 80491eb:	75 1b                	jne    8049208 <__do_global_dtors_aux+0x28>
 80491ed:	55                   	push   ebp
 80491ee:	89 e5                	mov    ebp,esp
 80491f0:	83 ec 08             	sub    esp,0x8
 80491f3:	e8 68 ff ff ff       	call   8049160 <deregister_tm_clones>
 80491f8:	c6 05 45 c0 04 08 01 	mov    BYTE PTR ds:0x804c045,0x1
 80491ff:	c9                   	leave
 8049200:	c3                   	ret
 8049201:	8d b4 26 00 00 00 00 	lea    esi,[esi+eiz*1+0x0]
 8049208:	c3                   	ret
 8049209:	8d b4 26 00 00 00 00 	lea    esi,[esi+eiz*1+0x0]

08049210 <frame_dummy>:
 8049210:	f3 0f 1e fb          	endbr32
 8049214:	eb 8a                	jmp    80491a0 <register_tm_clones>

08049216 <sigint_handler>:
 8049216:	55                   	push   ebp
 8049217:	89 e5                	mov    ebp,esp
 8049219:	53                   	push   ebx
 804921a:	83 ec 04             	sub    esp,0x4
 804921d:	e8 fe 07 00 00       	call   8049a20 <__x86.get_pc_thunk.ax>
 8049222:	05 de 2d 00 00       	add    eax,0x2dde
 8049227:	83 ec 0c             	sub    esp,0xc
 804922a:	6a 00                	push   0x0
 804922c:	89 c3                	mov    ebx,eax
 804922e:	e8 8d fe ff ff       	call   80490c0 <exit@plt>

08049233 <init_map>:
 8049233:	55                   	push   ebp
 8049234:	89 e5                	mov    ebp,esp
 8049236:	53                   	push   ebx
 8049237:	83 ec 14             	sub    esp,0x14
 804923a:	e8 11 ff ff ff       	call   8049150 <__x86.get_pc_thunk.bx>
 804923f:	81 c3 c1 2d 00 00    	add    ebx,0x2dc1
 8049245:	c7 45 f4 00 00 00 00 	mov    DWORD PTR [ebp-0xc],0x0
 804924c:	e9 c2 00 00 00       	jmp    8049313 <init_map+0xe0>
 8049251:	c7 45 f0 00 00 00 00 	mov    DWORD PTR [ebp-0x10],0x0
 8049258:	e9 a8 00 00 00       	jmp    8049305 <init_map+0xd2>
 804925d:	83 7d f4 1d          	cmp    DWORD PTR [ebp-0xc],0x1d
 8049261:	75 1e                	jne    8049281 <init_map+0x4e>
 8049263:	83 7d f0 59          	cmp    DWORD PTR [ebp-0x10],0x59
 8049267:	75 18                	jne    8049281 <init_map+0x4e>
 8049269:	8b 55 08             	mov    edx,DWORD PTR [ebp+0x8]
 804926c:	8b 45 f4             	mov    eax,DWORD PTR [ebp-0xc]
 804926f:	6b c0 5a             	imul   eax,eax,0x5a
 8049272:	01 c2                	add    edx,eax
 8049274:	8b 45 f0             	mov    eax,DWORD PTR [ebp-0x10]
 8049277:	01 d0                	add    eax,edx
 8049279:	c6 00 58             	mov    BYTE PTR [eax],0x58
 804927c:	e9 80 00 00 00       	jmp    8049301 <init_map+0xce>
 8049281:	8b 45 0c             	mov    eax,DWORD PTR [ebp+0xc]
 8049284:	8b 00                	mov    eax,DWORD PTR [eax]
 8049286:	39 45 f4             	cmp    DWORD PTR [ebp-0xc],eax
 8049289:	75 26                	jne    80492b1 <init_map+0x7e>
 804928b:	8b 45 0c             	mov    eax,DWORD PTR [ebp+0xc]
 804928e:	8b 40 04             	mov    eax,DWORD PTR [eax+0x4]
 8049291:	39 45 f0             	cmp    DWORD PTR [ebp-0x10],eax
 8049294:	75 1b                	jne    80492b1 <init_map+0x7e>
 8049296:	0f b6 83 44 00 00 00 	movzx  eax,BYTE PTR [ebx+0x44]
 804929d:	8b 4d 08             	mov    ecx,DWORD PTR [ebp+0x8]
 80492a0:	8b 55 f4             	mov    edx,DWORD PTR [ebp-0xc]
 80492a3:	6b d2 5a             	imul   edx,edx,0x5a
 80492a6:	01 d1                	add    ecx,edx
 80492a8:	8b 55 f0             	mov    edx,DWORD PTR [ebp-0x10]
 80492ab:	01 ca                	add    edx,ecx
 80492ad:	88 02                	mov    BYTE PTR [edx],al
 80492af:	eb 50                	jmp    8049301 <init_map+0xce>
 80492b1:	e8 3a fe ff ff       	call   80490f0 <rand@plt>
 80492b6:	8b 55 10             	mov    edx,DWORD PTR [ebp+0x10]
 80492b9:	8b 0a                	mov    ecx,DWORD PTR [edx]
 80492bb:	99                   	cdq
 80492bc:	f7 f9                	idiv   ecx
 80492be:	89 d0                	mov    eax,edx
 80492c0:	39 45 f4             	cmp    DWORD PTR [ebp-0xc],eax
 80492c3:	75 29                	jne    80492ee <init_map+0xbb>
 80492c5:	e8 26 fe ff ff       	call   80490f0 <rand@plt>
 80492ca:	8b 55 10             	mov    edx,DWORD PTR [ebp+0x10]
 80492cd:	8b 0a                	mov    ecx,DWORD PTR [edx]
 80492cf:	99                   	cdq
 80492d0:	f7 f9                	idiv   ecx
 80492d2:	89 d0                	mov    eax,edx
 80492d4:	39 45 f0             	cmp    DWORD PTR [ebp-0x10],eax
 80492d7:	75 15                	jne    80492ee <init_map+0xbb>
 80492d9:	8b 55 08             	mov    edx,DWORD PTR [ebp+0x8]
 80492dc:	8b 45 f4             	mov    eax,DWORD PTR [ebp-0xc]
 80492df:	6b c0 5a             	imul   eax,eax,0x5a
 80492e2:	01 c2                	add    edx,eax
 80492e4:	8b 45 f0             	mov    eax,DWORD PTR [ebp-0x10]
 80492e7:	01 d0                	add    eax,edx
 80492e9:	c6 00 23             	mov    BYTE PTR [eax],0x23
 80492ec:	eb 13                	jmp    8049301 <init_map+0xce>
 80492ee:	8b 55 08             	mov    edx,DWORD PTR [ebp+0x8]
 80492f1:	8b 45 f4             	mov    eax,DWORD PTR [ebp-0xc]
 80492f4:	6b c0 5a             	imul   eax,eax,0x5a
 80492f7:	01 c2                	add    edx,eax
 80492f9:	8b 45 f0             	mov    eax,DWORD PTR [ebp-0x10]
 80492fc:	01 d0                	add    eax,edx
 80492fe:	c6 00 2e             	mov    BYTE PTR [eax],0x2e
 8049301:	83 45 f0 01          	add    DWORD PTR [ebp-0x10],0x1
 8049305:	83 7d f0 59          	cmp    DWORD PTR [ebp-0x10],0x59
 8049309:	0f 8e 4e ff ff ff    	jle    804925d <init_map+0x2a>
 804930f:	83 45 f4 01          	add    DWORD PTR [ebp-0xc],0x1
 8049313:	83 7d f4 1d          	cmp    DWORD PTR [ebp-0xc],0x1d
 8049317:	0f 8e 34 ff ff ff    	jle    8049251 <init_map+0x1e>
 804931d:	90                   	nop
 804931e:	90                   	nop
 804931f:	8b 5d fc             	mov    ebx,DWORD PTR [ebp-0x4]
 8049322:	c9                   	leave
 8049323:	c3                   	ret

08049324 <find_player_pos>:
 8049324:	55                   	push   ebp
 8049325:	89 e5                	mov    ebp,esp
 8049327:	53                   	push   ebx
 8049328:	83 ec 14             	sub    esp,0x14
 804932b:	e8 20 fe ff ff       	call   8049150 <__x86.get_pc_thunk.bx>
 8049330:	81 c3 d0 2c 00 00    	add    ebx,0x2cd0
 8049336:	c7 45 f4 00 00 00 00 	mov    DWORD PTR [ebp-0xc],0x0
 804933d:	eb 67                	jmp    80493a6 <find_player_pos+0x82>
 804933f:	c7 45 f0 00 00 00 00 	mov    DWORD PTR [ebp-0x10],0x0
 8049346:	eb 54                	jmp    804939c <find_player_pos+0x78>
 8049348:	8b 55 08             	mov    edx,DWORD PTR [ebp+0x8]
 804934b:	8b 45 f4             	mov    eax,DWORD PTR [ebp-0xc]
 804934e:	6b c0 5a             	imul   eax,eax,0x5a
 8049351:	01 c2                	add    edx,eax
 8049353:	8b 45 f0             	mov    eax,DWORD PTR [ebp-0x10]
 8049356:	01 d0                	add    eax,edx
 8049358:	0f b6 10             	movzx  edx,BYTE PTR [eax]
 804935b:	0f b6 83 44 00 00 00 	movzx  eax,BYTE PTR [ebx+0x44]
 8049362:	38 c2                	cmp    dl,al
 8049364:	75 32                	jne    8049398 <find_player_pos+0x74>
 8049366:	83 ec 04             	sub    esp,0x4
 8049369:	ff 75 f0             	push   DWORD PTR [ebp-0x10]
 804936c:	ff 75 f4             	push   DWORD PTR [ebp-0xc]
 804936f:	8d 83 08 e0 ff ff    	lea    eax,[ebx-0x1ff8]
 8049375:	50                   	push   eax
 8049376:	e8 d5 fc ff ff       	call   8049050 <printf@plt>
 804937b:	83 c4 10             	add    esp,0x10
 804937e:	8b 45 0c             	mov    eax,DWORD PTR [ebp+0xc]
 8049381:	8b 00                	mov    eax,DWORD PTR [eax]
 8049383:	83 ec 08             	sub    esp,0x8
 8049386:	50                   	push   eax
 8049387:	8d 83 20 e0 ff ff    	lea    eax,[ebx-0x1fe0]
 804938d:	50                   	push   eax
 804938e:	e8 bd fc ff ff       	call   8049050 <printf@plt>
 8049393:	83 c4 10             	add    esp,0x10
 8049396:	eb 14                	jmp    80493ac <find_player_pos+0x88>
 8049398:	83 45 f0 01          	add    DWORD PTR [ebp-0x10],0x1
 804939c:	83 7d f0 59          	cmp    DWORD PTR [ebp-0x10],0x59
 80493a0:	7e a6                	jle    8049348 <find_player_pos+0x24>
 80493a2:	83 45 f4 01          	add    DWORD PTR [ebp-0xc],0x1
 80493a6:	83 7d f4 1d          	cmp    DWORD PTR [ebp-0xc],0x1d
 80493aa:	7e 93                	jle    804933f <find_player_pos+0x1b>
 80493ac:	8b 5d fc             	mov    ebx,DWORD PTR [ebp-0x4]
 80493af:	c9                   	leave
 80493b0:	c3                   	ret

080493b1 <find_end_tile_pos>:
 80493b1:	55                   	push   ebp
 80493b2:	89 e5                	mov    ebp,esp
 80493b4:	53                   	push   ebx
 80493b5:	83 ec 14             	sub    esp,0x14
 80493b8:	e8 63 06 00 00       	call   8049a20 <__x86.get_pc_thunk.ax>
 80493bd:	05 43 2c 00 00       	add    eax,0x2c43
 80493c2:	c7 45 f4 00 00 00 00 	mov    DWORD PTR [ebp-0xc],0x0
 80493c9:	eb 4b                	jmp    8049416 <find_end_tile_pos+0x65>
 80493cb:	c7 45 f0 00 00 00 00 	mov    DWORD PTR [ebp-0x10],0x0
 80493d2:	eb 38                	jmp    804940c <find_end_tile_pos+0x5b>
 80493d4:	8b 4d 08             	mov    ecx,DWORD PTR [ebp+0x8]
 80493d7:	8b 55 f4             	mov    edx,DWORD PTR [ebp-0xc]
 80493da:	6b d2 5a             	imul   edx,edx,0x5a
 80493dd:	01 d1                	add    ecx,edx
 80493df:	8b 55 f0             	mov    edx,DWORD PTR [ebp-0x10]
 80493e2:	01 ca                	add    edx,ecx
 80493e4:	0f b6 12             	movzx  edx,BYTE PTR [edx]
 80493e7:	80 fa 58             	cmp    dl,0x58
 80493ea:	75 1c                	jne    8049408 <find_end_tile_pos+0x57>
 80493ec:	83 ec 04             	sub    esp,0x4
 80493ef:	ff 75 f0             	push   DWORD PTR [ebp-0x10]
 80493f2:	ff 75 f4             	push   DWORD PTR [ebp-0xc]
 80493f5:	8d 90 2b e0 ff ff    	lea    edx,[eax-0x1fd5]
 80493fb:	52                   	push   edx
 80493fc:	89 c3                	mov    ebx,eax
 80493fe:	e8 4d fc ff ff       	call   8049050 <printf@plt>
 8049403:	83 c4 10             	add    esp,0x10
 8049406:	eb 14                	jmp    804941c <find_end_tile_pos+0x6b>
 8049408:	83 45 f0 01          	add    DWORD PTR [ebp-0x10],0x1
 804940c:	83 7d f0 59          	cmp    DWORD PTR [ebp-0x10],0x59
 8049410:	7e c2                	jle    80493d4 <find_end_tile_pos+0x23>
 8049412:	83 45 f4 01          	add    DWORD PTR [ebp-0xc],0x1
 8049416:	83 7d f4 1d          	cmp    DWORD PTR [ebp-0xc],0x1d
 804941a:	7e af                	jle    80493cb <find_end_tile_pos+0x1a>
 804941c:	8b 5d fc             	mov    ebx,DWORD PTR [ebp-0x4]
 804941f:	c9                   	leave
 8049420:	c3                   	ret

08049421 <print_lives_left>:
 8049421:	55                   	push   ebp
 8049422:	89 e5                	mov    ebp,esp
 8049424:	53                   	push   ebx
 8049425:	83 ec 04             	sub    esp,0x4
 8049428:	e8 f3 05 00 00       	call   8049a20 <__x86.get_pc_thunk.ax>
 804942d:	05 d3 2b 00 00       	add    eax,0x2bd3
 8049432:	8b 55 08             	mov    edx,DWORD PTR [ebp+0x8]
 8049435:	8b 52 08             	mov    edx,DWORD PTR [edx+0x8]
 8049438:	83 ec 08             	sub    esp,0x8
 804943b:	52                   	push   edx
 804943c:	8d 90 45 e0 ff ff    	lea    edx,[eax-0x1fbb]
 8049442:	52                   	push   edx
 8049443:	89 c3                	mov    ebx,eax
 8049445:	e8 06 fc ff ff       	call   8049050 <printf@plt>
 804944a:	83 c4 10             	add    esp,0x10
 804944d:	90                   	nop
 804944e:	8b 5d fc             	mov    ebx,DWORD PTR [ebp-0x4]
 8049451:	c9                   	leave
 8049452:	c3                   	ret

08049453 <print_map>:
 8049453:	55                   	push   ebp
 8049454:	89 e5                	mov    ebp,esp
 8049456:	53                   	push   ebx
 8049457:	83 ec 14             	sub    esp,0x14
 804945a:	e8 f1 fc ff ff       	call   8049150 <__x86.get_pc_thunk.bx>
 804945f:	81 c3 a1 2b 00 00    	add    ebx,0x2ba1
 8049465:	e8 36 02 00 00       	call   80496a0 <clear_screen>
 804946a:	83 ec 08             	sub    esp,0x8
 804946d:	ff 75 10             	push   DWORD PTR [ebp+0x10]
 8049470:	ff 75 08             	push   DWORD PTR [ebp+0x8]
 8049473:	e8 ac fe ff ff       	call   8049324 <find_player_pos>
 8049478:	83 c4 10             	add    esp,0x10
 804947b:	83 ec 0c             	sub    esp,0xc
 804947e:	ff 75 08             	push   DWORD PTR [ebp+0x8]
 8049481:	e8 2b ff ff ff       	call   80493b1 <find_end_tile_pos>
 8049486:	83 c4 10             	add    esp,0x10
 8049489:	83 ec 0c             	sub    esp,0xc
 804948c:	ff 75 0c             	push   DWORD PTR [ebp+0xc]
 804948f:	e8 8d ff ff ff       	call   8049421 <print_lives_left>
 8049494:	83 c4 10             	add    esp,0x10
 8049497:	c7 45 f4 00 00 00 00 	mov    DWORD PTR [ebp-0xc],0x0
 804949e:	eb 46                	jmp    80494e6 <print_map+0x93>
 80494a0:	c7 45 f0 00 00 00 00 	mov    DWORD PTR [ebp-0x10],0x0
 80494a7:	eb 26                	jmp    80494cf <print_map+0x7c>
 80494a9:	8b 55 08             	mov    edx,DWORD PTR [ebp+0x8]
 80494ac:	8b 45 f4             	mov    eax,DWORD PTR [ebp-0xc]
 80494af:	6b c0 5a             	imul   eax,eax,0x5a
 80494b2:	01 c2                	add    edx,eax
 80494b4:	8b 45 f0             	mov    eax,DWORD PTR [ebp-0x10]
 80494b7:	01 d0                	add    eax,edx
 80494b9:	0f b6 00             	movzx  eax,BYTE PTR [eax]
 80494bc:	0f be c0             	movsx  eax,al
 80494bf:	83 ec 0c             	sub    esp,0xc
 80494c2:	50                   	push   eax
 80494c3:	e8 18 fc ff ff       	call   80490e0 <putchar@plt>
 80494c8:	83 c4 10             	add    esp,0x10
 80494cb:	83 45 f0 01          	add    DWORD PTR [ebp-0x10],0x1
 80494cf:	83 7d f0 59          	cmp    DWORD PTR [ebp-0x10],0x59
 80494d3:	7e d4                	jle    80494a9 <print_map+0x56>
 80494d5:	83 ec 0c             	sub    esp,0xc
 80494d8:	6a 0a                	push   0xa
 80494da:	e8 01 fc ff ff       	call   80490e0 <putchar@plt>
 80494df:	83 c4 10             	add    esp,0x10
 80494e2:	83 45 f4 01          	add    DWORD PTR [ebp-0xc],0x1
 80494e6:	83 7d f4 1d          	cmp    DWORD PTR [ebp-0xc],0x1d
 80494ea:	7e b4                	jle    80494a0 <print_map+0x4d>
 80494ec:	8b 83 fc ff ff ff    	mov    eax,DWORD PTR [ebx-0x4]
 80494f2:	8b 00                	mov    eax,DWORD PTR [eax]
 80494f4:	83 ec 0c             	sub    esp,0xc
 80494f7:	50                   	push   eax
 80494f8:	e8 63 fb ff ff       	call   8049060 <fflush@plt>
 80494fd:	83 c4 10             	add    esp,0x10
 8049500:	90                   	nop
 8049501:	8b 5d fc             	mov    ebx,DWORD PTR [ebp-0x4]
 8049504:	c9                   	leave
 8049505:	c3                   	ret

08049506 <init_player>:
 8049506:	55                   	push   ebp
 8049507:	89 e5                	mov    ebp,esp
 8049509:	e8 12 05 00 00       	call   8049a20 <__x86.get_pc_thunk.ax>
 804950e:	05 f2 2a 00 00       	add    eax,0x2af2
 8049513:	8b 45 08             	mov    eax,DWORD PTR [ebp+0x8]
 8049516:	c7 00 04 00 00 00    	mov    DWORD PTR [eax],0x4
 804951c:	8b 45 08             	mov    eax,DWORD PTR [ebp+0x8]
 804951f:	c7 40 04 04 00 00 00 	mov    DWORD PTR [eax+0x4],0x4
 8049526:	8b 45 08             	mov    eax,DWORD PTR [ebp+0x8]
 8049529:	c7 40 08 32 00 00 00 	mov    DWORD PTR [eax+0x8],0x32
 8049530:	90                   	nop
 8049531:	5d                   	pop    ebp
 8049532:	c3                   	ret

08049533 <move_player>:
 8049533:	55                   	push   ebp
 8049534:	89 e5                	mov    ebp,esp
 8049536:	56                   	push   esi
 8049537:	53                   	push   ebx
 8049538:	83 ec 10             	sub    esp,0x10
 804953b:	e8 10 fc ff ff       	call   8049150 <__x86.get_pc_thunk.bx>
 8049540:	81 c3 c0 2a 00 00    	add    ebx,0x2ac0
 8049546:	8b 45 0c             	mov    eax,DWORD PTR [ebp+0xc]
 8049549:	88 45 f4             	mov    BYTE PTR [ebp-0xc],al
 804954c:	8b 45 08             	mov    eax,DWORD PTR [ebp+0x8]
 804954f:	8b 40 08             	mov    eax,DWORD PTR [eax+0x8]
 8049552:	85 c0                	test   eax,eax
 8049554:	7f 30                	jg     8049586 <move_player+0x53>
 8049556:	83 ec 0c             	sub    esp,0xc
 8049559:	8d 83 58 e0 ff ff    	lea    eax,[ebx-0x1fa8]
 804955f:	50                   	push   eax
 8049560:	e8 4b fb ff ff       	call   80490b0 <puts@plt>
 8049565:	83 c4 10             	add    esp,0x10
 8049568:	8b 83 fc ff ff ff    	mov    eax,DWORD PTR [ebx-0x4]
 804956e:	8b 00                	mov    eax,DWORD PTR [eax]
 8049570:	83 ec 0c             	sub    esp,0xc
 8049573:	50                   	push   eax
 8049574:	e8 e7 fa ff ff       	call   8049060 <fflush@plt>
 8049579:	83 c4 10             	add    esp,0x10
 804957c:	83 ec 0c             	sub    esp,0xc
 804957f:	6a 00                	push   0x0
 8049581:	e8 3a fb ff ff       	call   80490c0 <exit@plt>
 8049586:	80 7d f4 6c          	cmp    BYTE PTR [ebp-0xc],0x6c
 804958a:	75 0b                	jne    8049597 <move_player+0x64>
 804958c:	e8 df fa ff ff       	call   8049070 <getchar@plt>
 8049591:	88 83 44 00 00 00    	mov    BYTE PTR [ebx+0x44],al
 8049597:	80 7d f4 70          	cmp    BYTE PTR [ebp-0xc],0x70
 804959b:	75 14                	jne    80495b1 <move_player+0x7e>
 804959d:	83 ec 04             	sub    esp,0x4
 80495a0:	ff 75 14             	push   DWORD PTR [ebp+0x14]
 80495a3:	ff 75 08             	push   DWORD PTR [ebp+0x8]
 80495a6:	ff 75 10             	push   DWORD PTR [ebp+0x10]
 80495a9:	e8 30 01 00 00       	call   80496de <solve_round>
 80495ae:	83 c4 10             	add    esp,0x10
 80495b1:	8b 45 08             	mov    eax,DWORD PTR [ebp+0x8]
 80495b4:	8b 10                	mov    edx,DWORD PTR [eax]
 80495b6:	8b 45 08             	mov    eax,DWORD PTR [ebp+0x8]
 80495b9:	8b 48 04             	mov    ecx,DWORD PTR [eax+0x4]
 80495bc:	8b 75 10             	mov    esi,DWORD PTR [ebp+0x10]
 80495bf:	6b c2 5a             	imul   eax,edx,0x5a
 80495c2:	01 f0                	add    eax,esi
 80495c4:	01 c8                	add    eax,ecx
 80495c6:	c6 00 2e             	mov    BYTE PTR [eax],0x2e
 80495c9:	80 7d f4 77          	cmp    BYTE PTR [ebp-0xc],0x77
 80495cd:	75 0f                	jne    80495de <move_player+0xab>
 80495cf:	8b 45 08             	mov    eax,DWORD PTR [ebp+0x8]
 80495d2:	8b 00                	mov    eax,DWORD PTR [eax]
 80495d4:	8d 50 ff             	lea    edx,[eax-0x1]
 80495d7:	8b 45 08             	mov    eax,DWORD PTR [ebp+0x8]
 80495da:	89 10                	mov    DWORD PTR [eax],edx
 80495dc:	eb 41                	jmp    804961f <move_player+0xec>
 80495de:	80 7d f4 73          	cmp    BYTE PTR [ebp-0xc],0x73
 80495e2:	75 0f                	jne    80495f3 <move_player+0xc0>
 80495e4:	8b 45 08             	mov    eax,DWORD PTR [ebp+0x8]
 80495e7:	8b 00                	mov    eax,DWORD PTR [eax]
 80495e9:	8d 50 01             	lea    edx,[eax+0x1]
 80495ec:	8b 45 08             	mov    eax,DWORD PTR [ebp+0x8]
 80495ef:	89 10                	mov    DWORD PTR [eax],edx
 80495f1:	eb 2c                	jmp    804961f <move_player+0xec>
 80495f3:	80 7d f4 61          	cmp    BYTE PTR [ebp-0xc],0x61
 80495f7:	75 11                	jne    804960a <move_player+0xd7>
 80495f9:	8b 45 08             	mov    eax,DWORD PTR [ebp+0x8]
 80495fc:	8b 40 04             	mov    eax,DWORD PTR [eax+0x4]
 80495ff:	8d 50 ff             	lea    edx,[eax-0x1]
 8049602:	8b 45 08             	mov    eax,DWORD PTR [ebp+0x8]
 8049605:	89 50 04             	mov    DWORD PTR [eax+0x4],edx
 8049608:	eb 15                	jmp    804961f <move_player+0xec>
 804960a:	80 7d f4 64          	cmp    BYTE PTR [ebp-0xc],0x64
 804960e:	75 0f                	jne    804961f <move_player+0xec>
 8049610:	8b 45 08             	mov    eax,DWORD PTR [ebp+0x8]
 8049613:	8b 40 04             	mov    eax,DWORD PTR [eax+0x4]
 8049616:	8d 50 01             	lea    edx,[eax+0x1]
 8049619:	8b 45 08             	mov    eax,DWORD PTR [ebp+0x8]
 804961c:	89 50 04             	mov    DWORD PTR [eax+0x4],edx
 804961f:	8b 45 08             	mov    eax,DWORD PTR [ebp+0x8]
 8049622:	8b 10                	mov    edx,DWORD PTR [eax]
 8049624:	8b 45 08             	mov    eax,DWORD PTR [ebp+0x8]
 8049627:	8b 48 04             	mov    ecx,DWORD PTR [eax+0x4]
 804962a:	8b 75 10             	mov    esi,DWORD PTR [ebp+0x10]
 804962d:	6b c2 5a             	imul   eax,edx,0x5a
 8049630:	01 f0                	add    eax,esi
 8049632:	01 c8                	add    eax,ecx
 8049634:	0f b6 00             	movzx  eax,BYTE PTR [eax]
 8049637:	3c 23                	cmp    al,0x23
 8049639:	75 30                	jne    804966b <move_player+0x138>
 804963b:	83 ec 0c             	sub    esp,0xc
 804963e:	8d 83 77 e0 ff ff    	lea    eax,[ebx-0x1f89]
 8049644:	50                   	push   eax
 8049645:	e8 66 fa ff ff       	call   80490b0 <puts@plt>
 804964a:	83 c4 10             	add    esp,0x10
 804964d:	8b 83 fc ff ff ff    	mov    eax,DWORD PTR [ebx-0x4]
 8049653:	8b 00                	mov    eax,DWORD PTR [eax]
 8049655:	83 ec 0c             	sub    esp,0xc
 8049658:	50                   	push   eax
 8049659:	e8 02 fa ff ff       	call   8049060 <fflush@plt>
 804965e:	83 c4 10             	add    esp,0x10
 8049661:	83 ec 0c             	sub    esp,0xc
 8049664:	6a 00                	push   0x0
 8049666:	e8 55 fa ff ff       	call   80490c0 <exit@plt>
 804966b:	8b 45 08             	mov    eax,DWORD PTR [ebp+0x8]
 804966e:	8b 08                	mov    ecx,DWORD PTR [eax]
 8049670:	8b 45 08             	mov    eax,DWORD PTR [ebp+0x8]
 8049673:	8b 70 04             	mov    esi,DWORD PTR [eax+0x4]
 8049676:	0f b6 93 44 00 00 00 	movzx  edx,BYTE PTR [ebx+0x44]
 804967d:	8b 5d 10             	mov    ebx,DWORD PTR [ebp+0x10]
 8049680:	6b c1 5a             	imul   eax,ecx,0x5a
 8049683:	01 d8                	add    eax,ebx
 8049685:	01 f0                	add    eax,esi
 8049687:	88 10                	mov    BYTE PTR [eax],dl
 8049689:	8b 45 08             	mov    eax,DWORD PTR [ebp+0x8]
 804968c:	8b 40 08             	mov    eax,DWORD PTR [eax+0x8]
 804968f:	8d 50 ff             	lea    edx,[eax-0x1]
 8049692:	8b 45 08             	mov    eax,DWORD PTR [ebp+0x8]
 8049695:	89 50 08             	mov    DWORD PTR [eax+0x8],edx
 8049698:	90                   	nop
 8049699:	8d 65 f8             	lea    esp,[ebp-0x8]
 804969c:	5b                   	pop    ebx
 804969d:	5e                   	pop    esi
 804969e:	5d                   	pop    ebp
 804969f:	c3                   	ret

080496a0 <clear_screen>:
 80496a0:	55                   	push   ebp
 80496a1:	89 e5                	mov    ebp,esp
 80496a3:	53                   	push   ebx
 80496a4:	83 ec 04             	sub    esp,0x4
 80496a7:	e8 a4 fa ff ff       	call   8049150 <__x86.get_pc_thunk.bx>
 80496ac:	81 c3 54 29 00 00    	add    ebx,0x2954
 80496b2:	83 ec 0c             	sub    esp,0xc
 80496b5:	8d 83 8c e0 ff ff    	lea    eax,[ebx-0x1f74]
 80496bb:	50                   	push   eax
 80496bc:	e8 8f f9 ff ff       	call   8049050 <printf@plt>
 80496c1:	83 c4 10             	add    esp,0x10
 80496c4:	8b 83 fc ff ff ff    	mov    eax,DWORD PTR [ebx-0x4]
 80496ca:	8b 00                	mov    eax,DWORD PTR [eax]
 80496cc:	83 ec 0c             	sub    esp,0xc
 80496cf:	50                   	push   eax
 80496d0:	e8 8b f9 ff ff       	call   8049060 <fflush@plt>
 80496d5:	83 c4 10             	add    esp,0x10
 80496d8:	90                   	nop
 80496d9:	8b 5d fc             	mov    ebx,DWORD PTR [ebp-0x4]
 80496dc:	c9                   	leave
 80496dd:	c3                   	ret

080496de <solve_round>:
 80496de:	55                   	push   ebp
 80496df:	89 e5                	mov    ebp,esp
 80496e1:	53                   	push   ebx
 80496e2:	83 ec 04             	sub    esp,0x4
 80496e5:	e8 66 fa ff ff       	call   8049150 <__x86.get_pc_thunk.bx>
 80496ea:	81 c3 16 29 00 00    	add    ebx,0x2916
 80496f0:	eb 47                	jmp    8049739 <solve_round+0x5b>
 80496f2:	8b 45 0c             	mov    eax,DWORD PTR [ebp+0xc]
 80496f5:	8b 40 04             	mov    eax,DWORD PTR [eax+0x4]
 80496f8:	83 f8 58             	cmp    eax,0x58
 80496fb:	7f 15                	jg     8049712 <solve_round+0x34>
 80496fd:	ff 75 10             	push   DWORD PTR [ebp+0x10]
 8049700:	ff 75 08             	push   DWORD PTR [ebp+0x8]
 8049703:	6a 64                	push   0x64
 8049705:	ff 75 0c             	push   DWORD PTR [ebp+0xc]
 8049708:	e8 26 fe ff ff       	call   8049533 <move_player>
 804970d:	83 c4 10             	add    esp,0x10
 8049710:	eb 13                	jmp    8049725 <solve_round+0x47>
 8049712:	ff 75 10             	push   DWORD PTR [ebp+0x10]
 8049715:	ff 75 08             	push   DWORD PTR [ebp+0x8]
 8049718:	6a 61                	push   0x61
 804971a:	ff 75 0c             	push   DWORD PTR [ebp+0xc]
 804971d:	e8 11 fe ff ff       	call   8049533 <move_player>
 8049722:	83 c4 10             	add    esp,0x10
 8049725:	83 ec 04             	sub    esp,0x4
 8049728:	ff 75 10             	push   DWORD PTR [ebp+0x10]
 804972b:	ff 75 0c             	push   DWORD PTR [ebp+0xc]
 804972e:	ff 75 08             	push   DWORD PTR [ebp+0x8]
 8049731:	e8 1d fd ff ff       	call   8049453 <print_map>
 8049736:	83 c4 10             	add    esp,0x10
 8049739:	8b 45 0c             	mov    eax,DWORD PTR [ebp+0xc]
 804973c:	8b 40 04             	mov    eax,DWORD PTR [eax+0x4]
 804973f:	83 f8 59             	cmp    eax,0x59
 8049742:	75 ae                	jne    80496f2 <solve_round+0x14>
 8049744:	eb 47                	jmp    804978d <solve_round+0xaf>
 8049746:	8b 45 0c             	mov    eax,DWORD PTR [ebp+0xc]
 8049749:	8b 40 04             	mov    eax,DWORD PTR [eax+0x4]
 804974c:	83 f8 1c             	cmp    eax,0x1c
 804974f:	7f 15                	jg     8049766 <solve_round+0x88>
 8049751:	ff 75 10             	push   DWORD PTR [ebp+0x10]
 8049754:	ff 75 08             	push   DWORD PTR [ebp+0x8]
 8049757:	6a 77                	push   0x77
 8049759:	ff 75 0c             	push   DWORD PTR [ebp+0xc]
 804975c:	e8 d2 fd ff ff       	call   8049533 <move_player>
 8049761:	83 c4 10             	add    esp,0x10
 8049764:	eb 13                	jmp    8049779 <solve_round+0x9b>
 8049766:	ff 75 10             	push   DWORD PTR [ebp+0x10]
 8049769:	ff 75 08             	push   DWORD PTR [ebp+0x8]
 804976c:	6a 73                	push   0x73
 804976e:	ff 75 0c             	push   DWORD PTR [ebp+0xc]
 8049771:	e8 bd fd ff ff       	call   8049533 <move_player>
 8049776:	83 c4 10             	add    esp,0x10
 8049779:	83 ec 04             	sub    esp,0x4
 804977c:	ff 75 10             	push   DWORD PTR [ebp+0x10]
 804977f:	ff 75 0c             	push   DWORD PTR [ebp+0xc]
 8049782:	ff 75 08             	push   DWORD PTR [ebp+0x8]
 8049785:	e8 c9 fc ff ff       	call   8049453 <print_map>
 804978a:	83 c4 10             	add    esp,0x10
 804978d:	8b 45 0c             	mov    eax,DWORD PTR [ebp+0xc]
 8049790:	8b 00                	mov    eax,DWORD PTR [eax]
 8049792:	83 f8 1d             	cmp    eax,0x1d
 8049795:	75 af                	jne    8049746 <solve_round+0x68>
 8049797:	83 ec 0c             	sub    esp,0xc
 804979a:	6a 00                	push   0x0
 804979c:	e8 ff f8 ff ff       	call   80490a0 <sleep@plt>
 80497a1:	83 c4 10             	add    esp,0x10
 80497a4:	8b 45 0c             	mov    eax,DWORD PTR [ebp+0xc]
 80497a7:	8b 00                	mov    eax,DWORD PTR [eax]
 80497a9:	83 f8 1d             	cmp    eax,0x1d
 80497ac:	75 09                	jne    80497b7 <solve_round+0xd9>
 80497ae:	8b 45 0c             	mov    eax,DWORD PTR [ebp+0xc]
 80497b1:	8b 40 04             	mov    eax,DWORD PTR [eax+0x4]
 80497b4:	83 f8 59             	cmp    eax,0x59
 80497b7:	8b 5d fc             	mov    ebx,DWORD PTR [ebp-0x4]
 80497ba:	c9                   	leave
 80497bb:	c3                   	ret

080497bc <win>:
 80497bc:	55                   	push   ebp
 80497bd:	89 e5                	mov    ebp,esp
 80497bf:	53                   	push   ebx
 80497c0:	83 ec 44             	sub    esp,0x44
 80497c3:	e8 88 f9 ff ff       	call   8049150 <__x86.get_pc_thunk.bx>
 80497c8:	81 c3 38 28 00 00    	add    ebx,0x2838
 80497ce:	90                   	nop
 80497cf:	90                   	nop
 80497d0:	90                   	nop
 80497d1:	90                   	nop
 80497d2:	90                   	nop
 80497d3:	90                   	nop
 80497d4:	90                   	nop
 80497d5:	90                   	nop
 80497d6:	90                   	nop
 80497d7:	90                   	nop
 80497d8:	83 ec 08             	sub    esp,0x8
 80497db:	8d 83 91 e0 ff ff    	lea    eax,[ebx-0x1f6f]
 80497e1:	50                   	push   eax
 80497e2:	8d 83 93 e0 ff ff    	lea    eax,[ebx-0x1f6d]
 80497e8:	50                   	push   eax
 80497e9:	e8 e2 f8 ff ff       	call   80490d0 <fopen@plt>
 80497ee:	83 c4 10             	add    esp,0x10
 80497f1:	89 45 f4             	mov    DWORD PTR [ebp-0xc],eax
 80497f4:	83 7d f4 00          	cmp    DWORD PTR [ebp-0xc],0x0
 80497f8:	75 30                	jne    804982a <win+0x6e>
 80497fa:	83 ec 0c             	sub    esp,0xc
 80497fd:	8d 83 9c e0 ff ff    	lea    eax,[ebx-0x1f64]
 8049803:	50                   	push   eax
 8049804:	e8 a7 f8 ff ff       	call   80490b0 <puts@plt>
 8049809:	83 c4 10             	add    esp,0x10
 804980c:	8b 83 fc ff ff ff    	mov    eax,DWORD PTR [ebx-0x4]
 8049812:	8b 00                	mov    eax,DWORD PTR [eax]
 8049814:	83 ec 0c             	sub    esp,0xc
 8049817:	50                   	push   eax
 8049818:	e8 43 f8 ff ff       	call   8049060 <fflush@plt>
 804981d:	83 c4 10             	add    esp,0x10
 8049820:	83 ec 0c             	sub    esp,0xc
 8049823:	6a 00                	push   0x0
 8049825:	e8 96 f8 ff ff       	call   80490c0 <exit@plt>
 804982a:	83 ec 04             	sub    esp,0x4
 804982d:	ff 75 f4             	push   DWORD PTR [ebp-0xc]
 8049830:	6a 3c                	push   0x3c
 8049832:	8d 45 b8             	lea    eax,[ebp-0x48]
 8049835:	50                   	push   eax
 8049836:	e8 45 f8 ff ff       	call   8049080 <fgets@plt>
 804983b:	83 c4 10             	add    esp,0x10
 804983e:	8b 45 08             	mov    eax,DWORD PTR [ebp+0x8]
 8049841:	8b 00                	mov    eax,DWORD PTR [eax]
 8049843:	83 f8 05             	cmp    eax,0x5
 8049846:	75 23                	jne    804986b <win+0xaf>
 8049848:	83 ec 0c             	sub    esp,0xc
 804984b:	8d 45 b8             	lea    eax,[ebp-0x48]
 804984e:	50                   	push   eax
 804984f:	e8 fc f7 ff ff       	call   8049050 <printf@plt>
 8049854:	83 c4 10             	add    esp,0x10
 8049857:	8b 83 fc ff ff ff    	mov    eax,DWORD PTR [ebx-0x4]
 804985d:	8b 00                	mov    eax,DWORD PTR [eax]
 804985f:	83 ec 0c             	sub    esp,0xc
 8049862:	50                   	push   eax
 8049863:	e8 f8 f7 ff ff       	call   8049060 <fflush@plt>
 8049868:	83 c4 10             	add    esp,0x10
 804986b:	90                   	nop
 804986c:	8b 5d fc             	mov    ebx,DWORD PTR [ebp-0x4]
 804986f:	c9                   	leave
 8049870:	c3                   	ret

08049871 <main>:
 8049871:	8d 4c 24 04          	lea    ecx,[esp+0x4]
 8049875:	83 e4 f0             	and    esp,0xfffffff0
 8049878:	ff 71 fc             	push   DWORD PTR [ecx-0x4]
 804987b:	55                   	push   ebp
 804987c:	89 e5                	mov    ebp,esp
 804987e:	53                   	push   ebx
 804987f:	51                   	push   ecx
 8049880:	81 ec b0 0a 00 00    	sub    esp,0xab0
 8049886:	e8 c5 f8 ff ff       	call   8049150 <__x86.get_pc_thunk.bx>
 804988b:	81 c3 75 27 00 00    	add    ebx,0x2775
 8049891:	8d 85 58 f5 ff ff    	lea    eax,[ebp-0xaa8]
 8049897:	50                   	push   eax
 8049898:	e8 69 fc ff ff       	call   8049506 <init_player>
 804989d:	83 c4 04             	add    esp,0x4
 80498a0:	c7 85 54 f5 ff ff 01 	mov    DWORD PTR [ebp-0xaac],0x1
 80498a7:	00 00 00 
 80498aa:	c7 45 f4 00 00 00 00 	mov    DWORD PTR [ebp-0xc],0x0
 80498b1:	83 ec 04             	sub    esp,0x4
 80498b4:	8d 85 54 f5 ff ff    	lea    eax,[ebp-0xaac]
 80498ba:	50                   	push   eax
 80498bb:	8d 85 58 f5 ff ff    	lea    eax,[ebp-0xaa8]
 80498c1:	50                   	push   eax
 80498c2:	8d 85 67 f5 ff ff    	lea    eax,[ebp-0xa99]
 80498c8:	50                   	push   eax
 80498c9:	e8 65 f9 ff ff       	call   8049233 <init_map>
 80498ce:	83 c4 10             	add    esp,0x10
 80498d1:	83 ec 04             	sub    esp,0x4
 80498d4:	8d 85 54 f5 ff ff    	lea    eax,[ebp-0xaac]
 80498da:	50                   	push   eax
 80498db:	8d 85 58 f5 ff ff    	lea    eax,[ebp-0xaa8]
 80498e1:	50                   	push   eax
 80498e2:	8d 85 67 f5 ff ff    	lea    eax,[ebp-0xa99]
 80498e8:	50                   	push   eax
 80498e9:	e8 65 fb ff ff       	call   8049453 <print_map>
 80498ee:	83 c4 10             	add    esp,0x10
 80498f1:	83 ec 08             	sub    esp,0x8
 80498f4:	8d 83 16 d2 ff ff    	lea    eax,[ebx-0x2dea]
 80498fa:	50                   	push   eax
 80498fb:	6a 02                	push   0x2
 80498fd:	e8 8e f7 ff ff       	call   8049090 <signal@plt>
 8049902:	83 c4 10             	add    esp,0x10
 8049905:	e8 66 f7 ff ff       	call   8049070 <getchar@plt>
 804990a:	88 45 f3             	mov    BYTE PTR [ebp-0xd],al
 804990d:	0f be 45 f3          	movsx  eax,BYTE PTR [ebp-0xd]
 8049911:	8d 95 54 f5 ff ff    	lea    edx,[ebp-0xaac]
 8049917:	52                   	push   edx
 8049918:	8d 95 67 f5 ff ff    	lea    edx,[ebp-0xa99]
 804991e:	52                   	push   edx
 804991f:	50                   	push   eax
 8049920:	8d 85 58 f5 ff ff    	lea    eax,[ebp-0xaa8]
 8049926:	50                   	push   eax
 8049927:	e8 07 fc ff ff       	call   8049533 <move_player>
 804992c:	83 c4 10             	add    esp,0x10
 804992f:	83 ec 04             	sub    esp,0x4
 8049932:	8d 85 54 f5 ff ff    	lea    eax,[ebp-0xaac]
 8049938:	50                   	push   eax
 8049939:	8d 85 58 f5 ff ff    	lea    eax,[ebp-0xaa8]
 804993f:	50                   	push   eax
 8049940:	8d 85 67 f5 ff ff    	lea    eax,[ebp-0xa99]
 8049946:	50                   	push   eax
 8049947:	e8 07 fb ff ff       	call   8049453 <print_map>
 804994c:	83 c4 10             	add    esp,0x10
 804994f:	8b 85 58 f5 ff ff    	mov    eax,DWORD PTR [ebp-0xaa8]
 8049955:	83 f8 1d             	cmp    eax,0x1d
 8049958:	75 6d                	jne    80499c7 <main+0x156>
 804995a:	8b 85 5c f5 ff ff    	mov    eax,DWORD PTR [ebp-0xaa4]
 8049960:	83 f8 59             	cmp    eax,0x59
 8049963:	75 62                	jne    80499c7 <main+0x156>
 8049965:	8b 85 54 f5 ff ff    	mov    eax,DWORD PTR [ebp-0xaac]
 804996b:	83 f8 04             	cmp    eax,0x4
 804996e:	74 57                	je     80499c7 <main+0x156>
 8049970:	83 ec 0c             	sub    esp,0xc
 8049973:	8d 83 e8 e0 ff ff    	lea    eax,[ebx-0x1f18]
 8049979:	50                   	push   eax
 804997a:	e8 31 f7 ff ff       	call   80490b0 <puts@plt>
 804997f:	83 c4 10             	add    esp,0x10
 8049982:	83 45 f4 01          	add    DWORD PTR [ebp-0xc],0x1
 8049986:	8b 85 54 f5 ff ff    	mov    eax,DWORD PTR [ebp-0xaac]
 804998c:	83 c0 01             	add    eax,0x1
 804998f:	89 85 54 f5 ff ff    	mov    DWORD PTR [ebp-0xaac],eax
 8049995:	83 ec 0c             	sub    esp,0xc
 8049998:	8d 85 58 f5 ff ff    	lea    eax,[ebp-0xaa8]
 804999e:	50                   	push   eax
 804999f:	e8 62 fb ff ff       	call   8049506 <init_player>
 80499a4:	83 c4 10             	add    esp,0x10
 80499a7:	83 ec 04             	sub    esp,0x4
 80499aa:	8d 85 54 f5 ff ff    	lea    eax,[ebp-0xaac]
 80499b0:	50                   	push   eax
 80499b1:	8d 85 58 f5 ff ff    	lea    eax,[ebp-0xaa8]
 80499b7:	50                   	push   eax
 80499b8:	8d 85 67 f5 ff ff    	lea    eax,[ebp-0xa99]
 80499be:	50                   	push   eax
 80499bf:	e8 6f f8 ff ff       	call   8049233 <init_map>
 80499c4:	83 c4 10             	add    esp,0x10
 80499c7:	8b 85 58 f5 ff ff    	mov    eax,DWORD PTR [ebp-0xaa8]
 80499cd:	83 f8 1d             	cmp    eax,0x1d
 80499d0:	0f 85 2f ff ff ff    	jne    8049905 <main+0x94>
 80499d6:	8b 85 5c f5 ff ff    	mov    eax,DWORD PTR [ebp-0xaa4]
 80499dc:	83 f8 59             	cmp    eax,0x59
 80499df:	0f 85 20 ff ff ff    	jne    8049905 <main+0x94>
 80499e5:	8b 85 54 f5 ff ff    	mov    eax,DWORD PTR [ebp-0xaac]
 80499eb:	83 f8 05             	cmp    eax,0x5
 80499ee:	0f 85 11 ff ff ff    	jne    8049905 <main+0x94>
 80499f4:	83 7d f4 04          	cmp    DWORD PTR [ebp-0xc],0x4
 80499f8:	0f 85 07 ff ff ff    	jne    8049905 <main+0x94>
 80499fe:	83 ec 0c             	sub    esp,0xc
 8049a01:	8d 85 54 f5 ff ff    	lea    eax,[ebp-0xaac]
 8049a07:	50                   	push   eax
 8049a08:	e8 af fd ff ff       	call   80497bc <win>
 8049a0d:	83 c4 10             	add    esp,0x10
 8049a10:	90                   	nop
 8049a11:	b8 00 00 00 00       	mov    eax,0x0
 8049a16:	8d 65 f8             	lea    esp,[ebp-0x8]
 8049a19:	59                   	pop    ecx
 8049a1a:	5b                   	pop    ebx
 8049a1b:	5d                   	pop    ebp
 8049a1c:	8d 61 fc             	lea    esp,[ecx-0x4]
 8049a1f:	c3                   	ret

08049a20 <__x86.get_pc_thunk.ax>:
 8049a20:	8b 04 24             	mov    eax,DWORD PTR [esp]
 8049a23:	c3                   	ret

Disassembly of section .fini:

08049a24 <_fini>:
 8049a24:	f3 0f 1e fb          	endbr32
 8049a28:	53                   	push   ebx
 8049a29:	83 ec 08             	sub    esp,0x8
 8049a2c:	e8 1f f7 ff ff       	call   8049150 <__x86.get_pc_thunk.bx>
 8049a31:	81 c3 cf 25 00 00    	add    ebx,0x25cf
 8049a37:	83 c4 08             	add    esp,0x8
 8049a3a:	5b                   	pop    ebx
 8049a3b:	c3                   	ret
