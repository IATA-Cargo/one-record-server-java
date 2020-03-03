package com.wisekey.ocsp;

/**
 * Information of ocsp and issuer certificate
 * @author
 */
class AIA {

    private String Issuer;

    /**
     * Set the certificate url of issuer
     * @param val Issuer certificate url
     */
    public void setIssuer(String val) {
        this.Issuer = val;
    }

    /**
     * Get the certificate url of issuer
     * @return Issuer certificate url
     */
    public String getIssuer() {
        return this.Issuer;
    }

    private String Ocsp;

    /**
     * Set the ocsp url of certificate
     * @param val the ocsp url
     */
    public void setOcsp(String val) {
        this.Ocsp = val;
    }

    /**
     * Get the ocsp url of certificate
     * @return Ocsp url
     */
    public String getOcsp() {
        return this.Ocsp;
    }

    /**
     * Initialize object AIA with issuer and ocsp
     * @param issuer The url of issuer certificate
     * @param ocsp The url of ocsp certificate
     */
    public AIA(String issuer, String ocsp) {
        Issuer = issuer;
        Ocsp = ocsp;
    }

    /**
     * Initialize object AIA
     */
    public AIA() {
        Issuer = "";
        Ocsp = "";
    }
}
