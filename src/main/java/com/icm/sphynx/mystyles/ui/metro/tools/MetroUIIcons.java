package com.icm.sphynx.mystyles.ui.metro.tools;

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
public class MetroUIIcons {
    public static final String PATH_ICONS = "com/icm/sphynx/mystyles/ui/metro/icons/";
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
    
    public ImageIcon iconHome(int size, boolean black) {
        String imageName = "home_white.png";
        if (black)
            imageName = "home_black.png";
        Image img = new ImageIcon(getClass().getClassLoader().getResource(PATH_ICONS + imageName)).getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(size, size, Image.SCALE_SMOOTH));
        return img2;
    }
    
    public ImageIcon iconNewFolder(int size, boolean black) {
        String imageName = "new_folder_white.png";
        if (black)
            imageName = "new_folder_black.png";
        Image img = new ImageIcon(getClass().getClassLoader().getResource(PATH_ICONS + imageName)).getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(size, size, Image.SCALE_SMOOTH));
        return img2;
    }
    
    public ImageIcon iconList(int size, boolean black) {
        String imageName = "list_white.png";
        if (black)
            imageName = "list_black.png";
        Image img = new ImageIcon(getClass().getClassLoader().getResource(PATH_ICONS + imageName)).getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(size, size, Image.SCALE_SMOOTH));
        return img2;
    }

    public ImageIcon iconUpFolder(int size, boolean black) {
        String imageName = "up_folder_white.png";
        if (black)
            imageName = "up_folder_black.png";
        Image img = new ImageIcon(getClass().getClassLoader().getResource(PATH_ICONS + imageName)).getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(size, size, Image.SCALE_SMOOTH));
        return img2;
    }
    
    public ImageIcon iconFile(int size) {
        Image img = new ImageIcon(getClass().getClassLoader().getResource(PATH_ICONS + "file_color.png")).getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(size, size, Image.SCALE_SMOOTH));
        return img2;
    }
    
    public ImageIcon iconDirectory(int size) {
        Image img = new ImageIcon(getClass().getClassLoader().getResource(PATH_ICONS + "directory2_color.png")).getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(size, size, Image.SCALE_SMOOTH));
        return img2;
    }
    
    public ImageIcon iconHardDrive(int size) {
        Image img = new ImageIcon(getClass().getClassLoader().getResource(PATH_ICONS + "hard_drive_color.png")).getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(size, size, Image.SCALE_SMOOTH));
        return img2;
    }
    
    public ImageIcon iconFloppyDrive(int size) {
        Image img = new ImageIcon(getClass().getClassLoader().getResource(PATH_ICONS + "floppy_drive_color.png")).getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(size, size, Image.SCALE_SMOOTH));
        return img2;
    }
    
    public ImageIcon iconComputer(int size) {
        Image img = new ImageIcon(getClass().getClassLoader().getResource(PATH_ICONS + "computer_color.png")).getImage();
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
