package br.com.emorgado.javafx;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.utils.FontAwesomeIconFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * TableCellFactory for boolean values.
 */
public class BooleanIconTableCellFactory<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {
    public TableCell<S, T> call(TableColumn<S, T> param) {
        return new TableCell<S, T>(){
        	@Override
        	protected void updateItem(T item, boolean empty) {
        		super.updateItem(item, empty);
        		if( item == null || empty ){
        			setText(null);
        		} else {
        			Text text; 
        			if( item.equals(true) ) {
        				text = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.CHECK);
        			} else {
        				text = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.BAN);
        			}
        			setGraphic( text );
        		}
        	}
        };
    }
}
