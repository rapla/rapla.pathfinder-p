/**
 * 
 */
package com.pathfinder.view.components;

/**
 * IDs of keyboard (used for identifying which key was pressed)
 * 
 * @author tim
 * 
 */
public enum KeyboardId {
	DELETE(""), SPACE(""), LEFT("<"), RIGHT(">"), ONE("1"), TWO("2"), THREE("3"), FOUR(
			"4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), ZERO(
			"0"), A("A"), B("B"), C("C"), D("D"), E("E"), F("F"), G("G"), H("H"), I(
			"I"), J("J"), K("K"), L("L"), M("M"), N("N"), O("O"), P("P"), Q("Q"), R(
			"R"), S("S"), T("T"), U("U"), V("V"), W("W"), X("X"), Y("Y"), Z("Z"), AE(
			"Ä"), OE("Ö"), UE("Ü");

	private String label;

	private KeyboardId(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
