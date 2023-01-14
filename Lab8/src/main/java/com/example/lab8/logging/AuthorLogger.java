package com.example.lab8.logging;

import com.example.lab8.annotations.Loggable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Interceptor
@Loggable
public class AuthorLogger implements Serializable {

    @AroundInvoke
    public Object logAddedDoc(InvocationContext ictx) throws Exception {
        String LOGFILE_PATH = "C:\\Users\\adria\\Desktop\\Java-Technologies-Labs\\Lab8\\src\\main\\resources\\logfile.log";
        Files.write(Paths.get(LOGFILE_PATH), ("Entering method...\n").getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        Object result = ictx.proceed();
        Files.write(Paths.get(LOGFILE_PATH), "Leaving Method - Author added Document to DataBase!\n".getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        return result;
    }
}
