import java.io.InputStream;
import javax.json.JsonReader;
import javax.json.JsonValue;
import org.leadpony.justify.api.JsonSchema;
import org.leadpony.justify.api.JsonValidationService;
import org.leadpony.justify.api.ProblemHandler;

public class App
{
    public static void main(String[] args)
    {
        JsonValidationService service = JsonValidationService.newInstance();
        // Problem handler which will print problems found.
        ProblemHandler handler = service.createProblemPrinter(System.out::println);

        // Reads the JSON schema
        InputStream inputSchema = App.class.getResourceAsStream("/schema.json");
        JsonSchema schema = service.readSchema(inputSchema);

        InputStream inputValue = App.class.getResourceAsStream("/value.json");
        // Reads the JSON instance by javax.json.JsonReader
        try (JsonReader reader = service.createReader(inputValue, schema, handler)) {
            JsonValue value = reader.readValue();
            System.out.println(value);
        }
    }
}
