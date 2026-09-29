package housemanagementsystem;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * A small, dependency-free modern theme for Swing.
 * <p>
 * Call {@link #apply()} once before creating the first window, then call
 * {@link #style(java.awt.Container)} on the content pane of every window after
 * {@code initComponents()}. All of the House Management System screens already
 * do this.
 */
public final class ModernTheme {

    // ---- Palette ----------------------------------------------------------
    public static final Color BACKGROUND    = new Color(0xF4, 0xF6, 0xFB); // page background
    public static final Color SURFACE       = Color.WHITE;                  // cards / fields / tables
    public static final Color PRIMARY       = new Color(0x4F, 0x46, 0xE5); // indigo
    public static final Color PRIMARY_HOVER = new Color(0x43, 0x38, 0xCA);
    public static final Color PRIMARY_PRESS = new Color(0x37, 0x2F, 0xA8);
    public static final Color TEXT          = new Color(0x1F, 0x29, 0x37); // near-black
    public static final Color TEXT_MUTED    = new Color(0x6B, 0x72, 0x80);
    public static final Color BORDER        = new Color(0xD1, 0xD5, 0xDB);
    public static final Color GRID          = new Color(0xEA, 0xEC, 0xF0);
    public static final Color SUCCESS       = new Color(0x10, 0xB9, 0x81);
    public static final Color DANGER        = new Color(0xEF, 0x44, 0x44);

    // ---- Fonts ------------------------------------------------------------
    public static final Font FONT       = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONT_BOLD  = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 26);

    private ModernTheme() {
    }

    /**
     * Installs the look and feel and global defaults. Should run once, before
     * any window is constructed.
     */
    public static void apply() {
        // Prefer FlatLaf if the user added it, otherwise fall back to Nimbus.
        if (!tryLookAndFeel("com.formdev.flatlaf.FlatLightLaf")
                && !tryLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel")) {
            // Keep the system look and feel.
        }
        setGlobalDefaults();
    }

    private static boolean tryLookAndFeel(String className) {
        try {
            UIManager.setLookAndFeel(className);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private static void setGlobalDefaults() {
        UIManager.put("defaultFont", FONT);
        UIManager.put("Button.font", FONT_BOLD);
        UIManager.put("Label.font", FONT);
        UIManager.put("TextField.font", FONT);
        UIManager.put("PasswordField.font", FONT);
        UIManager.put("TextArea.font", FONT);
        UIManager.put("ComboBox.font", FONT);
        UIManager.put("TabbedPane.font", FONT_BOLD);
        UIManager.put("Table.font", FONT);
        UIManager.put("TableHeader.font", FONT_BOLD);
        UIManager.put("MenuBar.font", FONT);
        UIManager.put("Menu.font", FONT);
        UIManager.put("MenuItem.font", FONT);
        UIManager.put("Panel.background", BACKGROUND);
        UIManager.put("Label.foreground", TEXT);
    }

    /**
     * Recursively restyles every Swing component inside {@code root} with the
     * modern look. Safe to call again on an already styled container.
     */
    public static void style(Container root) {
        if (root == null) {
            return;
        }
        for (java.awt.Component child : root.getComponents()) {
            if (child instanceof JButton) {
                styleButton((JButton) child);
            } else if (child instanceof JPasswordField) {
                styleField((JPasswordField) child);
            } else if (child instanceof JTextField) {
                styleField((JTextField) child);
            } else if (child instanceof JTable) {
                styleTable((JTable) child);
            } else if (child instanceof JTabbedPane) {
                styleTabbedPane((JTabbedPane) child);
            } else if (child instanceof JScrollPane) {
                ((JScrollPane) child).getViewport().setBackground(SURFACE);
                style(child);
            } else if (child instanceof JLabel) {
                styleLabel((JLabel) child);
            } else if (child instanceof JPanel) {
                stylePanel((JPanel) child);
            } else if (child instanceof JRadioButton || child instanceof JCheckBox) {
                styleToggle((AbstractButton) child);
            } else if (child instanceof JComboBox) {
                styleComboBox((JComboBox<?>) child);
            } else if (child instanceof JMenuBar) {
                styleMenuBar((JMenuBar) child);
            } else if (child instanceof Container) {
                style((Container) child);
            }
        }
    }

    private static void styleButton(JButton b) {
        b.setUI(new ModernButtonUI(PRIMARY, PRIMARY_HOVER, PRIMARY_PRESS));
        b.setForeground(Color.WHITE);
        b.setFont(FONT_BOLD);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setFocusPainted(false);
        b.setRolloverEnabled(true);
        b.setBorder(new EmptyBorder(9, 22, 9, 22));
    }

    private static void styleField(JTextField f) {
        f.setFont(FONT);
        f.setBackground(SURFACE);
        f.setForeground(TEXT);
        f.setCaretColor(PRIMARY);
        f.setBorder(fieldBorder());
    }

    private static Border fieldBorder() {
        return new CompoundBorder(
                new LineBorder(BORDER, 1, true),
                new EmptyBorder(8, 12, 8, 12));
    }

    private static void styleTable(JTable t) {
        t.setFont(FONT);
        t.setRowHeight(32);
        t.setBackground(SURFACE);
        t.setForeground(TEXT);
        t.setGridColor(GRID);
        t.setShowVerticalLines(false);
        t.setIntercellSpacing(new Dimension(0, 0));
        t.setSelectionBackground(PRIMARY);
        t.setSelectionForeground(Color.WHITE);
        t.setFillsViewportHeight(true);

        JTableHeader header = t.getTableHeader();
        if (header != null) {
            header.setFont(FONT_BOLD);
            header.setBackground(PRIMARY);
            header.setForeground(Color.WHITE);
            header.setPreferredSize(new Dimension(header.getPreferredSize().width, 36));
            header.setReorderingAllowed(false);

            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setBackground(PRIMARY);
            renderer.setForeground(Color.WHITE);
            renderer.setHorizontalAlignment(SwingConstants.LEFT);
            renderer.setBorder(new EmptyBorder(6, 12, 6, 12));
            header.setDefaultRenderer(renderer);
        }
    }

    private static void styleTabbedPane(JTabbedPane tp) {
        tp.setFont(FONT_BOLD);
        tp.setBackground(BACKGROUND);
        tp.setForeground(TEXT);
    }

    private static void styleLabel(JLabel l) {
        l.setForeground(TEXT);
        Font f = l.getFont();
        if (f != null && f.getSize() >= 20) {
            l.setFont(FONT_TITLE);
            l.setForeground(PRIMARY);
        }
    }

    private static void stylePanel(JPanel p) {
        p.setBackground(BACKGROUND);
    }

    private static void styleToggle(AbstractButton b) {
        b.setFont(FONT);
        b.setForeground(TEXT);
        b.setBackground(BACKGROUND);
        b.setOpaque(false);
    }

    private static void styleComboBox(JComboBox<?> cb) {
        cb.setFont(FONT);
        cb.setBackground(SURFACE);
        cb.setForeground(TEXT);
    }

    private static void styleMenuBar(JMenuBar mb) {
        mb.setBackground(SURFACE);
        mb.setFont(FONT);
        for (int i = 0; i < mb.getMenuCount(); i++) {
            JMenu menu = mb.getMenu(i);
            menu.setFont(FONT);
            for (int j = 0; j < menu.getItemCount(); j++) {
                JMenuItem item = menu.getItem(j);
                if (item != null) {
                    item.setFont(FONT);
                }
            }
        }
    }

    /**
     * A flat, rounded button look. It paints the rounded background first and
     * lets {@link BasicButtonUI} draw the icon/text on top.
     */
    private static final class ModernButtonUI extends BasicButtonUI {

        private final Color base;
        private final Color hover;
        private final Color press;

        ModernButtonUI(Color base, Color hover, Color press) {
            this.base = base;
            this.hover = hover;
            this.press = press;
        }

        @Override
        protected void installDefaults(AbstractButton b) {
            super.installDefaults(b);
            b.setOpaque(false);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            AbstractButton b = (AbstractButton) c;
            Color bg = base;
            if (!b.getModel().isEnabled()) {
                bg = new Color(
                        (base.getRed() + 180) / 2,
                        (base.getGreen() + 180) / 2,
                        (base.getBlue() + 180) / 2);
            } else if (b.getModel().isPressed()) {
                bg = press;
            } else if (b.getModel().isRollover()) {
                bg = hover;
            }

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bg);
            g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 14, 14);
            g2.dispose();

            super.paint(g, c);
        }
    }
}
