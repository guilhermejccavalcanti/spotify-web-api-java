package com.wrapper.spotify.requests.data.library;

import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class CheckUsersSavedAlbumsRequest extends AbstractDataRequest {

  private CheckUsersSavedAlbumsRequest(final Builder builder) {
    super(builder);
  }

  public Boolean[] get() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return new Gson().fromJson(new JsonParser().parse(getJson()).getAsJsonArray(), Boolean[].class);
  }

  public SettableFuture<Boolean[]> getAsync() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return executeAsync(new Gson().fromJson(new JsonParser().parse(getJson()).getAsJsonArray(), Boolean[].class));
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder ids(final String ids) {
      assert (ids != null);
      assert (ids.split(",").length <= 50);
      return setFormParameter("ids", ids);
    }

    @Override
    public CheckUsersSavedAlbumsRequest build() {
      setPath("/v1/me/albums/contains");
      return new CheckUsersSavedAlbumsRequest(this);
    }
  }
}
