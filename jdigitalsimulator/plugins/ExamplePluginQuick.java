package de.ksqaured.jds.components.plugin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import de.ksquared.jds.components.Component;
import de.ksquared.jds.components.Component.HiddenComponent;
import de.ksquared.jds.components.Sociable;
import de.ksquared.jds.contacts.Contact;
import de.ksquared.jds.contacts.InputContact;
import de.ksquared.jds.contacts.OutputContact;

/** Example-Plugin Component Quick
 * This example was developed to show and describe the basic aspects of the JDigitalSimulator
 * plugin development quickly. Due to the complete object oriented approach of the JDigitalSimulator
 * is is very easy to create and deploy new plugin-Components to the JDigitalSimulator.
 * The minimum plugin requirements are very simple: extend Component. Everything
 * else is optional, but provides many additional features.
 * I will mark every important/required step in this tutorial with an //XXX marker.
 * Have fun coding an own plugin, so I had while devloping the JDigitalSimulator.
 * Regards,
 * @author Kristian Kraljic (kriskra@gmail.com)
 */

/** Please make sure you have imported the <code>JDigitalSimulator.jar</code> to your classpath //XXX
 * so you will get nice value-helps and some documentations for all of the methods available.
 */

/** The annotation HiddenComponent means, that this component is not selectable in the catalog **/
@HiddenComponent
/** In this tutorial we will extend the <code>Component</class> so we will have to implement the
 * <code>paint</code>, <code>getSize</code> and <code>calculate</code> methods. Additionally we will
 * implement the <code>Sociable</code> interface, what means our component can be connected to other
 * components (via wires).
 */
public class ExamplePluginQuick extends Component implements Sociable {
	private static final long serialVersionUID = 1l;
	
	/** The getSize method should always return the non-negative maximum dimensions of your component. //XXX
	 * This information is later-on used to determine the component position and enable the interaction.
	 */
	@Override public Dimension getSize() { return new Dimension(100,50); }
	
	/** You may define any number of <code>InputContacts</code> and <code>OutputContacts</code> and place
	 * them anywhere in your component. Please do only initialize them once and return them everytime the
	 * getContacts method is called (This method will get called by the JDigitalSimuator frequently).
	 */
	private InputContact input = new InputContact(this,new Point(0,25)); /** at position 0,25, left side */
	private OutputContact output = new OutputContact(this,new Point(100,25)); /** at position 100,25, right side */
	@Override public Contact[] getContacts() {
		return new Contact[]{input,output};
	}
	
	/** In the paint mehtod you may use all Graphics2D methods, to draw your component as you like. Please
	 * do not do any calculations in here, because this would interfer with the general design of the Simulator. 
	 */
	@Override public void paint(Graphics graphics) {
		graphics.setColor(Color.BLACK); /** do not forget to set a color first */
		graphics.drawRect(0,0,getSize().width,getSize().height);
		graphics.drawString("Quick",25,25); /** do not draw outside of the components bounds please! */
	}

	/** In here you may alter the input and output contacts as you like or do any other calculations */
	@Override public void calculate() {
		/** this will do the trick to implement a simple not-gate! */
		this.output.setCharged(!this.input.isCharged());
	}
}
