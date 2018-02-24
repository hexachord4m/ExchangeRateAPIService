package jp.hexachord.api.def;

import java.util.Arrays;
import java.util.Optional;

public enum CalcType {
	NONE(0), SUM(1), AVG(2), MAX(3), MIN(4);

	private final int id;

	private CalcType(final int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public static CalcType getCalcType(String str) {
		if (str == null) {
			return CalcType.NONE;
		}

		Optional<CalcType> calcType = Arrays.stream(CalcType.values())
				.filter(r -> r.name().equals(str.toUpperCase()))
				.findAny();
		return calcType.isPresent() ? calcType.get() : CalcType.NONE;
	}
}
