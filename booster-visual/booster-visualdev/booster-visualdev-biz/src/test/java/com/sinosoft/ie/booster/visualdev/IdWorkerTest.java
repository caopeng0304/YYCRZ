package com.sinosoft.ie.booster.visualdev;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.junit.jupiter.api.Test;

public class IdWorkerTest {

	@Test
	public void testGenDictInsertSql() {
		for (int i = 1; i <= 34; i++) {
			System.out.println(IdWorker.getId());
		}
	}
}
