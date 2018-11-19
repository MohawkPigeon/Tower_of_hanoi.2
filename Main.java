import java.util.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main extends Application {
    LinkedList<Rectangle> Reclist = new LinkedList<>();


    Board board = new Board();
    GridPane grid2 = new GridPane();
    GridPane grid = new GridPane();

    final Text actiontarget = new Text();
    int numberofdisks;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Tower of hanoi");

        GridPane.setColumnSpan(grid2, 5);

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(10);

        grid2.setAlignment(Pos.CENTER);
        grid2.setHgap(40);
        grid2.setVgap(5);

        grid.add(grid2, 1, 0);

        Button btn12 = new Button("-->");
        HBox hbBtn12 = new HBox(10);
        hbBtn12.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn12.getChildren().add(btn12);
        grid.add(hbBtn12, 1, 5);

        Button btn13 = new Button("<--");
        HBox hbBtn13 = new HBox(10);
        hbBtn13.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn13.getChildren().add(btn13);
        grid.add(hbBtn13, 1, 6);

        Button btn21 = new Button("<--");
        HBox hbBtn21 = new HBox(10);
        hbBtn21.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn21.getChildren().add(btn21);
        grid.add(hbBtn21, 2, 6);

        Button btn23 = new Button("-->");
        HBox hbBtn23 = new HBox(10);
        hbBtn23.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn23.getChildren().add(btn23);
        grid.add(hbBtn23, 2, 5);

        Button btn31 = new Button("-->");
        HBox hbBtn31 = new HBox(10);
        hbBtn31.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn31.getChildren().add(btn31);
        grid.add(hbBtn31, 3, 5);

        Button btn32 = new Button("<--");
        HBox hbBtn32 = new HBox(10);
        hbBtn32.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn32.getChildren().add(btn32);
        grid.add(hbBtn32, 3, 6);

        grid.add(actiontarget, 3, 7);

        TextField disks = new TextField();
        disks.setPromptText("Enter number of disks");
        Button btndisks = new Button("Update");
        HBox hdisks = new HBox(disks, btndisks);
        grid.add(hdisks, 0, 1);


        btndisks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numberofdisks = Integer.parseInt(disks.getText());

                actiontarget.setText("");

                int z = grid2.getChildren().size();
                while (z > 0) {
                    grid2.getChildren().remove(0);
                    z--;
                }

                z = Reclist.size();
                while (z > 0) {
                    Reclist.remove(0);
                    z--;
                }

                z = board.getDisklist1().size();
                while (z > 0) {
                    board.getDisklist1().remove(0);
                    z--;
                }
                z = board.getDisklist2().size();
                while (z > 0) {
                    board.getDisklist2().remove(0);
                    z--;
                }
                z = board.getDisklist3().size();
                while (z > 0) {
                    board.getDisklist3().remove(0);
                    z--;
                }

                int x = 40;

                while (Reclist.size() < numberofdisks) {
                    Rectangle r = new Rectangle();
                    r.setWidth(x);
                    r.setHeight(20);
                    Random rand = new Random();
                    int red = rand.nextInt(256);
                    int green = rand.nextInt(256);
                    int blue = rand.nextInt(256);
                    r.setFill(Color.rgb(red, green, blue));
                    Reclist.add(r);
                    grid2.add(r, 1, 0);

                    x = x + 20;
                }

                int listsize = board.getDisklist1().size();
                int size = 40;

                while (listsize < numberofdisks) {

                    Disk disk = new Disk(size);
                    board.addlast(disk);

                    size = size + 20;
                    listsize++;
                }

                update();
            }
        });

        btn12.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (board.isBigger12()) {
                    board.getDisklist2().addFirst(board.getDisklist1().getFirst());
                    board.getDisklist1().removeFirst();
                    update();
                }
            }
        });

        btn13.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (board.isBigger13()) {
                    board.getDisklist3().addFirst(board.getDisklist1().getFirst());
                    board.getDisklist1().removeFirst();
                    update();
                }
            }
        });

        btn21.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (board.isBigger21()) {
                    board.getDisklist1().addFirst(board.getDisklist2().getFirst());
                    board.getDisklist2().removeFirst();
                    update();
                }
            }
        });

        btn23.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (board.isBigger23()) {
                    board.getDisklist3().addFirst(board.getDisklist2().getFirst());
                    board.getDisklist2().removeFirst();
                    update();
                }
            }
        });

        btn31.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (board.isBigger31()) {
                    board.getDisklist1().addFirst(board.getDisklist3().getFirst());
                    board.getDisklist3().removeFirst();
                    update();
                }
            }
        });

        btn32.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (board.isBigger32()) {
                    board.getDisklist2().addFirst(board.getDisklist3().getFirst());
                    board.getDisklist3().removeFirst();
                    update();
                }
            }
        });

        Scene scene = new Scene(grid, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void update() {

        int z = grid2.getChildren().size();
        while (z > 0) {
            grid2.getChildren().remove(0);
            z--;
        }

        int x = 0;
        int y = 0;

        int listsize = board.getDisklist1().size();
        while (listsize < numberofdisks) {
            listsize++;
            y++;
        }

        int counter = 0;
        int listnumber = 0;
        int Width = 40;

        while (board.getDisklist1().size() > counter) {

            while (listnumber < numberofdisks) {
                if (Reclist.get(listnumber).getWidth() == Width) {

                    while (x < board.getDisklist1().size()) {
                        if (board.getDisklist1().get(x).getSize() == Width) {
                            grid2.add(Reclist.get(listnumber), 0, y);
                            counter++;
                            y++;
                        }
                        x++;
                    }
                }

                listnumber++;
            }
            Width = Width + 20;
            x = 0;
            listnumber = 0;
        }

        x = 0;
        y = 0;
        listsize = board.getDisklist2().size();
        while (listsize < numberofdisks) {
            listsize++;
            y++;
        }
        counter = 0;
        listnumber = 0;
        Width = 40;

        while (board.getDisklist2().size() > counter) {

            while (listnumber < numberofdisks) {
                if (Reclist.get(listnumber).getWidth() == Width) {

                    while (x < board.getDisklist2().size()) {
                        if (board.getDisklist2().get(x).getSize() == Width) {
                            grid2.add(Reclist.get(listnumber), 1, y);
                            counter++;
                            y++;
                        }
                        x++;
                    }
                }

                listnumber++;
            }
            Width = Width + 20;
            x = 0;
            listnumber = 0;

        }

        x = 0;
        y = 0;
        if (board.getDisklist3().size() == numberofdisks) {
            actiontarget.setFill(Color.GOLD);
            actiontarget.setText("Congratulations!");
        }
        listsize = board.getDisklist3().size();
        while (listsize < numberofdisks) {
            listsize++;
            y++;
        }


        counter = 0;
        listnumber = 0;
        Width = 40;

        while (board.getDisklist3().size() > counter) {

            while (listnumber < numberofdisks) {
                if (Reclist.get(listnumber).getWidth() == Width) {

                    while (x < board.getDisklist3().size()) {
                        if (board.getDisklist3().get(x).getSize() == Width) {
                            grid2.add(Reclist.get(listnumber), 2, y);
                            counter++;
                            y++;
                        }
                        x++;
                    }
                }

                listnumber++;
            }
            Width = Width + 20;
            x = 0;
            listnumber = 0;
        }
    }


    public static void main(String[] args) {
        launch(args);


        int x;
        int y = 1;

        Scanner keyboard = new Scanner(System.in);

        int size = 4;
        Disk disk1 = new Disk(size);
        size = 3;
        Disk disk2 = new Disk(size);
        size = 2;
        Disk disk3 = new Disk(size);
        size = 1;
        Disk disk4 = new Disk(size);

        Board board = new Board();
        board.add(disk1);
        board.add(disk2);
        board.add(disk3);
        board.add(disk4);

        while (y == 1) {
            System.out.println(board);
            x = keyboard.nextInt();

            if (x == 12 && board.isBigger12()) {
                board.getDisklist2().addFirst(board.getDisklist1().getFirst());
                board.getDisklist1().removeFirst();
                x = 0;
            }
            if (x == 13 && board.isBigger13()) {
                board.getDisklist3().addFirst(board.getDisklist1().getFirst());
                board.getDisklist1().removeFirst();
                x = 0;
            }
            if (x == 23 && board.isBigger23()) {
                board.getDisklist3().addFirst(board.getDisklist2().getFirst());
                board.getDisklist2().removeFirst();
                x = 0;
            }
            if (x == 21 && board.isBigger21()) {
                board.getDisklist1().addFirst(board.getDisklist2().getFirst());
                board.getDisklist2().removeFirst();
                x = 0;
            }
            if (x == 31 && board.isBigger31()) {
                board.getDisklist1().addFirst(board.getDisklist3().getFirst());
                board.getDisklist3().removeFirst();
                x = 0;
            }
            if (x == 32 && board.isBigger32()) {
                board.getDisklist2().addFirst(board.getDisklist3().getFirst());
                board.getDisklist3().removeFirst();
                x = 0;
            }

            if (x != 0) {
                System.out.println("Error");
            }


        }


    }
}
