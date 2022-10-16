package org.laboratories.lab2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "lab2Servlet", value = "/my-other-servlet")
public class Lab2Servlet extends HttpServlet {

    public static final String COMPULSORY_PARAM_NAME = "word";
    public static final String HOMEWORK_PARAM_NAME = "size";
    private static final Logger LOGGER = LogManager.getLogger(Lab2Servlet.class);

    private static final String WORDLIST_PATH = "C:\\Users\\adria\\Desktop\\Java-Technologies-Labs\\Lab1\\src\\main\\resources\\wordlist.txt";
    private static final List<String> dictionary = extractDictionary();

    private static List<String> extractDictionary() {
        List<String> tempDict = new ArrayList<>();
        try {
            File file = new File(WORDLIST_PATH);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                tempDict.add(line.toUpperCase());
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempDict;
    }

    private void permutation(String str, int size, Set<String> result) {
        permutation("", str, size, result);
    }

    private void permutation(String prefix, String str, int size, Set<String> result) {
        int n = str.length();
        int currentSize = prefix.length();
        if (n == 0 || currentSize == size) result.add(prefix);
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), size, result);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Enumeration<String> params = request.getParameterNames();
        List<String> paramsList = new ArrayList<>();
        while (params.hasMoreElements()) {
            String currentParam = params.nextElement();
            paramsList.add(currentParam + " - " + request.getParameter(currentParam));
        }
        LOGGER.info("HTTP method: {}, IP address: {}, user-agent: {}, client language(s): {}, parameters: {}", request.getMethod(), request.getRemoteAddr(), request.getHeader("User-Agent"), request.getHeader("Accept-Language"), paramsList);
        String word = request.getParameter(COMPULSORY_PARAM_NAME);
        int size = Integer.parseInt(request.getParameter(HOMEWORK_PARAM_NAME));
        if (size > word.length())
            size = word.length();
        if (request.getHeader("Accept").contains("text/html")) {
            response.setContentType("text/html");

            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>" + "Compulsory" + "</h1>");
            out.println("</br>");
            out.println("<ol>");
            for (Character c : splitAndOrderLetters(word)) {
                out.println("<li><h2>" + c + "</h2></li>");
            }
            out.println("</ol>");
            out.println("</br>");
            out.println("<h1>" + "Homework" + "</h1>");
            out.println("</br>");

            Set<String> result = getPermutations(word, size);
            out.println("<h3>" + "All the permutations of length " + size + " of word " + word + " are: " + result + "</h3>");
            out.println("<br/>");
            result = result.stream().map(String::toUpperCase).collect(Collectors.toSet());
            result.retainAll(dictionary);
            out.println("<h2>" + "Out of all these, the recognized valid words which match the dictionary are: " + result + "</h2>");
            out.println("</body></html>");
        } else {
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.println(getPermutations(word, size));
        }
    }

    private Set<String> getPermutations(String word, int size) {
        Set<String> result = new HashSet<>();
        if (size == 0) {
            for (int i = 1; i <= word.length(); i++) {
                permutation(word, i, result);
            }
        } else {
            permutation(word, size, result);
        }
        return result;
    }

    private List<Character> splitAndOrderLetters(String word) {
        List<Character> letters = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            letters.add(word.charAt(i));
        }
        return letters;
    }

    public void destroy() {
    }
}