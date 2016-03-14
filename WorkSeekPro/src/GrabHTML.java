import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GrabHTML
{

    public static void Connect() throws Exception
    {

        // Set URL
        URL url = new URL("https://www.baidu.com");
        URLConnection spoof = url.openConnection();

        // Spoof the connection so we look like a web browser
        spoof.setRequestProperty("User-Agent",
                "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)");
        BufferedReader in = new BufferedReader(new InputStreamReader(
                spoof.getInputStream()));
        String strLine = "";

        // Loop through every line in the source
        while ((strLine = in.readLine()) != null)
        {

            // Prints each line to the console
            System.out.println(strLine);
        }

        System.out.println("End of page.");
    }

    public static void main(String[] args)
    {

        try
        {
            // Calling the Connect method
            Connect();
        }
        catch (Exception e)
        {

        }
    }
}