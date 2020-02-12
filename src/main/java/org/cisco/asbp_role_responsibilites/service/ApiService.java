/**
 * 
 */
package org.cisco.asbp_role_responsibilites.service;

import java.util.List;
import java.util.Map;

import org.cisco.asbp_role_responsibilites.entity.Api;
import org.cisco.asbp_role_responsibilites.entity.ApiModuleFeatureMapping;
import org.cisco.asbp_role_responsibilites.exception.ASBPException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Narendra
 *
 */
public interface ApiService {

	Api saveApi(Api api) throws ASBPException;

	Api updateApi(Api api) throws Exception;

	void deleteApi(Long apiId);

	void bulkAddApiswithRoles(MultipartFile file, List<String> headers) throws ASBPException;

	Page<Api> getAllApis(String searchColumn, String searchValue, Pageable pageable);

	Api getApi(Long apiId);

	Map<String, String> getBulkAddApiSTemplate();

	// api mappings
	ApiModuleFeatureMapping saveApiModuleFeatureMapping(ApiModuleFeatureMapping apiModuleFeatureMapping)
			throws ASBPException;

	ApiModuleFeatureMapping updateApiModuleFeatureMapping(ApiModuleFeatureMapping apiModuleFeatureMapping)
			throws Exception;

	void deleteApiModuleFeatureMapping(Long apiModuleFeatureMappingId);

	ApiModuleFeatureMapping getApiModuleFeatureMapping(Long apiModuleFeatureMappingId);

	Page<ApiModuleFeatureMapping> getAllApiModuleFeatureMappings(String searchColumn, String searchValue,
			Pageable pageable);

}
