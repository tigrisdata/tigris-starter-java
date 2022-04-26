/*
 * Copyright 2022 Tigris Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tigrisdata.starter.spring;

import com.tigrisdata.db.client.TigrisClient;
import com.tigrisdata.db.client.TigrisDatabase;
import com.tigrisdata.starter.collections.Order;
import com.tigrisdata.starter.collections.Product;
import com.tigrisdata.starter.collections.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

public class TigrisDBInitializer implements CommandLineRunner {

  private final TigrisClient tigrisDBClient;
  private final String dbName;

  private static final Logger log = LoggerFactory.getLogger(TigrisDBInitializer.class);

  public TigrisDBInitializer(TigrisClient tigrisDBClient, String dbName) {
    this.tigrisDBClient = tigrisDBClient;
    this.dbName = dbName;
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("createDbIfNotExists db: {}", dbName);
    TigrisDatabase tigrisDatabase = tigrisDBClient.createDatabaseIfNotExists(dbName);
    log.info("creating collections on db {}", dbName);
    tigrisDatabase.createOrUpdateCollections(User.class, Product.class, Order.class);
    log.info("Finished initializing TigrisDB");
  }
}
