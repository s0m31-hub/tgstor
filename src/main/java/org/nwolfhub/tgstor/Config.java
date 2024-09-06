package org.nwolfhub.tgstor;

import it.tdlight.Init;
import it.tdlight.Log;
import it.tdlight.Slf4JLogMessageHandler;
import it.tdlight.client.*;
import it.tdlight.util.UnsupportedNativeLibraryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class Config {
    private static final Logger log = LoggerFactory.getLogger(Config.class);

    @Bean
    SimpleTelegramClient getClient(Environment environment) {
        try {
            Init.init();
            Log.setLogMessageHandler(1, new Slf4JLogMessageHandler());
            try(SimpleTelegramClientFactory clientFactory = new SimpleTelegramClientFactory()) {
                APIToken token = new APIToken(Integer.parseInt(environment.getProperty("telegram.app_id")), environment.getProperty("telegram.api_hash"));
                TDLibSettings settings = TDLibSettings.create(token);
                Path sessionPath = Paths.get(environment.getProperty("telegram.session.path"));
                settings.setDatabaseDirectoryPath(sessionPath.resolve("data"));
                settings.setDownloadedFilesDirectoryPath(sessionPath.resolve("downloads"));
                settings.setDeviceModel(InetAddress.getLocalHost().getHostName());
                settings.setApplicationVersion("TGSTOR");
                SimpleTelegramClientBuilder clientBuilder = clientFactory.builder(settings);
                SimpleAuthenticationSupplier<?> authenticationData = AuthenticationSupplier.user(environment.getProperty("telegram.session.phone"));
                return clientBuilder.build(authenticationData);
            } catch (UnknownHostException e) {
                log.error("Failed to determine system host");
                throw new RuntimeException(e);
            }
        } catch (UnsupportedNativeLibraryException e) {
            log.error("Failed to init: {}", e.toString());
            throw new RuntimeException(e);
        }

    }
}
