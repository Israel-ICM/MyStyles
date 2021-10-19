package icm.sphynx.ui.components.metro;

import icm.sphynx.ui.tools.StyleColorsMetro;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

/**
 * Asigna los estilos para todos los combobox
 * @author israel-icm
 */
public class UIComboBox extends BasicComboBoxUI {
    private final String _colorBackground = StyleColorsMetro.COLOR_BACKGROUND_COMBOBOX;
    private final String _colorBorder = StyleColorsMetro.COLOR_BORDER_COMBOBOX;
    private final String _colorText = "#000000";
    private final String _colorTextSelection = "#000000";
    private final String _colorIconButton = "#838383";
    public static final String BUTTON_NAME = "ButtonComboboxMetroUI";
    
    private static final int STATE_DEFAULT = 1;
    private static final int STATE_OVER = 3;
    private int currentStateTextField = 1; // Controla los estados del combobox
    private boolean componenteIniciado = false;

    public static ComponentUI createUI(JComponent c) {
        return new UIComboBox();
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        installEvents();
        installBackground();
        installBorder();
        installSize();
    }
    
    private void installSize() {
        // if (comboBox.getHeight() <= 26)
        comboBox.setPreferredSize(new Dimension(comboBox.getWidth(), 28));
        // c.setBorder(BorderFactory.createCompoundBorder(c.getBorder(), BorderFactory.createEmptyBorder(UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS)));
    }
    
    private void installBackground() {
        comboBox.setBackground(Color.decode(_colorBackground));
        comboBox.setForeground(Color.decode(_colorText));
    }
    
    private void installBorder() {
        if (currentStateTextField == STATE_DEFAULT)
            comboBox.setBorder(new LineBorder(Color.decode(_colorBorder), 2));
        else
            comboBox.setBorder(new LineBorder(Color.decode(UITools.bajarBrillo(_colorBorder)), 2));
    }
    
    private void installEvents() {
        if (!componenteIniciado) {
            comboBox.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) { }

                @Override
                public void mousePressed(MouseEvent e) { }

                @Override
                public void mouseReleased(MouseEvent e) { }

                @Override
                public void mouseEntered(MouseEvent e) {
                    currentStateTextField = STATE_OVER;
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    currentStateTextField = STATE_DEFAULT;
                }
            });
            componenteIniciado = true;
        }
    }
    
    @Override 
    protected JButton createArrowButton() {
        JButton button = new JButton();
        button.setName(BUTTON_NAME); // Se le asigna un nombre para aplicar la propiedad empty del botón
        MetroUIComponent.setButtonEmpty(button.getName());
        button.setText("");
        button.setBorder(BorderFactory.createLineBorder(Color.decode(_colorBackground), 0));
        button.setContentAreaFilled(false);
        button.setIcon(createCbxImage(20, 20));
        button.setFocusable(false);
        button.setBackground(Color.decode(_colorBackground));
        return button;
    }

    @Override
    protected ComboPopup createPopup() {
        final int borde = 10;
        BasicComboPopup popupCombobox = new BasicComboPopup(comboBox) {
            @Override
            protected Rectangle computePopupBounds(int x, int y, int width, int height) {
                // Aquí subimos un poco la posición en "y" del popup del combobox
                return super.computePopupBounds(x - borde, y - (comboBox.getHeight() + borde) - 4, width + (borde * 2), height + (borde));
            }

            @Override
            protected void togglePopup() {
                if (isVisible())
                    hide();
                else
                    show();
            }

            /**
             * Apertura del popup
             */
            @Override
            public void show() {
                JComponent a = this;
                
                new Thread() {
                    @Override
                    public void run() {
                        // Animación de apertura
                        float crecer = getPopupHeightForRowCount(comboBox.getMaximumRowCount()) + 30;
                        int height = 0;
                        int velocidad = 7;
                        for (int i = 0; i < velocidad; i++) {
                            hide();
                            executePopupCombobox(a, height);

                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) { }
                            height += Math.ceil(crecer / velocidad); // El ceil redondea al inmediato superior
                            if (i == (velocidad - 2)) // Esto es para que la ultima iteración siempre el height tenga el tamaño total
                                height = (int)crecer;
                        }
                    }
                }.start();
            }
            public void executePopupCombobox(JComponent c, int height) {
                c.setPreferredSize(new Dimension(comboBox.getWidth() + 20, height));
                
                Point location = getPopupLocation();
                comboBox.firePopupMenuWillBecomeVisible();
                if (comboBox.getSelectedIndex() == -1)
                    list.clearSelection();
                else {
                    list.setSelectedIndex(comboBox.getSelectedIndex());
                    list.ensureIndexIsVisible(comboBox.getSelectedIndex());
                }
                show(comboBox, location.x, location.y);
            }

            private Point getPopupLocation() {
                // Este método se copió del método original getPopupLocation() que es privado en la clase ComboPopup
                Dimension popupSize = comboBox.getSize();
                Insets insets = getInsets();

                popupSize.setSize(popupSize.width - (insets.right + insets.left), getPopupHeightForRowCount( comboBox.getMaximumRowCount()));
                Rectangle popupBounds = computePopupBounds(0, comboBox.getBounds().height, popupSize.width, popupSize.height);
                Dimension scrollSize = popupBounds.getSize();
                Point popupLocation = popupBounds.getLocation();

                scroller.setMaximumSize( scrollSize );
                scroller.setPreferredSize( scrollSize );
                scroller.setMinimumSize( scrollSize );

                list.revalidate();
                return popupLocation;
            }
        };

        popupCombobox.setBorder(new MetroUIBorderShadow(Color.decode("#D8D8D8"), 10));
        popupCombobox.getAccessibleContext().setAccessibleParent(comboBox);
        
        return popupCombobox;
    }

    @Override
    public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus){
        // Establecemos los estilos para el item seleccionado con el combobox cerrado (La primera vez)
        g.setColor(Color.decode(_colorBackground));
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
    
    @Override
    protected ListCellRenderer createRenderer(){
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value,int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                list.setSelectionBackground(Color.decode(_colorBackground)); // Este color es del item seleccionado después de la primera apertura
                list.setSelectionForeground(Color.decode(_colorText));

                if (comboBox.getSelectedIndex() == index) { // Si el item esta seleccionado
                    setBackground(MetroUIConfigTheme.getThirdColor());
                    setForeground(Color.decode(_colorTextSelection));
                }
                else if (isSelected && comboBox.getSelectedIndex() != index) { // Si el item no esta seleccionado pero el cursor esta encima
                    setBackground(Color.decode("#CCCCCC"));
                    setForeground(Color.decode(_colorTextSelection));
                }
                if (isSelected && comboBox.getSelectedIndex() == index) { // Si el item esta seleccionado y el cursor esta encima
                    setBackground(MetroUIConfigTheme.getFourthColor());
                    setForeground(Color.decode(_colorTextSelection));
                }
                else if (!isSelected && comboBox.getSelectedIndex() != index) { // Si el item no esta seleccionado
                    setBackground(Color.decode("#F3F3F3"));
                    if (MetroUIConfigTheme.isDarkMode())
                        setBackground(Color.decode("#A6A6A6"));
                    setForeground(Color.BLACK);
                }
                return this;
            }
        };
    }
    
    private ImageIcon createCbxImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D)image.getGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        /*AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f);
        g2d.setComposite(ac);*/

        int yIniFin = (height / 2) - 2;
        int yPuntoMedio = (height / 2) + 3;
        int xPuntoMedio = width / 2;
        
        g2d.setColor(Color.decode(_colorIconButton));
        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine(5, yIniFin, xPuntoMedio, yPuntoMedio);
        g2d.drawLine(xPuntoMedio, yPuntoMedio, width - 5, yIniFin);
        return new ImageIcon(image);
    }
}
