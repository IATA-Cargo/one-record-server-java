package com.wisekey.ocsp;

// import java.io.StringWriter;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HttpsURLConnection;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.BERTags;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.cert.ocsp.OCSPResp;
import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.X509Certificate;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.cert.ocsp.OCSPException;
import org.bouncycastle.cert.ocsp.OCSPReq;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import java.security.Principal;
import java.security.cert.CRLException;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Date;
import org.bouncycastle.asn1.ocsp.OCSPResponseStatus;
import org.bouncycastle.asn1.x509.AccessDescription;
import org.bouncycastle.asn1.x509.AuthorityInformationAccess;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.cert.ocsp.SingleResp;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;
import org.bouncycastle.cert.ocsp.UnknownStatus;
import org.bouncycastle.cert.ocsp.RevokedStatus;
import org.bouncycastle.cert.ocsp.CertificateStatus;
// import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
// import org.bouncycastle.x509.extension.X509ExtensionUtil;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;

public class OcspUtils {
  public static final String CERT_STATUS_NOTHING = "Nothing"; // Error or other case

  public static final String CERT_STATUS_GOOD = "Good";
  public static final String CERT_STATUS_UNKNOWN = "Unknown";
  public static final String CERT_STATUS_EXPIRED = "Expired";
  // The certificate is revoked
  public static final String CERT_STATUS_REVOKED = "Revoked";
  // The live CRL expires.
  public static final String CERT_STATUS_BAD_CRL = "BadCrl";
  // No CDP URL found or there are more than one CDP CRL found.
  public static final String CERT_STATUS_NO_CRL = "NoCrl";
  // The CRL does not include revocation info.
  public static final String CERT_STATUS_BAD_EXTENSION = "BadExtension";
  // The certificate is revoked without revocation reason info.
  public static final String CERT_STATUS_BAD_REVOCATION_REASON = "BadRevocationReason";
  // Unable to load the issuer certificate.
  public static final String CERT_STATUS_BAD_ISSUER = "BadIssuer";
  // Issuer not match - There might be a problem with the OCSP Service
  public static final String CERT_STATUS_ISSUER_NOT_MATCH = "IssuerNotMatch";
  // Serial value not match - There might be a problem with the OCSP Service.
  public static final String CERT_STATUS_BAD_SERIAL = "BadSerial";

  private static final ASN1ObjectIdentifier CRL_DISTRIBUTION_POINTS = new ASN1ObjectIdentifier("2.5.29.31").intern();
  private static final ASN1ObjectIdentifier ID_AD_CAISSUERS = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.48.2").intern();
  private static final ASN1ObjectIdentifier OCSP_RESPONDER_OID = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.48.1")
      .intern();
  private static final String OCSP_REQUEST_TYPE = "application/ocsp-request";
  private static final String OCSP_RESPONSE_TYPE = "application/ocsp-response";

  private String cachedDir;

  public OcspUtils(String dir) {
    this.cachedDir = dir;
  }

  private static Date getCurrentUtcTime() {
    OffsetDateTime utc = OffsetDateTime.now(ZoneOffset.UTC);
    return Date.from(utc.toInstant());
    // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd
    // HH:mm:ss");
    // simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    // SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy-MMM-dd
    // HH:mm:ss");
    // return localDateFormat.parse( simpleDateFormat.format(new Date()) );
  }

  public KeyStore LoadCertPKCS12FromFile(String filePath, String pinStr)
      throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
    KeyStore keystore = null;
    File ksFile = new File(filePath);
    keystore = KeyStore.getInstance("PKCS12");
    char[] pin = pinStr.toCharArray();
    keystore.load(new FileInputStream(ksFile), pin);
    return keystore;
  }

  public X509Certificate loadCertificateFromFile(String filePath) throws FileNotFoundException, CertificateException {
    X509Certificate certificate = null;
    CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
    File file = new File(filePath);
    certificate = (X509Certificate) certificateFactory.generateCertificate(new FileInputStream(file));
    return certificate;
  }

  public String validate(X509Certificate certificate) throws IOException, IllegalStateException, URISyntaxException,
      OCSPException, CertificateException, NoSuchAlgorithmException, Exception {
    // StringWriter sw = new StringWriter();
    // JcaPEMWriter pemWriter = new JcaPEMWriter(sw);
    // pemWriter.writeObject(certificate);
    // pemWriter.flush();
    // System.out.println(sw.toString());
    if (certificate.getNotAfter().compareTo(getCurrentUtcTime()) < 0) {
      return CERT_STATUS_EXPIRED;
    }
    AIA aia = parseAIACertificate(certificate);
    if (aia == null || isNullOrEmpty(aia.Ocsp) || isNullOrEmpty(aia.Issuer)) {
      String url = parseCDPUrls(certificate);
      if (url == null || "".equals(url)) {
        return CERT_STATUS_NO_CRL;
      }
      X509CRL crl = getCrl(url);
      if (crl.getNextUpdate().compareTo(getCurrentUtcTime()) < 0) {
        return CERT_STATUS_BAD_CRL;
      }
      BigInteger serialNumber = certificate.getSerialNumber();
      X509CRLEntry entry = crl.getRevokedCertificate(serialNumber);
      if (entry == null) {
        return CERT_STATUS_GOOD;
      }
      return CERT_STATUS_REVOKED;
    } else {
      return validate(certificate, aia);
    }
  }

  public String getDescStatus(String certStatus) {
    String descStatus;
    switch (certStatus) {
    case CERT_STATUS_GOOD:
      descStatus = "Good";
      break;
    case CERT_STATUS_UNKNOWN:
      descStatus = "Certificate is not Good.";
      break;
    case CERT_STATUS_REVOKED:
      descStatus = "Your signing certificate was revoked.";
      break;
    case CERT_STATUS_BAD_REVOCATION_REASON:
      descStatus = "Your signing certificate was revoked.";
      break;
    case CERT_STATUS_BAD_CRL:
      descStatus = "The live CRL expires.";
      break;
    case CERT_STATUS_NO_CRL:
      descStatus = "No CDP URL found or there are more than one CDP CRL found.";
      break;
    case CERT_STATUS_BAD_EXTENSION:
      descStatus = "The CRL does not include revocation info.";
      break;
    case CERT_STATUS_BAD_ISSUER:
      descStatus = "Unable to load the issuer certificate.";
      break;
    case CERT_STATUS_ISSUER_NOT_MATCH:
      descStatus = "Issuer not match - There might be a problem with the OCSP Service.";
      break;
    case CERT_STATUS_BAD_SERIAL:
      descStatus = "Serial value not match - There might be a problem with the OCSP Service.";
      break;
    default:
      descStatus = "";
      break;
    }
    return descStatus;
  }

  private X509Certificate getIssuerCert(X509Certificate cert) throws IOException, CertificateException {
    X509Certificate issuer = null;
    // get Authority Information Access extension (will be null if extension is not
    // present)
    byte[] extVal = cert.getExtensionValue(Extension.authorityInfoAccess.getId());
    AuthorityInformationAccess aia = AuthorityInformationAccess
        .getInstance(JcaX509ExtensionUtils.parseExtensionValue(extVal)
        /* X509ExtensionUtil.fromExtensionValue(extVal) */);
    CertificateFactory certificateFactory;
    certificateFactory = CertificateFactory.getInstance("X.509");
    // check if there is a URL to issuer's certificate
    AccessDescription[] descriptions = aia.getAccessDescriptions();
    for (AccessDescription ad : descriptions) {
      // check if it's a URL to issuer's certificate
      if (ad.getAccessMethod().equals(X509ObjectIdentifiers.id_ad_caIssuers)) {
        GeneralName location = ad.getAccessLocation();
        if (location.getTagNo() == GeneralName.uniformResourceIdentifier) {
          String issuerUrl = location.getName().toString();
          // http URL to issuer (test in your browser to see if it's a valid certificate)
          // you can use java.net.URL.openStream() to create a InputStream and create
          // the certificate with your CertificateFactory
          URL url = new URL(issuerUrl);
          issuer = (X509Certificate) certificateFactory.generateCertificate(url.openStream());
        }
      }
    }
    return issuer;
  }

  private String validate(X509Certificate certificate, AIA aia) throws IOException, IllegalStateException,
      URISyntaxException, OCSPException, CertificateException, NoSuchAlgorithmException {
    String hash = getHash(aia.Issuer);
    String filePath = issuerCachedFolder() + hash;
    // Check if aki is cached
    if (!isIssuerCached(aia.Issuer)) {
      download(aia.Issuer, filePath);
      if (!isIssuerCached(aia.Issuer)) {
        return CERT_STATUS_BAD_ISSUER;
      }
    }
    URI uri = new URI(aia.Ocsp);
    if (uri == null) {
      throw new IllegalStateException("The CA/certificate doesn't have an OCSP responder");
    }
    // Step 3: Construct the OCSP request
    X509Certificate issuer = getIssuerCert(certificate);
    OCSPReq request = new OcspRequestBuilder().certificate(certificate).issuer(issuer).build();
    // Step 4: Do the request to the CA's OCSP responder
    OCSPResp response = request(uri, request, 5L, TimeUnit.SECONDS);
    if (response.getStatus() != OCSPResponseStatus.SUCCESSFUL) {
      throw new IllegalStateException("response-status=" + response.getStatus());
    }
    // Step 5: Is my certificate any good or has the CA revoked it?
    BasicOCSPResp basicResponse = (BasicOCSPResp) response.getResponseObject();
    SingleResp first = basicResponse.getResponses()[0];

    CertificateStatus status = first.getCertStatus();
    BigInteger certSerial = certificate.getSerialNumber();
    BigInteger ocspSerial = first.getCertID().getSerialNumber();
    if (!certSerial.equals(ocspSerial)) {
      return CERT_STATUS_BAD_SERIAL;
    }
    if (status == null || status == CertificateStatus.GOOD) {
      return CERT_STATUS_GOOD;
    }
    if (status instanceof RevokedStatus) {
      final RevokedStatus revokedStatus = (RevokedStatus) status;
      Date revocationDate = revokedStatus.getRevocationTime();
      int reasonId = 0; // unspecified
      if (revokedStatus.hasRevocationReason()) {
        reasonId = revokedStatus.getRevocationReason();
      }
      return CERT_STATUS_REVOKED;
    }
    if (status instanceof UnknownStatus) {
      return CERT_STATUS_UNKNOWN;
    }
    return CERT_STATUS_NOTHING;
  }

  /**
   * The OID for OCSP responder URLs.
   *
   * http://www.alvestrand.no/objectid/1.3.6.1.5.5.7.48.1.html
   */
  private OCSPResp request(URI uri, OCSPReq request, long timeout, TimeUnit unit) throws IOException {
    byte[] encoded = request.getEncoded();

    URL url = uri.toURL();
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    try {
      connection.setConnectTimeout((int) unit.toMillis(timeout));
      connection.setReadTimeout((int) unit.toMillis(timeout));
      connection.setDoOutput(true);
      connection.setDoInput(true);
      connection.setRequestMethod("POST");
      connection.setRequestProperty("host", uri.getHost());
      connection.setRequestProperty("content-type", OCSP_REQUEST_TYPE);
      connection.setRequestProperty("accept", OCSP_RESPONSE_TYPE);
      connection.setRequestProperty("content-length", String.valueOf(encoded.length));

      try (OutputStream out = connection.getOutputStream()) {
        out.write(encoded);
        out.flush();
        InputStream in = connection.getInputStream();
        int code = connection.getResponseCode();
        if (code != HttpsURLConnection.HTTP_OK) {
          throw new IOException("Unexpected status-code=" + code);
        }
        String contentType = connection.getContentType();
        if (!contentType.equalsIgnoreCase(OCSP_RESPONSE_TYPE)) {
          throw new IOException("Unexpected content-type=" + contentType);
        }
        int contentLength = connection.getContentLength();
        if (contentLength == -1) {
          // Probably a terrible idea!
          contentLength = Integer.MAX_VALUE;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
          byte[] buffer = new byte[8192];
          int length = -1;
          while ((length = in.read(buffer)) != -1) {
            baos.write(buffer, 0, length);

            if (baos.size() >= contentLength) {
              break;
            }
          }
        } catch (IOException ioe) {
          throw ioe;
        } finally {
          baos.close();
        }
        return new OCSPResp(baos.toByteArray());
      }
    } catch (IOException ioe) {
      throw ioe;
    } finally {
      connection.disconnect();
    }
  }

  private AIA parseAIACertificate(X509Certificate certificate) throws Exception {
    AIA aia = new AIA();
    Principal principal = certificate.getSubjectDN();
    principal = certificate.getIssuerDN();
    if (principal != null) {
      aia.Issuer = principal.getName();
    }
    aia.Ocsp = ocspUri(certificate);
    aia.Issuer = issuerUrl(certificate);
    return aia;
  }

  private X509CRL getCrl(String uri) throws Exception {
    String hash = getHash(uri);
    String fileName = crlCachedFolder() + hash;
    if (isExistPath(fileName)) {
      X509CRL crl = loadFromFile(fileName);
      if (crl.getNextUpdate().compareTo(getCurrentUtcTime()) > 0)
        return crl;
    }
    download(uri, fileName);
    return loadFromFile(fileName);
  }

  private X509CRL loadFromFile(String file)
      throws FileNotFoundException, FileNotFoundException, CRLException, CRLException, CertificateException {
    CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
    FileInputStream fis = new FileInputStream(file);
    X509CRL crl = (X509CRL) certFactory.generateCRL(fis);
    return crl;
  }

  private void getCrlUrls(X509Certificate certificate) throws IOException, CertificateException {
    // CertificateFactory certificateFactory =
    // CertificateFactory.getInstance("X.509");
    byte[] crlDistributionPointDerEncodedArray = certificate.getExtensionValue(Extension.cRLDistributionPoints.getId());

    DEROctetString dosCrlDP;
    try (ASN1InputStream oAsnInStream = new ASN1InputStream(
        new ByteArrayInputStream(crlDistributionPointDerEncodedArray))) {
      ASN1Primitive derObjCrlDP = oAsnInStream.readObject();
      dosCrlDP = (DEROctetString) derObjCrlDP;
    }

    byte[] crldpExtOctets = dosCrlDP.getOctets();
    CRLDistPoint distPoint;
    try (ASN1InputStream oAsnInStream2 = new ASN1InputStream(new ByteArrayInputStream(crldpExtOctets))) {
      ASN1Primitive derObj2 = oAsnInStream2.readObject();
      distPoint = CRLDistPoint.getInstance(derObj2);
    }

    List<String> crlUrls;
    crlUrls = new ArrayList<>();
    for (DistributionPoint dp : distPoint.getDistributionPoints()) {
      DistributionPointName dpn = dp.getDistributionPoint();
      // Look for URIs in fullName
      if (dpn != null) {

        if (dpn.getType() == DistributionPointName.FULL_NAME) {
          GeneralName[] genNames = GeneralNames.getInstance(dpn.getName()).getNames();
          // Look for an URI
          for (int j = 0; j < genNames.length; j++) {
            if (genNames[j].getTagNo() == GeneralName.uniformResourceIdentifier) {
              String url = DERIA5String.getInstance(genNames[j].getName()).getString();
              crlUrls.add(url);
            }
          }
        }
      }
    }
  }

  private String parseCDPUrls(X509Certificate certificate) throws IOException {
    return getExtension(certificate, CRL_DISTRIBUTION_POINTS);
  }

  private String ocspUri(X509Certificate certificate) throws IOException {
    return getExtension(certificate, OCSP_RESPONDER_OID);
  }

  private String issuerUrl(X509Certificate certificate) throws IOException {
    return getExtension(certificate, ID_AD_CAISSUERS);
  }

  private static void download(String url, String filePath) throws IOException {
    URL website = new URL(url);
    ReadableByteChannel rbc = Channels.newChannel(website.openStream());
    FileOutputStream fos = new FileOutputStream(filePath);
    try {
      fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    } catch (IOException e) {
      throw e;
    } finally {
      fos.close();
    }
  }

  // <editor-fold defaultstate="collapsed" desc="Utils function of class">

  private static boolean isNullOrEmpty(String str) {
    return !(str != null && !str.isEmpty());
  }

  private static String getExtension(X509Certificate certificate, ASN1ObjectIdentifier oIdentifier) throws IOException {
    byte[] value = certificate.getExtensionValue(Extension.authorityInfoAccess.getId());
    if (value == null) {
      return null;
    }
    ASN1Primitive authorityInfoAccess = JcaX509ExtensionUtils.parseExtensionValue(value);
    /* X509ExtensionUtil.fromExtensionValue(value); */
    if (!(authorityInfoAccess instanceof DLSequence)) {
      return null;
    }
    DLSequence aiaSequence = (DLSequence) authorityInfoAccess;
    DERTaggedObject taggedObject = findObject(aiaSequence, oIdentifier, DERTaggedObject.class);
    if (taggedObject == null) {
      return null;
    }
    if (taggedObject.getTagNo() != BERTags.OBJECT_IDENTIFIER) {
      return null;
    }
    byte[] encoded = taggedObject.getEncoded();
    int length = (int) encoded[1] & 0xFF;
    String uri = new String(encoded, 2, length, "UTF-8");
    return uri;
  }

  private static <T> T findObject(DLSequence sequence, ASN1ObjectIdentifier oid, Class<T> type) {
    for (ASN1Encodable element : sequence) {
      if (!(element instanceof DLSequence)) {
        continue;
      }
      DLSequence subSequence = (DLSequence) element;
      if (subSequence.size() != 2) {
        continue;
      }
      ASN1Encodable key = subSequence.getObjectAt(0);
      ASN1Encodable value = subSequence.getObjectAt(1);
      if (key.equals(oid) && type.isInstance(value)) {
        return type.cast(value);
      }
    }
    return null;
  }

  private static boolean createDir(String path) {
    try {
      File directory = new File(path);
      if (!directory.exists()) {
        directory.mkdir();
      }
      return directory.exists();
    } catch (Exception e) {
      File directory = new File(path);
      return directory.exists();
    }
  }

  private static boolean isExistPath(String path) {
    try {
      File directory = new File(path);
      return directory.exists();
    } catch (Exception e) {
      throw e;
    }
  }

  private static String getHash(String data) throws NoSuchAlgorithmException {
    MessageDigest md;
    md = MessageDigest.getInstance("SHA-1");
    byte[] digest = md.digest(data.getBytes(Charset.forName("ASCII")));
    String tmpHash = new String(Base64.getEncoder().encode(digest));
    tmpHash = tmpHash + "";
    tmpHash = tmpHash.replace("/", "_");
    tmpHash = tmpHash.replace("+", "-");
    tmpHash = tmpHash.replace("=", "");
    return tmpHash;
  }

  private String issuerCachedFolder() {
    String path = this.cachedDir + "Issuers" + File.separator;
    createDir(path);
    return path;
  }

  private boolean isIssuerCached(String url) throws NoSuchAlgorithmException {
    String hash = getHash(url);
    return isExistPath(issuerCachedFolder() + hash);
  }

  private String crlCachedFolder() {
    String path = this.cachedDir + "Crl" + File.separator;
    createDir(path);
    return path;
  }

}
