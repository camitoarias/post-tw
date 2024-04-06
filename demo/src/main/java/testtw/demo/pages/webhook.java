package testtw.demo.pages;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class webhook {

    @RestController
    public class WebhookController {

        private final ObjectMapper objectMapper;

        public WebhookController(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        @PostMapping(value="/webhook")
        public ResponseEntity<String> handleWebhook(@RequestBody String JSON_TWILIO) {
            try {
                // Parsear el JSON utilizando Jackson
                JsonNode jsonNode = objectMapper.readTree(JSON_TWILIO);

                // Extraer campos específicos del JSON según sea necesario
                String mensaje = jsonNode.get("Body").asText();
                String status= jsonNode.get("SmsStatus").asText();
                String remitente = jsonNode.get("From").asText();
                // Ejemplo de cómo obtener un campo específico del JSON
                System.out.println("Mensaje recibido: " + mensaje);
                System.out.println(status);
                System.out.println("este mensaje ah sido enviado desde"+remitente);

                // Devolver una respuesta 200 OK para confirmar la recepción del webhook
                return ResponseEntity.ok("OK");
            } catch (Exception e) {
                // En caso de excepción, devolver un código de estado 500 y un mensaje de error
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar el webhook");
            }
        }
    }
}
