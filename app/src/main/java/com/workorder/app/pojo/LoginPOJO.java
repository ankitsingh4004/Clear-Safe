package com.workorder.app.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LoginPOJO implements Serializable {
    @SerializedName("Profile")
    @Expose
    private Profile profile;
    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("expires_in")
    @Expose
    private Double expiresIn;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Double getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Double expiresIn) {
        this.expiresIn = expiresIn;
    }


    public class Profile {

        @SerializedName("RoleNames")
        @Expose
        private Object roleNames;
        @SerializedName("CompanyName")
        @Expose
        private Object companyName;
        @SerializedName("CountryID")
        @Expose
        private Integer countryID;
        @SerializedName("StateID")
        @Expose
        private Integer stateID;
        @SerializedName("CompanyTypeID")
        @Expose
        private Integer companyTypeID;
        @SerializedName("CurrentBalance")
        @Expose
        private Double currentBalance;
        @SerializedName("CompanyId")
        @Expose
        private Integer companyId;
        @SerializedName("PERSONAL_COUNTRY_ID")
        @Expose
        private Object pERSONALCOUNTRYID;
        @SerializedName("PERSONAL_STATE_ID")
        @Expose
        private Object pERSONALSTATEID;
        @SerializedName("FIRST_NAME")
        @Expose
        private Object fIRSTNAME;
        @SerializedName("MIDDLE_NAME")
        @Expose
        private Object mIDDLENAME;
        @SerializedName("LAST_NAME")
        @Expose
        private Object lASTNAME;
        @SerializedName("COMPANY_NAME")
        @Expose
        private Object cOMPANYNAME;
        @SerializedName("PERSONALADDRESS1")
        @Expose
        private Object pERSONALADDRESS1;
        @SerializedName("PERSONALADDRESS2")
        @Expose
        private Object pERSONALADDRESS2;
        @SerializedName("PERSONAL_CITY")
        @Expose
        private Object pERSONALCITY;
        @SerializedName("PERSONAL_MOBILE")
        @Expose
        private Object pERSONALMOBILE;
        @SerializedName("PERSONAL_PHONE")
        @Expose
        private Object pERSONALPHONE;
        @SerializedName("BUSINESS_ADDRESS1")
        @Expose
        private Object bUSINESSADDRESS1;
        @SerializedName("BUSINESS_ADDRESS2")
        @Expose
        private Object bUSINESSADDRESS2;
        @SerializedName("BUSINESS_CITY")
        @Expose
        private Object bUSINESSCITY;
        @SerializedName("BUSINESS_COUNTRY_ID")
        @Expose
        private Object bUSINESSCOUNTRYID;
        @SerializedName("BUSINESS_EMAIL")
        @Expose
        private Object bUSINESSEMAIL;
        @SerializedName("BUSINESS_MOBILE")
        @Expose
        private Object bUSINESSMOBILE;
        @SerializedName("BUSINESS_PHONE")
        @Expose
        private Object bUSINESSPHONE;
        @SerializedName("BUSINESS_STATE_ID")
        @Expose
        private Object bUSINESSSTATEID;
        @SerializedName("BUSINESS_WEB_ADD")
        @Expose
        private Object bUSINESSWEBADD;
        @SerializedName("APPLICENCEID")
        @Expose
        private Integer aPPLICENCEID;
        @SerializedName("PERSON_STATUS")
        @Expose
        private Integer pERSONSTATUS;
        @SerializedName("CURRENT_BALANCE")
        @Expose
        private Double cURRENTBALANCE;
        @SerializedName("COMPANYTYPE")
        @Expose
        private Integer cOMPANYTYPE;
        @SerializedName("DEFAULT_CURRENCY")
        @Expose
        private Object dEFAULTCURRENCY;
        @SerializedName("PARENT_PERSON_ID")
        @Expose
        private Integer pARENTPERSONID;
        @SerializedName("PERSONAL_EMAIL")
        @Expose
        private Object pERSONALEMAIL;
        @SerializedName("PERSONAL_FAX")
        @Expose
        private Object pERSONALFAX;
        @SerializedName("PERSONAL_WEB_ADD")
        @Expose
        private Object pERSONALWEBADD;
        @SerializedName("BUSINESS_FAX")
        @Expose
        private Object bUSINESSFAX;
        @SerializedName("PERSONAL_POSTCODE")
        @Expose
        private Object pERSONALPOSTCODE;
        @SerializedName("BUSINESS_POSTCODE")
        @Expose
        private Object bUSINESSPOSTCODE;
        @SerializedName("isSameAddress")
        @Expose
        private Boolean isSameAddress;
        @SerializedName("Id")
        @Expose
        private String id;
        @SerializedName("UserName")
        @Expose
        private String userName;
        @SerializedName("NormalizedUserName")
        @Expose
        private Object normalizedUserName;
        @SerializedName("Email")
        @Expose
        private String email;
        @SerializedName("NormalizedEmail")
        @Expose
        private Object normalizedEmail;
        @SerializedName("EmailConfirmed")
        @Expose
        private Boolean emailConfirmed;
        @SerializedName("SecurityStamp")
        @Expose
        private String securityStamp;
        @SerializedName("ConcurrencyStamp")
        @Expose
        private String concurrencyStamp;
        @SerializedName("PhoneNumber")
        @Expose
        private Object phoneNumber;
        @SerializedName("PhoneNumberConfirmed")
        @Expose
        private Boolean phoneNumberConfirmed;
        @SerializedName("TwoFactorEnabled")
        @Expose
        private Boolean twoFactorEnabled;
        @SerializedName("LockoutEnd")
        @Expose
        private Object lockoutEnd;
        @SerializedName("LockoutEnabled")
        @Expose
        private Boolean lockoutEnabled;
        @SerializedName("AccessFailedCount")
        @Expose
        private Integer accessFailedCount;

        public Object getRoleNames() {
            return roleNames;
        }

        public void setRoleNames(Object roleNames) {
            this.roleNames = roleNames;
        }

        public Object getCompanyName() {
            return companyName;
        }

        public void setCompanyName(Object companyName) {
            this.companyName = companyName;
        }

        public Integer getCountryID() {
            return countryID;
        }

        public void setCountryID(Integer countryID) {
            this.countryID = countryID;
        }

        public Integer getStateID() {
            return stateID;
        }

        public void setStateID(Integer stateID) {
            this.stateID = stateID;
        }

        public Integer getCompanyTypeID() {
            return companyTypeID;
        }

        public void setCompanyTypeID(Integer companyTypeID) {
            this.companyTypeID = companyTypeID;
        }

        public Double getCurrentBalance() {
            return currentBalance;
        }

        public void setCurrentBalance(Double currentBalance) {
            this.currentBalance = currentBalance;
        }

        public Integer getCompanyId() {
            return companyId;
        }

        public void setCompanyId(Integer companyId) {
            this.companyId = companyId;
        }

        public Object getPERSONALCOUNTRYID() {
            return pERSONALCOUNTRYID;
        }

        public void setPERSONALCOUNTRYID(Object pERSONALCOUNTRYID) {
            this.pERSONALCOUNTRYID = pERSONALCOUNTRYID;
        }

        public Object getPERSONALSTATEID() {
            return pERSONALSTATEID;
        }

        public void setPERSONALSTATEID(Object pERSONALSTATEID) {
            this.pERSONALSTATEID = pERSONALSTATEID;
        }

        public Object getFIRSTNAME() {
            return fIRSTNAME;
        }

        public void setFIRSTNAME(Object fIRSTNAME) {
            this.fIRSTNAME = fIRSTNAME;
        }

        public Object getMIDDLENAME() {
            return mIDDLENAME;
        }

        public void setMIDDLENAME(Object mIDDLENAME) {
            this.mIDDLENAME = mIDDLENAME;
        }

        public Object getLASTNAME() {
            return lASTNAME;
        }

        public void setLASTNAME(Object lASTNAME) {
            this.lASTNAME = lASTNAME;
        }

        public Object getCOMPANYNAME() {
            return cOMPANYNAME;
        }

        public void setCOMPANYNAME(Object cOMPANYNAME) {
            this.cOMPANYNAME = cOMPANYNAME;
        }

        public Object getPERSONALADDRESS1() {
            return pERSONALADDRESS1;
        }

        public void setPERSONALADDRESS1(Object pERSONALADDRESS1) {
            this.pERSONALADDRESS1 = pERSONALADDRESS1;
        }

        public Object getPERSONALADDRESS2() {
            return pERSONALADDRESS2;
        }

        public void setPERSONALADDRESS2(Object pERSONALADDRESS2) {
            this.pERSONALADDRESS2 = pERSONALADDRESS2;
        }

        public Object getPERSONALCITY() {
            return pERSONALCITY;
        }

        public void setPERSONALCITY(Object pERSONALCITY) {
            this.pERSONALCITY = pERSONALCITY;
        }

        public Object getPERSONALMOBILE() {
            return pERSONALMOBILE;
        }

        public void setPERSONALMOBILE(Object pERSONALMOBILE) {
            this.pERSONALMOBILE = pERSONALMOBILE;
        }

        public Object getPERSONALPHONE() {
            return pERSONALPHONE;
        }

        public void setPERSONALPHONE(Object pERSONALPHONE) {
            this.pERSONALPHONE = pERSONALPHONE;
        }

        public Object getBUSINESSADDRESS1() {
            return bUSINESSADDRESS1;
        }

        public void setBUSINESSADDRESS1(Object bUSINESSADDRESS1) {
            this.bUSINESSADDRESS1 = bUSINESSADDRESS1;
        }

        public Object getBUSINESSADDRESS2() {
            return bUSINESSADDRESS2;
        }

        public void setBUSINESSADDRESS2(Object bUSINESSADDRESS2) {
            this.bUSINESSADDRESS2 = bUSINESSADDRESS2;
        }

        public Object getBUSINESSCITY() {
            return bUSINESSCITY;
        }

        public void setBUSINESSCITY(Object bUSINESSCITY) {
            this.bUSINESSCITY = bUSINESSCITY;
        }

        public Object getBUSINESSCOUNTRYID() {
            return bUSINESSCOUNTRYID;
        }

        public void setBUSINESSCOUNTRYID(Object bUSINESSCOUNTRYID) {
            this.bUSINESSCOUNTRYID = bUSINESSCOUNTRYID;
        }

        public Object getBUSINESSEMAIL() {
            return bUSINESSEMAIL;
        }

        public void setBUSINESSEMAIL(Object bUSINESSEMAIL) {
            this.bUSINESSEMAIL = bUSINESSEMAIL;
        }

        public Object getBUSINESSMOBILE() {
            return bUSINESSMOBILE;
        }

        public void setBUSINESSMOBILE(Object bUSINESSMOBILE) {
            this.bUSINESSMOBILE = bUSINESSMOBILE;
        }

        public Object getBUSINESSPHONE() {
            return bUSINESSPHONE;
        }

        public void setBUSINESSPHONE(Object bUSINESSPHONE) {
            this.bUSINESSPHONE = bUSINESSPHONE;
        }

        public Object getBUSINESSSTATEID() {
            return bUSINESSSTATEID;
        }

        public void setBUSINESSSTATEID(Object bUSINESSSTATEID) {
            this.bUSINESSSTATEID = bUSINESSSTATEID;
        }

        public Object getBUSINESSWEBADD() {
            return bUSINESSWEBADD;
        }

        public void setBUSINESSWEBADD(Object bUSINESSWEBADD) {
            this.bUSINESSWEBADD = bUSINESSWEBADD;
        }

        public Integer getAPPLICENCEID() {
            return aPPLICENCEID;
        }

        public void setAPPLICENCEID(Integer aPPLICENCEID) {
            this.aPPLICENCEID = aPPLICENCEID;
        }

        public Integer getPERSONSTATUS() {
            return pERSONSTATUS;
        }

        public void setPERSONSTATUS(Integer pERSONSTATUS) {
            this.pERSONSTATUS = pERSONSTATUS;
        }

        public Double getCURRENTBALANCE() {
            return cURRENTBALANCE;
        }

        public void setCURRENTBALANCE(Double cURRENTBALANCE) {
            this.cURRENTBALANCE = cURRENTBALANCE;
        }

        public Integer getCOMPANYTYPE() {
            return cOMPANYTYPE;
        }

        public void setCOMPANYTYPE(Integer cOMPANYTYPE) {
            this.cOMPANYTYPE = cOMPANYTYPE;
        }

        public Object getDEFAULTCURRENCY() {
            return dEFAULTCURRENCY;
        }

        public void setDEFAULTCURRENCY(Object dEFAULTCURRENCY) {
            this.dEFAULTCURRENCY = dEFAULTCURRENCY;
        }

        public Integer getPARENTPERSONID() {
            return pARENTPERSONID;
        }

        public void setPARENTPERSONID(Integer pARENTPERSONID) {
            this.pARENTPERSONID = pARENTPERSONID;
        }

        public Object getPERSONALEMAIL() {
            return pERSONALEMAIL;
        }

        public void setPERSONALEMAIL(Object pERSONALEMAIL) {
            this.pERSONALEMAIL = pERSONALEMAIL;
        }

        public Object getPERSONALFAX() {
            return pERSONALFAX;
        }

        public void setPERSONALFAX(Object pERSONALFAX) {
            this.pERSONALFAX = pERSONALFAX;
        }

        public Object getPERSONALWEBADD() {
            return pERSONALWEBADD;
        }

        public void setPERSONALWEBADD(Object pERSONALWEBADD) {
            this.pERSONALWEBADD = pERSONALWEBADD;
        }

        public Object getBUSINESSFAX() {
            return bUSINESSFAX;
        }

        public void setBUSINESSFAX(Object bUSINESSFAX) {
            this.bUSINESSFAX = bUSINESSFAX;
        }

        public Object getPERSONALPOSTCODE() {
            return pERSONALPOSTCODE;
        }

        public void setPERSONALPOSTCODE(Object pERSONALPOSTCODE) {
            this.pERSONALPOSTCODE = pERSONALPOSTCODE;
        }

        public Object getBUSINESSPOSTCODE() {
            return bUSINESSPOSTCODE;
        }

        public void setBUSINESSPOSTCODE(Object bUSINESSPOSTCODE) {
            this.bUSINESSPOSTCODE = bUSINESSPOSTCODE;
        }

        public Boolean getIsSameAddress() {
            return isSameAddress;
        }

        public void setIsSameAddress(Boolean isSameAddress) {
            this.isSameAddress = isSameAddress;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getNormalizedUserName() {
            return normalizedUserName;
        }

        public void setNormalizedUserName(Object normalizedUserName) {
            this.normalizedUserName = normalizedUserName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Object getNormalizedEmail() {
            return normalizedEmail;
        }

        public void setNormalizedEmail(Object normalizedEmail) {
            this.normalizedEmail = normalizedEmail;
        }

        public Boolean getEmailConfirmed() {
            return emailConfirmed;
        }

        public void setEmailConfirmed(Boolean emailConfirmed) {
            this.emailConfirmed = emailConfirmed;
        }

        public String getSecurityStamp() {
            return securityStamp;
        }

        public void setSecurityStamp(String securityStamp) {
            this.securityStamp = securityStamp;
        }

        public String getConcurrencyStamp() {
            return concurrencyStamp;
        }

        public void setConcurrencyStamp(String concurrencyStamp) {
            this.concurrencyStamp = concurrencyStamp;
        }

        public Object getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(Object phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Boolean getPhoneNumberConfirmed() {
            return phoneNumberConfirmed;
        }

        public void setPhoneNumberConfirmed(Boolean phoneNumberConfirmed) {
            this.phoneNumberConfirmed = phoneNumberConfirmed;
        }

        public Boolean getTwoFactorEnabled() {
            return twoFactorEnabled;
        }

        public void setTwoFactorEnabled(Boolean twoFactorEnabled) {
            this.twoFactorEnabled = twoFactorEnabled;
        }

        public Object getLockoutEnd() {
            return lockoutEnd;
        }

        public void setLockoutEnd(Object lockoutEnd) {
            this.lockoutEnd = lockoutEnd;
        }

        public Boolean getLockoutEnabled() {
            return lockoutEnabled;
        }

        public void setLockoutEnabled(Boolean lockoutEnabled) {
            this.lockoutEnabled = lockoutEnabled;
        }

        public Integer getAccessFailedCount() {
            return accessFailedCount;
        }

        public void setAccessFailedCount(Integer accessFailedCount) {
            this.accessFailedCount = accessFailedCount;
        }
    }
}
