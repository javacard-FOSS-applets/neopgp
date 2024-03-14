// SPDX-License-Identifier: GPL-3.0-or-later
package cc.walle.neopgp;

import static org.junit.Assert.*;
import javax.smartcardio.ResponseAPDU;
import javacard.framework.ISO7816;
import org.junit.Test;

public class KeyImportTest extends JcardsimTestCase {
	String importPrivateKey =
		"00 DB 3F FF 00 03 A6 4D 82 03 A2 B8 00 7F 48 15 91 03 92" +
		"81 80 93 81 80 94 81 80 95 81 80 96 81 80 97 82 01 00 5F" +
		"48 82 03 83 01 00 01 C1 2F BF 1B B2 75 C6 B0 8A F0 F6 9E" +
		"1F 28 0D 4E CB C3 76 47 B8 DA E0 5D 48 30 5E D0 3A 72 55" +
		"EA C7 43 7B 63 86 AC 20 53 EA 7B 56 55 F4 94 A3 B8 CB 7C" +
		"C7 4A 46 41 A8 B2 CE 0B 74 5A 47 E7 0B 47 B5 9A 03 71 7B" +
		"12 A3 97 4C DB 56 12 9C 1F E1 B4 5E B8 E0 DE 30 12 C9 E9" +
		"4C 8E A8 B8 03 2D FD 35 D6 5D 94 17 CC CC C9 05 43 9D 10" +
		"46 FF 7B D2 DD 7C 58 0B 31 B5 3C AC 63 6D A2 02 AF 9F 01" +
		"CF DF E0 71 32 97 E0 2F E8 20 12 BF 33 FF F3 64 8E C5 69" +
		"1E F0 AD DE 95 84 83 8D F3 DE C9 53 4D CA 5F 57 B8 62 BD" +
		"AD EB EA 4A A2 D1 DD F2 40 F4 0C 27 86 BE 67 58 2E A2 59" +
		"37 D5 74 D9 C5 C4 D9 B2 79 17 48 AE 0D C0 B8 02 72 B6 7F" +
		"E7 CB D3 69 44 20 C2 7E 86 D7 79 B0 FB 3A 91 71 FD ED 7F" +
		"04 20 7F 7D DE 9D 62 59 E6 FB 98 2B BE 76 2C 0B B2 F2 13" +
		"5A 7F AA A5 3A 4A 1D 35 36 02 28 50 C3 12 70 61 13 EB 36" +
		"F9 63 BE 58 7B E8 19 9A A7 5B 27 57 1E 06 98 B1 2D 49 23" +
		"BE 7F F4 E1 B3 61 EF DA 97 F2 07 5B E1 EB E2 DC 7D 13 BA" +
		"37 F2 B0 AB 41 77 70 A9 63 50 AD FB 7A 0C 54 87 22 B3 70" +
		"10 6F 71 D7 5E F8 23 A8 D6 E0 C8 1F B5 8E 17 71 27 54 55" +
		"CA 37 DA 67 8D 3E 45 2B A8 DA 93 7A 8C 21 99 1A 77 B7 BE" +
		"72 C6 0C E7 13 6A 2A 0D 35 E1 E2 EB AF EE 6C F5 19 D4 B5" +
		"A6 C6 BC F9 95 C5 7F C2 9E 1C 95 76 BC 39 E1 74 16 1F 40" +
		"72 A5 17 6E A8 07 9D E6 48 26 A2 94 02 71 C4 64 90 F1 E9" +
		"D4 61 97 D4 66 B0 82 31 5C 42 71 98 8C FB 19 43 3F 21 4C" +
		"1A 34 82 1C F7 00 17 9E 53 69 52 6A 87 E0 D5 40 94 16 46" +
		"EF 9A 1E 2D 5E 8F 5D 76 C7 78 67 87 BD B0 A3 83 74 6E 01" +
		"21 DA EA 6E 91 B7 3C CB 9C 68 AF B0 5F 48 B4 B4 CC 13 31" +
		"9A 22 AC 6E 4C B4 CA 0E 18 43 4C 79 D4 DB 67 A7 2C 95 3D" +
		"76 19 2A C9 9A 51 A1 77 4E 5A 8C E2 6A 30 88 CC 1D 70 C9" +
		"7D 52 69 1B 13 33 3B 22 A2 96 4E BE 54 EF B6 0E C4 2C 0F" +
		"23 60 77 48 D6 52 9E 9F 4B 3B 88 5C BB F5 D6 B8 A8 EB 5A" +
		"15 CA 96 E9 70 8D 94 1F 07 74 72 D9 3E DC 4D 61 4A 6B 96" +
		"F5 CB E9 17 63 7E F3 7D 0B 92 35 DA 28 CF E3 B0 3B 4D 0B" +
		"4D E2 CC 75 50 56 C0 E1 A0 C1 C0 FB C4 7C C4 20 D4 48 D7" +
		"AF 22 F1 9C 92 C2 BD D5 58 75 F1 45 52 B1 82 A7 77 6F 50" +
		"81 A9 5F 33 79 87 0B E4 30 3D 52 4D 64 6B A4 D7 77 AC E2" +
		"A8 37 02 7A 02 BD DD 8B B0 98 12 3A 1E 86 7D 19 31 79 A3" +
		"F2 43 AB 1F 47 C8 AF 98 67 CC E7 C0 22 A7 E8 31 FE 28 F6" +
		"16 B8 0C 4C F9 9D 17 EE D9 F8 36 D6 77 45 D5 1A EF A6 96" +
		"AF 27 D0 6D 54 54 EE 88 15 F8 12 40 EC 4E 12 36 31 30 BF" +
		"47 6B C5 5F 7F B6 84 62 B7 22 7B 59 51 C3 22 00 19 AC 34" +
		"28 92 F4 73 43 58 C8 20 EA A3 AB EF 27 CD AD 32 E4 E1 B9" +
		"4D 31 98 AF 9D 50 6D AA E9 4A 15 24 42 0F CA 2F 7C CC 1F" +
		"95 DE 3F 06 03 1C 74 81 65 F9 F2 E5 54 88 5F F6 A7 76 71" +
		"36 76 37 BF B6 9F 4C 09 D5 20 43 E5 27 AC 1A C9 28 87 2B" +
		"5E 84 12 F3 61 F2 81 75 CF 5E D2 0C 5C 6A 94 6A EB 24 08" +
		"6E D4 1C 17 EA ED 3E 6A 40 BC 10 37 70 17 E0 0A 03 D7 41" +
		"85 3B 71 08 84 82 5A 7E 2E 67 EE 8A 7C 8C 13 46 75 73 41" +
		"22 D5 04 D1 37 43 85 4F 53 7F";

	@Test public void importRSAKey() {
		admin();
		assertResponseOK(importPrivateKey);
	}

	@Test public void importRSAKeyAndCheckPublicKey() {
		admin();
		assertResponseOK(importPrivateKey);
		assertResponseData(
			"7F4982010981820100A95F3379870BE4303D524D646BA4D777ACE2A837027A02" +
			"BDDD8BB098123A1E867D193179A3F243AB1F47C8AF9867CCE7C022A7E831FE28" +
			"F616B80C4CF99D17EED9F836D67745D51AEFA696AF27D06D5454EE8815F81240" +
			"EC4E12363130BF476BC55F7FB68462B7227B5951C3220019AC342892F4734358" +
			"C820EAA3ABEF27CDAD32E4E1B94D3198AF9D506DAAE94A1524420FCA2F7CCC1F" +
			"95DE3F06031C748165F9F2E554885FF6A77671367637BFB69F4C09D52043E527" +
			"AC1AC928872B5E8412F361F28175CF5ED20C5C6A946AEB24086ED41C17EAED3E" +
			"6A40BC10377017E00A03D741853B710884825A7E2E67EE8A7C8C134675734122" +
			"D504D13743854F537F8203010001",
			"00478100000002B800010E");
	}
}
