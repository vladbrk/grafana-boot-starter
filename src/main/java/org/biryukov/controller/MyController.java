package org.biryukov.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.graphite.GraphiteMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.xml.ws.Provider;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
public class MyController {

    private int MAX_RANDOM = 5;

    @Autowired
    private GraphiteMeterRegistry meterRegistry;

    Map<Integer, Counter> randomNumCounter = new HashMap<>();
    Map<String, Counter> envNameCounter = new HashMap<>();

    private Counter registerRandomNumCounter(int num) {
        return Counter.builder("data.random")
                .tag("num", String.valueOf(num))
                .register(meterRegistry);
    }

    private Counter registerEnvNameCounter(String env) {
        return this.meterRegistry.counter("data.env", "name", env);
    }

    @RequestMapping("/env/{env}")
    public Data env(@PathVariable String env) {
        incEnvNameCounter(env);

        return new <String>Data(env, System.getenv().get(env));
    }

    @RequestMapping("/random")
    public Data data() {
        Random r = new Random();
        int next = r.nextInt(MAX_RANDOM);

        incRandomNumCounter(next);

        return new <Integer>Data("next", next);
    }

    private void incEnvNameCounter(String env) {
        if (envNameCounter.get(env) == null) {
            Counter counter = registerEnvNameCounter(env);
            envNameCounter.put(env, counter);
        }
        envNameCounter.get(env).increment();
    }

    private void incRandomNumCounter(int next) {
        if (randomNumCounter.get(next) == null) {
            Counter counter = registerRandomNumCounter(next);
            randomNumCounter.put(next, counter);
        }
        randomNumCounter.get(next).increment();
    }

}
