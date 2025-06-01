package ticket.booking.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Manages database operations for the train booking system
 */
public class DatabaseManager {
    private static final String DATA_DIR = "data";
    private static final String USERS_FILE = DATA_DIR + "/users.json";
    private static final String TRAINS_FILE = DATA_DIR + "/trains.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Initialize the database
     */
    public static void initDatabase() {
        try {
            // Create data directory if it doesn't exist
            Files.createDirectories(Paths.get(DATA_DIR));

            // Check if users file exists, create it if not
            File usersFile = new File(USERS_FILE);
            if (!usersFile.exists()) {
                // Create default admin user
                List<User> defaultUsers = new ArrayList<>();
                defaultUsers.add(new User(
                        "admin-" + java.util.UUID.randomUUID().toString(),
                        "admin",
                        UserServiceUtil.hashPassword("admin123"),
                        "admin@trainbooking.com",
                        "1234567890"
                ));
                saveUsers(defaultUsers);
            }

            // Check if trains file exists, create it if not
            File trainsFile = new File(TRAINS_FILE);
            if (!trainsFile.exists()) {
                // Create initial trains data
                List<Train> initialTrains = createInitialTrainsData();
                saveTrains(initialTrains);
            }
        } catch (Exception e) {
            System.err.println("Error initializing database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Loads users from the JSON database
     */
    public static List<User> loadUsers() {
        try {
            // Create directory if it doesn't exist
            Files.createDirectories(Paths.get(DATA_DIR));

            File file = new File(USERS_FILE);
            if (!file.exists()) {
                // Create empty users file if it doesn't exist
                Files.write(Paths.get(USERS_FILE), "[]".getBytes());
                return new ArrayList<>();
            }

            return objectMapper.readValue(file, new TypeReference<List<User>>() {});
        } catch (IOException e) {
            System.err.println("Error loading users: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Saves users to the JSON database
     */
    public static void saveUsers(List<User> users) {
        try {
            objectMapper.writeValue(new File(USERS_FILE), users);
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    /**
     * Loads trains from the JSON database
     */
    public static List<Train> loadTrains() {
        try {
            // Create directory if it doesn't exist
            Files.createDirectories(Paths.get(DATA_DIR));

            File file = new File(TRAINS_FILE);
            if (!file.exists()) {
                // Create initial trains data if file doesn't exist
                List<Train> initialTrains = createInitialTrainsData();
                objectMapper.writeValue(file, initialTrains);
                return initialTrains;
            }

            return objectMapper.readValue(file, new TypeReference<List<Train>>() {});
        } catch (IOException e) {
            System.err.println("Error loading trains: " + e.getMessage());
            return createInitialTrainsData(); // Return default data on error
        }
    }

    /**
     * Saves trains to the JSON database
     */
    public static void saveTrains(List<Train> trains) {
        try {
            objectMapper.writeValue(new File(TRAINS_FILE), trains);
        } catch (IOException e) {
            System.err.println("Error saving trains: " + e.getMessage());
        }
    }

    /**
     * Creates initial train data with major Indian cities
     */
    private static List<Train> createInitialTrainsData() {
        List<Train> trains = new ArrayList<>();

        // Add all the train routes
        trains.addAll(createRajdhaniExpressTrains());
        trains.addAll(createShatabdiExpressTrains());
        trains.addAll(createDurontoExpressTrains());
        trains.addAll(createVandeBharatTrains());
        trains.addAll(createSuperFastTrains());
        trains.addAll(createPassengerTrains());

        return trains;
    }

    /**
     * Creates Rajdhani Express trains connecting major cities to Delhi
     */
    private static List<Train> createRajdhaniExpressTrains() {
        List<Train> trains = new ArrayList<>();

        // Mumbai to Delhi Rajdhani Express
        Train mumbaiRajdhani = new Train();
        mumbaiRajdhani.setTrainId("rajdhani_mumbai");
        mumbaiRajdhani.setTrainNo("12951");
        mumbaiRajdhani.setSeats(createSeatLayout(4, 6));
        mumbaiRajdhani.setStations(Arrays.asList("mumbai", "surat", "vadodara", "kota", "delhi"));
        mumbaiRajdhani.getStationTimes().put("mumbai", "16:35:00");
        mumbaiRajdhani.getStationTimes().put("surat", "19:45:00");
        mumbaiRajdhani.getStationTimes().put("vadodara", "22:15:00");
        mumbaiRajdhani.getStationTimes().put("kota", "02:30:00");
        mumbaiRajdhani.getStationTimes().put("delhi", "06:15:00");
        trains.add(mumbaiRajdhani);

        // Howrah (Kolkata) to Delhi Rajdhani Express
        Train kolkataRajdhani = new Train();
        kolkataRajdhani.setTrainId("rajdhani_kolkata");
        kolkataRajdhani.setTrainNo("12301");
        kolkataRajdhani.setSeats(createSeatLayout(4, 6));
        kolkataRajdhani.setStations(Arrays.asList("kolkata", "dhanbad", "gaya", "patna", "mughalsarai", "delhi"));
        kolkataRajdhani.getStationTimes().put("kolkata", "16:55:00");
        kolkataRajdhani.getStationTimes().put("dhanbad", "20:30:00");
        kolkataRajdhani.getStationTimes().put("gaya", "22:15:00");
        kolkataRajdhani.getStationTimes().put("patna", "00:40:00");
        kolkataRajdhani.getStationTimes().put("mughalsarai", "03:15:00");
        kolkataRajdhani.getStationTimes().put("delhi", "10:00:00");
        trains.add(kolkataRajdhani);

        // Chennai to Delhi Rajdhani Express
        Train chennaiRajdhani = new Train();
        chennaiRajdhani.setTrainId("rajdhani_chennai");
        chennaiRajdhani.setTrainNo("12433");
        chennaiRajdhani.setSeats(createSeatLayout(4, 6));
        chennaiRajdhani.setStations(Arrays.asList("chennai", "vijayawada", "nagpur", "bhopal", "jhansi", "delhi"));
        chennaiRajdhani.getStationTimes().put("chennai", "06:10:00");
        chennaiRajdhani.getStationTimes().put("vijayawada", "12:30:00");
        chennaiRajdhani.getStationTimes().put("nagpur", "20:45:00");
        chennaiRajdhani.getStationTimes().put("bhopal", "02:15:00");
        chennaiRajdhani.getStationTimes().put("jhansi", "05:30:00");
        chennaiRajdhani.getStationTimes().put("delhi", "10:55:00");
        trains.add(chennaiRajdhani);

        // Bengaluru to Delhi Rajdhani Express
        Train bengaluruRajdhani = new Train();
        bengaluruRajdhani.setTrainId("rajdhani_bengaluru");
        bengaluruRajdhani.setTrainNo("22691");
        bengaluruRajdhani.setSeats(createSeatLayout(4, 6));
        bengaluruRajdhani.setStations(Arrays.asList("bengaluru", "secunderabad", "nagpur", "bhopal", "jhansi", "delhi"));
        bengaluruRajdhani.getStationTimes().put("bengaluru", "20:00:00");
        bengaluruRajdhani.getStationTimes().put("secunderabad", "05:30:00");
        bengaluruRajdhani.getStationTimes().put("nagpur", "12:15:00");
        bengaluruRajdhani.getStationTimes().put("bhopal", "17:45:00");
        bengaluruRajdhani.getStationTimes().put("jhansi", "21:30:00");
        bengaluruRajdhani.getStationTimes().put("delhi", "05:55:00");
        trains.add(bengaluruRajdhani);

        return trains;
    }

    /**
     * Creates Shatabdi Express trains connecting nearby major cities
     */
    private static List<Train> createShatabdiExpressTrains() {
        List<Train> trains = new ArrayList<>();

        // Delhi to Jaipur Shatabdi Express
        Train delhiJaipurShatabdi = new Train();
        delhiJaipurShatabdi.setTrainId("shatabdi_delhi_jaipur");
        delhiJaipurShatabdi.setTrainNo("12015");
        delhiJaipurShatabdi.setSeats(createSeatLayout(4, 6));
        delhiJaipurShatabdi.setStations(Arrays.asList("delhi", "gurgaon", "alwar", "jaipur"));
        delhiJaipurShatabdi.getStationTimes().put("delhi", "06:05:00");
        delhiJaipurShatabdi.getStationTimes().put("gurgaon", "06:40:00");
        delhiJaipurShatabdi.getStationTimes().put("alwar", "08:15:00");
        delhiJaipurShatabdi.getStationTimes().put("jaipur", "10:05:00");
        trains.add(delhiJaipurShatabdi);

        // Delhi to Lucknow Shatabdi Express
        Train delhiLucknowShatabdi = new Train();
        delhiLucknowShatabdi.setTrainId("shatabdi_delhi_lucknow");
        delhiLucknowShatabdi.setTrainNo("12003");
        delhiLucknowShatabdi.setSeats(createSeatLayout(4, 6));
        delhiLucknowShatabdi.setStations(Arrays.asList("delhi", "ghaziabad", "aligarh", "kanpur", "lucknow"));
        delhiLucknowShatabdi.getStationTimes().put("delhi", "06:10:00");
        delhiLucknowShatabdi.getStationTimes().put("ghaziabad", "06:40:00");
        delhiLucknowShatabdi.getStationTimes().put("aligarh", "08:05:00");
        delhiLucknowShatabdi.getStationTimes().put("kanpur", "10:45:00");
        delhiLucknowShatabdi.getStationTimes().put("lucknow", "12:40:00");
        trains.add(delhiLucknowShatabdi);

        // Mumbai to Ahmedabad Shatabdi Express
        Train mumbaiAhmedabadShatabdi = new Train();
        mumbaiAhmedabadShatabdi.setTrainId("shatabdi_mumbai_ahmedabad");
        mumbaiAhmedabadShatabdi.setTrainNo("12009");
        mumbaiAhmedabadShatabdi.setSeats(createSeatLayout(4, 6));
        mumbaiAhmedabadShatabdi.setStations(Arrays.asList("mumbai", "borivali", "surat", "vadodara", "ahmedabad"));
        mumbaiAhmedabadShatabdi.getStationTimes().put("mumbai", "06:25:00");
        mumbaiAhmedabadShatabdi.getStationTimes().put("borivali", "07:00:00");
        mumbaiAhmedabadShatabdi.getStationTimes().put("surat", "08:55:00");
        mumbaiAhmedabadShatabdi.getStationTimes().put("vadodara", "10:35:00");
        mumbaiAhmedabadShatabdi.getStationTimes().put("ahmedabad", "12:25:00");
        trains.add(mumbaiAhmedabadShatabdi);

        // Chennai to Bengaluru Shatabdi Express
        Train chennaiBengaluruShatabdi = new Train();
        chennaiBengaluruShatabdi.setTrainId("shatabdi_chennai_bengaluru");
        chennaiBengaluruShatabdi.setTrainNo("12027");
        chennaiBengaluruShatabdi.setSeats(createSeatLayout(4, 6));
        chennaiBengaluruShatabdi.setStations(Arrays.asList("chennai", "katpadi", "bengaluru"));
        chennaiBengaluruShatabdi.getStationTimes().put("chennai", "06:00:00");
        chennaiBengaluruShatabdi.getStationTimes().put("katpadi", "08:10:00");
        chennaiBengaluruShatabdi.getStationTimes().put("bengaluru", "11:00:00");
        trains.add(chennaiBengaluruShatabdi);

        return trains;
    }

    /**
     * Creates Duronto Express trains connecting distant cities
     */
    private static List<Train> createDurontoExpressTrains() {
        List<Train> trains = new ArrayList<>();

        // Mumbai to Howrah (Kolkata) Duronto Express
        Train mumbaiKolkataDuronto = new Train();
        mumbaiKolkataDuronto.setTrainId("duronto_mumbai_kolkata");
        mumbaiKolkataDuronto.setTrainNo("12261");
        mumbaiKolkataDuronto.setSeats(createSeatLayout(4, 6));
        mumbaiKolkataDuronto.setStations(Arrays.asList("mumbai", "nagpur", "raipur", "kolkata"));
        mumbaiKolkataDuronto.getStationTimes().put("mumbai", "11:05:00");
        mumbaiKolkataDuronto.getStationTimes().put("nagpur", "22:30:00");
        mumbaiKolkataDuronto.getStationTimes().put("raipur", "04:15:00");
        mumbaiKolkataDuronto.getStationTimes().put("kolkata", "16:05:00");
        trains.add(mumbaiKolkataDuronto);

        // Chennai to Coimbatore Duronto Express
        Train chennaiCoimbatoreDuronto = new Train();
        chennaiCoimbatoreDuronto.setTrainId("duronto_chennai_coimbatore");
        chennaiCoimbatoreDuronto.setTrainNo("12243");
        chennaiCoimbatoreDuronto.setSeats(createSeatLayout(4, 6));
        chennaiCoimbatoreDuronto.setStations(Arrays.asList("chennai", "salem", "erode", "coimbatore"));
        chennaiCoimbatoreDuronto.getStationTimes().put("chennai", "22:15:00");
        chennaiCoimbatoreDuronto.getStationTimes().put("salem", "02:30:00");
        chennaiCoimbatoreDuronto.getStationTimes().put("erode", "03:45:00");
        chennaiCoimbatoreDuronto.getStationTimes().put("coimbatore", "05:30:00");
        trains.add(chennaiCoimbatoreDuronto);

        // Delhi to Jammu Duronto Express
        Train delhiJammuDuronto = new Train();
        delhiJammuDuronto.setTrainId("duronto_delhi_jammu");
        delhiJammuDuronto.setTrainNo("12265");
        delhiJammuDuronto.setSeats(createSeatLayout(4, 6));
        delhiJammuDuronto.setStations(Arrays.asList("delhi", "ambala", "ludhiana", "jalandhar", "jammu"));
        delhiJammuDuronto.getStationTimes().put("delhi", "22:00:00");
        delhiJammuDuronto.getStationTimes().put("ambala", "00:30:00");
        delhiJammuDuronto.getStationTimes().put("ludhiana", "02:15:00");
        delhiJammuDuronto.getStationTimes().put("jalandhar", "03:30:00");
        delhiJammuDuronto.getStationTimes().put("jammu", "07:00:00");
        trains.add(delhiJammuDuronto);

        // Bengaluru to Chennai Duronto Express
        Train bengaluruChennaiDuronto = new Train();
        bengaluruChennaiDuronto.setTrainId("duronto_bengaluru_chennai");
        bengaluruChennaiDuronto.setTrainNo("12213");
        bengaluruChennaiDuronto.setSeats(createSeatLayout(4, 6));
        bengaluruChennaiDuronto.setStations(Arrays.asList("bengaluru", "katpadi", "chennai"));
        bengaluruChennaiDuronto.getStationTimes().put("bengaluru", "22:15:00");
        bengaluruChennaiDuronto.getStationTimes().put("katpadi", "01:30:00");
        bengaluruChennaiDuronto.getStationTimes().put("chennai", "04:30:00");
        trains.add(bengaluruChennaiDuronto);

        return trains;
    }

    /**
     * Creates Vande Bharat Express trains connecting major cities
     */
    private static List<Train> createVandeBharatTrains() {
        List<Train> trains = new ArrayList<>();

        // Delhi to Varanasi Vande Bharat Express
        Train delhiVaranasiVandeBharat = new Train();
        delhiVaranasiVandeBharat.setTrainId("vande_bharat_delhi_varanasi");
        delhiVaranasiVandeBharat.setTrainNo("22435");
        delhiVaranasiVandeBharat.setSeats(createSeatLayout(4, 6));
        delhiVaranasiVandeBharat.setStations(Arrays.asList("delhi", "kanpur", "prayagraj", "varanasi"));
        delhiVaranasiVandeBharat.getStationTimes().put("delhi", "06:00:00");
        delhiVaranasiVandeBharat.getStationTimes().put("kanpur", "10:30:00");
        delhiVaranasiVandeBharat.getStationTimes().put("prayagraj", "12:45:00");
        delhiVaranasiVandeBharat.getStationTimes().put("varanasi", "14:00:00");
        trains.add(delhiVaranasiVandeBharat);

        // Mumbai to Ahmedabad Vande Bharat Express
        Train mumbaiAhmedabadVandeBharat = new Train();
        mumbaiAhmedabadVandeBharat.setTrainId("vande_bharat_mumbai_ahmedabad");
        mumbaiAhmedabadVandeBharat.setTrainNo("20901");
        mumbaiAhmedabadVandeBharat.setSeats(createSeatLayout(4, 6));
        mumbaiAhmedabadVandeBharat.setStations(Arrays.asList("mumbai", "surat", "vadodara", "ahmedabad"));
        mumbaiAhmedabadVandeBharat.getStationTimes().put("mumbai", "06:00:00");
        mumbaiAhmedabadVandeBharat.getStationTimes().put("surat", "07:55:00");
        mumbaiAhmedabadVandeBharat.getStationTimes().put("vadodara", "09:30:00");
        mumbaiAhmedabadVandeBharat.getStationTimes().put("ahmedabad", "11:25:00");
        trains.add(mumbaiAhmedabadVandeBharat);

        // Chennai to Mysuru Vande Bharat Express
        Train chennaiMysuruVandeBharat = new Train();
        chennaiMysuruVandeBharat.setTrainId("vande_bharat_chennai_mysuru");
        chennaiMysuruVandeBharat.setTrainNo("20607");
        chennaiMysuruVandeBharat.setSeats(createSeatLayout(4, 6));
        chennaiMysuruVandeBharat.setStations(Arrays.asList("chennai", "bengaluru", "mysuru"));
        chennaiMysuruVandeBharat.getStationTimes().put("chennai", "05:50:00");
        chennaiMysuruVandeBharat.getStationTimes().put("bengaluru", "10:20:00");
        chennaiMysuruVandeBharat.getStationTimes().put("mysuru", "12:30:00");
        trains.add(chennaiMysuruVandeBharat);

        // Delhi to Bhopal Vande Bharat Express
        Train delhiBhopalVandeBharat = new Train();
        delhiBhopalVandeBharat.setTrainId("vande_bharat_delhi_bhopal");
        delhiBhopalVandeBharat.setTrainNo("20173");
        delhiBhopalVandeBharat.setSeats(createSeatLayout(4, 6));
        delhiBhopalVandeBharat.setStations(Arrays.asList("delhi", "agra", "gwalior", "jhansi", "bhopal"));
        delhiBhopalVandeBharat.getStationTimes().put("delhi", "06:00:00");
        delhiBhopalVandeBharat.getStationTimes().put("agra", "08:15:00");
        delhiBhopalVandeBharat.getStationTimes().put("gwalior", "09:45:00");
        delhiBhopalVandeBharat.getStationTimes().put("jhansi", "10:45:00");
        delhiBhopalVandeBharat.getStationTimes().put("bhopal", "13:30:00");
        trains.add(delhiBhopalVandeBharat);

        return trains;
    }

    /**
     * Creates Super Fast Express trains connecting various cities
     */
    private static List<Train> createSuperFastTrains() {
        List<Train> trains = new ArrayList<>();

        // Mumbai to Goa Superfast Express
        Train mumbaiGoaSuperfast = new Train();
        mumbaiGoaSuperfast.setTrainId("superfast_mumbai_goa");
        mumbaiGoaSuperfast.setTrainNo("12051");
        mumbaiGoaSuperfast.setSeats(createSeatLayout(4, 6));
        mumbaiGoaSuperfast.setStations(Arrays.asList("mumbai", "ratnagiri", "madgaon", "vasco_da_gama"));
        mumbaiGoaSuperfast.getStationTimes().put("mumbai", "09:10:00");
        mumbaiGoaSuperfast.getStationTimes().put("ratnagiri", "14:30:00");
        mumbaiGoaSuperfast.getStationTimes().put("madgaon", "19:45:00");
        mumbaiGoaSuperfast.getStationTimes().put("vasco_da_gama", "20:30:00");
        trains.add(mumbaiGoaSuperfast);

        // Delhi to Amritsar Superfast Express
        Train delhiAmritsarSuperfast = new Train();
        delhiAmritsarSuperfast.setTrainId("superfast_delhi_amritsar");
        delhiAmritsarSuperfast.setTrainNo("12459");
        delhiAmritsarSuperfast.setSeats(createSeatLayout(4, 6));
        delhiAmritsarSuperfast.setStations(Arrays.asList("delhi", "ambala", "ludhiana", "jalandhar", "amritsar"));
        delhiAmritsarSuperfast.getStationTimes().put("delhi", "07:20:00");
        delhiAmritsarSuperfast.getStationTimes().put("ambala", "10:30:00");
        delhiAmritsarSuperfast.getStationTimes().put("ludhiana", "12:15:00");
        delhiAmritsarSuperfast.getStationTimes().put("jalandhar", "13:30:00");
        delhiAmritsarSuperfast.getStationTimes().put("amritsar", "14:45:00");
        trains.add(delhiAmritsarSuperfast);

        // Chennai to Hyderabad Superfast Express
        Train chennaiHyderabadSuperfast = new Train();
        chennaiHyderabadSuperfast.setTrainId("superfast_chennai_hyderabad");
        chennaiHyderabadSuperfast.setTrainNo("12603");
        chennaiHyderabadSuperfast.setSeats(createSeatLayout(4, 6));
        chennaiHyderabadSuperfast.setStations(Arrays.asList("chennai", "nellore", "ongole", "vijayawada", "hyderabad"));
        chennaiHyderabadSuperfast.getStationTimes().put("chennai", "17:45:00");
        chennaiHyderabadSuperfast.getStationTimes().put("nellore", "20:30:00");
        chennaiHyderabadSuperfast.getStationTimes().put("ongole", "22:15:00");
        chennaiHyderabadSuperfast.getStationTimes().put("vijayawada", "00:45:00");
        chennaiHyderabadSuperfast.getStationTimes().put("hyderabad", "06:15:00");
        trains.add(chennaiHyderabadSuperfast);

        // Kolkata to Puri Superfast Express
        Train kolkataPuriSuperfast = new Train();
        kolkataPuriSuperfast.setTrainId("superfast_kolkata_puri");
        kolkataPuriSuperfast.setTrainNo("12837");
        kolkataPuriSuperfast.setSeats(createSeatLayout(4, 6));
        kolkataPuriSuperfast.setStations(Arrays.asList("kolkata", "kharagpur", "balasore", "bhubaneswar", "puri"));
        kolkataPuriSuperfast.getStationTimes().put("kolkata", "08:30:00");
        kolkataPuriSuperfast.getStationTimes().put("kharagpur", "10:15:00");
        kolkataPuriSuperfast.getStationTimes().put("balasore", "12:00:00");
        kolkataPuriSuperfast.getStationTimes().put("bhubaneswar", "14:30:00");
        kolkataPuriSuperfast.getStationTimes().put("puri", "16:00:00");
        trains.add(kolkataPuriSuperfast);

        return trains;
    }

    /**
     * Creates Passenger trains connecting nearby cities and towns
     */
    private static List<Train> createPassengerTrains() {
        List<Train> trains = new ArrayList<>();

        // Delhi to Chandigarh Passenger
        Train delhiChandigarhPassenger = new Train();
        delhiChandigarhPassenger.setTrainId("passenger_delhi_chandigarh");
        delhiChandigarhPassenger.setTrainNo("54011");
        delhiChandigarhPassenger.setSeats(createSeatLayout(4, 6));
        delhiChandigarhPassenger.setStations(Arrays.asList("delhi", "panipat", "karnal", "ambala", "chandigarh"));
        delhiChandigarhPassenger.getStationTimes().put("delhi", "05:55:00");
        delhiChandigarhPassenger.getStationTimes().put("panipat", "07:30:00");
        delhiChandigarhPassenger.getStationTimes().put("karnal", "08:45:00");
        delhiChandigarhPassenger.getStationTimes().put("ambala", "10:30:00");
        delhiChandigarhPassenger.getStationTimes().put("chandigarh", "12:00:00");
        trains.add(delhiChandigarhPassenger);

        // Mumbai to Pune Passenger
        Train mumbaiPunePassenger = new Train();
        mumbaiPunePassenger.setTrainId("passenger_mumbai_pune");
        mumbaiPunePassenger.setTrainNo("51301");
        mumbaiPunePassenger.setSeats(createSeatLayout(4, 6));
        mumbaiPunePassenger.setStations(Arrays.asList("mumbai", "karjat", "lonavala", "pune"));
        mumbaiPunePassenger.getStationTimes().put("mumbai", "07:15:00");
        mumbaiPunePassenger.getStationTimes().put("karjat", "08:45:00");
        mumbaiPunePassenger.getStationTimes().put("lonavala", "09:30:00");
        mumbaiPunePassenger.getStationTimes().put("pune", "11:15:00");
        trains.add(mumbaiPunePassenger);

        // Chennai to Pondicherry Passenger
        Train chennaiPondicherryPassenger = new Train();
        chennaiPondicherryPassenger.setTrainId("passenger_chennai_pondicherry");
        chennaiPondicherryPassenger.setTrainNo("56037");
        chennaiPondicherryPassenger.setSeats(createSeatLayout(4, 6));
        chennaiPondicherryPassenger.setStations(Arrays.asList("chennai", "chengalpattu", "villupuram", "pondicherry"));
        chennaiPondicherryPassenger.getStationTimes().put("chennai", "06:05:00");
        chennaiPondicherryPassenger.getStationTimes().put("chengalpattu", "07:30:00");
        chennaiPondicherryPassenger.getStationTimes().put("villupuram", "09:45:00");
        chennaiPondicherryPassenger.getStationTimes().put("pondicherry", "11:00:00");
        trains.add(chennaiPondicherryPassenger);

        return trains;
    }

    /**
     * Creates a seat layout with random availability
     */
    private static List<List<Integer>> createSeatLayout(int rows, int cols) {
        List<List<Integer>> seats = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                // Randomly set some seats as booked (1) and some as available (0)
                row.add(Math.random() > 0.7 ? 1 : 0);
            }
            seats.add(row);
        }
        return seats;
    }
}