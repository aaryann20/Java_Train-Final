package ticket.booking.util;

import java.util.*;

/**
 * Database of major Indian cities and their information
 */
public class CityDatabase {
    private static final Map<String, CityInfo> cities = new HashMap<>();

    static {
        initializeCities();
    }

    /**
     * Initialize the database with major Indian cities
     */
    private static void initializeCities() {
        // Metro cities
        addCity("delhi", "Delhi", "National Capital Territory", 28.7041, 77.1025, "North India");
        addCity("mumbai", "Mumbai", "Maharashtra", 19.0760, 72.8777, "West India");
        addCity("kolkata", "Kolkata", "West Bengal", 22.5726, 88.3639, "East India");
        addCity("chennai", "Chennai", "Tamil Nadu", 13.0827, 80.2707, "South India");
        addCity("bengaluru", "Bengaluru", "Karnataka", 12.9716, 77.5946, "South India");
        addCity("hyderabad", "Hyderabad", "Telangana", 17.3850, 78.4867, "South India");

        // Major cities - North India
        addCity("jaipur", "Jaipur", "Rajasthan", 26.9124, 75.7873, "North India");
        addCity("lucknow", "Lucknow", "Uttar Pradesh", 26.8467, 80.9462, "North India");
        addCity("chandigarh", "Chandigarh", "Chandigarh", 30.7333, 76.7794, "North India");
        addCity("amritsar", "Amritsar", "Punjab", 31.6340, 74.8723, "North India");
        addCity("varanasi", "Varanasi", "Uttar Pradesh", 25.3176, 82.9739, "North India");
        addCity("agra", "Agra", "Uttar Pradesh", 27.1767, 78.0081, "North India");
        addCity("shimla", "Shimla", "Himachal Pradesh", 31.1048, 77.1734, "North India");
        addCity("dehradun", "Dehradun", "Uttarakhand", 30.3165, 78.0322, "North India");
        addCity("jammu", "Jammu", "Jammu and Kashmir", 32.7266, 74.8570, "North India");
        addCity("srinagar", "Srinagar", "Jammu and Kashmir", 34.0837, 74.7973, "North India");
        addCity("gurgaon", "Gurgaon", "Haryana", 28.4595, 77.0266, "North India");
        addCity("kanpur", "Kanpur", "Uttar Pradesh", 26.4499, 80.3319, "North India");
        addCity("prayagraj", "Prayagraj", "Uttar Pradesh", 25.4358, 81.8463, "North India");
        addCity("jhansi", "Jhansi", "Uttar Pradesh", 25.4484, 78.5685, "North India");
        addCity("gwalior", "Gwalior", "Madhya Pradesh", 26.2183, 78.1828, "North India");

        // Major cities - West India
        addCity("ahmedabad", "Ahmedabad", "Gujarat", 23.0225, 72.5714, "West India");
        addCity("surat", "Surat", "Gujarat", 21.1702, 72.8311, "West India");
        addCity("vadodara", "Vadodara", "Gujarat", 22.3072, 73.1812, "West India");
        addCity("pune", "Pune", "Maharashtra", 18.5204, 73.8567, "West India");
        addCity("nagpur", "Nagpur", "Maharashtra", 21.1458, 79.0882, "West India");
        addCity("indore", "Indore", "Madhya Pradesh", 22.7196, 75.8577, "West India");
        addCity("bhopal", "Bhopal", "Madhya Pradesh", 23.2599, 77.4126, "West India");
        addCity("rajkot", "Rajkot", "Gujarat", 22.3039, 70.8022, "West India");
        addCity("kota", "Kota", "Rajasthan", 25.2138, 75.8648, "West India");
        addCity("vasco_da_gama", "Vasco da Gama", "Goa", 15.3961, 73.8120, "West India");
        addCity("madgaon", "Madgaon", "Goa", 15.2993, 73.9557, "West India");
        addCity("ratnagiri", "Ratnagiri", "Maharashtra", 16.9902, 73.3120, "West India");
        addCity("borivali", "Borivali", "Maharashtra", 19.2307, 72.8567, "West India");

        // Major cities - East India
        addCity("patna", "Patna", "Bihar", 25.5941, 85.1376, "East India");
        addCity("guwahati", "Guwahati", "Assam", 26.1445, 91.7362, "East India");
        addCity("bhubaneswar", "Bhubaneswar", "Odisha", 20.2961, 85.8245, "East India");
        addCity("ranchi", "Ranchi", "Jharkhand", 23.3441, 85.3096, "East India");
        addCity("dhanbad", "Dhanbad", "Jharkhand", 23.7957, 86.4304, "East India");
        addCity("gaya", "Gaya", "Bihar", 24.7914, 84.9994, "East India");
        addCity("mughalsarai", "Mughalsarai", "Uttar Pradesh", 25.2809, 83.1309, "East India");
        addCity("puri", "Puri", "Odisha", 19.8133, 85.8314, "East India");
        addCity("kharagpur", "Kharagpur", "West Bengal", 22.3460, 87.2320, "East India");
        addCity("balasore", "Balasore", "Odisha", 21.4934, 86.9335, "East India");

        // Major cities - South India
        addCity("kochi", "Kochi", "Kerala", 9.9312, 76.2673, "South India");
        addCity("thiruvananthapuram", "Thiruvananthapuram", "Kerala", 8.5241, 76.9366, "South India");
        addCity("coimbatore", "Coimbatore", "Tamil Nadu", 11.0168, 76.9558, "South India");
        addCity("mysuru", "Mysuru", "Karnataka", 12.2958, 76.6394, "South India");
        addCity("vijayawada", "Vijayawada", "Andhra Pradesh", 16.5062, 80.6480, "South India");
        addCity("visakhapatnam", "Visakhapatnam", "Andhra Pradesh", 17.6868, 83.2185, "South India");
        addCity("madurai", "Madurai", "Tamil Nadu", 9.9252, 78.1198, "South India");
        addCity("secunderabad", "Secunderabad", "Telangana", 17.4399, 78.4983, "South India");
        addCity("pondicherry", "Pondicherry", "Puducherry", 11.9416, 79.8083, "South India");
        addCity("erode", "Erode", "Tamil Nadu", 11.3410, 77.7172, "South India");
        addCity("salem", "Salem", "Tamil Nadu", 11.6643, 78.1460, "South India");
        addCity("katpadi", "Katpadi", "Tamil Nadu", 12.9698, 79.1378, "South India");
        addCity("nellore", "Nellore", "Andhra Pradesh", 14.4426, 79.9865, "South India");
        addCity("ongole", "Ongole", "Andhra Pradesh", 15.5057, 80.0499, "South India");
        addCity("chengalpattu", "Chengalpattu", "Tamil Nadu", 12.6819, 79.9888, "South India");
        addCity("villupuram", "Villupuram", "Tamil Nadu", 11.9401, 79.4861, "South India");
    }

    /**
     * Add a city to the database
     */
    private static void addCity(String id, String name, String state, double latitude, double longitude, String region) {
        cities.put(id, new CityInfo(id, name, state, latitude, longitude, region));
    }

    /**
     * Get all cities in the database
     */
    public static List<CityInfo> getAllCities() {
        return new ArrayList<>(cities.values());
    }

    /**
     * Get cities by region
     */
    public static List<CityInfo> getCitiesByRegion(String region) {
        List<CityInfo> regionCities = new ArrayList<>();
        for (CityInfo city : cities.values()) {
            if (city.getRegion().equalsIgnoreCase(region)) {
                regionCities.add(city);
            }
        }
        return regionCities;
    }

    /**
     * Get a city by its ID
     */
    public static CityInfo getCityById(String id) {
        return cities.get(id.toLowerCase());
    }

    /**
     * Get a city by its name
     */
    public static CityInfo getCityByName(String name) {
        for (CityInfo city : cities.values()) {
            if (city.getName().equalsIgnoreCase(name)) {
                return city;
            }
        }
        return null;
    }

    /**
     * Search cities by name (partial match)
     */
    public static List<CityInfo> searchCities(String query) {
        List<CityInfo> results = new ArrayList<>();
        String lowerQuery = query.toLowerCase();

        for (CityInfo city : cities.values()) {
            if (city.getName().toLowerCase().contains(lowerQuery) ||
                    city.getState().toLowerCase().contains(lowerQuery)) {
                results.add(city);
            }
        }

        return results;
    }

    /**
     * Get all city names for display
     */
    public static String[] getCityNames() {
        List<String> names = new ArrayList<>();
        for (CityInfo city : cities.values()) {
            names.add(city.getName());
        }
        Collections.sort(names);
        return names.toArray(new String[0]);
    }

    /**
     * City information class
     */
    public static class CityInfo {
        private final String id;
        private final String name;
        private final String state;
        private final double latitude;
        private final double longitude;
        private final String region;

        public CityInfo(String id, String name, String state, double latitude, double longitude, String region) {
            this.id = id;
            this.name = name;
            this.state = state;
            this.latitude = latitude;
            this.longitude = longitude;
            this.region = region;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getState() {
            return state;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public String getRegion() {
            return region;
        }

        @Override
        public String toString() {
            return name + ", " + state;
        }
    }
}