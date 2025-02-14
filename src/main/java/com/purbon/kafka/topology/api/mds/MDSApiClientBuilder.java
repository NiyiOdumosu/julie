package com.purbon.kafka.topology.api.mds;

import com.purbon.kafka.topology.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class MDSApiClientBuilder {

  private static final Logger LOGGER = LogManager.getLogger(MDSApiClientBuilder.class);

  private Configuration config;

  public MDSApiClientBuilder(Configuration config) {
    this.config = config;
  }

  public MDSApiClient build() {
    String mdsServer = config.getMdsServer();

    MDSApiClient apiClient = new MDSApiClient(mdsServer, Optional.of(config));
    // Pass Cluster IDS
    apiClient.setKafkaClusterId(config.getKafkaClusterId());
    apiClient.setSchemaRegistryClusterID(config.getSchemaRegistryClusterId());
    apiClient.setConnectClusterID(config.getKafkaConnectClusterId());

    LOGGER.debug(String.format("Connecting to an MDS server at %s", mdsServer));
    return apiClient;
  }

  public void configure(Configuration config) {
    this.config = config;
  }
}
