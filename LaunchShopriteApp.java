/**
 * @author  <GregJava@OGS Nig ltd>
 */
package shoprite;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;

public class LaunchShopriteApp {
    private static final String appName = "Item Locator";
        
    /** 
     * @param args  represents app runtime arguments
     * The main method runs the GUI using the invoke-later argument.
     */
    public static void main(String[] args){
        /* Schedule a job for the event-dispatching thread: creating and showing this application's GUI. */
        javax.swing.SwingUtilities.invokeLater(() -> {
            for (Object obj : (System.getProperties()).values()) System.out.println(obj);
            createAndShowGUI();
        });
    }
    private static void createAndShowGUI() {
        // Create & Set up the app machine.
        Window machine = new Window();
    }
    private static void findAppInstallLoc(String start, String appName) {
        boolean fileFound = false; int metFirstFile = 0; boolean firstTime = true;
        File file = new File(start);
        String firstFileName = appName;
        do  {
            if (metFirstFile>=1) break;
            if (file.isDirectory()) {
                for (File fil : file.listFiles()){
                    if (firstTime){
                        firstFileName = fil.getName(); firstTime=false;
                    }
                    if (fil.getAbsolutePath().contains(appName)) {
                        file = fil; fileFound = true; 
                        // System.err.println(file.getName());
                        break;
                    }
                    System.out.println(fil.getName());
                    if (fil.getName().equalsIgnoreCase(firstFileName)) metFirstFile++;
                }
            } else {
                if (file.getAbsolutePath().contains(appName)) {
                    fileFound = true; 
                    // System.err.println(file.getName()); 
                    break;
                }
            }
        } while (!fileFound);
        if (!fileFound) {
            // System.err.println("Shoprite default folder not found...");
        } else System.out.println(file.getName());
    }
    private static class Window extends JFrame implements ActionListener{
        private static final ShoppingMall building = new ShoppingMall();
        private static final JComboBox categorySelector = new JComboBox();
        private static JTextField inputPane;
        private static JTextArea infoBoard;
        private static JTabbedPane homePane, infoPane;
        private static JButton itemFinder;
        private static String state, userInput;
        private static ArrayList<Color[]> themeColor = new ArrayList<Color[]>();
        // holds the application themes and categories
        private static ArrayList<String> items = new ArrayList<>(), description = new ArrayList<>(),
                theme = new ArrayList<String>(), category = new ArrayList<String>();
        // holds the application categories' sublist
        private static Map<String, String[]> desc = new HashMap(), itm = new HashMap(); // Holds items and their categories
        private static int SCALE = 1, INSET = 30;
        private static int THEME;
        private static String[] phoneShops = {"Airtel","Best Mobile", "Etisalat","Fine Brothers",
            "Glo World","LG","Pointek","Tecno"};
        private static String[] computerShops = {"Yudala","Best Mobile", "LG"};
        private static String[] electronicShops = {"Fine Brothers","LG", "Marvel"};
        private static String[] fabricShops = {"MRP","Button Up", "Identity","Secret View Lingerie",
            "Truworths","Audacious"};
        private static String[] cosmeticShops = {"Casabella","Essenza", "Pink Sky","Montaigne"};
        private static String[] leatherJewelShops = {"Maybrand","Kontessa","Swatch","a! Accessories","Diva"};
        private static String[] fastfoodShops = {"Kilmanjaro","Aroma","Chicken Republic"};
        private static String[] medShops = {"Medplus","NettPharmacy"};
        private static String[] cinemaShops = {"Genesis Deluxe Cinema"};
        private static String[] sportsShops = {"Sports World"};
        private static String[] shopriteShops = {"Shoprite"};
        private static String[] phoneItems = {"Acer","Alcatel", "Amazon","Apple","Blackberry","Dell",
            "Google","Htc","Infinix","Iphone","Lg","Microsoft","Motorola","Nokia","Sagem",
            "Samsung","Sharp","Sony","Tecno"};
        private static String[] computerItems = {"Acer","Apple","Asus","BenQ","Compaq","Dell","Gateway","HP",
            "Hewlett-Packard","Hewlett Packard","Hitachi","HTC","IBM","Intel","Lenovo","LG","LiteOn",
            "Microsoft","Motorola","NEC","Oracle","Packard Bell","Panasonic","Samsung","Sharp","Sony",
            "Toshiba"};
        private static String[] electronicItems = {"Apple","Bosch","Dell","General Electronics","HP",
            "Hewlett-Packard","Hewlett Packard","Hitachi","IBM","Intel","Lenovo","LG","LiteOn",
            "Microsoft","Motorola","NEC","Oracle","Packard Bell","Panasonic","Samsung","Sharp","Sony",
            "Toshiba"};
        private static String[] fabricItems = {"Traditional","Wear","Suit","Shirt","Trouser", "Socks",
            "Pant","Bra","Singlet","Boxers","Undies","Nighties","Sleeveless","Short Sleeve",
            "Long Sleeve","Knicker","Shorts","Skirt","Wrapper","Underwear","Gown","Handkerchief","Tie",
            "Denim","Jacket","Jeans","Chinos"};
        private static String[] cosmeticItems = {"Powder","Foundation","Lipstick","Eye Pencil","Mascara","Wet Lips",
            "Skin Care","Cream","Lotion","Hair Color","Dye","Hair Spray","Perfume","Nail Polish","Gel","Toiletories",
            "Bath Oil","Bubble Bath","Make Up","Makeup","Make-Up","Waves","Contact Lens","Deodorant","Sanitizer",
            "Bath Salt"};
        private static String[] leatherJewelItems = {"Shoe","Handbag","Wallet","Ring","Necklace","Studs","Eye glasses",
            "Sun glasses","Sun Shades","Cap","Wrist Watch","Earring","Bango","Bracelet","Anklet"};
        private static String[] fastfoodItems = {};
        private static String[] medItems = {};
        private static String[] cinemaItems = {};
        private static String[] sportsItems = {};
        private static String[] shopriteItems = {"Chi-Exotic","Honeywell","Chicken","Wheat Meal","Flour",
            "Yoghurt","Oil","French Fries","Milk","Tea","Oats","Sprite","Coke","Fanta","Soft Drinks","Coca Cola",
            "Chicken Franks","Butter","Vita Milk","Teabags","Candy","Sweet","Biscuit","Crackers","Swan",
            "Vegetables","Mixed Vegetables","Corn","Semolina","Sugar","Knorr","Ovaltine","Beverage","Short-Bread",
            "Short Bread","Shortbread","Water","Juice","Spinach","Green Pea","Fish","Ice Cream","Corn Flakes",
            "Cookies","Chocolate","Scones","Cake","Pasta","Sauce","Salad","Lettuce","Tomato","Mince","Pork",
            "Roast","Boerewors","Roast","Apple","Peer","Grape","Plum","Baby Powder","Baby Cream","Baby Oil","Baby",
            "Johnson's","Johnsons","Johnson","Pampers","Toothpaste","Diapers","Baby wipe","Soap","Hand Wash",
            "Sporting Waves","Styling Gel","Shaving Powder","Mouthwash","Antiseptic","Body Spray","Aerosol",
            "Body Wash","Hand Wash","Washing Powder","Surface Cleaner","Liquid Wash","Bath Cleaner","Toilet Cleaner",
            "Glass Cleaner","Mirror Cleaner","Cleaner","Ciders","Wine","Rum","Brandy","Vodka","Irish Cream","Whisky",
            "Beef","Roast","Steak","Pork","Lamb","Goat","Smoked Russians","Sausages"};
        private static int searchIndex; // 1=phones,2=computers,3=electronics,4=fabrics,5=cosmetics,
        // 6=leather,7=fastfood,8=med,9=cinema,10=sports,11=shoprite
        
        Window(){
            setSystemThemes();
            setThemeNames();
            setDBItems(items);
            setCategories(category);
            setData(desc);
            infoBoard = new JTextArea(12,18);
            infoBoard.setFont(new Font("Arial Narrow",4,11));
            infoBoard.setBackground(Color.green);
            infoBoard.setForeground(Color.black);
            infoBoard.setEditable(false);
            infoBoard.append("\n\nWelcome to Shoprite Item Finder!"
                    + "\n\nThis window will display precise "
                    + "\ndetails of your search items and "
                    + "\nother important alerts."
                    + "\nPlease enter the item you seek "
                    + "\nin the search box above."
                    + "\n\nWhy Pay More...");
            infoPane = new JTabbedPane();
            infoPane.add("Wall", infoBoard);
            add(new JScrollPane(infoPane), BorderLayout.WEST);

            homePane = new JTabbedPane();
            HTMLPanel homePanel = new HTMLPanel("http://www.shoprite.com.ng");
            homePanel.setToolTipText("http://www.shoprite.com.ng");
            homePanel.show();
            HTMLPanel specialPanel = new HTMLPanel("http://www.shoprite.com.ng/specials.html");
            specialPanel.setToolTipText("http://www.shoprite.com.ng/specials.html");
            specialPanel.show();
            HTMLPanel storeLocatorPanel = new HTMLPanel("http://www.shoprite.com.ng/store-locator.html");
            storeLocatorPanel.setToolTipText("http://www.shoprite.com.ng/store-locator.html");
            storeLocatorPanel.show();
            building.setToolTipText("Map of item location in Shoprite, Warri");
            HTMLPanel newsletterPanel = new HTMLPanel("http://www.shoprite.com.ng/store-locator.html");
            newsletterPanel.setToolTipText("http://www.shoprite.com.ng/store-locator.html");
            newsletterPanel.show();
            HTMLPanel obsPanel = new HTMLPanel("http://www.shoprite.com.ng/store-locator.html");
            obsPanel.setToolTipText("http://www.shoprite.com.ng/store-locator.html");
            obsPanel.show();
            try {
                homePane.addTab("Home", new ImageIcon(ImageIO.read(new File("C:/Shoprite/images/about_us_header.jpg")).getScaledInstance(20, 20, 10)), homePanel);
                homePane.addTab("Specials", new ImageIcon(ImageIO.read(new File("C:/Shoprite/images/geographical spread.gif")).getScaledInstance(20, 20, 10)), specialPanel);
                homePane.addTab("Find a Store", new ImageIcon(ImageIO.read(new File("C:/Shoprite/images/locator.png")).getScaledInstance(20, 20, 10)), storeLocatorPanel);
                homePane.addTab("Find Item", new ImageIcon(ImageIO.read(new File("C:/Shoprite/images/locator.png")).getScaledInstance(20, 20, 10)), building);
                homePane.addTab("Newsletter", new ImageIcon(ImageIO.read(new File("C:/Shoprite/images/newslettersignup_imagecard_1020x315.jpg")).getScaledInstance(20, 20, 10)), newsletterPanel);
                homePane.addTab("Business Online", new ImageIcon(ImageIO.read(new File("C:/Shoprite/images/shoprite_holdings_logo.jpg")).getScaledInstance(20, 20, 10)), obsPanel);
                } catch (IOException ex) {
                homePane.add("Home", homePanel);
                homePane.add("Specials", specialPanel);
                homePane.add("Find a Store", storeLocatorPanel);
                homePane.add("Find Item", building);
                homePane.add("Newsletter", newsletterPanel);
                homePane.add("Business Online", obsPanel);
            }
            add(homePane, BorderLayout.CENTER);
            
            JPanel toolsPanel = new JPanel();

            itemFinder = new JButton("Find an Item");
            itemFinder.addActionListener(this);
            itemFinder.setToolTipText("Start search");
            toolsPanel.add(itemFinder);
            
            JLabel blankSpace = new JLabel("             Categories: ");
            blankSpace.setSize(getWidth()/5, 40);
            toolsPanel.add(blankSpace);

            categorySelector.setToolTipText("Search for items by their category");
            categorySelector.addItem("Find Item by Category"); // Add drop-down title
            for(String item : category) categorySelector.addItem(item);
            categorySelector.addActionListener(this);
            toolsPanel.add(categorySelector);

            JPanel upperPanel = new JPanel();
            upperPanel.setLayout(new GridLayout(1,0));

            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridLayout(0,1));

            JLabel inputLabel = new JLabel("What are you buying today?");
            inputLabel.setSize(getWidth()/5, getHeight()/5);
            inputLabel.setLocation((this.getWidth())-5, this.getHeight()-69);

            inputPane = new JTextField("Enter item to search for...");
            inputPane.setSize(getWidth()/5, getHeight()/5);
            inputPane.setLocation((this.getWidth())-5, this.getHeight()-69);
            inputPane.addActionListener(this);
            inputPane.setToolTipText("Input box for items to be searched for");
            inputPanel.add(inputLabel, BorderLayout.NORTH);
            inputPanel.add(inputPane, BorderLayout.SOUTH);
            // Add upper panel content
            upperPanel.add(inputPanel, BorderLayout.NORTH);
            upperPanel.add(toolsPanel, BorderLayout.SOUTH);
            add(upperPanel, BorderLayout.NORTH);
            state = "Welcome Window"; // switch from default state to operational state
            addMenu();
            setLocation(200,50);
            setPreferredSize(new Dimension(950,450));
            setMinimumSize(new Dimension(900,450));
            setMaximumSize(new Dimension(WIDTH, HEIGHT));
            setTitle(appName);
            try {
                findAppInstallLoc("C:/", "Shoprite");
                setIconImage(ImageIO.read(new File("C:/Shoprite/images/shoprite_logo_2.png")));
            } catch (IOException ex) {
                System.out.printf("Problem with machine icon loading...\n%1s\n", ex);
            }
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setExtendedState(2);
            setVisible(true);
        }
        private void setData(Map desc){
            String entrance1 = "  From Entrance 1:"+
                            "\nWalk in. Take left turn.";
            String entrance2 = "\n\nFrom Entrance 2:"+ 
                    "\nWalk in. Take right turn.";
            String end = "\nYou're there!  ";
            for (String shop : phoneShops) {
                description = new ArrayList<String>();
                if (shop.equalsIgnoreCase("airtel")) {
                    description.add(entrance1 + 
                            "\nFirst store, by your left."+ end);
                    description.add(entrance2 + 
                            "\nWalk down the hallway to Entrance 1."
                            + "\nLast shop, on the right."+ end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("best mobile")) {
                    description.add( entrance1 + 
                            "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n6 stores after the lavatory section is the Shoprite Superstore. Keep walking."
                            + "\n4 stores after the Shoprite Superstore, on the right."
                            + end);
                    description.add( entrance2 + 
                            "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n3 stores after the fastfood junction, on the left."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("fine brothers")) {
                    description.add( entrance1 + 
                            "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n6 stores after the lavatory section is the Shoprite Superstore. Keep walking."
                            + "\n7 stores after the Shoprite Superstore is the fastfood junction. Keep walking."
                            + "\nFirst store after the fastfood junction, on the right."
                            + end);
                    description.add( entrance2 + 
                            "\n5 stores ahead, on the left."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("tecno")) {
                    description.add( entrance1 + 
                            "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n6 stores after the lavatory section is the Shoprite Superstore. Keep walking."
                            + "\n7 stores after the Shoprite Superstore is the fastfood junction. Keep walking."
                            + "\n2 stores after the fastfood junction, on the right."
                            + end);
                    description.add( entrance2 + 
                            "\n4 stores ahead, on the left."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("etisalat")) {
                    description.add( entrance1 
                            + "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n6 stores after the lavatory section is the Shoprite Superstore. Keep walking."
                            + "\nFirst store after Shoprite Superstore, on the left."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n6 stores ahead, on the right."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("glo world")) {
                    description.add( entrance1 
                            + "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n6 stores after the lavatory section is the Shoprite Superstore. Keep walking."
                            + "\n3 stores after Shoprite Superstore, on the left."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n4 stores ahead, on the right."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("lg")) {
                    description.add( entrance1 
                            + "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n3 stores after the lavatory section, on the left."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n7 stores after the fastfood junction is the Shoprite Superstore. Keep walking."
                            + "\n3 stores ahead, on the right."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("pointek")) {
                    description.add( entrance1 
                            + "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\nFirst store after the lavatory section, on the right."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n7 stores after the fastfood junction is the Shoprite Superstore. Keep walking."
                            + "\n5 stores ahead, on the left."
                            + end);
                    desc.putIfAbsent(shop, description);
                }
            }
            for (String shop : computerShops) {
                description = new ArrayList<String>();
                if (shop.equalsIgnoreCase("yudala")) {
                    description.add(entrance1 
                            + "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\nFirst store after the lavatory section, on the right."
                            + end);
                    description.add(entrance2 
                            + "\n3 stores ahead, on the right."+ end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("best mobile")) {
                    description.add( entrance1 + 
                            "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n6 stores after the lavatory section is the Shoprite Superstore. Keep walking."
                            + "\n4 stores after the Shoprite Superstore, on the right."
                            + end);
                    description.add( entrance2 + 
                            "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n3 stores after the fastfood junction, on the left."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("lg")) {
                    description.add( entrance1 
                            + "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n3 stores after the lavatory section, on the left."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n7 stores after the fastfood junction is the Shoprite Superstore. Keep walking."
                            + "\n3 stores ahead, on the right."
                            + end);
                    desc.putIfAbsent(shop, description);
                }
            }
            for (String shop : electronicShops) {
                description = new ArrayList<String>();
                if (shop.equalsIgnoreCase("marvel")) {
                    description.add( entrance1
                            + "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\nAt the curved bend, on the right."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n7 stores after the fastfood junction is the Shoprite Superstore. Keep walking."
                            + "\n6 stores after the Shoprite Superstore is the lavatory. Keep walking."
                            + "\n4 stores ahead, on the left."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("fine brothers")) {
                    description.add( entrance1 + 
                            "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n6 stores after the lavatory section is the Shoprite Superstore. Keep walking."
                            + "\n7 stores after the Shoprite Superstore is the fastfood junction. Keep walking."
                            + "\nFirst store after the fastfood junction, on the right."
                            + end);
                    description.add( entrance2 + 
                            "\n5 stores ahead, on the left."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("lg")) {
                    description.add( entrance1 
                            + "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n3 stores after the lavatory section, on the left."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n7 stores after the fastfood junction is the Shoprite Superstore. Keep walking."
                            + "\n3 stores ahead, on the right."
                            + end);
                    desc.putIfAbsent(shop, description);
                }
            }
            for (String shop : fabricShops) {
                description = new ArrayList<String>();
                if (shop.equalsIgnoreCase("mrp")) {
                    description.add(entrance1 
                            + "\nLast stores ahead, on the right."+ end);
                    description.add("Shop Description:\nEntrance 2:"+
                            "\nWalk in. Take left turn." 
                            + "\nWalk down the hallway to Entrance 1."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("identity")) {
                    description.add( entrance1 + 
                            "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n2 stores after the lavatory, on the left."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n7 stores after the fastfood junction is the Shoprite Superstore. Keep walking."
                            + "\n4 stores ahead, on the right."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("truworths")) {
                    description.add( entrance1 
                            + "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n5 stores after the curved bend, on the left."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n7 stores after the fastfood junction is the Shoprite Superstore. Keep walking."
                            + "\n6 stores after the Shoprite Superstore is the lavatory. Keep walking."
                            + "\n5 stores ahead, on the right."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("button up")) {
                    description.add( entrance1 
                            + "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n3 stores after the curved bend, on the right."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n7 stores after the fastfood junction is the Shoprite Superstore. Keep walking."
                            + "\n6 stores after the Shoprite Superstore is the lavatory. Keep walking."
                            + "\nFirst stores after the lavatory, on the left."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("secret view lingerie")) {
                    description.add( entrance1
                            + "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\nAt the curved bend, on the right."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n7 stores after the fastfood junction is the Shoprite Superstore. Keep walking."
                            + "\n6 stores after the Shoprite Superstore is the lavatory. Keep walking."
                            + "\n4 stores ahead, on the left."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("audacious")) {
                    description.add( entrance1 
                            + "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n3 stores after the curved bend, on the left."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n7 stores after the fastfood junction is the Shoprite Superstore. Keep walking."
                            + "\n6 stores after the Shoprite Superstore is the lavatory. Keep walking."
                            + "\nFirst stores after the lavatory, on the right."
                            + end);
                    desc.putIfAbsent(shop, description);
                }
            }
            for (String shop : cosmeticShops) {
                description = new ArrayList<String>();
                if (shop.equalsIgnoreCase("casabella")) {
                    description.add(entrance1 + 
                            "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n6 stores after the lavatory section is the Shoprite Superstore. Keep walking."
                            + "\n7 stores after the Shoprite Superstore is the fastfood junction. Keep walking."
                            + "\n6 stores after Shoprite Superstore, on the right."
                            + end);
                    description.add(entrance2 
                            + "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\nFirst store after the fastfood junction, on the left."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("essenza")) {
                    description.add( entrance1 + 
                            "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n6 stores after the lavatory section is the Shoprite Superstore. Keep walking."
                            + "\n2 stores after Shoprite Superstore, on the right."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n5 stores after the fastfood junction, on the left."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("pink sky")) {
                    description.add( entrance1 + 
                            "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n6 stores after the lavatory section is the Shoprite Superstore. Keep walking."
                            + "\nFirst store after Shoprite Superstore, on the right."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n6 stores after fastfood junction, on the left."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("montaigne")) {
                    description.add( entrance1 + 
                            "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n4 stores after the lavatory section, on the right."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n7 stores after the fastfood junction is the Shoprite Superstore. Keep walking."
                            + "\n2 stores after the Shoprite Superstore, on the left."
                            + end);
                    desc.putIfAbsent(shop, description);
                }
            }
            for (String shop : leatherJewelShops) {
                description = new ArrayList<String>();
                if (shop.equalsIgnoreCase("maybrand")) {
                    description.add( entrance1 + 
                            "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n6 stores after the lavatory section is the Shoprite Superstore. Keep walking."
                            + "\n7 stores after the Shoprite Superstore is the fastfood junction. Keep walking."
                            + "\n5 stores after the fastfood junction, on the right."
                            + end);
                    description.add( entrance2 + 
                            "\nFirst store, on the left."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("kontessa")) {
                    description.add( entrance1 + 
                            "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n6 stores after the lavatory section is the Shoprite Superstore. Keep walking."
                            + "\n4 stores after the Shoprite Superstore, on the right."
                            + end);
                    description.add( entrance2 + 
                            "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n3 stores after the fastfood junction, on the left."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("swatch")) {
                    description.add( entrance1 + 
                            "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n6 stores after the lavatory section is the Shoprite Superstore. Keep walking."
                            + "\n3 stores after the Shoprite Superstore, on the right."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n4 stores after fastfood junction, on the left."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("a! accessories")) {
                    description.add( entrance1 + 
                            "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n5 stores after the lavatory section, on the right."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n7 stores after the fastfood junction is the Shoprite Superstore. Keep walking."
                            + "\nFirst store after the Shoprite Superstore, on the left."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("diva")) {
                    description.add( entrance1 + 
                            "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n2 stores after the lavatory section, on the right."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n7 stores after the fastfood junction is the Shoprite Superstore. Keep walking."
                            + "\n4 stores after the Shoprite Superstore, on the left."
                            + end);
                    desc.putIfAbsent(shop, description);
                }
            }
            for (String shop : fastfoodShops) {
                description = new ArrayList<String>();
                if (shop.equalsIgnoreCase("kilmanjaro")) {
                    description.add( entrance1 + 
                            "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n6 stores after the lavatory section is the Shoprite Superstore. Keep walking."
                            + "\n7 stores after the Shoprite Superstore is the fastfood junction."
                            + "\n3rd store in the fastfood cut-out, on the left."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction."
                            + "\nFirst store in the fastfood cut-out, on the right."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("aroma")) {
                    description.add( entrance1 + 
                            "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n6 stores after the lavatory section is the Shoprite Superstore. Keep walking."
                            + "\n7 stores after the Shoprite Superstore is the fastfood junction."
                            + "\n2nd (middle) store in the fastfood cut-out, on the left."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction."
                            + "\n2nd (middle) store in the fastfood cut-out, on the right."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("chicken republic")) {
                    description.add( entrance1 + 
                            "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n6 stores after the lavatory section is the Shoprite Superstore. Keep walking."
                            + "\n7 stores after the Shoprite Superstore is the fastfood junction."
                            + "\nFirst store in the fastfood cut-out, on the left."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction."
                            + "\n3rd store in the fastfood cut-out, on the right."
                            + end);
                    desc.putIfAbsent(shop, description);
                }
            }
            for (String shop : medShops) {
                description = new ArrayList<String>();
                if (shop.equalsIgnoreCase("medplus")) {
                    description.add( entrance1 + 
                            "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n6 stores after the lavatory section is the Shoprite Superstore. Keep walking."
                            + "\n7 stores after the Shoprite Superstore is the fastfood junction. Keep walking."
                            + "\n4 stores after the fastfood junction, on the right."
                            + end);
                    description.add( entrance2 + 
                            "\n2nd store, on the left."
                            + end);
                    desc.putIfAbsent(shop, description);
                } else if (shop.equalsIgnoreCase("nettpharmacy")) {
                    description.add( entrance1 + 
                            "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n6 stores after the lavatory section is the Shoprite Superstore. Keep walking."
                            + "\n3 stores after the lavatory section, on the right."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n7 stores after the fastfood junction is the Shoprite Superstore. Keep walking."
                            + "\n3 stores after the Shoprite Superstore, on the left."
                            + end);
                    desc.putIfAbsent(shop, description);
                }
            }
            for (String shop : cinemaShops) {
                description = new ArrayList<String>();
                if (shop.equalsIgnoreCase("genesis deluxe cinema")) {
                    description.add( entrance1 + 
                            "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n6 stores after the lavatory section is the Shoprite Superstore. Keep walking."
                            + "\n7 stores after the Shoprite Superstore is the fastfood junction. Keep walking."
                            + "\n3 stores after the fastfood junction, on the right, is the cinema cut-out."
                            + end);
                    description.add( entrance2 + 
                            "\n3rd store, on the left, is the cinema cut-out."
                            + end);
                    desc.putIfAbsent(shop, description);
                }
            }
            for (String shop : sportsShops) {
                description = new ArrayList<String>();
                if (shop.equalsIgnoreCase("sports world")) {
                    description.add( entrance1 
                            + "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n2nd stores after the curved bend, on the left."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n7 stores after the fastfood junction is the Shoprite Superstore. Keep walking."
                            + "\n6 stores after the Shoprite Superstore is the lavatory. Keep walking."
                            + "\n2nd store after the lavatory, on the right."
                            + end);
                    desc.putIfAbsent(shop, description);
                }
            }
            for (String shop : shopriteShops) {
                description = new ArrayList<String>();
                if (shop.equalsIgnoreCase("shoprite")) {
                    description.add( entrance1 
                            + "\n7 stores ahead, there is a curved bend. Keep walking."
                            + "\n4 stores after the curved bend is the lavatory section. Keep walking."
                            + "\n6 stores after the lavatory section is the Shoprite Superstore."
                            + end);
                    description.add( entrance2 
                            + "\n6 stores ahead, there is the fastfood junction. Keep walking."
                            + "\n7 stores after the fastfood junction is the Shoprite Superstore."
                            + end);
                    desc.putIfAbsent(shop, description);
                }
            }
        }
        private void setCategories(ArrayList<String> category){
           category.add("Phones and Tablets");
           category.add("Home and Kitchen");
           category.add("Beauty, Health and Personal Care");
           category.add("Fashion");
           category.add("Subscriptions");
           category.add("Electronics");
           category.add("Computer and Accessories");
           category.add("Music and Movies");
           category.add("Baby, Kids and Toys");
           category.add("Sports and Fitness");
           category.add("Books and Stationery");
           category.add("Automotive and Industrial");
           category.add("Drinks and Groceries");
           category.add("Wholesale");
        }
        private void setThemeNames(){
           theme.add("Early Dusk");
           theme.add("Night Blues");
           theme.add("Blue Haven");
           theme.add("Royal Soul");
           theme.add("Mettalic Hound");
        }
        private void setSystemThemes(){
            Color[] theme1 = new Color[9], theme2 = new Color[9], 
                theme3 = new Color[9], theme4 = new Color[9], theme5 = new Color[9];
           theme1[0] = Color.black;
           theme1[1] = new Color(1.0f,1.0f,0.5f);
           theme1[2] = Color.blue;
           theme1[3] = Color.gray;
           theme1[4] = Color.green;
           theme1[5] = Color.white;
           theme1[6] = Color.darkGray;
           theme1[7] = Color.red;
           theme1[8] = new Color(127,0,0); // brown labellings
           themeColor.add(theme1);
           theme2[0] = Color.black;
           theme2[1] = Color.gray;
           theme2[2] = Color.blue;
           theme2[3] = Color.darkGray;
           theme2[4] = Color.green;
           theme2[5] = Color.white;
           theme2[6] = Color.yellow;
           theme2[7] = Color.red;
           theme2[8] = Color.white; // white labellings
           themeColor.add(theme2);
           theme3[0] = Color.black;
           theme3[1] = new Color(192,192,192); // ash-colored background
           theme3[2] = Color.blue;
           theme3[3] = Color.darkGray;
           theme3[4] = Color.green; // green toilets
           theme3[5] = Color.yellow;
           theme3[6] = new Color(0.0f,0.8f,0.0f); // dark-green arrows
           theme3[7] = Color.red;
           theme3[8] = Color.white; // white labellings
           themeColor.add(theme3);
           theme4[0] = Color.black;
           theme4[1] = Color.orange; // light-yellow background
           theme4[2] = new Color(1.0f,0.0f,1.0f); // purple shops
           theme4[3] = Color.darkGray;
           theme4[4] = Color.green; // green toilets
           theme4[5] = Color.yellow;
           theme4[6] = new Color(0.4f,0.4f,1.0f); // light-blue arrows
           theme4[7] = Color.red;
           theme4[8] = new Color(127,255,255); // bluish-green labellings
           themeColor.add(theme4);
           theme5[0] = Color.cyan;
           theme5[1] = Color.black; // black background
           theme5[2] = Color.lightGray; // light-gray shops
           theme5[3] = Color.darkGray;
           theme5[4] = Color.blue; // blue toilets
           theme5[5] = Color.white;
           theme5[6] = Color.blue; // blue arrows
           theme5[7] = Color.red;
           theme5[8] = Color.white; // white labellings
           themeColor.add(theme5);
        }
        private static void setFoneData(String[] ipad, String[] google, String[] microsoft, 
                String[] samsung, String[] sony, String[] others){
            ipad[0]= "ipad mini 2";
            ipad[1]= "ipad mini 4";
            ipad[2]= "ipad pro";
            ipad[3]= "ipad air 1";
            ipad[4]= "ipad air 2";
            google[0]= "google pixel c";
            google[1]= "google nexus 7";
            google[2]= "google nexus 9";
            microsoft[0]= "microsoft surface pro 3";
            microsoft[1]= "microsoft surface pro 4";
            samsung[0]= "samsung galaxy tab s";
            samsung[1]= "samsung galaxy tab s2";
            sony[0]= "sony xperia z3";
            sony[1]= "sony xperia z4";
            others[0]= "dell venue 8 7000";
            others[1]= "amazon fire hdx";
        }
        private static void setElectData(String[] electronics){
            electronics[0] = "acer";
            electronics[1] = "alcatel";
            electronics[2] = "amazon";
            electronics[3] = "apple";
            electronics[4] = "bb";
            electronics[5] = "dell";
            electronics[6] = "google";
            electronics[7] = "lg";
            electronics[8] = "microsoft";
            electronics[9] = "motorola";
            electronics[10] = "nokia";
            electronics[11] = "sagem";
            electronics[12] = "samsung";
            electronics[13] = "sharp";
            electronics[14] = "sony";
            electronics[15] = "tecno";
        }
        private static void setComputersData(String[] computers){
            computers[0] = "acer";
            computers[1] = "apple";
            computers[2] = "asus";
            computers[3] = "benq";
            computers[4] = "compaq";
            computers[5] = "gateway";
            computers[6] = "hp";
            computers[7] = "hitachi";
            computers[8] = "htc";
            computers[9] = "ibm";
            computers[10] = "intel";
            computers[11] = "lenovo";
            computers[12] = "lg";
            computers[13] = "liteon";
            computers[14] = "microsoft";
            computers[15] = "motorola";
            computers[16] = "nec";
            computers[17] = "oracle";
        }
        private static void setDBItems(ArrayList<String> items){
            /* The various phones */
            String[] ipad = new String[5], google = new String[3], microsoft = new String[2], 
                samsung = new String[2], sony = new String[2], others = new String[2], 
                electronics = new String[16], computers = new String[18];
            setFoneData(ipad,google,microsoft,samsung,sony,others);
            setElectData(electronics);
            setComputersData(computers);
            for (String item : ipad) items.add(item);
            for (String item : google) items.add(item);
            for (String item : microsoft) items.add(item);
            for (String item : samsung) items.add(item);
            for (String item : sony) items.add(item);
            for (String item : others) items.add(item);
            for (String item : electronics) items.add(item);
            for (String item : computers) items.add(item);
            for (String item : phoneItems) items.add(item);
            for (String item : electronicItems) items.add(item);
            for (String item : computerItems) items.add(item);
            for (String item : fabricItems) items.add(item);
            for (String item : cosmeticItems) items.add(item);
            for (String item : leatherJewelItems) items.add(item);
            for (String item : fastfoodItems) items.add(item);
            for (String item : medItems) items.add(item);
            for (String item : cinemaItems) items.add(item);
            for (String item : sportsItems) items.add(item);
            for (String item : shopriteItems) items.add(item);
            for (int i=0; i<items.size(); i++){
                String temp = items.get(i);
                for (int j=i+1; j<items.size(); j++) if (temp.equalsIgnoreCase(items.get(j))) items.remove(j);
            }
            itm.putIfAbsent("Phones and Tablets", phoneItems);
            itm.putIfAbsent("Electronics", electronicItems);
            itm.putIfAbsent("Computer and Accessories", computerItems);
            itm.putIfAbsent("Fashion", fabricItems);
            itm.putIfAbsent("Beauty, Health and Personal Care", cosmeticItems);
            itm.putIfAbsent("Fashion", leatherJewelItems);
            itm.putIfAbsent("Drinks and Groceries", fastfoodItems);
            itm.putIfAbsent("Beauty, Health and Personal Care", medItems);
            itm.putIfAbsent("Music and Movies", cinemaItems);
            itm.putIfAbsent("Sports and Fitness", sportsItems);
            itm.putIfAbsent("Baby, Kids and Toys", shopriteItems);
            itm.putIfAbsent("Phones and Tablet", ipad);
            itm.putIfAbsent("Phones and Tablet", google);
            itm.putIfAbsent("Phones and Tablet", microsoft);
            itm.putIfAbsent("Phones and Tablet", samsung);
            itm.putIfAbsent("Phones and Tablet", sony);
            itm.putIfAbsent("Phones and Tablet", others);
            itm.putIfAbsent("Phones and Tablet", electronics);
            itm.putIfAbsent("Phones and Tablet", computers);
        }
        private static boolean equalStrings(String str1, String str2){
            String temp1="", temp2="";
            for (int i=0; i<str1.length(); i++) if (str1.charAt(i)!=' ') temp1+=str1.charAt(i);
            for (int i=0; i<str2.length(); i++) if (str2.charAt(i)!=' ') temp2+=str2.charAt(i);
            if (temp1.equalsIgnoreCase(temp2)) return true;
            return false;
        }
        @Override
        public void actionPerformed(ActionEvent evt){
            String action = evt.getActionCommand();
            if (evt.getSource()==inputPane || evt.getSource()==itemFinder){
                if (!(inputPane.getText().contains("item to search for...")||inputPane.getText()==""
                        ||inputPane.getText().contains("successful")||inputPane.getText().contains("unsuccessful"))){
                    userInput = inputPane.getText().trim();
                    boolean itemFound = false;
                    for (String item : items) if (userInput.equalsIgnoreCase(item)||equalStrings(userInput,item)) {
                        itemFound = true; break;
                    }
                    if (itemFound) {
                        inputPane.setText("Search for "+inputPane.getText().toUpperCase()+" successful!");
                        displayLocation(inputPane.getText(), infoBoard);
                    } else if (!inputPane.getText().equals("")) {
                        inputPane.setText("Search for "+inputPane.getText().toUpperCase()+" unsuccessful...");
                        infoBoard.setText("\n\nThe item you searched for, \""+userInput+"\", "
                                + "\nwas not found in our database!\n\nSuggestions:\n");
                        for (String item : items) if (item.contains(userInput)||userInput.contains(item)) {
                            infoBoard.append(item+"\n");
                        }
                    }
                } else if (!inputPane.getText().equals("")) {
                    inputPane.setText("");
                }
                state = "Find Item";
                return;
            } 
            switch(action){
                case "Early Dusk":{
                    THEME = 0;
                    break;
                }
                case "Night Blues":{
                    THEME = 1;
                    break;
                }
                case "Blue Haven":{
                    THEME = 2;
                    break;
                }
                case "Royal Soul":{
                    THEME = 3;
                    break;
                }
                case "Mettalic Hound":{
                    THEME = 4;
                    break;
                }
                case "Zoom+               Ctrl+]":{
                    if(SCALE<2) SCALE++;
                    break;
                }
                case "Zoom-               Ctrl+[":{
                    if(SCALE>1) SCALE--;
                    break;
                }
                case "New                Ctrl+F4":{
                    createAndShowGUI();
                    dispose();
                    break;
                }
                case "Exit                     Alt+F4":{
                    state = null; // change app state to non-operational state
                    System.exit(0);
                    break;
                }
            }
            repaint();
        }
        private void displayLocation(String inputText, JTextArea infoBoard) {
            if (inputText.contains("bb")||inputText.contains("Bb")||inputText.contains("BB")){
                infoBoard.append("Your Blackberry phone is available at:\n\n");
                for (String str : phoneShops) infoBoard.append(str.toUpperCase()+"\n\n***\n"+desc.get(str)+"\n***\n\n");
                searchIndex = 1;
            }
            for (String foneType : phoneItems){
                if (inputText.contains(foneType)||inputText.contains(foneType.toLowerCase())
                        ||inputText.contains(foneType.toUpperCase())){
                    infoBoard.append("\nFor your "+foneType.toUpperCase()+" phone, visit:\n\n");
                    for (String str : phoneShops) infoBoard.append(str.toUpperCase()+"\n\n***\n"+desc.get(str)+"\n***\n\n");
                    searchIndex = 1;
                }
            }
            for (String electType : computerItems){
                if (inputText.contains(electType)||inputText.contains(electType.toLowerCase())
                        ||inputText.contains(electType.toUpperCase())){
                    infoBoard.append("\nFor your "+electType.toUpperCase()+" computer, visit:\n\n");
                    for (String str : computerShops) infoBoard.append(str.toUpperCase()+"\n\n***\n"+desc.get(str)+"\n***\n\n");
                    searchIndex = 2;
                }
            }
            for (String electType : electronicItems){
                if (inputText.contains(electType)||inputText.contains(electType.toLowerCase())
                        ||inputText.contains(electType.toUpperCase())){
                    infoBoard.append("\nFor your "+electType.toUpperCase()+" appliance, visit:\n\n");
                    for (String str : electronicShops) infoBoard.append(str.toUpperCase()+"\n\n***\n"+desc.get(str)+"\n***\n\n");
                    searchIndex = 3;
                }
            }
            for (String electType : fabricItems){
                if (inputText.contains(electType)||inputText.contains(electType.toLowerCase())
                        ||inputText.contains(electType.toUpperCase())){
                    infoBoard.append("\nFor your "+electType.toUpperCase()+", visit:\n\n");
                    for (String str : fabricShops) infoBoard.append(str.toUpperCase()+"\n\n***\n"+desc.get(str)+"\n***\n\n");
                    searchIndex = 4;
                }
            }
            for (String electType : cosmeticItems){
                if (inputText.contains(electType)||inputText.contains(electType.toLowerCase())
                        ||inputText.contains(electType.toUpperCase())){
                    infoBoard.append("\nFor your "+electType.toUpperCase()+" (& other \ncosmetics), visit:\n\n");
                    for (String str : cosmeticShops) infoBoard.append(str.toUpperCase()+"\n\n***\n"+desc.get(str)+"\n***\n\n");
                    searchIndex = 5;
                }
            }
            for (String electType : leatherJewelItems){
                if (inputText.contains(electType)||inputText.contains(electType.toLowerCase())
                        ||inputText.contains(electType.toUpperCase())){
                    infoBoard.append("\nFor your "+electType.toUpperCase()+" (& other \naccessories), visit:\n\n");
                    for (String str : leatherJewelShops) infoBoard.append(str.toUpperCase()+"\n\n***\n"+desc.get(str)+"\n***\n\n");
                    searchIndex = 6;
                }
            }
            for (String electType : fastfoodItems){
                if (inputText.contains(electType)||inputText.contains(electType.toLowerCase())
                        ||inputText.contains(electType.toUpperCase())){
                    infoBoard.append("\nFor your "+electType.toUpperCase()+" (& other fastfood items, stopover & takeaway), visit:\n\n");
                    for (String str : fastfoodShops) infoBoard.append(str.toUpperCase()+"\n\n***\n"+desc.get(str)+"\n***\n\n");
                    searchIndex = 7;
                }
            }
            for (String electType : medItems){
                if (inputText.contains(electType)||inputText.contains(electType.toLowerCase())
                        ||inputText.contains(electType.toUpperCase())){
                    infoBoard.append("\nFor your "+electType.toUpperCase()+" (& other pharmaceutical products), visit:\n\n");
                    for (String str : medShops) infoBoard.append(str.toUpperCase()+"\n\n***\n"+desc.get(str)+"\n***\n\n");
                    searchIndex = 8;
                }
            }
            for (String electType : cinemaItems){
                if (inputText.contains(electType)||inputText.contains(electType.toLowerCase())
                        ||inputText.contains(electType.toUpperCase())){
                    infoBoard.append("\nFor your "+electType.toUpperCase()+" (& other movies), visit:\n\n");
                    for (String str : cinemaShops) infoBoard.append(str.toUpperCase()+"\n\n***\n"+desc.get(str)+"\n***\n\n");
                    searchIndex = 9;
                }
            }
            for (String electType : sportsItems){
                if (inputText.contains(electType)||inputText.contains(electType.toLowerCase())
                        ||inputText.contains(electType.toUpperCase())){
                    infoBoard.append("\nFor your "+electType.toUpperCase()+" (& other sports items), visit:\n\n");
                    for (String str : sportsShops) infoBoard.append(str.toUpperCase()+"\n\n***\n"+desc.get(str)+"\n***\n\n");
                    searchIndex = 10;
                }
            }
            for (String electType : shopriteItems){
                if (inputText.contains(electType)||inputText.contains(electType.toLowerCase())
                        ||inputText.contains(electType.toUpperCase())){
                    infoBoard.append("\nFor your "+electType.toUpperCase()+" (& other superstore items), visit:\n\n");
                    for (String str : shopriteShops) infoBoard.append(str.toUpperCase()+"\n\n***\n"+desc.get(str)+"\n***\n\n");
                    searchIndex = 11;
                }
            }
        }
        private void addMenu() {
            JMenuBar menubar = new JMenuBar();
            JMenu fileMenu = new JMenu("File");
            JMenu viewMenu = new JMenu("View");
            JMenu helpMenu = new JMenu("Help");

            JMenuItem close = new JMenuItem("New                Ctrl+F4");
            close.addActionListener(this);
            close.setToolTipText("Open fresh window");
            JMenuItem exit = new JMenuItem("Exit                     Alt+F4");
            exit.addActionListener(this);
            exit.setToolTipText("Close the application");
            fileMenu.add(close);
            fileMenu.add(exit);

            JMenu themes = new JMenu("Themes                ");
            themes.setToolTipText("Application themes");
            JMenuItem earlyDusk = new JMenuItem("Early Dusk");
            earlyDusk.addActionListener(this);
            JMenuItem nightBlues = new JMenuItem("Night Blues");
            nightBlues.addActionListener(this);
            JMenuItem blueHaven = new JMenuItem("Blue Haven");
            earlyDusk.addActionListener(this);
            JMenuItem royalSoul = new JMenuItem("Royal Soul");
            royalSoul.addActionListener(this);
            JMenuItem metallicHound = new JMenuItem("Mettalic Hound");
            metallicHound.addActionListener(this);
            themes.add(earlyDusk);
            themes.add(nightBlues);
            themes.add(blueHaven);
            themes.add(royalSoul);
            themes.add(metallicHound);
            viewMenu.add(themes);

            JMenu tools = new JMenu("Accessibility Tools");
            tools.setToolTipText("Accessibility tools");
            JMenuItem zoomOut = new JMenuItem("Zoom+               Ctrl+]");
            zoomOut.addActionListener(this);
            zoomOut.setToolTipText("Zoom in on the map");
            JMenuItem zoomIn = new JMenuItem("Zoom-               Ctrl+[");
            zoomIn.addActionListener(this);
            zoomIn.setToolTipText("Zoom out on the map");
            tools.add(zoomOut);
            tools.add(zoomIn);
            viewMenu.add(tools);

            JMenuItem wizard = new JMenuItem("Wizard                        F1");
            wizard.addActionListener(this);
            wizard.setToolTipText("Get help with using application functions");
            JMenuItem update = new JMenuItem("Check For Update");
            update.addActionListener(this);
            update.setToolTipText("Update application");
            JMenuItem policies = new JMenuItem("Policies");
            policies.addActionListener(this);
            policies.setToolTipText("Shoprite policies");
            JMenuItem terms = new JMenuItem("Terms and Conditions");
            terms.addActionListener(this);
            terms.setToolTipText("Shoprite terms and conditions");
            JMenuItem contactUs = new JMenuItem("Contact Us");
            contactUs.addActionListener(this);
            contactUs.setToolTipText("Leave us comments, recommendations, suggestions, complaints, etc.");
            JMenuItem aboutUs = new JMenuItem("About Us");
            aboutUs.addActionListener(this);
            aboutUs.setToolTipText("About Shoprite holdings ltd.");
            helpMenu.add(wizard);
            helpMenu.add(update);
            helpMenu.add(policies);
            helpMenu.add(terms);
            helpMenu.add(contactUs);
            helpMenu.add(aboutUs);

            menubar.add(fileMenu);
            menubar.add(viewMenu);
            menubar.add(helpMenu);
            menubar.setBackground(Color.white);
            setJMenuBar(menubar);
        }
        /**
         * @param comp1 the component on which texts are to be centralized. It will
         * contain the string 'str' and method is expected to return 'str' in the
         * middle of comp1.
         * @param str the characters to be centralized. May be an empty string or
         * a string of characters.
         * @param getX the original X position of the characters relative to the 
         * component, comp1
         * @param getY the original Y position of the characters relative to the 
         * component, comp1
         * @param dx the horizontal difference between the beginning and end of
         * the space on which 'str' is to be inscribed in the component, comp1. 
         * Value is expected to be a positive integer.
         * @param dy the vertical difference difference between the topmost and 
         * lowest points that define the space on which 'str' is to be inscribed 
         * in the component, comp1. Value is expected to be a positive integer.
         * @return values: an integer array of the most fit X and Y values to 
         * define a centralized view for the string 'str' in the component, comp1.
         */
        private static int[] centralize(JComponent comp1, String str, int getX, int getY, int dx, int dy){
            int strWidth, strHeight;
            int centreX, centreY;
            int baseX, baseY;
            int topOfString;

            centreX = dx/2;
            centreY = dy/2;
            // Get FontMetrics object
            Font font = comp1.getFont();
            FontMetrics fm = comp1.getFontMetrics(font);
            // Get value of baseX
            strWidth = fm.stringWidth(str);
            baseX = centreX - (strWidth/2);
            // Get value of baseY
            strHeight = fm.getAscent();
            topOfString = centreY - (strHeight/2);
            baseY = topOfString + fm.getAscent();
            // Apply the calculated centre
            comp1.setAlignmentX(baseX); // NOT WORKING
            comp1.setAlignmentY(baseY); // NOT WORKING
            int[] answer = new int[2];
            answer[0] = getX+baseX;
            answer[1] = getY+baseY;
            return answer;
        }
        // The Simple Web Browser.
        private class HTMLPanel extends JPanel implements HyperlinkListener {

            // Editor pane for displaying pages.
            private JEditorPane displayEditorPane;

            // Constructor for Mini Web Browser.
            public HTMLPanel(String address) {
                // Set up page display.
                displayEditorPane = new JEditorPane();
                displayEditorPane.setContentType("text/html");
                displayEditorPane.setEditable(false);
                displayEditorPane.addHyperlinkListener(this);
                add(displayEditorPane);
                actionGo(address);
                setVisible(true);
            }

            // Load and show the page specified in the location text field.
            private void actionGo(String address) {
                URL verifiedUrl = verifyUrl(address);
                if (verifiedUrl != null) {
                    showPage(verifiedUrl);
                } else {
                    showError("Invalid URL");
                }
            }

            // Show dialog box with error message.
            private void showError(String errorMessage) {
                JOptionPane.showMessageDialog(this, errorMessage,"Error", JOptionPane.ERROR_MESSAGE);
            }

            // Verify URL format.
            private URL verifyUrl(String url) {
                // Only allow HTTP URLs.
                if (!url.toLowerCase().startsWith("http://"))
                    return null;

                // Verify format of URL.
                URL verifiedUrl = null;
                try {
                    verifiedUrl = new URL(url);
                } catch (Exception e) {
                    return null;
                }

                return verifiedUrl;
            }

            // Show the specified page.
            private void showPage(URL pageUrl) {
                // Show hour glass cursor while crawling is under way.
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                try {
                    // Load and display specified page.
                    displayEditorPane.setPage(pageUrl);
                } catch (Exception e) {
                    // Show error messsage.
                    showError("Unable to load page");
                } finally {
                    // Return to default cursor.
                    setCursor(Cursor.getDefaultCursor());
                }
            }

            // Handle hyperlink's being clicked.
            @Override
            public void hyperlinkUpdate(HyperlinkEvent event) {
                HyperlinkEvent.EventType eventType = event.getEventType();
                if (eventType == HyperlinkEvent.EventType.ACTIVATED) {
                    if (event instanceof HTMLFrameHyperlinkEvent) {
                        HTMLFrameHyperlinkEvent linkEvent = (HTMLFrameHyperlinkEvent) event;
                        HTMLDocument document = (HTMLDocument) displayEditorPane.getDocument();
                        document.processHTMLFrameHyperlinkEvent(linkEvent);
                    } else {
                        showPage(event.getURL());
                    }
                }
            }

        }
        private static class ShoppingMall extends JPanel{
            int[] values; boolean added; Image image;
            Color fgdColor, bgdColor, shopColor, emptyShopColor, toiletColor, lineColor, arrowColor, sourceColor, labelColor;
            
            @Override
            public void paintComponent(Graphics g){
                refreshCanvas(g);
                applyTheme(THEME);
                switch (state) {
                    case "Find Item": { drawAppWindow(g,state,searchIndex); break; }
                    case "Find Store": { break; }
                    case "Online Business Systems": { break; }
                }
                repaint();
            }
            private void applyTheme(int i){
                Color[] get = themeColor.get(i);
                fgdColor = get[0];
                bgdColor = get[1];
                shopColor = get[2];
                emptyShopColor = get[3];
                toiletColor = get[4];
                lineColor = get[5];
                arrowColor = get[6];
                sourceColor = get[7];
                labelColor = get[8];
            }
            private void drawAppWindow(Graphics g, String state, int searchIndex){
                if (state==null||state.equals("Welcome Window")) {
                    g.setColor(emptyShopColor);
                    buildWallAbove(g,0,0,getWidth()/30,getHeight()/12,getWidth()/30);
                    buildEmptySpacesAbove(g,0,getHeight()/12,getWidth()/30,getHeight()/6,getWidth()/30);
                    buildEmptySpacesBelow(g,0,getHeight()/1.49,getWidth()/30,getHeight()/6,getWidth()/30);
                    g.setColor(toiletColor);
                    buildToiletSpacesAbove(g,0,getHeight()/12,getWidth()/30,getHeight()/12,getWidth()/30);
                    g.setColor(shopColor);
                    buildAboveUpperRow(g,0,getHeight()/12,getWidth()/30,getHeight()/6,getWidth()/30);
                    buildUpperRow(g,0,getHeight()/3.965,getWidth()/30,getHeight()/6,getWidth()/30);
                    buildLowerRow(g,0,getHeight()/2.0,getWidth()/30,getHeight()/6,getWidth()/30);
                    buildLowestRow(g,0,getHeight()/1.48,getWidth()/30,getHeight()/11,getWidth()/30);
                    g.setColor(bgdColor);
                    buildUpperCurves(g,0,getHeight()/4.92,getWidth()/30,getHeight()/21,getWidth()/30);
                    buildLowerCurves(g,0,getHeight()/1.496,getWidth()/30,getHeight()/21,getWidth()/30);
                    g.setColor(sourceColor);
                    buildEntranceIndicator(g,getWidth()/24,getHeight()/1.36,getWidth()/60,getHeight()/28);
                } else if (state.equals("Find Item")) {
                    g.setColor(sourceColor);
                    buildEntranceIndicator(g,getWidth()/24,getHeight()/1.36,getWidth()/60,getHeight()/28);
                    g.setColor(emptyShopColor);
                    buildWallAbove(g,0,0,getWidth()/30,getHeight()/12,getWidth()/30);
                    buildEmptySpacesAbove(g,0,getHeight()/12,getWidth()/30,getHeight()/6,getWidth()/30);
                    buildEmptySpacesBelow(g,0,getHeight()/1.49,getWidth()/30,getHeight()/6,getWidth()/30);
                    g.setColor(toiletColor);
                    buildToiletSpacesAbove(g,0,getHeight()/12,getWidth()/30,getHeight()/12,getWidth()/30);
                    g.setColor(shopColor);
                    buildAboveUpperRow(g,0,getHeight()/12,getWidth()/30,getHeight()/6,getWidth()/30);
                    buildUpperRow(g,0,getHeight()/3.965,getWidth()/30,getHeight()/6,getWidth()/30);
                    buildLowerRow(g,0,getHeight()/2.0,getWidth()/30,getHeight()/6,getWidth()/30);
                    buildLowestRow(g,0,getHeight()/1.48,getWidth()/30,getHeight()/11,getWidth()/30);
                    g.setColor(bgdColor);
                    buildUpperCurves(g,0,getHeight()/4.92,getWidth()/30,getHeight()/21,getWidth()/30);
                    buildLowerCurves(g,0,getHeight()/1.496,getWidth()/30,getHeight()/21,getWidth()/30);
                    g.setColor(arrowColor);
                    switch (searchIndex){
                        case 1 : {
                            pointToPhoneShops(g, this); break;
                        } case 2 : {
                            pointToCompShops(g, this); break;
                        } case 3 : {
                            pointToElectShops(g, this); break;
                        } case 4 : {
                            pointToFabricShops(g, this); break;
                        } case 5 : {
                            pointToCosmeticShops(g, this); break;
                        } case 6 : {
                            pointToFashionAccShops(g, this); break;
                        } case 7 : { // THIS IS NOT A VALID CATEGORY!!!
                            pointToFastFoodShops(g, this); break;
                        } case 8 : {
                            pointToMedShops(g, this); break;
                        } case 9 : {
                            pointToCinemaShops(g, this); break;
                        } case 10 : {
                            pointToSportShops(g, this); break;
                        } case 11 : {
                            pointToShopriteShops(g, this); break;
                        }
                    }
                    /* DIRECTIONAL ARROWS
                    buildUppermostDirectionalArrows(g,getWidth()/60,getHeight()/5,getWidth()/200,getHeight()/9,getWidth()/30);
                    buildUpperHorizontalDirectionalArrows(g,getWidth()/48,getHeight()/6,getWidth()/48,getHeight()/118.75,getWidth()/30);
                    */
                    g.setColor(sourceColor);
                    buildEntranceIndicator(g,getWidth()/24,getHeight()/1.36,getWidth()/60,getHeight()/28);
                }
            }
            private void refreshCanvas(Graphics g){
                g.clearRect(0, 0, getWidth(), getHeight());
                g.setColor(bgdColor);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
            /**
             * This method builds the toilets available on the map. This
             * row of blocks is after Pointek and Button-Up stores.
             * Precondition: g has to be prior instantiated.
             * Postcondition: All the toilet architecture on the map should be displayed.
             * @param g  The object of class Graphics used to describe the architecture.
             * @param y  The distance between top of described row and top of application window.
             */
            private void buildToiletSpacesAbove(Graphics g, double x, double y, double dx, double dy, double dx2){
                for(int i=0; i<26; i++){ // grid1 is i=0, we place at grid8,9,10
                   if(i>20&&i<24) 
                       g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true); // color shop
                }
            }
            /**
             * This method builds the inner walls surrounding the shopping mall. It
             * colors the spaces black to show the architectural limits of the mall.
             * Precondition: g has to be prior instantiated.
             * Postcondition: All the row-2 architecture on the map should be displayed.
             * @param g  The object of class Graphics used to describe the architecture.
             * @param y  The distance between top of described row and top of application window.
             */
            private void buildEmptySpacesAbove(Graphics g, double x, double y, double dx, double dy, double dx2){
                for(int i=0; i<26; i++){ // grid1 is i=0, we place at grid8,9,10
                   if(i<3||(i>5&&i<12)||i>23) 
                       g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true); // color shop
                }
            }
            /**
             * This method builds the upper boundaries of the architecture. 
             * Precondition: g has to be prior instantiated.
             * Postcondition: All the toilet architecture on the map should be displayed.
             * @param g  The object of class Graphics used to describe the architecture.
             * @param y  The distance between top of described row and top of application window.
             */
            private void buildWallAbove(Graphics g, double x, double y, double dx, double dy, double dx2){
                for(int i=0; i<26; i++){ // grid1 is i=0, we place at grid8,9,10
                   g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true); // color shop
                }
            }
            /**
             * This method builds the 1st row of blocks available on the map. This
             * row of blocks contain the uppermost stores available in the architecture.
             * Stores like the Shoprite store and Genesis Deluxe Cinema with its
             * opposite refreshment stopover, are represented here.
             * Precondition: g has to be prior instantiated.
             * Postcondition: All the row-2 architecture on the map should be displayed.
             * @param g  The object of class Graphics used to describe the architecture.
             * @param y  The distance between top of described row and top of application window.
             */
            private void buildAboveUpperRow(Graphics g, double x, double y, double dx, double dy, double dx2){
                for(int i=0; i<26; i++){ // grid1 is i=0, we place at grid8,9,10
                   if(i==3||i==5||(i>11&&i<21)){
                       g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                       Color temp = g.getColor();
                       g.setColor(labelColor);
                       if (i==3) {
                           values = centralize(this,"GDC",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("GDC",values[0],(int)((y*SCALE+INSET)+(3*y*SCALE+INSET)/5));
                       }
                       else if (i==5) {
                           values = centralize(this,"Snacks",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Snacks",values[0],(int)((y*SCALE+INSET)+(3*y*SCALE+INSET)/5));
                       }
                       else if (i==12) {
                           values = centralize(this,"S",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("S",values[0],(int)((y*SCALE+INSET)+(3*y*SCALE+INSET)/5));
                       }
                       else if (i==13) {
                           values = centralize(this,"H",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("H",values[0],(int)((y*SCALE+INSET)+(3*y*SCALE+INSET)/5));
                       }
                       else if (i==14) {
                           values = centralize(this,"O",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("O",values[0],(int)((y*SCALE+INSET)+(3*y*SCALE+INSET)/5));
                       }
                       else if (i==15) {
                           values = centralize(this,"P",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("P",values[0],(int)((y*SCALE+INSET)+(3*y*SCALE+INSET)/5));
                       }
                       else if (i==16) {
                           values = centralize(this,"R",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("R",values[0],(int)((y*SCALE+INSET)+(3*y*SCALE+INSET)/5));
                       }
                       else if (i==17) {
                           values = centralize(this,"I",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("I",values[0],(int)((y*SCALE+INSET)+(3*y*SCALE+INSET)/5));
                       }
                       else if (i==18) {
                           values = centralize(this,"T",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("T",values[0],(int)((y*SCALE+INSET)+(3*y*SCALE+INSET)/5));
                       }
                       else if (i==19) {
                           values = centralize(this,"E",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("E",values[0],(int)((y*SCALE+INSET)+(3*y*SCALE+INSET)/5));
                       }
                       g.setColor(temp);
                   } // color shop
                }
            }
            private void buildUpperCurves(Graphics g, double x, double y, double dx, double dy, double dx2){
                for(int i=0; i<26; i++){ // grid1 is i=0, we place at grid8,9,10
                   if(i>6&&i<10) {
                       int[] xitems = new int[3];
                       int[] yitems = new int[3];
                       if (i==7){
                           xitems[0] = (int)((x+dx2*i)*SCALE+INSET);
                           xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                           xitems[2] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                           yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE);
                           yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE);
                           yitems[2] = (int)(y*SCALE+INSET);
                           g.fillPolygon(xitems,yitems,3);
                       }
                       else if (i==8) g.fillRoundRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET)-((int)(dy*SCALE)/2)+5, (int)(dx*SCALE-1), 2*(int)(dy*SCALE), (int)(dx*SCALE-1)/2,(int)(dx*SCALE-1)/2); // color shop
                       else {
                           xitems[0] = (int)((x+dx2*i)*SCALE+INSET);
                           xitems[1] = (int)((x+dx2*i)*SCALE+INSET);
                           xitems[2] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                           yitems[0] = (int)(y*SCALE+INSET);
                           yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE);
                           yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE);
                           g.fillPolygon(xitems,yitems,3);
                       }
                   }
                }
            }
            /**
             * This method builds the 2nd row of blocks available on the map. This
             * row of blocks contain the stores directly facing the user once he
             * enters the shop through any of the entrances.
             * Precondition: g has to be prior instantiated.
             * Postcondition: All the row-2 architecture on the map should be displayed.
             * @param g  The object of class Graphics used to describe the architecture.
             * @param y  The distance between top of described row and top of application window.
             */
            private void buildUpperRow(Graphics g, double x, double y, double dx, double dy, double dx2){
                for(int i=0; i<26; i++){ // grid1 is i=0, we place at grid8,9,10
                   if(i!=4&&i!=7&&i!=8&&i!=9&&i!=16&&i!=22) {
                       g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                       Color temp = g.getColor();
                       g.setColor(labelColor);
                       if (i==0) {
                           values = centralize(this,"MRP",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("MRP",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/7));
                       }
                       else if (i==2) {
                           values = centralize(this,"Maybrand",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Maybrand",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/7));
                       }
                       else if (i==3) {
                           values = centralize(this,"Medplus",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Medplus",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/7));
                       }
                       else if (i==5) {
                           values = centralize(this,"Tecno",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Tecno",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/7));
                       }
                       else if (i==6) {
                           values = centralize(this,"Fine",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Fine",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/7));
                           values = centralize(this,"Brothers",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Brothers",values[0],(int)((y*SCALE+INSET)+(1.6*(y*SCALE+INSET)/7)));
                       }
                       else if (i==10) {
                           values = centralize(this,"Casabel.",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Casabel.",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/7));
                       }
                       else if (i==11) {
                           values = centralize(this,"Kontess.",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Kontess.",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/7));
                       }
                       else if (i==12) {
                           values = centralize(this,"Best",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Best",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/7));
                           values = centralize(this,"Mobile",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Mobile",values[0],(int)((y*SCALE+INSET)+(1.6*(y*SCALE+INSET)/7)));
                       }
                       else if (i==13) {
                           values = centralize(this,"Swatch",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Swatch",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/7));
                       }
                       else if (i==14) {
                           values = centralize(this,"Essenza",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Essenza",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/7));
                       }
                       else if (i==15) {
                           values = centralize(this,"Pink",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Pink",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/7));
                           values = centralize(this,"Sky",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Sky",values[0],(int)((y*SCALE+INSET)+(1.6*(y*SCALE+INSET)/7)));
                       }
                       else if (i==17) {
                           values = centralize(this,"a!",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("a!",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/7));
                       }
                       else if (i==18) {
                           values = centralize(this,"Montaig.",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Montaig.",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/7));
                       }
                       else if (i==19) {
                           values = centralize(this,"Nett",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Nett",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/7));
                           values = centralize(this,"Pharma.",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Pharma.",values[0],(int)((y*SCALE+INSET)+(1.6*(y*SCALE+INSET)/7)));
                       }
                       else if (i==20) {
                           values = centralize(this,"Diva",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Diva",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/7));
                       }
                       else if (i==21) {
                           values = centralize(this,"Pointek",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Pointek",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/7));
                       }
                       else if (i==23) {
                           values = centralize(this,"Button",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Button",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/7));
                           values = centralize(this,"Up",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Up",values[0],(int)((y*SCALE+INSET)+(1.6*(y*SCALE+INSET)/7)));
                       }
                       g.setColor(temp);
                   } // color shop
                }
            }
            private void buildLowerRow(Graphics g, double x, double y, double dx, double dy, double dx2){
                for(int i=0; i<26; i++){ // grid1 is i=0, we place at grid8,9,10
                   if(i!=1&&i!=7&&i!=8&&i!=9) 
                       g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true); // color shop
                       Color temp = g.getColor();
                       g.setColor(labelColor);
                       if (i==0) {
                           values = centralize(this,"Native",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Native",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/4));
                       }
                       else if (i==2) {
                           values = centralize(this,"Fabric",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Fabric",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/4));
                       }
                       else if (i==3) {
                           values = centralize(this,"Yud",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Yud",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/4));
                       }
                       else if (i==4) {
                           g.drawString("ala",(int)((x+dx2*i)*SCALE+INSET),(int)((y*SCALE+INSET)+(y*SCALE+INSET)/4));
                       }
                       else if (i==13) {
                           values = centralize(this,"Glo",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Glo",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/4));
                       }
                       else if (i==14) {
                           values = centralize(this,"Etisalat",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Etisalat",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/4));
                       }
                       else if (i==16) {
                           values = centralize(this,"PEP",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("PEP",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/4));
                       }
                       else if (i==18) {
                           values = centralize(this,"LG",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("LG",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/4));
                       }
                       else if (i==20) {
                           values = centralize(this,"Identity",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Identity",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/4));
                       }
                       else if (i==21) {
                           values = centralize(this,"Truwort.",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Truwort.",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/4));
                       }
                       else if (i==22) {
                           values = centralize(this,"Audacio.",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Audacio.",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/4));
                       }
                       else if (i==24) {
                           values = centralize(this,"Sports",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Sports",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/4));
                           values = centralize(this,"World",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("World",values[0],(int)((y*SCALE+INSET)+(1.2*(y*SCALE+INSET)/4)));
                       }
                       g.setColor(temp);
                }
            }
            private void buildLowerCurves(Graphics g, double x, double y, double dx, double dy, double dx2){
                for(int i=0; i<26; i++){ // grid1 is i=0, we place at grid8,9,10
                   if(i>6&&i<10) {
                       int[] xitems = new int[3];
                       int[] yitems = new int[3];
                       if (i==7){
                           xitems[0] = (int)((x+dx2*i)*SCALE+INSET);
                           xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                           xitems[2] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                           yitems[0] = (int)(y*SCALE+INSET);
                           yitems[1] = (int)(y*SCALE+INSET);
                           yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE);
                           g.fillPolygon(xitems,yitems,3);
                       }
                       else if (i==8) g.fillRoundRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET)-((int)(dy*SCALE)/2)-6, (int)(dx*SCALE-1), 2*(int)(dy*SCALE), (int)(dx*SCALE-1)/2,(int)(dx*SCALE-1)/2); // color shop
                       else {
                           xitems[0] = (int)((x+dx2*i)*SCALE+INSET);
                           xitems[1] = (int)((x+dx2*i)*SCALE+INSET);
                           xitems[2] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                           yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE);
                           yitems[1] = (int)(y*SCALE+INSET);
                           yitems[2] = (int)(y*SCALE+INSET);
                           g.fillPolygon(xitems,yitems,3);
                       }
                   }
                }
            }
            private void buildLowestRow(Graphics g, double x, double y, double dx, double dy, double dx2){
                for(int i=0; i<26; i++){ // grid1 is i=0, we place at grid8,9,10
                   if(i==7||i==8||i==9){
                       g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                       Color temp = g.getColor();
                       g.setColor(labelColor);
                       if (i==7) {
                           values = centralize(this,"Kilmanj.",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Kilmanj.",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/7));
                       }
                       else if (i==8) {
                           values = centralize(this,"Aroma",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Aroma",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/7));
                       }
                       else if (i==9) {
                           values = centralize(this,"Chicken",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Chicken",values[0],(int)((y*SCALE+INSET)+(y*SCALE+INSET)/7));
                           values = centralize(this,"Republic",(int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE));
                           g.drawString("Republic",values[0],(int)((y*SCALE+INSET)+(1.2*(y*SCALE+INSET)/7)));
                       }
                       g.setColor(temp);
                   } // color shop
                }
            }
            private void buildEmptySpacesBelow(Graphics g, double x, double y, double dx, double dy, double dx2){
                for(int i=0; i<26; i++){ // grid1 is i=0, we place at grid2,8,9,10
                   g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true); // color shop
                }
            }
            private void buildUppermostDirectionalArrows(Graphics g, double x, double y, double dx, double dy, double dx2){
                for(int i=0; i<26; i++){ // grid1 is i=0, we place at grid2,8,9,10
                   if(i==4||i==16||i==22) {
                       g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                       int[] xitems = new int[3];
                       int[] yitems = new int[3];
                       xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                       xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                       xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                       yitems[0] = (int)(y*SCALE+INSET);
                       yitems[1] = (int)(y*SCALE+INSET);
                       yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                       g.fillPolygon(xitems,yitems,3);
                   } // color shop
                }
            }
            private void buildUpperHorizontalDirectionalArrows(Graphics g, double x, double y, double dx, double dy, double dx2){
                // x value for line 1 is x+dx2*0, hence we start drawing at line 4
               int[] xitems = new int[3];
               int[] yitems = new int[3];
               for(int i=3; i<5; i++){
                    g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                    if (i!=3){ // point the arrow forwards
                        xitems[0] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                        xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                        xitems[2] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*1.4);
                    }else { // point the arrows backwards
                        xitems[0] = (int)((x+dx2*i)*SCALE+INSET);
                        xitems[1] = (int)((x+dx2*i)*SCALE+INSET);
                        xitems[2] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)/2);
                    }
                    yitems[0] = (int)(y*SCALE+INSET)-(int)((dx*SCALE-1)/4);
                    yitems[1] = (int)(y*SCALE+INSET)+(int)(3*(dx*SCALE-1)/8);
                    yitems[2] = (int)(y*SCALE+INSET)+(int)((dx*SCALE-1)/8);
                    g.fillPolygon(xitems,yitems,3);
                } // color shop
            }
            private void buildEntranceIndicator(Graphics g, double x, double y, double dx, double dy){
                g.fillOval((int)(x*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE), (int)(dy*SCALE)); // color shop
            }
            private void phoneStoresDirection(Graphics g, double x, double y, double dx, double dy, double dx2, String position){
                    for(int i=0; i<26; i++){ // grid1 is i=0, we place at grid2,8,9,10
                       if(i==1&&position.equals("lowest")) {
                           g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                           int[] xitems = new int[3];
                           int[] yitems = new int[3];
                           if (i==1){ // point the arrow upwards
                               xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                               xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                               xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                               yitems[0] = (int)(y*SCALE+INSET);
                               yitems[1] = (int)(y*SCALE+INSET);
                               yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                           }else{
                               xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                               xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                               xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                               yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                               yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                               yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1)+(int)((dy*SCALE-1)/4);
                           }
                           g.fillPolygon(xitems,yitems,3);
                       } else if (position.equals("lower")) {
                            if(i==1||i==13||i==14||i==18) {
                                g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                                int[] xitems = new int[3];
                                int[] yitems = new int[3];
                                if (i==1){ // point the arrow upwards
                                    xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                    xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                    xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                    yitems[0] = (int)(y*SCALE+INSET);
                                    yitems[1] = (int)(y*SCALE+INSET);
                                    yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                                    g.fillPolygon(xitems,yitems,3);
                                }else { // point the arrows downward
                                    xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                    xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                    xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                    yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                                    yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                                    yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1)+(int)((dy*SCALE-1)/4);
                                    g.fillPolygon(xitems,yitems,3);
                                }
                            } // color shop
                        } else if (position.equals("upper")){
                            if(i==5||i==6||i==12||i==21) {
                                g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                                int[] xitems = new int[3];
                                int[] yitems = new int[3];
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                yitems[0] = (int)(y*SCALE+INSET);
                                yitems[1] = (int)(y*SCALE+INSET);
                                yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                                g.fillPolygon(xitems,yitems,3);
                            } // color shop
                        } else if (position.equals("horizontal")){
                            int[] xitems = new int[3];
                            int[] yitems = new int[3];
                            if(i>0&&i<21) {
                                g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                                if (i!=0){ // point the arrow forwards
                                    xitems[0] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                                    xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                                    xitems[2] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*1.4);
                                }else { // point the arrows backwards
                                    xitems[0] = (int)((x+dx2*i)*SCALE+INSET);
                                    xitems[1] = (int)((x+dx2*i)*SCALE+INSET);
                                    xitems[2] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)/2);
                                }
                                yitems[0] = (int)(y*SCALE+INSET)-(int)((dx*SCALE-1)/4);
                                yitems[1] = (int)(y*SCALE+INSET)+(int)(3*(dx*SCALE-1)/8);
                                yitems[2] = (int)(y*SCALE+INSET)+(int)((dx*SCALE-1)/8);
                                g.fillPolygon(xitems,yitems,3);
                            } // color shop
                        }
                    }
                }
            private void compStoresDirection(Graphics g, double x, double y, double dx, double dy, double dx2, String position){
                for(int i=0; i<26; i++){ // grid1 is i=0, we place at grid2,8,9,10
                   if(i==1&&position.equals("lowest")) {
                       g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                       int[] xitems = new int[3];
                       int[] yitems = new int[3];
                       if (i==1){ // point the arrow upwards
                           xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                           xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                           xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                           yitems[0] = (int)(y*SCALE+INSET);
                           yitems[1] = (int)(y*SCALE+INSET);
                           yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                       }else{
                           xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                           xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                           xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                           yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                           yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                           yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1)+(int)((dy*SCALE-1)/4);
                       }
                       g.fillPolygon(xitems,yitems,3);
                   } else if (position.equals("lower")) {
                        if(i==1||i==3||i==18) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            int[] xitems = new int[3];
                            int[] yitems = new int[3];
                            if (i==1){ // point the arrow upwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                yitems[0] = (int)(y*SCALE+INSET);
                                yitems[1] = (int)(y*SCALE+INSET);
                                yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                                g.fillPolygon(xitems,yitems,3);
                            }else { // point the arrows downward
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                                yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                                yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1)+(int)((dy*SCALE-1)/4);
                                g.fillPolygon(xitems,yitems,3);
                            }
                        } // color shop
                    } else if (position.equals("upper")){
                        if(i==12) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            int[] xitems = new int[3];
                            int[] yitems = new int[3];
                            xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                            xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                            xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                            yitems[0] = (int)(y*SCALE+INSET);
                            yitems[1] = (int)(y*SCALE+INSET);
                            yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                            g.fillPolygon(xitems,yitems,3);
                        } // color shop
                    } else if (position.equals("horizontal")){
                        int[] xitems = new int[3];
                        int[] yitems = new int[3];
                        if(i>0&&i<18) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            if (i!=0){ // point the arrow forwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*1.4);
                            }else { // point the arrows backwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)/2);
                            }
                            yitems[0] = (int)(y*SCALE+INSET)-(int)((dx*SCALE-1)/4);
                            yitems[1] = (int)(y*SCALE+INSET)+(int)(3*(dx*SCALE-1)/8);
                            yitems[2] = (int)(y*SCALE+INSET)+(int)((dx*SCALE-1)/8);
                            g.fillPolygon(xitems,yitems,3);
                        } // color shop
                    }
                }
            }
            private void electStoresDirection(Graphics g, double x, double y, double dx, double dy, double dx2, String position){
                for(int i=0; i<26; i++){ // grid1 is i=0, we place at grid2,8,9,10
                   if(i==1&&position.equals("lowest")) {
                       g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                       int[] xitems = new int[3];
                       int[] yitems = new int[3];
                       if (i==1){ // point the arrow upwards
                           xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                           xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                           xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                           yitems[0] = (int)(y*SCALE+INSET);
                           yitems[1] = (int)(y*SCALE+INSET);
                           yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                       }else{
                           xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                           xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                           xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                           yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                           yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                           yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1)+(int)((dy*SCALE-1)/4);
                       }
                       g.fillPolygon(xitems,yitems,3);
                   } else if (position.equals("lower")) {
                        if(i==1||i==3||i==18) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            int[] xitems = new int[3];
                            int[] yitems = new int[3];
                            if (i==1){ // point the arrow upwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                yitems[0] = (int)(y*SCALE+INSET);
                                yitems[1] = (int)(y*SCALE+INSET);
                                yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                                g.fillPolygon(xitems,yitems,3);
                            }else { // point the arrows downward
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                                yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                                yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1)+(int)((dy*SCALE-1)/4);
                                g.fillPolygon(xitems,yitems,3);
                            }
                        } // color shop
                    } else if (position.equals("upper")){
                        if(i==6) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            int[] xitems = new int[3];
                            int[] yitems = new int[3];
                            xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                            xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                            xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                            yitems[0] = (int)(y*SCALE+INSET);
                            yitems[1] = (int)(y*SCALE+INSET);
                            yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                            g.fillPolygon(xitems,yitems,3);
                        } // color shop
                    } else if (position.equals("horizontal")){
                        int[] xitems = new int[3];
                        int[] yitems = new int[3];
                        if(i>0&&i<18) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            if (i!=0){ // point the arrow forwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*1.4);
                            }else { // point the arrows backwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)/2);
                            }
                            yitems[0] = (int)(y*SCALE+INSET)-(int)((dx*SCALE-1)/4);
                            yitems[1] = (int)(y*SCALE+INSET)+(int)(3*(dx*SCALE-1)/8);
                            yitems[2] = (int)(y*SCALE+INSET)+(int)((dx*SCALE-1)/8);
                            g.fillPolygon(xitems,yitems,3);
                        } // color shop
                    }
                }
            }
            private void fabricStoresDirection(Graphics g, double x, double y, double dx, double dy, double dx2, String position){
                for(int i=0; i<26; i++){ // grid1 is i=0, we place at grid2,8,9,10
                   if(i==1&&position.equals("lowest")) {
                       g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                       int[] xitems = new int[3];
                       int[] yitems = new int[3];
                       if (i==1){ // point the arrow upwards
                           xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                           xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                           xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                           yitems[0] = (int)(y*SCALE+INSET);
                           yitems[1] = (int)(y*SCALE+INSET);
                           yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                       }else{
                           xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                           xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                           xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                           yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                           yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                           yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1)+(int)((dy*SCALE-1)/4);
                       }
                       g.fillPolygon(xitems,yitems,3);
                   } else if (position.equals("lower")) {
                        if(i==1||i==19||i==21||i==22) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            int[] xitems = new int[3];
                            int[] yitems = new int[3];
                            if (i==1){ // point the arrow upwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                yitems[0] = (int)(y*SCALE+INSET);
                                yitems[1] = (int)(y*SCALE+INSET);
                                yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                                g.fillPolygon(xitems,yitems,3);
                            }else { // point the arrows downward
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                                yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                                yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1)+(int)((dy*SCALE-1)/4);
                                g.fillPolygon(xitems,yitems,3);
                            }
                        } // color shop
                    } else if (position.equals("upper")){
                        if(i==0||i==23||i==26) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            int[] xitems = new int[3];
                            int[] yitems = new int[3];
                            xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                            xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                            xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                            yitems[0] = (int)(y*SCALE+INSET);
                            yitems[1] = (int)(y*SCALE+INSET);
                            yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                            g.fillPolygon(xitems,yitems,3);
                        } // color shop
                    } else if (position.equals("horizontal")){
                        int[] xitems = new int[3];
                        int[] yitems = new int[3];
                        if(i>=0&&i<23) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            if (i!=0){ // point the arrow forwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*1.4);
                            }else { // point the arrows backwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)/2);
                            }
                            yitems[0] = (int)(y*SCALE+INSET)-(int)((dx*SCALE-1)/4);
                            yitems[1] = (int)(y*SCALE+INSET)+(int)(3*(dx*SCALE-1)/8);
                            yitems[2] = (int)(y*SCALE+INSET)+(int)((dx*SCALE-1)/8);
                            g.fillPolygon(xitems,yitems,3);
                        } // color shop
                    }
                }
            }
            private void cosmeticStoresDirection(Graphics g, double x, double y, double dx, double dy, double dx2, String position){
                for(int i=0; i<26; i++){ // grid1 is i=0, we place at grid2,8,9,10
                   if(i==1&&position.equals("lowest")) {
                       g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                       int[] xitems = new int[3];
                       int[] yitems = new int[3];
                       if (i==1){ // point the arrow upwards
                           xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                           xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                           xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                           yitems[0] = (int)(y*SCALE+INSET);
                           yitems[1] = (int)(y*SCALE+INSET);
                           yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                       }else{
                           xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                           xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                           xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                           yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                           yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                           yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1)+(int)((dy*SCALE-1)/4);
                       }
                       g.fillPolygon(xitems,yitems,3);
                   } else if (position.equals("lower")) {
                        if(i==1) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            int[] xitems = new int[3];
                            int[] yitems = new int[3];
                            if (i==1){ // point the arrow upwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                yitems[0] = (int)(y*SCALE+INSET);
                                yitems[1] = (int)(y*SCALE+INSET);
                                yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                                g.fillPolygon(xitems,yitems,3);
                            }else { // point the arrows downward
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                                yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                                yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1)+(int)((dy*SCALE-1)/4);
                                g.fillPolygon(xitems,yitems,3);
                            }
                        } // color shop
                    } else if (position.equals("upper")){
                        if(i==10||i==14||i==15||i==18) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            int[] xitems = new int[3];
                            int[] yitems = new int[3];
                            xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                            xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                            xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                            yitems[0] = (int)(y*SCALE+INSET);
                            yitems[1] = (int)(y*SCALE+INSET);
                            yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                            g.fillPolygon(xitems,yitems,3);
                        } // color shop
                    } else if (position.equals("horizontal")){
                        int[] xitems = new int[3];
                        int[] yitems = new int[3];
                        if(i>0&&i<18) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            if (i!=0){ // point the arrow forwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*1.4);
                            }else { // point the arrows backwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)/2);
                            }
                            yitems[0] = (int)(y*SCALE+INSET)-(int)((dx*SCALE-1)/4);
                            yitems[1] = (int)(y*SCALE+INSET)+(int)(3*(dx*SCALE-1)/8);
                            yitems[2] = (int)(y*SCALE+INSET)+(int)((dx*SCALE-1)/8);
                            g.fillPolygon(xitems,yitems,3);
                        } // color shop
                    }
                }
            }
            private void fashionAccStoresDirection(Graphics g, double x, double y, double dx, double dy, double dx2, String position){
                for(int i=0; i<26; i++){ // grid1 is i=0, we place at grid2,8,9,10
                   if(i==1&&position.equals("lowest")) {
                       g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                       int[] xitems = new int[3];
                       int[] yitems = new int[3];
                       if (i==1){ // point the arrow upwards
                           xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                           xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                           xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                           yitems[0] = (int)(y*SCALE+INSET);
                           yitems[1] = (int)(y*SCALE+INSET);
                           yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                       }else{
                           xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                           xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                           xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                           yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                           yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                           yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1)+(int)((dy*SCALE-1)/4);
                       }
                       g.fillPolygon(xitems,yitems,3);
                   } else if (position.equals("lower")) {
                        if(i==1) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            int[] xitems = new int[3];
                            int[] yitems = new int[3];
                            if (i==1){ // point the arrow upwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                yitems[0] = (int)(y*SCALE+INSET);
                                yitems[1] = (int)(y*SCALE+INSET);
                                yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                                g.fillPolygon(xitems,yitems,3);
                            }else { // point the arrows downward
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                                yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                                yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1)+(int)((dy*SCALE-1)/4);
                                g.fillPolygon(xitems,yitems,3);
                            }
                        } // color shop
                    } else if (position.equals("upper")){
                        if(i==2||i==11||i==13||i==17||i==20) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            int[] xitems = new int[3];
                            int[] yitems = new int[3];
                            xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                            xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                            xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                            yitems[0] = (int)(y*SCALE+INSET);
                            yitems[1] = (int)(y*SCALE+INSET);
                            yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                            g.fillPolygon(xitems,yitems,3);
                        } // color shop
                    } else if (position.equals("horizontal")){
                        int[] xitems = new int[3];
                        int[] yitems = new int[3];
                        if(i>0&&i<20) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            if (i!=0){ // point the arrow forwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*1.4);
                            }else { // point the arrows backwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)/2);
                            }
                            yitems[0] = (int)(y*SCALE+INSET)-(int)((dx*SCALE-1)/4);
                            yitems[1] = (int)(y*SCALE+INSET)+(int)(3*(dx*SCALE-1)/8);
                            yitems[2] = (int)(y*SCALE+INSET)+(int)((dx*SCALE-1)/8);
                            g.fillPolygon(xitems,yitems,3);
                        } // color shop
                    }
                }
            }
            private void FastFoodStoresDirection(Graphics g, double x, double y, double dx, double dy, double dx2, String position){
                /*"Maybrand","Kontessa","Swatch","a! Accessories","Diva"*/
                for(int i=0; i<26; i++){ // grid1 is i=0, we place at grid2,8,9,10
                   if((i==1||i==7||i==8||i==9)&&position.equals("lowest")) {
                       g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                       int[] xitems = new int[3];
                       int[] yitems = new int[3];
                       if (i==1){ // point the arrow upwards
                           xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                           xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                           xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                           yitems[0] = (int)(y*SCALE+INSET);
                           yitems[1] = (int)(y*SCALE+INSET);
                           yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                       }else if(i==7||i==8||i==9){ // point the arrow downwards
                           xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                           xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                           xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                           yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                           yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                           yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1)+(int)((dy*SCALE-1)/4);
                       }
                       g.fillPolygon(xitems,yitems,3);
                   } else if (position.equals("lower")) {
                        if(i==1||i==7||i==8||i==9) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            int[] xitems = new int[3];
                            int[] yitems = new int[3];
                            if (i==1){ // point the arrow upwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                yitems[0] = (int)(y*SCALE+INSET);
                                yitems[1] = (int)(y*SCALE+INSET);
                                yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                                g.fillPolygon(xitems,yitems,3);
                            }else { // point the arrows downward
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                                yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                                yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1)+(int)((dy*SCALE-1)/4);
                                g.fillPolygon(xitems,yitems,3);
                            }
                        } // color shop
                    } else if (position.equals("upper")){
                    } else if (position.equals("horizontal")){
                        int[] xitems = new int[3];
                        int[] yitems = new int[3];
                        if(i>0&&i<9) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            if (i!=0){ // point the arrow forwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*1.4);
                            }else { // point the arrows backwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)/2);
                            }
                            yitems[0] = (int)(y*SCALE+INSET)-(int)((dx*SCALE-1)/4);
                            yitems[1] = (int)(y*SCALE+INSET)+(int)(3*(dx*SCALE-1)/8);
                            yitems[2] = (int)(y*SCALE+INSET)+(int)((dx*SCALE-1)/8);
                            g.fillPolygon(xitems,yitems,3);
                        } // color shop
                    }
                }
            }
            private void medStoresDirection(Graphics g, double x, double y, double dx, double dy, double dx2, String position){
                for(int i=0; i<26; i++){ // grid1 is i=0, we place at grid2,8,9,10
                   if(i==1&&position.equals("lowest")) {
                       g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                       int[] xitems = new int[3];
                       int[] yitems = new int[3];
                       if (i==1){ // point the arrow upwards
                           xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                           xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                           xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                           yitems[0] = (int)(y*SCALE+INSET);
                           yitems[1] = (int)(y*SCALE+INSET);
                           yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                       }else{
                           xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                           xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                           xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                           yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                           yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                           yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1)+(int)((dy*SCALE-1)/4);
                       }
                       g.fillPolygon(xitems,yitems,3);
                   } else if (position.equals("lower")) {
                        if(i==1) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            int[] xitems = new int[3];
                            int[] yitems = new int[3];
                            if (i==1){ // point the arrow upwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                yitems[0] = (int)(y*SCALE+INSET);
                                yitems[1] = (int)(y*SCALE+INSET);
                                yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                                g.fillPolygon(xitems,yitems,3);
                            }else { // point the arrows downward
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                                yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                                yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1)+(int)((dy*SCALE-1)/4);
                                g.fillPolygon(xitems,yitems,3);
                            }
                        } // color shop
                    } else if (position.equals("upper")){
                        if(i==3||i==19) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            int[] xitems = new int[3];
                            int[] yitems = new int[3];
                            xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                            xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                            xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                            yitems[0] = (int)(y*SCALE+INSET);
                            yitems[1] = (int)(y*SCALE+INSET);
                            yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                            g.fillPolygon(xitems,yitems,3);
                        } // color shop
                    } else if (position.equals("horizontal")){
                        int[] xitems = new int[3];
                        int[] yitems = new int[3];
                        if(i>0&&i<19) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            if (i!=0){ // point the arrow forwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*1.4);
                            }else { // point the arrows backwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)/2);
                            }
                            yitems[0] = (int)(y*SCALE+INSET)-(int)((dx*SCALE-1)/4);
                            yitems[1] = (int)(y*SCALE+INSET)+(int)(3*(dx*SCALE-1)/8);
                            yitems[2] = (int)(y*SCALE+INSET)+(int)((dx*SCALE-1)/8);
                            g.fillPolygon(xitems,yitems,3);
                        } // color shop
                    }
                }
            }
            private void cinemaStoresDirection(Graphics g, double x, double y, double dx, double dy, double dx2, String position){
                for(int i=0; i<26; i++){ // grid1 is i=0, we place at grid2,8,9,10
                   if(i==1&&position.equals("lowest")) {
                       g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                       int[] xitems = new int[3];
                       int[] yitems = new int[3];
                       if (i==1){ // point the arrow upwards
                           xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                           xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                           xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                           yitems[0] = (int)(y*SCALE+INSET);
                           yitems[1] = (int)(y*SCALE+INSET);
                           yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                       }else{
                           xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                           xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                           xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                           yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                           yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                           yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1)+(int)((dy*SCALE-1)/4);
                       }
                       g.fillPolygon(xitems,yitems,3);
                   } else if (position.equals("lower")) {
                        if(i==1) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            int[] xitems = new int[3];
                            int[] yitems = new int[3];
                            if (i==1){ // point the arrow upwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                yitems[0] = (int)(y*SCALE+INSET);
                                yitems[1] = (int)(y*SCALE+INSET);
                                yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                                g.fillPolygon(xitems,yitems,3);
                            }else { // point the arrows downward
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                                yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                                yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1)+(int)((dy*SCALE-1)/4);
                                g.fillPolygon(xitems,yitems,3);
                            }
                        } // color shop
                    } else if (position.equals("upper")){
                        if(i==4) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            int[] xitems = new int[3];
                            int[] yitems = new int[3];
                            xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                            xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                            xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                            yitems[0] = (int)(y*SCALE+INSET);
                            yitems[1] = (int)(y*SCALE+INSET);
                            yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                            g.fillPolygon(xitems,yitems,3);
                        } // color shop
                    } else if (position.equals("uppermost")){
                        if(i==4) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            int[] xitems = new int[3];
                            int[] yitems = new int[3];
                            xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                            xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                            xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                            yitems[0] = (int)(y*SCALE+INSET);
                            yitems[1] = (int)(y*SCALE+INSET);
                            yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                            g.fillPolygon(xitems,yitems,3);
                        } // color shop
                    } else if (position.equals("horizontal")){
                        int[] xitems = new int[3];
                        int[] yitems = new int[3];
                        if(i>0&&i<4) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            if (i!=0){ // point the arrow forwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*1.4);
                            }else { // point the arrows backwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)/2);
                            }
                            yitems[0] = (int)(y*SCALE+INSET)-(int)((dx*SCALE-1)/4);
                            yitems[1] = (int)(y*SCALE+INSET)+(int)(3*(dx*SCALE-1)/8);
                            yitems[2] = (int)(y*SCALE+INSET)+(int)((dx*SCALE-1)/8);
                            g.fillPolygon(xitems,yitems,3);
                        } // color shop
                    }
                }
            }
            private void sportStoresDirection(Graphics g, double x, double y, double dx, double dy, double dx2, String position){
                for(int i=0; i<26; i++){ // grid1 is i=0, we place at grid2,8,9,10
                   if(i==1&&position.equals("lowest")) {
                       g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                       int[] xitems = new int[3];
                       int[] yitems = new int[3];
                       if (i==1){ // point the arrow upwards
                           xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                           xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                           xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                           yitems[0] = (int)(y*SCALE+INSET);
                           yitems[1] = (int)(y*SCALE+INSET);
                           yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                       }else{
                           xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                           xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                           xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                           yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                           yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                           yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1)+(int)((dy*SCALE-1)/4);
                       }
                       g.fillPolygon(xitems,yitems,3);
                   } else if (position.equals("lower")) {
                        if(i==1||i==24) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            int[] xitems = new int[3];
                            int[] yitems = new int[3];
                            if (i==1){ // point the arrow upwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                yitems[0] = (int)(y*SCALE+INSET);
                                yitems[1] = (int)(y*SCALE+INSET);
                                yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                                g.fillPolygon(xitems,yitems,3);
                            }else { // point the arrows downward
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                                yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                                yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1)+(int)((dy*SCALE-1)/4);
                                g.fillPolygon(xitems,yitems,3);
                            }
                        } // color shop
                    } else if (position.equals("upper")){
                    } else if (position.equals("horizontal")){
                        int[] xitems = new int[3];
                        int[] yitems = new int[3];
                        if(i>0&&i<24) {
                            g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                            if (i!=0){ // point the arrow forwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*1.4);
                            }else { // point the arrows backwards
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)/2);
                            }
                            yitems[0] = (int)(y*SCALE+INSET)-(int)((dx*SCALE-1)/4);
                            yitems[1] = (int)(y*SCALE+INSET)+(int)(3*(dx*SCALE-1)/8);
                            yitems[2] = (int)(y*SCALE+INSET)+(int)((dx*SCALE-1)/8);
                            g.fillPolygon(xitems,yitems,3);
                        } // color shop
                    }
                }
            }
            private void shopriteStoresDirection(Graphics g, double x, double y, double dx, double dy, double dx2, String position){
                    for(int i=0; i<26; i++){ // grid1 is i=0, we place at grid2,8,9,10
                       if(i==1&&position.equals("lowest")) {
                           g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                           int[] xitems = new int[3];
                           int[] yitems = new int[3];
                           if (i==1){ // point the arrow upwards
                               xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                               xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                               xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                               yitems[0] = (int)(y*SCALE+INSET);
                               yitems[1] = (int)(y*SCALE+INSET);
                               yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                           }else{
                               xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                               xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                               xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                               yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                               yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                               yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1)+(int)((dy*SCALE-1)/4);
                           }
                           g.fillPolygon(xitems,yitems,3);
                       } else if (position.equals("lower")) {
                            if(i==1) {
                                g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                                int[] xitems = new int[3];
                                int[] yitems = new int[3];
                                if (i==1){ // point the arrow upwards
                                    xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                    xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                    xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                    yitems[0] = (int)(y*SCALE+INSET);
                                    yitems[1] = (int)(y*SCALE+INSET);
                                    yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                                    g.fillPolygon(xitems,yitems,3);
                                }else { // point the arrows downward
                                    xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                    xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                    xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                    yitems[0] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                                    yitems[1] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1);
                                    yitems[2] = (int)(y*SCALE+INSET)+(int)(dy*SCALE-1)+(int)((dy*SCALE-1)/4);
                                    g.fillPolygon(xitems,yitems,3);
                                }
                            } // color shop
                        } else if (position.equals("upper")){
                            if(i==16) {
                                g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                                int[] xitems = new int[3];
                                int[] yitems = new int[3];
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                yitems[0] = (int)(y*SCALE+INSET);
                                yitems[1] = (int)(y*SCALE+INSET);
                                yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                                g.fillPolygon(xitems,yitems,3);
                            } // color shop
                        } else if (position.equals("uppermost")){
                            if(i==16) {
                                g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                                int[] xitems = new int[3];
                                int[] yitems = new int[3];
                                xitems[0] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)*1.5);
                                xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*2);
                                xitems[2] = (int)((x+dx2*i)*SCALE+INSET);
                                yitems[0] = (int)(y*SCALE+INSET);
                                yitems[1] = (int)(y*SCALE+INSET);
                                yitems[2] = (int)(y*SCALE+INSET)-(int)((dy*SCALE-1)/4);
                                g.fillPolygon(xitems,yitems,3);
                            } // color shop
                        } else if (position.equals("horizontal")){
                            int[] xitems = new int[3];
                            int[] yitems = new int[3];
                            if(i>0&&i<16) {
                                g.fill3DRect((int)((x+dx2*i)*SCALE+INSET), (int)(y*SCALE+INSET), (int)(dx*SCALE-1), (int)(dy*SCALE), true);
                                if (i!=0){ // point the arrow forwards
                                    xitems[0] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                                    xitems[1] = (int)((x+dx2*i)*SCALE+INSET)+(int)(dx*SCALE-1);
                                    xitems[2] = (int)((x+dx2*i)*SCALE+INSET)+(int)((dx*SCALE-1)*1.4);
                                }else { // point the arrows backwards
                                    xitems[0] = (int)((x+dx2*i)*SCALE+INSET);
                                    xitems[1] = (int)((x+dx2*i)*SCALE+INSET);
                                    xitems[2] = (int)((x+dx2*i)*SCALE+INSET)-(int)((dx*SCALE-1)/2);
                                }
                                yitems[0] = (int)(y*SCALE+INSET)-(int)((dx*SCALE-1)/4);
                                yitems[1] = (int)(y*SCALE+INSET)+(int)(3*(dx*SCALE-1)/8);
                                yitems[2] = (int)(y*SCALE+INSET)+(int)((dx*SCALE-1)/8);
                                g.fillPolygon(xitems,yitems,3);
                            } // color shop
                        }
                    }
                }
            private void pointToPhoneShops(Graphics g, ShoppingMall building) {
                phoneStoresDirection(g,building.getWidth()/60,building.getHeight()/1.6,building.getWidth()/200,building.getHeight()/9,building.getWidth()/30,"lowest");
                phoneStoresDirection(g,building.getWidth()/60,building.getHeight()/2.05,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"lower");
                phoneStoresDirection(g,building.getWidth()/60,building.getHeight()/2.88,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"upper");
                phoneStoresDirection(g,building.getWidth()/48,building.getHeight()/2.19,building.getWidth()/48,building.getHeight()/118.75,building.getWidth()/30,"horizontal");

            }
            private void pointToCompShops(Graphics g, ShoppingMall building) {
                compStoresDirection(g,building.getWidth()/60,building.getHeight()/1.6,building.getWidth()/200,building.getHeight()/9,building.getWidth()/30,"lowest");
                compStoresDirection(g,building.getWidth()/60,building.getHeight()/2.05,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"lower");
                compStoresDirection(g,building.getWidth()/60,building.getHeight()/2.88,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"upper");
                compStoresDirection(g,building.getWidth()/48,building.getHeight()/2.19,building.getWidth()/48,building.getHeight()/118.75,building.getWidth()/30,"horizontal");
            }
            private void pointToElectShops(Graphics g, ShoppingMall building) {
                electStoresDirection(g,building.getWidth()/60,building.getHeight()/1.6,building.getWidth()/200,building.getHeight()/9,building.getWidth()/30,"lowest");
                electStoresDirection(g,building.getWidth()/60,building.getHeight()/2.05,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"lower");
                electStoresDirection(g,building.getWidth()/60,building.getHeight()/2.88,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"upper");
                electStoresDirection(g,building.getWidth()/48,building.getHeight()/2.19,building.getWidth()/48,building.getHeight()/118.75,building.getWidth()/30,"horizontal");
            }
            private void pointToFabricShops(Graphics g, ShoppingMall building) {
                fabricStoresDirection(g,building.getWidth()/60,building.getHeight()/1.6,building.getWidth()/200,building.getHeight()/9,building.getWidth()/30,"lowest");
                fabricStoresDirection(g,building.getWidth()/60,building.getHeight()/2.05,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"lower");
                fabricStoresDirection(g,building.getWidth()/60,building.getHeight()/2.88,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"upper");
                fabricStoresDirection(g,building.getWidth()/48,building.getHeight()/2.19,building.getWidth()/48,building.getHeight()/118.75,building.getWidth()/30,"horizontal");
            }
            private void pointToCosmeticShops(Graphics g, ShoppingMall building) {
                cosmeticStoresDirection(g,building.getWidth()/60,building.getHeight()/1.6,building.getWidth()/200,building.getHeight()/9,building.getWidth()/30,"lowest");
                cosmeticStoresDirection(g,building.getWidth()/60,building.getHeight()/2.05,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"lower");
                cosmeticStoresDirection(g,building.getWidth()/60,building.getHeight()/2.88,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"upper");
                cosmeticStoresDirection(g,building.getWidth()/48,building.getHeight()/2.19,building.getWidth()/48,building.getHeight()/118.75,building.getWidth()/30,"horizontal");
            }
            private void pointToFashionAccShops(Graphics g, ShoppingMall building) {
                fashionAccStoresDirection(g,building.getWidth()/60,building.getHeight()/1.6,building.getWidth()/200,building.getHeight()/9,building.getWidth()/30,"lowest");
                fashionAccStoresDirection(g,building.getWidth()/60,building.getHeight()/2.05,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"lower");
                fashionAccStoresDirection(g,building.getWidth()/60,building.getHeight()/2.88,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"upper");
                fashionAccStoresDirection(g,building.getWidth()/48,building.getHeight()/2.19,building.getWidth()/48,building.getHeight()/118.75,building.getWidth()/30,"horizontal");
            }
            private void pointToFastFoodShops(Graphics g, ShoppingMall building) {
                FastFoodStoresDirection(g,building.getWidth()/60,building.getHeight()/1.6,building.getWidth()/200,building.getHeight()/9,building.getWidth()/30,"lowest");
                FastFoodStoresDirection(g,building.getWidth()/60,building.getHeight()/2.05,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"lower");
                FastFoodStoresDirection(g,building.getWidth()/60,building.getHeight()/2.88,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"upper");
                FastFoodStoresDirection(g,building.getWidth()/48,building.getHeight()/2.19,building.getWidth()/48,building.getHeight()/118.75,building.getWidth()/30,"horizontal");
            }
            private void pointToMedShops(Graphics g, ShoppingMall building) {
                medStoresDirection(g,building.getWidth()/60,building.getHeight()/1.6,building.getWidth()/200,building.getHeight()/9,building.getWidth()/30,"lowest");
                medStoresDirection(g,building.getWidth()/60,building.getHeight()/2.05,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"lower");
                medStoresDirection(g,building.getWidth()/60,building.getHeight()/2.88,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"upper");
                medStoresDirection(g,building.getWidth()/48,building.getHeight()/2.19,building.getWidth()/48,building.getHeight()/118.75,building.getWidth()/30,"horizontal");
            }
            private void pointToCinemaShops(Graphics g, ShoppingMall building) {
                cinemaStoresDirection(g,building.getWidth()/60,building.getHeight()/1.6,building.getWidth()/200,building.getHeight()/9,building.getWidth()/30,"lowest");
                cinemaStoresDirection(g,building.getWidth()/60,building.getHeight()/2.05,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"lower");
                cinemaStoresDirection(g,building.getWidth()/60,building.getHeight()/2.88,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"upper");
                cinemaStoresDirection(g,building.getWidth()/60,building.getHeight()/4.48,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"uppermost");
                cinemaStoresDirection(g,building.getWidth()/48,building.getHeight()/2.19,building.getWidth()/48,building.getHeight()/118.75,building.getWidth()/30,"horizontal");
            }
            private void pointToSportShops(Graphics g, ShoppingMall building) {
                sportStoresDirection(g,building.getWidth()/60,building.getHeight()/1.6,building.getWidth()/200,building.getHeight()/9,building.getWidth()/30,"lowest");
                sportStoresDirection(g,building.getWidth()/60,building.getHeight()/2.05,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"lower");
                sportStoresDirection(g,building.getWidth()/60,building.getHeight()/2.88,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"upper");
                sportStoresDirection(g,building.getWidth()/60,building.getHeight()/4.48,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"uppermost");
                sportStoresDirection(g,building.getWidth()/48,building.getHeight()/2.19,building.getWidth()/48,building.getHeight()/118.75,building.getWidth()/30,"horizontal");
            }
            private void pointToShopriteShops(Graphics g, ShoppingMall building) {
                shopriteStoresDirection(g,building.getWidth()/60,building.getHeight()/1.6,building.getWidth()/200,building.getHeight()/9,building.getWidth()/30,"lowest");
                shopriteStoresDirection(g,building.getWidth()/60,building.getHeight()/2.05,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"lower");
                shopriteStoresDirection(g,building.getWidth()/60,building.getHeight()/2.88,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"upper");
                shopriteStoresDirection(g,building.getWidth()/60,building.getHeight()/4.48,building.getWidth()/200,2*building.getHeight()/21,building.getWidth()/30,"uppermost");
                shopriteStoresDirection(g,building.getWidth()/48,building.getHeight()/2.19,building.getWidth()/48,building.getHeight()/118.75,building.getWidth()/30,"horizontal");
            }
        }
    }
}