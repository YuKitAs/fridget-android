package edu.kit.pse.fridget.client.ServiceTests;

import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

import com.google.gson.Gson;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import edu.kit.pse.fridget.client.activity.CreateFlatshareActivity;
import edu.kit.pse.fridget.client.activity.HomeActivity;
import edu.kit.pse.fridget.client.datamodel.Flatshare;
import edu.kit.pse.fridget.client.datamodel.command.CreateFlatshareCommand;
import edu.kit.pse.fridget.client.service.FlatshareService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.Assert.assertTrue;


/**
 * Diese Klasse testet die Flatshareservice Methoden mit einem MockWebServer
 */
public class FlatshareServiceTest {


    /**
     * Testet die Methode createFlatshare
     * @throws IOException
     */
    Flatshare flatshare =new Flatshare("testId","testName");
    CreateFlatshareCommand createFlatshareCommand = new CreateFlatshareCommand("testName","testUserId");

    MockWebServer server =new MockWebServer();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(server.url("").toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Test
    public void createFlatshare() throws IOException {


        server.enqueue(new MockResponse().setBody(new Gson().toJson(flatshare)));
        FlatshareService service = retrofit.create(FlatshareService.class);
        Call<Flatshare> call =service.createFlatshare(createFlatshareCommand);
        assertTrue(call.execute() != null);
        server.shutdown();
    }


    /**
     * Testet die Methode getFlatshare
     * @throws IOException
     */

    @Test
    public void getFlatshare() throws IOException {

        server.enqueue(new MockResponse().setBody(new Gson().toJson(flatshare)));
        FlatshareService service = retrofit.create(FlatshareService.class);
        Call<Flatshare> call =service.getFlatshare("testId");
        assertTrue(call.execute() != null);
        server.shutdown();
    }


}
