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

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.openshift.test.commons.ConfigUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class OpenshiftJmsTestBase {

   protected static final Logger logger = LoggerFactory.getLogger(OpenshiftJmsTestBase.class);

   public static final String OPENSHIFT_HOST = "openshift.exported.host";
   public static final String OPENSHIFT_PORT = "openshift.exported.port";

   protected static Properties rootProperties;

   protected static String host;
   protected static String port;
   protected static String normalUrl;
   protected static String haUrl;

   protected static Map<String, ActiveMQConnectionFactory> factories = new HashMap<>();

   @BeforeAll
   public static void init(TestInfo info) throws Exception {
      rootProperties = ConfigUtil.loadProperties("base.properties");
      host = getOpenshiftHost();
      port = getOpenshiftPort();
      normalUrl = "tcp://" + host + ":" + port;
      haUrl = normalUrl + "?ha=true";
      logger.info("got host: {} and port: {}", host, port);
   }

   @AfterAll
   public static void cleanup() throws Exception {
      for (ActiveMQConnectionFactory cf : factories.values()) {
         cf.close();
      }
   }

   protected static String getOpenshiftHost() {
      return getTestProperty(OPENSHIFT_HOST, "localhost");
   }

   protected static String getOpenshiftPort() {
      return getTestProperty(OPENSHIFT_PORT, "61616");
   }

   private static String getTestProperty(String key, String defValue) {
      String value = rootProperties.getProperty(key);
      if (value != null) {
         return value;
      }
      return defValue;
   }

   protected ActiveMQConnectionFactory getConnectionFactory(String url) {
      ActiveMQConnectionFactory cf = factories.get(url);
      if (cf == null) {
         cf = new ActiveMQConnectionFactory(url);
         factories.put(url, cf);
      }
      return cf;
   }
}
