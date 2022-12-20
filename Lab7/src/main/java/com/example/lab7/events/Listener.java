package com.example.lab7.events;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Named;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Named("Listener")
@Dependent
public class Listener {

    public void treatEvent(@Observes String message) throws IOException {
        String LOGFILE_PATH = "C:\\Users\\adria\\Desktop\\Java-Technologies-Labs\\Lab7\\src\\main\\resources\\logfile.log";
        Files.write(Paths.get(LOGFILE_PATH), ("[Listener] Received String: " + message + "\n").getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
    }
}
