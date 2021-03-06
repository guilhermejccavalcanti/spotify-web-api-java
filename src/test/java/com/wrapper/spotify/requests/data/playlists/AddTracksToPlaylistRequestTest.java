package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AddTracksToPlaylistRequestTest {

  @Test
  public void shouldAddTracksToPlaylist_async() throws Exception {
    final String accessToken = "someAccessToken";

    final Api api = new Api.Builder().setAccessToken("AccessToken").build();

    final String myUsername = "thelinmichael";
    final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
    final String[] tracksToAdd = new String[]{"spotify:track:4BYGxv4rxSNcTgT3DsFB9o", "spotify:track:0BG2iE6McPhmAEKIhfqy1X"};
    final int insertIndex = 3;

    final AddTracksToPlaylistRequest request = api.addTracksToPlaylist(myUsername, myPlaylistId, tracksToAdd)
            .position(insertIndex)
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/AddTracksToPlaylistRequest.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<SnapshotResult> addTrackFuture = request.postAsync();

    Futures.addCallback(addTrackFuture, new FutureCallback<SnapshotResult>() {
      @Override
      public void onSuccess(SnapshotResult response) {
        assertEquals("JbtmHBDBAYu3/bt8BOXKjzKx3i0b6LCa/wVjyl6qQ2Yf6nFXkbmzuEa+ZI/U1yF+", response.getSnapshotId());
        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future: " + throwable.getMessage());
      }
    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }
}
