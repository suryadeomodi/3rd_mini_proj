package in.surya.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.surya.bindings.SearchRequest;
import in.surya.entity.EligibilityDetailsEntity;
import in.surya.repository.EligDtlsRepository;
import in.surya.response.SearchResponse;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private EligDtlsRepository repository;

	@Override
	public List<String> getPlanNames() {

		return repository.getUniquePlanNames();
	}

	@Override
	public List<String> getPlanStatus() {
		return repository.getUniqueStatus();
	}

	@Override
	public List<SearchResponse> searchPlans(SearchRequest request) {
		List<EligibilityDetailsEntity> eligRecords = null;

		List<SearchResponse> responses = new ArrayList<SearchResponse>();

		/*
		 * if (isSearchEmpty(request)) { eligRecords = repository.findAll(); }
		 */

		if (request == null) {
			eligRecords = repository.findAll();
			for (EligibilityDetailsEntity entity : eligRecords) {
				SearchResponse response = new SearchResponse();
				response.setBenefitAmt(entity.getBenefitAmt());
				response.setDenailReason(entity.getDenailReason());
				response.setEndDate(entity.getEndDate());
				response.setHolderName(entity.getHolderName());
				response.setHolderSsn(entity.getHolderName());
				responses.add(response);
			}
		}

		else {

			EligibilityDetailsEntity entity = new EligibilityDetailsEntity();

			String planName = request.getPlanName();
			String planStatus = request.getPlanStatus();
			LocalDate startDate = request.getStartDate();
			LocalDate endDate = request.getEndDate();

			if (planName != null && !planName.equals("")) {
				// add planName in where clause
				entity.setPlanName(planName);

			}
			if (planStatus != null && !planStatus.equals("")) {
				// add planStatus in where clause
				entity.setPlanStatus(planStatus);
			}
			if (startDate != null && endDate != null) {
				// add startDate and endDate in where clause
				entity.setStartDate(startDate);
				entity.setEndDate(endDate);

			}
			Example<EligibilityDetailsEntity> of = Example.of(entity);
			eligRecords = repository.findAll(of);

			for (EligibilityDetailsEntity detailsEntity : eligRecords) {
				SearchResponse response = new SearchResponse();
				BeanUtils.copyProperties(detailsEntity, response);
				responses.add(response);

			}

		}
		return responses;
	}

	private boolean isSearchEmpty(SearchRequest request) {

		if (request.getPlanName() != null && !request.getPlanName().equals("")) {
			return false;
		}

		if (request.getPlanStatus() != null && !request.getPlanStatus().equals("")) {
			return false;
		}

		if (request.getStartDate() != null && !request.getStartDate().equals("")) {
			return false;
		}

		if (request.getEndDate() != null && !request.getEndDate().equals("")) {
			return false;
		}

		return true;
	}

}
