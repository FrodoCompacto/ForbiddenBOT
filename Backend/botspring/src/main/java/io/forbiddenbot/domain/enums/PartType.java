package io.forbiddenbot.domain.enums;

public enum PartType {

	ARM(1, "Arm of the Forbidden One"),
	LEG(2, "Leg of the Forbidden One"),
	HEAD(3, "Head of the Forbidden One");
	
	private int cod;
	private String desc;
	
	private PartType(int cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}

	public int getCod() {
		return cod;
	}

	public String getDesc() {
		return desc;
	}
	
	public static PartType toEnum(Integer cod) {
		if (cod == null) return null;
		for (PartType x : PartType.values()){
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
