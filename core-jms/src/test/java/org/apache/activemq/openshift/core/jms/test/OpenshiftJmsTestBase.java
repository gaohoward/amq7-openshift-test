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

import org.apache.activemq.openshift.test.commons.ConfigUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInfo;

import java.util.Properties;

public class OpenshiftJmsTestBase {

   public static final String OPENSHIFT_HOST = "openshift.host";
   public static final String OPENSHIFT_PORT = "openshift.port";

   protected static Properties rootProperties;

   @BeforeAll
   public static void init(TestInfo info) throws Exception {
      rootProperties = ConfigUtil.loadProperties("base.properties");
   }

   @AfterAll
   public static void cleanup() throws Exception {

   }

   protected String getOpenshiftHost() {
      return getTestProperty(OPENSHIFT_HOST, "172.30.9.135");
   }

   protected String getOpenshiftPort() {
      return getTestProperty(OPENSHIFT_PORT, "61616");
   }

   private String getTestProperty(String key, String defValue) {
      String value = rootProperties.getProperty(key);
      if (value != null) {
         return value;
      }
      return defValue;
   }
}
