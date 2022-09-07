package com.sphynxs.mystyles.ui.metro.manager;

import com.sphynxs.mystyles.ui.metro.tools.MetroUIConfigTheme;
import com.sphynxs.mystyles.ui.metro.tools.MetroUIComponent;
import com.sphynxs.mystyles.ui.metro.tools.MetroUIStyleColors;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPasswordFieldUI;

/**
 * Asigna los estilos de todas las cajas de password
 * @author israel-icm
 */
public class UIPasswordField extends BasicPasswordFieldUI {
    private String COLOR_BACKGROUND = MetroUIStyleColors.TEXT_FIELD_BACKGROUND;
    private String COLOR_BORDER = MetroUIStyleColors.TEXT_FIELD_BORDER;
    private String COLOR_BUTTON_ICON = MetroUIStyleColors.TEXT_FIELD_BUTTON_ICON;
    private String COLOR_FOREGROUND = MetroUIStyleColors.TEXT_FIELD_FOREGROUND;
    private String COLOR_PLACEHOLDER = MetroUIStyleColors.TEXT_FIELD_PLACEHOLDER;
    private String COLOR_CARET = MetroUIStyleColors.TEXT_FIELD_CARET;

    private static final int STATE_DEFAULT = 1;
    private static final int STATE_FOCUS = 2;
    private static final int STATE_OVER = 3;
    private int currentStatePasswordField = 1; // Controla los estados del textfield
    
    private JPasswordField passwordField;
    private final JLabel btnVerPassword = new JLabel();
    private int sizeButton;
    private boolean inicializado = false;
    private boolean passwordFieldVacio = false;
    private String strPasswordAux = "";
    private boolean swBtnVerPresionado = false;
    
    // Controladores de animación
    private boolean verButton = false; // Define si se visualizará el botón para ver el password
    private float controlAnimation = 0; // Define la transparencia del botón (0.0 < > 1.0)

    public UIPasswordField() {
        inicializado = false;
    }
    
    public static ComponentUI createUI(JComponent c) {
        return new UIPasswordField();
    }

    @Override
    protected void paintBackground(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        super.paintBackground(g2d);
        passwordField = (JPasswordField)super.getComponent();

        installColors();
        installBackground(g2d);
        installFont();
        installBorder();
        installProperties(g2d);
        installEventsPasswordField();
        installPadding();
        listenerVerPassword(g2d);
    }
    private void installColors() {
        if (MetroUIConfigTheme.isDarkMode()) {
            COLOR_BACKGROUND = MetroUIStyleColors.TEXT_FIELD_BACKGROUND_DARK;
            COLOR_BORDER = MetroUIStyleColors.TEXT_FIELD_BORDER_DARK;
            COLOR_BUTTON_ICON = MetroUIStyleColors.TEXT_FIELD_BUTTON_ICON_DARK;
            COLOR_FOREGROUND = MetroUIStyleColors.TEXT_FIELD_FOREGROUND_DARK;
            COLOR_PLACEHOLDER = MetroUIStyleColors.TEXT_FIELD_PLACEHOLDER_DARK;
            COLOR_CARET = MetroUIStyleColors.TEXT_FIELD_CARET_DARK;
        }
    }
    private void installProperties(Graphics2D g2d) {
        addButtonVerPassword();
        installEventsButtonVerPassword();
        
        // Placeholder
        passwordFieldVacio = passwordField.getPassword().length == 0;
        String placeholder = MetroUIComponent.getPropertyPasswordFieldPlaceholder(passwordField.getName());
        if (placeholder != null)
            installPlaceholder(g2d, placeholder);
    }
    private void installPlaceholder(Graphics2D g2d, String placeholder) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.decode(COLOR_PLACEHOLDER));
        g2d.drawString((passwordFieldVacio && strPasswordAux.length() == 0) ? placeholder : "", UITools.PADDING_CONTENTS * 2, (passwordField.getSize().height) / 2 + (passwordField.getFont().getSize() / 2) - (UITools.PADDING_CONTENTS / 2));
    }
    private void installBackground(Graphics2D g2d) {
        passwordField.setBackground(null);
        passwordField.setSelectionColor(MetroUIConfigTheme.getPrimaryColor());
        passwordField.setSelectedTextColor(Color.WHITE);
        passwordField.setForeground(Color.decode(COLOR_FOREGROUND));
        passwordField.setCaretColor(Color.decode(COLOR_CARET));
        sizeButton = passwordField.getHeight() - (UITools.PADDING_CONTENTS * 2);

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f);
        g2d.setComposite(ac);
        g2d.setColor(Color.decode(COLOR_BACKGROUND));
        g2d.fillRect(0, 0, super.getComponent().getWidth(), super.getComponent().getHeight());
    }
    private void installFont() {
        passwordField.setFont(new Font(UITools.FONT_DEFAULT, passwordField.getFont().getStyle(), passwordField.getFont().getSize()));
    }
    private void installPadding() {
        passwordField.setBorder(BorderFactory.createCompoundBorder(passwordField.getBorder(), BorderFactory.createEmptyBorder(UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, sizeButton + UITools.PADDING_CONTENTS)));
    }
    private void listenerVerPassword(Graphics2D g2d) {
        if(swBtnVerPresionado){
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // g.setFont(new Font(UITools.COLOR_FONT_DEFAULT, passwordField.getFont().getStyle(), passwordField.getFont().getSize()));
            g2d.setColor(passwordField.getForeground());
            g2d.drawString(strPasswordAux, passwordField.getMargin().left + 5, ((passwordField.getSize().height) / 2) + (passwordField.getFont().getSize() / 2) - 1);
        }
    }
    private void installEventsPasswordField() {
        if (!inicializado) {
            passwordField.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) { }

                @Override
                public void mousePressed(MouseEvent e) { }

                @Override
                public void mouseReleased(MouseEvent e) { }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // btnVerPassword.setVisible(true);
                    verButton = true;
                    if (currentStatePasswordField != STATE_FOCUS)
                        currentStatePasswordField = STATE_OVER;
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // btnVerPassword.setVisible(false);
                    verButton = false;
                    if (currentStatePasswordField != STATE_FOCUS)
                        currentStatePasswordField = STATE_DEFAULT;
                }
            });
            passwordField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    currentStatePasswordField = STATE_FOCUS;
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (currentStatePasswordField != STATE_OVER)
                        currentStatePasswordField = STATE_DEFAULT;
                }
            });
            passwordField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void removeUpdate(DocumentEvent e) {
                    passwordFieldVacio = !(passwordField.getPassword().length > 0);
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    passwordFieldVacio = false;
                }

                @Override
                public void changedUpdate(DocumentEvent de) {}
            });
            inicializado = true;
        }
    }
    private void installEventsButtonVerPassword() {
        if (!inicializado) {
            // passwordField.setSize(new Dimension(passwordField.getWidth(), 30));
            // btnVerPassword.setVisible(false);
            btnVerPassword.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) { }

                @Override
                public void mousePressed(MouseEvent e) {
                    strPasswordAux = ((JTextField) btnVerPassword.getParent()).getText();
                    ((JTextField) btnVerPassword.getParent()).setText("");
                    swBtnVerPresionado = true;
                    if (!MetroUIConfigTheme.isDarkMode())
                        btnVerPassword.setOpaque(true);
                    passwordField.setFocusable(false);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    ((JTextField) btnVerPassword.getParent()).setText(strPasswordAux);
                    strPasswordAux = "";
                    swBtnVerPresionado = false;
                    btnVerPassword.setOpaque(false);
                    passwordField.setFocusable(true);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // btnVerPassword.setVisible(true);
                    verButton = true;
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // btnVerPassword.setVisible(false);
                    verButton = false;
                }
            });
        }
    }
    /**
     * Agrega el botón de ver password
     */
    private void addButtonVerPassword(){
        btnVerPassword.setText("");
        btnVerPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        passwordField.setVisible(true);
        buttonVerPasswordResized();
    }
    /**
     * Ajusta la dimensión y posición del botón X
     */
    private void buttonVerPasswordResized() {
        sizeButton = passwordField.getSize().width - (UITools.PADDING_CONTENTS * 2);
        if (sizeButton > 20) // El botón no debería tener un ancho mayor a 20
            sizeButton = 20;
        btnVerPassword.setSize(new Dimension(sizeButton, passwordField.getSize().height - (UITools.PADDING_CONTENTS * 2)));
        btnVerPassword.setPreferredSize(new Dimension(sizeButton, passwordField.getSize().height - (UITools.PADDING_CONTENTS * 2)));

        btnVerPassword.setLocation(passwordField.getWidth() - btnVerPassword.getWidth() - UITools.PADDING_CONTENTS, passwordField.getHeight()- btnVerPassword.getHeight()- UITools.PADDING_CONTENTS);
        btnVerPassword.setIcon(createViewPasswordImage(sizeButton, sizeButton, (currentStatePasswordField == STATE_FOCUS) ? UITools.colorToHex(MetroUIConfigTheme.getPrimaryColor()) : COLOR_BUTTON_ICON, 2));
        passwordField.add(btnVerPassword);
    }
    
    /**
     * Genera la imagen para el icono de ver password
     * @param width
     * @param height
     * @param colorHex
     * @param stroke
     * @return 
     */
    private ImageIcon createViewPasswordImage(int width, int height, String colorHex, int stroke) {
        // Se utiliza el width en todo y no así al height para que la imagen siempre sea cuadrada
        BufferedImage image = new BufferedImage(width + UITools.PADDING_CONTENTS, height + UITools.PADDING_CONTENTS, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D)image.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

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
        g2d.setComposite(ac);

        // Icono del botón clear
        g2d.setStroke(new BasicStroke(stroke));

        // Las ubicaciones se miden en porcentaje para que no se deforme si crece
        int puntoMedio = (width / 2);
        int inicioX = puntoMedio - (puntoMedio / 2);
        int inicioY = inicioX;

        g2d.setColor(Color.decode(colorHex));
        g2d.drawArc(0, inicioY, width, width, 10, 160);
        g2d.fillOval(inicioX, inicioY + 2, width / 2, width / 2);
        
        return new ImageIcon(image);
    }

    private void installBorder() {
        switch (currentStatePasswordField) {
            case STATE_DEFAULT:
                passwordField.setBorder(new LineBorder(Color.decode(COLOR_BORDER), 2));
                break;
            case STATE_OVER:
                passwordField.setBorder(new LineBorder(Color.decode(UITools.bajarBrillo(COLOR_BORDER)), 2));
                break;
            case STATE_FOCUS:
                passwordField.setBorder(new LineBorder(MetroUIConfigTheme.getPrimaryColor(), 2));
                break;
        }
    }
}
