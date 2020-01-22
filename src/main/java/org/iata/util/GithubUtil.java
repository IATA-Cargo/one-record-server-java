package org.iata.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class GithubUtil {
  private static final Logger LOG = LoggerFactory.getLogger(GithubUtil.class);

  private static final String ONE_RECORD_CARGO_ONTOLOGY_URL = "https://raw.githubusercontent.com/IATA-Cargo/ONE-Record/master/working_draft/ontology/iata.ttl";
  private static final String ONE_RECORD_API_ONTOLOGY_URL = "https://raw.githubusercontent.com/IATA-Cargo/ONE-Record/master/working_draft/ontology/iata.ttl";
  private static final String CARGO_FILE = "iata.ttl";
  private static final String API_FILE = "api_models.ttl";

  public static void main(String[] args) throws Throwable {
    LOG.info("Writing cargo ontology from Github to iata.ttl...");

    try (PrintWriter out = new PrintWriter(CARGO_FILE)) {
      URL githubUrl = new URL(ONE_RECORD_CARGO_ONTOLOGY_URL);
      HttpURLConnection githubHttp = (HttpURLConnection) githubUrl.openConnection();
      InputStream githubStream = githubHttp.getInputStream();
      String githubResponse = getStringFromStream(githubStream);
      out.println(githubResponse);
    }

    LOG.info("Writing API ontology from Github to api_models.ttl...");

    try (PrintWriter out = new PrintWriter(API_FILE)) {
      URL githubUrl = new URL(ONE_RECORD_API_ONTOLOGY_URL);
      HttpURLConnection githubHttp = (HttpURLConnection) githubUrl.openConnection();
      InputStream githubStream = githubHttp.getInputStream();
      String githubResponse = getStringFromStream(githubStream);
      out.println(githubResponse);
    }
  }

  private static String getStringFromStream(InputStream stream) throws IOException {
    if (stream != null) {
      Writer writer = new StringWriter();

      char[] buffer = new char[2048];
      try {
        Reader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        int counter;
        while ((counter = reader.read(buffer)) != -1) {
          writer.write(buffer, 0, counter);
        }
      } finally {
        stream.close();
      }
      return writer.toString();
    } else {
      return "No Contents";
    }
  }
}