package com.I2I.healthCare.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.I2I.healthCare.Index.PatientIndex;
import com.I2I.healthCare.Service.PatientIndexService;

/**
 * PatientSearchController works as End point for Patient module Search
 * operation from Elastic Search operations.
 * 
 * @author Subash_Sakthivel
 * @since 21.02
 *
 */
@RestController
@RefreshScope
@RequestMapping("/patientsearch")
public class PatientSearchController {

	@Lazy
	@Autowired
	public PatientSearchController(PatientIndexService patientIndexService) {
		this.patientIndexService = patientIndexService;
	}

	private final PatientIndexService patientIndexService;

	/**
	 * getAllUsers method is used to return all the Users Details From Elastic
	 * Search.
	 * 
	 * @return List of users Details
	 */
	@GetMapping("/")
	public List<PatientIndex> getAllUsers() {
		return patientIndexService.findAll();
	}

	/**
	 * getPatientById method is used to return Patient Details based on ID From
	 * Elastic Search.
	 * 
	 * @param pId
	 * @return PatientIndex
	 */
	@GetMapping("/{pId}")
	public PatientIndex getPatientById(@PathVariable long pId) {
		return patientIndexService.findById(pId);
	}

	/**
	 * getByUserName method is used to return Patient Details based on firstName and
	 * lastName from Elastic Search.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return List of Patient Details
	 */
	@GetMapping("/details")
	public SearchHits<PatientIndex> getByUserName(@RequestParam String firstName, @RequestParam String lastName) {
		return patientIndexService.getByUserName(firstName, lastName);
	}

	/**
	 * deleteById method is used to delete Patient Details based on pId.
	 * 
	 * @param pId
	 * @return String
	 */
	@DeleteMapping("/{pId}")
	public String deleteById(@PathVariable long pId) {
		return patientIndexService.delete(pId);
	}

	/**
	 * getUserSearchData method is used to return Patient Details based on firstName
	 * from Elastic Search.
	 * 
	 * @param searchData
	 * @return List of Patient Details
	 */
	@GetMapping("/search")
	public SearchHits<PatientIndex> getUserSearchData(@RequestParam String searchData) {
		return patientIndexService.getUserSearchData(searchData);
	}

	/**
	 * multiMatchQuery method is used to return Patient Details based on firstName
	 * and lastName from Elastic Search.
	 * 
	 * @param searchData
	 * @return List of Patient Details
	 */
	@GetMapping("/multiMatchQuery")
	public SearchHits<PatientIndex> multiMatchQuery(@RequestParam String searchData) {
		return patientIndexService.multiMatchQuery(searchData);
	}
}
