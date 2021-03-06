package com.wrapper.spotify.requests.data.tracks;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.miscellaneous.AudioAnalysis;
import com.wrapper.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;

public class GetAudioAnalysisForTrackRequest extends AbstractDataRequest {

  private GetAudioAnalysisForTrackRequest(final Builder builder) {
    super(builder);
  }

  public AudioAnalysis get() throws
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
    return new AudioAnalysis.JsonUtil().createModelObject(getJson());
  }

  public SettableFuture<AudioAnalysis> getAsync() throws
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
    return executeAsync(new AudioAnalysis.JsonUtil().createModelObject(getJson()));
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
    public GetAudioAnalysisForTrackRequest build() {
      setPath("/v1/audio-analysis/{id}");
      return new GetAudioAnalysisForTrackRequest(this);
    }
  }
}
