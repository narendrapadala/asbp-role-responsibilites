package org.cisco.asbp_role_responsibilites.domain;

import java.util.List;

import org.cisco.asbp_role_responsibilites.entity.Module;
import org.cisco.asbp_role_responsibilites.entity.ModuleConfigMapping;
import org.cisco.asbp_role_responsibilites.entity.ModuleFeature;
import org.cisco.asbp_role_responsibilites.entity.Role;
import org.cisco.asbp_role_responsibilites.entity.RoleCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleAndResponsibilityMappingDto {

    private List<RoleAndResponsibilities> roleAndResponsibilities;

    private List<ModuleAndFeatures> allModuleAndFeatures;

    
	@Data
    @NoArgsConstructor
    public static class RoleAndResponsibilities 
    {
		private RoleCategory roleCategory;
		
		private List<Role> roles;
		
		List<ModuleConfigMapping> modules;
    }
	
	@Data
    @NoArgsConstructor
    public static class ModuleAndFeatures
    {
		private Module module;
		
		private List<ModuleFeature> moduleFeatures;
    }
}
