/*
 * Open Source Software published under the Apache Licence, Version 2.0.
 */

package io.github.vocabhunter.gui.event;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CommandLineEventSource implements ExternalEventSource {
    private final List<ExternalOpenFileEvent> events;

    public CommandLineEventSource(final String... args) {
        events = Arrays.stream(args)
                .map(Paths::get)
                .map(ExternalOpenFileEvent::new)
                .limit(1)
                .collect(toList());
    }

    @Override
    public void setListener(final ExternalEventListener listener) {
        events.forEach(listener::fireOpenFileEvent);
    }
}