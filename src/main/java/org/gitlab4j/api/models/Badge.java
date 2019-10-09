package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

public class Badge {

  private Integer id;
  private String linkUrl;
  private String imageUrl;
  private String renderedLinkUrl;
  private String renderedImageUrl;
  private String kind;

  /**
   * @return The ID or URL-encoded path of the project owned by the authenticated user
   */
  public Integer getId() {
    return id;
  }

  /**
   * @param id The ID or URL-encoded path of the project owned by the authenticated user
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * @return URL of the badge link
   */
  public String getLinkUrl() {
    return linkUrl;
  }

  /**
   * @param linkUrl URL of the badge link
   */
  public void setLinkUrl(String linkUrl) {
    this.linkUrl = linkUrl;
  }

  /**
   * @return URL of the badge image
   */
  public String getImageUrl() {
    return imageUrl;
  }

  /**
   * @param imageUrl URL of the badge image
   */
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getRenderedImageUrl() {
    return renderedImageUrl;
  }

  public void setRenderedImageUrl(String renderedImageUrl) {
    this.renderedImageUrl = renderedImageUrl;
  }

  public String getRenderedLinkUrl() {
    return renderedLinkUrl;
  }

  public void setRenderedLinkUrl(String renderedLinkUrl) {
    this.renderedLinkUrl = renderedLinkUrl;
  }

  public String getKind() {
    return kind;
  }

  public void setKind(String kind) {
    this.kind = kind;
  }

  @Override
  public String toString() {
    return (JacksonJson.toJsonString(this));
  }
}
