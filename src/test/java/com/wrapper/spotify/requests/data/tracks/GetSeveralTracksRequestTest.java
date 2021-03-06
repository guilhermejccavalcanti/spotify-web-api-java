package com.wrapper.spotify.requests.data.tracks;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.specification.Track;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class GetSeveralTracksRequestTest {

  @Test
  public void shouldGetTracksResult_async() throws Exception {
    final Api api = new Api.Builder().setAccessToken("AccessToken").build();

    final GetSeveralTracksRequest request = api.getTracks("0eGsygTp906u18L0Oimnem", "1lDWb6b6ieDQ2xT7ewTC3G")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/tracks/GetSeveralTracksRequest.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Track[]> tracksFuture = request.getAsync();

    Futures.addCallback(tracksFuture, new FutureCallback<Track[]>() {
      @Override
      public void onSuccess(Track... tracks) {
        assertEquals(2, tracks.length);

        Track firstTrack = tracks[0];
        assertEquals("0eGsygTp906u18L0Oimnem", firstTrack.getId());

        Track secondTrack = tracks[1];
        assertEquals("1lDWb6b6ieDQ2xT7ewTC3G", secondTrack.getId());

        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future");
      }
    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

  @Test
  public void shouldGetTracksResult_sync() throws Exception {
    final Api api = new Api.Builder().setAccessToken("AccessToken").build();

    final GetSeveralTracksRequest request = api.getTracks("0eGsygTp906u18L0Oimnem", "1lDWb6b6ieDQ2xT7ewTC3G")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/tracks/GetSeveralTracksRequest.json"))
            .build();

    final Track[] tracks = request.get();

    assertEquals(2, tracks.length);

    final Track firstTrack = tracks[0];
    assertEquals("0eGsygTp906u18L0Oimnem", firstTrack.getId());

    final Track secondTrack = tracks[1];
    assertEquals("1lDWb6b6ieDQ2xT7ewTC3G", secondTrack.getId());
  }
}
