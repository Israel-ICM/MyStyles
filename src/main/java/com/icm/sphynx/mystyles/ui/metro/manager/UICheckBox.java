package com.icm.sphynx.mystyles.ui.metro.manager;

import com.icm.sphynx.mystyles.ui.metro.tools.MetroUIConfigTheme;
import com.icm.sphynx.mystyles.ui.metro.tools.MetroUIComponent;
import com.icm.sphynx.mystyles.ui.metro.tools.MetroUIStyleColors;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicCheckBoxUI;

/**
 * Asigna los estilos a todos los checkbox
 * @author israel-icm
 */
public class UICheckBox extends BasicCheckBoxUI {
    private String COLOR_ICON_BACKGROUND = MetroUIStyleColors.CHECKBOX_ICON_BACKGROUND;
    private String COLOR_ICON_BORDER = MetroUIStyleColors.CHECKBOX_ICON_BORDER;
    private String COLOR_ICON_BACKGROUND_OVER = MetroUIStyleColors.CHECKBOX_ICON_BACKGROUND_OVER;
    private String COLOR_ICON_BORDER_OVER = MetroUIStyleColors.CHECKBOX_ICON_BORDER_OVER;
    private String COLOR_ICON_BACKGROUND_PRESSED = MetroUIStyleColors.CHECKBOX_ICON_BACKGROUND_PRESSED;
    private String COLOR_ICON_BORDER_PRESSED = MetroUIStyleColors.CHECKBOX_ICON_BORDER_PRESSED;
    private String COLOR_ICON_FOREGROUND = MetroUIStyleColors.CHECKBOX_ICON_FOREGROUND;
    private String COLOR_ICON_CHECK = MetroUIStyleColors.CHECKBOX_ICON_CHECK;
    private String COLOR_ICON_SWITCH_ON = MetroUIStyleColors.CHECKBOX_ICON_SWITCH_ON;
    private String COLOR_ICON_SWITCH_OFF = MetroUIStyleColors.CHECKBOX_ICON_SWITCH_OFF;
    
    private boolean componenteIniciado = false;
    private JCheckBox checkbox;
    private int widthIconSwitch = 45;

    private static final int STATE_DEFAULT = 0;
    private static final int STATE_OVER = 1;
    private static final int STATE_PRESSED = 2;
    private int currentStateCheckBox = 0; // Controla los estados del checkbox
    
    // Controladores de animación
    private boolean moverButton = false; // Define si se movera el activador del switch
    private int controlAnimation = 0; // Define la posición del activador switch
    private boolean dragMove = false; // Define si el botón del switch se está moviendo
    private boolean seArrastro = false; // Define si el botón del switch se arrastró

    public static ComponentUI createUI(JComponent b) {
        return new UICheckBox();
    }

    @Override
    public synchronized void paint(Graphics g, JComponent c) {
        checkbox = (JCheckBox)c;
        Graphics2D g2d = (Graphics2D)g;
        super.paint(g2d, checkbox);

        moverButton = checkbox.isSelected();
        installColors();
        installEvents();
        installBackground();
        installFont();
        installProperties();
    }
    private void installColors() {
        if (MetroUIConfigTheme.isDarkMode()) {
            COLOR_ICON_BACKGROUND = MetroUIStyleColors.CHECKBOX_ICON_BACKGROUND_DARK;
            COLOR_ICON_BORDER = MetroUIStyleColors.CHECKBOX_ICON_BORDER_DARK;
            COLOR_ICON_BACKGROUND_OVER = MetroUIStyleColors.CHECKBOX_ICON_BACKGROUND_OVER_DARK;
            COLOR_ICON_BORDER_OVER = MetroUIStyleColors.CHECKBOX_ICON_BORDER_OVER_DARK;
            COLOR_ICON_BACKGROUND_PRESSED = MetroUIStyleColors.CHECKBOX_ICON_BACKGROUND_PRESSED_DARK;
            COLOR_ICON_BORDER_PRESSED = MetroUIStyleColors.CHECKBOX_ICON_BORDER_PRESSED_DARK;
            COLOR_ICON_FOREGROUND = MetroUIStyleColors.CHECKBOX_ICON_FOREGROUND_DARK;
            COLOR_ICON_CHECK = MetroUIStyleColors.CHECKBOX_ICON_CHECK_DARK;
            COLOR_ICON_SWITCH_ON = MetroUIStyleColors.CHECKBOX_ICON_SWITCH_ON_DARK;
            COLOR_ICON_SWITCH_OFF = MetroUIStyleColors.CHECKBOX_ICON_SWITCH_OFF_DARK;
        }
    }
    private void installBackground() {
        checkbox.setBackground(null);
    }
    private void installFont() {
        checkbox.setForeground(Color.decode(COLOR_ICON_FOREGROUND));
        checkbox.setFont(new Font(UITools.FONT_DEFAULT, Font.PLAIN, checkbox.getFont().getSize()));
    }
    private void installProperties() {
        // Switch
        if (MetroUIComponent.getPropertyCheckBoxAsSwitch(checkbox.getName()))
            checkbox.setIcon(createSwitchImage(widthIconSwitch, 20, checkbox.isSelected()));
        else
            checkbox.setIcon(createCheckImage(20, 20, checkbox.isSelected()));

        // Text
        if (MetroUIComponent.getPropertyCheckBoxSwitchTextOn(checkbox.getName()) != null && MetroUIComponent.getPropertyCheckBoxSwitchTextOff(checkbox.getName()) != null) {
            if (checkbox.isSelected())
                checkbox.setText(MetroUIComponent.getPropertyCheckBoxSwitchTextOn(checkbox.getName()));
            else
                checkbox.setText(MetroUIComponent.getPropertyCheckBoxSwitchTextOff(checkbox.getName()));
        }
    }
    
    private void installEvents() {
        if (!componenteIniciado) {
            checkbox.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) { }

                @Override
                public void mousePressed(MouseEvent e) {
                    currentStateCheckBox = STATE_PRESSED;
                    // Cuando se preseiona se sobreentiende que se esta realizando un drag
                    dragMove = true;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    currentStateCheckBox = STATE_DEFAULT;
                    // Cuando se preseiona se sobreentiende que se dejó de hacer el drag
                    dragMove = false;

                    // Solo si se arrastró se valida la posición
                    if (seArrastro) {
                        // Al soltar el botón del mouse verificamos si el control esta más de la mitad o menos para activar o desactivar el switch
                        int posicion = e.getX() - (((60 * widthIconSwitch) / 100) - 10);
                        if (posicion > (27 / 2))
                            checkbox.setSelected(true);
                        else
                            checkbox.setSelected(false);
                    }

                    // La animación del botón del switch va en base al check
                    moverButton = !checkbox.isSelected();
                    // Una vez soltado la verificación de si se movió el botón cambia a falso
                    seArrastro = false;
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    currentStateCheckBox = STATE_OVER;
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    currentStateCheckBox = STATE_DEFAULT;
                }
            });
            checkbox.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    // Se calcula la posición inicial
                    int posicion = e.getX() - (((60 * widthIconSwitch) / 100) - 10);
                    // Se establece el parámetro maximo y mínimo para poder moverse en el área
                    if (posicion >= 0 && posicion < 27)
                        controlAnimation = e.getX() - (((60 * widthIconSwitch) / 100) - 10);
                    // Solo si se movió el botón la variable seArrastro cambia a verdadero
                    seArrastro = true;
                }

                @Override
                public void mouseMoved(MouseEvent e) { }
            });
            componenteIniciado = true;
        }
    }
    
    /**
     * Crea la imagen del checkbox
     * @param width Ancho de la imagen
     * @param height Alto de la imagen
     * @param checked Define si el checkbox esta seleccionado o no
     * @return Retorna la imagen generada
     */
    private ImageIcon createCheckImage(int width, int height, boolean checked) {
        BufferedImage image = new BufferedImage(width + 5, height + 5, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D)image.getGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (!checked) {
            switch (currentStateCheckBox) {
                case STATE_DEFAULT:
                    // Fondo del check
                    g2d.setColor(Color.decode(COLOR_ICON_BACKGROUND));
                    g2d.fillRect(1, 1, width, height);

                    // Borde del check
                    g2d.setColor(Color.decode(COLOR_ICON_BORDER));
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawRect(1, 1, width - 1, height - 1);
                    break;
                case STATE_OVER:
                    // Fondo del check
                    g2d.setColor(Color.decode(COLOR_ICON_BACKGROUND_OVER));
                    g2d.fillRect(1, 1, width, height);

                    // Borde del check
                    g2d.setColor(Color.decode(COLOR_ICON_BORDER_OVER));
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawRect(1, 1, width - 1, height - 1);
                    break;
                case STATE_PRESSED:
                    // Fondo del check
                    g2d.setColor(Color.decode(COLOR_ICON_BACKGROUND_PRESSED));
                    g2d.fillRect(1, 1, width, height);

                    // Borde del check
                    g2d.setColor(Color.decode(COLOR_ICON_BORDER_PRESSED));
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawRect(1, 1, width - 1, height - 1);
                    break;
                default:
                    break;
            }
        }
        else {
            switch (currentStateCheckBox) {
                case STATE_DEFAULT:
                    // Fondo del check
                    g2d.setColor(MetroUIConfigTheme.getPrimaryColor());
                    g2d.fillRect(1, 1, width, height);
                    
                    // Borde del check
                    g2d.setColor(MetroUIConfigTheme.getPrimaryColor());
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawRect(1, 1, width - 1, height - 1);
                    break;
                case STATE_OVER:
                    // Fondo del check
                    g2d.setColor(MetroUIConfigTheme.getPrimaryColor());
                    g2d.fillRect(1, 1, width, height);
                    
                    // Borde del check
                    g2d.setColor(Color.decode(COLOR_ICON_BORDER_OVER));
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawRect(1, 1, width - 1, height - 1);
                    break;
                case STATE_PRESSED:
                    // Fondo del check
                    g2d.setColor(Color.decode(COLOR_ICON_BACKGROUND_PRESSED));
                    g2d.fillRect(1, 1, width, height);

                    // Borde del check
                    g2d.setColor(Color.decode(COLOR_ICON_BORDER_PRESSED));
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawRect(1, 1, width - 1, height - 1);
                    break;
                default:
                    break;
            }
            // Icono del check activado
            g2d.setStroke(new BasicStroke(2));

            // Las ubicaciones se miden en porcentaje para que no se deforme si crece
            int inicioX = (18 * width) / 100;
            int inicioY = (60 * height) / 100;
            int medioX = (40 * width) / 100;
            int medioY = (80 * height) / 100;
            int finalX = (90 * width) / 100;
            int finalY = (30 * height) / 100;

            g2d.setColor(Color.decode(COLOR_ICON_CHECK));
            g2d.drawLine(inicioX, inicioY, medioX, medioY);
            g2d.drawLine(medioX, medioY, finalX, finalY);
        }
        return new ImageIcon(image);
    }
    
    /**
     * Crea la imagen del checkbox
     * @param width Ancho de la imagen
     * @param height Alto de la imagen
     * @param checked Define si el checkbox esta seleccionado o no
     * @return Retorna la imagen generada
     */
    private ImageIcon createSwitchImage(int width, int height, boolean checked) {
        BufferedImage image = new BufferedImage(width + 5, height + 5, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D)image.getGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int arcWidth = 20;
        int arcHeight = 45;
        if (!checked) {
            switch (currentStateCheckBox) {
                case STATE_DEFAULT:
                    // Fondo del check
                    g2d.setColor(Color.decode(COLOR_ICON_BACKGROUND));
                    g2d.fillRoundRect(1, 1, width, height, arcWidth, arcHeight);

                    // Borde del check
                    g2d.setColor(Color.decode(COLOR_ICON_BORDER));
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawRoundRect(1, 1, width - 1, height - 1, arcWidth, arcHeight);
                    break;
                case STATE_OVER:
                    // Fondo del check
                    g2d.setColor(Color.decode(COLOR_ICON_BACKGROUND_OVER));
                    g2d.fillRoundRect(1, 1, width, height, arcWidth, arcHeight);

                    // Borde del check
                    g2d.setColor(Color.decode(COLOR_ICON_BORDER_OVER));
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawRoundRect(1, 1, width - 1, height - 1, arcWidth, arcHeight);
                    break;
                case STATE_PRESSED:
                    // Fondo del check
                    g2d.setColor(Color.decode(COLOR_ICON_BACKGROUND_PRESSED));
                    g2d.fillRoundRect(1, 1, width, height, arcWidth, arcHeight);

                    // Borde del check
                    g2d.setColor(Color.decode(COLOR_ICON_BORDER_PRESSED));
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawRoundRect(1, 1, width - 1, height - 1, arcWidth, arcHeight);
                    break;
                default:
                    break;
            }
        }
        else {
            switch (currentStateCheckBox) {
                case STATE_DEFAULT:
                    // Fondo del check
                    g2d.setColor(MetroUIConfigTheme.getPrimaryColor());
                    g2d.fillRoundRect(1, 1, width, height, arcWidth, arcHeight);
                    
                    // Borde del check
                    g2d.setColor(MetroUIConfigTheme.getPrimaryColor());
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawRoundRect(1, 1, width - 1, height - 1, arcWidth, arcHeight);
                    break;
                case STATE_OVER:
                    // Fondo del check
                    g2d.setColor(MetroUIConfigTheme.getFourthColor());
                    g2d.fillRoundRect(1, 1, width, height, arcWidth, arcHeight);
                    
                    // Borde del check
                    g2d.setColor(MetroUIConfigTheme.getFourthColor());
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawRoundRect(1, 1, width - 1, height - 1, arcWidth, arcHeight);
                    break;
                case STATE_PRESSED:
                    // Fondo del check
                    g2d.setColor(Color.decode(COLOR_ICON_BACKGROUND_PRESSED));
                    g2d.fillRoundRect(1, 1, width, height, arcWidth, arcHeight);

                    // Borde del check
                    g2d.setColor(Color.decode(COLOR_ICON_BORDER_PRESSED));
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawRoundRect(1, 1, width - 1, height - 1, arcWidth, arcHeight);
                    break;
                default:
                    break;
            }
        }
        // Icono del check activado
        g2d.setStroke(new BasicStroke(2));

        // Las ubicaciones se miden en porcentaje para que no se deforme si crece
        int x = ((12 * width) / 100) + controlAnimation;
        int y = (26 * height) / 100;
        int size = (60 * height) / 100;
        
        if (!dragMove) {
            if (moverButton) {
                if (controlAnimation < (width - size) - 8)
                    controlAnimation++;
            }
            else {
                if (controlAnimation > 0)
                    controlAnimation--;
            }
        }

        if (checked)
            g2d.setColor(Color.decode(COLOR_ICON_SWITCH_ON));
        else {
            if (currentStateCheckBox == STATE_PRESSED)
                g2d.setColor(Color.decode(COLOR_ICON_SWITCH_ON));
            else
                g2d.setColor(Color.decode(COLOR_ICON_SWITCH_OFF));
        }
        
        g2d.fillOval(x, y, size, size);

        return new ImageIcon(image);
    }
}
