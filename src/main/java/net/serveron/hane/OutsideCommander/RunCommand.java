package net.serveron.hane.OutsideCommander;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class RunCommand {

    @RequestMapping(value="/command", method= RequestMethod.POST)
    public OutputData run(@RequestBody InputData command) {
        OutputData output = new OutputData();

        if (command == null) return null; // 記載漏れを確認
        String[] commandList = command.command.split(" "); // コマンドを分割

        output.text = runCommand(commandList); // コマンドを実行
        return output;
    }

    public List<String> runCommand(String[] command) {
        List<String> result = new ArrayList<>();

        try {
            String tentativeResult;
            Process process = new ProcessBuilder(command)
                    .redirectOutput(ProcessBuilder.Redirect.PIPE)
                    .redirectError(ProcessBuilder.Redirect.PIPE)
                    .start();

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            BufferedReader brError = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));
            while ((tentativeResult = br.readLine()) != null) {
                result.add(tentativeResult);
            }
            while ((tentativeResult = brError.readLine()) != null) {
                result.add(tentativeResult);
            }
            if (!process.waitFor(60, TimeUnit.SECONDS)) {
                process.destroy();
                result.add("タイムアウトしました。タイムアウト時間は60秒です。コマンドを変更してください");
            }
            result.add("exit: " + process.exitValue());
            process.destroy();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.add("コードの実行に失敗しました。デベロッパーに報告してください");
            return result;
        }
    }
}
