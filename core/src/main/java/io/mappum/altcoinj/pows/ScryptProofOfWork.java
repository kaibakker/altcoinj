package io.mappum.altcoinj.pows;

import io.mappum.altcoinj.core.Sha256Hash;
import io.mappum.altcoinj.core.ProofOfWork;
import io.mappum.altcoinj.core.Utils;
import com.lambdaworks.crypto.SCrypt;

import java.security.GeneralSecurityException;

public class ScryptProofOfWork extends ProofOfWork {
    protected int N, p, r, dkLen;

    public ScryptProofOfWork(int N, int p, int r, int dkLen) {
        super();
        this.N = N;
        this.p = p;
        this.r = r;
        this.dkLen = dkLen;
    }

    protected Sha256Hash hash(byte[] header) {
        try {
            byte[] h = Utils.reverseBytes(SCrypt.scrypt(header, header, N, r, p, dkLen));
            return new Sha256Hash(h);
        } catch(GeneralSecurityException ex) {
            return null;
        }
    }
}
