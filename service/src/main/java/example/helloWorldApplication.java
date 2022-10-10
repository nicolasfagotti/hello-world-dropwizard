package example;

import example.resources.Host;
import example.resources.Hello;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class helloWorldApplication extends Application<helloWorldConfiguration> {

    public static void main(final String[] args) throws Exception {
        new helloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "helloWorld";
    }

    @Override
    public void initialize(final Bootstrap<helloWorldConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final helloWorldConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        environment.jersey().register(new Host());
        environment.jersey().register(new Hello());
    }
}
