#!/usr/bin/env python3

import math
import struct

class EsotericInterpreter:
    def __init__(self):
        self.stack = []
        self.vars = {}
        self.functions = {}
        self.function_data = {}
        self.nfs = 0
        
    def f2b(self, val):
        """Convierte float a representación de 2 bits"""
        if val == 0.0:
            return 0
        elif val == float('inf'):
            return 1
        elif val == -0.0:
            return 2
        elif val == float('-inf'):
            return 3
        else:
            return 0
    
    def b2f(self, val):
        """Convierte representación de 2 bits a float"""
        if val == 0:
            return 0.0
        elif val == 1:
            return float('inf')
        elif val == 2:
            return -0.0
        elif val == 3:
            return float('-inf')
        else:
            return 0.0
    
    def c2f(self, byte):
        """Convierte byte a 4 floats usando 2 bits por float"""
        out = [0.0, 0.0, 0.0, 0.0]
        out[3] = self.b2f(byte & 3)
        out[2] = self.b2f((byte >> 2) & 3)
        out[1] = self.b2f((byte >> 4) & 3)
        out[0] = self.b2f((byte >> 6) & 3)
        return out
    
    def f2c(self, floats):
        """Convierte 4 floats de vuelta a byte"""
        return self.f2b(floats[0]) + (self.f2b(floats[1]) << 2) + (self.f2b(floats[2]) << 4) + (self.f2b(floats[3]) << 6)
    
    def push(self, val):
        self.stack.append(val)
    
    def pop(self):
        return self.stack.pop()
    
    def cpop(self):
        """Convierte 4 floats del stack a byte"""
        f4 = self.pop()
        f3 = self.pop()
        f2 = self.pop()
        f1 = self.pop()
        return self.f2c([f1, f2, f3, f4])
    
    def execute_instruction(self, code, args):
        """Ejecuta una instrucción del intérprete esotérico"""
        if not code:
            return True
            
        opcode = code[0]
        code = code[1:]
        
        if opcode == ';':
            return True
            
        if opcode.isalnum() and opcode not in 'mMA01':
            if code and code[0] == '=':
                code = code[1:]
                # Asignación de variable
                while not self.execute_instruction(code, args):
                    pass
                self.vars[opcode] = self.pop()
            else:
                # Push de variable o argumento
                if opcode.isdigit():
                    self.push(args[int(opcode) - 2])
                else:
                    self.push(self.vars.get(opcode, 0.0))
            return False
            
        if opcode == ':':
            # Definición de función
            func_name = ""
            while code and code[0] != ':':
                func_name += code[0]
                code = code[1:]
            code = code[1:]  # Skip ':'
            
            func_data = ""
            nest = 1
            while code and nest > 0:
                ch = code[0]
                code = code[1:]
                if ch == '=':
                    nest += 1
                elif ch == ';':
                    nest -= 1
                func_data += ch
            
            self.functions[func_name] = func_data
            self.nfs += 1
            return False
            
        if opcode == '^':
            # Llamada de función
            for func_name, func_data in self.functions.items():
                if code.startswith(func_name):
                    new_args = [0.0] * 8
                    for j in range(min(8, len(self.stack))):
                        new_args[j] = self.stack[-(j+1)]
                    
                    func_code = func_data
                    while not self.execute_instruction(func_code, new_args):
                        pass
                    code = code[len(func_name):]
                    return False
            
        # Operaciones aritméticas
        if opcode == '+':
            b = self.pop()
            a = self.pop()
            self.push(a + b)
        elif opcode == '-':
            b = self.pop()
            a = self.pop()
            self.push(a - b)
        elif opcode == '*':
            b = self.pop()
            a = self.pop()
            self.push(a * b)
        elif opcode == '/':
            b = self.pop()
            a = self.pop()
            self.push(a / b)
        elif opcode == 'm':
            b = self.pop()
            a = self.pop()
            self.push(min(a, b))
        elif opcode == 'M':
            b = self.pop()
            a = self.pop()
            self.push(max(a, b))
        elif opcode == '0':
            self.push(0.0)
        elif opcode == '1':
            self.push(float('inf'))
        elif opcode == '\'':
            val = self.pop()
            self.push(1.0 / val if val != 0 else float('inf'))
        elif opcode == '!':
            val = self.pop()
            self.push(-val)
        elif opcode == '.':
            self.pop()
            
        return False

def decrypt_flag():
    # La bandera encriptada
    encrypted_hex = "fa8c3453029e0e236800c26cc9d1748dde9e98c1e24804cb9602b68a4fd424fc7d7dca9fb3b4f6102ce5d3fb70cf4af0d1204c1df031fb0ec583c5108fca97904d35b6b60183cac9b6183e4e4dc94d4d8fc9356d"
    encrypted_bytes = bytes.fromhex(encrypted_hex)
    
    print(f"Datos encriptados: {len(encrypted_bytes)} bytes")
    
    # El código del intérprete
    code = ":i:...033**03'3'**m4-3'1!M3'1m-31!M31m-+'2-m;:n00:'1-;:n10:'1+;:n01:1-;:n11:1+;:g1:..10*2^n103^n10m1^i2^n103^n11m1^i2^n113^n10m1^i2^n013^n01m0!^i2^n113^n01m0!^i2^n013^n11m0!^i2^n113^n11m0^i1!M;:g2:^g1'!;:g3:..2'!3'!^g1;:g4:..23^g323^g1^g2;:x1:^g4^g4;:x2:...23^g44^g223^g2^g3;:sg:.021+2'1++1^i;:x3:...22*33*4^x1;:x4:...02^sg3^sg22*33*4^x2^x10*0!^i;:x5:...2^sg3^sg22*33*33*4^x2^x2;:x6:...432^x3432^x4^g3;:xb:........026^x537^x548^x559^x6026^x537^x548^x6026^x537^x6026^x6;:gb4:........59^g448^g437^g426^g4;:gb2:........59^g248^g237^g226^g2;:gb3:........59^g348^g337^g326^g3;:nb:23451!1!1!1!^gb40001^xb;:o1r:.12^n000^i;:fnz:.1!20^i1!20^i1!20^i1!20^i;:ftz:^g3^g3^g3^o1r^n01^fnz;:l:........9876^ftz5432^gb2^gb398761!1!1!1!^xb;:al:....0000543210!1!1^l1!1!1!1^l100!1!^l11!0!0^l10!11^l1!0!01^l0!1!1!1^l110!0^l0!0!11!^l011!0!^l1!10!0^l0!000!^l00!01!^l0011!^l00!00!^l1!0!1!1^l1000^l0!0!1!1!^l0!1!0!0!^l1!01!1!^l10!0!0!^l00!11!^l0!11!1!^l11!11!^l0!0!1!1^l1!00!1!^l00!1!1^l0!0!0!1!^l111!1!^l00!1!0!^l0!00!0!^l00!10!^l1!000!^l11!0!1^l1!101!^l00!0!1^l1!0!0!1!^l0!101^l100!0!^l0!10!0^l00!10^l1!100!^l1011!^l1!010!^l11!1!0!^l0!00!0^l010!0^l011!1^l1!1!01!^l0000!^l0101!^l0!010!^l01!01!^l1!1!1!0^l101!0^l0!110!^l0!11!0^l1010^l010!0!^l0!01!0^l1!1!11!^l0!0!01!^l0!11!1^l0!011^l110!1^l000!0!^l01!1!0!^l0!0!1!0^l1!01!0!^l01!0!0!^l1!100^l1!0!00!^l0!1!0!0^l0!11!0!^l1010!^l0000^l01!0!1!^l101!0!^l1!1!0!0!^l1!111^l00!1!0^l000!1!^l0!1!11^l0!00!1!^l1!0!00^l0!011!^l11!01!^l01!1!1^l11!01^l001!1^l00!11^l10!1!1!^l10!1!0^l01!1!0^l10!0!1^l101!1^l11!11^l0!01!1!^l0!10!0!^l1!011^l10!0!1!^l01!11^l0!001!^l1!0!1!0!^l1!110^l0001^l1111!^l10!01^l00!0!0!^l0!00!1^l1!00!0!^l0!1!0!1!^l0100^l1!01!0^l0!100^l0!1!10!^l1!00!1^l1101^l101!1!^l11!00^l1!1!00^l1101!^l0!111!^l011!1!^l0!1!11!^l011!0^l0!1!0!1^l0!01!0!^l0!0!00!^l1110!^l1!0!1!0^l1110^l00!00^l0!1!00!^l1!111!^l01!00^l1!1!10!^l10!00^l11!00!^l0111!^l1!010^l1!10!1^l0!000^l10!10^l0!0!11^l000!0^l11!1!0^l0111^l0!010^l10!00!^l11!0!1!^l1!000^l0100!^l1!001^l1000!^l10!11!^l1!1!00!^l0!1!10^l01!10^l0!0!01^l010!1!^l1!1!10^l1100!^l1!11!1!^l01!1!1!^l0!0!00^l1!0!0!1^l0!001^l0!1!01^l00!0!1!^l11!10!^l1!011!^l10!01!^l1!1!1!0!^l00!01^l00!0!0^l1!0!01!^l11!10^l1!0!11!^l1!110!^l0!1!1!0^l0!1!1!1!^l0!10!1^l11!1!1^l0010^l000!1^l1001!^l0!0!10!^l1!10!0!^l10!10!^l111!0!^l0!111^l0!101!^l0!0!1!0!^l01!01^l1!1!0!1^l11!0!0!^l01!0!1^l1001^l1!11!0^l1!0!10!^l1!11!1^l01!00!^l100!1^l0!0!0!1^l110!1!^l01!10!^l10!1!0!^l111!1^l111!0^l10!0!0^l0!0!10^l0010!^l0!110^l1!10!1!^l1!1!1!1!^l1!0!0!0^l001!0!^l1011^l100!0^l0!100!^l1!1!11^l0011^l0110^l0!1!00^l0!10!1!^l001!1!^l00!1!1!^l0001!^l1!1!01^l0!1!01!^l1!1!0!1!^l0110!^l0!01!1^l1!0!0!0!^l001!0^l01!0!0^l1!11!0!^l010!1^l1!0!11^l1!01!1^l1100^l1!101^l11!1!1!^l0!0!0!0^l0!0!0!0!^l1!1!0!0^l1!00!0^l0101^l1!0!1!1!^l0!1!1!0!^l1!0!10^l1!001!^l1111^l110!0!^l01!11!^l....;;"
    
    # El algoritmo de encriptación
    algo = "aqpb2345^xbhijg6789^xbfeno5432^xbcdlk9876^xbabcd6789^gb42345nopq^gb4^xbijkl2345^gb4efgh6789^gb4^xb2345^al;"
    
    # Intentar diferentes longitudes de flag
    for flag_len in range(10, 50):
        if flag_len * 3 // 2 > len(encrypted_bytes):
            break
            
        flag_bytes = encrypted_bytes[:flag_len]
        checksum_bytes = encrypted_bytes[flag_len:flag_len + (flag_len // 2)]
        
        if len(checksum_bytes) != flag_len // 2:
            continue
            
        print(f"\nProbando longitud de flag: {flag_len}")
        print(f"Flag bytes: {flag_bytes.hex()}")
        print(f"Checksum bytes: {checksum_bytes.hex()}")
        
        # Intentar descifrar usando fuerza bruta para los primeros caracteres
        if flag_len >= 5:
            # Intentar con "actf{"
            test_flag = bytearray(flag_len)
            test_flag[:5] = b"actf{"
            
            # Simular el proceso de encriptación
            interpreter = EsotericInterpreter()
            
            # Ejecutar el código principal
            code_ptr = code
            while not interpreter.execute_instruction(code_ptr, [0.0] * 8):
                pass
            
            # Intentar descifrar
            chain = 0x5f3759df
            decrypted = bytearray(flag_len)
            
            try:
                for i in range(0, flag_len, 2):
                    if i + 1 < flag_len:
                        # Simular la función encrypt_byte
                        inargs = [0.0] * 8
                        
                        # Convertir bytes a floats
                        floats1 = interpreter.c2f(flag_bytes[i])
                        floats2 = interpreter.c2f(flag_bytes[i+1])
                        
                        inargs[4:8] = floats1
                        inargs[0:4] = floats2
                        
                        # Convertir chain a floats
                        temp = [0.0] * 4
                        temp_floats = interpreter.c2f(chain & 0xff)
                        for j in range(4):
                            temp[j] = temp_floats[j]
                        
                        # Asignar variables
                        vname = ord('a')
                        for k in range(4):
                            for j in range(4):
                                interpreter.vars[chr(vname)] = temp[j]
                                vname += 1
                                if chr(vname) == 'm':
                                    vname += 1
                            chain >>= 8
                        
                        # Ejecutar algoritmo
                        algo_ptr = algo
                        while not interpreter.execute_instruction(algo_ptr, inargs):
                            pass
                        
                        # Obtener resultados
                        cs = interpreter.cpop()
                        decrypted[i+1] = interpreter.cpop()
                        decrypted[i] = interpreter.cpop()
                        
                        # Actualizar chain
                        chain = interpreter.cpop()
                        chain = (chain << 8) | interpreter.cpop()
                        chain = (chain << 8) | interpreter.cpop()
                        chain = (chain << 8) | interpreter.cpop()
                        
                        print(f"Byte {i}: {decrypted[i]:02x}, Byte {i+1}: {decrypted[i+1]:02x}")
                        
            except Exception as e:
                print(f"Error: {e}")
                continue
            
            # Verificar si el resultado tiene sentido
            try:
                decrypted_str = decrypted.decode('ascii', errors='ignore')
                if 'actf{' in decrypted_str and '}' in decrypted_str:
                    print(f"¡POSIBLE BANDERA ENCONTRADA: {decrypted_str}")
                    return decrypted_str
            except:
                pass

if __name__ == "__main__":
    decrypt_flag()