package icm.sphynx.ui.components.metro;

import icm.sphynx.ui.tools.StyleColorsMetro;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTextFieldUI;

/**
 * Asigna estilos a todas las cajas de texto
 * @author israel-icm
 */
public class UITextField extends BasicTextFieldUI  {
    private final String _colorBackground = StyleColorsMetro.COLOR_BACKGROUND_TEXT_FIELD;
    private final String _colorBorder = StyleColorsMetro.COLOR_BORDER_TEXT_FIELD;
    private final String _colorIconButton = "#838383";
    private final String _colorText = "#000000";
    private final String _colorPlaceholder = "#8F8F90";

    private static final int STATE_DEFAULT = 1;
    private static final int STATE_FOCUS = 2;
    private static final int STATE_OVER = 3;
    private int currentStateTextField = 1; // Controla los estados del textfield

    private JTextField textField;
    private final JLabel iconOrButton = new JLabel();
    private int sizeButton;
    private boolean inicializado = false;
    private boolean textFieldVacio = false;
    
    // Controladores de animación
    private boolean verButton = false; // Define si se visualizará el botón para limpiar el textfield
    private float controlAnimation = 0; // Define la transparencia del botón (0.0 < > 1.0)

    public UITextField() {
        inicializado = false;
    }
    
    public static ComponentUI createUI(JComponent c) {
        return new UITextField();
    }

    @Override
    protected void paintBackground(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        super.paintBackground(g2d);
        textField = (JTextField)super.getComponent();

        installBackground(g2d);
        installFont();
        installBorder();
        installProperties(g2d);
        installEventsTextField();
        installPadding();
    }
    
    private void installProperties(Graphics2D g2d) {
        // Iconos
        String icon = MetroUIComponent.getPropertyTextFieldIcon(textField.getName());
        icon = icon != null ? icon : "";
        switch (icon) {
            case MetroUIComponent.ICON_SEARCH:
                addIcon(icon);
                break;
            default: // Por default siempre se agrega el botón limpiar
                addButtonClear();
                installEventsButtonClear();
                break;
        }
        
        // Placeholder
        textFieldVacio = textField.getText().length() == 0;
        String placeholder = MetroUIComponent.getPropertyTextFieldPlaceholder(textField.getName());
        if (placeholder != null)
            installPlaceholder(g2d, placeholder);
    }

    private void installPlaceholder(Graphics2D g2d, String placeholder) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.decode(_colorPlaceholder));
        g2d.drawString((textFieldVacio) ? placeholder : "", UITools.PADDING_CONTENTS * 2, (textField.getSize().height) / 2 + (textField.getFont().getSize() / 2) - (UITools.PADDING_CONTENTS / 2));
    }

    private void addIcon(String icon) {
        iconOrButton.setText("");
        textField.setVisible(true);
        iconResized(icon);
    }
    /**
     * Ajusta la dimensión y posición del ícono
     */
    private void iconResized(String icon) {
        sizeButton = textField.getSize().width - (UITools.PADDING_CONTENTS * 2);
        if (sizeButton > 20) // El ícono no debería tener un ancho mayor a 20
            sizeButton = 20;
        iconOrButton.setSize( new Dimension(sizeButton, textField.getSize().height - (UITools.PADDING_CONTENTS * 2)));
        iconOrButton.setPreferredSize(new Dimension(sizeButton, textField.getSize().height - (UITools.PADDING_CONTENTS * 2)));

        iconOrButton.setLocation(textField.getWidth() - iconOrButton.getWidth() - UITools.PADDING_CONTENTS, textField.getHeight() - iconOrButton.getHeight() - UITools.PADDING_CONTENTS);
        switch (icon) {
            case MetroUIComponent.ICON_SEARCH:
                iconOrButton.setIcon(createSearchImage(sizeButton, sizeButton, (currentStateTextField == STATE_FOCUS) ? UITools.colorToHex(MetroUIConfigTheme.getPrimaryColor()) : _colorIconButton, 1));
                textField.add(iconOrButton);
                break;
            default: break;
        }
    }
    
    /**
     * Agrega el botón para borrar el texto
     */
    private void addButtonClear(){
        iconOrButton.setText("");
        iconOrButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textField.setVisible(true);
        buttonClearResized();
    }
    /**
     * Ajusta la dimensión y posición del botón X
     */
    private void buttonClearResized() {
        sizeButton = textField.getSize().width - (UITools.PADDING_CONTENTS * 2);
        if (sizeButton > 20) // El botón no debería tener un ancho mayor a 20
            sizeButton = 20;
        iconOrButton.setSize( new Dimension(sizeButton, textField.getSize().height - (UITools.PADDING_CONTENTS * 2)));
        iconOrButton.setPreferredSize(new Dimension(sizeButton, textField.getSize().height - (UITools.PADDING_CONTENTS * 2)));

        iconOrButton.setLocation(textField.getWidth() - iconOrButton.getWidth() - UITools.PADDING_CONTENTS, textField.getHeight() - iconOrButton.getHeight() - UITools.PADDING_CONTENTS);
        iconOrButton.setIcon(createClearImage(sizeButton, sizeButton, (currentStateTextField == STATE_FOCUS) ? UITools.colorToHex(MetroUIConfigTheme.getPrimaryColor()) : _colorIconButton, 1));
        textField.add(iconOrButton);
    }

    /**
     * Genera la imagen del icono para borrar el texto de la caja
     * @param width
     * @param height
     * @param colorHex
     * @param stroke
     * @return 
     */
    private ImageIcon createClearImage(int width, int height, String colorHex, int stroke) {
        BufferedImage image = new BufferedImage(width + UITools.PADDING_CONTENTS, height + UITools.PADDING_CONTENTS, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D)image.getGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Bloque de animación
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Float.parseFloat(UITools.redondear(controlAnimation, 2) + "f"));
        if (verButton) {
            if (UITools.redondear(controlAnimation, 2) < 0.99f)
                controlAnimation += 0.02f;
        }
        else {
            if (UITools.redondear(controlAnimation, 2) > 0f)
                controlAnimation -= 0.02f;
        }
        graphics.setComposite(ac);
        
        // Icono del botón clear
        graphics.setStroke(new BasicStroke(stroke));

        // Las ubicaciones se miden en porcentaje para que no se deforme si crece
        int inicioX = (10 * width) / 100;
        int inicioY = (10 * width) / 100;
        int finX = (90 * width) / 100;
        int finY = (90 * width) / 100;

        int inicioX2 = (90 * width) / 100;
        int inicioY2 = (10 * width) / 100;
        int finX2 = (10 * width) / 100;
        int finY2 = (90 * width) / 100;
        
        int centrar = (iconOrButton.getWidth() - width) / 2;
        
        graphics.setColor(Color.decode(colorHex));
        graphics.drawLine(inicioX + centrar, inicioY + centrar, finX + centrar, finY + centrar);
        graphics.drawLine(inicioX2 + centrar, inicioY2 + centrar, finX2 + centrar, finY2 + centrar);
        
        return new ImageIcon(image);
    }
    /**
     * Genera la imagen del icono para borrar el texto de la caja
     * @param width
     * @param height
     * @param colorHex
     * @param stroke
     * @return 
     */
    private ImageIcon createSearchImage(int width, int height, String colorHex, int stroke) {
        BufferedImage image = new BufferedImage(width, height + UITools.PADDING_CONTENTS, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D)image.getGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setStroke(new BasicStroke(stroke));

        width = width - 4;
        int puntoMedio = (width / 2);
        int inicioX = puntoMedio - (puntoMedio / 2);
        int inicioY = inicioX;
        
        graphics.setColor(Color.decode(colorHex));
        graphics.drawOval(inicioX + 2, inicioY, width / 2, width / 2);
        graphics.drawLine(3, width, (width / 2) - 1, (width / 2) + 3);
        
        return new ImageIcon(image);
    }
    
    private void installBackground(Graphics2D g2d) {
        textField.setBackground(null);
        textField.setSelectionColor(MetroUIConfigTheme.getPrimaryColor());
        textField.setSelectedTextColor(Color.WHITE);
        textField.setForeground(Color.decode(_colorText));
        sizeButton = textField.getHeight() - (UITools.PADDING_CONTENTS * 2);

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f);
        g2d.setComposite(ac);
        g2d.setColor(Color.decode(_colorBackground));
        g2d.fillRect(0, 0, super.getComponent().getWidth(), super.getComponent().getHeight());
    }
    
    private void installFont() {
        textField.setFont(new Font(UITools.FONT_DEFAULT, textField.getFont().getStyle(), textField.getFont().getSize()));
    }
    
    private void installBorder() {
        switch (currentStateTextField) {
            case STATE_DEFAULT:
                textField.setBorder(new LineBorder(Color.decode(_colorBorder), 2));
                break;
            case STATE_OVER:
                textField.setBorder(new LineBorder(Color.decode(UITools.bajarBrillo(_colorBorder)), 2));
                break;
            case STATE_FOCUS:
                textField.setBorder(new LineBorder(MetroUIConfigTheme.getPrimaryColor(), 2));
                break;
        }
    }
    
    private void installPadding() {
        // Agregamos los padding normalmente después de agregar el botón o icono
        textField.setBorder(BorderFactory.createCompoundBorder(textField.getBorder(), BorderFactory.createEmptyBorder(UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, sizeButton + UITools.PADDING_CONTENTS)));
    }
    
    private void installEventsTextField() {
        if (!inicializado) {
            // textField.setSize(new Dimension(textField.getWidth(), 30));
            
            textField.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) { }

                @Override
                public void mousePressed(MouseEvent e) { }

                @Override
                public void mouseReleased(MouseEvent e) { }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // btnClear.setVisible(true);
                    verButton = true;
                    if (currentStateTextField != STATE_FOCUS)
                        currentStateTextField = STATE_OVER;
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // btnClear.setVisible(false);
                    verButton = false;
                    if (currentStateTextField != STATE_FOCUS)
                        currentStateTextField = STATE_DEFAULT;
                }
            });
            textField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    currentStateTextField = STATE_FOCUS;
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (currentStateTextField != STATE_OVER)
                        currentStateTextField = STATE_DEFAULT;
                }
            });
            textField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void removeUpdate(DocumentEvent e) {
                    textFieldVacio = !(textField.getText().length() > 0);
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    textFieldVacio = false;
                }

                @Override
                public void changedUpdate(DocumentEvent de) {}
            });
            inicializado = true;
        }
    }
    
    private void installEventsButtonClear() {
        if (!inicializado) {
            textField.setSize(new Dimension(textField.getWidth(), 30));
            // btnClear.setVisible(false);
            iconOrButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ((JTextField) iconOrButton.getParent()).setText("");
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    iconOrButton.setOpaque(true);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    iconOrButton.setOpaque(false);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // btnClear.setVisible(true);
                    verButton = true;
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // btnClear.setVisible(false);
                    verButton = false;
                }
            });
        }
    }
}
