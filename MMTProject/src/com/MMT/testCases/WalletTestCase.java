package com.MMT.testCases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.MMT.bean.Wallet;
import com.MMT.helper.WalletHelperMMT;

public class WalletTestCase {
Wallet wl=null;


	@Before
	public void setUp() throws Exception {
		wl=new Wallet("user1",800);
		
	}

	@After
	public void tearDown() throws Exception {
		wl=null;
	}

	@Test
	public void testdisplayWallet() {
		assertEquals(800,wl.getWalletBalance());
	}

}
