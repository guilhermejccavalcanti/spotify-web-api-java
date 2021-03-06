package com.wrapper.spotify.requests.data.tracks;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.specification.AudioFeatures;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetAudioFeaturesForTrackRequest extends AbstractDataRequest {

  private GetAudioFeaturesForTrackRequest(final Builder builder) {
    super(builder);
  }

  public AudioFeatures get() throws
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
    return new AudioFeatures.JsonUtil().createModelObject(getJson());
  }

  public SettableFuture<AudioFeatures> getAsync() throws
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
    return executeAsync(new AudioFeatures.JsonUtil().createModelObject(getJson()));
  }

  public static final class Builder extends AbstractDataRequest.Builder<Builder> {

    public Builder(final String accessToken) {
      super(accessToken);
    }

    public Builder id(final String id) {
      assert (id != null);
      assert (!id.equals(""));
      return setPathParameter("id", id);
    }

    @Override
    public GetAudioFeaturesForTrackRequest build() {
      setPath("/v1/audio-features/{id}");
      return new GetAudioFeaturesForTrackRequest(this);
    }
  }
}
