package firstdemo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.TextArea;

public class FirstDemo implements EntryPoint {
	public void onModuleLoad() {
		NodeList<Element> textareas =  Document.get().getElementsByTagName("textarea");
		
		for(int i = 0; i < textareas.getLength(); i++)
		{	
			final TextArea textarea = TextArea.wrap(textareas.getItem(i));
			final String maxlength = textarea.getElement().getAttribute("maxlength");
			
			if (!maxlength.isEmpty())
			{
				textarea.addKeyPressHandler(new KeyPressHandler()
				{
					public void onKeyPress(KeyPressEvent event)
					{							
						int keyCode = event.getNativeEvent().getKeyCode();
						if (((keyCode == KeyCodes.KEY_BACKSPACE) || (keyCode >= KeyCodes.KEY_LEFT)  && (keyCode <= KeyCodes.KEY_DOWN))
								|| (textarea.getText().length() < Integer.parseInt(maxlength))) return;		
						event.preventDefault();
					}
				});
				addPasteHandler(textareas.getItem(i), maxlength);
			}
		}	
	}
	
	public native void addPasteHandler(Element element, String maxlength)
    /*-{
        element.onpaste = function(e) {
			setTimeout(function()
			{
				if(element.value.length > maxlength) {
					element.value = element.value.substring(0, maxlength);
				}							
			}, 1);
            
        }
    }-*/;	
}
