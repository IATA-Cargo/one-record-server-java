package com.wisekey.ocsp;

<<<<<<< HEAD
import java.io.OutputStream;
=======
>>>>>>> 1070697bfd4057f2558b23422a7d8150d2e0c7d9
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.io.DigestOutputStream;
import org.bouncycastle.operator.DigestCalculator;

<<<<<<< HEAD
/**
 * Digester class inherited Digest Calculator
 *
 * @author
 */
class Digester implements DigestCalculator {

=======
import java.io.OutputStream;

class Digester implements DigestCalculator {

  static DigestCalculator sha1() {
    Digest digest = new SHA1Digest();
    AlgorithmIdentifier algId = new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1);

    return new Digester(digest, algId);
  }

>>>>>>> 1070697bfd4057f2558b23422a7d8150d2e0c7d9
  private final DigestOutputStream dos;
  private final AlgorithmIdentifier algId;

  /**
   * Constructor of Digester class
   *
   * @param digest
   * @param algId
   */
  private Digester(Digest digest, AlgorithmIdentifier algId) {
    this.dos = new DigestOutputStream(digest);
    this.algId = algId;
  }

  /**
   * Make Digest Calculator for sha1
   *
   * @return Digester object
   */
  public static DigestCalculator sha1() {
    Digest digest = new SHA1Digest();
    AlgorithmIdentifier algId = new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1);

    return new Digester(digest, algId);
  }

  /**
   *
   * @return
   */
  @Override
  public AlgorithmIdentifier getAlgorithmIdentifier() {
    return algId;
  }

  /**
   *
   * @return
   */
  @Override
  public OutputStream getOutputStream() {
    return dos;
  }

  /**
   *
   * @return
   */
  @Override
  public byte[] getDigest() {
    return dos.getDigest();
  }

}
