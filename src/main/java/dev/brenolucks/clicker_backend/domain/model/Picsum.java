package dev.brenolucks.clicker_backend.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Picsum {
    private Long id;
    private String author;
    private int width;
    private int height;
    private String url;
    @JsonProperty("download_url")
    private String downloadUrl;
}
