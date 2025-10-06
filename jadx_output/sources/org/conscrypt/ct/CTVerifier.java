package org.conscrypt.ct;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.conscrypt.NativeCrypto;
import org.conscrypt.OpenSSLX509Certificate;
import org.conscrypt.ct.SignedCertificateTimestamp;
import org.conscrypt.ct.VerifiedSCT;

/* loaded from: classes.dex */
public class CTVerifier {
    private final CTLogStore store;

    public CTVerifier(CTLogStore cTLogStore) {
        this.store = cTLogStore;
    }

    private List<SignedCertificateTimestamp> getSCTsFromOCSPResponse(byte[] bArr, OpenSSLX509Certificate[] openSSLX509CertificateArr) {
        if (bArr != null && openSSLX509CertificateArr.length >= 2) {
            byte[] bArr2 = NativeCrypto.get_ocsp_single_extension(bArr, CTConstants.OCSP_SCT_LIST_OID, openSSLX509CertificateArr[0].getContext(), openSSLX509CertificateArr[0], openSSLX509CertificateArr[1].getContext(), openSSLX509CertificateArr[1]);
            if (bArr2 == null) {
                return Collections.emptyList();
            }
            try {
                return getSCTsFromSCTList(Serialization.readDEROctetString(Serialization.readDEROctetString(bArr2)), SignedCertificateTimestamp.Origin.OCSP_RESPONSE);
            } catch (SerializationException unused) {
                return Collections.emptyList();
            }
        }
        return Collections.emptyList();
    }

    private static List<SignedCertificateTimestamp> getSCTsFromSCTList(byte[] bArr, SignedCertificateTimestamp.Origin origin) {
        if (bArr == null) {
            return Collections.emptyList();
        }
        try {
            byte[][] readList = Serialization.readList(bArr, 2, 2);
            ArrayList arrayList = new ArrayList();
            for (byte[] bArr2 : readList) {
                try {
                    arrayList.add(SignedCertificateTimestamp.decode(bArr2, origin));
                } catch (SerializationException unused) {
                }
            }
            return arrayList;
        } catch (SerializationException unused2) {
            return Collections.emptyList();
        }
    }

    private List<SignedCertificateTimestamp> getSCTsFromTLSExtension(byte[] bArr) {
        return getSCTsFromSCTList(bArr, SignedCertificateTimestamp.Origin.TLS_EXTENSION);
    }

    private List<SignedCertificateTimestamp> getSCTsFromX509Extension(OpenSSLX509Certificate openSSLX509Certificate) {
        byte[] extensionValue = openSSLX509Certificate.getExtensionValue(CTConstants.X509_SCT_LIST_OID);
        if (extensionValue == null) {
            return Collections.emptyList();
        }
        try {
            return getSCTsFromSCTList(Serialization.readDEROctetString(Serialization.readDEROctetString(extensionValue)), SignedCertificateTimestamp.Origin.EMBEDDED);
        } catch (SerializationException unused) {
            return Collections.emptyList();
        }
    }

    private void markSCTsAsInvalid(List<SignedCertificateTimestamp> list, CTVerificationResult cTVerificationResult) {
        Iterator<SignedCertificateTimestamp> it = list.iterator();
        while (it.hasNext()) {
            cTVerificationResult.add(new VerifiedSCT(it.next(), VerifiedSCT.Status.INVALID_SCT));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void verifyEmbeddedSCTs(java.util.List<org.conscrypt.ct.SignedCertificateTimestamp> r4, org.conscrypt.OpenSSLX509Certificate[] r5, org.conscrypt.ct.CTVerificationResult r6) {
        /*
            r3 = this;
            boolean r0 = r4.isEmpty()
            if (r0 == 0) goto L7
            return
        L7:
            int r0 = r5.length
            r1 = 2
            if (r0 < r1) goto L16
            r0 = 0
            r0 = r5[r0]
            r1 = 1
            r5 = r5[r1]
            org.conscrypt.ct.CertificateEntry r5 = org.conscrypt.ct.CertificateEntry.createForPrecertificate(r0, r5)     // Catch: java.security.cert.CertificateException -> L16
            goto L17
        L16:
            r5 = 0
        L17:
            if (r5 != 0) goto L1d
            r3.markSCTsAsInvalid(r4, r6)
            return
        L1d:
            java.util.Iterator r4 = r4.iterator()
        L21:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L3a
            java.lang.Object r0 = r4.next()
            org.conscrypt.ct.SignedCertificateTimestamp r0 = (org.conscrypt.ct.SignedCertificateTimestamp) r0
            org.conscrypt.ct.VerifiedSCT$Status r1 = r3.verifySingleSCT(r0, r5)
            org.conscrypt.ct.VerifiedSCT r2 = new org.conscrypt.ct.VerifiedSCT
            r2.<init>(r0, r1)
            r6.add(r2)
            goto L21
        L3a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.conscrypt.ct.CTVerifier.verifyEmbeddedSCTs(java.util.List, org.conscrypt.OpenSSLX509Certificate[], org.conscrypt.ct.CTVerificationResult):void");
    }

    private void verifyExternalSCTs(List<SignedCertificateTimestamp> list, OpenSSLX509Certificate openSSLX509Certificate, CTVerificationResult cTVerificationResult) {
        if (list.isEmpty()) {
            return;
        }
        try {
            CertificateEntry createForX509Certificate = CertificateEntry.createForX509Certificate(openSSLX509Certificate);
            for (SignedCertificateTimestamp signedCertificateTimestamp : list) {
                cTVerificationResult.add(new VerifiedSCT(signedCertificateTimestamp, verifySingleSCT(signedCertificateTimestamp, createForX509Certificate)));
            }
        } catch (CertificateException unused) {
            markSCTsAsInvalid(list, cTVerificationResult);
        }
    }

    private VerifiedSCT.Status verifySingleSCT(SignedCertificateTimestamp signedCertificateTimestamp, CertificateEntry certificateEntry) {
        CTLogInfo knownLog = this.store.getKnownLog(signedCertificateTimestamp.getLogID());
        if (knownLog == null) {
            return VerifiedSCT.Status.UNKNOWN_LOG;
        }
        return knownLog.verifySingleSCT(signedCertificateTimestamp, certificateEntry);
    }

    public CTVerificationResult verifySignedCertificateTimestamps(List<X509Certificate> list, byte[] bArr, byte[] bArr2) {
        OpenSSLX509Certificate[] openSSLX509CertificateArr = new OpenSSLX509Certificate[list.size()];
        Iterator<X509Certificate> it = list.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            openSSLX509CertificateArr[i2] = OpenSSLX509Certificate.fromCertificate(it.next());
            i2++;
        }
        return verifySignedCertificateTimestamps(openSSLX509CertificateArr, bArr, bArr2);
    }

    public CTVerificationResult verifySignedCertificateTimestamps(OpenSSLX509Certificate[] openSSLX509CertificateArr, byte[] bArr, byte[] bArr2) {
        if (openSSLX509CertificateArr.length != 0) {
            OpenSSLX509Certificate openSSLX509Certificate = openSSLX509CertificateArr[0];
            CTVerificationResult cTVerificationResult = new CTVerificationResult();
            verifyExternalSCTs(getSCTsFromTLSExtension(bArr), openSSLX509Certificate, cTVerificationResult);
            verifyExternalSCTs(getSCTsFromOCSPResponse(bArr2, openSSLX509CertificateArr), openSSLX509Certificate, cTVerificationResult);
            verifyEmbeddedSCTs(getSCTsFromX509Extension(openSSLX509CertificateArr[0]), openSSLX509CertificateArr, cTVerificationResult);
            return cTVerificationResult;
        }
        throw new IllegalArgumentException("Chain of certificates mustn't be empty.");
    }
}
