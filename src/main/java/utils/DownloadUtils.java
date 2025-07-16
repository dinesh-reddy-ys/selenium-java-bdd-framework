package utils;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

/**
 * Utility class for managing file downloads.
 * Provides methods to handle download folder operations, file monitoring, and file manipulation.
 */
public class DownloadUtils {

    private final String downloadDir; // Directory where files are downloaded
    private final boolean debugMode = true; // Flag to enable/disable debug logging

    /**
     * Constructor to initialize the download directory.
     * Ensures the download folder exists.
     *
     * @param downloadDir Path to the download directory
     */
    public DownloadUtils(String downloadDir) {
        this.downloadDir = downloadDir;
        createDownloadFolderIfNotExists();
    }

    /**
     * Logs debug messages if debug mode is enabled.
     *
     * @param msg Message to log
     */
    private void log(String msg) {
        if (debugMode) {
            System.out.println("[DownloadUtils] " + msg);
        }
    }

    /**
     * Creates the download folder if it does not exist.
     */
    private void createDownloadFolderIfNotExists() {
        File folder = new File(downloadDir);
        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            log("Download folder created: " + created);
        }
    }

    /**
     * Clears all files in the download folder.
     */
    public void clearDownloadFolder() {
        File folder = new File(downloadDir);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.delete()) {
                    log("Failed to delete: " + file.getAbsolutePath());
                }
            }
        }
    }

    /**
     * Waits for a specific file to be downloaded within a timeout period.
     *
     * @param fileName Name of the file to wait for
     * @param timeoutInSeconds Maximum time to wait in seconds
     * @return true if the file is downloaded successfully, false otherwise
     */
    public boolean waitForFileToDownload(String fileName, int timeoutInSeconds) {
        Path filePath = Paths.get(downloadDir, fileName);
        File file = filePath.toFile();
        int waited = 0;

        log("Waiting for file to download: " + fileName);

        while (waited < timeoutInSeconds) {
            if (file.exists() && !file.getName().endsWith(".crdownload")) {
                log("File downloaded: " + file.getAbsolutePath());
                return true;
            }

            try {
                Thread.sleep(1000); // Wait for 1 second before checking again
            } catch (InterruptedException e) {
                throw new RuntimeException("Interrupted while waiting for file download", e);
            }
            waited++;
        }

        log("Timeout reached. File not found or still downloading.");
        return false;
    }

    /**
     * Retrieves the most recently downloaded file from the download folder.
     *
     * @return The latest downloaded file, or null if no files are found
     */
    public File getLatestDownloadedFile() {
        File folder = new File(downloadDir);
        File[] files = folder.listFiles();

        if (files == null || files.length == 0) {
            log("No files found in download folder.");
            return null;
        }

        File latestFile = files[0];
        for (int i = 1; i < files.length; i++) {
            if (files[i].lastModified() > latestFile.lastModified()) {
                latestFile = files[i];
            }
        }

        log("Latest downloaded file: " + latestFile.getName());
        return latestFile;
    }

    /**
     * Reads the content of a downloaded file.
     *
     * @param fileName Name of the file to read
     * @return Content of the file as a String
     * @throws Exception If an error occurs while reading the file
     */
    public String readDownloadedFileContent(String fileName) throws Exception {
        Path filePath = Paths.get(downloadDir, fileName);
        return new String(Files.readAllBytes(filePath));
    }

    /**
     * Deletes a specific file from the download folder.
     *
     * @param fileName Name of the file to delete
     * @return true if the file is deleted successfully, false otherwise
     */
    public boolean deleteDownloadedFile(String fileName) {
        File file = Paths.get(downloadDir, fileName).toFile();
        if (file.exists()) {
            boolean deleted = file.delete();
            log("Deleted file: " + fileName + " -> " + deleted);
            return deleted;
        } else {
            log("File not found to delete: " + fileName);
            return false;
        }
    }

    /**
     * Retrieves a file from the download folder based on a partial name match.
     *
     * @param partialName Partial name of the file to search for
     * @return The first matching file, or null if no match is found
     */
    public File getFileByPartialName(String partialName) {
        File folder = new File(downloadDir);
        File[] files = folder.listFiles((dir, name) -> name.contains(partialName) && !name.endsWith(".crdownload"));

        if (files != null && files.length > 0) {
            log("Found file with partial name: " + files[0].getName());
            return files[0];
        } else {
            log("No file found with partial name: " + partialName);
            return null;
        }
    }

    /**
     * Deletes a specific file from the download folder.
     * This method is similar to deleteDownloadedFile but includes additional logging.
     *
     * @param fileName Name of the file to delete
     * @return true if the file is deleted successfully, false otherwise
     */
    public boolean deleteSpecificFile(String fileName) {
        File file = Paths.get(downloadDir, fileName).toFile();

        if (file.exists()) {
            System.out.println("File exists: " + fileName);
            boolean deleted = file.delete();
            System.out.println("Deleted file: " + deleted);
            return deleted;
        } else {
            System.out.println("File not found: " + fileName);
            return false;
        }
    }
}
