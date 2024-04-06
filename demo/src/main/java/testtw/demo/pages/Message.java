import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.rest.api.v2010.account.MessageInteraction;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TwilioController {

    // Credenciales de Twilio
    public static final String ACCOUNT_SID = "your_account_sid";
    public static final String AUTH_TOKEN = "your_auth_token";

    static {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    @PostMapping("/send")
    public String sendConfirmationMessage() {
        String fromPhoneNumber = "whatsapp:+14155238886";
        String toPhoneNumber = "whatsapp:+573248324121";

        // Crear una lista de opciones de botón (en este caso, confirmar o cancelar)
        List<MessageCreator.MessageTemplateOption> options = new ArrayList<>();
        options.add(new MessageCreator.MessageTemplateOption("confirmar", "Confirmar"));
        options.add(new MessageCreator.MessageTemplateOption("cancelar", "Cancelar"));

        // Crear y enviar el mensaje con botones
        Message message = Message.creator(
                        new PhoneNumber(toPhoneNumber),
                        new PhoneNumber(fromPhoneNumber),
                        new MessageCreator.MessageTemplateBuilder()
                                .setBody("¿Estás seguro de que deseas realizar esta acción?")
                                .setTemplateOptions(options)
                                .build())
                .create();

        System.out.println("Message SID: " + message.getSid());
        return "Mensaje enviado";
    }
}
