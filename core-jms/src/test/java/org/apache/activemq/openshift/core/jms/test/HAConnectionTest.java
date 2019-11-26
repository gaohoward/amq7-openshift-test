/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.activemq.openshift.core.jms.test;

import org.apache.activemq.artemis.api.core.TransportConfiguration;
import org.apache.activemq.artemis.jms.client.ActiveMQConnection;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import javax.jms.Connection;

@DisplayName("Testing client HA connection")
public class HAConnectionTest extends OpenshiftJmsTestBase {

   @BeforeAll
   public static void beforeAll(TestInfo info) {
   }

   @BeforeEach
   public void setUp() throws Exception {
      System.out.println("before each...");
   }

   @AfterEach
   public void tearDown() throws Exception {
      System.out.println("after each");
   }

   @Test
   public void testSingleConnection() throws Exception {
      System.out.println("begin test");
      String host = getOpenshiftHost();
      String port = getOpenshiftPort();
      System.out.println("got host: " + host + " port: " + port);
      try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory("tcp://" + host + ":" + port)) {
         try (Connection conn = cf.createConnection()) {
            System.out.println("got connection " + conn);
            ActiveMQConnection amqConn = (ActiveMQConnection) conn;
            TransportConfiguration tc = amqConn.getSessionFactory().getConnectorConfiguration();
            System.out.println("tc is: " + tc);
         }
      }
   }
}
