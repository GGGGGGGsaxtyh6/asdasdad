#!/usr/bin/env python3

from hashlib import sha1
from Crypto.Util.number import bytes_to_long, long_to_bytes
from ecdsa import ellipticcurve
from ecdsa.ecdsa import curve_256, generator_256, Public_key, Private_key

# Parámetros de la curva
G = generator_256
q = G.order()
p = curve_256.p()

# Datos dados
hidden_flag = (16807196250009982482930925323199249441776811719221084165690521045921016398804, 72892323560996016030675756815328265928288098939353836408589138718802282948311)
pubkey_point = (48780765048182146279105449292746800142985733726316629478905429239240156048277, 74172919609718191102228451394074168154654001177799772446328904575002795731796)

# Firmas
signatures = [
    {
        'msg': 'I have hidden the secret flag as a point of an elliptic curve using my private key.',
        'r': 0x91f66ac7557233b41b3044ab9daf0ad891a8ffcaf99820c3cd8a44fc709ed3ae,
        's': 0x1dd0a378454692eb4ad68c86732404af3e73c6bf23a8ecc5449500fcab05208d
    },
    {
        'msg': 'The discrete logarithm problem is very hard to solve, so it will remain a secret forever.',
        'r': 0xe8875e56b79956d446d24f06604b7705905edac466d5469f815547dea7a3171c,
        's': 0x582ecf967e0e3acf5e3853dbe65a84ba59c3ec8a43951bcff08c64cb614023f8
    },
    {
        'msg': 'Good luck!',
        'r': 0x566ce1db407edae4f32a20defc381f7efb63f712493c3106cf8e85f464351ca6,
        's': 0x9e4304a36d2c83ef94e19a60fb98f659fa874bfb999712ceb58382e2ccda26ba
    }
]

def get_nonce(privkey_d, msg):
    """Generar nonce usando el mismo método que en el desafío"""
    hsh = sha1(msg.encode()).digest()
    nonce = sha1(long_to_bytes(privkey_d) + hsh).digest()
    return bytes_to_long(nonce)

def ataque_ecuaciones_simultaneas():
    """Intentar resolver el sistema de ecuaciones simultáneas"""
    print("Intentando resolver sistema de ecuaciones simultáneas...")
    
    # Obtener hashes de mensajes
    h1 = bytes_to_long(sha1(signatures[0]['msg'].encode()).digest())
    h2 = bytes_to_long(sha1(signatures[1]['msg'].encode()).digest())
    h3 = bytes_to_long(sha1(signatures[2]['msg'].encode()).digest())
    
    r1, s1 = signatures[0]['r'], signatures[0]['s']
    r2, s2 = signatures[1]['r'], signatures[1]['s']
    r3, s3 = signatures[2]['r'], signatures[2]['s']
    
    print(f"Ecuaciones:")
    print(f"s1 * k1 = h1 + r1 * d mod q")
    print(f"s2 * k2 = h2 + r2 * d mod q") 
    print(f"s3 * k3 = h3 + r3 * d mod q")
    print(f"donde k1 = sha1(d + h1), k2 = sha1(d + h2), k3 = sha1(d + h3)")
    
    # Intentar resolver usando el hecho de que tenemos múltiples ecuaciones
    # Para dos firmas: s1*k1 = h1 + r1*d y s2*k2 = h2 + r2*d
    # Donde k1 = sha1(d + h1) y k2 = sha1(d + h2)
    
    # Esto nos da: d = (s1*k1 - h1) * r1^(-1) mod q
    # Y también: d = (s2*k2 - h2) * r2^(-1) mod q
    
    # Por lo tanto: (s1*k1 - h1) * r1^(-1) = (s2*k2 - h2) * r2^(-1) mod q
    # Simplificando: s1*k1*r2 = s2*k2*r1 + h1*r2 - h2*r1 mod q
    
    # Pero k1 = sha1(d + h1) y k2 = sha1(d + h2), así que tenemos:
    # s1*sha1(d + h1)*r2 = s2*sha1(d + h2)*r1 + h1*r2 - h2*r1 mod q
    
    print("Intentando resolver esta ecuación...")
    
    # Calcular inversos modulares
    r1_inv = pow(r1, -1, q)
    r2_inv = pow(r2, -1, q)
    r3_inv = pow(r3, -1, q)
    
    # Intentar valores de d y verificar si satisfacen las ecuaciones
    print("Probando valores de d...")
    
    for d in range(1, 1000000):  # Rango más pequeño para prueba
        if d % 10000 == 0:
            print(f"Probando d = {d}")
        
        try:
            # Generar nonces
            k1 = get_nonce(d, signatures[0]['msg'])
            k2 = get_nonce(d, signatures[1]['msg'])
            k3 = get_nonce(d, signatures[2]['msg'])
            
            # Verificar si d genera la clave pública correcta
            test_pubkey = Public_key(G, d * G)
            if (int(test_pubkey.point.x()), int(test_pubkey.point.y())) == pubkey_point:
                print(f"¡Encontrada clave pública coincidente para d = {d}!")
                
                # Verificar todas las firmas
                valid = 0
                if (s1 * k1) % q == (h1 + r1 * d) % q:
                    valid += 1
                if (s2 * k2) % q == (h2 + r2 * d) % q:
                    valid += 1
                if (s3 * k3) % q == (h3 + r3 * d) % q:
                    valid += 1
                
                if valid == 3:
                    print(f"¡Todas las firmas verificadas! Clave privada: d = {d}")
                    return d
                else:
                    print(f"Solo {valid}/3 firmas verificadas para d = {d}")
        except:
            continue
    
    return None

def recuperar_flag(privkey_d):
    """Recuperar el flag usando la clave privada"""
    print(f"Recuperando flag con clave privada d = {privkey_d}")
    
    T_x, T_y = hidden_flag
    T = ellipticcurve.Point(curve_256, T_x, T_y)
    
    # Calcular Q = (1/d) * T
    d_inv = pow(privkey_d, -1, q)
    Q = d_inv * T
    
    print(f"Punto del flag recuperado: Q = ({int(Q.x())}, {int(Q.y())})")
    return int(Q.x()), int(Q.y())

def extraer_flag(x, y):
    """Extraer flag de las coordenadas del punto"""
    print(f"Extrayendo flag del punto ({x}, {y})")
    
    flag_bytes = long_to_bytes(x)
    
    try:
        flag = flag_bytes.decode('utf-8')
        print(f"Flag extraído: {flag}")
        return flag
    except:
        print("No se pudo decodificar como UTF-8")
        print(f"Bytes crudos: {flag_bytes}")
        return None

def main():
    print("=== Ataque Matemático ECDSA ===")
    print("Intentando resolver sistema de ecuaciones...")
    print()
    
    # Intentar ataque matemático
    privkey_d = ataque_ecuaciones_simultaneas()
    
    if privkey_d is None:
        print("No se pudo resolver con ecuaciones simultáneas")
        return
    
    # Recuperar flag
    flag_x, flag_y = recuperar_flag(privkey_d)
    
    # Extraer flag
    flag = extraer_flag(flag_x, flag_y)
    
    if flag:
        print(f"\n=== FLAG ENCONTRADO ===")
        print(f"crypto{{{flag}}}")
    else:
        print("No se pudo extraer el flag")

if __name__ == "__main__":
    main()