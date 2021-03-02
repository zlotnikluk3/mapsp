package com.zlotluk.MaPSP.Notifications;
public final class Znaki {
	private Znaki() {
	}

	public static String bezZn(String TextZrodlowy) {

		StringBuilder Sb = new StringBuilder();
		int L = TextZrodlowy.length();
		for (int i = 0; i < L; i++) {
			switch ((int)TextZrodlowy.toCharArray()[i]) {
			case 261:
				Sb.append('a');
				break;
			case 263:
				Sb.append('c');
				break;
			case 281:
				Sb.append('e');
				break;
			case 322:
				Sb.append('l');
				break;
			case 324:
				Sb.append('n');
				break;
			case 243:
				Sb.append('o');
				break;
			case 347:
				Sb.append('s');
				break;
			case 378:
				Sb.append('z');
				break;
			case 380:
				Sb.append('z');
				break;
			case 260:
				Sb.append('A');
				break;
			case 262:
				Sb.append('C');
				break;
			case 280	:
				Sb.append('E');
				break;
			case 321:
				Sb.append('L');
				break;
			case 323:
				Sb.append('N');
				break;
			case 211:
				Sb.append('O');
				break;
			case 346:
				Sb.append('S');
				break;
			case 377:
				Sb.append('Z');
				break;
			case 379:
				Sb.append('Z');
				break;
			default:
				Sb.append(TextZrodlowy.toCharArray()[i]);
				break;
			}
		}

		return Sb.toString();
	}
}