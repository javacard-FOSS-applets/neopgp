// SPDX-License-Identifier: GPL-3.0-or-later
package cc.walle.neopgp;

import static org.junit.Assert.*;
import javax.smartcardio.ResponseAPDU;
import javacard.framework.ISO7816;
import org.junit.Test;

public class GenerateKeyTest extends JcardsimTestCase {
	@Test public void generateSignatureKey() {
		admin();
		assertResponseData(
			"7F4982010981820100B3F92F416BCB2129AFFD2AC35CD0A1D0D2CFC6EEDE7253" +
			"A127BBA2FCAFD2BB87FF72A1B34390323DC31B6A86991298B96EB440BCA379D1" +
			"08E311B01321AE516A46496DD5DBCC1A9BDCB7A69DDAC7A098CF9EEC6378E72C" +
			"23DBEC868B2745458CF125879797A47037EA291441B5A83490BF7E6F8F779E0B" +
			"E69A36D58EE5A4DC9B60A9A10A1197EEFA52C7024E4C6464C86A86E5F0C0187E" +
			"D4EEA89B14B1299D25DCE9F744C4A434993437FBDDA67A6D5AA2D217FEF24425" +
			"04EF96D33278CF8C07B14C82E2EE31C5E7EF063F409AABCEF04C174617B66EDC" +
			"A803DE75884DB3B1CA0A23E58469078A08EA32EFA46D90E4EEEFF7947A0F8BD3" +
			"012B6860F1519BC0B78203010001",
			"00478000000002B6000000");
	}

	@Test public void defaultResponseNoKey() {
		assertResponseStatus(0x6a88, "00478100000002B800010E");
	}
}

