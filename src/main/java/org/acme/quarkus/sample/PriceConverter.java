package org.acme.quarkus.sample;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

/**
 * A bean consuming data from the "prices" MQTT topic and applying some conversion.
 * The result is pushed to the "my-data-stream" stream which is an in-memory stream.
 */
@Slf4j
@ApplicationScoped
public class PriceConverter {

    private static final double CONVERSION_RATE = 0.88;

    @Incoming("prices")
    @Outgoing("my-data-stream")
    @Broadcast
    public double process(byte[] priceRaw) {
        int priceInUsd = Integer.valueOf(new String(priceRaw));
        log.info("Receiving price: {}", priceInUsd);
        double convPrice = priceInUsd * CONVERSION_RATE;
        log.info("Converted price: {}", convPrice);
        return convPrice;
    }

}
