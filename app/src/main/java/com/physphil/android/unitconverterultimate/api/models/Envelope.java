/*
 * Copyright 2018 Phil Shadlyn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.physphil.android.unitconverterultimate.api.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Model for XML returned from currency API
 */
@Root(name="Envelope")
public class Envelope {

    @Element(name="Sender", required=false)
    Sender sender;

    @Element(name="Cube", required=false)
    Cube cube;

    @Element(name="subject", required=false)
    String subject;

    public Sender getSender() {return this.sender;}
    public void setSender(Sender value) {this.sender = value;}

    public Cube getCube() {return this.cube;}
    public void setCube(Cube value) {this.cube = value;}

    public String getSubject() {return this.subject;}
    public void setSubject(String value) {this.subject = value;}

    public static class Sender {

        @Element(name="name", required=false)
        String name;

        public String getName() {return this.name;}
        public void setName(String value) {this.name = value;}

    }

    public static class Cube {

        @ElementList(name="Cube", required=false, entry="Cube", inline=true)
        List<Cube> cube;

        @Attribute(name="rate", required=false)
        Double rate;

        @Attribute(name="currency", required=false)
        String currency;

        @Attribute(name="time", required=false)
        String time;

        public List<Cube> getCube() {return this.cube;}
        public void setCube(List<Cube> value) {this.cube = value;}

        public Double getRate() {return this.rate;}
        public void setRate(Double value) {this.rate = value;}

        public String getCurrency() {return this.currency;}
        public void setCurrency(String value) {this.currency = value;}

        public String getTime() {return this.time;}
        public void setTime(String value) {this.time = value;}

    }

}