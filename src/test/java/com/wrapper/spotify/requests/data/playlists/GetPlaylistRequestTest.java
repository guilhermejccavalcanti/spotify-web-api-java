package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.specification.Playlist;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/*
 * TODO: Add negative tests
 */
public class GetPlaylistRequestTest {

  @Test
  public void shouldCreatePlaylistPage_async() throws Exception {
    final Api api = new Api.Builder().setAccessToken("AccessToken").build();

    final GetPlaylistRequest request = api.getPlaylist("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/GetPlaylistRequest.json"))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Playlist> playlistFuture = request.getAsync();

    Futures.addCallback(playlistFuture, new FutureCallback<Playlist>() {
      @Override
      public void onSuccess(Playlist playlist) {
        assertEquals("https://api.spotify.com/v1/users/thelinmichael/playlists/3ktAYNcRHpazJ9qecm3ptn", playlist.getHref());
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
  public void shouldCreatePlaylistPage() throws Exception {
    final Api api = new Api.Builder().setAccessToken("AccessToken").build();

    final GetPlaylistRequest request = api.getPlaylist("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/GetPlaylistRequest.json"))
            .build();

    final Playlist playlist = request.get();

    assertEquals("https://api.spotify.com/v1/users/thelinmichael/playlists/3ktAYNcRHpazJ9qecm3ptn", playlist.getHref());
  }

  @Test
  public void shouldBeAbleToHandlePlaylistsWithLocalFiles() throws Exception {
    final Api api = new Api.Builder().setAccessToken("AccessToken").build();

    final GetPlaylistRequest request = api.getPlaylist("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn")
            .setHttpManager(TestUtil.MockedHttpManager.returningJson("requests/data/playlists/GetPlaylistRequest_Local.json"))
            .build();

    Playlist playlist = request.get();

    assertTrue(playlist.getTracks().getItems()[0].getTrack().getAlbum().getAlbumType() == null);

    assertNotNull(playlist);
  }

  @Ignore
  @Test
  public void shouldFailFutureIfPlaylistIsNotFound() throws Exception {
  }

  @Test
  @Ignore
  public void shouldFailFutureIfNotAllowedAccess() throws Exception {
  }

  @Test
  @Ignore
  public void shouldFailFutureIfUserDoesNotExist() throws Exception {
  }

  @Test
  @Ignore
  public void shouldThrowExceptionIfPlaylistIsNotFound() throws Exception {
  }

  @Test
  @Ignore
  public void shouldThrowExceptionIfNotAllowedAccess() throws Exception {
  }

}
