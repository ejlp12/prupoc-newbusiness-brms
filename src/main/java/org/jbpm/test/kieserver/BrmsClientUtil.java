package org.jbpm.test.kieserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.kie.api.KieServices;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ExecutionResults;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.KieContainerResourceList;
import org.kie.server.api.model.KieServerInfo;
import org.kie.server.api.model.ReleaseId;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.api.model.ServiceResponse.ResponseType;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;

import prupoc.newbusiness.CoverageData;

public class BrmsClientUtil {

	private static KieServicesClient kieServicesClient = null;
	private static ResourceBundle properties;
	private static String containerId;
	
	static {
		properties = ResourceBundle.getBundle("Application");
		containerId = properties.getString("brms.containerId");
	}

    public static KieServicesClient getKieServicesClient() {
    	return kieServicesClient;
    }
    
    public static String getContainerId() {
    	return containerId;
    }
    	
    public static void init() {
        
        final String serverUrl = properties.getString("brms.kieServerUrl");
        final String user = properties.getString("brms.username");;
        final String password = properties.getString("brms.password");;
        final long timeoutMilis = Long.parseLong(properties.getString("brms.timeoutMilis"));
        final MarshallingFormat FORMAT = MarshallingFormat.JAXB; //MarshallingFormat.JSON
        
        final String projectRelease = properties.getString("brms.projectRelease");


        
        KieServicesConfiguration configuration = KieServicesFactory.newRestConfiguration(serverUrl, user, password, timeoutMilis);
        configuration.setMarshallingFormat(FORMAT);
        
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(CoverageData.class);
        configuration.addJaxbClasses(classes);        

        kieServicesClient =  KieServicesFactory.newKieServicesClient(configuration);
        
        //disposeContainer(containerId);
        checkAndDeployContainter(containerId, projectRelease);
        //listCapabilities();
        
    }
    
    public static void checkAndDeployContainter(String containerId, String projectRelease) {
        boolean deployContainer = true;
        KieContainerResourceList containers = kieServicesClient.listContainers().getResult();
        // check if the container is not yet deployed, if not deploy it
        if (containers != null) {
        	if (containers.getContainers().size() == 0) {
        		System.out.println("\t######### No containers available...");
        	} else {		
                for (KieContainerResource kieContainerResource : containers.getContainers()) {
                    if (kieContainerResource.getContainerId().equals(containerId)) {
                        System.out.println("\t######### Found container '" + containerId + "' skipping deployment...");
                        System.out.println("\t######### Container status " + kieContainerResource.getStatus());
                        deployContainer = false;
                        break;
                    }
                }	
        	}        	
        } else {
        	System.out.println("\t######### No containers available...");
        }
        
        // deploy container if not there yet        
        if (deployContainer) {
        	System.out.println("\t######### EJLP12: Container containerId='" + containerId + "' not found");
            System.out.println("\t######### Deploying container " + containerId);
            
            final String[] releaseParts = projectRelease.split(":");
            final ReleaseId releaseId = new ReleaseId(releaseParts[0], releaseParts[1], releaseParts[2]);
            
            KieContainerResource resource = new KieContainerResource(containerId, releaseId); 
            ServiceResponse<KieContainerResource> createResponse = kieServicesClient.createContainer(containerId, resource);
    		if(createResponse.getType() == ResponseType.FAILURE) {
	    		System.out.println("\t######### Error creating " + containerId + ". Message: ");
	    		System.out.println(createResponse.getMsg());
	    		return;
	    	}
        }
		
	}
    
    public static void disposeContainer(String containerId) {

    	ServiceResponse<Void> responseDispose = kieServicesClient.disposeContainer(containerId);
    	if (responseDispose.getType() == ResponseType.FAILURE) {
    		System.out.println("\t######### Error disposing " + containerId + ". Message: ");
    		System.out.println(responseDispose.getMsg());
    		return;
    	}
    	System.out.println("\t######### Success Disposing container " + containerId);
  
    }

	public static void listCapabilities() {
        KieServerInfo serverInfo = kieServicesClient.getServerInfo().getResult();
        System.out.print("\t######### Server capabilities:");
        for(String capability: serverInfo.getCapabilities()) {
            System.out.print(" " + capability);
        }
        System.out.println();
    } 
}