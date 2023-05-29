package com.example.news.Conaiguration.pojo;

import java.util.LinkedHashMap;
        import java.util.List;
        import java.util.Map;
        import javax.annotation.Generated;
        import com.fasterxml.jackson.annotation.JsonAnyGetter;
        import com.fasterxml.jackson.annotation.JsonAnySetter;
        import com.fasterxml.jackson.annotation.JsonIgnore;
        import com.fasterxml.jackson.annotation.JsonInclude;
        import com.fasterxml.jackson.annotation.JsonProperty;
        import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 Pojo класс для маппинга из json
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "title",
        "url",
        "imageUrl",
        "newsSite",
        "summary",
        "publishedAt",
        "updatedAt",
        "featured",
        "launches",
        "events"
})
@Generated("jsonschema2pojo")
public class ArticleInfo {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("url")
    private String url;
    @JsonProperty("imageUrl")
    private String imageUrl;
    @JsonProperty("newsSite")
    private String newsSite;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("publishedAt")
    private String publishedAt;
    @JsonProperty("updatedAt")
    private String updatedAt;
    @JsonProperty("featured")
    private Boolean featured;
    @JsonProperty("launches")
    private List<Launch> launches;
    @JsonProperty("events")
    private List<Object> events;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("imageUrl")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("newsSite")
    public String getNewsSite() {
        return newsSite;
    }

    @JsonProperty("newsSite")
    public void setNewsSite(String newsSite) {
        this.newsSite = newsSite;
    }

    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @JsonProperty("publishedAt")
    public String getPublishedAt() {
        return publishedAt;
    }

    @JsonProperty("publishedAt")
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @JsonProperty("updatedAt")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updatedAt")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("featured")
    public Boolean getFeatured() {
        return featured;
    }

    @JsonProperty("featured")
    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    @JsonProperty("launches")
    public List<Launch> getLaunches() {
        return launches;
    }

    @JsonProperty("launches")
    public void setLaunches(List<Launch> launches) {
        this.launches = launches;
    }

    @JsonProperty("events")
    public List<Object> getEvents() {
        return events;
    }

    @JsonProperty("events")
    public void setEvents(List<Object> events) {
        this.events = events;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}