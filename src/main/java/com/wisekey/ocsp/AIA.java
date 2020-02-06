package com.wisekey.ocsp;

class AIA {
  public String Issuer;
  public String Ocsp;

  public AIA(String issuer, String ocsp) {
    Issuer = issuer;
    Ocsp = ocsp;
  }

  public AIA() {
    Issuer = "";
    Ocsp = "";
  }
}
