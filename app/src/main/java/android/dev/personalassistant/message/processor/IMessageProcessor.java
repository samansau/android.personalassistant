package android.dev.personalassistant.message.processor;

import android.dev.personalassistant.message.enums.MessageSourceEnum;

import java.util.List;

/**
 * Created by saurabh on 5/10/18.
 */

public interface IMessageProcessor {
    public List<String> readRawMessagesFromSource(int numberOfMessages);
}
