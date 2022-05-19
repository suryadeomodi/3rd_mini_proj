package in.surya.service;

import java.util.List;

import in.surya.bindings.SearchRequest;
import in.surya.response.SearchResponse;

public interface ReportService {

	public List<String> getPlanNames();

	public List<String> getPlanStatus();

	public List<SearchResponse> searchPlans(SearchRequest request);

//	public void exportExcel(List<SearchResponse> requests);

//	public void exportPDF(List<SearchResponse> requests);

}
