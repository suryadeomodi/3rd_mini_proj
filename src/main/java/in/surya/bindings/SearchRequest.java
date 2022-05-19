package in.surya.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchRequest {

	private String planName;
	private String planStatus;
	private LocalDate startDate;
	private LocalDate endDate;

}
