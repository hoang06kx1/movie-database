package nguyen.hoang.movierating;

import android.content.Context;

import javax.inject.Singleton;

import nguyen.hoang.movierating.di.DaggerMockParseWrapperComponent;
import nguyen.hoang.movierating.di.DaggerParseWrapperComponent;
import nguyen.hoang.movierating.di.MockParseWrapperModule;
import nguyen.hoang.movierating.di.ParseWrapperComponent;

/**
 * Created by Hoang on 12/14/2015.
 */
public class TestParseApplication extends ParseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void enableParseLocalDatastore(Context context) {
        // Parse.enableLocalDatastore(context);
    }

    private final ParseWrapperComponent component = createComponent();

    ParseWrapperComponent createComponent() {
        return DaggerMockParseWrapperComponent.builder()
                .mockParseWrapperModule(new MockParseWrapperModule())
                .build();
    }

    @Override
    public ParseWrapperComponent getParseWrapperComponent() {
        return component;
    }
}
