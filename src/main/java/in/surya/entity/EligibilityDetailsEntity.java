package in.surya.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ELIGIBILITY_DTLS")
@Data
public class EligibilityDetailsEntity {

	@Id
	@GeneratedValue
	@Column(name = "ELIG_ID")
	private Integer eligId;

	@Column(name = "CASE_NUM")
	private Long caseNum;

	@Column(name = "PLAN_NAME")
	private String planName;

	@Column(name = "PLAN_STATUS")
	private String planStatus;

	@Column(name = "BENEFIT_AMT")
	private Double benefitAmt;

	@Column(name = "START_DATE")
	private LocalDate startDate;

	@Column(name = "END_DATE")
	private LocalDate endDate;

	@Column(name = "DENIAL_REASN")
	private String denailReason;

	@Column(name = "HOLDER_NAME")
	private String holderName;

	@Column(name = "HOLDER_SSN")
	private String holderSsn;

}
