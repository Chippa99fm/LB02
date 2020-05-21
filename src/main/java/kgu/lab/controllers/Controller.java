package kgu.lab.controllers;

import kgu.lab.model.Response;
import kgu.lab.model.Task;
import kgu.lab.repositories.RepositoryImpl;
import kgu.lab.service.Calculation;
import kgu.lab.utils.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller implements AutoCloseable {
    private RepositoryImpl repository;
    private File file;
    private final List<String> tasks;
    private final Calculation calculation;
    private static final String SOURCE_FILE_NAME = "source_file.txt";
    private final BufferedWriter bufferedWriter;

    public Controller() throws IOException {
        file = new File(SOURCE_FILE_NAME);
        tasks = new ArrayList<String>();
        calculation = new Calculation();

        FileWriter fileWriter = new FileWriter(file);
        bufferedWriter = new BufferedWriter(fileWriter);
    }

    @Autowired
    public void setRepository(RepositoryImpl repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/testGet/{id}", method = RequestMethod.GET, produces = "application/json")
    public List<String> testList(@PathVariable String id, HttpServletRequest request) {
        try {
            List<String> testList = new ArrayList<String>();
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                testList.add(line);
                System.out.println(line);
            }

            return testList;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/testPost", method = RequestMethod.POST, produces = "application/json")
    public Response index(@RequestBody Task task, HttpServletRequest request) throws IOException {
        ErrorEnum error = ErrorEnum.SUCCESS;

        if (task.getText() != null) {
            tasks.add(task.getText());
            for (String s : tasks)
                bufferedWriter.write(s + "\n");
        }

        if (task.getText() != null) {
            try {
                repository.addTest(task.getId(), task.getText());
            } catch (Exception e) {
                error = ErrorEnum.SYSTEM_ERROR;
                return ErrorEnum.createResponse(error);
            }
        }
        return ErrorEnum.createResponse(error);
    }

    @RequestMapping(value = "/test1", method = RequestMethod.POST, produces = "application/json")
    public Response index(@RequestBody double test, HttpServletRequest request) throws IOException {
        ErrorEnum error = ErrorEnum.SUCCESS;
        String res = calculation.distanceToLightning(test);

        tasks.add(res);
        for (String s : tasks)
            bufferedWriter.write(s + "\n");

        try {
            repository.addTest(String.valueOf(test), String.valueOf(test));
        } catch (Exception e) {
            error = ErrorEnum.SYSTEM_ERROR;
            return ErrorEnum.createResponse(error);
        }

        return ErrorEnum.createResponse(error);
    }

    @RequestMapping(value = "/test3", method = RequestMethod.POST, produces = "application/json")
    public Response index(@RequestBody String[] names, int[] times, HttpServletRequest request) throws IOException {
        ErrorEnum error = ErrorEnum.SUCCESS;

        String test = calculation.winnerSearch(names, times);

        tasks.add(test);
        for (String s : tasks)
            bufferedWriter.write(s + "\n");

        try {
            repository.addTest(test, test);
        } catch (Exception e) {
            error = ErrorEnum.SYSTEM_ERROR;
            return ErrorEnum.createResponse(error);
        }

        return ErrorEnum.createResponse(error);
    }

    @RequestMapping(value = "/test2", method = RequestMethod.POST, produces = "application/json")
    public Response index(@RequestBody String test, HttpServletRequest request) throws IOException {
        ErrorEnum error = ErrorEnum.SUCCESS;
        String tmp = "";
        test = calculation.flipLine(test, tmp);

        tasks.add(test);
        for (String s : tasks)
            bufferedWriter.write(s + "\n");

        try {
            repository.addTest(tmp, tmp);
        } catch (Exception e) {
            error = ErrorEnum.SYSTEM_ERROR;
            return ErrorEnum.createResponse(error);
        }

        return ErrorEnum.createResponse(error);
    }

    @RequestMapping(value = "/test4", method = RequestMethod.POST, produces = "application/json")
    public Response index(@RequestBody String text) throws IOException {
        ErrorEnum error = ErrorEnum.SUCCESS;
        String test = String.valueOf(calculation.isPalindrome(text));

        tasks.add(test);
        for (String s : tasks)
            bufferedWriter.write(s + "\n");

        try {
            repository.addTest(test, test);
        } catch (Exception e) {
            error = ErrorEnum.SYSTEM_ERROR;
            return ErrorEnum.createResponse(error);
        }

        return ErrorEnum.createResponse(error);
    }

    @RequestMapping(value = "/test5", method = RequestMethod.POST, produces = "application/json")
    public Response index(@RequestBody int day) throws IOException {
        ErrorEnum error = ErrorEnum.SUCCESS;

        int[] tmp = calculation.hoursMinutesSecondsInDays(day);
        String test = tmp[0] + " H, " + tmp[1] + " M, " + tmp[2] + " S";

        tasks.add(test);
        for (String s : tasks)
            bufferedWriter.write(s + "\n");

        try {
            repository.addTest(test, test);
        } catch (Exception e) {
            error = ErrorEnum.SYSTEM_ERROR;
            return ErrorEnum.createResponse(error);
        }
        return ErrorEnum.createResponse(error);
    }

    @Override
    public void close() throws Exception {
        bufferedWriter.close();
    }
}