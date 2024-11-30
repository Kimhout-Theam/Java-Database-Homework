package view;

import model.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;

public class TableView {
    static String RESET = "\033[0m";
    static String BLUE = "\033[34m";
    static String GREEN = "\033[32m";
    static String RED = "\033[31m";

    private Table tableStyle() {

        Table tt = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);


        tt.addCell(RED + "ID" + RESET, new CellStyle(CellStyle.HorizontalAlign.center), 1);
        tt.addCell( RED +"Product Name" + RESET, new CellStyle(CellStyle.HorizontalAlign.center), 1);
        tt.addCell(RED + "Category" + RESET, new CellStyle(CellStyle.HorizontalAlign.center), 1);
        tt.addCell(RED + "Price" + RESET, new CellStyle(CellStyle.HorizontalAlign.center), 1);


        tt.setColumnWidth(0, 10, 50);
        tt.setColumnWidth(1, 10, 50);
        tt.setColumnWidth(2, 10, 50);
        tt.setColumnWidth(3, 10, 50);
        return tt;
    }

    public void TableViewProduct(List<Product> products) {

        Table tt = tableStyle();
        CellStyle centerAndDot = new CellStyle(CellStyle.HorizontalAlign.center, CellStyle.AbbreviationStyle.dots);

        for (Product p : products) {
            tt.addCell(BLUE + p.getId() + RESET, centerAndDot, 1);
            tt.addCell(GREEN + p.getProductName() + RESET, centerAndDot, 1);
            tt.addCell(GREEN + p.getCategory() + RESET, centerAndDot, 1);
            tt.addCell(GREEN + p.getPrice() + RESET, centerAndDot, 1);
        }

        for (String t : tt.renderAsStringArray()) {
            System.out.println(t);
        }
    }

    public void TableViewOneProduct(Product product) {
        Table tt = tableStyle();
        CellStyle centerAndDot = new CellStyle(CellStyle.HorizontalAlign.center, CellStyle.AbbreviationStyle.dots);


            tt.addCell(BLUE + product.getId() + RESET, centerAndDot, 1);
            tt.addCell(GREEN + product.getProductName() + RESET, centerAndDot, 1);
            tt.addCell(GREEN + product.getCategory() + RESET, centerAndDot, 1);
            tt.addCell(GREEN + product.getPrice() + RESET, centerAndDot, 1);


        for (String t : tt.renderAsStringArray()) {
            System.out.println(t);
        }
    }

}
