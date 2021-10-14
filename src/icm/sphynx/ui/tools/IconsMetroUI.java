package icm.sphynx.ui.tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 * @author israel-icm
 */
public class IconsMetroUI {
    /**
     * @param size Solo se usa una variable para el tamaño porque siempre será un icono cuadrado
     * @param colorHex
     * @return Icono
     */
    public static ImageIcon iconListDetail(int size, String colorHex) {
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D)image.getGraphics();
        
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setStroke(new BasicStroke(1));

        graphics.setColor(Color.decode(colorHex));
        graphics.drawRect(getPunto(2, size), getPunto(2, size), getPunto(5, size), getPunto(5, size));
        graphics.drawRect(getPunto(2, size), getPunto(9, size), getPunto(5, size), getPunto(5, size));
        graphics.drawLine(getPunto(9, size), getPunto(4, size), getPunto(14, size), getPunto(4, size));
        graphics.drawLine(getPunto(9, size), getPunto(11, size), getPunto(14, size), getPunto(11, size));

        return new ImageIcon(image);
    }
    /**
     * @param size Solo se usa una variable para el tamaño porque siempre será un icono cuadrado
     * @param colorHex
     * @return Icono
     */
    public static ImageIcon iconListDirectory(int size, String colorHex) {
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D)image.getGraphics();
        
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setStroke(new BasicStroke(1));

        graphics.setColor(Color.decode(colorHex));
        graphics.drawRect(getPunto(2, size), getPunto(2, size), getPunto(5, size), getPunto(5, size));
        graphics.drawRect(getPunto(2, size), getPunto(9, size), getPunto(5, size), getPunto(5, size));
        graphics.drawRect(getPunto(9, size), getPunto(2, size), getPunto(5, size), getPunto(5, size));
        graphics.drawRect(getPunto(9, size), getPunto(9, size), getPunto(5, size), getPunto(5, size));

        return new ImageIcon(image);
    }
    /**
     * @param size Solo se usa una variable para el tamaño porque siempre será un icono cuadrado
     * @param colorHex
     * @return Icono
     */
    public static ImageIcon iconHome2(int size, String colorHex) {
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D)image.getGraphics();
        
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setStroke(new BasicStroke(1));

        graphics.setColor(Color.decode(colorHex));
        graphics.drawRect(getPunto(2, size), getPunto(2, size), getPunto(5, size), getPunto(5, size));
        graphics.drawRect(getPunto(2, size), getPunto(9, size), getPunto(5, size), getPunto(5, size));
        graphics.drawRect(getPunto(9, size), getPunto(2, size), getPunto(5, size), getPunto(5, size));
        graphics.drawRect(getPunto(9, size), getPunto(9, size), getPunto(5, size), getPunto(5, size));

        return new ImageIcon(image);
    }
    
    public ImageIcon iconHome(int size) {
        Image img = new ImageIcon(getClass().getClassLoader().getResource("icm/sphynx/ui/components/metro/icons/home_white.png")).getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(size, size, Image.SCALE_SMOOTH));
        return img2;
    }
    
    public ImageIcon iconNewFolder(int size) {
        Image img = new ImageIcon(getClass().getClassLoader().getResource("icm/sphynx/ui/components/metro/icons/new_folder_white.png")).getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(size, size, Image.SCALE_SMOOTH));
        return img2;
    }
    
    public ImageIcon iconList(int size) {
        Image img = new ImageIcon(getClass().getClassLoader().getResource("icm/sphynx/ui/components/metro/icons/list_white.png")).getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(size, size, Image.SCALE_SMOOTH));
        return img2;
    }

    public ImageIcon iconUpFolder(int size) {
        Image img = new ImageIcon(getClass().getClassLoader().getResource("icm/sphynx/ui/components/metro/icons/up_folder_white.png")).getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(size, size, Image.SCALE_SMOOTH));
        return img2;
    }
    
    public ImageIcon iconFile(int size) {
        Image img = new ImageIcon(getClass().getClassLoader().getResource("icm/sphynx/ui/components/metro/icons/file_color.png")).getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(size, size, Image.SCALE_SMOOTH));
        return img2;
    }
    
    public ImageIcon iconDirectory(int size) {
        Image img = new ImageIcon(getClass().getClassLoader().getResource("icm/sphynx/ui/components/metro/icons/directory2_color.png")).getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(size, size, Image.SCALE_SMOOTH));
        return img2;
    }
    
    public ImageIcon iconHardDrive(int size) {
        Image img = new ImageIcon(getClass().getClassLoader().getResource("icm/sphynx/ui/components/metro/icons/hard_drive_color.png")).getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(size, size, Image.SCALE_SMOOTH));
        return img2;
    }
    
    public ImageIcon iconFloppyDrive(int size) {
        Image img = new ImageIcon(getClass().getClassLoader().getResource("icm/sphynx/ui/components/metro/icons/floppy_drive_color.png")).getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(size, size, Image.SCALE_SMOOTH));
        return img2;
    }
    
    public ImageIcon iconComputer(int size) {
        Image img = new ImageIcon(getClass().getClassLoader().getResource("icm/sphynx/ui/components/metro/icons/computer_color.png")).getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(size, size, Image.SCALE_SMOOTH));
        return img2;
    }
    /**
     * Calcular un punto en base a un tamaño definido en 16 con una regla de 3
     * @param punto
     * @return 
     */
    private static int getPunto(int punto, int size) {
        return (punto * size) / 16;
    }
}
