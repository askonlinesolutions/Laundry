package com.laundry.ui.LoginScreen.vo;

public class AccessTokenResponse {


    /**
     * access_token : e1e057edb82fc59dfb75766b4794caba0d3ea92f
     * scope : api_user
     * token_type : Bearer
     * expires_in : 5184000
     */
    private String access_token;
    private String scope;
    private String token_type;
    private int expires_in;

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getScope() {
        return scope;
    }

    public String getToken_type() {
        return token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }
}
