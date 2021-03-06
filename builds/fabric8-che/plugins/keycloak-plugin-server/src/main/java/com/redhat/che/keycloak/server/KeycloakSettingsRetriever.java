/*
 * Copyright (c) 2016-2017 Red Hat, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package com.redhat.che.keycloak.server;

import com.redhat.che.keycloak.shared.KeycloakSettings;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class KeycloakSettingsRetriever {
  private static final Logger LOG = LoggerFactory.getLogger(KeycloakSettingsRetriever.class);

  private final String apiEndpoint;

  @Inject
  public KeycloakSettingsRetriever(@Named("che.api") String apiEndpoint) {
    this.apiEndpoint = apiEndpoint;
    LOG.debug("Endpoint = {0}", apiEndpoint);
    KeycloakSettings.pullFromApiEndpointIfNecessary(apiEndpoint);
  }

  public Map<String, String> getSettings() {
    KeycloakSettings.pullFromApiEndpointIfNecessary(apiEndpoint);
    return KeycloakSettings.get();
  }
}
