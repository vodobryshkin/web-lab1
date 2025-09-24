package minio.generators.implementations;

import minio.generators.interfaces.LinkGenerator;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PutObjectLinkGenerator implements LinkGenerator {
    @Override
    public String generateLink() throws IOException {
        List<String> cmd = Arrays.asList(
                "mc",
                "share",
                "upload",
                "--expire", "30s",
                System.getenv("MC_ALIAS_PATH")
        );

        return new CommandLineExecutor().execute(cmd).split("Share: ")[1];
    }
}
