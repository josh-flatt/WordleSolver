package net.incandescently.wordlesolver;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private CheckBox startsInd;
    @FXML
    private CheckBox endsInd;
    @FXML
    private CheckBox containsInd;
    @FXML
    private CheckBox omitsInd;
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
        WordQuery q = new WordQuery();
//        ObservableList<String> result = FXCollections.observableList(q.filterByWordStarts(wordStarts));
//        ObservableList<String> result = FXCollections.observableList(q.filterByWordEnds(wordEnds));
//        ObservableList<String> result = FXCollections.observableList(q.filterByWordContains(wordContains));
        ObservableList<String> result = FXCollections.observableList(q.filterByWordOmits(wordOmits));

        wordsTable.setItems(null);
        wordsTable.setItems(result);
        wordsColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue()));

    }
}