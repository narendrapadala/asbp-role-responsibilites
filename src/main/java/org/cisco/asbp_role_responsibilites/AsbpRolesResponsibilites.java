package org.cisco.asbp_role_responsibilites;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({ "org.cisco.asbp_role_responsibilites" })
//@EnableCaching
public class AsbpRolesResponsibilites 
{
	private static final Logger LOG = LoggerFactory.getLogger(AsbpRolesResponsibilites.class);
	
    public static void main( String[] args )
    {
    	ApplicationContext ctx = SpringApplication.run(AsbpRolesResponsibilites.class, args);

		LOG.info("AsbpRolesResponsibilites  application is running..!");

		System.out.println("AsbpRolesResponsibilites application is running..!!");
    }
}
