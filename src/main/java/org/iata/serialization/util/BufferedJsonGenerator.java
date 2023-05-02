package org.iata.serialization.util;
/**
 * Copyright (C) 2022 Czech Technical University in Prague
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details. You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */


import cz.cvut.kbss.jsonld.serialization.JsonGenerator;

import java.util.Stack;

public class BufferedJsonGenerator implements JsonGenerator {

    private final StringBuilder buffer = new StringBuilder();

    private boolean firstElement = true;
    private boolean firstAttribute = true;
    private final Stack<NodeType> nodes = new Stack<>();

    private enum NodeType {
        ARRAY, OBJECT
    }

    @Override
    public void writeFieldName(String name) {
        if (!firstAttribute) {
            buffer.append(',');
        }
        firstAttribute = false;
        buffer.append('\"').append(name).append("\":");
    }

    @Override
    public void writeObjectStart() {
        if (!nodes.isEmpty() && nodes.peek() == NodeType.ARRAY) {
            if (!firstElement) {
                buffer.append(',');
            }
            this.firstElement = false;
        }
        buffer.append('{');
        nodes.push(NodeType.OBJECT);
        this.firstAttribute = true;
    }

    @Override
    public void writeObjectEnd() {
        buffer.append('}');
        nodes.pop();
    }

    @Override
    public void writeArrayStart() {
        if (!nodes.isEmpty() && nodes.peek() == NodeType.ARRAY) {
            if (!firstElement) {
                buffer.append(',');
            }
        }
        buffer.append('[');
        this.firstElement = true;
        nodes.push(NodeType.ARRAY);
    }

    @Override
    public void writeArrayEnd() {
        buffer.append(']');
        nodes.pop();
    }

    @Override
    public void writeNumber(Number number) {
        if (!nodes.isEmpty() && nodes.peek() == NodeType.ARRAY) {
            if (!firstElement) {
                buffer.append(',');
            }
            this.firstElement = false;
        }
        buffer.append(number);
    }

    @Override
    public void writeBoolean(boolean value) {
        if (!nodes.isEmpty() && nodes.peek() == NodeType.ARRAY) {
            if (!firstElement) {
                buffer.append(',');
            }
            this.firstElement = false;
        }
        buffer.append(value);
    }

    @Override
    public void writeNull() {
        if (!nodes.isEmpty() && nodes.peek() == NodeType.ARRAY) {
            if (!firstElement) {
                buffer.append(',');
            }
            this.firstElement = false;
        }
        buffer.append("null");
    }

    @Override
    public void writeString(String text) {
        if (!nodes.isEmpty() && nodes.peek() == NodeType.ARRAY) {
            if (!firstElement) {
                buffer.append(',');
            }
            this.firstElement = false;
        }
        buffer.append('\"').append(text).append('\"');
    }

    public String getResult() {
        return buffer.toString();
    }
}