package application;

import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * Interface for the method that I use more than one time.
 * @author Setthanat Kladee
 */
public interface property {

    Button createButton(String message, int positionX, int positionY );
    Text createText(String message, int positionX, int positionY);

}
