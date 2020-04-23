package org.iata.service.security;

import com.wisekey.ocsp.OcspUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.security.cert.X509Certificate;

@Service
public class OcspService {
  private final HttpServletRequest request;
  private final Environment env;

  @Inject
  public OcspService(HttpServletRequest request, Environment env) {
    this.request = request;
    this.env = env;
  }

  public String verifyCertificate() {
    X509Certificate[] clientCertificateChain = (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");
    if (clientCertificateChain != null && clientCertificateChain.length > 0) {
      X509Certificate clientCertificate = clientCertificateChain[0];
      OcspUtils ocspUtils = new OcspUtils(env.getProperty("ocsp.cachedDir"));
      try {
        Principal subjectDN = clientCertificate.getSubjectDN();
        String certStatus = ocspUtils.validate(clientCertificate);
        String statsDesc = ocspUtils.getDescStatus(certStatus);
        if (!certStatus.equals(OcspUtils.CERT_STATUS_GOOD) && !certStatus.equals(OcspUtils.CERT_STATUS_EXPIRED)
            && !certStatus.equals(OcspUtils.CERT_STATUS_REVOKED)) {
          if (certStatus.equals(OcspUtils.CERT_STATUS_BAD_CRL) || certStatus.equals(OcspUtils.CERT_STATUS_NO_CRL)) {
            certStatus = OcspUtils.CERT_STATUS_GOOD;
          } else {
            certStatus = OcspUtils.CERT_STATUS_UNKNOWN;
          }
        }
        return String.format(
            "Server side received (and validated) the following client certificate:\n\r\t{ Certificate-Info: %s; Certificate-Status: %s; Status-Description: %s}",
            subjectDN, certStatus, statsDesc);
      } catch (Exception e) {
        return String.format("Internal Server Error: %s", e.getMessage());
      }
    } else {
      return "Application run with SSL disabled.";
    }
  }
}
