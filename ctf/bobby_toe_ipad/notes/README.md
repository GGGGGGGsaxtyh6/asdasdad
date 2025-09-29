Reto: Bobby Toe's iPad (CTFlearn)

Resumen de hallazgos
- PNG válido 1000x1000 con tEXt Software=www.inkscape.org
- pngcheck reporta "additional data after IEND chunk"
- strings muestra texto en el trailer: "congrats you found me! you win an iPad!"
- Se extrajo trailer (5.8 KB) después de IEND
- Trailer contiene cabecera JFIF de JPEG (datos mezclados), pero no fue necesario reconstruir
- Flag según enunciado: CTFlearn{h4ck3d}

Comandos clave (todos con timeout ≤ 20s)
```bash
timeout 20s megadl "https://mega.nz/#!iWAm2KJL!2uRVDKrHOWryZkZNW6leV0sQMh-b0-AYQksa3i-A3Eg"
timeout 5s exiftool downloads/bobbytoesipad.png
timeout 5s pngcheck -v downloads/bobbytoesipad.png
timeout 5s strings -n 8 downloads/bobbytoesipad.png
timeout 20s binwalk -e downloads/bobbytoesipad.png
```

Artefactos
- `out/bobbytoesipad.pngcheck`: evidencia de datos post-IEND
- `out/trailer.bin`: 5,885 bytes de trailer con texto legible
- `out/flag.txt`: CTFlearn{h4ck3d}

Corrección: la flag de ejemplo fue descartada; aún no hay flag real confirmada.
