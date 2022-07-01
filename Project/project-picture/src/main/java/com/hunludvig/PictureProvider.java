package com.hunludvig;

import io.micronaut.context.annotation.Value;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class PictureProvider {
    private static final Logger LOG = LoggerFactory.getLogger(PictureProvider.class.getCanonicalName());

    @Inject
    private PictureFetcher fetcher;

    @Value("${micronaut.application.picture.cache}")
    private String cacheLocation;

    public byte[] pictureOfTheDay() {
        return tryCache().orElseGet(this::fetchNewPictureAndSave);
    }

    private Path todayCachedPicture() {
        var today = LocalDate.now();
        var filename = String.format("pictureOf_%d_%d_%d.jpg", today.getYear(),
                today.getMonthValue(), today.getDayOfMonth());
        return Path.of(cacheLocation, filename);
    }

    private Optional<byte[]> tryCache() {
        var cachedPicture = todayCachedPicture();
        try {
          if(Files.exists(cachedPicture)) {
              LOG.info("Cache found at {}", cachedPicture);
                return Optional.of(Files.readAllBytes(cachedPicture));
            }
        } catch (IOException e) {
            LOG.error("Failed to read cache", e);
        }
        LOG.info("Cache not found at {}", cachedPicture);
        return Optional.empty();
    }

    private byte[] fetchNewPictureAndSave() {
        try {
            LOG.info("Fetching new picture");
            var picture = fetcher.fetchPicture();
            savePictureToCache(picture);
            return picture;
        } catch (RuntimeException | IOException e) {
            LOG.error("Failed to fetch picture", e);
            throw new RuntimeException("Failed to fetch picture", e);
        }
    }

    private void savePictureToCache(final byte[] picture) throws IOException {
        var cache = todayCachedPicture();
        LOG.info("Save picture to {}", cache);
        Files.write(cache , picture);
    }
}
