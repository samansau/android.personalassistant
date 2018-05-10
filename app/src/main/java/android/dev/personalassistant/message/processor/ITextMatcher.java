package android.dev.personalassistant.message.processor;

/**
 * Created by saurabh on 5/9/18.
 */

public interface ITextMatcher {
    public boolean matches(String source,String regex);

}
