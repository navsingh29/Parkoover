package com.grasshoppers.parkfinder.client.GUI;

import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;


public class TextBoxWithGhostText extends TextBox {

	
	/**
	 * Creates a TextBoxWithGhostText widget
	 */
	public static TextBoxWithGhostText wrap(Element element) {
        // Assert that the element is attached.
        assert Document.get().getBody().isOrHasChild(element);

        TextBoxWithGhostText textBox = new TextBoxWithGhostText(element);

        // Mark it attached and remember it for cleanup.
        textBox.onAttach();
        RootPanel.detachOnWindowClose(textBox);

        return textBox;
}

private String ghosttext = null;
private boolean isGhostTextVisible = false;

/**
 * Creates an empty text box.
 */
public TextBoxWithGhostText() {
        this(DOM.createInputText());
}

/**
 * This constructor may be used by subclasses to explicitly use an existing
 * element. This element must be an &lt;input&gt; element whose type is
 * 'text'.
 *
 * @param element the element to be used
 */
protected TextBoxWithGhostText(Element element) {
        super(element);
        setStyleName("TextBoxWithGhostText");
        setupHandlers();
}

@Override
public String getText() {
        if (isGhostTextVisible) {
                return "";
        } else {
                return super.getText();
        }
}

@Override
public void setText(String text) {
        super.setText(text);
       
        if (text == null || text.length() == 0) {
                addGhostText();
        } else {
                removeGhostText(text);
        }
}

public String getGhostText() {
        return ghosttext;
}

/**
 * Set the Ghost Text that will be displayed
 * @param value the text used
 */
public void setGhostText(String value) {
        if (isGhostTextVisible && value != null && !value.equals(ghosttext)) {
                // clear the text so the new ghosttext will be displayed
                super.setText("");
        }
       
        ghosttext = value;
       
        addGhostText();
}

private void setupHandlers() {
        addFocusHandler(new FocusHandler() {
                public void onFocus(FocusEvent event) {
                        removeGhostText();
                }
        });
       
        addBlurHandler(new BlurHandler() {
                public void onBlur(BlurEvent event) {
                        addGhostText();
                }
        });
}
/**
 * Makes the GhostText visible
 */
private void addGhostText() {
        if (super.getText().equals("") && getGhostText() != null) {
                addStyleName("gwt-Ghost-Text");
                super.setText(getGhostText());
                isGhostTextVisible = true;
        }
}

/**
 * Removes the GhostText, giving an empty box
 * @param newText
 */
private void removeGhostText(String newText) {
        if (isGhostTextVisible) {
                removeStyleName("gwt-Ghost-Text");
                super.setText(newText);
                isGhostTextVisible = false;
        }
}

private void removeGhostText() {
        removeGhostText("");
}


}
