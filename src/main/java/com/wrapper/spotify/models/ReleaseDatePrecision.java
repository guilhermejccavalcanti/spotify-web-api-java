package com.wrapper.spotify.models;

public enum ReleaseDatePrecision {

  YEAR("year"),
  MONTH("month"),
  DAY("day");

  public final String precision;

  ReleaseDatePrecision(String precision) {
    this.precision = precision;
  }

  public String getPrecision() {
    return precision;
  }

}