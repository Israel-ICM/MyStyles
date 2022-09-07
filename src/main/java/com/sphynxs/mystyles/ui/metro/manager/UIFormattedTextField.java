package com.sphynxs.mystyles.ui.metro.manager;

import com.sphynxs.mystyles.ui.metro.tools.MetroUIConfigTheme;
import com.sphynxs.mystyles.ui.metro.tools.MetroUIStyleColors;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicFormattedTextFieldUI;

/**
 * Asigna estilos a todas las areas de texto
 * @author israel-icm
 */
public class UIFormattedTextField extends BasicFormattedTextFieldUI {
    private String COLOR_BACKGROUND = MetroUIStyleColors.TEXT_FIELD_BACKGROUND;
    private String COLOR_BORDER = MetroUIStyleColors.TEXT_FIELD_BORDER;
    private String COLOR_FOREGROUND = MetroUIStyleColors.TEXT_FIELD_FOREGROUND;
    private String COLOR_CARET = MetroUIStyleColors.TEXT_FIELD_CARET;
    
    private static final int STATE_DEFAULT = 1;
    private static final int STATE_FOCUS = 2;
    private static final int STATE_OVER = 3;
    private int currentStateFormatted = 1; // Controla los estados del textfield

    private JFormattedTextField formattedTextField;
    private boolean inicializado = false;

    public static ComponentUI createUI(JComponent c) {
        return new UIFormattedTextField();
    }

    @Override
    protected void paintBackground(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        super.paintBackground(g2d);
        formattedTextField = (JFormattedTextField)super.getComponent();

        installColors();
        installBackground(g2d);
        installFont();
        installBorder();
        installEventsFormattedField();
        installPadding();
    }
    
    private void installColors() {
        if (MetroUIConfigTheme.isDarkMode()) {
            COLOR_BACKGROUND = MetroUIStyleColors.TEXT_FIELD_BACKGROUND_DARK;
            COLOR_BORDER = MetroUIStyleColors.TEXT_FIELD_BORDER_DARK;
            COLOR_FOREGROUND = MetroUIStyleColors.TEXT_FIELD_FOREGROUND_DARK;
            COLOR_CARET = MetroUIStyleColors.TEXT_FIELD_CARET_DARK;
        }
    }
    
    private void installBackground(Graphics2D g2d) {
        formattedTextField.setBackground(null);
        formattedTextField.setSelectionColor(MetroUIConfigTheme.getPrimaryColor());
        formattedTextField.setSelectedTextColor(Color.WHITE);
        formattedTextField.setForeground(Color.decode(COLOR_FOREGROUND));
        formattedTextField.setCaretColor(Color.decode(COLOR_CARET));

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f);
        g2d.setComposite(ac);
        g2d.setColor(Color.decode(COLOR_BACKGROUND));
        g2d.fillRect(0, 0, super.getComponent().getWidth(), super.getComponent().getHeight());
    }
    
    private void installFont() {
        formattedTextField.setFont(new Font(UITools.FONT_DEFAULT, formattedTextField.getFont().getStyle(), formattedTextField.getFont().getSize()));
    }
    
    private void installBorder() {
        switch (currentStateFormatted) {
            case STATE_DEFAULT:
                formattedTextField.setBorder(new LineBorder(Color.decode(COLOR_BORDER), 2));
                break;
            case STATE_OVER:
                formattedTextField.setBorder(new LineBorder(Color.decode(UITools.bajarBrillo(COLOR_BORDER)), 2));
                break;
            case STATE_FOCUS:
                formattedTextField.setBorder(new LineBorder(MetroUIConfigTheme.getPrimaryColor(), 2));
                break;
        }
    }
    private void installEventsFormattedField() {
        if (!inicializado) {
            formattedTextField.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) { }

                @Override
                public void mousePressed(MouseEvent e) { }

                @Override
                public void mouseReleased(MouseEvent e) { }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (currentStateFormatted != STATE_FOCUS)
                        currentStateFormatted = STATE_OVER;
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (currentStateFormatted != STATE_FOCUS)
                        currentStateFormatted = STATE_DEFAULT;
                }
            });
            formattedTextField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    currentStateFormatted = STATE_FOCUS;
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (currentStateFormatted != STATE_OVER)
                        currentStateFormatted = STATE_DEFAULT;
                }
            });
            inicializado = true;
        }
    }
    private void installPadding() {
        // Agregamos los padding normalmente después de agregar el botón o icono
        formattedTextField.setBorder(BorderFactory.createCompoundBorder(formattedTextField.getBorder(), BorderFactory.createEmptyBorder(UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS)));
    }
}
