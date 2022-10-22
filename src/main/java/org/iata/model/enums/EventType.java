package org.iata.model.enums;

import org.iata.cargo.Vocabulary;

/**
 * Enum containing all the existing types of Notification Event types
 */
public enum EventType {

    OBJECT_CREATED("OBJECT_CREATED"),
    OBJECT_UPDATED("OBJECT_UPDATED");

    private final String className;

    EventType(String className) {
        this.className = className;
    }

    public String getEventType() {
        return this.className;
    }

}