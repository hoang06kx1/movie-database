package nguyen.hoang.movierating.di;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nguyen.hoang.movierating.Utils.ParseWrapper;

/**
 * Created by Hoang on 5/29/2016.
 */

@Module
public class MockParseWrapperModule {
    @Singleton
    @Provides
    public ParseWrapper provideMockParseWrapper() {
        return Mockito.mock(ParseWrapper.class);
    }
}
