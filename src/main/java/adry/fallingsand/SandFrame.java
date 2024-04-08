package adry.fallingsand;

import javax.swing.*;
import java.awt.*;

public class SandFrame extends JFrame {

    private final Sand sand = new Sand(500, 500);

    public SandFrame() {
        setSize(800, 600);
        setTitle("Falling Sand");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        sand.randomSand(5000);

        SandComponent sandComponent = new SandComponent(sand);
        add(sandComponent, BorderLayout.CENTER);
    }

}