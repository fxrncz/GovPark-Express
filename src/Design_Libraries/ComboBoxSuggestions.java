package Design_Libraries;

import javax.swing.*;

public class ComboBoxSuggestions<E> extends JComboBox<E>{
	
	public ComboBoxSuggestions() {
		setUI(new ComboSuggestionUI());
	}

}
