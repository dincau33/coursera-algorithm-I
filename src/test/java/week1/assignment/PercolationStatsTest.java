package week1.assignment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.*;

class PercolationStatsTest {

	@Test
	void failToCreatePercolationStatsIfGridSizeIsNegative() {
		assertThrows(IllegalArgumentException.class, () -> new PercolationStats(-1, 1));
		assertThrows(IllegalArgumentException.class, () -> new PercolationStats(0, 1));
	}

	@Test
	void failToCreatePercolationStatsIfTrialNumberIsNegative() {
		assertThrows(IllegalArgumentException.class, () -> new PercolationStats(1, 0));
		assertThrows(IllegalArgumentException.class, () -> new PercolationStats(1, -1));
	}

	@Test
	void percolationThresholdIsOneForOneCellGrid() {
		PercolationStats ps1 = new PercolationStats(1, 1);
		assertThat(ps1.mean()).isEqualTo(1.0);
	}

	@Test
	void percolationThresholdFor4CellsGrid() {
		PercolationStats ps2 = new PercolationStats(2, 10);
		assertThat(ps2.mean()).isCloseTo(0.625, within(0.125));
	}

	@Test
	void stddevIsNanForOneCellGrid(){
		PercolationStats ps1 = new PercolationStats(1, 1);
		assertThat(ps1.stddev()).isNaN();
	}

	@Test
	void stddevFor4CellsGrid() {
		PercolationStats ps2 = new PercolationStats(2, 10);
		assertThat(ps2.stddev()).isCloseTo(0.1, within(0.1));
	}

	@Test
	void confidenceLowIsNanForOneCellGrid(){
		PercolationStats ps1 = new PercolationStats(1, 1);
		assertThat(ps1.confidenceLo()).isNaN();
	}

	@Test
	void confidenceLowFor4CellsGrid() {
		PercolationStats ps2 = new PercolationStats(2, 10);
		assertThat(ps2.confidenceLo()).isCloseTo(0.6, within(0.1));
	}

	@Test
	void confidenceHighIsNanForOneCellGrid(){
		PercolationStats ps1 = new PercolationStats(1, 1);
		assertThat(ps1.confidenceHi()).isNaN();
	}

	@Test
	void confidenceHighFor4CellsGrid() {
		PercolationStats ps2 = new PercolationStats(2, 10);
		assertThat(ps2.confidenceHi()).isCloseTo(0.7, within(0.15));
	}
}