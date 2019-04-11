package app.akilesh.nex.fonts;

import android.app.Application;

public class CustomFont extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "DEFAULT");
        FontsOverride.setDefaultFont(this, "MONOSPACE");
        FontsOverride.setDefaultFont(this, "SERIF");
        FontsOverride.setDefaultFont(this, "SANS_SERIF");
    }
}
