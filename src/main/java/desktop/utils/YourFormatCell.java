package desktop.utils;

import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;

public class YourFormatCell extends ListCell<String> {

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        setText(item);
        if (item.contains("1234"))
            setTextFill(Color.RED);
    }
}
