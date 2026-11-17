package br.fai.lds.projetolds2026.ports_and_adapters.port.service.tools;

import java.io.IOError;
import java.io.IOException;

public interface ResourceFilesService {

    String read(final String resourcePath) throws IOException;
}
