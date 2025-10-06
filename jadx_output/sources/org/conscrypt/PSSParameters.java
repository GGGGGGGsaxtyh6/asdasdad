package org.conscrypt;

import java.io.IOException;
import java.security.AlgorithmParametersSpi;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;

/* loaded from: classes.dex */
public class PSSParameters extends AlgorithmParametersSpi {
    private PSSParameterSpec spec = PSSParameterSpec.DEFAULT;

    @Override // java.security.AlgorithmParametersSpi
    public byte[] engineGetEncoded() {
        long j2;
        Throwable th;
        long j3;
        IOException e2;
        long j4 = 0;
        try {
            try {
                j3 = NativeCrypto.asn1_write_init();
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                long asn1_write_sequence = NativeCrypto.asn1_write_sequence(j3);
                try {
                    OAEPParameters.writeHashAndMgfHash(asn1_write_sequence, this.spec.getDigestAlgorithm(), (MGF1ParameterSpec) this.spec.getMGFParameters());
                    if (this.spec.getSaltLength() != 20) {
                        try {
                            j4 = NativeCrypto.asn1_write_tag(asn1_write_sequence, 2);
                            NativeCrypto.asn1_write_uint64(j4, this.spec.getSaltLength());
                            NativeCrypto.asn1_write_flush(asn1_write_sequence);
                            NativeCrypto.asn1_write_free(j4);
                        } catch (Throwable th3) {
                            NativeCrypto.asn1_write_flush(asn1_write_sequence);
                            NativeCrypto.asn1_write_free(j4);
                            throw th3;
                        }
                    }
                    byte[] asn1_write_finish = NativeCrypto.asn1_write_finish(j3);
                    NativeCrypto.asn1_write_free(asn1_write_sequence);
                    NativeCrypto.asn1_write_free(j3);
                    return asn1_write_finish;
                } catch (IOException e3) {
                    e2 = e3;
                    NativeCrypto.asn1_write_cleanup(j3);
                    throw e2;
                }
            } catch (IOException e4) {
                e2 = e4;
            } catch (Throwable th4) {
                th = th4;
                j2 = 0;
                NativeCrypto.asn1_write_free(j2);
                NativeCrypto.asn1_write_free(j3);
                throw th;
            }
        } catch (IOException e5) {
            e2 = e5;
            j3 = 0;
        } catch (Throwable th5) {
            j2 = 0;
            th = th5;
            j3 = 0;
        }
    }

    @Override // java.security.AlgorithmParametersSpi
    public <T extends AlgorithmParameterSpec> T engineGetParameterSpec(Class<T> cls) {
        if (cls != null && cls == PSSParameterSpec.class) {
            return this.spec;
        }
        throw new InvalidParameterSpecException("Unsupported class: " + cls);
    }

    @Override // java.security.AlgorithmParametersSpi
    public void engineInit(AlgorithmParameterSpec algorithmParameterSpec) {
        if (algorithmParameterSpec instanceof PSSParameterSpec) {
            this.spec = (PSSParameterSpec) algorithmParameterSpec;
            return;
        }
        throw new InvalidParameterSpecException("Only PSSParameterSpec is supported");
    }

    @Override // java.security.AlgorithmParametersSpi
    public String engineToString() {
        return "Conscrypt PSS AlgorithmParameters";
    }

    @Override // java.security.AlgorithmParametersSpi
    public void engineInit(byte[] bArr) {
        long j2;
        long asn1_read_sequence;
        int asn1_read_uint64;
        long j3 = 0;
        try {
            j2 = NativeCrypto.asn1_read_init(bArr);
            try {
                asn1_read_sequence = NativeCrypto.asn1_read_sequence(j2);
            } catch (Throwable th) {
                th = th;
            }
            try {
                String readHash = OAEPParameters.readHash(asn1_read_sequence);
                String readMgfHash = OAEPParameters.readMgfHash(asn1_read_sequence);
                if (NativeCrypto.asn1_read_next_tag_is(asn1_read_sequence, 2)) {
                    try {
                        long asn1_read_tagged = NativeCrypto.asn1_read_tagged(asn1_read_sequence);
                        try {
                            asn1_read_uint64 = (int) NativeCrypto.asn1_read_uint64(asn1_read_tagged);
                            NativeCrypto.asn1_read_free(asn1_read_tagged);
                        } catch (Throwable th2) {
                            th = th2;
                            j3 = asn1_read_tagged;
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } else {
                    asn1_read_uint64 = 20;
                }
                int i2 = asn1_read_uint64;
                if (NativeCrypto.asn1_read_next_tag_is(asn1_read_sequence, 3)) {
                    try {
                        j3 = NativeCrypto.asn1_read_tagged(asn1_read_sequence);
                        long asn1_read_uint642 = (int) NativeCrypto.asn1_read_uint64(j3);
                        NativeCrypto.asn1_read_free(j3);
                        if (asn1_read_uint642 != 1) {
                            throw new IOException("Error reading ASN.1 encoding");
                        }
                    } finally {
                        NativeCrypto.asn1_read_free(j3);
                    }
                }
                if (NativeCrypto.asn1_read_is_empty(asn1_read_sequence) && NativeCrypto.asn1_read_is_empty(j2)) {
                    this.spec = new PSSParameterSpec(readHash, "MGF1", new MGF1ParameterSpec(readMgfHash), i2, 1);
                    NativeCrypto.asn1_read_free(asn1_read_sequence);
                    NativeCrypto.asn1_read_free(j2);
                    return;
                }
                throw new IOException("Error reading ASN.1 encoding");
            } catch (Throwable th4) {
                th = th4;
                j3 = asn1_read_sequence;
                NativeCrypto.asn1_read_free(j3);
                NativeCrypto.asn1_read_free(j2);
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            j2 = 0;
        }
    }

    @Override // java.security.AlgorithmParametersSpi
    public byte[] engineGetEncoded(String str) {
        if (str != null && !str.equals("ASN.1") && !str.equals("X.509")) {
            throw new IOException("Unsupported format: ".concat(str));
        }
        return engineGetEncoded();
    }

    @Override // java.security.AlgorithmParametersSpi
    public void engineInit(byte[] bArr, String str) {
        if (str != null && !str.equals("ASN.1") && !str.equals("X.509")) {
            throw new IOException("Unsupported format: ".concat(str));
        }
        engineInit(bArr);
    }
}
