package org.conscrypt;

import java.io.IOException;
import java.security.AlgorithmParametersSpi;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.MGF1ParameterSpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

/* loaded from: classes.dex */
public class OAEPParameters extends AlgorithmParametersSpi {
    private static final String MGF1_OID = "1.2.840.113549.1.1.8";
    private static final Map<String, String> NAME_TO_OID;
    private static final Map<String, String> OID_TO_NAME;
    private static final String PSPECIFIED_OID = "1.2.840.113549.1.1.9";
    private OAEPParameterSpec spec = OAEPParameterSpec.DEFAULT;

    /* JADX WARN: Multi-variable type inference failed */
    static {
        HashMap hashMap = new HashMap();
        OID_TO_NAME = hashMap;
        NAME_TO_OID = new HashMap();
        hashMap.put("1.3.14.3.2.26", "SHA-1");
        hashMap.put("2.16.840.1.101.3.4.2.4", "SHA-224");
        hashMap.put("2.16.840.1.101.3.4.2.1", "SHA-256");
        hashMap.put("2.16.840.1.101.3.4.2.2", "SHA-384");
        hashMap.put("2.16.840.1.101.3.4.2.3", "SHA-512");
        for (Map.Entry entry : hashMap.entrySet()) {
            NAME_TO_OID.put(entry.getValue(), entry.getKey());
        }
    }

    private static String getHashName(long j2) {
        long j3;
        try {
            j3 = NativeCrypto.asn1_read_sequence(j2);
            try {
                String asn1_read_oid = NativeCrypto.asn1_read_oid(j3);
                if (!NativeCrypto.asn1_read_is_empty(j3)) {
                    NativeCrypto.asn1_read_null(j3);
                }
                if (NativeCrypto.asn1_read_is_empty(j3)) {
                    Map<String, String> map = OID_TO_NAME;
                    if (map.containsKey(asn1_read_oid)) {
                        String str = map.get(asn1_read_oid);
                        NativeCrypto.asn1_read_free(j3);
                        return str;
                    }
                }
                throw new IOException("Error reading ASN.1 encoding");
            } catch (Throwable th) {
                th = th;
                NativeCrypto.asn1_read_free(j3);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            j3 = 0;
        }
    }

    public static String readHash(long j2) {
        long j3;
        if (NativeCrypto.asn1_read_next_tag_is(j2, 0)) {
            try {
                j3 = NativeCrypto.asn1_read_tagged(j2);
                try {
                    String hashName = getHashName(j3);
                    NativeCrypto.asn1_read_free(j3);
                    return hashName;
                } catch (Throwable th) {
                    th = th;
                    NativeCrypto.asn1_read_free(j3);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                j3 = 0;
            }
        } else {
            return "SHA-1";
        }
    }

    public static String readMgfHash(long j2) {
        long j3;
        if (NativeCrypto.asn1_read_next_tag_is(j2, 1)) {
            try {
                j3 = NativeCrypto.asn1_read_tagged(j2);
                try {
                    long asn1_read_sequence = NativeCrypto.asn1_read_sequence(j3);
                    if (NativeCrypto.asn1_read_oid(asn1_read_sequence).equals(MGF1_OID)) {
                        String hashName = getHashName(asn1_read_sequence);
                        if (NativeCrypto.asn1_read_is_empty(asn1_read_sequence)) {
                            NativeCrypto.asn1_read_free(asn1_read_sequence);
                            NativeCrypto.asn1_read_free(j3);
                            return hashName;
                        }
                        throw new IOException("Error reading ASN.1 encoding");
                    }
                    throw new IOException("Error reading ASN.1 encoding");
                } catch (Throwable th) {
                    th = th;
                    NativeCrypto.asn1_read_free(0L);
                    NativeCrypto.asn1_read_free(j3);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                j3 = 0;
            }
        } else {
            return "SHA-1";
        }
    }

    private static long writeAlgorithmIdentifier(long j2, String str) {
        long j3;
        try {
            j3 = NativeCrypto.asn1_write_sequence(j2);
        } catch (IOException e2) {
            e = e2;
            j3 = 0;
        }
        try {
            NativeCrypto.asn1_write_oid(j3, str);
            return j3;
        } catch (IOException e3) {
            e = e3;
            NativeCrypto.asn1_write_free(j3);
            throw e;
        }
    }

    public static void writeHashAndMgfHash(long j2, String str, MGF1ParameterSpec mGF1ParameterSpec) {
        long j3;
        long j4;
        long j5;
        long j6 = 0;
        if (!str.equals("SHA-1")) {
            try {
                j5 = NativeCrypto.asn1_write_tag(j2, 0);
                try {
                    long writeAlgorithmIdentifier = writeAlgorithmIdentifier(j5, NAME_TO_OID.get(str));
                    try {
                        NativeCrypto.asn1_write_null(writeAlgorithmIdentifier);
                        NativeCrypto.asn1_write_flush(j2);
                        NativeCrypto.asn1_write_free(writeAlgorithmIdentifier);
                        NativeCrypto.asn1_write_free(j5);
                    } catch (Throwable th) {
                        th = th;
                        j6 = writeAlgorithmIdentifier;
                        NativeCrypto.asn1_write_flush(j2);
                        NativeCrypto.asn1_write_free(j6);
                        NativeCrypto.asn1_write_free(j5);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                j5 = 0;
            }
        }
        if (!mGF1ParameterSpec.getDigestAlgorithm().equals("SHA-1")) {
            try {
                j3 = NativeCrypto.asn1_write_tag(j2, 1);
                try {
                    j4 = writeAlgorithmIdentifier(j3, MGF1_OID);
                } catch (Throwable th4) {
                    th = th4;
                    j4 = 0;
                }
            } catch (Throwable th5) {
                th = th5;
                j3 = 0;
                j4 = 0;
            }
            try {
                j6 = writeAlgorithmIdentifier(j4, NAME_TO_OID.get(mGF1ParameterSpec.getDigestAlgorithm()));
                NativeCrypto.asn1_write_null(j6);
                NativeCrypto.asn1_write_flush(j2);
                NativeCrypto.asn1_write_free(j6);
                NativeCrypto.asn1_write_free(j4);
                NativeCrypto.asn1_write_free(j3);
            } catch (Throwable th6) {
                th = th6;
                NativeCrypto.asn1_write_flush(j2);
                NativeCrypto.asn1_write_free(j6);
                NativeCrypto.asn1_write_free(j4);
                NativeCrypto.asn1_write_free(j3);
                throw th;
            }
        }
    }

    @Override // java.security.AlgorithmParametersSpi
    public byte[] engineGetEncoded() {
        long j2;
        Throwable th;
        long j3;
        IOException e2;
        long j4;
        long j5 = 0;
        try {
            try {
                j3 = NativeCrypto.asn1_write_init();
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                long asn1_write_sequence = NativeCrypto.asn1_write_sequence(j3);
                try {
                    writeHashAndMgfHash(asn1_write_sequence, this.spec.getDigestAlgorithm(), (MGF1ParameterSpec) this.spec.getMGFParameters());
                    PSource.PSpecified pSpecified = (PSource.PSpecified) this.spec.getPSource();
                    if (pSpecified.getValue().length != 0) {
                        try {
                            j4 = NativeCrypto.asn1_write_tag(asn1_write_sequence, 2);
                            try {
                                j5 = writeAlgorithmIdentifier(j4, PSPECIFIED_OID);
                                NativeCrypto.asn1_write_octetstring(j5, pSpecified.getValue());
                                NativeCrypto.asn1_write_flush(asn1_write_sequence);
                                NativeCrypto.asn1_write_free(j5);
                                NativeCrypto.asn1_write_free(j4);
                            } catch (Throwable th3) {
                                th = th3;
                                NativeCrypto.asn1_write_flush(asn1_write_sequence);
                                NativeCrypto.asn1_write_free(j5);
                                NativeCrypto.asn1_write_free(j4);
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            j4 = 0;
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
            } catch (Throwable th5) {
                th = th5;
                j2 = 0;
                NativeCrypto.asn1_write_free(j2);
                NativeCrypto.asn1_write_free(j3);
                throw th;
            }
        } catch (IOException e5) {
            e2 = e5;
            j3 = 0;
        } catch (Throwable th6) {
            j2 = 0;
            th = th6;
            j3 = 0;
        }
    }

    @Override // java.security.AlgorithmParametersSpi
    public <T extends AlgorithmParameterSpec> T engineGetParameterSpec(Class<T> cls) {
        if (cls != null && cls == OAEPParameterSpec.class) {
            return this.spec;
        }
        throw new InvalidParameterSpecException("Unsupported class: " + cls);
    }

    @Override // java.security.AlgorithmParametersSpi
    public void engineInit(AlgorithmParameterSpec algorithmParameterSpec) {
        if (algorithmParameterSpec instanceof OAEPParameterSpec) {
            this.spec = (OAEPParameterSpec) algorithmParameterSpec;
            return;
        }
        throw new InvalidParameterSpecException("Only OAEPParameterSpec is supported");
    }

    @Override // java.security.AlgorithmParametersSpi
    public String engineToString() {
        return "Conscrypt OAEP AlgorithmParameters";
    }

    @Override // java.security.AlgorithmParametersSpi
    public void engineInit(byte[] bArr) {
        long j2;
        long j3;
        long j4 = 0;
        try {
            j2 = NativeCrypto.asn1_read_init(bArr);
            try {
                long asn1_read_sequence = NativeCrypto.asn1_read_sequence(j2);
                try {
                    PSource.PSpecified pSpecified = PSource.PSpecified.DEFAULT;
                    String readHash = readHash(asn1_read_sequence);
                    String readMgfHash = readMgfHash(asn1_read_sequence);
                    if (NativeCrypto.asn1_read_next_tag_is(asn1_read_sequence, 2)) {
                        try {
                            j3 = NativeCrypto.asn1_read_tagged(asn1_read_sequence);
                            try {
                                long asn1_read_sequence2 = NativeCrypto.asn1_read_sequence(j3);
                                if (NativeCrypto.asn1_read_oid(asn1_read_sequence2).equals(PSPECIFIED_OID)) {
                                    pSpecified = new PSource.PSpecified(NativeCrypto.asn1_read_octetstring(asn1_read_sequence2));
                                    if (NativeCrypto.asn1_read_is_empty(asn1_read_sequence2)) {
                                        NativeCrypto.asn1_read_free(asn1_read_sequence2);
                                        NativeCrypto.asn1_read_free(j3);
                                    } else {
                                        throw new IOException("Error reading ASN.1 encoding");
                                    }
                                } else {
                                    throw new IOException("Error reading ASN.1 encoding");
                                }
                            } catch (Throwable th) {
                                th = th;
                                NativeCrypto.asn1_read_free(0L);
                                NativeCrypto.asn1_read_free(j3);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            j3 = 0;
                        }
                    }
                    if (NativeCrypto.asn1_read_is_empty(asn1_read_sequence) && NativeCrypto.asn1_read_is_empty(j2)) {
                        this.spec = new OAEPParameterSpec(readHash, "MGF1", new MGF1ParameterSpec(readMgfHash), pSpecified);
                        NativeCrypto.asn1_read_free(asn1_read_sequence);
                        NativeCrypto.asn1_read_free(j2);
                        return;
                    }
                    throw new IOException("Error reading ASN.1 encoding");
                } catch (Throwable th3) {
                    th = th3;
                    j4 = asn1_read_sequence;
                    NativeCrypto.asn1_read_free(j4);
                    NativeCrypto.asn1_read_free(j2);
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Throwable th5) {
            th = th5;
            j2 = 0;
        }
    }

    @Override // java.security.AlgorithmParametersSpi
    public byte[] engineGetEncoded(String str) {
        if (str != null && !str.equals("ASN.1")) {
            throw new IOException("Unsupported format: ".concat(str));
        }
        return engineGetEncoded();
    }

    @Override // java.security.AlgorithmParametersSpi
    public void engineInit(byte[] bArr, String str) {
        if (str != null && !str.equals("ASN.1")) {
            throw new IOException("Unsupported format: ".concat(str));
        }
        engineInit(bArr);
    }
}
