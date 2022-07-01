package com.hunludvig;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client
public interface PictureFetcher {
    @Get("${micronaut.application.picture.source}")
    byte[] fetchPicture();
}
