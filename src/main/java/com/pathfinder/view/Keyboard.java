package com.pathfinder.view;

import static com.pathfinder.view.KeyboardId.A;
import static com.pathfinder.view.KeyboardId.AE;
import static com.pathfinder.view.KeyboardId.B;
import static com.pathfinder.view.KeyboardId.C;
import static com.pathfinder.view.KeyboardId.D;
import static com.pathfinder.view.KeyboardId.DELETE;
import static com.pathfinder.view.KeyboardId.E;
import static com.pathfinder.view.KeyboardId.EIGHT;
import static com.pathfinder.view.KeyboardId.F;
import static com.pathfinder.view.KeyboardId.FIVE;
import static com.pathfinder.view.KeyboardId.FOUR;
import static com.pathfinder.view.KeyboardId.G;
import static com.pathfinder.view.KeyboardId.H;
import static com.pathfinder.view.KeyboardId.I;
import static com.pathfinder.view.KeyboardId.J;
import static com.pathfinder.view.KeyboardId.K;
import static com.pathfinder.view.KeyboardId.L;
import static com.pathfinder.view.KeyboardId.LEFT;
import static com.pathfinder.view.KeyboardId.M;
import static com.pathfinder.view.KeyboardId.N;
import static com.pathfinder.view.KeyboardId.NINE;
import static com.pathfinder.view.KeyboardId.O;
import static com.pathfinder.view.KeyboardId.OE;
import static com.pathfinder.view.KeyboardId.ONE;
import static com.pathfinder.view.KeyboardId.P;
import static com.pathfinder.view.KeyboardId.Q;
import static com.pathfinder.view.KeyboardId.R;
import static com.pathfinder.view.KeyboardId.RIGHT;
import static com.pathfinder.view.KeyboardId.S;
import static com.pathfinder.view.KeyboardId.SEVEN;
import static com.pathfinder.view.KeyboardId.SIX;
import static com.pathfinder.view.KeyboardId.SPACE;
import static com.pathfinder.view.KeyboardId.T;
import static com.pathfinder.view.KeyboardId.THREE;
import static com.pathfinder.view.KeyboardId.TWO;
import static com.pathfinder.view.KeyboardId.U;
import static com.pathfinder.view.KeyboardId.UE;
import static com.pathfinder.view.KeyboardId.V;
import static com.pathfinder.view.KeyboardId.W;
import static com.pathfinder.view.KeyboardId.X;
import static com.pathfinder.view.KeyboardId.Y;
import static com.pathfinder.view.KeyboardId.Z;
import static com.pathfinder.view.KeyboardId.ZERO;

import java.util.ArrayList;
import java.util.List;

import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * View for the Keyboard
 * 
 * @author max
 * 
 */
public class Keyboard extends CustomComponent implements KeyboardSpec {
	private final TranslatorSpec translator = Translator.getInstance();

	private final List<Button> buttonList = new ArrayList<>();

	private final Button deleteButton = createButton(DELETE);
	private final Button spaceButton = createButton(SPACE);

	private final VerticalLayout layout = new VerticalLayout();
	private final HorizontalLayout row1 = new HorizontalLayout();
	private final HorizontalLayout row2 = new HorizontalLayout();
	private final HorizontalLayout row3 = new HorizontalLayout();
	private final HorizontalLayout row4 = new HorizontalLayout();

	public Keyboard() {
		buildLayout();
		setCompositionRoot(layout);
		this.setPrimaryStyleName("keyboard");
	}

	private void buildLayout() {

		row1.setPrimaryStyleName("keyboard-row");
		row2.setPrimaryStyleName("keyboard-row");
		row3.setPrimaryStyleName("keyboard-row");
		row4.setPrimaryStyleName("keyboard-row");

		// The operations for the Keyboard in the order they appear on the
		// screen (left to right, top to bottom)
		KeyboardId[] firstRow = new KeyboardId[] { ONE, TWO, THREE, FOUR, FIVE,
				SIX, SEVEN, EIGHT, NINE, ZERO };
		KeyboardId[] secondRow = new KeyboardId[] { Q, W, E, R, T, Z, U, I, O,
				P, UE, LEFT };
		KeyboardId[] thirdRow = new KeyboardId[] { A, S, D, F, G, H, J, K, L,
				OE, AE, RIGHT };
		KeyboardId[] fourthRow = new KeyboardId[] { Y, X, C, V, B, N, M };

		for (KeyboardId id : firstRow) {
			row1.addComponent(createButton(id));
		}
		row1.addComponent(deleteButton);

		for (KeyboardId id : secondRow) {
			row2.addComponent(createButton(id));
		}

		for (KeyboardId id : thirdRow) {
			row3.addComponent(createButton(id));
		}

		for (KeyboardId id : fourthRow) {
			row4.addComponent(createButton(id));
		}
		row4.addComponent(spaceButton, 4);

		layout.addComponent(row1);
		layout.addComponent(row2);
		layout.addComponent(row3);
		layout.addComponent(row4);
	}

	/**
	 * Creates a new Button with label as specified in id and this class as
	 * listener
	 * 
	 * @param id
	 *            Keyboard ID with label
	 * @return New Button as specified above
	 */
	private Button createButton(KeyboardId id) {
		Button newButton = new Button();
		newButton.setData(id);
		buttonList.add(newButton);

		switch (id) {
		case SPACE:
			newButton.setPrimaryStyleName("keyboard-space");
			newButton.setCaption(translator.translate(TranslationKeys.SPACE));
			break;
		case DELETE:
			newButton.setPrimaryStyleName("keyboard-delete");
			newButton.setCaption(translator.translate(TranslationKeys.DELETE));
			break;
		default:
			newButton.setPrimaryStyleName("keyboard-button");
			newButton.setCaption(id.getLabel());
			break;
		}

		return newButton;
	}

	@Override
	public void hideKeyboard() {
		this.setVisible(false);
	}

	@Override
	public void showKeyboard() {
		this.setVisible(true);
	}

	@Override
	public void updateTranslations() {
		deleteButton.setCaption(translator.translate(TranslationKeys.DELETE));
		spaceButton.setCaption(translator.translate(TranslationKeys.SPACE));
	}

	@Override
	public void addKeyboardButtonListener(ClickListener listener) {
		for (Button button : buttonList) {
			button.addClickListener(listener);
		}
	}

	@Override
	public void doCleanup() {
	}
}