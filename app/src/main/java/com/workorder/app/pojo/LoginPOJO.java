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
        private List<String> roleNames = null;
        @SerializedName("CompanyName")
        @Expose
        private Object companyName;
        @SerializedName("CountryID")
        @Expose
        private Object countryID;
        @SerializedName("StateID")
        @Expose
        private Object stateID;
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
        private Integer pERSONALCOUNTRYID;
        @SerializedName("PERSONAL_STATE_ID")
        @Expose
        private Integer pERSONALSTATEID;
        @SerializedName("FIRST_NAME")
        @Expose
        private String fIRSTNAME;
        @SerializedName("MIDDLE_NAME")
        @Expose
        private Object mIDDLENAME;
        @SerializedName("LAST_NAME")
        @Expose
        private String lASTNAME;
        @SerializedName("COMPANY_NAME")
        @Expose
        private Object cOMPANYNAME;
        @SerializedName("PERSONALADDRESS1")
        @Expose
        private String pERSONALADDRESS1;
        @SerializedName("PERSONALADDRESS2")
        @Expose
        private String pERSONALADDRESS2;
        @SerializedName("PERSONAL_CITY")
        @Expose
        private String pERSONALCITY;
        @SerializedName("PERSONAL_MOBILE")
        @Expose
        private String pERSONALMOBILE;
        @SerializedName("PERSONAL_PHONE")
        @Expose
        private String pERSONALPHONE;
        @SerializedName("BUSINESS_ADDRESS1")
        @Expose
        private String bUSINESSADDRESS1;
        @SerializedName("BUSINESS_ADDRESS2")
        @Expose
        private String bUSINESSADDRESS2;
        @SerializedName("BUSINESS_CITY")
        @Expose
        private String bUSINESSCITY;
        @SerializedName("BUSINESS_COUNTRY_ID")
        @Expose
        private Integer bUSINESSCOUNTRYID;
        @SerializedName("BUSINESS_EMAIL")
        @Expose
        private String bUSINESSEMAIL;
        @SerializedName("BUSINESS_MOBILE")
        @Expose
        private String bUSINESSMOBILE;
        @SerializedName("BUSINESS_PHONE")
        @Expose
        private String bUSINESSPHONE;
        @SerializedName("BUSINESS_STATE_ID")
        @Expose
        private Integer bUSINESSSTATEID;
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
        private String pERSONALEMAIL;
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
        @SerializedName("PersonalState")
        @Expose
        private String personalState;
        @SerializedName("BusinessState")
        @Expose
        private String businessState;
        @SerializedName("PersonalCountry")
        @Expose
        private String personalCountry;
        @SerializedName("BusinessCountry")
        @Expose
        private String businessCountry;
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

        public List<String> getRoleNames() {
            return roleNames;
        }

        public void setRoleNames(List<String> roleNames) {
            this.roleNames = roleNames;
        }

        public Object getCompanyName() {
            return companyName;
        }

        public void setCompanyName(Object companyName) {
            this.companyName = companyName;
        }

        public Object getCountryID() {
            return countryID;
        }

        public void setCountryID(Object countryID) {
            this.countryID = countryID;
        }

        public Object getStateID() {
            return stateID;
        }

        public void setStateID(Object stateID) {
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

        public Integer getPERSONALCOUNTRYID() {
            return pERSONALCOUNTRYID;
        }

        public void setPERSONALCOUNTRYID(Integer pERSONALCOUNTRYID) {
            this.pERSONALCOUNTRYID = pERSONALCOUNTRYID;
        }

        public Integer getPERSONALSTATEID() {
            return pERSONALSTATEID;
        }

        public void setPERSONALSTATEID(Integer pERSONALSTATEID) {
            this.pERSONALSTATEID = pERSONALSTATEID;
        }

        public String getFIRSTNAME() {
            return fIRSTNAME;
        }

        public void setFIRSTNAME(String fIRSTNAME) {
            this.fIRSTNAME = fIRSTNAME;
        }

        public Object getMIDDLENAME() {
            return mIDDLENAME;
        }

        public void setMIDDLENAME(Object mIDDLENAME) {
            this.mIDDLENAME = mIDDLENAME;
        }

        public String getLASTNAME() {
            return lASTNAME;
        }

        public void setLASTNAME(String lASTNAME) {
            this.lASTNAME = lASTNAME;
        }

        public Object getCOMPANYNAME() {
            return cOMPANYNAME;
        }

        public void setCOMPANYNAME(Object cOMPANYNAME) {
            this.cOMPANYNAME = cOMPANYNAME;
        }

        public String getPERSONALADDRESS1() {
            return pERSONALADDRESS1;
        }

        public void setPERSONALADDRESS1(String pERSONALADDRESS1) {
            this.pERSONALADDRESS1 = pERSONALADDRESS1;
        }

        public String getPERSONALADDRESS2() {
            return pERSONALADDRESS2;
        }

        public void setPERSONALADDRESS2(String pERSONALADDRESS2) {
            this.pERSONALADDRESS2 = pERSONALADDRESS2;
        }

        public String getPERSONALCITY() {
            return pERSONALCITY;
        }

        public void setPERSONALCITY(String pERSONALCITY) {
            this.pERSONALCITY = pERSONALCITY;
        }

        public String getPERSONALMOBILE() {
            return pERSONALMOBILE;
        }

        public void setPERSONALMOBILE(String pERSONALMOBILE) {
            this.pERSONALMOBILE = pERSONALMOBILE;
        }

        public String getPERSONALPHONE() {
            return pERSONALPHONE;
        }

        public void setPERSONALPHONE(String pERSONALPHONE) {
            this.pERSONALPHONE = pERSONALPHONE;
        }

        public String getBUSINESSADDRESS1() {
            return bUSINESSADDRESS1;
        }

        public void setBUSINESSADDRESS1(String bUSINESSADDRESS1) {
            this.bUSINESSADDRESS1 = bUSINESSADDRESS1;
        }

        public String getBUSINESSADDRESS2() {
            return bUSINESSADDRESS2;
        }

        public void setBUSINESSADDRESS2(String bUSINESSADDRESS2) {
            this.bUSINESSADDRESS2 = bUSINESSADDRESS2;
        }

        public String getBUSINESSCITY() {
            return bUSINESSCITY;
        }

        public void setBUSINESSCITY(String bUSINESSCITY) {
            this.bUSINESSCITY = bUSINESSCITY;
        }

        public Integer getBUSINESSCOUNTRYID() {
            return bUSINESSCOUNTRYID;
        }

        public void setBUSINESSCOUNTRYID(Integer bUSINESSCOUNTRYID) {
            this.bUSINESSCOUNTRYID = bUSINESSCOUNTRYID;
        }

        public String getBUSINESSEMAIL() {
            return bUSINESSEMAIL;
        }

        public void setBUSINESSEMAIL(String bUSINESSEMAIL) {
            this.bUSINESSEMAIL = bUSINESSEMAIL;
        }

        public String getBUSINESSMOBILE() {
            return bUSINESSMOBILE;
        }

        public void setBUSINESSMOBILE(String bUSINESSMOBILE) {
            this.bUSINESSMOBILE = bUSINESSMOBILE;
        }

        public String getBUSINESSPHONE() {
            return bUSINESSPHONE;
        }

        public void setBUSINESSPHONE(String bUSINESSPHONE) {
            this.bUSINESSPHONE = bUSINESSPHONE;
        }

        public Integer getBUSINESSSTATEID() {
            return bUSINESSSTATEID;
        }

        public void setBUSINESSSTATEID(Integer bUSINESSSTATEID) {
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

        public String getPERSONALEMAIL() {
            return pERSONALEMAIL;
        }

        public void setPERSONALEMAIL(String pERSONALEMAIL) {
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

        public String getPersonalState() {
            return personalState;
        }

        public void setPersonalState(String personalState) {
            this.personalState = personalState;
        }

        public String getBusinessState() {
            return businessState;
        }

        public void setBusinessState(String businessState) {
            this.businessState = businessState;
        }

        public String getPersonalCountry() {
            return personalCountry;
        }

        public void setPersonalCountry(String personalCountry) {
            this.personalCountry = personalCountry;
        }

        public String getBusinessCountry() {
            return businessCountry;
        }

        public void setBusinessCountry(String businessCountry) {
            this.businessCountry = businessCountry;
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
