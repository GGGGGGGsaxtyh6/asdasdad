import re
from pathlib import Path

disasm_path = Path('/workspace/bare_metal/analysis/disasm.S')
text = disasm_path.read_text(errors='ignore')

prog = []
line_re = re.compile(r'^\s*([0-9a-f]+):\s+(?:[0-9a-f]{2}\s+)*([a-zA-Z.]+)\s*(.*)$')
for line in text.splitlines():
    m = line_re.match(line)
    if not m:
        continue
    addr = int(m.group(1), 16)
    ins = m.group(2).lower()
    arg = (m.group(3) or '').strip()
    # strip comment
    if ';' in arg:
        arg = arg.split(';', 1)[0].strip()
    prog.append((addr, ins, arg))

# Simulate PORTD writes via sbi/cbi on I/O 0x0b (PORTD)
portd = 0
timeline = []
for addr, ins, arg in prog:
    if ins in ('sbi', 'cbi') and ',' in arg:
        parts = [p.strip() for p in arg.split(',', 1)]
        if len(parts) != 2:
            continue
        io_str, bit_str = parts
        if io_str.lower() == '0x0b':
            try:
                bit = int(bit_str, 0)
            except ValueError:
                continue
            if ins == 'sbi':
                portd |= (1 << bit)
            else:
                portd &= ~(1 << bit)
            timeline.append((addr, portd))

# Use D6 as clock, sample D5 on rising edges
bits = []
prev_clk = 0
for addr, pd in timeline:
    clk = (pd >> 6) & 1
    dat = (pd >> 5) & 1
    if clk == 1 and prev_clk == 0:
        bits.append(dat)
    prev_clk = clk

# Convert bits to bytes
lsb_bytes = []
msb_bytes = []
for i in range(0, len(bits) // 8 * 8, 8):
    b_lsb = 0
    b_msb = 0
    for k in range(8):
        b_lsb |= (bits[i + k] & 1) << k
        b_msb = (b_msb << 1) | (bits[i + k] & 1)
    lsb_bytes.append(b_lsb)
    msb_bytes.append(b_msb)

lsb_ascii = bytes(lsb_bytes).decode('latin1', 'ignore')
msb_ascii = bytes(msb_bytes).decode('latin1', 'ignore')

out_dir = Path('/workspace/bare_metal/outputs')
out_dir.mkdir(parents=True, exist_ok=True)
(out_dir / 'decoded_lsb.txt').write_text(lsb_ascii, errors='ignore')
(out_dir / 'decoded_msb.txt').write_text(msb_ascii, errors='ignore')

print('LEN_BITS', len(bits))
print('LSB_ASCII')
print(lsb_ascii)
print('MSB_ASCII')
print(msb_ascii)
