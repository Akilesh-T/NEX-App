package app.akilesh.nex;

import android.app.Application;

public class CustomFont extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/NokiaPureText-Regular.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/NokiaPureText-Regular.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/NokiaPureText-Regular.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/NokiaPureText-Regular.ttf");
    }
}
