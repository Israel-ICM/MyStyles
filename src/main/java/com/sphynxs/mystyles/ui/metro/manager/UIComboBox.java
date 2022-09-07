package com.sphynxs.mystyles.ui.metro.manager;

import com.sphynxs.mystyles.ui.metro.tools.MetroUIConfigTheme;
import com.sphynxs.mystyles.ui.metro.tools.MetroUIComponent;
import com.sphynxs.mystyles.ui.metro.components.BorderShadow;
import com.sphynxs.mystyles.ui.metro.tools.MetroUIStyleColors;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
    private String COLOR_BACKGROUND = MetroUIStyleColors.COMBOBOX_BACKGROUND;
    private String COLOR_BORDER = MetroUIStyleColors.COMBOBOX_BORDER;
    private String COLOR_FOREGROUND = MetroUIStyleColors.COMBOBOX_FOREGROUND;
    private String COLOR_ICON_BUTTON = MetroUIStyleColors.COMBOBOX_BUTTON_ICON;
    private String COLOR_POPUP_BACKGROUND = MetroUIStyleColors.COMBOBOX_POPUP_BACKGROUND;
    private String COLOR_POPUP_ITEM_OVER = MetroUIStyleColors.COMBOBOX_POPUP_ITEM_OVER;
    private String COLOR_POPUP_ITEM_SELECTED = UITools.colorToHex(MetroUIConfigTheme.getThirdColor());
    private String COLOR_POPUP_ITEM_SELECTED_OVER = UITools.colorToHex(MetroUIConfigTheme.getFourthColor());
    
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
        installColors();
        installEvents();
        installBackground();
        installBorder();
        installSize();
    }
    
    private void installColors() {
        if (MetroUIConfigTheme.isDarkMode()) {
            COLOR_BACKGROUND = MetroUIStyleColors.COMBOBOX_BACKGROUND_DARK;
            COLOR_FOREGROUND = MetroUIStyleColors.COMBOBOX_FOREGROUND_DARK;
            COLOR_BORDER = MetroUIStyleColors.COMBOBOX_BORDER_DARK;
            COLOR_ICON_BUTTON = MetroUIStyleColors.COMBOBOX_BUTTON_ICON_DARK;
            COLOR_POPUP_BACKGROUND = MetroUIStyleColors.COMBOBOX_POPUP_BACKGROUND_DARK;
            COLOR_POPUP_ITEM_OVER = MetroUIStyleColors.COMBOBOX_POPUP_ITEM_OVER_DARK;
            COLOR_POPUP_ITEM_SELECTED = UITools.colorToHex(MetroUIConfigTheme.getSecondColor());
            COLOR_POPUP_ITEM_SELECTED_OVER = UITools.colorToHex(MetroUIConfigTheme.getPrimaryColor());
        }
        else {
            COLOR_POPUP_ITEM_SELECTED = UITools.colorToHex(MetroUIConfigTheme.getThirdColor());
            COLOR_POPUP_ITEM_SELECTED_OVER = UITools.colorToHex(MetroUIConfigTheme.getFourthColor());
        }
    }
    
    private void installSize() {
        // if (comboBox.getHeight() <= 26)
        comboBox.setPreferredSize(new Dimension(comboBox.getWidth(), 28));
        // c.setBorder(BorderFactory.createCompoundBorder(c.getBorder(), BorderFactory.createEmptyBorder(UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS)));
    }
    
    private void installBackground() {
        comboBox.setBackground(Color.decode(COLOR_BACKGROUND));
        comboBox.setForeground(Color.decode(COLOR_FOREGROUND));
    }
    
    private void installBorder() {
        if (currentStateTextField == STATE_DEFAULT)
            comboBox.setBorder(new LineBorder(Color.decode(COLOR_BORDER), 2));
        else
            comboBox.setBorder(new LineBorder(Color.decode(UITools.bajarBrillo(COLOR_BORDER)), 2));
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
        button.setBorder(BorderFactory.createLineBorder(Color.decode(COLOR_BACKGROUND), 0));
        button.setContentAreaFilled(false);
        button.setIcon(createCbxImage(20, 20));
        button.setFocusable(false);
        button.setBackground(Color.decode(COLOR_BACKGROUND));
        return button;
    }

    @Override
    protected ComboPopup createPopup() {
        final int borde = 10;
        BasicComboPopup popupCombobox = new BasicComboPopup(comboBox) {
            @Override
            protected Rectangle computePopupBounds(int x, int y, int width, int height) {
                if (MetroUIConfigTheme.isDarkMode() && UITools.isMacOS()) {
                    return super.computePopupBounds(x, y - comboBox.getHeight(), width, height);
                }
                else if (MetroUIConfigTheme.isDarkMode() && !UITools.isMacOS()) {
                    return super.computePopupBounds(x, y - comboBox.getHeight(), width + 2, height);
                }
                else {
                    // Aquí subimos un poco la posición en "y" del popup del combobox
                    return super.computePopupBounds(x - borde, y - (comboBox.getHeight() + borde) - 4, width + (borde * 2), height + (borde));
                }
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
                //if (UITools.isMacOS() || MetroUIConfigTheme.isDarkMode()) {
                    super.show();
                //}
                /*else {
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
                }*/
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

                scroller.setMaximumSize(scrollSize);
                scroller.setPreferredSize(scrollSize);
                scroller.setMinimumSize(scrollSize);

                list.revalidate();
                return popupLocation;
            }
        };

        if (!MetroUIConfigTheme.isDarkMode() && !UITools.isMacOS())
            popupCombobox.setBorder(new BorderShadow(Color.decode("#D8D8D8"), 10));
        popupCombobox.getAccessibleContext().setAccessibleParent(comboBox);
        
        return popupCombobox;
    }

    @Override
    public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus){
        // Establecemos los estilos para el item seleccionado con el combobox cerrado (La primera vez)
        g.setColor(Color.decode(COLOR_BACKGROUND));
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
    
    @Override
    protected ListCellRenderer createRenderer(){
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value,int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                list.setSelectionBackground(Color.decode(COLOR_BACKGROUND)); // Este color es del item seleccionado después de la primera apertura
                list.setSelectionForeground(Color.decode(COLOR_FOREGROUND));

                if (comboBox.getSelectedIndex() == index) { // Si el item esta seleccionado
                    setBackground(Color.decode(COLOR_POPUP_ITEM_SELECTED));
                    setForeground(Color.decode(COLOR_FOREGROUND));
                }
                else if (isSelected && comboBox.getSelectedIndex() != index) { // Si el item no esta seleccionado pero el cursor esta encima
                    setBackground(Color.decode(COLOR_POPUP_ITEM_OVER));
                    setForeground(Color.decode(COLOR_FOREGROUND));
                }
                if (isSelected && comboBox.getSelectedIndex() == index) { // Si el item esta seleccionado y el cursor esta encima
                    setBackground(Color.decode(COLOR_POPUP_ITEM_SELECTED_OVER));
                    setForeground(Color.decode(COLOR_FOREGROUND));
                }
                else if (!isSelected && comboBox.getSelectedIndex() != index) { // Si el item no esta seleccionado
                    setBackground(Color.decode(COLOR_POPUP_BACKGROUND));
                    setForeground(Color.BLACK);
                }
                setFont(new Font(UITools.FONT_DEFAULT, Font.PLAIN, 13));
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
        
        g2d.setColor(Color.decode(COLOR_ICON_BUTTON));
        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine(5, yIniFin, xPuntoMedio, yPuntoMedio);
        g2d.drawLine(xPuntoMedio, yPuntoMedio, width - 5, yIniFin);
        return new ImageIcon(image);
    }
}
