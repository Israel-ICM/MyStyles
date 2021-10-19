package icm.sphynx.ui.components.metro;

import icm.sphynx.ui.tools.StyleColorsMetro;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTextAreaUI;

/**
 * Asigna estilos a todas las areas de texto
 * @author israel-icm
 */
public class UITextArea extends BasicTextAreaUI {
    private final String _colorBackground = StyleColorsMetro.COLOR_BACKGROUND_TEXT_FIELD;
    private final String _colorBorder = StyleColorsMetro.COLOR_BORDER_TEXT_FIELD;
    private final String _colorText = "#000000";
    private final String _colorPlaceholder = "#8F8F90";
    
    private static final int STATE_DEFAULT = 1;
    private static final int STATE_FOCUS = 2;
    private static final int STATE_OVER = 3;
    private int currentStateTextArea = 1; // Controla los estados del textfield

    private JTextArea textArea;
    private boolean inicializado = false;
    private boolean textAreaVacio = false;

    public static ComponentUI createUI(JComponent c) {
        return new UITextArea();
    }

    @Override
    protected void paintBackground(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        super.paintBackground(g2d);
        textArea = (JTextArea)super.getComponent();

        installBackground(g2d);
        installFont();
        installBorder();
        installProperties(g2d);
        installEvents();
        // textField.setBorder(BorderFactory.createCompoundBorder(textField.getBorder(), BorderFactory.createEmptyBorder(UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS)));
    }
    private void installProperties(Graphics2D g2d) {
        // Placeholder
        textAreaVacio = textArea.getText().length() == 0;
        String placeholder = MetroUIComponent.getPropertyTextAreaPlaceholder(textArea.getName());
        if (placeholder != null)
            installPlaceholder(g2d, placeholder);
    }

    private void installPlaceholder(Graphics2D g2d, String placeholder) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.decode(_colorPlaceholder));
        g2d.drawString((textAreaVacio) ? placeholder : "", UITools.PADDING_CONTENTS, 15);
    }
    private void installEvents() {
        if (!inicializado) {
            textArea.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) { }

                @Override
                public void mousePressed(MouseEvent e) { }

                @Override
                public void mouseReleased(MouseEvent e) { }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (currentStateTextArea != STATE_FOCUS)
                        currentStateTextArea = STATE_OVER;
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (currentStateTextArea != STATE_FOCUS)
                        currentStateTextArea = STATE_DEFAULT;
                }
            });
            textArea.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    currentStateTextArea = STATE_FOCUS;
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (currentStateTextArea != STATE_OVER)
                        currentStateTextArea = STATE_DEFAULT;
                }
            });
            textArea.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void removeUpdate(DocumentEvent e) {
                    textAreaVacio = !(textArea.getText().length() > 0);
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    textAreaVacio = false;
                }

                @Override
                public void changedUpdate(DocumentEvent de) {}
            });
            inicializado = true;
        }
    }
    private void installFont() {
        textArea.setFont(new Font(UITools.FONT_DEFAULT, textArea.getFont().getStyle(), textArea.getFont().getSize()));
    }
    private void installBackground(Graphics2D g2d) {
        textArea.setBackground(null);
        textArea.setSelectionColor(MetroUIConfigTheme.getPrimaryColor());
        textArea.setSelectedTextColor(Color.WHITE);

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f);
        g2d.setComposite(ac);
        g2d.setColor(Color.decode(_colorBackground));
        if (MetroUIConfigTheme.isDarkMode())
            g2d.setColor(Color.decode("#A6A6A6"));
        g2d.fillRect(0, 0, super.getComponent().getWidth(), super.getComponent().getHeight());
    }
    
    private void installBorder() {
        switch (currentStateTextArea) {
            case STATE_DEFAULT:
                textArea.setBorder(new LineBorder(Color.decode(_colorBorder), 2));
                break;
            case STATE_OVER:
                textArea.setBorder(new LineBorder(Color.decode(UITools.bajarBrillo(_colorBorder)), 2));
                break;
            case STATE_FOCUS:
                textArea.setBorder(new LineBorder(MetroUIConfigTheme.getPrimaryColor(), 2));
                break;
        }
    }
}
