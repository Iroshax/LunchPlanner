package com.iro.lunchplanner;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class LunchPlannerApplicationTests {

	@Test
	void contextLoads() {
	}

	/*@Test
	public void shouldReturnPortfolioData() {

		BigDecimal portfolioValue = new BigDecimal("43213");
		Portfolio expectedPortfolio = new Portfolio(1,"John Doe", "1001", portfolioValue, 23.4, "Safe");

		when(portfolioRepository.findById(anyInt())).thenReturn(Optional.of(expectedPortfolio));
		PortfolioDto actualPortfolio = portfolioService.getPortfolio(1);
		// Assert
		assertEquals(expectedPortfolio.getCustomerName(), actualPortfolio.getCustomerName());
	}
	*/

}
