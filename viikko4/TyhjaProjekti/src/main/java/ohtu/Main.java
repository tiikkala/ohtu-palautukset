
package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan
        String studentNr = "012345678";
        String course = "1"; 
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats2017.herokuapp.com/students/" + studentNr + "/submissions";
        String courseUrl = "https://ohtustats2017.herokuapp.com/courses/" + course + ".json";
        String bodyText = Request.Get(url).execute().returnContent().asString();
        String courseBodyText = Request.Get(courseUrl).execute().returnContent().asString();

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Course cour = mapper.fromJson(courseBodyText, Course.class);
        System.out.println("Kurssi:" + cour.toString());
        int yht = 0;
        for (Submission submission : subs) {
            System.out.println(submission);
            yht += submission.doneExercises();

        }
        System.out.println("yhteensä tehty: " + yht);
    }
}