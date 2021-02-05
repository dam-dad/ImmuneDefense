package dad.immuneDefense.Mapa;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.mapeditor.core.Map;
import org.mapeditor.core.ObjectGroup;
import org.mapeditor.core.Tile;
import org.mapeditor.core.MapLayer;
import org.mapeditor.core.TileLayer;
import org.mapeditor.io.TMXMapReader;
import org.mapeditor.view.HexagonalRenderer;
import org.mapeditor.view.MapRenderer;
import org.mapeditor.view.OrthogonalRenderer;


import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.mapeditor.view.IsometricRenderer;

/**
 * An example showing how to use libtiled-java to do a simple TMX viewer.
 */
public class TMXViewer
{
    public static void main(String[] arguments) throws Exception {
        String pathname;
		File fileToOpen = new File("src\\main\\resources\\mapas\\MapaCamino.tmx"); 
        		//"F:\\Cosas del tower defense\\mapas\\MapaCamino.tmx";

        for (String arg : arguments) {
            if ("-?".equals(arg) || "-help".equals(arg)) {
                printHelpMessage();
                return;
            } else if (arg.startsWith("-")) {
                System.out.println("Unknown option: " + arg);
                printHelpMessage();
                return;
            } else if (fileToOpen == null) {
                arg = fileToOpen.getPath();
            }
        }

        if (fileToOpen == null) {
            printHelpMessage();
            return;
        }

        Map map;
        //try {
            TMXMapReader mapReader = new TMXMapReader();
            //En esta línea da error
            System.out.println(fileToOpen.toString());
            System.out.println(fileToOpen.exists());
            map = mapReader.readMap(fileToOpen.getPath());
        /*} catch (Exception e) {
            System.out.println("Error while reading the map:\n" + e.getMessage());
            return;
        }*/

        System.out.println(map.toString() + " loaded");
      /*  TileLayer tl = new TileLayer();
        tl.setMap(map);
        Tile t= new Tile();
        File image = new File("file:///C:\\Users\\Usuario\\Desktop\\Fvbjf7Z.png");
        
        BufferedImage img = null;
        try {
            img = ImageIO.read(image);
        } catch (IOException e) {
        }
       
       t.setTileSet();
        t.setImage(img);
       tl.setTileAt(12, 13,t );
        tl.setVisible(true);
        
        map.insertLayer(1, tl);
        */
        JScrollPane scrollPane = new JScrollPane(new MapView(map));
        
        scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(498, 639));
     
        
        
        
        JFrame appFrame = new JFrame("TMX Viewer");
        appFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        appFrame.setContentPane(scrollPane);
        appFrame.pack();
        appFrame.setVisible(true);
       // EnemigoPrueba ene = new EnemigoPrueba(map.getHeight(),map.getWidth());
        
    }

    private static void printHelpMessage() {
        System.out.println("Java TMX Viewer\n" +
                "\n" +
                "When a parameter is given, it can either be a file name or an \n" +
                "option starting with '-'. These options are available:\n" +
                "\n" +
                "-?\n" +
                "-help\n" +
                "\tDisplays this help message\n");
    }
}

class MapView extends JPanel implements Scrollable
{
    private final Map map;
    private final MapRenderer renderer;
  

    public MapView(Map map) {
        this.map = map;
        renderer = createRenderer(map);

        setPreferredSize(renderer.getMapSize());
        setOpaque(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        final Graphics2D g2d = (Graphics2D) g.create();
        final Rectangle clip = g2d.getClipBounds();

        // Draw a gray background
        g2d.setPaint(new Color(100, 100, 100));
        g2d.fill(clip);

        // Draw each map layer
        for (MapLayer layer : map.getLayers()) {
        	
        	
            if (layer instanceof TileLayer) {
                renderer.paintTileLayer(g2d, (TileLayer) layer);
            } else if (layer instanceof ObjectGroup) {
                renderer.paintObjectGroup(g2d, (ObjectGroup) layer);
            }
        }
    }

    private static MapRenderer createRenderer(Map map) {
        switch (map.getOrientation()) {
            case ORTHOGONAL:
                return new OrthogonalRenderer(map);

            case ISOMETRIC:
                return new IsometricRenderer(map);

            case HEXAGONAL:
                return new HexagonalRenderer(map);

            default:
                return null;
        }
    }
    
 

    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    public int getScrollableUnitIncrement(Rectangle visibleRect,
                                          int orientation, int direction) {
        if (orientation == SwingConstants.HORIZONTAL)
            return map.getTileWidth();
        else
            return map.getTileHeight();
    }

    public int getScrollableBlockIncrement(Rectangle visibleRect,
                                           int orientation, int direction) {
        if (orientation == SwingConstants.HORIZONTAL) {
            final int tileWidth = map.getTileWidth();
            return (visibleRect.width / tileWidth - 1) * tileWidth;
        } else {
            final int tileHeight = map.getTileHeight();
            return (visibleRect.height / tileHeight - 1) * tileHeight;
        }
    }

    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    public boolean getScrollableTracksViewportHeight() {
        return false;
    }


}