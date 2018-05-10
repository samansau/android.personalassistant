package android.dev.personalassistant.message.processor;

import android.support.annotation.NonNull;

/**
 * Created by saurabh on 5/9/18.
 */

public class TextMatcher implements ITextMatcher{
    @Override
    public boolean matches(@NonNull String source, @NonNull String regex) {
        return source.matches(regex);
    }
}
