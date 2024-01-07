package Design_Libraries;

import javax.swing.border.EmptyBorder;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.basic.*;

public class ComboSuggestionUI extends BasicComboBoxUI{

Image arrowbutton;
    
    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        Border border = new Border();
        JTextField txt = (JTextField)comboBox.getEditor().getEditorComponent();
        
        txt.addFocusListener(new FocusAdapter(){
            
            public void focusGained(FocusEvent e) {
                border.setColor(new Color(128, 189, 255));
            }    
            
            public void focusLost(FocusEvent e) {
                border.setColor(new Color(162, 162, 162));
            }
            
        });
        
        AutoCompleteDecorator.decorate(comboBox);
        txt.setSelectionColor(new Color(54, 189, 248));
        txt.setBorder(new EmptyBorder(0, 10, 0, 10));
        comboBox.setBackground(Color.WHITE);
        comboBox.setBorder(border);
    }
 
    protected JButton createArrowButton() {
        return new ArrowButton();
    }
    
    protected ComboPopup createPopup() {
        return new ComboSuggestionPopup(comboBox);
    }
    
    protected ListCellRenderer createRenderer() {
        return new ListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList jlist, Object e, int i, boolean bln, boolean bln1) {
                String text = e == null ? "" : e.toString();
                JLabel label = new JLabel(text);
                label.setFont(comboBox.getFont());
                if (i >= 0) {
                    label.setBorder(new EmptyBorder(5, 8, 5, 8));
                } else {
                    label.setBorder(null);
                }
                if (bln) {
                    label.setOpaque(true);
                    label.setBackground(new Color(240, 240, 240));
                    label.setForeground(new Color(17, 155, 215));
                }
                return label;
            }
        };
    }
    
    public void paintCurrentValueBackground(Graphics grphcs, Rectangle rctngl, boolean bln) {
        
    }
    
    private class ComboSuggestionPopup extends BasicComboPopup {
        
        public ComboSuggestionPopup(JComboBox combo) {
            super(combo);
            setBorder(new Border(1));
        }
        
        protected JScrollPane createScroller() {
            JScrollPane scroll = super.createScroller();
            list.setBackground(Color.WHITE);
            ScrollBarCustom sb = new ScrollBarCustom();
            sb.setPreferredSize(new Dimension(12, 70));
            scroll.setVerticalScrollBar(sb);
            ScrollBarCustom sbH = new ScrollBarCustom();
            sbH.setOrientation(JScrollBar.HORIZONTAL);
            scroll.setHorizontalScrollBar(sbH);
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            return scroll;
        }
    }
    
    private class ArrowButton extends JButton {
        
        public ArrowButton() {
            setContentAreaFilled(false);
            setBorder(new EmptyBorder(15, 15, 15, 15));
            setBackground(new Color(150, 150, 150));
        }
        
        @Override
        public void paint(Graphics grphcs) {
            super.paint(grphcs); 
            
            Graphics2D g2 = (Graphics2D) grphcs.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            arrowbutton = new ImageIcon("img/arrow.png").getImage();
            g2.drawImage(arrowbutton, 2, 15, 30, 12, null);
            
            g2.dispose();
        }
    }
    
    
    private class Border extends EmptyBorder {

        public Color getFocusColor() {
            return focusColor;
        }

        public void setFocusColor(Color focusColor) {
            this.focusColor = focusColor;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }
        
        private Color focusColor = new Color(128, 189, 255);
        private Color color = new Color(162, 162, 162);

        public Border(int border) {
            super(border, border, border, border);
        }
        
        public Border() {
            this(5);
        }
        
        @Override
        public void paintBorder(Component cmpnt, Graphics grphcs, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) grphcs.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            if(cmpnt.isFocusOwner()) {
                g2.setColor(getFocusColor());
            } else {
                g2.setColor(getColor());
            }
            
            g2.drawRect(x, y, width - 1, height - 1);
            g2.dispose();
        }

    }
}
