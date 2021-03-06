package com.wrapper.spotify.model_objects;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.model_objects.specification.Artist;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArtistTest {
  @Test
  public void shouldCreateArtist() throws Exception {
    Artist artist = new Artist.JsonUtil().createModelObject(TestUtil.readTestData("requests/data/artists/GetArtistRequest.json"));
    assertEquals("https://api.spotify.com/v1/artists/2BTZIqw0ntH9MvilQ3ewNY", artist.getHref());
  }

//  @Test
//  public void shouldCreateSeveralArtists() throws Exception {
//    Artist[] artists = new Artist.JsonUtil().createModelObjectArray(TestUtil.readTestData("GetArtistRequest.json"));
//    assertEquals(2, artists.length);
//  }
}
