/*******************************************************************************
 * Copyright (c) 2017 Red Hat Inc and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.kura.simulator.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.google.common.base.Function;
import org.apache.commons.lang3.StringUtils;

public final class Topic {

    private static final Segment CONTROL = new Segment() {

        @Override
        public String render(final Function<String, String> replaceMapper) {
            return "$EDC";
        }

        @Override
        public String toString() {
            return render(null);
        }
    };

    private static final Segment WILDCARD = new Segment() {

        @Override
        public String render(final Function<String, String> replaceMapper) {
            return "#";
        }

        @Override
        public String toString() {
            return render(null);
        }
    };

	private final List<Segment> segments;

	private final Map<String, String> context = new HashMap<>();

	private Topic(final List<Segment> segments) {
        this.segments = segments;
    }

	private Topic(final Segment... segments) {
        this.segments = Arrays.asList(segments);
    }

	public List<Segment> getSegments() {
        return Collections.unmodifiableList(this.segments);
    }

	public String renderSegment(final Segment segment, final Map<String, String> context) {
        return segment.render(key -> {
            if (context.containsKey(key)) {
                return context.get(key);
            }
            return Topic.this.context.get(key);
        });
    }

	public String render(final Map<String, String> context) {
        return renderInternal(this.segments, context);
    }

	/**
     * Renders all tokens from {@code fromIndex} (inclusive) to {@code toIndex}
     * (exclusive)
     *
     * @param fromIndex
     *            first item to render
     * @param toIndex
     *            first item <strong>not</strong> to render
     * @param context
     *            the additional context to use
     * @return the rendered string
     */
    public String render(final int fromIndex, final int toIndex, final Map<String, String> context) {
        return renderInternal(this.segments.subList(fromIndex, toIndex), context);
    }

	public String render() {
        return renderInternal(this.segments, Collections.emptyMap());
    }

	/**
     * Renders all tokens from {@code fromIndex} (inclusive) to {@code toIndex}
     * (exclusive)
     *
     * @param fromIndex
     *            first item to render
     * @param toIndex
     *            first item <strong>not</strong> to render
     * @return the rendered string
     */
    public String render(final int fromIndex, final int toIndex) {
        return renderInternal(this.segments.subList(fromIndex, toIndex), Collections.emptyMap());
    }

	private String renderInternal(final List<Segment> segments, final Map<String, String> context) {
        return segments.stream().map(seg -> renderSegment(seg, context)).collect(Collectors.joining("/"));
    }

	public static Topic from(final List<Segment> segments) {
        Objects.requireNonNull(segments);

        return new Topic(segments);
    }

	public static Topic fromString(final String topic) {
        return from(Arrays.stream(topic.split("\\/")).map(Segment::plain).collect(Collectors.toList()));
    }

	public static Topic reply(final String requesterClientId, final String requestId) {
        return new Topic(Segment.control(), Segment.account(), Segment.plain(requesterClientId), Segment.replace("application-id"), Segment.plain("REPLY"),
                Segment.plain(requestId));
    }

	public static Topic notify(final String requesterClientId, final String... resource) {
        final List<Segment> s = new LinkedList<>();
        s.add(Segment.control());
        s.add(Segment.account());
        s.add(Segment.plain(requesterClientId));
        s.add(Segment.replace("application-id"));
        s.add(Segment.plain("NOTIFY"));
        s.addAll(Segment.plain(resource));
        return new Topic(s);
    }

	/**
     * Get the topic for an application
     * <p>
     * <strong>Note:</strong> This is a topic for a control application, for sending data use a data application topic created by {@link #data(String)}.
     * </p>
     *
     * @param application
     *            the application ID
     * @return a new topic
     */
    public static Topic application(final String application) {
        return new Topic(Segment.control(), Segment.account(), Segment.clientId(), Segment.plain(application));
    }

	public static Topic device(final String localTopic) {
        return new Topic(Segment.control(), Segment.account(), Segment.clientId(), Segment.raw(localTopic));
    }

	public static Topic data(final String dataTopic) {
        if (StringUtils.isEmpty(dataTopic)) {
            throw new IllegalArgumentException("Data topic must not be empty");
        }
        if (StringUtils.contains(dataTopic, "#") || StringUtils.contains(dataTopic, "+")) {
            throw new IllegalArgumentException("Data topic must not contain wildcards");
        }
        if (StringUtils.startsWith(dataTopic, "/")) {
            throw new IllegalArgumentException("Data topic must not start with /");
        }
        return new Topic(Segment.account(), Segment.clientId(), Segment.applicationId(), Segment.raw(dataTopic));
    }

	public Topic append(final Segment segment) {
        final List<Segment> segs = new ArrayList<>(this.segments.size() + 1);
        segs.addAll(this.segments);
        segs.add(segment);
        return new Topic(segs);
    }

	/**
     * Attach information to the local topic context
     *
     * @param key
     *            the key of the value
     * @param value
     *            the value to attach
     * @return the current instance
     */
    public Topic attach(final String key, final String value) {
        this.context.put(key, value);
        return this;
    }

	public Topic attachAll(final Map<String, String> topicContext) {
        this.context.putAll(topicContext);
        return this;
    }

	public Topic localize(final Topic otherTopic) {
        return localize(otherTopic, Collections.emptyMap());
    }

	public Topic localize(final Topic otherTopic, final Map<String, String> topicContext) {
        final LinkedList<Segment> newTopic = new LinkedList<>(getSegments());

        for (final Segment seg : otherTopic.getSegments()) {
            final String segValue1 = renderSegment(newTopic.removeFirst(), topicContext);
            final String segValue2 = otherTopic.renderSegment(seg, topicContext);

            if (!segValue1.equals(segValue2)) {
                return null;
            }
        }

        return Topic.from(newTopic);
    }

	@Override
    public String toString() {
        return this.segments.stream().map(Object::toString).collect(Collectors.joining("/"));
    }

	public Map<String, String> getContext() {
        return Collections.unmodifiableMap(this.context);
    }

    public interface Segment {

        String render(Function<String, String> replaceMapper);

        default String render() {
            return render(key -> null);
        }

        static Segment control() {
            return CONTROL;
        }

        static Segment wildcard() {
            return WILDCARD;
        }

        static Segment plain(final String segment) {
            Objects.requireNonNull(segment);
            if (StringUtils.isEmpty(segment) || StringUtils.contains(segment, "/")) {
                throw new IllegalArgumentException(String.format("Illegal argument: '%s'", segment));
            }

            return raw(segment);
        }

        static List<Segment> plain(final String... segment) {
            Objects.requireNonNull(segment);
            return Arrays.stream(segment).map(Segment::plain).collect(Collectors.toList());
        }

        static Segment raw(final String raw) {
            Objects.requireNonNull(raw);

            return new Segment() {

                @Override
                public String render(final Function<String, String> replaceMapper) {
                    return raw;
                }

                @Override
                public String toString() {
                    return render(null);
                }
            };
        }

        static Segment replace(final String key) {
            return new ReplaceSegment(key);
        }

        static Segment account() {
            return replace("account-name");
        }

        static Segment clientId() {
            return replace("client-id");
        }

        static Segment applicationId() {
            return replace("application-id");
        }
    }

    private static class ReplaceSegment implements Segment {

        private final String key;

        public ReplaceSegment(final String key) {
            this.key = key;
        }

        @Override
        public String render(final Function<String, String> replaceMapper) {
            final String value = replaceMapper.apply(this.key);
            if (value == null || StringUtils.isEmpty(value)) {
                throw new IllegalStateException(
                        String.format("Unable to replace segment '%s', no value found", this.key));
            }
            return value;
        }

        @Override
        public String toString() {
            return new StringBuilder().append("<").append(this.key).append(">").toString();
        }

    }
}
