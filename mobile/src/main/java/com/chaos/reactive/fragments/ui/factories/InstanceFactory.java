package com.chaos.reactive.fragments.ui.factories;

import android.content.Context;

public interface InstanceFactory<T> {
    T instantiate(Context context);
}
