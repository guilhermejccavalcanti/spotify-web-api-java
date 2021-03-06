package com.wrapper.spotify.requests.data.library;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class RemoveUsersSavedTracksRequestTest {

  @Test
  public void removeFromMySavedTracks_async() throws Exception {
    final String accessToken = "accessToken";

    final Api api = new Api.Builder().setAccessToken(accessToken)
            .setHttpManager(TestUtil.MockedHttpManager.returningString(""))
            .build();

    final String[] tracksToAdd = new String[]{"5xFF6wNcoRwx7N3cDTgVWP", "13zm8XhfM4RBtQpjdqY44e"};

    final RemoveUsersSavedTracksRequest request = api.removeFromMySavedTracks(tracksToAdd).build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture removeTrackFuture = request.deleteAsync();

    Futures.addCallback(removeTrackFuture, new FutureCallback<String>() {

      @Override
      public void onSuccess(String response) {
        assertEquals("", response);

        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future: " + throwable.getMessage());
      }

    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

  @Test
  public void removeFromMySavedTracks_sync() throws Exception {
    final String accessToken = "accessToken";

    final Api api = new Api.Builder().setAccessToken(accessToken)
            .setHttpManager(TestUtil.MockedHttpManager.returningString(""))
            .build();

    final String[] tracksToAdd = new String[]{"5xFF6wNcoRwx7N3cDTgVWP", "13zm8XhfM4RBtQpjdqY44e"};

    final RemoveUsersSavedTracksRequest request = api.removeFromMySavedTracks(tracksToAdd).build();

    request.delete();
  }
}
