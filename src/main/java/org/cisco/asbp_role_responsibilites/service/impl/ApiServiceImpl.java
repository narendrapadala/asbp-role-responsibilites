package org.cisco.asbp_role_responsibilites.service.impl;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.cisco.asbp_common_utils.cu.model.CommonStatus;
import org.cisco.asbp_role_responsibilites.entity.Api;
import org.cisco.asbp_role_responsibilites.entity.ApiModuleFeatureMapping;
import org.cisco.asbp_role_responsibilites.entity.ModuleFeature;
import org.cisco.asbp_role_responsibilites.exception.ASBPException;
import org.cisco.asbp_role_responsibilites.repository.ApiModuleFeatureMappingRepository;
import org.cisco.asbp_role_responsibilites.repository.ApiRepository;
import org.cisco.asbp_role_responsibilites.repository.ModuleFeatureRepository;
import org.cisco.asbp_role_responsibilites.service.ApiService;
import org.cisco.asbp_role_responsibilites.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Narendra
 *
 */
@Service
@Transactional
@SuppressWarnings("unused")
public class ApiServiceImpl implements ApiService {

	private static Logger log = LoggerFactory.getLogger(ApiServiceImpl.class);
	@Autowired
	private ApiRepository apiRepo;
	
	@Autowired
	private ApiModuleFeatureMappingRepository apiModuleFeatureMappingRepo;
	
	@Autowired
	private ModuleFeatureRepository moduleFeatureRepo;

	@Override
	public Api saveApi(Api api) {
		api.setCreatedAt(System.currentTimeMillis());
		api.setCreatedBy(1L);
		api.setModifiedBy(1L);
		api.setModifiedAt(System.currentTimeMillis());
		api.setStatus(CommonStatus.ENABLED);
		return apiRepo.save(api);
	}

	@Override
	public Api updateApi(Api api) throws Exception {
		Api ex_api = apiRepo.findById(api.getId())
				.orElseThrow(() -> new Exception("api with id " + api.getId() + " doesn't exist"));
		if (StringUtils.isNotEmpty(api.getName()))
			ex_api.setName(api.getName());
		if (StringUtils.isNotEmpty(api.getVersion()))
			ex_api.setVersion(api.getVersion());
		if (StringUtils.isNotEmpty(api.getDescription()))
			ex_api.setDescription(api.getDescription());
		ex_api.setModifiedBy(1L);
		ex_api.setModifiedAt(System.currentTimeMillis());
		return apiRepo.save(ex_api);
	}

	@Override
	public void deleteApi(Long apiId) {
		apiRepo.deleteById(apiId);
	}


	@Override
	public Page<Api> getAllApis(String searchColumn, String searchValue, Pageable pageable) {
		if (StringUtils.isEmpty(searchColumn)) {
			return apiRepo.findAll(pageable);
		} else {
			//Specification.where(getFilterQuery(searchColumn, searchValue)), 
			return apiRepo.findAll(pageable);
		}
	}

	public Specification<Api> getFilterQuery(String searchQuery, String searchValue) {
		return (root, query, builder) -> builder.like(root.get(searchQuery), "%" + searchValue + "%");
	}

	@Transactional
	@Override
	public Api getApi(Long apiId) {
		// return
		return apiRepo.findFirstById(apiId);
	}

	@Override
	public Map<String, String> getBulkAddApiSTemplate() {
		String header = Constants.BULK_APIS_HEADERS;
		Map<String, String> responseMap = new LinkedHashMap<>();
		String[] cols = header.split(",");
		responseMap.put(cols[0], "/api/service");
		responseMap.put(cols[1], "GET");
		responseMap.put(cols[2], "test api");
		responseMap.put(cols[3], "SUPER_ADMIN,ADMIN,ANY_USER,GUEST");
		return responseMap;
	}

	@Override
	public void bulkAddApiswithRoles(MultipartFile file, List<String> headers) throws ASBPException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ApiModuleFeatureMapping saveApiModuleFeatureMapping(ApiModuleFeatureMapping apiModuleFeatureMapping)
			throws ASBPException {
		
		Api api = apiRepo.findById(apiModuleFeatureMapping.getApi().getId())
				.orElseThrow(() -> new ASBPException("Api with id " + apiModuleFeatureMapping.getApi().getId() + " doesn't exist"));

		ModuleFeature moduleFeature = moduleFeatureRepo.findById(apiModuleFeatureMapping.getModuleFeature().getId())
				.orElseThrow(() -> new ASBPException("ModuleFeature with id " + apiModuleFeatureMapping.getModuleFeature().getId() + " doesn't exist"));
	
		apiModuleFeatureMapping.setCreatedAt(System.currentTimeMillis());
		apiModuleFeatureMapping.setCreatedBy(1L);
		apiModuleFeatureMapping.setModifiedBy(1L);
		apiModuleFeatureMapping.setModifiedAt(System.currentTimeMillis());
		//return
		return apiModuleFeatureMappingRepo.save(apiModuleFeatureMapping);
	}

	@Override
	public ApiModuleFeatureMapping updateApiModuleFeatureMapping(ApiModuleFeatureMapping apiModuleFeatureMapping)
			throws Exception {
		
		Api api = apiRepo.findById(apiModuleFeatureMapping.getApi().getId())
				.orElseThrow(() -> new Exception("Api with id " + apiModuleFeatureMapping.getApi().getId() + " doesn't exist"));

		ModuleFeature moduleFeature = moduleFeatureRepo.findById(apiModuleFeatureMapping.getModuleFeature().getId())
				.orElseThrow(() -> new Exception("ModuleFeature with id " + apiModuleFeatureMapping.getModuleFeature().getId() + " doesn't exist"));
		
		apiModuleFeatureMapping.setModifiedBy(1L);
		apiModuleFeatureMapping.setModifiedAt(System.currentTimeMillis());
		//return
		return apiModuleFeatureMappingRepo.save(apiModuleFeatureMapping);
	}

	@Override
	public void deleteApiModuleFeatureMapping(Long apiModuleFeatureMappingId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ApiModuleFeatureMapping getApiModuleFeatureMapping(Long apiModuleFeatureMappingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ApiModuleFeatureMapping> getAllApiModuleFeatureMappings(String searchColumn, String searchValue,
			Pageable pageable) {
		return apiModuleFeatureMappingRepo.findAll(pageable);
	}

}
