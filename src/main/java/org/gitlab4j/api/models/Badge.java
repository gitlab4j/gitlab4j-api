package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

public class Badge {

  private Integer id;
  private String link_url;
  private String image_url;
  private String rendered_link_url;
  private String rendered_image_url;
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
  public String getLink_url() {
    return link_url;
  }

  /**
   * @param link_url URL of the badge link
   */
  public void setLink_url(String link_url) {
    this.link_url = link_url;
  }

  /**
   * @return URL of the badge image
   */
  public String getImage_url() {
    return image_url;
  }

  /**
   * @param image_url URL of the badge image
   */
  public void setImage_url(String image_url) {
    this.image_url = image_url;
  }

  public String getRendered_image_url() {
    return rendered_image_url;
  }

  public void setRendered_image_url(String rendered_image_url) {
    this.rendered_image_url = rendered_image_url;
  }

  public String getRendered_link_url() {
    return rendered_link_url;
  }

  public void setRendered_link_url(String rendered_link_url) {
    this.rendered_link_url = rendered_link_url;
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
