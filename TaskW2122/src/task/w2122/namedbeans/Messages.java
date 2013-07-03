/*
 * Class to handle error messages
 */
package task.w2122.namedbeans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class Messages {
	
	private UIComponent messageButton;

	public UIComponent getMessageButton() {
		return messageButton;
	}

	public void setMessageButton(UIComponent messageButton) {
		this.messageButton = messageButton;
	}
	/*
	 * Sets the error message
	 */
	public void errorMessage(String notice) {
		FacesMessage message = new FacesMessage(notice);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}

}
