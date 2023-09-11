package net.incandescently.wordlesolver;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.lang.reflect.Array;
import java.util.List;

public class MainController {
    @FXML
    private Button runButton;
    @FXML
    private TextField startsField;
    @FXML
    private TextField endsField;
    @FXML
    private TextField containsField;
    @FXML
    private TextField omitsField;
    @FXML
    private TextField letter1Field;
    @FXML
    private TextField letter2Field;
    @FXML
    private TextField letter3Field;
    @FXML
    private TextField letter4Field;
    @FXML
    private TextField letter5Field;
    @FXML
    protected TableView<String> wordsTable;
    @FXML
    protected TableColumn<String, String> wordsColumn;



    @FXML
    protected void onRunButtonClicked(ActionEvent event) {
        String wordStarts = this.startsField.getText();
        String wordEnds = this.endsField.getText();
        String wordContains = this.containsField.getText();
        String wordOmits = this.omitsField.getText();
        char letter1 = '\u0000';
        char letter2 = '\u0000';
        char letter3 = '\u0000';
        char letter4 = '\u0000';
        char letter5 = '\u0000';
        if (!this.letter1Field.getText().isEmpty()) {
            letter1 = this.letter1Field.getText().charAt(0);
        }
        if (!this.letter2Field.getText().isEmpty()) {
            letter2 = this.letter2Field.getText().charAt(0);
        }
        if (!this.letter3Field.getText().isEmpty()) {
            letter3 = this.letter3Field.getText().charAt(0);
        }
        if (!this.letter4Field.getText().isEmpty()) {
            letter4 = this.letter4Field.getText().charAt(0);
        }
        if (!this.letter5Field.getText().isEmpty()) {
            letter5 = this.letter5Field.getText().charAt(0);
        }
        Character[] letters = new Character[] {letter1, letter2, letter3, letter4, letter5};
        WordQuery q = new WordQuery(wordStarts, wordEnds, wordContains, wordOmits, letters);
//        ObservableList<String> result = FXCollections.observableList(q.filterByWordStarts(wordStarts));
//        ObservableList<String> result = FXCollections.observableList(q.filterByWordEnds(wordEnds));
//        ObservableList<String> result = FXCollections.observableList(q.filterByWordContains(wordContains));
//        ObservableList<String> result = FXCollections.observableList(q.filterByWordOmits(wordOmits));
        ObservableList<String> result = FXCollections.observableList(q.getResult());

        wordsTable.setItems(null);
        wordsTable.setItems(result);
        wordsColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue()));

    }
}