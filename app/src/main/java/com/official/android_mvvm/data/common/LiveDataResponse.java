package com.official.android_mvvm.data.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import static com.official.android_mvvm.data.common.Status.ERROR;
import static com.official.android_mvvm.data.common.Status.LOADING;
import static com.official.android_mvvm.data.common.Status.SUCCESS;


/**
 * LiveDataResponse holder provided to the UI
 */
public class LiveDataResponse<T> {

    public final Status status;

    @Nullable
    public final T data;

    @Nullable
    public final Throwable error;

    private LiveDataResponse(Status status, @Nullable T data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static LiveDataResponse loading() {
        return new LiveDataResponse(LOADING, null, null);
    }

    public static <T> LiveDataResponse success(@NonNull T data) {
        return new LiveDataResponse(SUCCESS, data, null);
    }

    public static LiveDataResponse error(@NonNull Throwable error) {
        return new LiveDataResponse(ERROR, null, error);
    }
}
