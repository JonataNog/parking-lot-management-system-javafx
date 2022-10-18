module plmswithjavafx {

	exports gui;
	
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
	opens gui to javafx.fxml;
    opens modelo.entidades to javafx.graphics, javafx.fxml, javafx.base;
    opens modelo.servicos to javafx.graphics, javafx.fxml;
    
}
