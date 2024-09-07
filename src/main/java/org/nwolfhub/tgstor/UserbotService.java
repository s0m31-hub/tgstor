package org.nwolfhub.tgstor;


import it.tdlight.client.SimpleTelegramClient;
import it.tdlight.jni.TdApi;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class UserbotService {
    private final Environment environment;
    private SimpleTelegramClient client;
    private final Long channelId;

    public UserbotService(SimpleTelegramClient client, Environment environment) {
        this.client = client;
        this.environment = environment;
        String timedId = environment.getProperty("telegram.channel");
        channelId = timedId==null?null:Long.valueOf(timedId);
    }

    private void postStartup() {
        if(channelId==null) {
            
        } else {
            TdApi.GetSupergroupFullInfo getSupergroupFullInfo = new TdApi.GetSupergroupFullInfo(channelId);
        }
    }

    private void setUpdateListeners() {

    }
}
