package com.wrapper.spotify.enums;

public enum ProductType {

  PREMIUM("premium"),
  FREE("free"),
  OPEN("open"),
  DAYPASS("daypass");

  public final String type;

  ProductType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

}
