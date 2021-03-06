package com.wrapper.spotify.model_objects.miscellaneous;

import com.google.gson.JsonObject;
import com.wrapper.spotify.model_objects.AbstractModelObject;

/**
 * Retrieve information about a playlists tracks by building instances from this class.
 */
public class PlaylistTracksInformation extends AbstractModelObject {
  private final String href;
  private final int total;

  private PlaylistTracksInformation(final PlaylistTracksInformation.Builder builder) {
    super(builder);

    this.href = builder.href;
    this.total = builder.total;
  }

  /**
   * Get the full Spotify API endpoint url of a playlist tracks information object.
   *
   * @return A Spotify API endpoint url.
   */
  public String getHref() {
    return href;
  }

  /**
   * Get the total amount of tracks in a playlist.
   *
   * @return The total amount of tracks in a playlist.
   */
  public int getTotal() {
    return total;
  }

  @Override
  public Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for building playlist tracks information instances.
   */
  public static final class Builder extends AbstractModelObject.Builder {
    private String href;
    private int total;

    /**
     * Set href of Spotify api endpoint of the playlist tracks information object to be built.
     *
     * @param href  Spotify api endpoint url.
     * @return      A builder object.
     */
    public Builder setHref(String href) {
      this.href = href;
      return this;
    }

    /**
     * Set the total amount of tracks in the playlist.
     *
     * @param total Total amount of tracks.
     * @return      A builder object.
     */
    public Builder setTotal(int total) {
      this.total = total;
      return this;
    }

    @Override
    public PlaylistTracksInformation build() {
      return new PlaylistTracksInformation(this);
    }
  }

  /**
   * JsonUtil class for building playlist tracks information instances.
   */
  public static final class JsonUtil extends AbstractModelObject.JsonUtil<PlaylistTracksInformation> {
    public PlaylistTracksInformation createModelObject(JsonObject jsonObject) {
      if (jsonObject == null || jsonObject.isJsonNull()) {
        return null;
      }

      return new PlaylistTracksInformation.Builder()
              .setHref(jsonObject.get("href").getAsString())
              .setTotal(jsonObject.get("total").getAsInt())
              .build();
    }
  }
}
