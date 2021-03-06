package com.wrapper.spotify.model_objects.credentials;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

public class AuthorizationCodeCredentials extends AbstractModelObject {
  private final String accessToken;
  private final String tokenType;
  private final String scope;
  private final int expiresIn;
  private final String refreshToken;

  private AuthorizationCodeCredentials(final AuthorizationCodeCredentials.Builder builder) {
    super(builder);

    this.accessToken = builder.accessToken;
    this.tokenType = builder.tokenType;
    this.scope = builder.scope;
    this.expiresIn = builder.expiresIn;
    this.refreshToken = builder.refreshToken;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public String getTokenType() {
    return tokenType;
  }

  public String getScope() {
    return scope;
  }

  public int getExpiresIn() {
    return expiresIn;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  public static final class Builder extends AbstractModelObject.Builder {
    private String accessToken;
    private String tokenType;
    private String scope;
    private int expiresIn;
    private String refreshToken;

    public Builder setAccessToken(String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    public Builder setTokenType(String tokenType) {
      this.tokenType = tokenType;
      return this;
    }

    public Builder setScope(String scope) {
      this.scope = scope;
      return this;
    }

    public Builder setExpiresIn(int expiresIn) {
      this.expiresIn = expiresIn;
      return this;
    }

    public Builder setRefreshToken(String refreshToken) {
      this.refreshToken = refreshToken;
      return this;
    }

    @Override
    public AuthorizationCodeCredentials build() {
      return new AuthorizationCodeCredentials(this);
    }
  }

  public static final class JsonUtil extends AbstractModelObject.JsonUtil<AuthorizationCodeCredentials> {
    public AuthorizationCodeCredentials createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new AuthorizationCodeCredentials.Builder()
              .setAccessToken(jsonObject.get("access_token").getAsString())
              .setTokenType(jsonObject.get("token_type").getAsString())
              .setScope(jsonObject.get("scope").getAsString())
              .setExpiresIn(jsonObject.get("expires_in").getAsInt())
              .setRefreshToken(jsonObject.get("refresh_token").getAsString())
              .build();
    }
  }
}
