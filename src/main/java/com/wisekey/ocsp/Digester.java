package com.wisekey.ocsp;

import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.io.DigestOutputStream;
import org.bouncycastle.operator.DigestCalculator;

import java.io.OutputStream;

class Digester implements DigestCalculator {

  static DigestCalculator sha1() {
    Digest digest = new SHA1Digest();
    AlgorithmIdentifier algId = new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1);

    return new Digester(digest, algId);
  }

  private final DigestOutputStream dos;

  private final AlgorithmIdentifier algId;

  private Digester(Digest digest, AlgorithmIdentifier algId) {
    this.dos = new DigestOutputStream(digest);
    this.algId = algId;
  }

  @Override
  public AlgorithmIdentifier getAlgorithmIdentifier() {
    return algId;
  }

  @Override
  public OutputStream getOutputStream() {
    return dos;
  }

  @Override
  public byte[] getDigest() {
    return dos.getDigest();
  }

}
