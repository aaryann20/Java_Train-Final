package ticket.booking.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Service class for train-related operations
 */
public class TrainService {

    private List<Train> trainList;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String TRAIN_DB_PATH = "app/src/main/java/ticket/booking/localDb/trains.json";

    /**
     * Constructor - loads trains from file
     */
    public TrainService() throws IOException {
        File trainsFile = new File(TRAIN_DB_PATH);

        // Create directory and file if they don't exist
        if (!trainsFile.exists()) {
            File dir = new File(trainsFile.getParent());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            trainsFile.createNewFile();

            // Initialize with empty array
            objectMapper.writeValue(trainsFile, new ArrayList<Train>());
        }

        trainList = objectMapper.readValue(trainsFile, new TypeReference<List<Train>>() {});

        // If train list is empty, add sample trains
        if (trainList.isEmpty()) {
            initializeWithSampleTrains();
        }
    }

    /**
     * Search for trains between source and destination
     */
    public List<Train> searchTrains(String source, String destination) {
        return trainList.stream()
                .filter(train -> validTrain(train, source, destination))
                .collect(Collectors.toList());
    }

    /**
     * Add a new train or update if it already exists
     */
    public void addTrain(Train newTrain) {
        Optional<Train> existingTrain = trainList.stream()
                .filter(train -> train.getTrainId().equalsIgnoreCase(newTrain.getTrainId()))
                .findFirst();

        if (existingTrain.isPresent()) {
            updateTrain(newTrain);
        } else {
            trainList.add(newTrain);
            saveTrainListToFile();
        }
    }

    /**
     * Update an existing train
     */
    public void updateTrain(Train updatedTrain) {
        OptionalInt index = IntStream.range(0, trainList.size())
                .filter(i -> trainList.get(i).getTrainId().equalsIgnoreCase(updatedTrain.getTrainId()))
                .findFirst();

        if (index.isPresent()) {
            trainList.set(index.getAsInt(), updatedTrain);
            saveTrainListToFile();
        } else {
            addTrain(updatedTrain);
        }
    }

    /**
     * Save the train list to file
     */
    private void saveTrainListToFile() {
        try {
            objectMapper.writeValue(new File(TRAIN_DB_PATH), trainList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if a train is valid for the given route
     */
    private boolean validTrain(Train train, String source, String destination) {
        if (train.getStations() == null) {
            return false;
        }

        List<String> stationOrder = train.getStations();

        // Convert to lowercase for case-insensitive comparison
        String sourceLower = source.toLowerCase();
        String destinationLower = destination.toLowerCase();

        int sourceIndex = stationOrder.indexOf(sourceLower);
        int destinationIndex = stationOrder.indexOf(destinationLower);

        return sourceIndex != -1 && destinationIndex != -1 && sourceIndex < destinationIndex;
    }

    /**
     * Initialize with sample trains if the list is empty
     */
    private void initializeWithSampleTrains() {
        // Create sample trains
        List<String> stations1 = new ArrayList<>();
        stations1.add("delhi");
        stations1.add("jaipur");
        stations1.add("ahmedabad");
        stations1.add("mumbai");

        List<List<Integer>> seats1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                row.add(0);
            }
            seats1.add(row);
        }

        Map<String, String> stationTimes1 = new HashMap<>();
        stationTimes1.put("delhi", "08:00:00");
        stationTimes1.put("jaipur", "11:30:00");
        stationTimes1.put("ahmedabad", "16:45:00");
        stationTimes1.put("mumbai", "21:00:00");

        Train train1 = new Train("T001", "12301", seats1, stationTimes1, stations1);

        // Create another sample train
        List<String> stations2 = new ArrayList<>();
        stations2.add("bangalore");
        stations2.add("hyderabad");
        stations2.add("nagpur");
        stations2.add("delhi");

        List<List<Integer>> seats2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                row.add(0);
            }
            seats2.add(row);
        }

        Map<String, String> stationTimes2 = new HashMap<>();
        stationTimes2.put("bangalore", "07:00:00");
        stationTimes2.put("hyderabad", "12:30:00");
        stationTimes2.put("nagpur", "18:45:00");
        stationTimes2.put("delhi", "09:00:00");

        Train train2 = new Train("T002", "12302", seats2, stationTimes2, stations2);

        trainList.add(train1);
        trainList.add(train2);

        saveTrainListToFile();
    }
}