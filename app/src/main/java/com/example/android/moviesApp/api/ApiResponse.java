
package com.example.android.moviesApp.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.example.android.moviesApp.service.model.Movies;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Response;
import timber.log.Timber;


public class ApiResponse<T> {
    private static final Pattern LINK_PATTERN = Pattern
          .compile("<([^>]*)>[\\s]*;[\\s]*rel=\"([a-zA-Z0-9]+)\"");
    private static final Pattern PAGE_PATTERN = Pattern.compile("\\bpage=(\\d+)");
    private static final String NEXT_LINK = "next";
    public int code;

    @Nullable
    public T body;

    public List<Movies.Results> movies;

    @Nullable
    public String errorMessage;

    @NonNull
    public Map<String, String> links;

    public ApiResponse(Throwable error) {
        Log.d("throweror", Integer.toString(code));
        code = 500;
        body = null;
        errorMessage = error.getMessage();

        links = Collections.emptyMap();
        Log.d("eroor", errorMessage);
    }

    public ApiResponse(Response<T> response) {
        code = response.code();
        Log.d("code", Integer.toString(code));
        if (response.isSuccessful()) {
            Log.d("apiress", "isnotnull");
            body=response.body();
            errorMessage = null;
        } else {
            Log.d("apiress", "isnull");
            String message = null;
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody().string();
                } catch (IOException ignored) {
                    Timber.e(ignored, "error while parsing response");
                }
            }
            if (message == null || message.trim().length() == 0) {
                message = response.message();
            }
            errorMessage = message;
            body = null;
        }
        String linkHeader = response.headers().get("link");
        if (linkHeader == null) {
            links = Collections.emptyMap();
        } else {
            links = new ArrayMap<>();
            Matcher matcher = LINK_PATTERN.matcher(linkHeader);

            while (matcher.find()) {
                int count = matcher.groupCount();
                if (count == 3) {
                    links.put(matcher.group(2), matcher.group(1));
                }
            }
        }
    }

   public boolean isSuccessful() {
        return code >= 200 && code < 300;
    }

   public Integer getNextPage() {
        String next = links.get(NEXT_LINK);
        if (next == null) {
            return null;
        }
        Matcher matcher = PAGE_PATTERN.matcher(next);
        if (!matcher.find() || matcher.groupCount() != 1) {
            return null;
        }
        try {
            return Integer.parseInt(matcher.group(1));
        } catch (NumberFormatException ex) {
            Timber.w("cannot parse next page from %s", next);
            return null;
        }
    }

}
