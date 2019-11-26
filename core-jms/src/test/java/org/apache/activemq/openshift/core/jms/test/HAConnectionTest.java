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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Connection;

@DisplayName("Testing client HA connection")
public class HAConnectionTest extends OpenshiftJmsTestBase {

   private static final Logger logger = LoggerFactory.getLogger(HAConnectionTest.class);

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
   public void testSingleConnection(TestInfo info) throws Exception {
      logger.info("begin test: " + info.getTestMethod());
      String host = getOpenshiftHost();
      String port = getOpenshiftPort();
      logger.info("got host: {} and port: {}", host, port);
      try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory("tcp://" + host + ":" + port)) {
         try (Connection conn = cf.createConnection()) {
            logger.info("got connection " + conn);
            ActiveMQConnection amqConn = (ActiveMQConnection) conn;
            TransportConfiguration tc = amqConn.getSessionFactory().getConnectorConfiguration();
            logger.info("tc is: {}", tc);
         }
      }
   }
}
