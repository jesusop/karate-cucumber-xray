package util;


import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Logger;


public class ReportCucumber {
    private static final Logger logger = Logger.getLogger(ReportCucumber.class.getName());

    private static final String JSON_EXTENSION = "json";
    private static final String TARGET_DIRECTORY = "target";
    private static final String JSON_DIRECTORY = "/cucumber-html-reports";
    private static final String CUCUMBER_JSON = "/cucumber.json";

    public static void generateCucumberReport(String karateOutputPath) {
        try {
            List<String> jsonPaths = getJsonPaths(karateOutputPath);
            Configuration config = new Configuration(new File(TARGET_DIRECTORY), "Cucumber Reports");

            JSONArray totalResults = getTotalResults(jsonPaths);
            writeResultsToOneFile(totalResults);

            ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
            reportBuilder.generateReports();
        } catch (IOException | ParseException e) {
            logger.severe("Error generating Cucumber report");
        }
    }

    private static List<String> getJsonPaths(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[]{JSON_EXTENSION}, true);
        return jsonFiles.stream().map(File::getAbsolutePath).collect(Collectors.toList());
    }

    //codigo de mas
    private static JSONArray getTotalResults(List<String> jsonPaths) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray totalResults = new JSONArray();
        for (String jsonPath : jsonPaths) {
            JSONArray results = (JSONArray) parser.parse(new FileReader(jsonPath));
            totalResults.addAll(results);
        }
        return totalResults;
    }

    private static void writeResultsToOneFile(JSONArray totalResults) throws IOException {
        File currentDirectory = new File((new File(".")).getAbsolutePath());
        String absolutePath = currentDirectory.getCanonicalPath();

        File directory = new File(absolutePath + "/" + TARGET_DIRECTORY + JSON_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File cucumberJsonFile = new File(directory + CUCUMBER_JSON);
        try (FileWriter fileWriter = new FileWriter(cucumberJsonFile)) {
            fileWriter.write(totalResults.toJSONString());

        }

        logger.info("cucumber.json file has been created at: " + cucumberJsonFile.getAbsolutePath());
    }

}