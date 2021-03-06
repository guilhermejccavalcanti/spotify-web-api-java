package com.wrapper.spotify.model_objects.special;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;

/**
 * Retrieve information about featured playlists by building instances from this class.
 */
public class FeaturedPlaylists extends AbstractModelObject {
  private final String message;
  private final Paging<ArtistSimplified.PlaylistSimplified> playlists;

  private FeaturedPlaylists(final FeaturedPlaylists.Builder builder) {
    super(builder);

    this.message = builder.message;
    this.playlists = builder.playlists;
  }

  /**
   * Get the message which is displayed on the front page of the "browse" tab
   * in the Spotify client. <br>
   * The message usually refers to the featured playlists.
   *
   * @return A "welcoming" message.
   */
  public String getMessage() {
    return message;
  }

  /**
   * Get a page of featured playlists.
   *
   * @return Featured playlists page.
   */
  public Paging<ArtistSimplified.PlaylistSimplified> getPlaylists() {
    return playlists;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building featured playlists instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String message;
    private Paging<ArtistSimplified.PlaylistSimplified> playlists;

    /**
     * Set the message, which normally would be displayed on the front page
     * of the "browse" tab.
     *
     * @param message Message to be set.
     * @return A builder object.
     */
    public Builder setMessage(String message) {
      this.message = message;
      return this;
    }

    /**
     * Set a page of playlists contained in the featured playlists object to be built.
     *
     * @param playlists A page of simplified playlists.
     * @return A builder object.
     */
    public Builder setPlaylists(Paging<ArtistSimplified.PlaylistSimplified> playlists) {
      this.playlists = playlists;
      return this;
    }

    @Override
    public FeaturedPlaylists build() {
      return new FeaturedPlaylists(this);
    }
  }

  /**
   * JsonUtil class for building featured playlists instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<FeaturedPlaylists> {
    public FeaturedPlaylists createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new FeaturedPlaylists.Builder()
              .setMessage(jsonObject.get("message").getAsString())
              .setPlaylists(new ArtistSimplified.PlaylistSimplified.JsonUtil().createModelObjectPaging(jsonObject.getAsJsonObject("playlists")))
              .build();
    }
  }
}
