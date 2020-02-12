/**
 * 
 */
package org.cisco.asbp_role_responsibilites.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cisco.asbp_common_utils.cu.model.GenericResponseDto;
import org.cisco.asbp_role_responsibilites.entity.Api;
import org.cisco.asbp_role_responsibilites.entity.ApiModuleFeatureMapping;
import org.cisco.asbp_role_responsibilites.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Narendra
 *
 */
@RestController
@RequestMapping("/apis")
public class ApiController {

	private ApiService apiService;

	@Autowired
	public void setApiService(ApiService apiService) {
		this.apiService = apiService;
	}

	@GetMapping("/all")
	public GenericResponseDto<Page<Api>> getAllApis(@RequestParam(required = false) String searchColumn,
			@RequestParam(required = false) String searchValue, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "25") int size, HttpServletRequest request) {
		return new GenericResponseDto.GenericResponseDtoBuilder<>(request,
				apiService.getAllApis(searchColumn, searchValue, PageRequest.of(page, size))).build();
	}

	@GetMapping("/getApi")
	public GenericResponseDto<Api> getApi(@RequestParam Long apiId, HttpServletRequest request) {

		return new GenericResponseDto.GenericResponseDtoBuilder<>(request, apiService.getApi(apiId)).build();
	}

	@PostMapping("/addApi")
	public GenericResponseDto<Api> addApi(@RequestBody Api api, HttpServletRequest request) throws Exception {
		return new GenericResponseDto.GenericResponseDtoBuilder<>("Api saved sucessfully", request,
				apiService.saveApi(api)).build();
	}

	@PutMapping("/updateApi")
	public GenericResponseDto<Api> updateApi(@RequestBody Api api, HttpServletRequest request) throws Exception {
		return new GenericResponseDto.GenericResponseDtoBuilder<>("Api updated sucessfully", request,
				apiService.updateApi(api)).build();
	}

	@DeleteMapping("/deleteApi")
	public GenericResponseDto<Boolean> deleteApi(@RequestParam Long apiId, HttpServletRequest request) {
		apiService.deleteApi(apiId);
		return new GenericResponseDto.GenericResponseDtoBuilder<>("Api deleted sucessfully", request, true).build();
	}

	@GetMapping("/template/addApi/bulk")
	public GenericResponseDto<Map<String, String>> getBulkAddApiSTemplate(HttpServletRequest request) throws Exception {

		return new GenericResponseDto.GenericResponseDtoBuilder<>(request, apiService.getBulkAddApiSTemplate()).build();
	}

	@PostMapping("/addApiModuleFeatureMapping")
	public GenericResponseDto<ApiModuleFeatureMapping> addApiModuleFeatureMapping(
			@RequestBody ApiModuleFeatureMapping apiModuleFeatureMapping, HttpServletRequest request) throws Exception {
		return new GenericResponseDto.GenericResponseDtoBuilder<>("ApiModuleFeatureMapping saved sucessfully", request,
				apiService.saveApiModuleFeatureMapping(apiModuleFeatureMapping)).build();
	}

	@PutMapping("/updateApiModuleFeatureMapping")
	public GenericResponseDto<ApiModuleFeatureMapping> updateApiModuleFeatureMapping(
			@RequestBody ApiModuleFeatureMapping apiModuleFeatureMapping, HttpServletRequest request) throws Exception {
		return new GenericResponseDto.GenericResponseDtoBuilder<>("ApiModuleFeatureMapping updated sucessfully",
				request, apiService.updateApiModuleFeatureMapping(apiModuleFeatureMapping)).build();
	}

	@GetMapping("/getAllApiModuleFeatureMappings")
	public GenericResponseDto<Page<ApiModuleFeatureMapping>> getAllApiModuleFeatureMappings(
			@RequestParam(required = false) String searchColumn, @RequestParam(required = false) String searchValue,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			HttpServletRequest request) {
		return new GenericResponseDto.GenericResponseDtoBuilder<>(request,
				apiService.getAllApiModuleFeatureMappings(searchColumn, searchValue, PageRequest.of(page, size)))
						.build();
	}

	@GetMapping("/getApiModuleFeatureMapping")
	public GenericResponseDto<ApiModuleFeatureMapping> getApiModuleFeatureMapping(
			@RequestParam Long apiModuleFeatureMappingId, HttpServletRequest request) {

		return new GenericResponseDto.GenericResponseDtoBuilder<>(request,
				apiService.getApiModuleFeatureMapping(apiModuleFeatureMappingId)).build();
	}
}
