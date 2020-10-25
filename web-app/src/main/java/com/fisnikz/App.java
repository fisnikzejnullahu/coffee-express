package com.fisnikz;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.ext.ContextResolver;
import java.util.HashSet;
import java.util.Set;

import static javax.json.bind.config.PropertyNamingStrategy.LOWER_CASE_WITH_DASHES;

/**
 * Default JAX-RS application listening on /mvc
 */
@ApplicationPath("/mvc")
public class App extends Application {

}