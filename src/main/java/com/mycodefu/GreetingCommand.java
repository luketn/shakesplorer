package com.mycodefu;

import com.mycodefu.data.Work;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import javax.inject.Inject;
import java.util.List;

@QuarkusMain
@Command(name = "greeting", mixinStandardHelpOptions = true)
public class GreetingCommand implements Runnable, QuarkusApplication {

    @Inject
    CommandLine.IFactory factory;

    @Parameters(paramLabel = "<name>", defaultValue = "picocli", description = "Your name.")
    String name;

    @Override
    public void run() {
        System.out.print("These are shakespeare's works!\n");
        List<Work> shakespearesWorks = Work.load();
        shakespearesWorks.stream()
                .map(Work::title)
                .forEach(System.out::println);
    }

    @Override
    public int run(String... args) throws Exception {
        return new CommandLine(this, factory).execute(args);
    }

    public static void main(String ...args) {
        Quarkus.run(GreetingCommand.class, args);
    }
}
