/**
 * 
 */
package org.cisco.asbp_role_responsibilites.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

/**
 * @author rakesh
 *
 */
@Component
@Getter
public class EnvironmentProperties {
	
	@Value("${redis.host:}")
	private String redisHost;

	@Value("${redis.port:}")
	private String redisPort;
	
	@Value("${server.servlet.session.timeout:0}")
	private int sessionTimeout;
	
	@Value("${ems.redis.config.cluster.enabled:false}")
	private boolean redisClusterEnabled;

	@Value("${ems.redis.config.password:}")
	private String redisPassword;
	
	
}
