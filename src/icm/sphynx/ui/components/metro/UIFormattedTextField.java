package icm.sphynx.ui.components.metro;

import icm.sphynx.ui.tools.StyleColorsMetro;
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
    private final String colorBackground = StyleColorsMetro.COLOR_BACKGROUND_TEXT_FIELD;
    private final String colorBorder = StyleColorsMetro.COLOR_BORDER_TEXT_FIELD;
    
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
        formattedTextField.setBackground(null);

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f);
        g2d.setComposite(ac);
        g2d.setColor(Color.decode(colorBackground));
        g2d.fillRect(0, 0, super.getComponent().getWidth(), super.getComponent().getHeight());

        formattedTextField.setFont(new Font(UITools.FONT_DEFAULT, formattedTextField.getFont().getStyle(), formattedTextField.getFont().getSize()));
        installBorder();
        formattedTextField.setBorder(BorderFactory.createCompoundBorder(formattedTextField.getBorder(), BorderFactory.createEmptyBorder(UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS)));
        formattedTextField.setSelectionColor(MetroUIConfigTheme.getPrimaryColor());
        formattedTextField.setSelectedTextColor(Color.WHITE);
        
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
    
    private void installBorder() {
        switch (currentStateFormatted) {
            case STATE_DEFAULT:
                formattedTextField.setBorder(new LineBorder(Color.decode(colorBorder), 2));
                break;
            case STATE_OVER:
                formattedTextField.setBorder(new LineBorder(Color.decode(UITools.bajarBrillo(colorBorder)), 2));
                break;
            case STATE_FOCUS:
                formattedTextField.setBorder(new LineBorder(MetroUIConfigTheme.getPrimaryColor(), 2));
                break;
        }
    }
}
