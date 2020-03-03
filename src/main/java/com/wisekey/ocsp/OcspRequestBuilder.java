package com.wisekey.ocsp;

import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.ocsp.OCSPObjectIdentifiers;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.ocsp.CertificateID;
import org.bouncycastle.cert.ocsp.OCSPException;
import org.bouncycastle.cert.ocsp.OCSPReq;
import org.bouncycastle.cert.ocsp.OCSPReqBuilder;
import org.bouncycastle.operator.DigestCalculator;

<<<<<<< HEAD
/**
 * Class to help build Ocsp request
 *
 * @author
 */
public class OcspRequestBuilder {
=======
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

class OcspRequestBuilder {
  private static final SecureRandom GENERATOR = new SecureRandom();

  private SecureRandom generator = GENERATOR;
>>>>>>> 1070697bfd4057f2558b23422a7d8150d2e0c7d9

  private static final SecureRandom GENERATOR = new SecureRandom();
  private final SecureRandom generator = GENERATOR;
  private final DigestCalculator calculator = Digester.sha1();

  private X509Certificate certificate;
  private X509Certificate issuer;

<<<<<<< HEAD
  /**
   * Set certificate to builder
   *
   * @param certificate main certificate
   * @return OcspRequestBuilder object
   */
  public OcspRequestBuilder certificate(X509Certificate certificate) {
=======
  OcspRequestBuilder certificate(X509Certificate certificate) {
>>>>>>> 1070697bfd4057f2558b23422a7d8150d2e0c7d9
    this.certificate = certificate;
    return this;
  }

<<<<<<< HEAD
  /**
   * Set the certificate of issuer to builder
   *
   * @param issuer the certificate of issuer
   * @return OcspRequestBuilder object
   */
  public OcspRequestBuilder issuer(X509Certificate issuer) {
=======
  OcspRequestBuilder issuer(X509Certificate issuer) {
>>>>>>> 1070697bfd4057f2558b23422a7d8150d2e0c7d9
    this.issuer = issuer;
    return this;
  }

  /**
   * ATTENTION: The returned {@link OCSPReq} is not re-usable/cacheable! It
   * contains a one-time nonce and CA's will (should) reject subsequent requests
   * that have the same nonce value.
   *
   * @return the builded OCSPReq object
   * @throws org.bouncycastle.cert.ocsp.OCSPException
   * @throws java.io.IOException
   * @throws java.security.cert.CertificateEncodingException
   */
<<<<<<< HEAD
  public OCSPReq build() throws OCSPException, IOException, CertificateEncodingException {
=======
  OCSPReq build() throws OCSPException, IOException, CertificateEncodingException {
    SecureRandom generator = this.generator;
    DigestCalculator calculator = this.calculator;
    X509Certificate certificate = this.certificate;
    X509Certificate issuer = this.issuer;
>>>>>>> 1070697bfd4057f2558b23422a7d8150d2e0c7d9

    BigInteger serial;
    serial = this.certificate.getSerialNumber();

    CertificateID certId = new CertificateID(this.calculator, new X509CertificateHolder(this.issuer.getEncoded()),
        serial);

    OCSPReqBuilder builder = new OCSPReqBuilder();
    builder.addRequest(certId);

    byte[] nonce = new byte[8];
    this.generator.nextBytes(nonce);

    Extension[] extensions = new Extension[] {new Extension(OCSPObjectIdentifiers.id_pkix_ocsp_nonce, false, new DEROctetString(nonce)) };

    builder.setRequestExtensions(new Extensions(extensions));

    return builder.build();
  }

}
